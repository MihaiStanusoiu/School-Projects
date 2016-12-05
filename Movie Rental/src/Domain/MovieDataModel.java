package Domain;

import GUI.MovieGuiController;
import Repository.AbstractRepository.CrudRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Collections;
import java.util.List;
import java.util.Observable;

/**
 * Created by Mike on 11/15/2016.
 */
public class MovieDataModel
{
    ObservableList<Movie> model;
    MovieGuiController controller;

    public MovieDataModel(CrudRepository<Movie, Integer> repo)
    {
        model = FXCollections.observableArrayList((List<Movie>)repo.items());
    }

    public void setController(MovieGuiController controller)
    {
        this.controller = controller;
    }

    public ObservableList<Movie> getData()
    {
        return model;
    }

    public void refresh()
    {
        model = FXCollections.observableArrayList((List<Movie>)controller.getService().getMovies());
    }
}
