package com.se181513.pe.repository;

import java.util.List;

import com.se181513.pe.pojo.Directors;



public interface IDirectorRepo {
	public List<Directors> findAll();

	public void save(Directors director);

	public void delete(int id);

	public Directors findById(int id);

	public void update(Directors directors);
	
}
