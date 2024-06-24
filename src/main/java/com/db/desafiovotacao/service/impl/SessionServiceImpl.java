package com.db.desafiovotacao.service.impl;

import com.db.desafiovotacao.dto.Mapper;
import com.db.desafiovotacao.dto.SessionDto;
import com.db.desafiovotacao.exception.MandatoryFieldException;
import com.db.desafiovotacao.exception.SessionNotFoundException;
import com.db.desafiovotacao.exception.SessionOpenException;
import com.db.desafiovotacao.exception.VotingAgendaNotFoundException;
import com.db.desafiovotacao.model.Session;
import com.db.desafiovotacao.model.VotingAgenda;
import com.db.desafiovotacao.repository.SessionRepository;
import com.db.desafiovotacao.repository.VotingAgendaRepository;
import com.db.desafiovotacao.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SessionServiceImpl implements SessionService
{

    @Autowired
    SessionRepository sessionRepository;

    @Autowired
    VotingAgendaRepository votingAgendaRepository;

    @Override
    public List<SessionDto> findAll()
    {
        List<Session> session = sessionRepository.findAll();

        return Mapper.listToMapSessionDto( session );
    }

    @Override
    public SessionDto findById(Integer sessionId)
    {
        Session session = sessionRepository.findById(sessionId)
                .orElseThrow( SessionNotFoundException::new );

        return Mapper.mapToSessionDto( session );
    }

    @Override
    public SessionDto create(SessionDto sessionDto )
    {
        validateFields( sessionDto );

        List<Session> sessions = sessionRepository.findAllByVotingAgendaId( sessionDto.getVotingAgendaId() );

        sessionRepository.findAllByVotingAgendaId( sessionDto.getVotingAgendaId()).forEach( session -> {
            if( !session.isClosed() )
            {
                throw new SessionOpenException();
            }
        } );

        VotingAgenda agenda = votingAgendaRepository.findById(sessionDto.getVotingAgendaId())
                .orElseThrow( VotingAgendaNotFoundException::new );

        sessionDto.setDuration( sessionDto.getDuration() == null ? Session.DEFAULT_DURATION : sessionDto.getDuration() );

        Session newSession = sessionRepository.save(Mapper.mapToSession( sessionDto ));

        return Mapper.mapToSessionDto( newSession );
    }

    public void validateFields( SessionDto sessionDto )
    {
        if ( sessionDto.getVotingAgendaId() == null )
        {
            throw new MandatoryFieldException( "votingAgendaId" );
        }
    }
}
