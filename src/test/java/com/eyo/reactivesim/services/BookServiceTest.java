package com.eyo.reactivesim.services;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookServiceTest {

    private BookInfoService bookInfoService = new BookInfoService();
    private ReviewService reviewService = new ReviewService();

    private final BookService bookService = new BookService(bookInfoService, reviewService);

    @Test
    void getBooks() {
        var books = bookService.getBooks();

        StepVerifier.create(books)
                .assertNext(book -> {
                    assertEquals("Book one", book.getBookInfo().getTitle());
                    assertEquals(2, book.getReviews().size());
                }).assertNext(book -> {
                    assertEquals("Book two", book.getBookInfo().getTitle());
                    assertEquals(2, book.getReviews().size());
                }).assertNext(book -> {
                    assertEquals("Book three", book.getBookInfo().getTitle());
                    assertEquals(2, book.getReviews().size());
                }).verifyComplete();
    }
}