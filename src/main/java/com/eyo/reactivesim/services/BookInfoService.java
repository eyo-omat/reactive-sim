package com.eyo.reactivesim.services;

import com.eyo.reactivesim.domain.BookInfo;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class BookInfoService {

    public Flux<BookInfo> getBooks() {
        var books = List.of(
                new BookInfo(1, "Book one", "Author one", "12928322"),
                new BookInfo(2, "Book two", "Author two", "12876278"),
                new BookInfo(3, "Book three", "Author three", "128343")
        );

        return Flux.fromIterable(books);
    }

    public Mono<BookInfo> getBookById(long bookId) {
        var book = new BookInfo(bookId, "Book one", "Author one", "12928322");

        return Mono.just(book);
    }
}
