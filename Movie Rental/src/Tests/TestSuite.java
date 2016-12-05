package Tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Created by Mihai on 10/9/2016.
 */

@RunWith(Suite.class)
@SuiteClasses(
        {
                TestDomain.class,
                TestMovieRepo.class,
                TestController.class
        }
)public class TestSuite
{
}
