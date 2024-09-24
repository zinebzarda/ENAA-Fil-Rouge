package com.filRouge;

import com.filRouge.exception.ResourceNotFoundException;
import com.filRouge.model.Feedback;
import com.filRouge.model.DemandeService;
import com.filRouge.repository.FeedbackRepository;
import com.filRouge.repository.DemandeServiceRepository;
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

    @Mock
    private DemandeServiceRepository demandeServiceRepository;

    @InjectMocks
    private FeedbackService feedbackService;

    private Feedback feedback;
    private DemandeService demandeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialiser les mocks

        // Initialiser les objets Feedback et DemandeService
        demandeService = new DemandeService(); // Assurez-vous d'initialiser avec les valeurs nécessaires
        feedback = new Feedback();
        feedback.setNote(4);
        feedback.setCommentaire("Très bon service");
        feedback.setDateCreation(LocalDate.now());
        feedback.setDemandeService(demandeService);
    }

    @Test
    void testCreateFeedback() {
        // Configuration du mock pour la demande de service
        when(demandeServiceRepository.findById(anyLong())).thenReturn(Optional.of(demandeService));
        when(feedbackRepository.save(any(Feedback.class))).thenReturn(feedback);

        // Appel du service pour créer un feedback
        Feedback createdFeedback = feedbackService.createFeedback(1L, 4, "Très bon service");

        // Assertions
        assertNotNull(createdFeedback);
        assertEquals(feedback.getNote(), createdFeedback.getNote());
        assertEquals(feedback.getCommentaire(), createdFeedback.getCommentaire());

        // Vérification des interactions avec le mock
        verify(demandeServiceRepository, times(1)).findById(1L);
        verify(feedbackRepository, times(1)).save(any(Feedback.class));
    }

    @Test
    void testGetAllFeedbacks() {
        List<Feedback> feedbackList = new ArrayList<>();
        feedbackList.add(feedback);

        when(feedbackRepository.findAll()).thenReturn(feedbackList);

        List<Feedback> feedbacks = feedbackService.getAllFeedbacks();

        assertNotNull(feedbacks);
        assertEquals(1, feedbacks.size());

        Feedback firstFeedback = feedbacks.get(0);
        assertEquals(feedback.getNote(), firstFeedback.getNote());
        assertEquals(feedback.getCommentaire(), firstFeedback.getCommentaire());

        verify(feedbackRepository, times(1)).findAll();
    }

    @Test
    void testGetFeedbackById() {
        when(feedbackRepository.findById(1L)).thenReturn(Optional.of(feedback));

        Feedback foundFeedback = feedbackService.getFeedbackById(1L);

        assertNotNull(foundFeedback);
        assertEquals(feedback.getNote(), foundFeedback.getNote());
        assertEquals(feedback.getCommentaire(), foundFeedback.getCommentaire());

        verify(feedbackRepository, times(1)).findById(1L);
    }

    @Test
    void testGetFeedbackByIdNotFound() {
        when(feedbackRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            feedbackService.getFeedbackById(1L);
        });

        assertEquals("Feedback non trouvé avec l'id : 1", exception.getMessage());
        verify(feedbackRepository, times(1)).findById(1L);
    }

    @Test
    void testUpdateFeedback() {
        when(feedbackRepository.findById(1L)).thenReturn(Optional.of(feedback));
        when(feedbackRepository.save(any(Feedback.class))).thenReturn(feedback);

        Feedback updatedFeedback = feedbackService.updateFeedback(1L, 5, "Service excellent");

        assertNotNull(updatedFeedback);
        assertEquals(5, updatedFeedback.getNote());
        assertEquals("Service excellent", updatedFeedback.getCommentaire());

        verify(feedbackRepository, times(1)).findById(1L);
        verify(feedbackRepository, times(1)).save(any(Feedback.class));
    }

    @Test
    void testDeleteFeedback() {
        when(feedbackRepository.findById(1L)).thenReturn(Optional.of(feedback));

        feedbackService.deleteFeedback(1L);

        verify(feedbackRepository, times(1)).delete(feedback);
    }

    @Test
    void testDeleteFeedbackNotFound() {
        when(feedbackRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            feedbackService.deleteFeedback(1L);
        });

        assertEquals("Feedback non trouvé avec l'id : 1", exception.getMessage());
        verify(feedbackRepository, times(1)).findById(1L);
    }
}
