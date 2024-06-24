package com.db.desafiovotacao.repository;

import com.db.desafiovotacao.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Integer>
{
    Optional<Vote> findByAssociateIdAndSessionId( Integer associateId, Integer sessionId);
}
