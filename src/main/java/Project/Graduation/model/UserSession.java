package Project.Graduation.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserSession implements Serializable {

    private String sessionId;
    private String email;


}
