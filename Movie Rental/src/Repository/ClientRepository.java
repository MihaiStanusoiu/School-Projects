package Repository;

import Domain.Client;
import Domain.Movie;
import Repository.AbstractRepository.AbstractCrudRepository;
import Validator.IValidator;

import java.io.FileNotFoundException;

/**
 * Created by Mike on 10/25/2016.
 */
public class ClientRepository extends AbstractCrudRepository<Client, Integer>
{
    public ClientRepository(IValidator<Client> val)
    {
        setValidator(val);
    }

    @Override
    public void loadData() throws FileNotFoundException
    {}

    @Override
    public void saveData() throws FileNotFoundException
    {}

    @Override
    public Integer getNextId()
    {
        if (items.size() == 0)
        {
            return 1;
        }
        return items.get(items.size() - 1).getId() + 1;
    }
}
