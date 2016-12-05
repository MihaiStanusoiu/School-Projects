package Repository;
import Domain.Movie;
import Repository.AbstractRepository.Repository;
import Validator.IValidator;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created by Mike on 10/6/2016.
 */
public class MovieRepository extends Repository<Movie, Integer>
{
    public MovieRepository(IValidator<Movie> val)
    {
        super(val);
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
        return items.get(items.size() - 1).getId() + 1;
    }

}
