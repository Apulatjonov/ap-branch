package com.example.apbranch.user;

import com.example.apbranch.entity.Workplace;
import com.example.apbranch.response.Pair;
import com.example.apbranch.response.Response;
import com.example.apbranch.response.ResponseCode;
import com.example.apbranch.workplace.WorkplaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    WorkplaceRepository workplaceRepository;

    public Pair<Response, List<User>> getUsers() {
        List<User> users = userRepository.findAll();
        Response response = new Response();
        if (!users.isEmpty()) {
            response.setMessage("OK");
            response.setStatus(ResponseCode.OK);
            return new Pair<>(response, users);
        }
        response.setMessage("Not Found");
        response.setStatus(ResponseCode.NOT_FOUND);
        return new Pair<>(response, null);
    }

    public Pair<Response, User> getUser(UUID id) {
        Optional<User> optionalUser = userRepository.findById(id);
        Response response = new Response();
        if (optionalUser.isPresent()) {
            response.setStatus(ResponseCode.OK);
            response.setMessage("OK");
            User user = optionalUser.get();
            return new Pair<>(response, user);
        }
        response.setStatus(ResponseCode.NOT_FOUND);
        response.setMessage("Not Found");
        return new Pair<>(response, null);
    }

    public Response addUser(UserDTO userDto) {
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setEmploymentEnd(userDto.getEmploymentEnd());
        user.setEmploymentStart(userDto.getEmploymentStart());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setRole(userDto.getRole());
        user.setTelegramChatId(userDto.getTelegramChatId());
        Optional<Workplace> optionalWorkplace = workplaceRepository.findById(userDto.getWorkplaceId());
        if (optionalWorkplace.isPresent()) {
            user.setWorkplace(optionalWorkplace.get());
            userRepository.save(user);
            return new Response("Successfully added", ResponseCode.CREATED);
        }
        return new Response("There is no such an workPlace", ResponseCode.BAD_REQUEST);
    }

    public Response editUser(UUID id, UserDTO userDto) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setEmail(userDto.getEmail());
            user.setEmploymentEnd(userDto.getEmploymentEnd());
            user.setEmploymentStart(userDto.getEmploymentStart());
            user.setFirstName(userDto.getFirstName());
            user.setLastName(userDto.getLastName());
            user.setRole(userDto.getRole());
            user.setTelegramChatId(userDto.getTelegramChatId());
            Optional<Workplace> optionalWorkplace = workplaceRepository.findById(userDto.getWorkplaceId());
            if (optionalWorkplace.isPresent()) {
                user.setWorkplace(optionalWorkplace.get());
                userRepository.save(user);
                return new Response("Successfully added", ResponseCode.CREATED);
            }
            return new Response("There is no such an workPlace", ResponseCode.BAD_REQUEST);
        }
        return new Response("There is no such an user", ResponseCode.NOT_FOUND);
    }

    public Response deleteUser(UUID id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            userRepository.deleteById(id);
            return new Response("Successfully deleted", ResponseCode.OK);
        }
        return new Response("There is no such an user", ResponseCode.NOT_FOUND);
    }
}
