package com.example.firstproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 댓글 엔티티
 */

@Entity
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //대표키
    @ManyToOne
    @JoinColumn(name="article_id")
    private Article article; //해당 댓글의 부모 게시글
    @Column
    private String nickname; //댓글을 단 사람
    @Column
    private String body; //댓글 본문
}
