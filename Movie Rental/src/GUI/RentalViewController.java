package GUI;

import Domain.*;
import Domain.Rental;
import Repository.MyRepoException;
import Service.RentalService;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import utils.Observable;
import utils.Observer;

import java.util.List;

/**
 * Created by Mihai on 12/5/2016.
 */
public class RentalViewController implements Observer<Rental>
{
    @FXML
    private TableView<Rental> rentalsTableView;
    @FXML
    private TableColumn<Rental, Integer> idColumn;
    @FXML
    private TableColumn<Rental, Integer> movIdColumn;
    @FXML
    private TableColumn<Rental, String> titleColumn;
    @FXML
    private TableColumn<Rental, Integer> clIdColumn;
    @FXML
    private TableColumn<Rental, String> nameColumn;
    @FXML
    private TextField idTextBox;
    @FXML
    private TextField movIdTextBox;
    @FXML
    private TextField nameTextBox;
    @FXML
    private TextField clIdTextBox;
    @FXML
    private TextField titleTextBox;
    @FXML
    private Button clearButton;
    @FXML
    private Button saveButton;
    @FXML
    private Button updateButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button moviesButton;
    @FXML
    private Button clientsButton;
    @FXML
    private HBox bottomLayout;

    private ClientViewController clViewCtrl;
    private MovieGuiController movViewCtrl;

    RentalDataModel model;
    RentalService service;

    public RentalViewController() {}

    public void setClientViewController(ClientViewController ctrl)
    {
        this.clViewCtrl = ctrl;
    }

    public void setMovieViewController(MovieGuiController ctrl)
    {
        this.movViewCtrl = ctrl;
    }

    public void setService(RentalService service)
    {
        this.service = service;
    }

    public RentalService getService()
    {
        return service;
    }

    public void setModel(RentalDataModel model)
    {
        this.model = model;
        model.setController(this);
        rentalsTableView.setItems(FXCollections.observableArrayList(this.model.getData()));
    }

    public RentalDataModel getDataModel()
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
        idColumn.setCellValueFactory(new PropertyValueFactory<Rental, Integer>("id"));
        movIdColumn.setCellValueFactory(new PropertyValueFactory<Rental, Integer>("MovieId"));
        clIdColumn.setCellValueFactory(new PropertyValueFactory<Rental, Integer>("ClientId"));

        rentalsTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Rental>()
        {
            @Override
            public void changed(ObservableValue<? extends Rental> observable, Rental oldValue, Rental newValue)
            {
                if (rentalsTableView.getSelectionModel().getSelectedItem() != null)
                {
                    idTextBox.setText(newValue.getId().toString());
                    movIdTextBox.setText(newValue.getMovieId().toString());
                    clIdTextBox.setText(newValue.getClientId().toString());

                    if (clViewCtrl != null)
                    {
                        TableView tbView = clViewCtrl.getClientsTableView();
                        tbView.requestFocus();
                        Client index = newValue.getCl();
                        tbView.getSelectionModel().select(index);
                        tbView.getFocusModel().focus(index.getId());
                    }

                    if (movViewCtrl != null)
                    {
                        TableView tbView = movViewCtrl.getTableView();
                        tbView.requestFocus();
                        Movie index = newValue.getMov();
                        tbView.getSelectionModel().select(index);
                        tbView.getFocusModel().focus(index.getId());
                    }
                }
            }
        });
    }

    @FXML
    public void handleOpenClients()
    {
    }

    @FXML
    public void handleOpenMovies()
    {
    }

    @FXML
    public void handleClearAll()
    {
        service.clearAll();
    }

    @FXML
    public void handleSave()
    {
        String movID = movIdTextBox.getText();
        String clId = clIdTextBox.getText();

        try
        {
            service.save(movID, clId);
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
        catch (MyRepoException ex)
        {
            System.out.println(ex.getMessage());
            showErrorMessage();
        }
    }

    @FXML
    public void handleUpdate()
    {
        String id = idTextBox.getText();
        String movId = movIdTextBox.getText();
        String clId = clIdTextBox.getText();

        try
        {
            service.update(id, movId, clId);
        }
        catch (NumberFormatException ex)
        {
            System.out.println("Input data invalid!");
            showErrorMessage();
        }
        catch (MyRepoException ex)
        {
            System.out.println(ex.getMessage());
            showErrorMessage();

        }
        catch (MyDomainException ex)
        {
            System.out.println(ex.getMessage());
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

    @Override
    public void update(Observable<Rental> observable)
    {
        RentalService service = (RentalService)observable;
        model.getData().setAll((List<Rental>)service.getRentals());
        rentalsTableView.setItems(model.getData());
    }
}
