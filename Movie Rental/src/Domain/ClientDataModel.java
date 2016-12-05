package Domain;

import GUI.ClientViewController;
import Repository.AbstractRepository.CrudRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

/**
 * Created by Mihai on 11/28/2016.
 */
public class ClientDataModel
{
    ObservableList<Client> model;
    ClientViewController controller;

    public ClientDataModel(CrudRepository<Client, Integer> repo)
    {
        model = FXCollections.observableArrayList((List<Client>) repo.items());
    }

    public void setController(ClientViewController ctrl)
    {
        this.controller = ctrl;
    }

    public ObservableList<Client> getData()
    {
        return model;
    }

    public void refresh()
    {
        model = FXCollections.observableArrayList((List<Client>)controller.getService().getClients());
    }
}
