package com.db.desafiovotacao.api.v1.controler;


import com.db.desafiovotacao.dto.VoteDto;
import com.db.desafiovotacao.service.VoteService;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/vote")
@Tag(name = "Votos", description = "Informações sobre os votos")
public class VoteController
{
    @Autowired
    VoteService service;

    @PostMapping("/create")
    @Operation(
            summary = "Realiza o cadastro de um voto do associado",
            description = "Realiza o cadastro de um voto do associado em uma sessão ativa de determinada pauta definida"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = {
                    @Content(schema = @Schema(implementation = VoteDto.class))
            })
    })
    public ResponseEntity<Object> create( @Valid @RequestBody VoteDto voteDto)
    {
        try
        {
            VoteDto newVote = service.create(voteDto);

            return ResponseEntity.status( HttpStatus.CREATED).body(newVote  );
        }
        catch ( Exception exception)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }
    }
 }
