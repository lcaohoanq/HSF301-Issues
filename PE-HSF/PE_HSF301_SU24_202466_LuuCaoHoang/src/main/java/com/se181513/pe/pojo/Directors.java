package com.se181513.pe.pojo;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Directors")
public class Directors {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID",nullable =false)
	private int id;
	
	@Column(name = "DirectorName",length = 100,nullable =false)
	private String directorName;
	

	@Column(name = "Nationality",length = 100,nullable =false)
	private String nationality;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "DirectorID")
	private Set<Movies> movies;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDirectorName() {
		return directorName;
	}

	public void setDirectorName(String directorName) {
		this.directorName = directorName;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public Set<Movies> getMovies() {
		return movies;
	}

	public void setMovies(Set<Movies> movies) {
		this.movies = movies;
	}

	public Directors(int id, String directorName, String nationality, Set<Movies> movies) {
		super();
		this.id = id;
		this.directorName = directorName;
		this.nationality = nationality;
		this.movies = movies;
	}

	public Directors() {
		super();
	}

	@Override
	public String toString() {
		return this.getDirectorName();
	}
	
	
}
