package Project.Graduation.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name="levels")
@NoArgsConstructor
@AllArgsConstructor
public class Level {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="level")
    private String level;

    @OneToOne(mappedBy = "level")
    @JsonIgnore
    private Lesson lesson;
}
