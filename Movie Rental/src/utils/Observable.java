package utils;

/**
 * Created by Mihai on 11/28/2016.
 */
public interface Observable<E>
{
    void addObserver(Observer<E> obs);

    void removeObserver(Observer<E> obs);

    void notifyObservers();
}
