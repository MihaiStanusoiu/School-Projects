package Validator;

import Domain.*;

/**
 * Created by Mihai on 10/23/2016.
 */
public class MovieValidator implements IValidator<Movie>
{
    @Override
    public void validate(Movie mov) throws MyDomainException
    {
        Integer y = mov.getYear();
        Double r = mov.getRating();
        if (y.toString().length() != 4 || !(r > 0 && r <= 10) || mov.getTitle() == "")
            throw new MyDomainException("Input data invalid");
    }
}
