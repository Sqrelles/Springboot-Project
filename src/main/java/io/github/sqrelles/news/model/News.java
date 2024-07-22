package io.github.sqrelles.news.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity


public class News {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;
        private String title;
        private String content;
        private String author;
        private Date date = new Date();
        private String image;



}

