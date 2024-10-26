package controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.application.Platform;
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
import pe.hsf301.fall24.pojo.Director;
import pe.hsf301.fall24.pojo.Movie;
import pe.hsf301.fall24.repository.director.DirectorRepo;
import pe.hsf301.fall24.repository.director.IDirectorRepository;
import pe.hsf301.fall24.repository.movie.IMovieRepository;
import pe.hsf301.fall24.repository.movie.MovieRepo;
import util.AlertHandler;
import util.ValidationHandler;

public class MovieManagementController extends ValidationHandler<Movie> implements Initializable {

    // Constants
    private static final String HIBERNATE_CONFIG = "hibernate.cfg.xml";
    private static final List<String> VALID_STATUSES = List.of("active", "inactive", "coming");
    private static final int MIN_DURATION = 75;
    private static final int MAX_DURATION = 120;
    private static final int MAX_MOVIE_NAME_LENGTH = 20;
    private static final String MOVIE_NAME_PATTERN = "^[A-Z][a-zA-Z]*(\\s[A-Z][a-zA-Z]*)*$";

    // Repositories
    private final IMovieRepository movieRepository;
    private final IDirectorRepository directorRepository;
    private final ObservableList<Movie> tableModel;

    // FXML Controls
    @FXML private TextField txtMovieId;
    @FXML private TextField txtMovieName;
    @FXML private TextField txtDuration;
    @FXML private TextField txtActor;
    @FXML private TextField txtStatus;
    @FXML private ComboBox<Director> comboBoxDirector;
    @FXML private Button btnAdd;
    @FXML private Button btnUpdate;
    @FXML private Button btnDelete;
    @FXML private Button btnCancel;
    @FXML private TableView<Movie> tblMovies;
    @FXML private TableColumn<Movie, Integer> movieId;
    @FXML private TableColumn<Movie, String> movieName;
    @FXML private TableColumn<Movie, Integer> duration;
    @FXML private TableColumn<Movie, String> actor;
    @FXML private TableColumn<Movie, String> status;
    @FXML private TableColumn<Movie, Director> directorId;

    private int roleID;

    public MovieManagementController() {
        movieRepository = new MovieRepo(HIBERNATE_CONFIG);
        directorRepository = new DirectorRepo(HIBERNATE_CONFIG);
        tableModel = FXCollections.observableArrayList(movieRepository.findAll());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeTableColumns();
        initializeTableView();
        initializeComboBox();
        setupEventHandlers();
    }

    //this function will take the class props to set the table column
    private void initializeTableColumns() {
        movieId.setCellValueFactory(new PropertyValueFactory<>("movieId"));
        movieName.setCellValueFactory(new PropertyValueFactory<>("movieName"));
        duration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        actor.setCellValueFactory(new PropertyValueFactory<>("actor"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));
        directorId.setCellValueFactory(new PropertyValueFactory<>("director"));
    }

    private void initializeTableView() {
        tblMovies.setItems(tableModel);
    }

    private void initializeComboBox() {
        List<Director> directors = directorRepository.findAll();
        comboBoxDirector.setItems(FXCollections.observableArrayList(directors));
        comboBoxDirector.setPromptText("Select Director");
    }

    private void setupEventHandlers() {
        tblMovies.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                showMovie(newValue);
                txtMovieId.setEditable(false);
            }
        });

        // Add listeners for real-time validation
        txtMovieName.textProperty().addListener((observable, oldValue, newValue) -> {
            validateMovieName(newValue); //text color validation
        });

        txtDuration.textProperty().addListener((observable, oldValue, newValue) -> {
        	//if not a number, then replace with empty string immediately
            if (!newValue.matches("\\d*")) {
                txtDuration.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }

    @FXML
    public void btnAddOnAction() {
        try {
            Movie newMovie = getMovieFromInput();
            movieRepository.save(newMovie);
            tableModel.add(0, newMovie);
            tblMovies.getSelectionModel().selectFirst();
            clearInputFields();
            AlertHandler.showAlert("Success", "Movie successfully added.");
        } catch (Exception e) {
            AlertHandler.showAlert("Error", e.getMessage());
        }
    }

    @FXML
    public void btnUpdateOnAction() {
        try {
            Movie selectedMovie = tblMovies.getSelectionModel().getSelectedItem();
            if (selectedMovie == null) {
                throw new Exception("Please select a movie to update.");
            }
            updateMovieFromInput(selectedMovie);
            movieRepository.update(selectedMovie);
            refreshDataTable();
            AlertHandler.showAlert("Success", "Movie updated successfully.");
        } catch (Exception e) {
            AlertHandler.showAlert("Error", e.getMessage());
        }
    }

    @FXML
    public void btnDeleteOnAction() {
        try {
            Movie selectedMovie = tblMovies.getSelectionModel().getSelectedItem();
            if (selectedMovie == null) {
                throw new Exception("Please select a movie to delete.");
            }
            movieRepository.delete(selectedMovie.getMovieId());
            tableModel.remove(selectedMovie);
            clearInputFields();
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
        validateInputFields();
        
        String movieName = txtMovieName.getText().trim();
        int duration = Integer.parseInt(txtDuration.getText().trim());
        String actor = txtActor.getText().trim();
        String status = txtStatus.getText().trim().toLowerCase();
        Director director = comboBoxDirector.getValue();

        Movie movie = new Movie(movieName, duration, actor, status, director);
        return valid(movie);
    }

    private void validateInputFields() throws Exception {
        if (txtMovieName.getText().trim().isEmpty() ||
            txtDuration.getText().trim().isEmpty() ||
            txtActor.getText().trim().isEmpty() ||
            txtStatus.getText().trim().isEmpty() ||
            comboBoxDirector.getValue() == null) {
            throw new Exception("All fields are required.");
        }
    }

    private void validateMovieName(String movieName) {
        if (movieName.length() > MAX_MOVIE_NAME_LENGTH) {
            txtMovieName.setStyle("-fx-border-color: red;");
        } else if (!movieName.matches(MOVIE_NAME_PATTERN)) {
            txtMovieName.setStyle("-fx-border-color: red;");
        } else {
            txtMovieName.setStyle("");
        }
    }

    private void updateMovieFromInput(Movie movie) throws Exception {
        Movie updatedInfo = getMovieFromInput();
        movie.setMovieName(updatedInfo.getMovieName());
        movie.setDuration(updatedInfo.getDuration());
        movie.setActor(updatedInfo.getActor());
        movie.setStatus(updatedInfo.getStatus());
        movie.setDirector(updatedInfo.getDirector());
    }

    private void showMovie(Movie movie) {
        txtMovieId.setText(String.valueOf(movie.getMovieId()));
        txtMovieName.setText(movie.getMovieName());
        txtDuration.setText(String.valueOf(movie.getDuration()));
        txtActor.setText(movie.getActor());
        txtStatus.setText(movie.getStatus());
        comboBoxDirector.setValue(movie.getDirector());
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
        comboBoxDirector.setValue(null);
        // Reset styles
        txtMovieName.setStyle("");
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
        configureButtonsByRole(roleID);
    }

    private void configureButtonsByRole(int roleID) {
        switch (roleID) {
            case 1 -> { /* Admin: All permissions */ }
            case 2 -> {
                btnAdd.setDisable(true);
                btnDelete.setDisable(true);
            }
            case 3 -> btnDelete.setDisable(true);
            case 4 -> {
                btnAdd.setDisable(true);
                btnDelete.setDisable(true);
            }
            default -> throw new IllegalArgumentException("Invalid role ID: " + roleID);
        }
    }

    @Override
    public Movie valid(Movie movie) throws Exception {
        if (movie.getDirector() == null) {
            throw new Exception("Director is required");
        }

        if (movie.getMovieName().length() >= MAX_MOVIE_NAME_LENGTH) {
            throw new Exception("Movie name must be less than " + MAX_MOVIE_NAME_LENGTH + " characters");
        }

        if (!movie.getMovieName().matches(MOVIE_NAME_PATTERN)) {
            throw new Exception("Invalid movie name format. Each word must begin with a capital letter and contain only letters.");
        }

        if (movie.getDuration() < MIN_DURATION || movie.getDuration() > MAX_DURATION) {
            throw new Exception("Duration must be between " + MIN_DURATION + " and " + MAX_DURATION + " minutes");
        }

        if (!VALID_STATUSES.contains(movie.getStatus().toLowerCase())) {
            throw new Exception("Invalid status. Must be one of: " + String.join(", ", VALID_STATUSES));
        }

        return movie;
    }
}