package com.example.firstproject.repository;

import com.example.firstproject.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 댓글 리포지토리
 */
public interface CommentRepository extends JpaRepository<Comment, Long> {
    //특정 게시글의 모든 댓글 조회(NativeQuery 사용)
    @Query(value="SELECT * FROM comment WHERE article_id = :articleId", nativeQuery = true)
    List<Comment> findByArticleId(Long articleId);
    //특정 닉네임의 모든 댓글 조회(NativeQuery XML 사용)
    List<Comment> findByNickname(String nickname);
}
