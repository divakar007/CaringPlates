package com.application.caringplates.repository;

import com.application.caringplates.models.Post;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    @NotNull
    List<Post> findAll();
    List<Post> findAllByBestBeforeGreaterThanAndClaimedIsFalse(Date currentTime);
    List<Post> findAllByBestBeforeBeforeAndClaimedFalse(Date currentTime);
}
