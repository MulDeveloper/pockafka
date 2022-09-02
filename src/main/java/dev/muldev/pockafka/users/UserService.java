package dev.muldev.pockafka.users;

import dev.muldev.pockafka.config.ApplicationConstant;
import dev.muldev.pockafka.events.UserCreated;
import dev.muldev.pockafka.exceptions.UserAlreadyRegisteredException;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final UserRepository userRepository;

    public UserEntity createUser(UserEntity user) throws UserAlreadyRegisteredException {
        Optional<UserEntity> optionalUser = this.userRepository.findByEmail(user.getEmail());
        if(optionalUser.isPresent()){
            throw new UserAlreadyRegisteredException(user.getEmail());
        }

        user.setCreatedAt(Date.from(Instant.now()));

        this.userRepository.save(user);

        UserCreated userCreated = UserCreated.builder()
                .userId(user.getUserId().toString()).build();

        kafkaTemplate.send(ApplicationConstant.TOPIC_NAME, userCreated);

        return user;
    }
}
