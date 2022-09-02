package dev.muldev.pockafka.users;

import dev.muldev.pockafka.exceptions.UserAlreadyRegisteredException;
import dev.muldev.pockafka.users.dto.UserCreateDTO;
import dev.muldev.pockafka.users.dto.UserResponseDTO;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(UserController.USER_URL)
@AllArgsConstructor
public class UserController {
    public static final String USER_URL = "/users";

    private final UserService userService;
    private final ModelMapper modelMapper;

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDTO createUser(@RequestBody UserCreateDTO userCreateDTO) throws UserAlreadyRegisteredException {
        return this.modelMapper.map(this.userService.createUser(this.modelMapper.map(userCreateDTO, UserEntity.class)), UserResponseDTO.class);
    }
}
