package com.octopus.taxcube.eds.service;

import org.springframework.web.multipart.MultipartFile;

public interface IUploadService {
    String upload(MultipartFile file, String localPath);
}
