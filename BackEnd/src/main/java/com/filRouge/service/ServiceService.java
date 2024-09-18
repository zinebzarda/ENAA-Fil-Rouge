package com.filRouge.service;

import com.filRouge.dto.ServiceDTO;
import com.filRouge.exception.ResourceNotFoundException;
import com.filRouge.model.Media;
import com.filRouge.model.Prestataire;
import com.filRouge.model.Services;
import com.filRouge.model.enums.Type;
import com.filRouge.model.enums.ValidateStatus;
import com.filRouge.repository.ServiceRepository;
import com.filRouge.util.FileUploadUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceService {



        @Autowired
        private ServiceRepository serviceRepository;

        @Autowired
        MediaService mediaService;


    @Transactional
    public Services createServiceWithImages(Services service, List<MultipartFile> attachments, Prestataire prestataire) {
        if (prestataire.getValidateStatus().equals(ValidateStatus.EN_ATTENTE)) {
            throw new ResourceNotFoundException("Prestataire en attente");
        }

        if (attachments.isEmpty()) {
            throw new IllegalArgumentException("No media files provided.");
        }

        List<MultipartFile> photos = new ArrayList<>();
        MultipartFile video = null;

        for (MultipartFile file : attachments) {
            String fileType = file.getContentType();
            if (fileType != null && fileType.startsWith("video")) {
                if (video != null) {
                    throw new IllegalArgumentException("Only one video is allowed.");
                }
                video = file;
            } else if (fileType != null && fileType.startsWith("image")) {
                photos.add(file);
            } else {
                throw new IllegalArgumentException("Invalid file type.");
            }
        }

        if (video != null && photos.size() > 9) {
            throw new IllegalArgumentException("With a video, only up to 10 photos are allowed.");
        }

        if (video == null && photos.size() > 10) {
            throw new IllegalArgumentException("Cannot upload more than 10 photos.");
        }

        List<Media> mediaList = new ArrayList<>();
        if (video != null) {
            Media videoMedia = mediaService.handleMediaUpload(video, service);
            videoMedia.setType(Type.VIDEO);
            mediaList.add(videoMedia);
        }

        for (MultipartFile file : photos) {
            Media media = mediaService.handleMediaUpload(file, service);
            media.setType(Type.PHOTO);
            mediaList.add(media);
        }

        service.setMedia(mediaList);

        return serviceRepository.save(service);
    }
    public Optional<Services> findById(Long id) {
            return serviceRepository.findById(id);
        }

        public void deleteService(Long id) {
            serviceRepository.deleteById(id);
        }

        public List<Services> getAllServices() {
            return serviceRepository.findAll();
        }



        public Services updateService(Long id, Services serviceDetails) {
            Services existingService = serviceRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Service non trouvé avec l'id : " + id));

            existingService.setTitre(serviceDetails.getTitre());
            existingService.setDescription(serviceDetails.getDescription());
            existingService.setPrix(serviceDetails.getPrix());
            return serviceRepository.save(existingService);
        }

    public List<Services> searchServices(String titre) {
        return serviceRepository.findByTitreContaining(titre);
    }
    }


