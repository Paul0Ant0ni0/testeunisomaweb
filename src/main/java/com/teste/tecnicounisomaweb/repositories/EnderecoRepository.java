package com.teste.tecnicounisomaweb.repositories;


import com.teste.tecnicounisomaweb.models.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {
    Optional<Endereco> findByCep(String cep);

}
