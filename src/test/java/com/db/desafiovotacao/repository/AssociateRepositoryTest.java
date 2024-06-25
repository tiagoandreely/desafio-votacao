package com.db.desafiovotacao.repository;

import com.db.desafiovotacao.model.Associate;
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
public class AssociateRepositoryTest
{
    @Autowired
    private AssociateRepository associateRepository;

    @Mock
    private Associate associate;

    @BeforeEach
    void before()
    {
        associate = associateRepository.save( new Associate(1,"Associate Test","945.027.260-36", LocalDateTime.now() ) );
    }

    @AfterEach
    void after()
    {
        associateRepository.delete( associate );
    }

    @Test
    void shouldFindById()
    {
        Optional<Associate> associateById = associateRepository.findById( associate.getId() );

        Assertions.assertNotNull( associateById );
    }

    @Test
    void shouldFindByCpf()
    {
        Optional<Associate> associateById = associateRepository.findByCpf(associate.getCpf());

        Assertions.assertNotNull(associateById);
    }
}
