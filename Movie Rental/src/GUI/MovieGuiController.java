package GUI;

import Domain.Client;
import Domain.Movie;
import Domain.MovieDataModel;
import Domain.MyDomainException;
import Repository.MyRepoException;
import Service.ClientService;
import Service.MovieService;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import Controller.Controller;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import utils.Observable;
import utils.Observer;

import java.util.List;

/**
 * Created by Mike on 11/15/2016.
 */
public class MovieGuiController implements Observer<Movie>
{
    private MovieView view;
    private MovieDataModel model;
    private MovieService service;

    public MovieGuiController(MovieView view)
    {
        this.view = view;
    }

    public MovieGuiController() {}

    public void setModel(MovieDataModel model)
    {
        this.model = model;
        model.setController(this);
    }

    public void setView(MovieView view)
    {
        this.view = view;
    }

    public void setService(MovieService service)
    {
        this.service = service;
    }

    public MovieService getService()
    {
        return service;
    }

    public MovieDataModel getDataModel()
    {
        return model;
    }

    public TableView<Movie> getTableView()
    {
        return view.tableViewMovie;
    }

    private void showErrorMessage()
    {
        Alert msg = new Alert(Alert.AlertType.ERROR);
        msg.setHeaderText("Input data invalid");
        msg.show();
    }

    public EventHandler<ActionEvent> addButtonHandler()
    {
        EventHandler<ActionEvent> addEvent = new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                String title = view.textFieldTitle.getText();
                String year = view.textFieldYear.getText();
                String rating = view.textFieldRating.getText();

                try
                {
                    service.save(title, year, rating);
                }
                catch (NumberFormatException ex)
                {
                    System.out.println("Input data invalid!");
                    showErrorMessage();
                }
                catch (MyDomainException ex)
                {
                    System.out.println(ex.getMessage());
                    showErrorMessage();
                }
            };
        };

        return addEvent;
    }

    public EventHandler<ActionEvent> deleteButtonHandler()
    {
        EventHandler<ActionEvent> ev = new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                String id = view.textFieldId.getText();

                try
                {
                    service.delete(id);
                }
                catch (MyDomainException ex)
                {
                    System.out.println(ex.getMessage());
                    showErrorMessage();
                }
                catch (MyRepoException ex)
                {
                    System.out.println(ex.getMessage());
                    showErrorMessage();
                }
                catch (NumberFormatException ex)
                {
                    System.out.println(ex.getMessage());
                    showErrorMessage();
                }
            }
        };

        return ev;
    }

    public EventHandler<ActionEvent> updateButtonHandler()
    {
        EventHandler<ActionEvent> ev = new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                String id = view.textFieldId.getText();
                String title = view.textFieldTitle.getText();
                String year = view.textFieldYear.getText();
                String rating = view.textFieldRating.getText();

                try
                {
                    service.update(id, title, year, rating);

                }
                catch (MyDomainException ex)
                {
                    System.out.println(ex.getMessage());
                    showErrorMessage();
                }
                catch (MyRepoException ex)
                {
                    System.out.println(ex.getMessage());
                    showErrorMessage();
                }
                catch (NumberFormatException ex)
                {
                    System.out.println(ex.getMessage());
                    showErrorMessage();
                }
            }
        };

        return ev;
    }

    public EventHandler<ActionEvent> clearButtonHandler()
    {
        EventHandler<ActionEvent> ev =  new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                service.clearAll();
            }
        };

        return ev;
    }

    @Override
    public void update(Observable<Movie> observable)
    {
        MovieService service = (MovieService) observable;
        model.getData().setAll((List<Movie>)service.getMovies());
        view.tableViewMovie.setItems(model.getData());
    }
}
