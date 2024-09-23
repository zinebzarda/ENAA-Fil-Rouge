package com.filRouge;


import com.filRouge.dto.FeedbackDTO;
import com.filRouge.model.Feedback;
import com.filRouge.repository.FeedbackRepository;
import com.filRouge.service.FeedbackService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FeedbackServiceTest {

    @Mock
    private FeedbackRepository feedbackRepository;

    @InjectMocks
    private FeedbackService feedbackService;

    private Feedback feedback;
    private FeedbackDTO feedbackDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialiser les mocks

        feedback = new Feedback();
        feedback.setNote(4);
        feedback.setCommentaire("Très bon service");
        feedback.setDateCreation(LocalDate.now());

        feedbackDTO = new FeedbackDTO();
        feedbackDTO.setNote(4);
        feedbackDTO.setCommentaire("Très bon service");
        feedbackDTO.setDateCreation(LocalDate.now());
    }

    @Test
    void testCreateFeedback() {
        when(feedbackRepository.save(any(Feedback.class))).thenReturn(feedback);

        FeedbackDTO savedFeedbackDTO = feedbackService.createFeedback(feedbackDTO);

        assertNotNull(savedFeedbackDTO);
        assertEquals(feedbackDTO.getNote(), savedFeedbackDTO.getNote());
        assertEquals(feedbackDTO.getCommentaire(), savedFeedbackDTO.getCommentaire());

        verify(feedbackRepository, times(1)).save(any(Feedback.class));
    }

    @Test
    void testGetAllFeedbacks() {
        List<Feedback> feedbackList = new ArrayList<>();
        feedbackList.add(feedback);

        when(feedbackRepository.findAll()).thenReturn(feedbackList);

        List<FeedbackDTO> feedbackDTOList = feedbackService.getAllFeedbacks();

        assertNotNull(feedbackDTOList);
        assertEquals(1, feedbackDTOList.size());

        FeedbackDTO firstFeedbackDTO = feedbackDTOList.get(0);
        assertEquals(feedback.getNote(), firstFeedbackDTO.getNote());
        assertEquals(feedback.getCommentaire(), firstFeedbackDTO.getCommentaire());

        verify(feedbackRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        when(feedbackRepository.findById(1L)).thenReturn(Optional.of(feedback));

        FeedbackDTO foundFeedbackDTO = feedbackService.findById(1L);

        assertNotNull(foundFeedbackDTO);
        assertEquals(feedback.getNote(), foundFeedbackDTO.getNote());
        assertEquals(feedback.getCommentaire(), foundFeedbackDTO.getCommentaire());

        verify(feedbackRepository, times(1)).findById(1L);
    }

    @Test
    void testFindByIdNotFound() {
        when(feedbackRepository.findById(1L)).thenReturn(Optional.empty());

        FeedbackDTO foundFeedbackDTO = feedbackService.findById(1L);

        assertNull(foundFeedbackDTO);

        verify(feedbackRepository, times(1)).findById(1L);
    }

    @Test
    void testDeleteFeedback() {
        feedbackService.deleteFeedback(1L);

        verify(feedbackRepository, times(1)).deleteById(1L);
    }
}
