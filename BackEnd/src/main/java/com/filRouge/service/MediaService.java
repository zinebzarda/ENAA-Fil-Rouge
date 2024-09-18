package com.filRouge.service;

import com.filRouge.dto.CloudinaryResponse;
import com.filRouge.model.Media;
import com.filRouge.model.Services;
import com.filRouge.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class MediaService {

        @Autowired
        private CloudinaryService cloudinaryService;

        public Media handleMediaUpload(MultipartFile file, Services services) {
            FileUploadUtil.assertAllowed(file, FileUploadUtil.MEDIA_PATTERN);
            String fileName = FileUploadUtil.getFileName(file.getOriginalFilename());
            CloudinaryResponse response = cloudinaryService.uploadFile(file, fileName, "image");

            Media media = new Media();
            media.setMediaUrl(response.getUrl());
            media.setMediaId(response.getPublicId());
            media.setService(services);

            return media;
        }
    }