package com.se181513.pe.repository;

import java.util.List;

import com.se181513.pe.dao.DirectorDAO;
import com.se181513.pe.dao.MovieDAO;
import com.se181513.pe.pojo.Directors;

public class DirectorRepo implements IDirectorRepo{
	private DirectorDAO directorDAO;
	
	public DirectorRepo(String name) {
		directorDAO = new DirectorDAO(name);
	}
	@Override
	public List<Directors> findAll() {
		// TODO Auto-generated method stub
		return directorDAO.findAll();
	}

	@Override
	public void save(Directors director) {
		// TODO Auto-generated method stub
		directorDAO.save(director);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		directorDAO.delete(id);
	}

	@Override
	public Directors findById(int id) {
		// TODO Auto-generated method stub
		return directorDAO.findById(id);
	}

	@Override
	public void update(Directors directors) {
		// TODO Auto-generated method stub
		directorDAO.update(directors);
	}

}
