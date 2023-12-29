package models.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author ivan.graciarena
 * @project order-domain-automation
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateRequest {
    private String name;
    private String job;
    private String email;
    private String password;
}
