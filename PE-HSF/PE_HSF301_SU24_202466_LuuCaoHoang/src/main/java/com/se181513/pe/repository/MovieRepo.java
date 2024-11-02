package com.se181513.pe.repository;

import java.util.List;

import com.se181513.pe.dao.MovieDAO;
import com.se181513.pe.pojo.Movies;

public class MovieRepo implements IMovieRepo{
	private MovieDAO movieDAO;
	public MovieRepo(String name) {
		movieDAO = new MovieDAO(name);
	}
	@Override
	public List<Movies> findAll() {
		// TODO Auto-generated method stub
		return movieDAO.getMovies();
	}

	@Override
	public void save(Movies movie) {
		// TODO Auto-generated method stub
		movieDAO.save(movie);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		movieDAO.delete(id);
	}

	@Override
	public Movies findById(int id) {
		// TODO Auto-generated method stub
		return movieDAO.findById(id);
	}

	@Override
	public void update(Movies movie) {
		// TODO Auto-generated method stub
		movieDAO.update(movie);
	}
	@Override
	public List<Movies> searchMname(String Mname) {
		// TODO Auto-generated method stub
		return movieDAO.searchByMName(Mname);
	}
	@Override
	public List<Movies> searchDname(String Dname) {
		// TODO Auto-generated method stub
		return movieDAO.searchByDName(Dname);
	}
	

}
