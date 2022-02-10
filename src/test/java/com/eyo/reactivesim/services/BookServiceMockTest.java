package com.eyo.reactivesim.services;

import com.eyo.reactivesim.exception.BookException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class BookServiceMockTest {

    @Mock
    private BookInfoService bookInfoService;
    @Mock
    private ReviewService reviewService;
    @InjectMocks
    private BookService bookService;

    @Test
    void getBooksMock() {
        Mockito.when(bookInfoService.getBooks()).thenCallRealMethod();
        Mockito.when(reviewService.getReviewsByBookId(Mockito.anyLong())).thenCallRealMethod();
        var books  = bookService.getBooks();
        StepVerifier.create(books)
                .expectNextCount(3)
                .verifyComplete();
    }
    @Test
    void getBooksMockOnError() {
        Mockito.when(bookInfoService.getBooks()).thenCallRealMethod();
        Mockito.when(reviewService.getReviewsByBookId(Mockito.anyLong()))
                .thenThrow(new IllegalStateException("exception using test"));
        var books  = bookService.getBooks();
        StepVerifier.create(books)
                .expectError(BookException.class)
                .verify();
    }
}