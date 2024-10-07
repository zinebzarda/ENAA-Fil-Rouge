package com.filRouge.service;

import com.filRouge.model.Review;
import com.filRouge.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;


    public Review saveReview(Review review) {
        return reviewRepository.save(review);
    }


    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    public Optional<Review> getReviewById(Long id) {
        return reviewRepository.findById(id);
    }


    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }


    public List<Review> getReviewsByClientId(Long clientId) {
        return reviewRepository.findByClientId(clientId);
    }


    public List<Review> getReviewsByServiceId(Long serviceId) {
        return reviewRepository.findByServicesId(serviceId);
    }
}
