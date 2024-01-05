package com.springboot.blog.repository;

import com.springboot.blog.entity.Comment;
import org.hibernate.annotations.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findByPostId(long postId);



}
