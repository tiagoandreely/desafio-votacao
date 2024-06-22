package com.db.desafiovotacao.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "associates")
public class Associate
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Campo name é obrigatório")
    private String name;

    @NotBlank(message = "Campo cpf é obrigatório")
    @Column(unique = true)
    private String cpf;

    @CreationTimestamp
    private LocalDateTime created;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Associate()
    {
    }

    public Associate( Integer id, String name, String cpf, LocalDateTime created )
    {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.created = created;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getCpf()
    {
        return cpf;
    }

    public void setCpf(String cpf)
    {
        this.cpf = cpf;
    }

    public LocalDateTime getCreated()
    {
        return created;
    }

    public void setCreated(LocalDateTime created)
    {
        this.created = created;
    }
}
