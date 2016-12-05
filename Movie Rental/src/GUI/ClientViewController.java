package GUI;

import Controller.Controller;
import Domain.Client;
import Domain.ClientDataModel;
import Domain.MyDomainException;
import Repository.MyRepoException;
import Service.ClientService;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.HBox;
import utils.Observable;
import utils.Observer;

import java.util.List;

/**
 * Created by Mihai on 11/28/2016.
 */
public class ClientViewController implements Observer<Client>
{
    @FXML
    private TableView<Client> clientsTableView;
    @FXML
    private TableColumn<Client, Integer> idColumn;
    @FXML
    private TableColumn<Client, String> nameColumn;
    @FXML
    private TableColumn<Client, String> cnpColumn;
    @FXML
    private TextField idTextBox;
    @FXML
    private TextField nameTextBox;
    @FXML
    private TextField cnpTextBox;
    @FXML
    private Button clearButton;
    @FXML
    private Button saveButton;
    @FXML
    private Button updateButton;
    @FXML
    private Button deleteButton;
    @FXML
    private HBox bottomLayout;
    @FXML
    private Label validLabel;

    ClientDataModel model;
    ClientService service;

    public ClientViewController() {}

    public void setService(ClientService service)
    {
        this.service = service;
    }

    public ClientService getService()
    {
        return service;
    }

    public void setModel(ClientDataModel model)
    {
        this.model = model;
        model.setController(this);
        clientsTableView.setItems(FXCollections.observableArrayList(this.model.getData()));
    }

    public ClientDataModel getDataModel()
    {
        return model;
    }

    private void showErrorMessage()
    {
        Alert msg = new Alert(Alert.AlertType.ERROR);
        msg.setHeaderText("Input data invalid");
        msg.show();
    }

    @FXML
    private void initialize()
    {
        idColumn.setCellValueFactory(new PropertyValueFactory<Client, Integer>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("name"));
        cnpColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("cnp"));

        clientsTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Client>()
        {
            @Override
            public void changed(ObservableValue<? extends Client> observable, Client oldValue, Client newValue)
            {
                if (clientsTableView.getSelectionModel().getSelectedItem() != null)
                {
                    idTextBox.setText(newValue.getId().toString());
                    nameTextBox.setText(newValue.getName().toString());
                    cnpTextBox.setText(newValue.getCnp().toString());
                }
            }
        });
    }

    @FXML
    public void handleClearAll()
    {
        service.clearAll();
    }

    @FXML
    public void handleSave()
    {
        String name = nameTextBox.getText();
        String cnp = cnpTextBox.getText();

        try
        {
            service.save(name, cnp);
        }
        catch (NumberFormatException ex)
        {
            System.out.println("Input data invalid!");
            validLabel.setDisable(false);
            showErrorMessage();
        }
        catch (MyDomainException ex)
        {
            System.out.println(ex.getMessage());
            validLabel.setDisable(false);
            showErrorMessage();
        }
    }

    @FXML
    public void handleUpdate()
    {
        String id = idTextBox.getText();
        String name = nameTextBox.getText();
        String cnp = cnpTextBox.getText();

        try
        {
            service.update(id, name, cnp);
        }
        catch (NumberFormatException ex)
        {
            System.out.println("Input data invalid!");
            validLabel.setDisable(false);
            showErrorMessage();
        }
        catch (MyRepoException ex)
        {
            System.out.println(ex.getMessage());
            validLabel.setDisable(false);
            showErrorMessage();

        }
        catch (MyDomainException ex)
        {
            System.out.println(ex.getMessage());
            validLabel.setDisable(false);
            showErrorMessage();
        }
    }

    @FXML
    public void handleDelete()
    {
        String id = idTextBox.getText();

        try
        {
            service.delete(id);
        }
        catch (MyDomainException ex)
        {
            System.out.println(ex.getMessage());
            validLabel.setDisable(false);
            showErrorMessage();
        }
        catch (MyRepoException ex)
        {
            System.out.println(ex.getMessage());
            validLabel.setDisable(false);
            showErrorMessage();
        }
        catch (NumberFormatException ex)
        {
            System.out.println(ex.getMessage());
            validLabel.setDisable(false);
            showErrorMessage();
        }
    }

    @Override
    public void update(Observable<Client> observable)
    {
        ClientService service = (ClientService)observable;
        model.getData().setAll((List<Client>)service.getClients());
        clientsTableView.setItems(model.getData());
        validLabel.setDisable(true);

    }
}
