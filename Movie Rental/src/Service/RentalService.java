package Service;

import Domain.*;
import Domain.Rental;
import Repository.*;
import utils.Observable;
import utils.Observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mihai on 12/5/2016.
 */
public class RentalService implements Observable<Rental>
{
    protected List<Observer<Rental>> observers = new ArrayList<>();
    private RentRepository repo;
    private MovieRepository movRepo;
    private ClientRepository clRepo;

    @Override
    public void addObserver(Observer<Rental> obs)
    {
        observers.add(obs);
    }

    @Override
    public void removeObserver(Observer<Rental> obs)
    {
        observers.remove(obs);
    }

    @Override
    public void notifyObservers()
    {
        for (Observer<Rental> obs : observers)
        {
            obs.update(this);
        }
    }

    public RentalService(RentRepository repo, MovieRepository movRepo, ClientRepository clRepo)
    {
        this.repo = repo;
        this.movRepo = movRepo;
        this.clRepo = clRepo;
    }

    public void save(String movId , String clId) throws MyDomainException, MyRepoException
    {
        try
        {
            Integer mId = Integer.parseInt(movId);
            Integer cId = Integer.parseInt(clId);
            Movie mov = movRepo.getItem(mId);
            Client cl = clRepo.getItem(cId);
            repo.store(new Rental(repo.size() + 1, mov, cl));
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

    public void update(String id, String movId, String clId) throws MyRepoException, MyDomainException
    {
        Integer mId = Integer.parseInt(movId);
        Integer cId = Integer.parseInt(clId);
        Movie mov = movRepo.getItem(mId);
        Client cl = clRepo.getItem(cId);
        Rental rent = new Rental(Integer.parseInt(id), mov, cl);
        repo.remove(Integer.parseInt(id));
        repo.store(rent);
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

    public Iterable<Rental> getRentals()
    {
        return repo.items();
    }
}
