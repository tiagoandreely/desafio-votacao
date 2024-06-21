package com.db.desafiovotacao.api.v1.controlers;

import com.db.desafiovotacao.exceptions.AssociateNotFoundException;
import com.db.desafiovotacao.model.Associate;
import com.db.desafiovotacao.service.AssociateService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/associate")
public class AssociateController
{
    @Autowired
    AssociateService service;

    @GetMapping("/")
    public ResponseEntity<Object> findAll()
    {
        try
        {
            List<Associate> associates = service.findAll();

            return ResponseEntity.ok(associates);
        }
        catch ( AssociateNotFoundException exception)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }
    }

    @GetMapping(value = "/{associated}")
    public ResponseEntity<Object> findById( @PathVariable( value = "associated" ) Integer associateId)
    {
        try
        {
            Associate associate = service.findById(associateId);

            return ResponseEntity.ok(associate);
        }
        catch ( Exception exception)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Object> create( @Valid @RequestBody Associate associate)
    {
        try
        {
            Associate newAssociate = service.create(associate);

            return ResponseEntity.status(HttpStatus.CREATED).body(newAssociate  );
        }
        catch ( Exception exception)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }
    }
 }
