package Repository;
import Domain.Movie;

/**
 * Created by Mike on 10/6/2016.
 */
public class MovieArray
{
    //private MyArray<Movie> _movs;
    private Movie _movs[];
    private Integer _cap;
    private int _size;

    public MovieArray(Integer cap)
    {
        //_movs = new MyArray<>(100);
        _movs = new Movie[cap];
        _size = 0;
        _cap = cap;
    }
    /*
    private void resize()
    {
        _cap *= 2;
        Movie aux[] = new Movie[_cap];
        for (Integer i = 0; i < _size; ++i)
            aux[i] = _movs[i];
        _movs = aux;
    }

    public void add(Movie mov) //throws MyRepoException
    {
        //_movs.add(mov);
        if (_size == _cap) resize();
        Integer i = 0;
        while (i < _size && mov.getId() > _movs[i].getId())
        {
            //if (_movs[i].getId() == mov.getId()) throw new MyRepoException("Movie already stored");
            ++i;
        }
        for (Integer j = _size; j > i; --j) _movs[j] = _movs[j - 1];
        ++_size;
        _movs[i] = mov;
    }

    public Movie remove(int pos) throws MyRepoException
    {
        Integer i = 0;
        while (i < _size && pos != _movs[i].getId()) ++i;
        if (i == _size) throw new MyRepoException("Movie is not stored");
        Movie ret = _movs[i];
        for (Integer j = i; j < _size - 1; ++j) _movs[j] = _movs[j + 1];
        --_size;
        return ret;
    }

    public Movie getMovie(Integer id) throws MyRepoException
    {
        Integer i = 0;
        while (i < _size && id != _movs[i].getId()) ++i;
        if (i == _size) throw new MyRepoException("Movie is not stored");
        return _movs[i];
    }

    public Movie[] getMovies()
    {
        return _movs;
    }

    public int getSize()
    {
        return _size;
    }
    */
}
