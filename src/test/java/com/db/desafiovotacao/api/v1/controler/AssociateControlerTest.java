package com.db.desafiovotacao.api.v1.controler;

import com.db.desafiovotacao.dto.AssociateDto;
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
@DisplayName( "Associate Controller Tests" )
public class AssociateControlerTest
{
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private AssociateDto associateCreateDto;

    @Mock
    private AssociateDto associateResponseDto;

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

        associateCreateDto = new AssociateDto( 0, name,  cpf, created);

        associateResponseDto = new AssociateDto( 1, name,  cpf, created);
    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "/scriptsSQL/resetDB.sql")
    @DisplayName("Should Create Associate")
    void shouldCreateAssociate()
    {
        try
        {
            mockMvc.perform( post( "/api/v1/associate/create" ).contentType( MediaType.APPLICATION_JSON_VALUE )
                            .content( objectMapper.writeValueAsString( associateCreateDto ) ) )
                    .andExpect( status().isCreated() )
                    .andExpect( content().contentType( MediaType.APPLICATION_JSON_VALUE ) );
        }
        catch ( Exception e )
        {
            throw new RuntimeException( e );
        }
    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "/scriptsSQL/resetDB.sql")
    @DisplayName("Should Not Create Associate")
    void shouldNotCreateAssociate() throws Exception
    {
        associateCreateDto.setCpf("000.000.000-00");

        mockMvc.perform(post("/api/v1/associate/create").contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(associateCreateDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @SqlGroup({
            @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "/scriptsSQL/insertAssociate.sql"),
            @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "/scriptsSQL/resetDB.sql")
    })
    @DisplayName("Should Find All")
    void shouldFindAll() throws Exception
    {
        mockMvc.perform(get("/api/v1/associate/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().json(objectMapper.writeValueAsString( List.of( associateResponseDto ))));

    }

    @Test
    @SqlGroup({
            @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "/scriptsSQL/insertAssociate.sql"),
            @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "/scriptsSQL/resetDB.sql")
    })
    @DisplayName("Should Find By ID")
    void shouldFindById() throws Exception
    {
        mockMvc.perform(get("/api/v1/associate/{associatedId}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().json(objectMapper.writeValueAsString(  associateResponseDto )));
    }
}