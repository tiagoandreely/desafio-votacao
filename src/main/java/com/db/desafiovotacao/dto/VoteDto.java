package com.db.desafiovotacao.dto;

import java.time.LocalDateTime;

public class VoteDto
{
    private Integer id;
    private Boolean optionVote;
    private Integer associateId;
    private Integer sessionId;
    private LocalDateTime created;

    public VoteDto()
    {
    }

    public VoteDto( Integer id, Boolean optionVote, Integer associateId, Integer sessionId, LocalDateTime created )
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
