package com.octopus.taxcube.eds.service.impl;

import com.octopus.taxcube.eds.service.IUploadService;
import com.octopus.taxcube.exception.IllegalException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
@Slf4j
public class UploadServiceImpl implements IUploadService {
    @Override
    public String upload(MultipartFile file, String localPath) {

        String fileName = file.getOriginalFilename();
        //扩展名
        //abc.jpg
        String fileExtensionName = fileName.substring(fileName.lastIndexOf(".") + 1);
        String uploadFileName = UUID.randomUUID().toString() + "." + fileExtensionName;
        log.info("开始上传文件,上传文件的文件名:{},上传的路径:{},新文件名:{}", fileName, localPath, uploadFileName);

        File fileDir = new File(localPath);
        if (!fileDir.exists()) {
            fileDir.setWritable(true);
            fileDir.mkdirs();
        }
        File targetFile = new File(localPath, uploadFileName);

        try {
            file.transferTo(targetFile);
            //文件已经上传成功了

//            Boolean aBoolean = COSUtil.uploadFile(cosPathPrefix + uploadFileName, targetFile);
//            if (!aBoolean) {
//                throw new IllegalException("COS上传失败");
//            }
            //已经上传到Cos

//            targetFile.delete();
        } catch (IOException e) {
            log.error("上传文件异常", e);
            return null;
        } catch (IllegalException e) {
            e.printStackTrace();
        }

        return targetFile.getName();
    }
}
