package com.aslljboilerplate.oauth2example.resourceserver.controller;

import com.aslljboilerplate.oauth2example.resourceserver.entities.Workout;
import com.aslljboilerplate.oauth2example.resourceserver.service.WorkoutService;
import lombok.AllArgsConstructor;
import org.apache.tomcat.util.http.parser.Authorization;
import org.hibernate.sql.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("workout")
public class WorkoutController {

    private WorkoutService service;


    @GetMapping()
    public List<Workout> getAllWorkouts(@AuthenticationPrincipal Jwt principal){
            return service.findAllWorkoutsForUser(principal.getClaimAsString("user_name"));
    }

    @PreAuthorize("#workout.user == authentication.name")
    @PostMapping
    public void createWorkout(@RequestBody Workout workout){
        service.saveWorkout(workout);
    }

    @DeleteMapping("/{id}")
    public void deleteWorkout(@PathVariable Integer id){
        service.deleteWorkout(id);
    }
}
