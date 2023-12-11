package ru.Bogachev.task_list.web.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "Request from login")
public class JwtRequest {

    @Schema(description = "email", example = "johndoe@gmail.com")
    @NotNull(message = "Username must be not null.")
    private String username;

    @Schema(description = "password", example = "12345")
    @NotNull(message = "Password must be not null.")
    private String password;

}
