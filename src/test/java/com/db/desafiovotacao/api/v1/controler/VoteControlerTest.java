package com.db.desafiovotacao.api.v1.controler;

import com.db.desafiovotacao.dto.VoteDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName( "Vote Controller Tests" )
public class VoteControlerTest
{
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private VoteDto voteCreateDto;

    private ObjectMapper objectMapper;

    @BeforeEach
    void before()
    {
        objectMapper = new ObjectMapper()
                .registerModule( new JavaTimeModule() )
                .configure( SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false );

        String name = "Associado Teste 01";
        String cpf = "945.027.260-36";
        LocalDateTime created = LocalDateTime.of( 2024, 06, 24,19,00,00 );

        voteCreateDto = new VoteDto( 0, true,  1,1, created);
    }
    @Test
    @SqlGroup({
            @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "/scriptsSQL/insertAssociate.sql"),
            @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "/scriptsSQL/insertVotingAgenda.sql"),
            @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "/scriptsSQL/insertCurrentSession.sql"),
            @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "/scriptsSQL/resetDB.sql")
    })
    @DisplayName("Should Create Vote")
    void shouldCreateVote() throws Exception
    {
        mockMvc.perform(post("/api/v1/vote/create").contentType( MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(voteCreateDto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
    }
}