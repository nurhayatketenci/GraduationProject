package Project.Graduation.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="learners")
@DiscriminatorValue("learner")
public class Learner extends User{
    @JsonIgnore
    @OneToMany(mappedBy = "learner")
    private List<Comment> comments;
}
