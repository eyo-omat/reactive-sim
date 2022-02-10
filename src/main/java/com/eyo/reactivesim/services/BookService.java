package com.eyo.reactivesim.services;

import com.eyo.reactivesim.domain.Book;
import com.eyo.reactivesim.domain.Review;
import com.eyo.reactivesim.exception.BookException;
import lombok.extern.slf4j.Slf4j;
import reactor.core.Exceptions;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;
import reactor.util.retry.RetryBackoffSpec;

import java.time.Duration;
import java.util.List;

@Slf4j
public class BookService {

    private BookInfoService infoService;
    private ReviewService reviewService;

    public BookService(BookInfoService infoService, ReviewService reviewService) {
        this.infoService = infoService;
        this.reviewService = reviewService;
    }


    public Flux<Book> getBooks() {
        var allBooks = infoService.getBooks();
        return allBooks
                .flatMap(bookInfo -> {
                    Mono<List<Review>> reviews = reviewService.getReviewsByBookId(bookInfo.getBookId()).collectList();
                    return reviews
                            .map(review -> new Book(bookInfo, review));
                })
                .onErrorMap(throwable -> {
                    log.error("Exception is: " + throwable);
                    return new BookException("Exception occurred while fetching book");
                })
                .log();
    }

    public Flux<Book> getBooksRetry() {
        var allBooks = infoService.getBooks();
        return allBooks
                .flatMap(bookInfo -> {
                    Mono<List<Review>> reviews = reviewService.getReviewsByBookId(bookInfo.getBookId()).collectList();
                    return reviews
                            .map(review -> new Book(bookInfo, review));
                })
                .onErrorMap(throwable -> {
                    log.error("Exception is: " + throwable);
                    return new BookException("Exception occurred while fetching book");
                })
                .retry(3)
                .log();
    }

    public Flux<Book> getBooksRetryWhen() {
        //var retrySpec = getRetryBackoffSpec();

        var allBooks = infoService.getBooks();
        return allBooks
                .flatMap(bookInfo -> {
                    Mono<List<Review>> reviews = reviewService.getReviewsByBookId(bookInfo.getBookId()).collectList();
                    return reviews
                            .map(review -> new Book(bookInfo, review));
                })
                .onErrorMap(throwable -> {
                    log.error("Exception is: " + throwable);
                    return new BookException("Exception occurred while fetching book");
                })
                .retryWhen(getRetryBackoffSpec())
                .log();
    }

    private RetryBackoffSpec getRetryBackoffSpec() {
        return Retry.backoff(3, Duration.ofMillis(1000))
                .filter(throwable -> throwable instanceof BookException)
                .onRetryExhaustedThrow(((retryBackoffSpec, retrySignal) ->
                        Exceptions.propagate(retrySignal.failure())));
    }

    public Mono<Book> getBookById(long bookId) {
        var book = infoService.getBookById(bookId);
        var review = reviewService.getReviewsByBookId(bookId).collectList();

        return book.zipWith(review, Book::new);
    }
}
