package Project.Graduation.model;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="instructors")
@DiscriminatorValue("instructor")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class Instructor extends User{

	private String description;
	private Boolean status=false;
	@JsonIgnore
	@OneToMany(mappedBy = "instructor")
	private List<Comment> comments;



}
