package pe.hsf301.fall24.repository.director;

import java.util.List;

import pe.hsf301.fall24.dao.director.DirectorDAO;
import pe.hsf301.fall24.dao.director.IDirectorDAO;
import pe.hsf301.fall24.pojo.Director;

public class DirectorRepo implements IDirectorRepository {

	private IDirectorDAO directoryDAO;
	
	public DirectorRepo(String jpaName) {
		directoryDAO = new DirectorDAO(jpaName);
	}

	@Override
	public void save(Director director) {
		directoryDAO.save(director);
	}

	@Override
	public List<Director> findAll() {
		return directoryDAO.findAll();
	}

	@Override
	public void delete(Integer id) {
		directoryDAO.delete(id);
	}

	@Override
	public Director findById(Integer id) {
		return directoryDAO.findById(id);
	}

	@Override
	public void update(Director director) {
		directoryDAO.update(director);
	}

}
