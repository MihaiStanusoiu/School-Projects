package Domain;

import Repository.AbstractRepository.IDInterface;

/**
 * Created by Mike on 10/25/2016.
 */
public class Rental implements IDInterface<Integer>
{
    private Integer _id;
    private Movie _mov;
    private Client _cl;

    public Rental(Integer _id, Movie _mov, Client _cl) {
        this._id = _id;
        this._mov = _mov;
        this._cl = _cl;
    }

    public Integer getId() {
        return _id;
    }

    public void setId(Integer _id) {
        this._id = _id;
    }

    public Movie getMov() {
        return _mov;
    }

    public void setMov(Movie _mov) {
        this._mov = _mov;
    }

    public Client getCl() {
        return _cl;
    }

    public void setCl(Client _cl) {
        this._cl = _cl;
    }

    public Integer getMovieId()
    {
        return _mov.getId();
    }

    public Integer getClientId()
    {
        return _cl.getId();
    }

    public String getClientName()
    {
        return _cl.getName();
    }

    public String getMovieTitle()
    {
        return _mov.getTitle();
    }

    @Override
    public String toString()
    {
        return _id + "     " + _mov.toString() + "     " + _cl.toString();
    }
}

