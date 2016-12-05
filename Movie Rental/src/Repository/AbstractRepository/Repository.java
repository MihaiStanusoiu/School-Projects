package Repository.AbstractRepository;

import Validator.IValidator;
import Validator.Validator;

import java.io.FileNotFoundException;

/**
 * Created by Mihai on 10/17/2016.
 */
public abstract class Repository<E extends IDInterface<ID>, ID> extends AbstractCrudRepository<E, ID>
{
    public Repository(IValidator<E> val)
    {
        //validator = new Validator<E>(val);
        setValidator(val);
    }

    public Repository() {}

    @Override
    public void loadData() throws FileNotFoundException
    {

    }

    @Override
    public void saveData() throws FileNotFoundException
    {

    }
}
