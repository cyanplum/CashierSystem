package org.uppower.project.cashiermanagesystem.minio;

import io.minio.MinioClient;
import io.minio.ObjectStat;
import io.minio.messages.Bucket;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
 * @date ：Created in 2019/10/21 3:55 下午
 * @description：
 * @modified By：
 * @version:
 */
@AllArgsConstructor
@Slf4j
public class MinioTemplate {

    private MinioClient minioClient;

    /**
     * 创建bucket
     *
     * @param bucketName bucket名称
     */
    @SneakyThrows
    public void createBucket(String bucketName) {
        if (!minioClient.bucketExists(bucketName)) {
            minioClient.makeBucket(bucketName);
        }
    }

    /**
     * 获取全部Bucket
     *
     * @return
     */
    @SneakyThrows
    public List<Bucket> getAllBuckets() {
        return minioClient.listBuckets();
    }

    /**
     * 通过bucketName得到bucket信息
     *
     * @param bucketName bucket名称
     */
    @SneakyThrows
    public Optional<Bucket> getBucket(String bucketName) {
        return minioClient.listBuckets().stream().filter(b -> b.name().equals(bucketName)).findFirst();
    }

    /**
     * @param bucketName bucket名称
     */
    @SneakyThrows
    public void removeBucket(String bucketName) {
        minioClient.removeBucket(bucketName);
    }


    /**
     * @param bucketName  bucket名称
     * @param file        文件
     * @param stream      文件流
     * @param contentType 类型
     * @throws Exception https://docs.minio.io/cn/java-client-api-reference.html#putObject
     */
    public void upload(String bucketName, File file, InputStream stream, String contentType) throws Exception {
        minioClient.putObject(bucketName, file.getName(), stream, file.length(), contentType);
    }

    /**
     * @param bucketName  bucket名称
     * @param file        文件
     * @param bytes       文件字节数组
     * @param contentType 类型
     * @throws Exception https://docs.minio.io/cn/java-client-api-reference.html#putObject
     */
    public void upload(String bucketName, File file, byte[] bytes, String contentType) throws Exception {
        minioClient.putObject(bucketName, file.getName(), new ByteArrayInputStream(bytes), contentType);
    }

    /**
     * 上传文件
     *
     * @param bucketName bucket名称
     * @param objectName 文件名称
     * @param stream     文件流
     * @throws Exception https://docs.minio.io/cn/java-client-api-reference.html#putObject
     */
    public void upload(String bucketName, String objectName, InputStream stream) throws Exception {
        minioClient.putObject(bucketName, objectName, stream, stream.available(), "application/octet-stream");
    }

    /**
     * 上传文件
     *
     * @param bucketName  bucket名称
     * @param objectName  文件名称
     * @param stream      文件流
     * @param size        大小
     * @param contextType 类型
     * @throws Exception https://docs.minio.io/cn/java-client-api-reference.html#putObject
     */
    public void upload(String bucketName, String objectName, InputStream stream, long size, String contextType) throws Exception {
        minioClient.putObject(bucketName, objectName, stream, size, contextType);
    }

    /**
     * 获取文件
     *
     * @param bucketName bucket名称
     * @param objectName 文件名称
     * @return 二进制流
     */
    @SneakyThrows
    public InputStream getObject(String bucketName, String objectName) {
        return minioClient.getObject(bucketName, objectName);
    }

    /**
     * 获取文件信息
     *
     * @param bucketName bucket名称
     * @param objectName 文件名称
     * @throws Exception https://docs.minio.io/cn/java-client-api-reference.html#statObject
     */
    public ObjectStat getObjectInfo(String bucketName, String objectName) throws Exception {
        return minioClient.statObject(bucketName, objectName);
    }


    /**
     * 获取文件信息
     *
     * @throws Exception https://docs.minio.io/cn/java-client-api-reference.html#statObject
     */
    public ObjectStat getObjectInfo(String fileName) {
        String[] split = fileName.split("-", 2);
        try {
            return minioClient.statObject(split[0], split[1]);
        } catch (Exception ignored) {
            log.error("文件信息获取失败! {}", ignored.getMessage());
        }
        return null;
    }


    /**
     * 删除文件
     *
     * @param bucketName bucket名称
     * @param objectName 文件名称
     * @throws Exception https://docs.minio.io/cn/java-client-api-reference.html#removeObject
     */
    public void removeObject(String bucketName, String objectName) throws Exception {
        minioClient.removeObject(bucketName, objectName);
    }

    /**
     * 获取文件外链
     *
     * @param bucketName bucket名称
     * @param objectName 文件名称
     * @param expires    过期时间 <=
     * @return url
     */
    @SneakyThrows
    public String getObjectURL(String bucketName, String objectName, Integer expires) {
        return minioClient.presignedGetObject(bucketName, objectName, expires);
    }

    /**
     * 获取文件外链
     *
     * @param fileName 组合文件名
     * @param expires  过期时间 <=7
     * @return url
     */
    @SneakyThrows
    public String getObjectURL(String fileName, Integer expires) {
        MinioFilenameDTO dto = MinioFilenameDTO.urlToModel(fileName);
        if (dto == null) {
            return null;
        }
        Map<String, String> params = new LinkedHashMap<>();
        params.put("response-content-disposition", "attachment;filename=" + dto.getOriginName());
        return minioClient.presignedGetObject(dto.getBucketName(), dto.getMd5() + "-" + dto.getOriginName(), expires, params);
    }

    /**
     * 获取文件外链
     *
     * @param fileName 组合文件名
     * @return url
     */
    @SneakyThrows
    public String getObjectURL(String fileName) {
        MinioFilenameDTO dto = MinioFilenameDTO.urlToModel(fileName);
        return minioClient.presignedGetObject(dto.getBucketName(), dto.getMd5() + "-" + dto.getOriginName(), 24 * 60 * 60);
    }


}
