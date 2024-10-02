package com.api.appactivitats.entities.users.service;

import com.api.appactivitats.entities.activities.domain.Activity;
import com.api.appactivitats.entities.activities.dto.ActivityMapper;
import com.api.appactivitats.entities.activities.exception.ActivityNotFoundException;
import com.api.appactivitats.entities.activities.repository.ActivityRepository;
import com.api.appactivitats.entities.users.domain.User;
import com.api.appactivitats.entities.users.dto.UserDTO;
import com.api.appactivitats.entities.users.dto.UserReference;
import com.api.appactivitats.entities.users.dto.UserRequest;
import com.api.appactivitats.entities.users.exception.UserAlreadyRegisteredException;
import com.api.appactivitats.entities.users.exception.UserNotFoundException;
import com.api.appactivitats.entities.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

@Service
public class UserServiceImplemented implements UserService {

    private static final Logger log = Logger.getLogger(UserServiceImplemented.class.getName());

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ActivityRepository activityRepository;

    @Override
    public void createUser(User user) {
        userRepository.save(user);
        log.info("User with UUID: " + user.getId() + "created.");
    }

    @Override
    public User readUser(String id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public List<User> readAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Set<UserReference> readUsersByActivity(String id) {
        Activity activity = activityRepository.findById(id).orElseThrow(() -> new ActivityNotFoundException(id));

        return activity.getEnrolledUsers();
    }

    @Override
    public void updateUser(String id, UserRequest userWithUpdates) {
        User userToUpdate = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));

        if (!userToUpdate.getName().equals(userWithUpdates.name())){
            userToUpdate.setName(userWithUpdates.name());
        }
        if (!userToUpdate.getSurname().equals(userWithUpdates.surname())){
            userToUpdate.setSurname(userWithUpdates.surname());
        }
        if (!userToUpdate.getContactInformation().equals(userWithUpdates.contactInformation())){
            userToUpdate.setContactInformation(userWithUpdates.contactInformation());
        }

        userRepository.save(userToUpdate);
    }

    @Override
    public void deleteUser(String id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));

        userRepository.delete(user);
    }

    @Override
    public void addActivityToUser(String id, String activityId) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        Activity activity = activityRepository.findById(activityId).orElseThrow(() -> new ActivityNotFoundException(activityId));

        user.getEnrolledActivities().add(ActivityMapper.toReference(activity));
        userRepository.save(user);
    }
}
