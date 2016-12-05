package Domain;

import Repository.AbstractRepository.IDInterface;
import org.apache.http.annotation.Obsolete;


public class Movie implements IDInterface<Integer>, Comparable<Movie>
{
    private Integer _id;
    private String _title;
    private Integer _year;
    private Double _rating;

    public Movie(Integer id, String title, Integer year, Double rating)
    {
        _id = id;
        _title = title;
        _year = year;
        _rating = rating;
    }

    public Movie()
    {
        this(0, "", 0, (double) 0);
    }

    public Integer getId()
    {
        return _id;
    }

    public void setId(Integer id) { _id = id; }

    public String getTitle()
    {
        return _title;
    }

    public void setTitle(String title) { _title = title; }

    public Integer getYear()
    {
        return _year;
    }

    public void setYear(Integer year) { _year = year; }

    public Double getRating()
    {
        return _rating;
    }

    public void setRating(Double rating) { _rating = rating; }

    public String toString()
    {
        return _id + " " + _title + " " + _year + " " + _rating;
    }

    @Override
    public int compareTo(Movie mov) { return _id - mov._id; }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (null == o || getClass() != o.getClass()) return false;

        Movie mov = (Movie)o;

        return _id == mov._id;
    }

    @Override
    public int hashCode()
    {
        int res = _id.hashCode();
        res = res * 31 + _title.hashCode();

        return res;
    }
}
