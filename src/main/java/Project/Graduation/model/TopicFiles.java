package Project.Graduation.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name="topicfiles")
@NoArgsConstructor
@AllArgsConstructor
public class TopicFiles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String header;
    private String description;
    private String filePath;

    @ManyToOne
    @JoinColumn(name = "topics_id")
    private Topics topics;
}
