package dev.muldev.pockafka.greetings;

import dev.muldev.pockafka.events.UserCreated;
import dev.muldev.pockafka.users.UserEntity;
import dev.muldev.pockafka.users.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@AllArgsConstructor
public class GreetingService {

    private final UserRepository userRepository;

    public void sendGreetingEmail(UserCreated userCreated){
        //we'll send the email here
        log.info("search email from user:" + userCreated.userId);
        Optional<UserEntity> persistedUser = this.userRepository.findById(UUID.fromString(userCreated.userId));
        if(!persistedUser.isPresent()){
            log.error("User not present");
            return;
        }

        UserEntity user = persistedUser.get();

        log.info("Sending email to:" + user.getEmail());
        log.info("Welcome " + user.getName());
    }


}
