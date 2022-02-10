package com.eyo.reactivesim.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    private BookInfo bookInfo;
    public List<Review> reviews;
}
