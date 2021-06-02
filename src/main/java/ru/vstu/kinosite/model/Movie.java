package ru.vstu.kinosite.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movie {

    private Long id;
    private String name;
    private int year;
    private String description;
    private String poster;
}
