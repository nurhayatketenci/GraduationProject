package Project.Graduation.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Data
@Entity
@AllArgsConstructor
@Table(name="learners")
@DiscriminatorValue("learner")
public class Learner extends User{

}
