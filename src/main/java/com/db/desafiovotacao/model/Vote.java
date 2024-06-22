package com.db.desafiovotacao.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "votes")
public class Vote
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Boolean optionVote;

    @ManyToOne
    @JoinColumn(name = "associate_id", insertable = false, updatable = false)
    private Associate associate;

    @Column(name = "associate_id", nullable = false)
    private Integer associateId;

    @ManyToOne
    @JoinColumn(name = "session_id", insertable = false, updatable = false)
    private Session session;

    @Column(name = "session_id", nullable = false)
    private Integer sessionId;

    @CreationTimestamp
    private LocalDateTime created;

    public Vote()
    {
    }

    public Vote( Integer id, Boolean optionVote, Integer associateId, Integer sessionId, LocalDateTime created )
    {
        this.id = id;
        this.optionVote = optionVote;
        this.associateId = associateId;
        this.sessionId = sessionId;
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

    public Boolean getOptionVote()
    {
        return optionVote;
    }

    public void setOptionVote( Boolean optionVote )
    {
        this.optionVote = optionVote;
    }

    public Integer getAssociateId()
    {
        return associateId;
    }

    public void setAssociateId( Integer associateId )
    {
        this.associateId = associateId;
    }

    public Integer getSessionId()
    {
        return sessionId;
    }

    public void setSessionId( Integer sessionId )
    {
        this.sessionId = sessionId;
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
