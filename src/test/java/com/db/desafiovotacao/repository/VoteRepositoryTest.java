package com.db.desafiovotacao.repository;

import com.db.desafiovotacao.dto.VoteResultDto;
import com.db.desafiovotacao.model.Associate;
import com.db.desafiovotacao.model.Session;
import com.db.desafiovotacao.model.Vote;
import com.db.desafiovotacao.model.VotingAgenda;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.Optional;


@DataJpaTest
public class VoteRepositoryTest
{
    @Autowired
    private VotingAgendaRepository votingAgendaRepository;
    @Autowired
    private AssociateRepository associateRepository;
    @Autowired
    private SessionRepository sessionRepository;
    @Autowired
    private VoteRepository voteRepository;

    @Mock
    private VotingAgenda votingAgenda;
    @Mock
    private Associate associate;
    @Mock
    private Session session;
    @Mock
    private Vote vote;

    @BeforeEach
    void before() {
        votingAgenda = votingAgendaRepository.save(new VotingAgenda(1,
                                                    "Pauta Teste 01",
                                                    "Descrição Pauta Teste 01",
                                                    LocalDateTime.now() ));

        associate = associateRepository.save( new Associate(1,
                                                    "Associado Teste 01",
                                                    "945.027.260-36",
                                                    LocalDateTime.now()));

        session = sessionRepository.save( new Session(1,
                                                    60,
                                                    votingAgenda.getId(),
                                                    LocalDateTime.now()));

        vote = voteRepository.save(new Vote(1, true, associate.getId(), session.getId(), LocalDateTime.now()));
    }

    @Test
    void shouldFindById() {
        Optional<Vote> voteById = voteRepository.findById( vote.getId());

        Assertions.assertNotNull(voteById);
    }

    @Test
    void shouldFindByAssociateIdAndSessionId() {
        Optional<Vote> vote = voteRepository.findByAssociateIdAndSessionId(associate.getId(), session.getId());

        Assertions.assertNotNull(vote);
    }

    @AfterEach
    void after()
    {
        votingAgendaRepository.delete( votingAgenda );

        associateRepository.delete(associate);

        sessionRepository.delete(session);

        voteRepository.delete( vote );
    }
}
