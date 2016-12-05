package Domain;

import Repository.AbstractRepository.IDInterface;

import java.io.Serializable;

public class Client implements IDInterface<Integer>, Serializable
{
    private Integer _id;
    private String _name;
    private String _cnp;

    public Client(Integer _id, String _name, String _cnp)
    {
        this._id = _id;
        this._name = _name;
        this._cnp = _cnp;
    }

    public Client()
    {
        this(0, "", "");
    }

    public String getCnp()
    {
        return _cnp;
    }

    public void setCnp(String _CNP)
    {
        this._cnp = _CNP;
    }

    public Integer getId()
    {
        return _id;
    }

    public void setId(Integer _id)
    {
        this._id = _id;
    }

    public String getName()
    {
        return _name;
    }

    public void setName(String _name)
    {
        this._name = _name;
    }

    public String toString()
    {
        return _id + " " + _name + " " + _cnp;
    }

}
