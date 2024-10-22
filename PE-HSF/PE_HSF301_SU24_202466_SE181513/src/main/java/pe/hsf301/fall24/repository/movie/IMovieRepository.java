package pe.hsf301.fall24.repository.movie;

import java.util.List;

import pe.hsf301.fall24.pojo.Movie;

public interface IMovieRepository {
	void save(Movie movie);	
	List<Movie> findAll();	
	void delete(Integer id);	
	Movie findById(Integer id);
	void update(Movie movie);
}
