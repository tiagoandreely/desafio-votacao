package com.db.desafiovotacao.dto;

public class VoteResultDto
{
    private String title;
    private String description;
    private String result;

    private Long favorVotes;
    private Long againstVotes;

    public VoteResultDto(Long favorVotes, Long againstVotes)
    {
        this.favorVotes = favorVotes;
        this.againstVotes = againstVotes;
    }

    public VoteResultDto( String title, String description, String result, Long favorVotes, Long againstVotes )
    {
        this.title = title;
        this.description = description;
        this.result = result;
        this.favorVotes = favorVotes;
        this.againstVotes = againstVotes;
    }

    public VoteResultDto()
    {
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle( String title )
    {
        this.title = title;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription( String description )
    {
        this.description = description;
    }

    public String getResult()
    {
        return result;
    }

    public void setResult( String result )
    {
        this.result = result;
    }

    public Long getFavorVotes()
    {
        return favorVotes;
    }

    public void setFavorVotes( Long favorVotes )
    {
        this.favorVotes = favorVotes;
    }

    public Long getAgainstVotes()
    {
        return againstVotes;
    }

    public void setAgainstVotes( Long againstVotes )
    {
        this.againstVotes = againstVotes;
    }

    @Override
    public boolean equals( Object o )
    {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;

        VoteResultDto resultDto = (VoteResultDto) o;
        return favorVotes.equals( resultDto.favorVotes ) && againstVotes.equals( resultDto.againstVotes );
    }

    @Override
    public int hashCode()
    {
        int result = favorVotes.hashCode();
        result = 31 * result + againstVotes.hashCode();
        return result;
    }
}
