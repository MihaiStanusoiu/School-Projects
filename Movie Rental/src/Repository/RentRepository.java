package Repository;

import Domain.Rental;
import Repository.AbstractRepository.AbstractCrudRepository;
import Repository.AbstractRepository.Repository;
import Validator.IValidator;

import java.io.FileNotFoundException;

/**
 * Created by Mike on 10/25/2016.
 */
public class RentRepository extends AbstractCrudRepository<Rental, Integer>
{
    public RentRepository(IValidator<Rental> val)
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
        return 0;
    }
}
