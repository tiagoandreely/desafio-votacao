package com.db.desafiovotacao.api.v1.controler;

import com.db.desafiovotacao.dto.VoteResultDto;
import com.db.desafiovotacao.dto.VotingAgendaDto;
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
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName( "Voting Agenda Controller Tests" )
public class VotingAgendaControlerTest
{
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private VotingAgendaDto votingAgendaCreateDto;

    @Mock
    private VotingAgendaDto votingAgendaResponseDto;

    @Mock
    private VoteResultDto voteResultDto;

    private ObjectMapper objectMapper;

    @BeforeEach
    void before()
    {
        objectMapper = new ObjectMapper()
                .registerModule( new JavaTimeModule() )
                .configure( SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false );

        String title = "Pauta Teste 01";
        String description = "Descrição Pauta Teste 01";
        LocalDateTime created = LocalDateTime.of( 2024, 06, 24,19,00,00 );

        votingAgendaCreateDto = new VotingAgendaDto(0, title, description, created);
        votingAgendaResponseDto = new VotingAgendaDto(1, title, description, created);

        voteResultDto = new VoteResultDto( title, description, "Aprovada", 1L, 0L );
    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "/scriptsSQL/resetDB.sql")
    @DisplayName("Should Create a Voting Agenda")
    void shouldCreateRuling() throws Exception
    {
        mockMvc.perform(post("/api/v1/votingagenda/create").contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(votingAgendaCreateDto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
    }

    @Test
    @SqlGroup({
            @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "/scriptsSQL/insertVotingAgenda.sql"),
            @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "/scriptsSQL/resetDB.sql")
    })
    @DisplayName("Should Find By Id")
    void shouldFindById() throws Exception
    {
        mockMvc.perform(get("/api/v1/votingagenda/{votingAgendaId}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().json(objectMapper.writeValueAsString(votingAgendaResponseDto)));
    }


    @Test
    @SqlGroup({
            @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "/scriptsSQL/insertAssociate.sql"),
            @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "/scriptsSQL/insertVotingAgenda.sql"),
            @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "/scriptsSQL/insertSession.sql"),
            @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "/scriptsSQL/insertVote.sql"),
            @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "/scriptsSQL/resetDB.sql")
    })
    @DisplayName("Should Count Votes")
    void shouldCountVotes() throws Exception
    {
        mockMvc.perform(get("/api/v1/votingagenda/count/votes/{votingAgendaId}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().json(objectMapper.writeValueAsString(voteResultDto)));
    }

}