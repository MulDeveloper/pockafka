package dev.muldev.pockafka.events;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
public class UserCreated extends Event{

    public String userId;

    @Builder
    public UserCreated(String userId) {
        super();
        this.userId = userId;
    }

}
