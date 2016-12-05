package GUI;

import Domain.Movie;
import Domain.MovieDataModel;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;

/**
 * Created by Mike on 11/15/2016.
 */
public class MovieView
{
    BorderPane background;

    Label labelId;
    Label labelTitle;
    Label labelRating;
    Label labelYear;

    TextField textFieldId;
    TextField textFieldTitle;
    TextField textFieldYear;
    TextField textFieldRating;

    Button buttonSave;
    Button buttonUpdate;
    Button buttonDelete;
    Button buttonClearAll;

    TableView<Movie> tableViewMovie;
    TableColumn<Movie, Integer> columnId;
    TableColumn<Movie, String> columnTitle;
    TableColumn<Movie, Integer> columnYear;
    TableColumn<Movie, Double> columnRating;

    MovieGuiController controller;

    public MovieView() {}

    public void setController(MovieGuiController controller)
    {
        this.controller = controller;

        initComponents();
    }

    private void initComponents()
    {
        //controller = new MovieGuiController(this);

        background = new BorderPane();
        background.setLeft(createLeftPane());
        background.setCenter(createCenterPane());
        background.setBottom(createBottomPane());
    }

    private Pane createLeftPane()
    {
        tableViewMovie = new TableView<>();
        columnId = new TableColumn<>("ID");
        columnTitle = new TableColumn<>("Title");
        columnYear = new TableColumn<>("Year");
        columnRating = new TableColumn<>("Rating");

        columnId.setCellValueFactory(new PropertyValueFactory<Movie, Integer>("id"));
        columnTitle.setCellValueFactory(new PropertyValueFactory<Movie, String>("Title"));
        columnYear.setCellValueFactory(new PropertyValueFactory<Movie, Integer>("Year"));
        columnRating.setCellValueFactory(new PropertyValueFactory<Movie, Double>("Rating"));

        tableViewMovie.getColumns().add(columnId);
        tableViewMovie.getColumns().add(columnTitle);
        tableViewMovie.getColumns().add(columnYear);
        tableViewMovie.getColumns().add(columnRating);
        tableViewMovie.setItems(controller.getDataModel().getData());

        tableViewMovie.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Movie>()
        {
            @Override
            public void changed(ObservableValue<? extends Movie> observable, Movie oldValue, Movie newValue)
            {
                if (tableViewMovie.getSelectionModel().getSelectedItem() != null)
                {
                    textFieldId.setText(newValue.getId().toString());
                    textFieldTitle.setText(newValue.getTitle());
                    textFieldYear.setText(newValue.getYear().toString());
                    textFieldRating.setText(newValue.getRating().toString());
                }
            }
        });

        return new HBox(tableViewMovie);
    }

    private Pane createCenterPane()
    {
        labelId = new Label("ID: ");
        labelTitle = new Label("Title: ");
        labelYear = new Label("Year: ");
        labelRating = new Label("Rating: ");
        textFieldId = new TextField();
        textFieldTitle = new TextField();
        textFieldYear = new TextField();
        textFieldRating = new TextField();
        GridPane gridPane = new GridPane();

        gridPane.add(labelId, 0, 0);
        gridPane.add(labelTitle, 0, 1);
        gridPane.add(labelYear, 0, 2);
        gridPane.add(labelRating, 0, 3);
        gridPane.add(textFieldId, 1, 0);
        gridPane.add(textFieldTitle, 1, 1);
        gridPane.add(textFieldYear, 1, 2);
        gridPane.add(textFieldRating, 1, 3);

        return gridPane;
    }

    private Pane createBottomPane()
    {
        buttonClearAll = new Button("Clear all");
        buttonSave = new Button("Save");
        buttonDelete = new Button("Delete");
        buttonUpdate = new Button("Update");

        buttonSave.setOnAction(controller.addButtonHandler());
        buttonClearAll.setOnAction(controller.clearButtonHandler());
        buttonDelete.setOnAction(controller.deleteButtonHandler());
        buttonUpdate.setOnAction(controller.updateButtonHandler());

        return new HBox(buttonClearAll, buttonSave, buttonDelete, buttonUpdate);
    }

    public BorderPane getView()
    {
        return background;
    }
}
