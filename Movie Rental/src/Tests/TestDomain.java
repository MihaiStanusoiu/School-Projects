package Tests;

import Domain.Client;
import Domain.Movie;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Mihai on 10/9/2016.
 */
public class TestDomain
{
    protected Movie mov;
    protected Client cl;

    @Before
    public void setUp()
    {
        mov = new Movie(1, "BvS", 2016, 7.5);
        cl = new Client(2, "Mihdsaai", "1234");
    }

    @Test
    public void testGetters()
    {
        //Movie mov  = new Movie(1, "BvS", 2016, 7.5);
        //Client cl = new Client(2, "Mihai", "1234");
        assertEquals(mov.getTitle(), "BvS");
        assertTrue(cl.getId() == 2);
    }

    @Test
    public void testSetters()
    {
        //Movie mov  = new Movie(1, "BvS", 2016, 7.5);
        //Client cl = new Client(2, "Mihai", "1234");
        mov.setRating(8.0);
        cl.setCnp("123");
        assertEquals((Double) mov.getRating(), (Double) 8.0);
        assertTrue(cl.getCnp() == "123");
    }
}
