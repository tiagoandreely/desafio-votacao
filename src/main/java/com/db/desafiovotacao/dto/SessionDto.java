package com.db.desafiovotacao.dto;

import java.time.LocalDateTime;

public class SessionDto
{
    private Integer id;

    private Integer duration;

    private Integer votingAgendaId;

    private LocalDateTime create;

    public boolean isClosed()
    {
        LocalDateTime now  = LocalDateTime.now();

        LocalDateTime sessionEnd = this.create.plusSeconds( this.duration );

        return now.isAfter( sessionEnd );
    }

    public SessionDto()
    {
    }

    public SessionDto( Integer id, Integer duration, Integer votingAgendaId, LocalDateTime create )
    {
        this.id = id;
        this.duration = duration;
        this.votingAgendaId = votingAgendaId;
        this.create = create;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId( Integer id )
    {
        this.id = id;
    }

    public Integer getDuration()
    {
        return duration;
    }

    public void setDuration( Integer duration )
    {
        this.duration = duration;
    }

    public Integer getVotingAgendaId()
    {
        return votingAgendaId;
    }

    public void setVotingAgendaId( Integer votingAgendaId )
    {
        this.votingAgendaId = votingAgendaId;
    }

    public LocalDateTime getCreate()
    {
        return create;
    }

    public void setCreate( LocalDateTime create )
    {
        this.create = create;
    }
}
