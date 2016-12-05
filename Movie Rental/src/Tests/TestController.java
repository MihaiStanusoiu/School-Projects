package Tests;

import Domain.MyDomainException;
import Repository.ClientRepository;
import Repository.MovieRepository;
import Repository.MyRepoException;
import Repository.RentRepository;
import Validator.ClientValidator;
import Validator.MovieValidator;
import Validator.RentalValidator;
import Controller.Controller;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Mihai on 10/9/2016.
 */
public class TestController
{

    MovieRepository movRepo;
    ClientRepository clRepo;
    RentRepository rentRepo;
    Controller ctrl;

    @Before
    public void setUp()
    {
        movRepo = new MovieRepository(new MovieValidator());
        clRepo = new ClientRepository(new ClientValidator());
        rentRepo = new RentRepository(new RentalValidator());
        ctrl = new Controller(movRepo, clRepo, rentRepo);
    }

    @Test
    public void testAdd()
    {
        assertEquals((Integer) ctrl.getMoviesNr(), (Integer) 0);
        try
        {
            ctrl.addMovie("BvS", "2016", "7.5");
            ctrl.addMovie("Departed", "2015", "10");
            assertEquals((Integer) ctrl.getMoviesNr(), (Integer) 2);
        }
        catch (MyRepoException ex)
        {
            assertTrue(false);
        }
        catch (MyDomainException ex)
        {
            assertTrue(false);
        }
        try
        {
            ctrl.addMovie("fda", "fad", "-2");
        }
        catch (MyRepoException ex)
        {
            assertTrue(false);
        }
        catch (MyDomainException ex)
        {
            assertTrue(false);
        }
        catch (NumberFormatException ex)
        {
            assertTrue(true);
        }
    }

    @Test
    public void testRemove()
    {
        try
        {
            ctrl.addMovie("BvS", "2016", "7.5");
            ctrl.addMovie("Departed", "2015", "10");
            ctrl.removeMovie("1");
            //assertEquals((Integer) ctrl.getMovies()[0].getId(), (Integer) 2);
        }
        catch (MyRepoException ex)
        {
            assertTrue(false);
        }
        catch (MyDomainException ex)
        {
            assertTrue(false);
        }
        try
        {
            ctrl.removeMovie("3");
        }
        catch (MyRepoException ex)
        {
            assertTrue(true);
        }
        catch (MyDomainException ex)
        {
            assertTrue(false);
        }
        try
        {
            ctrl.removeMovie("dfsa");
        }
        catch (MyRepoException ex)
        {
            assertTrue(false);
        }
        catch (MyDomainException ex)
        {
            assertTrue(false);
        }
        catch (NumberFormatException ex)
        {
            assertTrue(true);
        }
    }

    @Test
    public void testUpdate()
    {
        try
        {
            ctrl.addMovie("BvS", "2016", "7.5");
            ctrl.addMovie("Departed", "2015", "10");
            ctrl.updateMovie("1", "Batman", "2018", "10");
            assertEquals(ctrl.findMovie("1").getTitle(), "Batman");
        }
        catch (MyRepoException ex)
        {
            assertTrue(false);
        }
        catch (MyDomainException ex)
        {
            assertTrue(false);
        }
        try
        {
            ctrl.updateMovie("3", "fda", "1234", "10");
        }
        catch (MyRepoException ex)
        {
            assertTrue(true);
        }
        catch (MyDomainException ex)
        {
            assertTrue(false);
        }
        catch (NumberFormatException ex)
        {
            assertTrue(true);
        }
        try
        {
            ctrl.updateMovie("-2", "fda", "fa", "123");
        }
        catch (MyRepoException ex)
        {
            assertTrue(true);
        }
        catch (MyDomainException ex)
        {
            assertTrue(true);
        }
        catch (NumberFormatException ex)
        {
            assertTrue(true);
        }
    }
}
