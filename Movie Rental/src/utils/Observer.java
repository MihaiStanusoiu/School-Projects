package utils;

/**
 * Created by Mihai on 11/28/2016.
 */
public interface Observer<E>
{
    void update(Observable<E> observable);
}
