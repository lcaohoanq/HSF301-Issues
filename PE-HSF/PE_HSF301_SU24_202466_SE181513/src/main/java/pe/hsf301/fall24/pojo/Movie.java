package pe.hsf301.fall24.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Movies")
public class Movie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MovieID")
	private int movieId;
	
	@Column(name="MovieName")
	private String movieName;
	
	@Column(name="Duration")
	private int duration;
	
	@Column(name="Actor")
	private String actor;
	
	@Column(name="Status")
	private String status;
	
	@ManyToOne
	@JoinColumn(name = "DirectorID")
	private Director director;
	
	public Movie() {
		// TODO Auto-generated constructor stub
	}

	public int getMovieId() {
		return movieId;
	}

	public String getMovieName() {
		return movieName;
	}

	public int getDuration() {
		return duration;
	}

	public String getActor() {
		return actor;
	}

	public String getStatus() {
		return status;
	}

	public Director getDirector() {
		return director;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public void setActor(String actor) {
		this.actor = actor;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setDirector(Director director) {
		this.director = director;
	}

	@Override
	public String toString() {
		return "Movie [movieId=" + movieId + ", movieName=" + movieName + ", duration=" + duration + ", actor=" + actor
				+ ", status=" + status + ", director=" + director + "]";
	}

	public Movie(String movieName, int duration, String actor, String status, Director director) {
		super();
		this.movieName = movieName;
		this.duration = duration;
		this.actor = actor;
		this.status = status;
		this.director = director;
	}
	
	

}
