package com.filRouge.controller;

import com.filRouge.dto.FeedbackDTO;
import com.filRouge.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @PostMapping
    public FeedbackDTO createFeedback(@RequestBody FeedbackDTO feedbackDTO) {
        return feedbackService.createFeedback(feedbackDTO);
    }

    @GetMapping
    public List<FeedbackDTO> getAllFeedbacks() {
        return feedbackService.getAllFeedbacks();
    }

    @GetMapping("/{id}")
    public FeedbackDTO getFeedbackById(@PathVariable Long id) {
        return feedbackService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteFeedback(@PathVariable Long id) {
        feedbackService.deleteFeedback(id);
    }
}
