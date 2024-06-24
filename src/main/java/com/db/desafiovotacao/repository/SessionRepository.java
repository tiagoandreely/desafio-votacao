package com.db.desafiovotacao.repository;

import com.db.desafiovotacao.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessionRepository extends JpaRepository<Session, Integer>
{
    List<Session> findAllByVotingAgendaId( Integer votingAgendaId );
}
