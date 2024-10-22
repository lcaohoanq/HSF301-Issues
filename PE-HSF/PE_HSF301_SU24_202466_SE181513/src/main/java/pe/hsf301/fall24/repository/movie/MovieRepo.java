package pe.hsf301.fall24.repository.movie;

import java.util.List;

import pe.hsf301.fall24.dao.movie.IMovieDAO;
import pe.hsf301.fall24.dao.movie.MovieDAO;
import pe.hsf301.fall24.pojo.Movie;


public class MovieRepo implements IMovieRepository {

	private IMovieDAO movieDAO;
	
	public MovieRepo(String jpaName) {
		movieDAO = new MovieDAO(jpaName);
	}

	@Override
	public void save(Movie movie) {
		movieDAO.save(movie);
	}

	@Override
	public List<Movie> findAll() {
		return movieDAO.findAll();
	}

	@Override
	public void delete(Integer id) {
		movieDAO.delete(id);
	}

	@Override
	public Movie findById(Integer id) {
		return movieDAO.findById(id);
	}

	@Override
	public void update(Movie movie) {
		movieDAO.update(movie);
	}

}
