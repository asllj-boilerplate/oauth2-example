package com.aslljboilerplate.oauth2example.resourceserver.service;

import com.aslljboilerplate.oauth2example.resourceserver.entities.Workout;
import com.aslljboilerplate.oauth2example.resourceserver.repositories.WorkoutRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class WorkoutService {

    private WorkoutRepository repository;

    public Workout saveWorkout(Workout workout) {
        return repository.save(workout);
    }

    public List<Workout> findAllWorkoutsForUser(String user) {
        return repository.findAllByUser(user).get();
    }

    public void deleteWorkout(int id){
        repository.deleteById(id);
    }

}
