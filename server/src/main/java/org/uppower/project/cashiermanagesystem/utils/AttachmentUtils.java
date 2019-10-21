package org.uppower.project.cashiermanagesystem.utils;

import io.minio.ObjectStat;
import lombok.extern.slf4j.Slf4j;
import okhttp3.HttpUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.uppower.project.cashiermanagesystem.minio.MinioTemplate;
import org.uppower.project.cashiermanagesystem.model.result.FileInfoResult;

import java.util.List;
import java.util.stream.Collectors;

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
 * @date ：Created in 2019/10/21 4:16 下午
 * @description：
 * @modified By：
 * @version:
 */
@Service
@Slf4j
public class AttachmentUtils {

    @Autowired
    private MinioTemplate minioTemplate;

    /**
     * 通过文件名拿到文件信息
     *
     * @param s3Name
     * @return
     */
    public FileInfoResult getFileInfoResult(String s3Name) {
        String url = minioTemplate.getObjectURL(s3Name, 24 * 60 * 60);
        ObjectStat info = minioTemplate.getObjectInfo(s3Name);
        if (info == null) {
            return null;
        }
        String name = info.name().split("-", 2)[1];
        return new FileInfoResult(name, s3Name, url, info.length());
    }

}
