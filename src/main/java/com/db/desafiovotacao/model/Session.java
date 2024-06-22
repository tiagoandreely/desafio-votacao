package com.db.desafiovotacao.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "sessions")
public class Session
{
    public static Integer DEFAULT_DURATION = 60;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer duration;

    @ManyToOne()
    @JoinColumn(name = "voting_agenda_id", insertable = false, updatable = false)
    private VotingAgenda votingAgenda;

    @Column(name = "voting_agenda_id", nullable = false)
    private Integer votingAgendaId;

    private LocalDateTime created;

    public boolean isClosed()
    {
        LocalDateTime now  = LocalDateTime.now();

        LocalDateTime sessionEnd = this.created.plusSeconds( this.duration );

        return now.isAfter( sessionEnd );
    }

    public Session()
    {
    }

    public Session( Integer id, Integer duration, Integer votingAgendaId, LocalDateTime create )
    {
        this.id = id;
        this.duration = duration;
        this.votingAgendaId = votingAgendaId;
        this.created = create;
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

    public LocalDateTime getCreated()
    {
        return created;
    }

    public void setCreated( LocalDateTime created )
    {
        this.created = created;
    }
}
