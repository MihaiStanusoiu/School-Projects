package Domain;

import Repository.AbstractRepository.IDInterface;

/**
 * Created by Mihai on 12/5/2016.
 */
public class RentalDto implements IDInterface<Integer>
{
    private Integer id;
    private Integer movId;
    private Integer clId;

    public RentalDto(Integer id, Integer movId, Integer clId)
    {
        this.id = id;
        this.movId = movId;
        this.clId = clId;
    }

    public Integer getId()
    {
        return this.id;
    }

    @Override
    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getMovieId()
    {
        return this.movId;
    }

    public Integer getClientId()
    {
        return this.clId;
    }
}
