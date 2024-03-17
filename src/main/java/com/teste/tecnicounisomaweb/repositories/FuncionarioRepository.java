package com.teste.tecnicounisomaweb.repositories;

import com.teste.tecnicounisomaweb.models.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {

    Optional<Funcionario> findByCpf(String cpf);

}
