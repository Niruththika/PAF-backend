package com.example.eduposts.repository;

import com.example.eduposts.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByCategoryIn(List<String> categories);

    List<Post> findByTypeIn(List<String> types);

    List<Post> findByDurationIn(List<String> durations);

    List<Post> findByCategoryInAndTypeIn(List<String> categories, List<String> types);

    List<Post> findByCategoryInAndDurationIn(List<String> categories, List<String> durations);

    List<Post> findByTypeInAndDurationIn(List<String> types, List<String> durations);

    List<Post> findByCategoryInAndTypeInAndDurationIn(List<String> categories, List<String> types,
            List<String> durations);
}