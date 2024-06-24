package com.db.desafiovotacao.service.impl;


import com.db.desafiovotacao.dto.Mapper;
import com.db.desafiovotacao.dto.VoteDto;
import com.db.desafiovotacao.exception.AssociateAlreadyVotedException;
import com.db.desafiovotacao.exception.AssociateNotFoundException;
import com.db.desafiovotacao.exception.MandatoryFieldException;
import com.db.desafiovotacao.exception.SessionClosedException;
import com.db.desafiovotacao.exception.SessionNotFoundException;
import com.db.desafiovotacao.model.Session;
import com.db.desafiovotacao.model.Vote;
import com.db.desafiovotacao.repository.AssociateRepository;
import com.db.desafiovotacao.repository.SessionRepository;
import com.db.desafiovotacao.repository.VoteRepository;
import com.db.desafiovotacao.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoteImpl implements VoteService
{

    @Autowired
    VoteRepository voteRepository;

    @Autowired
    AssociateRepository associateRepository;

    @Autowired
    SessionRepository sessionRepository;

    @Override
    public VoteDto create( VoteDto voteDto )
    {
        validateFields( voteDto );

        associateRepository.findById( voteDto.getAssociateId() ).orElseThrow( AssociateNotFoundException::new );

        Session session = sessionRepository.findById( voteDto.getSessionId() ).orElseThrow( SessionNotFoundException::new);

        if(session.isClosed())
        {
            throw new SessionClosedException();
        }

        voteRepository.findByAssociateIdAndSessionId( voteDto.getAssociateId(), voteDto.getSessionId() ).ifPresent( vote -> {
            throw new AssociateAlreadyVotedException();
        });

        Vote vote = Mapper.mapToVote( voteDto );

        Vote newVote = voteRepository.save( vote );

        return Mapper.mapToVoteDto( newVote );
    }

    private void validateFields( VoteDto voteDto )
    {
        if (voteDto.getAssociateId() == null) {
            throw new MandatoryFieldException("associateId");
        }

        if (voteDto.getSessionId() == null) {
            throw new MandatoryFieldException("sessionId");
        }

        if (voteDto.getOptionVote() == null) {
            throw new MandatoryFieldException("resultVote");
        }
    }
}
