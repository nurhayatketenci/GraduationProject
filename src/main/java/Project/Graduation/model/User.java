package Project.Graduation.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.*;
import reactor.util.annotation.Nullable;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name="users")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@DiscriminatorColumn(name = "dtype", discriminatorType = DiscriminatorType.STRING)

public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	private String firstName;
	
	private String lastName;
	
	private String username;
	
	private String email;

	private String password;

	private String phoneNumber;

	private int age;

	private String imagePath;

	private String country;



}
