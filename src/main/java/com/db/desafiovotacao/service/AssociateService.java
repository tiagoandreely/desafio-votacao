package com.db.desafiovotacao.service;

import com.db.desafiovotacao.dto.AssociateDto;

import java.util.List;

public interface AssociateService
{
    List<AssociateDto> findAll();
    AssociateDto findById(Integer associateDtoId);
    AssociateDto create(AssociateDto associateDto);
}
