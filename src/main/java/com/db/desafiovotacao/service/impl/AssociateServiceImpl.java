package com.db.desafiovotacao.service.impl;

import com.db.desafiovotacao.dto.AssociateDto;
import com.db.desafiovotacao.dto.Mapper;
import com.db.desafiovotacao.exception.AssociateAlreadyRegisteredException;
import com.db.desafiovotacao.exception.AssociateNotFoundException;
import com.db.desafiovotacao.exception.InvalidCpfException;
import com.db.desafiovotacao.exception.MandatoryFieldException;
import com.db.desafiovotacao.model.Associate;
import com.db.desafiovotacao.repository.AssociateRepository;
import com.db.desafiovotacao.service.AssociateService;
import com.db.desafiovotacao.util.CPF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssociateServiceImpl implements AssociateService
{

    @Autowired
    AssociateRepository repository;

    @Override
    public List<AssociateDto> findAll()
    {
        List<Associate> associates = repository.findAll();
        List<AssociateDto> associateDtos = Mapper.listMapAssociateToDto( associates );

        return associateDtos;
    }

    public AssociateDto findById(Integer associateId)
    {
        Associate associate = repository.findById(associateId)
                .orElseThrow(() -> {
                    throw new AssociateNotFoundException();
                });

        return Mapper.mapToAssociateDto( associate );

    }

    public AssociateDto create(AssociateDto associateDto)
    {
        validateFields(associateDto);

        repository.findByCpf(associateDto.getCpf()).ifPresent(a -> {
            throw new AssociateAlreadyRegisteredException();
        });

        if(!CPF.validateCPF(associateDto.getCpf()))
        {
            throw new InvalidCpfException();
        }

        Associate newAssociate = repository.save(Mapper.mapToAssociate( associateDto ));

        return Mapper.mapToAssociateDto( newAssociate );
    }

    public void validateFields( AssociateDto associate )
    {
        if ( associate.getCpf() == null || associate.getCpf().isEmpty() )
        {
            throw new MandatoryFieldException( "CPF" );
        }

        if ( associate.getName() == null || associate.getName().isEmpty() )
        {
            throw new MandatoryFieldException( "nome" );
        }
    }
}
