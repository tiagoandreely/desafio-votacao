package com.db.desafiovotacao.exceptions;

public class AssociateAlreadyRegisteredException extends RuntimeException
{
    public AssociateAlreadyRegisteredException()
    {
        super("Associado já cadastrado!");
    }
}
