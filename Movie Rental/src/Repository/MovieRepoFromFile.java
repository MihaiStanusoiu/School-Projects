package Repository;

import Domain.Movie;
import Domain.MyDomainException;
import Repository.AbstractRepository.AbstractCrudRepository;
import Repository.AbstractRepository.Repository;
import Validator.IValidator;

import java.io.*;
import java.util.StringTokenizer;

/**
 * Created by Mihai on 11/2/2016.
 */
public class MovieRepoFromFile extends MovieRepository
{
    private String _fileName;

    public MovieRepoFromFile(IValidator<Movie> val, String _fileName)
    {
        super(val);
        this._fileName = _fileName;
        try
        {
            loadData();
        }
        catch (FileNotFoundException ex)
        {
            ex.printStackTrace();
        }
    }

    @Override
    public void loadData() throws FileNotFoundException
    {
        try
        {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(_fileName)));
            String line = reader.readLine();
            while (line != null)
            {
                if (line == null) break;
                StringTokenizer st = new StringTokenizer(line, "|");
                Movie mov = new Movie(Integer.parseInt(st.nextToken()), st.nextToken(),
                        Integer.parseInt(st.nextToken()), Double.parseDouble(st.nextToken()));
                try {
                    store(mov);
                }
                catch (MyDomainException ex) {
                    System.out.println();
                    System.out.println(ex);
                    System.out.println();
                }
                line = reader.readLine();
            }
            reader.close();
        }
        catch (FileNotFoundException ex)
        {
            ex.printStackTrace();
        }
        //catch (MyDomainException ex) { }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }

    @Override
    public void saveData() throws FileNotFoundException
    {
        try
        {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(_fileName)));
            String line;
            for (Movie mov : items)
            {
                line = mov.getId().toString() + "|" + mov.getTitle() + "|" + mov.getYear().toString() + "|" + mov.getRating().toString() + "\n";
                writer.write(line);
            }
            writer.close();
        } catch (FileNotFoundException ex)
        {
            ex.printStackTrace();
        } catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }

    @Override
    public void store(Movie el) throws MyDomainException
    {
        try
        {
            super.store(el);
            saveData();
        }
        catch (FileNotFoundException ex)
        {
            ex.printStackTrace();
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }

    @Override
    public Movie remove(Integer integer) throws MyDomainException, MyRepoException
    {
        try
        {
            Movie mov = super.remove(integer);
            saveData();
            return mov;
        }
        catch (FileNotFoundException ex)
        {
            ex.printStackTrace();
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public void clear()
    {
        try
        {
            super.clear();
            saveData();
        }
        catch (FileNotFoundException ex)
        {
            ex.printStackTrace();
        }
    }
}
