package dev.muldev.pockafka.users;

import dev.muldev.pockafka.shared.BaseControllerTest;
import dev.muldev.pockafka.users.dto.UserCreateDTO;
import dev.muldev.pockafka.users.dto.UserResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.util.UUID;

import static dev.muldev.pockafka.shared.TestUtils.asJsonString;
import static dev.muldev.pockafka.shared.TestUtils.mapFromJson;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserControllerTest extends BaseControllerTest {

    @Test
    void should_create_user() throws Exception {
        var request = new UserCreateDTO();

        request.setUserId(UUID.randomUUID());
        request.setName("muldev");
        request.setEmail("contact@muldev.dev");

        final var result = this.mvc.perform(put(getBasePath())
                                       .contentType(MediaType.APPLICATION_JSON)
                                       .content(asJsonString(request)))
                                   .andExpect(status().isCreated());


        var userResult = mapFromJson(result.andReturn().getResponse().getContentAsString(), UserResponseDTO.class);
        assertEquals(request.getEmail(), userResult.getEmail());
        assertEquals(request.getUserId(), request.getUserId());

    }

    @Override
    protected String getBasePath() {
        return UserController.USER_URL;
    }
}