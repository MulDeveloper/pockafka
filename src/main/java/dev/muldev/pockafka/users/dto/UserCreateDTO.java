package dev.muldev.pockafka.users.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UserCreateDTO {
    public UUID userId;
    public String name;
    public String email;

}
