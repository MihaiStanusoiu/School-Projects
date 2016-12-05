package Tests;

import Domain.Movie;
import Domain.MyDomainException;
import Repository.MovieRepository;
import Validator.MovieValidator;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Mihai on 10/9/2016.
 */
public class TestMovieRepo
{
    MovieValidator val;
    MovieRepository repo;

    @Before
    public void setUp()
    {
        val = new MovieValidator();
        repo = new MovieRepository(val);
    }

    @Test
    public void testStore()
    {
        try
        {
            assertEquals((Integer) repo.size(), (Integer) 0);
            repo.store(new Movie(1, "BvS", 2016, 7.5));
            assertEquals((Integer) repo.size(), (Integer) 1);
            assertEquals((Integer) repo.getItem(1).getId(), (Integer) 1);
        }
        catch (Exception ex)
        {
            assertTrue(false);
        }
        try
        {
            repo.store(new Movie(1, "dfa", 12, (Double)11.00));
        }
        catch (MyDomainException ex)
        {
            assertTrue(true);
        }
    }

    @Test
    public void testGet()
    {
        //repo.clear();
        try
        {
            repo.getItem(1);
            assertTrue(false);
        }
        catch (Exception ex)
        {
            assertTrue(true);
        }
    }

    @Test
    public void testRemove()
    {
        //repo.clear();
        try
        {
            repo.remove(1);
            assertTrue(false);
        } catch (Exception ex)
        {
            assertTrue(true);
        }
        try
        {
            repo.store(new Movie(1, "BvS", 2016, 7.0));
            repo.store(new Movie(2, "abc", 2016, 7.0));
            repo.store(new Movie(3, "fad", 2016, 7.0));
            assertEquals((Integer) repo.remove(2).getId(), (Integer) 2);
            assertEquals((Integer) repo.size(), (Integer) 2);
        } catch (Exception ex)
        {
            assertTrue(false);
        }
    }
}
