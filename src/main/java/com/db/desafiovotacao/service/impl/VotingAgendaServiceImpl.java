package com.db.desafiovotacao.service.impl;


import com.db.desafiovotacao.dto.Mapper;
import com.db.desafiovotacao.dto.VoteResultDto;
import com.db.desafiovotacao.dto.VotingAgendaDto;
import com.db.desafiovotacao.exception.MandatoryFieldException;
import com.db.desafiovotacao.exception.VotingAgendaNotFoundException;
import com.db.desafiovotacao.model.VotingAgenda;
import com.db.desafiovotacao.repository.VotingAgendaRepository;
import com.db.desafiovotacao.service.VotingAgendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VotingAgendaServiceImpl implements VotingAgendaService
{

    @Autowired
    VotingAgendaRepository repository;

    @Override
    public List<VotingAgendaDto> findAll()
    {
        List<VotingAgenda> agendas = repository.findAll();
        List<VotingAgendaDto> agendasDto = Mapper.listToMapToVotingAgendaDto( agendas );

        return agendasDto;
    }

    public VotingAgendaDto findById(Integer agendaId)
    {
        VotingAgenda agenda = repository.findById(agendaId)
                .orElseThrow(() -> {
                    throw new VotingAgendaNotFoundException();
                });

        return Mapper.mapToVotingAgendaDto( agenda );

    }

    public VotingAgendaDto create(VotingAgendaDto votingAgenda )
    {
        validateFields( votingAgenda );

        VotingAgenda newVotingAgenda = repository.save(Mapper.mapToVotingAgenda( votingAgenda ));

        return Mapper.mapToVotingAgendaDto( newVotingAgenda );
    }

    @Override
    public VoteResultDto countVotes( Integer rulingId )
    {
        VotingAgenda votingAgendaDto = repository.findById( rulingId ).orElseThrow( VotingAgendaNotFoundException::new );

        VoteResultDto resultDto = repository.countVotes( rulingId );

        resultDto.setTitle( votingAgendaDto.getTitle() );
        resultDto.setDescription( votingAgendaDto.getDescription() );
        resultDto.setResult( resultDto.getFavorVotes() == resultDto.getAgainstVotes() ? "Empate" : (resultDto.getFavorVotes() > resultDto.getAgainstVotes() ? "Aprovada" : "Desaprovada") );

        return resultDto;
    }

    public void validateFields( VotingAgendaDto agenda )
    {
        if ( agenda.getTitle() == null || agenda.getTitle().isEmpty() )
        {
            throw new MandatoryFieldException( "título" );
        }
    }

}
