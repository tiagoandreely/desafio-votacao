package com.db.desafiovotacao.repository;

import com.db.desafiovotacao.model.Session;
import com.db.desafiovotacao.model.VotingAgenda;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;
import java.util.Optional;

@DataJpaTest
public class SessionRepositoryTest
{
    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private VotingAgendaRepository votingAgendaRepository;

    @Mock
    private Session session;

    @Mock
    private VotingAgenda votingAgenda;


    @BeforeEach
    void before() {
        votingAgenda = votingAgendaRepository.save(new VotingAgenda(1,
                "Pauta Teste 01",
                "Descrição Pauta Teste 01",
                LocalDateTime.now() ));

        session = sessionRepository.save( new Session(1,
                60,
                votingAgenda.getId(),
                LocalDateTime.now()));
    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "/scriptsSQL/resetDB.sql")
    void shouldFindById() {
        Optional<Session> sessionById = sessionRepository.findById(session.getId());

        Assertions.assertNotNull(sessionById);
    }

    @AfterEach
    void after()
    {
        votingAgendaRepository.delete( votingAgenda );

        sessionRepository.delete(session);
    }
}
