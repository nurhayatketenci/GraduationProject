package Project.Graduation.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "user_image")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode

public class UserImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name="image_path")
    private String imagePath;

    @Column(name="date")
    private Date date;
}
