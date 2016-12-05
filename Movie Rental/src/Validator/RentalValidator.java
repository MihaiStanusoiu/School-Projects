package Validator;

import Domain.MyDomainException;
import Domain.Rental;

/**
 * Created by Mike on 10/25/2016.
 */
public class RentalValidator implements IValidator<Rental>
{
    @Override
    public void validate(Rental entity) throws MyDomainException
    {
        MovieValidator movVal = new MovieValidator();
        ClientValidator clVal = new ClientValidator();
        movVal.validate(entity.getMov());
        clVal.validate(entity.getCl());
    }
}
