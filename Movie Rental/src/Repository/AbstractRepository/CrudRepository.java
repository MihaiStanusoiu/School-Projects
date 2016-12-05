package Repository.AbstractRepository;

import Repository.MyRepoException;
import Validator.IValidator;
import Validator.Validator;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Created by Mihai on 10/15/2016.
 */
public interface CrudRepository<E, ID>
{
    Integer size();
    void store(E el) throws Exception;
    E remove(ID id) throws Exception;
    E getItem(ID id) throws Exception;
    ID getNextId();
    void loadData() throws FileNotFoundException;
    void saveData() throws FileNotFoundException;
    void setValidator(IValidator<E> val);
    void validate(E el) throws Exception;
    Iterable<E> items();
    void clear();
}
