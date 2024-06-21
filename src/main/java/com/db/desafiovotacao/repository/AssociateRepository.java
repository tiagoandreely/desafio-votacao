package com.db.desafiovotacao.repository;

import com.db.desafiovotacao.model.Associate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AssociateRepository extends JpaRepository<Associate, Integer>
{
    Optional<Associate> findByCpf(String cpf);
}
