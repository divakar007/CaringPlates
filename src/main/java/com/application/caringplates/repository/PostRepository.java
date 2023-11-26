package com.application.caringplates.repository;

import com.application.caringplates.models.Post;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    @NotNull
    List<Post> findAll();
    List<Post> findAllByBestBeforeGreaterThanAndClaimedIsFalse(Date currentTime);
    List<Post> findAllByBestBeforeBeforeAndClaimedFalse(Date currentTime);

    @Modifying
    @Query(value = "UPDATE post SET claimed = true WHERE postID = :postId", nativeQuery = true)
    void markPostAsClaimed(@Param("postId") Long postId);
}
