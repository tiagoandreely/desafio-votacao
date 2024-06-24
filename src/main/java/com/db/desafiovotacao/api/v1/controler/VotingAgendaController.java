package com.db.desafiovotacao.api.v1.controler;

import com.db.desafiovotacao.dto.VoteResultDto;
import com.db.desafiovotacao.dto.VotingAgendaDto;
import com.db.desafiovotacao.exception.VotingAgendaNotFoundException;
import com.db.desafiovotacao.service.VotingAgendaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/votingagenda")
@Tag(name = "Pautas", description = "Informações das Pautas")
public class VotingAgendaController
{
    @Autowired
    VotingAgendaService service;

    @GetMapping( "/" )
    @Operation(
            summary = "Listar todas as pautas",
            description = "Realiza a busca de todas as pautas"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = List.class))
            }),
    })
    public ResponseEntity<Object> findAll()
    {
        try
        {
            List<VotingAgendaDto> votingAgendaDtos = service.findAll();

            return ResponseEntity.ok( votingAgendaDtos );
        }
        catch ( VotingAgendaNotFoundException exception )
        {
            return ResponseEntity.status( HttpStatus.BAD_REQUEST ).body( exception.getMessage() );
        }
    }

    @GetMapping( value = "/{votingAgendaId}" )
    @Operation(
            summary = "Buscar pauta por código",
            description = "Realiza a busca de uma pauta pelo código"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = VotingAgendaDto.class))
            }),
            @ApiResponse(responseCode = "400", description = "Ruling not found")
    })
    public ResponseEntity<Object> findById( @PathVariable( value = "votingAgendaId" ) Integer votingAgendaId )
    {
        try
        {
            VotingAgendaDto votingAgenda = service.findById( votingAgendaId );

            return ResponseEntity.ok( votingAgenda );
        }
        catch ( Exception exception )
        {
            return ResponseEntity.status( HttpStatus.BAD_REQUEST ).body( exception.getMessage() );
        }
    }

    @PostMapping( "/create" )
    @Operation(
            summary = "Cadastrar uma nova pauta",
            description = "Realiza o cadastro de uma nova pauta para votação"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = {
                    @Content(schema = @Schema(implementation = VotingAgendaDto.class))
            })
    })
    public ResponseEntity<Object> create( @Valid @RequestBody VotingAgendaDto votingAgendaDto )
    {
        try
        {
            VotingAgendaDto newVotingAgenda = service.create( votingAgendaDto );

            return ResponseEntity.status( HttpStatus.CREATED ).body( newVotingAgenda );
        }
        catch ( Exception exception )
        {
            return ResponseEntity.status( HttpStatus.BAD_REQUEST ).body( exception.getMessage() );
        }
    }

    @GetMapping( value = "/count/votes/{votingAgendaId}" )
    @Operation(
            summary = "Realiza a contagem dos votos pelo código da pauta",
            description = "Realiza a contagem dos votos da sessão de uma pauta"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = VoteResultDto.class))
            }),
    })
    public ResponseEntity<Object> countVotes( @PathVariable( value = "votingAgendaId" ) Integer votingAgendaId )
    {
        try
        {
            VoteResultDto resultDto = service.countVotes( votingAgendaId );

            return ResponseEntity.ok( resultDto );
        }
        catch ( Exception exception )
        {
            return ResponseEntity.status( HttpStatus.BAD_REQUEST ).body( exception.getMessage() );
        }
    }
}
