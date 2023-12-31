package com.example.firstproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String title;
    @Column
    private String content;

    /**
     * 일부 데이터만 수정할 경우
     * @param article
     */
    public void patch(Article article) {
        if (article.title != null) {
            this.title = article.title;
        }

        if (article.content != null) {
            this.content = article.content;
        }
    }
}
