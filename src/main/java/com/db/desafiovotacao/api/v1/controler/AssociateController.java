package com.db.desafiovotacao.api.v1.controler;

import com.db.desafiovotacao.dto.AssociateDto;
import com.db.desafiovotacao.exception.AssociateNotFoundException;
import com.db.desafiovotacao.service.AssociateService;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/associate")
@Tag(name = "Associados", description = "Informações dos Associados")
public class AssociateController
{
    @Autowired
    AssociateService service;

    @GetMapping("/")
    @Operation(
            summary = "Listar todos os associados",
            description = "Realiza a busca de todos associados"
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
            List<AssociateDto> associates = service.findAll();

            return ResponseEntity.ok(associates);
        }
        catch ( AssociateNotFoundException exception)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }
    }

    @GetMapping(value = "/{associated}")
    @Operation(
            summary = "Buscar associado por código",
            description = "Realiza a busca de um associado pelo código"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = AssociateDto.class))
            }),
            @ApiResponse(responseCode = "400", description = "Associate not found")
    })
    public ResponseEntity<Object> findById( @PathVariable( value = "associated" ) Integer associateId)
    {
        try
        {
            AssociateDto associate = service.findById(associateId);

            return ResponseEntity.ok(associate);
        }
        catch ( Exception exception)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }
    }

    @PostMapping("/create")
    @Operation(
            summary = "Cadastrar novo associado",
            description = "Realiza o cadastro de um novo associado"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = {
                    @Content(schema = @Schema(implementation = AssociateDto.class))
            }),
            @ApiResponse(responseCode = "400", description = "Associate already exists")
    })
    public ResponseEntity<Object> create( @Valid @RequestBody AssociateDto associate)
    {
        try
        {
            AssociateDto newAssociate = service.create(associate);

            return ResponseEntity.status(HttpStatus.CREATED).body(newAssociate  );
        }
        catch ( Exception exception)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }
    }
 }
