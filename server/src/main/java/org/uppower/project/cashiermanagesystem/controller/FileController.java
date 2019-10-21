package org.uppower.project.cashiermanagesystem.controller;

import cn.windyrjc.utils.response.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.uppower.project.cashiermanagesystem.CashierManageSystemApplication;
import org.uppower.project.cashiermanagesystem.exceptions.ServerException;
import org.uppower.project.cashiermanagesystem.minio.MinioTemplate;
import org.uppower.project.cashiermanagesystem.model.result.FileInfoResult;
import org.uppower.project.cashiermanagesystem.utils.AttachmentUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * █████▒█      ██  ▄████▄   ██ ▄█▀       ██████╗ ██╗   ██╗ ██████╗
 * ▓██   ▒ ██  ▓██▒▒██▀ ▀█   ██▄█▒        ██╔══██╗██║   ██║██╔════╝
 * ▒████ ░▓██  ▒██░▒▓█    ▄ ▓███▄░        ██████╔╝██║   ██║██║  ███╗
 * ░▓█▒  ░▓▓█  ░██░▒▓▓▄ ▄██▒▓██ █▄        ██╔══██╗██║   ██║██║   ██║
 * ░▒█░   ▒▒█████▓ ▒ ▓███▀ ░▒██▒ █▄       ██████╔╝╚██████╔╝╚██████╔╝
 * ▒ ░   ░▒▓▒ ▒ ▒ ░ ░▒ ▒  ░▒ ▒▒ ▓▒       ╚═════╝  ╚═════╝  ╚═════╝
 * ░     ░░▒░ ░ ░   ░  ▒   ░ ░▒ ▒░
 * ░ ░    ░░░ ░ ░ ░        ░ ░░ ░
 * ░     ░ ░      ░  ░
 *
 * @author ：涂齐康
 * @date ：Created in 2019/10/21 4:07 下午
 * @description：
 * @modified By：
 * @version:
 */
@Api(value = "文件上传下载接口")
@RestController
@RequestMapping("/file")
@Slf4j
public class FileController {

    @Autowired
    private MinioTemplate minioTemplate;

    @Autowired
    private AttachmentUtils attachmentUtils;

    @ApiOperation("文件上传")
    @PostMapping("/upload")
    @Transactional(rollbackFor = Exception.class)
    public Response<FileInfoResult> upload(@ApiParam(required = true, value = "二进制文件流 file") @RequestParam MultipartFile file) {
        try {
            String md5 = DigestUtils.md5DigestAsHex(file.getBytes());
            minioTemplate.upload(CashierManageSystemApplication.BUCKET_NAME, md5 + "-" + file.getOriginalFilename(), file.getInputStream());
            String s3Name = CashierManageSystemApplication.BUCKET_NAME + "-" + md5 + "-" + file.getOriginalFilename();
            return Response.success(attachmentUtils.getFileInfoResult(s3Name));
        } catch (Exception e) {
            log.error("上传失败", e);
            throw new ServerException("上传失败!");
        }
    }

    @ApiOperation("文件下载,请求里面加个storeName属性")
    @GetMapping("/download")
    public void download(HttpServletRequest request,
                         HttpServletResponse response) throws Exception {
        try {
            String storeName = request.getParameter("storeName");
            String[] split = storeName.split("-", 2);
            InputStream object = minioTemplate.getObject(CashierManageSystemApplication.BUCKET_NAME, split[1]);
//            response.setHeader("Content-Disposition", "attachment;filename=" + split[1]);
            setFileDownloadHeader(response, split[1].split("-", 2)[1]);
            response.setHeader("Content-Type", "application/octet-stream");
            copyStream(object, response.getOutputStream());
            response.getOutputStream().flush();
        } catch (Exception e) {
            throw new ServerException("请检查文件名是否正确!");
        }
    }

    /**
     * @param ips 输入流
     * @param ops 输出流
     * @throws Exception
     */
    private static void copyStream(InputStream ips, OutputStream ops) throws Exception {
        byte[] buf = new byte[20480];
        int len = ips.read(buf);
        while (len != -1) {
            ops.write(buf, 0, len);
            len = ips.read(buf);
        }
    }

    /**
     * <pre>
     * 浏览器下载文件时需要在服务端给出下载的文件名，当文件名是ASCII字符时没有问题
     * 当文件名有非ASCII字符时就有可能出现乱码
     *
     * 这里的实现方式参考这篇文章
     * http://blog.robotshell.org/2012/deal-with-http-header-encoding-for-file-download/
     *
     * 最终设置的response header是这样:
     *
     * Content-Disposition: attachment;
     *                      filename="encoded_text";
     *                      filename*=utf-8''encoded_text
     *
     * 其中encoded_text是经过RFC 3986的“百分号URL编码”规则处理过的文件名
     * </pre>
     *
     * @param response
     * @param filename
     * @return
     */
    public static void setFileDownloadHeader(HttpServletResponse response, String filename) {
        String headerValue = "attachment;";
        headerValue += " filename=\"" + encodeUriComponent(filename) + "\";";
        headerValue += " filename*=utf-8''" + encodeUriComponent(filename);
        response.setHeader("Content-Disposition", headerValue);
    }

    /**
     * <pre>
     * 符合 RFC 3986 标准的“百分号URL编码”
     * 在这个方法里，空格会被编码成%20，而不是+
     * 和浏览器的encodeURIComponent行为一致
     * </pre>
     *
     * @param value
     * @return
     */
    public static String encodeUriComponent(String value) {
        try {
            return URLEncoder.encode(value, "UTF-8")
                    .replaceAll("\\+", "%20");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

}
