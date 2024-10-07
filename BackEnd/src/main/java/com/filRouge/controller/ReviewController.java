package com.filRouge.controller;

import com.filRouge.model.Review;
import com.filRouge.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;


    @PostMapping
    public ResponseEntity<Review> createReview(@RequestBody Review review) {
        Review savedReview = reviewService.saveReview(review);
        return ResponseEntity.ok(savedReview);
    }


    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews() {
        List<Review> reviews = reviewService.getAllReviews();
        return ResponseEntity.ok(reviews);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long id) {
        Optional<Review> review = reviewService.getReviewById(id);
        return review.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<Review>> getReviewsByClientId(@PathVariable Long clientId) {
        List<Review> reviews = reviewService.getReviewsByClientId(clientId);
        return ResponseEntity.ok(reviews);
    }


    @GetMapping("/service/{serviceId}")
    public ResponseEntity<List<Review>> getReviewsByServiceId(@PathVariable Long serviceId) {
        List<Review> reviews = reviewService.getReviewsByServiceId(serviceId);
        return ResponseEntity.ok(reviews);
    }
}
