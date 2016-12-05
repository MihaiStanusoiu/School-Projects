package Controller;

import Repository.*;
import Domain.*;
import utils.Observable;
import utils.Observer;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by Mike on 10/6/2016.
 */
public class Controller
{
    MovieRepository _movRepo;
    ClientRepository _clRepo;
    RentRepository _rentRepo;

    public Controller(MovieRepository _movRepo, ClientRepository _clRepo, RentRepository _rentRepo)
    {
        this._movRepo = _movRepo;
        this._clRepo = _clRepo;
        this._rentRepo = _rentRepo;
    }

    /*
    Filters the given list by the given predicate, using streams
    Input:
        - given list : List<E>
        - filter predicate : Predicate<E>
    Output:
        - filtered list : List<E>
     */

    /**
     *
     * @param list
     * @param pred
     * @param <E>
     * @return
     */
    public <E> List<E> filterGeneric(List<E> list, Predicate<E> pred)
    {
        return list.stream().filter(pred).collect(Collectors.toList());
    }

    /*
    Filters given list of movies by rating
     */
    public List<Movie> filterMoviesByRating(String rating)
    {
        Double r = Double.parseDouble(rating);
        Predicate<Movie> pred = mov -> mov.getRating() >= r;

        return filterGeneric((List<Movie>)_movRepo.items(), pred);
    }

    /*
    Filters given list of movies by Title
    */
    public List<Movie> filterMoviesByTitle(String title)
    {
        Predicate<Movie> pred = mov -> mov.getTitle().contains(title);

        return filterGeneric((List<Movie>)_movRepo.items(), pred);
    }

    /*
    Filters given list of clients by name
    */
    public List<Client> filterClientsByName(String name)
    {
        Predicate<Client> pred = client -> client.getName().contains(name);

        return filterGeneric((List<Client>)_clRepo.items(), pred);
    }

    /*
    Filters given list of clients by CNP
    */
    public List<Client> filterClientsByCnp(String cnp)
    {
        Predicate<Client> pred = client -> client.getCnp().contains(cnp);

        return filterGeneric((List<Client>)_clRepo.items(), pred);
    }

    public void clearMovies()
    {
        _movRepo.clear();
    }

    public void clearClients()
    {
        _clRepo.clear();
    }

    public void clearRentals()
    {
        _rentRepo.clear();
    }

    public void addMovie(String title, String year, String rating) throws MyRepoException, MyDomainException
    {
        Integer y = Integer.parseInt(year);
        Double r = Double.parseDouble(rating);
        Movie mov = new Movie(_movRepo.getNextId(), title, y, r);
        _movRepo.store(mov);
    }

    public Movie removeMovie(String pos) throws MyRepoException, MyDomainException
    {
        Integer id = Integer.parseInt(pos);
        return _movRepo.remove(id);
    }

    public Movie updateMovie(String id, String title, String year, String rating) throws MyRepoException, MyDomainException
    {
        Movie mov = new Movie(Integer.parseInt(id), title, Integer.parseInt(year), Double.parseDouble(rating));
        _movRepo.remove(Integer.parseInt(id));
        _movRepo.store(mov);
        return mov;
    }

    public Movie findMovie(String id) throws MyRepoException
    {
        Integer i = Integer.parseInt(id);
        return _movRepo.getItem(i);
    }

    public Iterable<Movie> getMovies()
    {
        return _movRepo.items();
    }

    public Integer getMoviesNr() { return _movRepo.size(); }

    public void addClient(String name, String cnp) throws MyRepoException, MyDomainException
    {
        Client cl = new Client(_clRepo.getNextId(), name, cnp);
        _clRepo.store(cl);
    }

    public Client removeClient(String pos) throws MyRepoException, MyDomainException
    {
        Integer id = Integer.parseInt(pos);
        return _clRepo.remove(id);
    }
    public Client updateClient(String id, String name, String cnp) throws MyRepoException, MyDomainException
    {
        Client cl = new Client(Integer.parseInt(id), name, cnp);
        _clRepo.remove(Integer.parseInt(id));
        _clRepo.store(cl);
        /*Iterable<Rental> all = _rentRepo.items();
        for (Rental rent : all)
        {
            if (rent.getCl().getId() == Integer.parseInt(id))
            {
                updateRental(rent.getId().toString(), rent.getMov().getId().toString(), id);
            }
        }*/
        return cl;
    }

    public Client findClient(String id) throws MyRepoException
    {
        Integer i = Integer.parseInt(id);
        return _clRepo.getItem(i);
    }

    public Iterable<Client> getClients()
    {
        return _clRepo.items();
    }

    public Integer getClientsNr() { return _clRepo.size(); }

    public void addRental(String movId, String clId) throws MyRepoException, MyDomainException
    {
        Integer mId = Integer.parseInt(movId);
        Integer cId = Integer.parseInt(clId);
        Movie mov = _movRepo.getItem(mId);
        Client cl = _clRepo.getItem(cId);
        _rentRepo.store(new Rental(_rentRepo.size() + 1, mov, cl));
    }

    public Rental removeRental(String pos) throws MyRepoException, MyDomainException
    {
        Integer id = Integer.parseInt(pos);
        return _rentRepo.remove(id);
    }

    public Rental updateRental(String id, String movId, String clId) throws MyRepoException, MyDomainException
    {
        Integer mId = Integer.parseInt(movId);
        Integer cId = Integer.parseInt(clId);
        Movie mov = _movRepo.getItem(mId);
        Client cl = _clRepo.getItem(cId);
        Rental rent = new Rental(Integer.parseInt(id), mov, cl);
        _rentRepo.remove(Integer.parseInt(id));
        _rentRepo.store(rent);
        return rent;
    }

    public Rental findRental(String id) throws MyRepoException
    {
        Integer i = Integer.parseInt(id);
        return _rentRepo.getItem(i);
    }

    public Iterable<Rental> getRentals()
    {
        return _rentRepo.items();
    }

    public Integer getRentalsNr() { return _rentRepo.size(); }

}