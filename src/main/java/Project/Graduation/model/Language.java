package Project.Graduation.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "languages")
@NoArgsConstructor
@AllArgsConstructor
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="language")
    private String languageName;

    @OneToOne(mappedBy = "language")
    @JsonIgnore
    private Lesson lesson;
}
