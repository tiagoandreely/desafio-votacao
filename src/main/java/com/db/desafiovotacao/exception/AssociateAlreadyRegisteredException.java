package com.db.desafiovotacao.exception;

public class AssociateAlreadyRegisteredException extends RuntimeException
{
    public AssociateAlreadyRegisteredException()
    {
        super("Associado já cadastrado!");
    }
}
