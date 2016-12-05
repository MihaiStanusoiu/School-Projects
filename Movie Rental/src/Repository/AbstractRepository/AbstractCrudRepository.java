package Repository.AbstractRepository;

import Domain.MyDomainException;
import Repository.MyRepoException;
import Validator.Validator;
import Validator.IValidator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mihai on 10/15/2016.
 */
public abstract class AbstractCrudRepository<E extends IDInterface<ID>, ID> implements CrudRepository<E, ID>
{
    protected List<E> items = new ArrayList<E>();
    protected Validator<E> validator;
    protected Integer maxId = 0;

    @Override
    public Integer size()
    {
        return items.size();
    }

    @Override
    public void store(E el) throws MyDomainException
    {
        if (validator != null)
           validator.validate(el);
        items.add(el);
    }

    @Override
    public E remove(ID id) throws MyDomainException, MyRepoException
    {
        Integer i = 0;
        while (i < items.size() && !items.get(i).getId().equals(id)) i++;
        if (i == items.size())
            throw new MyRepoException("Element not stored!");
        E el = items.get(i);
        items.remove(el);
        return el;
    }

    @Override
    public E getItem(ID id) throws MyRepoException
    {
        Integer i = 0;
        while (i < items.size() && !items.get(i).getId().equals(id)) i++;
        if (i == items.size())
            throw new MyRepoException("ELement not stored!");
        return items.get(i);
    }

    @Override
    public Iterable<E> items()
    {
        return items;
    }

    @Override
    public void clear()
    {
        items.clear();
    }

    @Override
    public void validate(E el) throws MyDomainException
    {
        if (validator != null)
            validator.validate(el);
    }

    @Override
    public void setValidator(IValidator<E> val)
    {
        validator = new Validator<E>(val);
    }

}
