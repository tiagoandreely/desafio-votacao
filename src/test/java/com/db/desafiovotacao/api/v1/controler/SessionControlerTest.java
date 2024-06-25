package com.db.desafiovotacao.api.v1.controler;

import com.db.desafiovotacao.dto.SessionDto;
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
@DisplayName( "Session Controller Tests" )
public class SessionControlerTest
{
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private SessionDto sessionCreateDto;

    @Mock
    private SessionDto sessionResponseDto;

    private ObjectMapper objectMapper;

    @BeforeEach
    void before()
    {
        objectMapper = new ObjectMapper()
                .registerModule( new JavaTimeModule() )
                .configure( SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false );

        LocalDateTime created = LocalDateTime.of(2024, 06, 24, 19, 00, 00);

        sessionCreateDto =  new SessionDto( 0, 60, 1, created);

        sessionResponseDto = new SessionDto( 1, 60, 1, created);
    }

    @Test
    @SqlGroup({
            @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "/scriptsSQL/insertVotingAgenda.sql" ),
            @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "/scriptsSQL/resetDB.sql")
    })
    @DisplayName("Should Open Session")
    void shouldOpenSession() throws Exception
    {
        mockMvc.perform(post("/api/v1/session/create").contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(sessionCreateDto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
    }

    @Test
    @SqlGroup({
            @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "/scriptsSQL/insertVotingAgenda.sql"),
            @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "/scriptsSQL/insertSession.sql"),
            @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "/scriptsSQL/resetDB.sql")
    })
    @DisplayName("Should Find By Id")
    void shouldFindById() throws Exception
    {
        mockMvc.perform(get("/api/v1/session/{sessionId}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().json(objectMapper.writeValueAsString(sessionResponseDto)));
    }

    @Test
    @SqlGroup({
            @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "/scriptsSQL/insertVotingAgenda.sql"),
            @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "/scriptsSQL/insertSession.sql"),
            @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "/scriptsSQL/resetDB.sql")
    })
    @DisplayName("Should Find All")
    void shouldFindAll() throws Exception
    {
        mockMvc.perform(get("/api/v1/session/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().json(objectMapper.writeValueAsString( List.of(sessionResponseDto))));
    }

    @Test
    @DisplayName("Should Not Find By Id")
    void shouldNotFindById() throws Exception
    {
        mockMvc.perform(get("/api/v1/session/{sessionId}", 2))
                .andExpect(status().isBadRequest());
    }

}