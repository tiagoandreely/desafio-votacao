package com.db.desafiovotacao.dto;

import java.time.LocalDateTime;

public class VotingAgendaDto
{
    private Integer id;
    private String title;
    private String description;
    private LocalDateTime created;

    public VotingAgendaDto()
    {
    }

    public VotingAgendaDto( Integer id, String title, String description, LocalDateTime created )
    {
        this.id = id;
        this.title = title;
        this.description = description;
        this.created = created;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId( Integer id )
    {
        this.id = id;
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

    public LocalDateTime getCreated()
    {
        return created;
    }

    public void setCreated( LocalDateTime created )
    {
        this.created = created;
    }
}
