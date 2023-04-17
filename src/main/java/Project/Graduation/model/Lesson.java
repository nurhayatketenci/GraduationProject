package Project.Graduation.model;

import java.sql.Date;
import java.util.List;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="lessons")
@NoArgsConstructor
@AllArgsConstructor
public class Lesson {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@ManyToOne()
	@JoinColumn(name="user_id")
	private User user;
    
    @Column(name="date")
    private Date date; 
    
    @Column(name="link")
    private String link;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(unique = true,name="level_id")
	private  Level level;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(unique = true,name="language_id")
	private  Language language;



}
