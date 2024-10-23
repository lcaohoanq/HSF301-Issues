package pe.hsf301.fall24.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Directors")
public class Director {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	private int id;
	
	@Column(name="DirectorName")
	private String directorName;
	
	@Column(name="Nationality")
	private String nationality;
	
	public Director() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public String getDirectorName() {
		return directorName;
	}

	public String getNationality() {
		return nationality;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setDirectorName(String directorName) {
		this.directorName = directorName;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	@Override
	public String toString() {
	    return this.getDirectorName(); // Assuming Director has a getName() method
	}

	public Director(String directorName, String nationality) {
		super();
		this.directorName = directorName;
		this.nationality = nationality;
	}
	
	

}
