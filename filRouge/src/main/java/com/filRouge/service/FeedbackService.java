package com.filRouge.service;

import com.filRouge.dto.FeedbackDTO;
import com.filRouge.model.Feedback;
import com.filRouge.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    public FeedbackDTO createFeedback(FeedbackDTO feedbackDTO) {
        Feedback feedback = new Feedback();
        feedback.setNote(feedbackDTO.getNote());
        feedback.setCommentaire(feedbackDTO.getCommentaire());
        feedback.setDateCreation(feedbackDTO.getDateCreation());
        Feedback savedFeedback = feedbackRepository.save(feedback);

        // Convert saved entity back to DTO
        FeedbackDTO savedFeedbackDTO = new FeedbackDTO();
        savedFeedbackDTO.setNote(savedFeedback.getNote());
        savedFeedbackDTO.setCommentaire(savedFeedback.getCommentaire());
        savedFeedbackDTO.setDateCreation(savedFeedback.getDateCreation());

        return savedFeedbackDTO;
    }

    public List<FeedbackDTO> getAllFeedbacks() {
        return feedbackRepository.findAll().stream()
                .map(feedback -> {
                    FeedbackDTO dto = new FeedbackDTO();
                    dto.setNote(feedback.getNote());
                    dto.setCommentaire(feedback.getCommentaire());
                    dto.setDateCreation(feedback.getDateCreation());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    public FeedbackDTO findById(Long id) {
        Feedback feedback = feedbackRepository.findById(id).orElse(null);
        if (feedback == null) {
            return null;
        }
        FeedbackDTO dto = new FeedbackDTO();
        dto.setNote(feedback.getNote());
        dto.setCommentaire(feedback.getCommentaire());
        dto.setDateCreation(feedback.getDateCreation());
        return dto;
    }

    public void deleteFeedback(Long id) {
        feedbackRepository.deleteById(id);
    }
}
