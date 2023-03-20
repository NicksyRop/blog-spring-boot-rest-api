package com.api.blog.respository;

import com.api.blog.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post ,Long> {
}
