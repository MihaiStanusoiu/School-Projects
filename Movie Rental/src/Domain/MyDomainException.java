package Domain;

/**
 * Created by Mihai on 10/8/2016.
 */
public class MyDomainException extends Exception
{
    public MyDomainException(String msg)
    {
        super(msg);
    }

    public MyDomainException(Throwable throwable)
    {
        super(throwable);
    }

    public MyDomainException(String msg, Throwable throwable)
    {
        super(msg, throwable);
    }

    public String getMessage()
    {
        return super.getMessage();
    }
}
