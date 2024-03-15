package com.teste.tecnicounisomaweb.repositories;

import com.teste.tecnicounisomaweb.models.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {

    Optional<Funcionario> findByCpf(String cpf);

    @Query(value = "SELECT * FROM funcionarios WHERE dtype = 'salario' BETWEEN :valor1 AND :valor2", nativeQuery = true)
    List<Funcionario> findBySalarioEntreFaixas(Double valor1, Double valor2);
}
