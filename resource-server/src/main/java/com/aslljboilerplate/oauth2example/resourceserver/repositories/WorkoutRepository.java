package com.aslljboilerplate.oauth2example.resourceserver.repositories;

import com.aslljboilerplate.oauth2example.resourceserver.entities.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WorkoutRepository extends JpaRepository<Workout,Integer> {

    Optional<List<Workout>> findAllByUser(String user);
}
