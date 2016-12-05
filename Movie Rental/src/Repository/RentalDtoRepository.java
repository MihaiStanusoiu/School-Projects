package Repository;

import Domain.Rental;
import Domain.RentalDto;
import Repository.AbstractRepository.Repository;
import Validator.IValidator;

import java.io.FileNotFoundException;

/**
 * Created by Mihai on 12/5/2016.
 */
public class RentalDtoRepository extends Repository<RentalDto, Integer>
{
    public RentalDtoRepository()
    {
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
