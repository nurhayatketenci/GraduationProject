package Project.Graduation.model;

import java.util.List;

import javax.persistence.*;

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
public class Instructor extends User{

	private String description;



}
