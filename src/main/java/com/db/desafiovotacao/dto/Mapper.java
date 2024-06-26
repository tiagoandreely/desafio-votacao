package com.db.desafiovotacao.dto;

import com.db.desafiovotacao.model.Associate;
import com.db.desafiovotacao.model.Session;
import com.db.desafiovotacao.model.Vote;
import com.db.desafiovotacao.model.VotingAgenda;

import java.util.ArrayList;
import java.util.List;

public class Mapper
{
    public static Associate mapToAssociate(AssociateDto associateDto)
    {
        Associate associate = new Associate(
                associateDto.getId(),
                associateDto.getName(),
                associateDto.getCpf(),
                associateDto.getCreated()
        );

        return associate;
    }

    public static AssociateDto mapToAssociateDto(Associate associate)
    {
        AssociateDto associateDto = new AssociateDto(
                associate.getId(),
                associate.getName(),
                associate.getCpf(),
                associate.getCreated()
        );

        return associateDto;
    }

    public static List<AssociateDto> listMapAssociateToDto( List<Associate> associates )
    {
        List<AssociateDto> associateDtos = new ArrayList<>();

        for( Associate a : associates )
        {
            associateDtos.add( mapToAssociateDto( a ) );
        }

        return associateDtos;
    }

    public static SessionDto mapToSessionDto( Session session )
    {
        SessionDto sessionDto = new SessionDto(
                session.getId(),
                session.getDuration(),
                session.getVotingAgendaId(),
                session.getCreated()
        );

        return sessionDto;
    }

    public static Session mapToSession( SessionDto sessionDto )
    {
        Session session = new Session(
                sessionDto.getId(),
                sessionDto.getDuration(),
                sessionDto.getVotingAgendaId(),
                sessionDto.getCreate()
        );

        return session;
    }

    public static List<Session> listToMapSession( List<SessionDto> sessionDtos )
    {
        List<Session> sessions = new ArrayList<>();

        for( SessionDto s : sessionDtos )
        {
            sessions.add( mapToSession( s ) );
        }

        return sessions;
    }

    public static List<SessionDto> listToMapSessionDto( List<Session> sessions )
    {
        List<SessionDto> sessionsDto = new ArrayList<>();

        for( Session s : sessions )
        {
            sessionsDto.add( mapToSessionDto( s ) );
        }

        return sessionsDto;
    }

    public static VoteDto mapToVoteDto( Vote vote)
    {
        VoteDto voteDto = new VoteDto(
                vote.getId(),
                vote.getOptionVote(),
                vote.getAssociateId(),
                vote.getSessionId(),
                vote.getCreated()
        );

        return voteDto;
    }

    public static Vote mapToVote( VoteDto voteDto)
    {
        Vote vote = new Vote(
                voteDto.getId(),
                voteDto.getOptionVote(),
                voteDto.getAssociateId(),
                voteDto.getSessionId(),
                voteDto.getCreated()
        );

        return vote;
    }

    public static VotingAgenda mapToVotingAgenda( VotingAgendaDto votingAgendaDto )
    {
        VotingAgenda votingAgenda = new VotingAgenda(
                votingAgendaDto.getId(),
                votingAgendaDto.getTitle(),
                votingAgendaDto.getDescription(),
                votingAgendaDto.getCreated()
        );

        return votingAgenda;
    }

    public static VotingAgendaDto mapToVotingAgendaDto( VotingAgenda votingAgenda )
    {
        VotingAgendaDto votingAgendaDto = new VotingAgendaDto(
                votingAgenda.getId(),
                votingAgenda.getTitle(),
                votingAgenda.getDescription(),
                votingAgenda.getCreated()
        );

        return votingAgendaDto;
    }

    public static List<VotingAgendaDto> listToMapToVotingAgendaDto( List<VotingAgenda> votingAgendas )
    {
        List<VotingAgendaDto> agendasDto = new ArrayList<>();

        for( VotingAgenda a : votingAgendas )
        {
            agendasDto.add( mapToVotingAgendaDto( a ) );
        }

        return agendasDto;
    }

    public static List<VotingAgenda> listToMapToVotingAgenda( List<VotingAgendaDto> votingAgendasDto )
    {
        List<VotingAgenda> agendas = new ArrayList<>();

        for( VotingAgendaDto a : votingAgendasDto )
        {
            agendas.add( mapToVotingAgenda( a ) );
        }

        return agendas;
    }
}
