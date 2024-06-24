package com.db.desafiovotacao.api.v1.controler;


import com.db.desafiovotacao.dto.SessionDto;
import com.db.desafiovotacao.exception.SessionNotFoundException;
import com.db.desafiovotacao.service.SessionService;
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
@RequestMapping("/api/v1/session")
@Tag(name = "Sessões", description = "Informações das Sessões")
public class SessionController
{
    @Autowired
    SessionService service;

    @GetMapping("/")
    @Operation(
            summary = "Listar todas as sessões",
            description = "Realiza a busca de todas as sessões"
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
            List<SessionDto> votingAgendaDtos = service.findAll();

            return ResponseEntity.ok(votingAgendaDtos);
        }
        catch ( SessionNotFoundException exception)
        {
            return ResponseEntity.status( HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }
    }

    @GetMapping(value = "/{sessionId}")
    @Operation(
            summary = "Buscar sessão por código",
            description = "Realiza a busca de uma sessão pelo código"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = SessionDto.class))
            }),
            @ApiResponse(responseCode = "400", description = "Session not found")
    })
    public ResponseEntity<Object> findById( @PathVariable( value = "sessionId" ) Integer sessionId)
    {
        try
        {
            SessionDto sessionDto = service.findById(sessionId);

            return ResponseEntity.ok(sessionDto);
        }
        catch ( Exception exception)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }
    }

    @PostMapping("/create")
    @Operation(
            summary = "Realiza a abertura de uma nova sessão para votação",
            description = "Realiza a abertura de uma nova sessão para votação de uma pauta"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = {
                    @Content(schema = @Schema(implementation = SessionDto.class))
            })
    })
    public ResponseEntity<Object> create( @Valid @RequestBody SessionDto sessionDto)
    {
        try
        {
            SessionDto newSessionDto = service.create(sessionDto);

            return ResponseEntity.status(HttpStatus.CREATED).body(newSessionDto  );
        }
        catch ( Exception exception)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }
    }
 }
