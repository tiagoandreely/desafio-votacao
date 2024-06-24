package com.db.desafiovotacao.service;

import com.db.desafiovotacao.dto.SessionDto;

import java.util.List;

public interface SessionService
{
    List<SessionDto> findAll();
    SessionDto findById(Integer sessionId);
    SessionDto create(SessionDto sessionDto);
}
