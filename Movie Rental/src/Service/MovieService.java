package Service;

import Domain.Client;
import Domain.Movie;
import Domain.MyDomainException;
import Repository.ClientRepository;
import Repository.MovieRepository;
import Repository.MyRepoException;
import utils.Observable;
import utils.Observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mike on 11/28/2016.
 */
public class MovieService implements Observable<Movie>
{
    protected List<Observer<Movie>> observers = new ArrayList<>();
    private MovieRepository repo;

    @Override
    public void addObserver(Observer<Movie> obs)
    {
        observers.add(obs);
    }

    @Override
    public void removeObserver(Observer<Movie> obs)
    {
        observers.remove(obs);
    }

    @Override
    public void notifyObservers()
    {
        for (Observer<Movie> obs : observers)
        {
            obs.update(this);
        }
    }

    public MovieService(MovieRepository repo)
    {
        this.repo = repo;
    }

    public void save(String title, String year, String rating) throws MyDomainException
    {
        try
        {
            Integer y = Integer.parseInt(year);
            Double r = Double.parseDouble(rating);
            Movie mov = new Movie(repo.getNextId(), title, y, r);
            repo.store(mov);
            notifyObservers();
        }
        catch (MyDomainException e)
        {
            throw e;
        }
        catch(NumberFormatException e)
        {
            throw e;
        }
    }

    public void update(String id, String title, String year, String rating) throws MyRepoException, MyDomainException
    {
        Movie mov = new Movie(Integer.parseInt(id), title, Integer.parseInt(year), Double.parseDouble(rating));
        repo.remove(Integer.parseInt(id));
        repo.store(mov);
        notifyObservers();
    }

    public void delete(String pos) throws MyRepoException, MyDomainException
    {
        Integer id = Integer.parseInt(pos);
        repo.remove(id);
        notifyObservers();
    }

    public void clearAll()
    {
        repo.clear();
        notifyObservers();
    }

    public Iterable<Movie> getMovies()
    {
        return repo.items();
    }
}
