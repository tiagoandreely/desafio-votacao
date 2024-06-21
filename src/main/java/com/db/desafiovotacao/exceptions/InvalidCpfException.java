package com.db.desafiovotacao.exceptions;

public class InvalidCpfException extends RuntimeException
{
    public InvalidCpfException()
    {
        super("CPF inválido");
    }
}
