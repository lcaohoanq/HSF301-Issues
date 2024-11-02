package com.se181513.pe.repository;

import java.util.List;

import com.se181513.pe.pojo.Movies;

public interface IMovieRepo {
	public List<Movies> findAll();

	public void save(Movies movie);

	public void delete(int id);

	public Movies findById(int id);

	public void update(Movies movie);
	
	public List<Movies> searchMname(String Mname);
	
	public List<Movies> searchDname(String Dname);
}
