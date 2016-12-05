package Domain;

import GUI.RentalViewController;
import Repository.AbstractRepository.CrudRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

/**
 * Created by Mihai on 12/5/2016.
 */
public class RentalDataModel
{
    ObservableList<Rental> model;
    RentalViewController controller;

    public RentalDataModel(CrudRepository<Rental, Integer> repo)
    {
        model = FXCollections.observableArrayList((List<Rental>) repo.items());
    }

    public void setController(RentalViewController ctrl)
    {
        this.controller = ctrl;
    }

    public ObservableList<Rental> getData()
    {
        return model;
    }

    public void refresh()
    {
        model = FXCollections.observableArrayList((List<Rental>)controller.getService().getRentals());
    }
}
