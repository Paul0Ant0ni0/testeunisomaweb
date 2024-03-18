package com.teste.tecnicounisomaweb.repositories;

import com.teste.tecnicounisomaweb.models.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {

    @Query(value = "SELECT * FROM funcionarios WHERE cpf = :cpf ",nativeQuery = true)
    Optional<Funcionario> findByCpf(String cpf);

}
