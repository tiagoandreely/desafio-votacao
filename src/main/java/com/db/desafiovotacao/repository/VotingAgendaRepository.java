package com.db.desafiovotacao.repository;

import com.db.desafiovotacao.dto.VoteResultDto;
import com.db.desafiovotacao.model.VotingAgenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VotingAgendaRepository extends JpaRepository<VotingAgenda, Integer>
{
    @Query(
            """
                SELECT NEW com.db.desafiovotacao.dto.VoteResultDto( 
                    SUM(CASE WHEN V.optionVote = TRUE THEN 1 ELSE 0 END),
                    SUM(CASE WHEN V.optionVote = FALSE THEN 1 ELSE 0 END)
                ) 
                FROM VotingAgenda VA 
                    JOIN Session S ON S.votingAgendaId = VA.id 
                    JOIN Vote V ON V.sessionId = S.id 
                WHERE 
                    S.created = (SELECT MAX(S2.created) 
                                    FROM Session S2 WHERE S2.votingAgendaId = VA.id) 
                AND 
                    VA.id = :votingAgendaId
            """
    )
    VoteResultDto countVotes( Integer votingAgendaId);
}
