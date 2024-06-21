package com.db.desafiovotacao.service;

import com.db.desafiovotacao.exceptions.AssociateAlreadyRegisteredException;
import com.db.desafiovotacao.exceptions.AssociateNotFoundException;
import com.db.desafiovotacao.exceptions.InvalidCpfException;
import com.db.desafiovotacao.exceptions.MandatoryFieldException;
import com.db.desafiovotacao.model.Associate;
import com.db.desafiovotacao.repository.AssociateRepository;
import com.db.desafiovotacao.util.CPF;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class AssociateService
{
    private Logger logger = Logger.getLogger(Associate.class.getName());

    @Autowired
    AssociateRepository repository;

    public List<Associate> findAll()
    {
        logger.info("Obtendo todos associados");

        return repository.findAll();
    }

    public Associate findById(Integer associateId)
    {
        Associate associate = repository.findById(associateId)
                                                      .orElseThrow(() -> {
                                                          throw new AssociateNotFoundException();
                                                      });

        return associate;
    }

    public Associate create(Associate associate)
    {
        validateFields(associate);

        repository.findByCpf(associate.getCpf()).ifPresent(a -> {
            throw new AssociateAlreadyRegisteredException();
        });

        if(!CPF.validateCPF(associate.getCpf()))
        {
            throw new InvalidCpfException();
        }

        return repository.save(associate);
    }

    public void validateFields( Associate associate )
    {
        if( associate.getCpf() == null || associate.getCpf().isEmpty() )
        {
            throw new MandatoryFieldException( "CPF");
        }

        if( associate.getName() == null || associate.getName().isEmpty() )
        {
            throw new MandatoryFieldException( "nome");
        }
    }
}
