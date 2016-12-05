package UI;

import Controller.Controller;
import Domain.*;
import Repository.MyArray;
import Repository.MyRepoException;
import javafx.application.Application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;

/**
 * Created by Mike on 10/6/2016.
 */
public class UI
{
    Controller _ctrl;

    public UI(Controller _ctrl)
    {
        this._ctrl = _ctrl;
    }

    private void printMenu()
    {
        System.out.println("Select one of the following options: ");
        System.out.println("    1. Add a new movie");
        System.out.println("    2. Remove a movie");
        System.out.println("    3. Update a movie");
        System.out.println("    4. Add a new client");
        System.out.println("    5. Remove a client");
        System.out.println("    6. Update a client");
        System.out.println("    7. Add a new rental");
        System.out.println("    8. Remove a rental");
        System.out.println("    9. Update a rental");
        System.out.println("    10. Filter movies");
        System.out.println("    11. Filter clients");
        System.out.println("    0. Exit");
    }

    private void printMovieFilterMenu()
    {
        System.out.println("        a) By title");
        System.out.println("        b) By rating");
    }

    private void printClientFilterMenu()
    {
        System.out.println("        a) By name");
        System.out.println("        b) By CNP");
    }

    public <E> void printList(Iterable<E> items, String entityName)
    {
        System.out.println("");
        System.out.println(entityName + ": ");
        items.forEach(System.out::println);
    }

    public void movieFilterMenu()
    {
        Scanner sc = new Scanner(System.in);
        printMovieFilterMenu();
        String input = sc.nextLine();
        switch (input)
        {
            case "a":
                try
                {
                    System.out.println("Enter part of title: ");
                    String title = sc.nextLine();
                    List<Movie> movies = _ctrl.filterMoviesByTitle(title);
                    movies.sort((mov1, mov2) -> mov1.getTitle().compareTo(mov2.getTitle()));
                    printList(movies, "Filterer movies");
                }
                catch (NumberFormatException ex)
                {
                    System.out.println(ex.getMessage());
                }
                break;

            case "b":
                try
                {
                    System.out.println("Enter rating: ");
                    String rating = sc.nextLine();
                    List<Movie> movies = _ctrl.filterMoviesByRating(rating);
                    movies.sort((mov1, mov2) -> (int)(mov1.getRating() - mov2.getRating()));
                    printList(movies, "Filterer movies");
                }
                catch (NumberFormatException ex)
                {
                    System.out.println(ex.getMessage());
                }
                break;
        }
    }

    public void clientFilterMenu()
    {
        Scanner sc = new Scanner(System.in);
        printClientFilterMenu();
        String input = sc.nextLine();
        switch (input)
        {
            case "a":
                System.out.println("Enter part of name: ");
                String name = sc.nextLine();
                List<Client> clients = _ctrl.filterClientsByName(name);
                clients.sort((cl1, cl2) -> cl1.getName().compareTo(cl2.getName()));
                printList(clients, "Filterer clients");
                break;

            case "b":
                System.out.println("Enter part of cnp: ");
                String cnp = sc.nextLine();
                clients = _ctrl.filterClientsByCnp(cnp);
                clients.sort((cl1, cl2) -> cl1.getId() - cl2.getId());
                printList(clients, "Filterer clients");
                break;
        }
    }

    public void startUi()
    {
        Scanner sc = new Scanner(System.in);
        while (true)
        {
            Iterable<Movie> movs = _ctrl.getMovies();
            printList(movs, "Movies");
            Iterable<Client> cls = _ctrl.getClients();
            printList(cls, "Clients");
            Iterable<Rental> rents = _ctrl.getRentals();
            printList(rents, "Rentals");
            System.out.println("");
            printMenu();
            System.out.println("");
            String input = sc.nextLine();
            switch (input)
            {
                case "1":
                    try
                    {
                        System.out.println("Enter title: ");
                        String title = sc.nextLine();
                        System.out.println("Enter year: ");
                        String year = sc.nextLine();
                        System.out.println("Enter rating: ");
                        String rating = sc.nextLine();
                        _ctrl.addMovie(title, year, rating);
                    }
                    catch (NumberFormatException ex)
                    {
                        System.out.println("Input data invalid!");
                    }
                    catch (MyRepoException ex)
                    {
                        System.out.println(ex.getMessage());
                    }
                    catch (MyDomainException ex)
                    {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case "2":
                    try
                    {
                        System.out.println("Enter the id: ");
                        String id = sc.nextLine();
                        _ctrl.removeMovie(id);
                    }
                    catch (MyDomainException ex)
                    {
                        System.out.println(ex.getMessage());
                    }
                    catch (MyRepoException ex)
                    {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case "3":
                    try
                    {
                        System.out.println("Enter the id of the movie to update: ");
                        String id = sc.nextLine();
                        System.out.println("Enter a new title: ");
                        String title = sc.nextLine();
                        System.out.println("Enter a new year: ");
                        String year = sc.nextLine();
                        System.out.println("Enter a new rating: ");
                        String rating = sc.nextLine();
                        _ctrl.updateMovie(id, title, year, rating);
                    }
                    catch (MyRepoException ex)
                    {
                        System.out.println(ex.getMessage());
                    }
                    catch (MyDomainException ex)
                    {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case "4":
                    try
                    {
                        System.out.println("Enter name: ");
                        String name = sc.nextLine();
                        System.out.println("Enter CNP: ");
                        String cnp = sc.nextLine();
                        _ctrl.addClient(name, cnp);
                    }
                    catch (NumberFormatException ex)
                    {
                        System.out.println("Input data invalid!");
                    }
                    catch (MyRepoException ex)
                    {
                        System.out.println(ex.getMessage());
                    }
                    catch (MyDomainException ex)
                    {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case "5":
                    try
                    {
                        System.out.println("Enter the id: ");
                        String id = sc.nextLine();
                        _ctrl.removeClient(id);
                    }
                    catch (MyDomainException ex)
                    {
                        System.out.println(ex.getMessage());
                    }
                    catch (MyRepoException ex)
                    {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case "6":
                    try
                    {
                        System.out.println("Enter the id of the client to update: ");
                        String id = sc.nextLine();
                        System.out.println("Enter a new name: ");
                        String name = sc.nextLine();
                        System.out.println("Enter a new CNP: ");
                        String cnp = sc.nextLine();
                        _ctrl.updateClient(id, name, cnp);
                    }
                    catch (MyRepoException ex)
                    {
                        System.out.println(ex.getMessage());
                    }
                    catch (MyDomainException ex)
                    {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case "7":
                    try
                    {
                        System.out.println("Enter movie ID: ");
                        String movId = sc.nextLine();
                        System.out.println("Enter client ID: ");
                        String clID = sc.nextLine();
                        _ctrl.addRental(movId, clID);
                    }
                    catch (NumberFormatException ex)
                    {
                        System.out.println("Input data invalid!");
                    }
                    catch (MyRepoException ex)
                    {
                        System.out.println(ex.getMessage());
                    }
                    catch (MyDomainException ex)
                    {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case "8":
                    try
                    {
                        System.out.println("Enter the id: ");
                        String id = sc.nextLine();
                        _ctrl.removeRental(id);
                    }
                    catch (MyDomainException ex)
                    {
                        System.out.println(ex.getMessage());
                    }
                    catch (MyRepoException ex)
                    {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case "9":
                    try
                    {
                        System.out.println("Enter the id of the rental to update: ");
                        String id = sc.nextLine();
                        System.out.println("Enter a new movie ID: ");
                        String movId = sc.nextLine();
                        System.out.println("Enter a new client ID: ");
                        String clId = sc.nextLine();
                        _ctrl.updateRental(id, movId, clId);
                    }
                    catch (MyRepoException ex)
                    {
                        System.out.println(ex.getMessage());
                    }
                    catch (MyDomainException ex)
                    {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case "10":
                    movieFilterMenu();
                    break;
                case "11":
                    clientFilterMenu();
                    break;
                case "0":
                    System.exit(0);
                    break;
            }
        }
    }
}
