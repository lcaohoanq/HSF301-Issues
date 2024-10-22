package controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import pe.hsf301.fall24.pojo.Director;
import pe.hsf301.fall24.pojo.Movie;
import pe.hsf301.fall24.repository.director.DirectorRepo;
import pe.hsf301.fall24.repository.director.IDirectorRepository;
import pe.hsf301.fall24.repository.movie.IMovieRepository;
import pe.hsf301.fall24.repository.movie.MovieRepo;
import util.AlertHandler;

public class MoiveManagementController implements Initializable {

	private final IMovieRepository movieRepository;
	private final IDirectorRepository directorRepository;
	private final ObservableList<Movie> tableModel;

	@FXML
	private TextField txtMovieId;
	@FXML
	private TextField txtMovieName;
	@FXML
	private TextField txtDuration;
	@FXML
	private TextField txtActor;
	@FXML
	private TextField txtStatus;
	@FXML
	private TextField txtDirectorId;

	@FXML
	private Button btnAdd;
	@FXML
	private Button btnUpdate;
	@FXML
	private Button btnDelete;
	@FXML
	private Button btnCancel;

	@FXML
	private TableView<Movie> tblMovies;
	@FXML
	private TableColumn<Movie, Integer> movieId;
	@FXML
	private TableColumn<Movie, String> movieName;
	@FXML
	private TableColumn<Movie, Integer> duration;
	@FXML
	private TableColumn<Movie, String> actor;
	@FXML
	private TableColumn<Movie, String> status;
	@FXML
	private TableColumn<Movie, Integer> directorId;

	private int roleID;
	public static String hibernateConfig = "hibernate.cfg.xml";

	public MoiveManagementController() {
		movieRepository = new MovieRepo(hibernateConfig);
		directorRepository = new DirectorRepo(hibernateConfig);
		tableModel = FXCollections.observableArrayList(movieRepository.findAll());
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initializeTableColumns();
		tblMovies.setItems(tableModel);
		tblMovies.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue != null) {
				showMovie(newValue);
				// disable the id text field when initialize
				// readonly
				txtMovieId.setEditable(false);
			}
		});
	}

	private void initializeTableColumns() {
		movieId.setCellValueFactory(new PropertyValueFactory<>("movieId"));
		movieName.setCellValueFactory(new PropertyValueFactory<>("movieName"));
		duration.setCellValueFactory(new PropertyValueFactory<>("duration"));
		actor.setCellValueFactory(new PropertyValueFactory<>("actor"));
		status.setCellValueFactory(new PropertyValueFactory<>("status"));
		directorId.setCellValueFactory(new PropertyValueFactory<>("director"));
	}

	@FXML
	public void btnAddOnAction() {
		try {
			Movie newMovie = getMovieFromInput();
			movieRepository.save(newMovie);
			
			// Add the new movie to the top of the list
	        tableModel.add(0, newMovie);  // Insert the new movie at the top (index 0)
	        tblMovies.getSelectionModel().selectFirst();  // Highlight the newly added movie
			
//			refreshDataTable();
			AlertHandler.showAlert("Success", "Student successfully added.");
		} catch (Exception e) {
			AlertHandler.showAlert("Error", e.getMessage());
		}
	}

	@FXML
	public void btnUpdateOnAction() {
		try {
			Integer id = Integer.parseInt(txtMovieId.getText().trim());
			Movie existingMovie = movieRepository.findById(id);
			if (existingMovie == null) {
				throw new Exception("Movie not found with id: " + id);
			}
			updateStudentFromInput(existingMovie);
			movieRepository.update(existingMovie);
			refreshDataTable();
			AlertHandler.showAlert("Success", "Movie updated successfully.");
		} catch (Exception e) {
			AlertHandler.showAlert("Error", e.getMessage());
		}
	}

	@FXML
	public void btnDeleteOnAction() {
		try {
			Integer id = Integer.parseInt(txtMovieId.getText().trim());
			if (movieRepository.findById(id) == null) {
				throw new Exception("Movie not found with id: " + id);
			}
			movieRepository.delete(id);
			refreshDataTable();
			AlertHandler.showAlert("Success", "Movie deleted successfully.");
		} catch (Exception e) {
			AlertHandler.showAlert("Error", e.getMessage());
		}
	}

	@FXML
	public void btnCancelOnAction() {
		Platform.exit();
	}

	private Movie getMovieFromInput() throws Exception {
		String movieName = txtMovieName.getText().trim();
		String durationStr = txtDuration.getText().trim();
		String actor = txtActor.getText().trim();
		String status = txtStatus.getText().trim();
		String directorId = txtDirectorId.getText().trim();
		
		System.out.println("MovieName: " + movieName);
		System.out.println("Duration: " + durationStr);
		System.out.println("Actor: " + actor);
		System.out.println("Status: " + status);
		System.out.println("DirectorId: " + directorId);
		
		Director existingDirector = directorRepository.findById(Integer.parseInt(directorId));

		if(existingDirector == null)
			throw new Exception("Director not found");
		
		if (movieName.isEmpty() || durationStr.isEmpty() || actor.isEmpty() || status.isEmpty()
				|| directorId.isEmpty()) {
			throw new Exception("All fields are required.");
		}
		
		//valid movie name is less then 20 character
		if(movieName.length() >= 20)
			throw new Exception("MovieName must less than 20 characters");

		//Each word of the MovieName must begin with the capital letter. 
		//MovieName don’t allowed with number (0, 1, 2 9) or 
		//special characters such as @, #, $, &,(, ).
		
		int duration = Integer.parseInt(durationStr);
		if (duration < 75 || duration > 120) {
		    throw new Exception("Value for Duration must be between 75 and 120.");
		}
		
		//Value for Status is enum = {active, inactive, coming}
		if(!List.of("active", "inactive", "coming").contains(status))
			throw new Exception("Value for Status is enum = {active, inactive, coming}");
		

		return new Movie(movieName, duration, actor, status, existingDirector);
	}

	private void updateStudentFromInput(Movie movie) throws Exception {
		Movie updatedInfo = getMovieFromInput();
		movie.setMovieName(updatedInfo.getMovieName());
		movie.setDuration(updatedInfo.getDuration());
		movie.setActor(updatedInfo.getActor());
		movie.setActor(updatedInfo.getStatus());
		movie.setDirector(updatedInfo.getDirector());
	}

	private void showMovie(Movie movie) {
		txtMovieId.setText(String.valueOf(movie.getMovieId()));
		txtMovieName.setText(movie.getMovieName());
		txtDuration.setText(String.valueOf(movie.getDuration()));
		txtActor.setText(String.valueOf(movie.getActor()));
		txtStatus.setText(String.valueOf(movie.getStatus()));
		txtDirectorId.setText(String.valueOf(movie.getDirector().getId()));
	}

	private void refreshDataTable() {
		tableModel.setAll(movieRepository.findAll());
		clearInputFields();
	}

	private void clearInputFields() {
		txtMovieId.clear();
		txtMovieName.clear();
		txtDuration.clear();
		txtActor.clear();
		txtStatus.clear();
		txtDirectorId.clear();
	}

	public int getRoleID() {
		return roleID;
	}

	public void setRoleID(int roleID) {
		this.roleID = roleID;

		switch (this.roleID) {
		case 1: {
			break;
		}
		case 2: {
			this.btnAdd.setDisable(true);
			this.btnDelete.setDisable(true);
			break;
		}
		case 3: {
			break;
		}
		case 4: {
			this.btnAdd.setDisable(true);
			this.btnDelete.setDisable(true);
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + this.roleID);
		}

	}

}