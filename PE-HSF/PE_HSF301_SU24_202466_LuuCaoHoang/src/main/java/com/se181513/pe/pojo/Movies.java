package com.se181513.pe.pojo;

import javax.annotation.processing.Generated;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Movies")
public class Movies {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MovieID",nullable = false)
	private int movieId;
	
	@Column(name = "MovieName",nullable = false)
	private String movieName;
	
	@Column(name = "Duration",nullable = false)
	private int duration;
	
	@Column(name = "Actor",nullable = false)
	private String actor;
	
	@Column(name = "Status",nullable = false)
	private String status;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "DirectorID")
	private Directors directors ;

	public Movies(int movieId, String movieName, int duration, String actor, String status, Directors directors) {
		super();
		this.movieId = movieId;
		this.movieName = movieName;
		this.duration = duration;
		this.actor = actor;
		this.status = status;
		this.directors = directors;
	}

	public Movies() {
		super();
	}

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getActor() {
		return actor;
	}

	public void setActor(String actor) {
		this.actor = actor;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Directors getDirectors() {
		return directors;
	}

	public void setDirectors(Directors directors) {
		this.directors = directors;
	}
	
}
