package Validator;

import Domain.*;

/**
 * Created by Mihai on 10/23/2016.
 */
public class ClientValidator implements IValidator<Client>
{
    @Override
    public void validate(Client cl) throws MyDomainException
    {
        String name = cl.getName();
        String cnp = cl.getCnp();
        if (cnp.length() != 4 || name.equals("")) throw new MyDomainException("Input data invalid!");
        for (int i = 0; i < cnp.length(); ++i)
        {
            char c = cnp.charAt(i);
            if (c < '0' || c > '9') throw new MyDomainException("Input data invalid!");
        }
    }
}
