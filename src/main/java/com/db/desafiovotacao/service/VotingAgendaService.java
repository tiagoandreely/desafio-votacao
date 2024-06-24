package com.db.desafiovotacao.service;

import com.db.desafiovotacao.dto.VoteResultDto;
import com.db.desafiovotacao.dto.VotingAgendaDto;

import java.util.List;

public interface VotingAgendaService
{
    List<VotingAgendaDto> findAll();
    VotingAgendaDto findById(Integer votingAgendaId);
    VotingAgendaDto create(VotingAgendaDto votingAgenda );
    VoteResultDto countVotes( Integer rulingId);
}
