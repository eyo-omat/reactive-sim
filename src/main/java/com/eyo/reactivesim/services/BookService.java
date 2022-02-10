package com.eyo.reactivesim.services;

import com.eyo.reactivesim.domain.Book;
import com.eyo.reactivesim.domain.Review;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class BookService {

    private BookInfoService infoService;
    private ReviewService reviewService;


    public Flux<Book> getBooks() {
        var allBooks = infoService.getBooks();
        return allBooks
                .flatMap(bookInfo -> {
                    Mono<List<Review>> reviews = reviewService.getReviewsByBookId(bookInfo.getBookId()).collectList();
                    return reviews
                            .map(review -> new Book(bookInfo, review));
                }).log();
    }
}