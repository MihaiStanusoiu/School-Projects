package Service;

import Controller.Controller;
import Domain.Client;
import Domain.MyDomainException;
import Repository.ClientRepository;
import Repository.MyRepoException;
import utils.Observable;
import utils.Observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mihai on 11/28/2016.
 */
public class ClientService implements Observable<Client>
{
    protected List<Observer<Client>> observers = new ArrayList<>();
    private ClientRepository repo;

    @Override
    public void addObserver(Observer<Client> obs)
    {
        observers.add(obs);
    }

    @Override
    public void removeObserver(Observer<Client> obs)
    {
        observers.remove(obs);
    }

    @Override
    public void notifyObservers()
    {
        for (Observer<Client> obs : observers)
        {
            obs.update(this);
        }
    }

    public ClientService(ClientRepository repo)
    {
        this.repo = repo;
    }

    public void save(String name, String cnp) throws MyDomainException
    {
        try
        {
            Client cl = new Client(repo.getNextId(), name, cnp);
            repo.store(cl);
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

    public void update(String id, String name, String cnp) throws MyRepoException, MyDomainException
    {
        Client cl = new Client(Integer.parseInt(id), name, cnp);
        repo.validate(cl);
        repo.remove(Integer.parseInt(id));
        repo.store(cl);
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

    public Iterable<Client> getClients()
    {
        return repo.items();
    }

}
