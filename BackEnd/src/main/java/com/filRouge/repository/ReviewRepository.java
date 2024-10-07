package com.filRouge.repository;

import com.filRouge.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

        List<Review> findByClientId(Long clientId);


        List<Review> findByServicesId(Long serviceId);
}
