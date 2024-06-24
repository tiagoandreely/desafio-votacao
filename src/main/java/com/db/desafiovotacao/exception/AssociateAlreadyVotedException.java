package com.db.desafiovotacao.exception;

public class AssociateAlreadyVotedException extends RuntimeException
{
    public AssociateAlreadyVotedException()
    {
        super("Associado já realizou seu voto!");
    }
}
