package com.application.caringplates.repository;

        import com.application.caringplates.models.Restaurant;
        import com.application.caringplates.models.User;
        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

}
