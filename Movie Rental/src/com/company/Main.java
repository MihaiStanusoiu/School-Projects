package com.company;
import Domain.*;
import GUI.ClientViewController;
import GUI.MovieGuiController;
import GUI.MovieView;
import GUI.RentalViewController;
import Repository.AbstractRepository.CrudRepository;
import Service.ClientService;
import Service.MovieService;
import Service.RentalService;
import UI.UI;
import Controller.Controller;
import Repository.*;
import Validator.ClientValidator;
import Validator.MovieValidator;
import Validator.RentalValidator;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import Tests.TestSuite;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application
{
    BorderPane clLayout;
    BorderPane rentLayout;
    Stage primaryStage;
    Stage movieStage;
    Stage clientStage;

    MovieRepository movRepo;
    ClientRepository clRepo;
    RentRepository rentRepo;
    Controller ctrl;

    ClientService clService;
    MovieService movieService;
    RentalService rentService;

    RentalViewController rentViewCtrl;
    ClientViewController clViewCtrl;
    MovieGuiController mvCtrl;

    public static void main(String args[])
    {
        /*
        Result res = JUnitCore.runClasses(TestSuite.class);
        for (Failure fail : res.getFailures())
            System.out.println(fail.toString());

        MovieRepoFromFile movRepo = new MovieRepoFromFile(new MovieValidator(), "src/movies.txt");
        ClientRepoSerializable clRepo = new ClientRepoSerializable(new ClientValidator(), "src/clients.txt");
        RentRepository rentRepo = new RentRepository(new RentalValidator());
        Controller ctrl = new Controller(movRepo, clRepo, rentRepo);

        try
        {
            //ctrl.addMovie("BvS", "2016", "7.5");
            //ctrl.addMovie("Departed", "2008", "10");
            //ctrl.addMovie("Suicide Squad", "2016", "7");
            //ctrl.addClient("Vlad", "1234");
            //ctrl.addClient("Steph", "1111");
            //ctrl.addClient("Mike", "2222");
        }
        catch (Exception ex) {}

        UI ui = new UI(ctrl);
        ui.startUi();*/

        launch(args);
    }

    private void initRentalView()
    {
        try
        {
            FXMLLoader loader = new FXMLLoader();

            loader.setLocation(Main.class.getResource("/GUI/RentalView.fxml"));
            rentLayout = (BorderPane)loader.load();

            rentViewCtrl = loader.getController();
            rentViewCtrl.setClientViewController(clViewCtrl);
            rentViewCtrl.setMovieViewController(mvCtrl);
            RentalDataModel model = new RentalDataModel(rentRepo);
            rentViewCtrl.setModel(model);
            model.setController(rentViewCtrl);
            rentViewCtrl.setService(rentService);
            rentService.addObserver(rentViewCtrl);

            Scene scene = new Scene(rentLayout);
            primaryStage.setX(0);
            primaryStage.setY(0);
            primaryStage.setHeight(1040);
            primaryStage.setWidth(960);
            primaryStage.setScene(scene);
            this.primaryStage.setTitle("Rentals manager");
            primaryStage.setOnCloseRequest(event ->
            {
                movieStage.close();
                clientStage.close();
            });
            primaryStage.show();
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }

    private void initClientView()
    {
        try
        {
            FXMLLoader loader = new FXMLLoader();

            loader.setLocation(Main.class.getResource("/GUI/ClientView.fxml"));
            clLayout = (BorderPane)loader.load();

            clViewCtrl = loader.getController();
            ClientDataModel clModel = new ClientDataModel(clRepo);
            clViewCtrl.setModel(clModel);
            clModel.setController(clViewCtrl);
            clViewCtrl.setService(clService);
            clService.addObserver(clViewCtrl);

            Scene scene = new Scene(clLayout);
            clientStage = new Stage();
            clientStage.setX(960);
            clientStage.setY(520);
            clientStage.setWidth(960);
            clientStage.setHeight(520);
            clientStage.setScene(scene);
            clientStage.setTitle("Clients manager");
            clientStage.setOnCloseRequest(event ->
            {
                event.consume();
            });
            clientStage.show();
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }

    private void initMovieView()
    {
        mvCtrl = new MovieGuiController();
        MovieDataModel model = new MovieDataModel(movRepo);
        mvCtrl.setModel(model);
        model.setController(mvCtrl);
        mvCtrl.setService(movieService);
        movieService.addObserver(mvCtrl);

        MovieView movView = new MovieView();
        mvCtrl.setView(movView);
        movView.setController(mvCtrl);

        Parent parent = movView.getView();
        Scene scene = new Scene(parent, 500, 500);

        movieStage = new Stage();
        movieStage.setX(960);
        movieStage.setY(0);
        movieStage.setWidth(960);
        movieStage.setHeight(520);
        movieStage.setScene(scene);
        movieStage.setTitle("Movies manager");
        movieStage.setOnCloseRequest(event ->
        {
            event.consume();
        });
        movieStage.show();
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        movRepo = new MovieRepoFromXml(new MovieValidator(), "src/moviesxml.xml");
        clRepo = new ClientRepoSerializable(new ClientValidator(), "src/clients.txt");
        rentRepo = new RentRepository(new RentalValidator());

        ctrl = new Controller(movRepo, clRepo, rentRepo);
        clService = new ClientService(clRepo);
        movieService = new MovieService(movRepo);
        rentService = new RentalService(rentRepo, movRepo, clRepo);

        this.primaryStage = primaryStage;

        initMovieView();
        initClientView();
        initRentalView();
    }
}
