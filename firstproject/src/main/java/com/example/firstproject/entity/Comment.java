package com.example.firstproject.entity;

import com.example.firstproject.dto.CommentDto;
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

    /**
     * 댓글 생성 메서드
     * @param dto
     * @param article
     * @return 댓글 엔티티 생성 및 반환
     */
    public static Comment createComment(CommentDto dto, Article article) {
        // 예외 발생
        if (dto.getId() != null) {
            throw new IllegalArgumentException("댓글 생성 실패! 댓글의 id가 없어야 합니다.");
        }

        if (dto.getArticleId() != article.getId()) {
            throw new IllegalArgumentException("댓글 생성 실패! 게시글의 id가 잘못됐습니다.");
        }

        // 엔티티 생성 및 반환
        return new Comment(
                dto.getId(), // 댓글 아이디
                article, // 부모 게시글
                dto.getNickname(), // 댓글 닉네임
                dto.getBody() // 댓글 본문
        );
    }

    /**
     * 댓글 수정 메서드
     * @param dto
     */
    public void patch(CommentDto dto) {
        // 예외 발생
        if (this.id != dto.getId()) {
            throw new IllegalArgumentException("댓글 수정 실패! 잘못된 id가 입력됐습니다.");
        }
        // 객체 갱신
        //수정할 닉네임 데이터가 있으면 -> 내용 반영
        if (dto.getNickname() != null) {
            this.nickname = dto.getNickname();
        }

        //수정할 본문 데이터가 있으면 -> 내용 반영
        if (dto.getBody() != null) {
            this.body = dto.getBody();
        }
    }
}
