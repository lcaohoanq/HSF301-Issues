package pe.hsf301.fall24.repository.director;

import java.util.List;

import pe.hsf301.fall24.pojo.Director;

public interface IDirectorRepository {
	void save(Director director);	
	List<Director> findAll();	
	void delete(Integer id);	
	Director findById(Integer id);
	void update(Director director);
}
