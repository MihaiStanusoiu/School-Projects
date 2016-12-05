package Repository;

/**
 * Created by Mihai on 10/7/2016.
 */
public class MyRepoException extends Exception
{
    public MyRepoException(String msg)
    {
        super(msg);
    }

    public MyRepoException(Throwable throwable)
    {
        super(throwable);
    }

    public MyRepoException(String msg, Throwable throwable)
    {
        super(msg, throwable);
    }

    public String getMessage()
    {
        return super.getMessage();
    }
}
