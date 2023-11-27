package com.application.caringplates.repository;

import com.application.caringplates.models.Post;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    @NotNull
    List<Post> findAll();
    List<Post> findAllByBestBeforeGreaterThanAndClaimedIsFalse(Date currentTime);
    List<Post> findAllByBestBeforeBeforeAndClaimedFalse(Date currentTime);
    @Transactional
    @Modifying
    @Query(value = "UPDATE Post p SET p.claimed = true WHERE p.postID = :postId", nativeQuery = true)
    void markPostAsClaimed(@Param("postId") Long postId);
}
