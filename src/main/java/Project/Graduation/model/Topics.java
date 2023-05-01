package Project.Graduation.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name="topics")
@NoArgsConstructor
@AllArgsConstructor
public class Topics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String header;
    private String description;
    private String imagePath;
    @OneToMany(mappedBy = "topics", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TopicFiles> files = new ArrayList<>();
}
