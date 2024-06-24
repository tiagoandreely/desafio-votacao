package com.db.desafiovotacao.exception;

public class VotingAgendaNotFoundException extends RuntimeException
{
    public VotingAgendaNotFoundException()
    {
        super("Pauta não encontrada!");
    }
}
