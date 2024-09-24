package com.filRouge.service;


import com.filRouge.exception.ResourceNotFoundException;
import com.filRouge.model.Feedback;
import com.filRouge.model.DemandeService;
import com.filRouge.repository.FeedbackRepository;
import com.filRouge.repository.DemandeServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private DemandeServiceRepository demandeServiceRepository;

    public Feedback createFeedback(Long demandeServiceId, int note, String commentaire) {
        DemandeService demandeService = demandeServiceRepository.findById(demandeServiceId)
                .orElseThrow(() -> new ResourceNotFoundException("DemandeService non trouvée avec l'id : " + demandeServiceId));

        Feedback feedback = new Feedback();
        feedback.setNote(note);
        feedback.setCommentaire(commentaire);
        feedback.setDateCreation(LocalDate.now());
        feedback.setDemandeService(demandeService);

        return feedbackRepository.save(feedback);
    }

    public List<Feedback> getAllFeedbacks() {
        return feedbackRepository.findAll();
    }

    public Feedback getFeedbackById(Long id) {
        return feedbackRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Feedback non trouvé avec l'id : " + id));
    }

    public Feedback updateFeedback(Long id, int note, String commentaire) {
        Feedback existingFeedback = feedbackRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Feedback non trouvé avec l'id : " + id));

        existingFeedback.setNote(note);
        existingFeedback.setCommentaire(commentaire);

        return feedbackRepository.save(existingFeedback);
    }

    public void deleteFeedback(Long id) {
        Feedback feedback = feedbackRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Feedback non trouvé avec l'id : " + id));
        feedbackRepository.delete(feedback);
    }
}
