package Validator;

import Domain.MyDomainException;

/**
 * Created by Mihai on 10/23/2016.
 */
public class Validator<E>
{
    private IValidator<E> validator;

    public Validator(IValidator<E> val)
    {
        this.validator = val;
    }

    public void validate(E entity) throws MyDomainException
    {
        validator.validate(entity);
    }
}
