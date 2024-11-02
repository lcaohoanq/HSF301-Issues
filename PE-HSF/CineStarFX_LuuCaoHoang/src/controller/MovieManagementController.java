package controller;

import java.util.List;
import java.util.ResourceBundle;

import com.se181513.pe.pojo.Account;
import com.se181513.pe.pojo.Directors;
import com.se181513.pe.pojo.Movies;
import com.se181513.pe.repository.DirectorRepo;
import com.se181513.pe.repository.IDirectorRepo;
import com.se181513.pe.repository.IMovieRepo;
import com.se181513.pe.repository.MovieRepo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import util.AlertHandler;

public class MovieManagementController implements Initializable {
	@FXML
	TextField txtMovieId;
	@FXML
	TextField txtMovieName;
	@FXML
	TextField txtDuration;
	@FXML
	TextField txtActor;
	@FXML
	TextField txtStatus;
	@FXML
	TextField txtDirectorName;
	@FXML
	ComboBox<Directors> cbDirector;

	@FXML
	Button btnAdd;
	@FXML
	Button btnUpdate;
	@FXML
	Button btnDelete;
	@FXML
	Button btnSearchM;
	@FXML
	Button btnSearchD;

	@FXML
	private TableView<Movies> tbData;
	@FXML
	public TableColumn<Movies, Integer> movieId;
	@FXML
	public TableColumn<Movies, String> movieName;
	@FXML
	public TableColumn<Movies, Integer> duration;
	@FXML
	public TableColumn<Movies, String> actor;
	@FXML
	public TableColumn<Movies, String> status;
	@FXML
	public TableColumn<Movies, Directors> director;

	private Account account;
	private int movId;
	private IMovieRepo movieRepo;
	private IDirectorRepo direcRepo;
	private ObservableList<Movies> tableModel;

	public MovieManagementController() {
		movieRepo = new MovieRepo("hibernate.cfg.xml");
		direcRepo = new DirectorRepo("hibernate.cfg.xml");
		tableModel = FXCollections.observableArrayList(movieRepo.findAll());
	}

	@FXML
	public void btnAddAction() {
		Movies movie = validateAndGetMovie();
	    if (movie != null) { // Validation passed
	        movieRepo.save(movie);
	        tableModel.add(0, movie); // Add to the top of the list
	        tbData.setItems(tableModel);
	        AlertHandler.showAlert("Success", "Movie added successfully!");
	    }
	}

	@FXML
	public void btnDeleteAction() {
		movieRepo.delete(Integer.parseInt(txtMovieId.getText()));
		AlertHandler.showAlert("Infomation Board!", "Delete Successfully!");
		refreshDataTable();
	}

	@FXML
	public void btnUpdateAction() {
		Movies movie = validateAndGetMovie();
	    if (movie != null) { // Validation passed
	        movie.setMovieId(Integer.parseInt(txtMovieId.getText())); // Ensure to keep the ID during update
	        movieRepo.update(movie);
	        refreshDataTable(); // Refresh the table to show the updated data
	        AlertHandler.showAlert("Success", "Movie updated successfully!");
	    }
	}

	@FXML
	public void btnSearchMAction() {
		String Mname = txtMovieName.getText();
		List<Movies> movies = movieRepo.searchMname(Mname);
		tableModel = FXCollections.observableArrayList(movies);
		tbData.setItems(tableModel);
	}

	@FXML
	public void btnSearchDAction() {
		String Dname = txtDirectorName.getText();
		List<Movies> movies = movieRepo.searchDname(Dname);
		tableModel = FXCollections.observableArrayList(movies);
		tbData.setItems(tableModel);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void initialize(java.net.URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

		// initializeTableColumns
		movieId.setCellValueFactory(new PropertyValueFactory<>("MovieId"));
		movieName.setCellValueFactory(new PropertyValueFactory<>("MovieName"));
		duration.setCellValueFactory(new PropertyValueFactory<>("Duration"));
		actor.setCellValueFactory(new PropertyValueFactory<>("Actor"));
		status.setCellValueFactory(new PropertyValueFactory<>("Status"));
		director.setCellValueFactory(new PropertyValueFactory<>("Directors"));

		// initializeTableView
		tbData.setItems(tableModel);

		// initializeComboBox
		List<Directors> directors = direcRepo.findAll();
		cbDirector.setItems(FXCollections.observableArrayList(directors));

//		tbData.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
//			@Override
//			public void changed(ObservableValue observableValue, Object oldValue, Object index) {
//				if (tbData.getSelectionModel().getSelectedItem() != null) {
//					TableViewSelectionModel selectionModel = tbData.getSelectionModel();
//					ObservableList selectedCells = selectionModel.getSelectedCells();
//					TablePosition tablePosition = (TablePosition) selectedCells.get(0);
//					Object moiveId = tablePosition.getTableColumn().getCellData(index);
//
//					Movies movies = movieRepo.findById(Integer.valueOf(moiveId.toString()));
//					show(movies);
//
////					try {
////						Movies movies = movieRepo.findById(Integer.valueOf(moiveId.toString()));
////						show(movies);
////					} catch (Exception ex) {
////						AlertHandler.showAlert("Infomation Board!", "Please choose the First Cell !");
////					}
//				}
//
//			}
//		});

		// Listen for row selection changes
		tbData.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

			if (newValue != null) {
				show(newValue);
				
				txtDirectorName.setManaged(false);
				txtDirectorName.setVisible(false);
			}

		});

	}

	private void show(Movies movie) {
		this.txtActor.setText(movie.getActor());
		this.txtDuration.setText(String.valueOf(movie.getDuration()));
		this.txtMovieId.setText(String.valueOf(movie.getMovieId()));
		this.txtMovieName.setText(movie.getMovieName());
		this.txtStatus.setText(movie.getStatus());
		this.cbDirector.setValue(movie.getDirectors());
		this.txtDirectorName.setText(String.valueOf(movie.getDirectors().getId()));
	}

	private void refreshDataTable() {
		this.txtActor.setText("");
		this.txtDirectorName.setText("");
		this.txtDuration.setText("");
		this.txtMovieId.setText("");
		this.txtMovieName.setText("");
		this.txtStatus.setText("");
		this.cbDirector.setValue(null);
		tableModel = FXCollections.observableArrayList(movieRepo.findAll());
		tbData.setItems(tableModel);
	}

	public TextField getTxtMovieId() {
		return txtMovieId;
	}

	public void setTxtMovieId(TextField txtMovieId) {
		this.txtMovieId = txtMovieId;
	}

	public TextField getTxtMovieName() {
		return txtMovieName;
	}

	public void setTxtMovieName(TextField txtMovieName) {
		this.txtMovieName = txtMovieName;
	}

	public TextField getTxtDuration() {
		return txtDuration;
	}

	public void setTxtDuration(TextField txtDuration) {
		this.txtDuration = txtDuration;
	}

	public TextField getTxtActor() {
		return txtActor;
	}

	public void setTxtActor(TextField txtActor) {
		this.txtActor = txtActor;
	}

	public TextField getTxtStatus() {
		return txtStatus;
	}

	public void setTxtStatus(TextField txtStatus) {
		this.txtStatus = txtStatus;
	}

	public TextField getTxtDirectorName() {
		return txtDirectorName;
	}

	public void setTxtDirectorName(TextField txtDirectorName) {
		this.txtDirectorName = txtDirectorName;
	}

	public IMovieRepo getMovieRepo() {
		return movieRepo;
	}

	public void setMovieRepo(IMovieRepo movieRepo) {
		this.movieRepo = movieRepo;
	}

	public TableView<Movies> getTbData() {
		return tbData;
	}

	public void setTbData(TableView<Movies> tbData) {
		this.tbData = tbData;
	}

	public TableColumn<Movies, Integer> getMovieId() {
		return movieId;
	}

	public void setMovieId(TableColumn<Movies, Integer> movieId) {
		this.movieId = movieId;
	}

	public TableColumn<Movies, String> getMovieName() {
		return movieName;
	}

	public void setMovieName(TableColumn<Movies, String> movieName) {
		this.movieName = movieName;
	}

	public TableColumn<Movies, Integer> getDuration() {
		return duration;
	}

	public void setDuration(TableColumn<Movies, Integer> duration) {
		this.duration = duration;
	}

	public TableColumn<Movies, String> getActor() {
		return actor;
	}

	public void setActor(TableColumn<Movies, String> actor) {
		this.actor = actor;
	}

	public TableColumn<Movies, String> getStatus() {
		return status;
	}

	public void setStatus(TableColumn<Movies, String> status) {
		this.status = status;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		int roleId = account.getRoleId();
		switch (roleId) {
		case 1:
			btnSearchM.setDisable(false);
			btnSearchD.setDisable(false);
			break;
		case 2:
			break;
		case 3:
			btnSearchM.setDisable(false);
			btnSearchD.setDisable(false);
			break;
		case 4:
			break;
		}

		this.account = account;
	}
	
	private Movies validateAndGetMovie() {
	    String movieName = txtMovieName.getText();
	    String durationStr = txtDuration.getText();
	    String actor = txtActor.getText();
	    String status = txtStatus.getText();
	    Directors director = cbDirector.getValue(); // Assuming you're using the ComboBox for director selection

	    // Validate all fields are not empty
	    if (movieName.isEmpty() || durationStr.isEmpty() || actor.isEmpty() || status.isEmpty() || director == null) {
	        AlertHandler.showAlert("Error", "All fields are required.");
	        return null;
	    }

	    // Validate Duration (between 75 and 120)
	    int duration;
	    try {
	        duration = Integer.parseInt(durationStr);
	        if (duration < 75 || duration > 120) {
	            AlertHandler.showAlert("Error", "Duration must be between 75 and 120.");
	            return null;
	        }
	    } catch (NumberFormatException e) {
	        AlertHandler.showAlert("Error", "Invalid duration format.");
	        return null;
	    }

	    // Validate MovieName
	    if (movieName.length() > 20) {
	        AlertHandler.showAlert("Error", "Movie name must be less than 20 characters.");
	        return null;
	    }

	    if (!movieName.matches("^[A-Z][a-zA-Z]*( [A-Z][a-zA-Z]*)*$")) { // Capital letters and no numbers/special characters
	        AlertHandler.showAlert("Error", "Movie name must start with a capital letter and contain no numbers or special characters.");
	        return null;
	    }

	    // Validate Status
	    if (!status.equalsIgnoreCase("active") && !status.equalsIgnoreCase("inactive") && !status.equalsIgnoreCase("coming")) {
	        AlertHandler.showAlert("Error", "Status must be one of the following: active, inactive, coming.");
	        return null;
	    }

	    // If validation passes, create and return the movie object
	    Movies movie = new Movies();
	    movie.setMovieName(movieName);
	    movie.setDuration(duration);
	    movie.setActor(actor);
	    movie.setStatus(status);
	    movie.setDirectors(director);

	    return movie;
	}


}
