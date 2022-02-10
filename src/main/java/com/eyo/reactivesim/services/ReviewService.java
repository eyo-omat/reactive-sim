package com.eyo.reactivesim.services;

import com.eyo.reactivesim.domain.Review;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
public class ReviewService {

    public Flux<Review> getReviewsByBookId(long bookId) {
        var reviewList = List.of(
                new Review(1, bookId, 9.3, "Great book"),
                new Review(2, bookId, 11, "Awesome read")

        );
        return Flux.fromIterable(reviewList);
    }
}
