package Validator;

import Domain.MyDomainException;

/**
 * Created by Mihai on 10/23/2016.
 */
public interface IValidator<E>
{
    void validate(E entity) throws MyDomainException;
}
