package dev.muldev.pockafka.users;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Entity
public class UserEntity implements Serializable {
    @Id
    @Column(columnDefinition = "uuid")
    private UUID userId;
    private String name;
    private String email;
    private Date createdAt;
}
