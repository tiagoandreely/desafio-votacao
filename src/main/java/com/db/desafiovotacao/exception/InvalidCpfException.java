package com.db.desafiovotacao.exception;

public class InvalidCpfException extends RuntimeException
{
    public InvalidCpfException()
    {
        super("CPF inválido");
    }
}
