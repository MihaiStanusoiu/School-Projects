package Repository;

import Domain.Client;
import Domain.MyDomainException;
import Validator.IValidator;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Mihai on 11/3/2016.
 */
public class ClientRepoSerializable extends ClientRepository
{
    private String _fileName;

    public ClientRepoSerializable(IValidator<Client> val, String fileName)
    {
        super(val);
        this._fileName = fileName;
        try
        {
            loadData();
        }
        catch (FileNotFoundException ex)
        {
            ex.printStackTrace();
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }

    @Override
    public void loadData() throws FileNotFoundException
    {
        try
        {
            ObjectInputStream input = new ObjectInputStream(new FileInputStream(_fileName));
            items = (ArrayList<Client>) input.readObject();
            input.close();
        }
        catch (FileNotFoundException ex)
        {
            ex.printStackTrace();
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
        catch (ClassNotFoundException ex)
        {
            ex.printStackTrace();
        }
    }

    @Override
    public void saveData() throws FileNotFoundException
    {
        try
        {
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(_fileName));
            output.writeObject(items);
            output.close();
        }
        catch (FileNotFoundException ex)
        {
            ex.printStackTrace();
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }

    public ClientRepoSerializable(IValidator<Client> val)
    {
        super(val);
    }

    @Override
    public void store(Client el) throws MyDomainException
    {
        try
        {
            super.store(el);
            saveData();
        }
        catch (FileNotFoundException ex)
        {
            ex.printStackTrace();
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }

    @Override
    public Client remove(Integer integer) throws MyDomainException, MyRepoException
    {
        try
        {
            Client cl = super.remove(integer);
            saveData();
            return cl;
        }
        catch (FileNotFoundException ex)
        {
            ex.printStackTrace();
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
        return null;
    }
}
