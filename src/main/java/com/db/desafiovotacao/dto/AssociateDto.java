package com.db.desafiovotacao.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

public class AssociateDto implements Serializable
{
    private Integer id;

    private String name;

    private String cpf;

    private LocalDateTime created;

    public AssociateDto( Integer id, String name, String cpf, LocalDateTime created )
    {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
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

    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public String getCpf()
    {
        return cpf;
    }

    public void setCpf( String cpf )
    {
        this.cpf = cpf;
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
