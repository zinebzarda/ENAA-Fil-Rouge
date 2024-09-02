package com.filRouge.service;

import com.filRouge.model.Feedback;
import com.filRouge.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    public Feedback createFeedback(Feedback feedback) {
        return feedbackRepository.save(feedback);
    }


    public List<Feedback> getAllFeedbacks() {
        return feedbackRepository.findAll();
    }

    public Feedback findById(Long id) {
        return feedbackRepository.findById(id).orElse(null);
    }

    public void deleteFeedback(Long id) {
        feedbackRepository.deleteById(id);
    }

}