package com.teste.tecnicounisomaweb.repositories;

import com.teste.tecnicounisomaweb.models.Endereco;
import com.teste.tecnicounisomaweb.models.Funcionario;
import com.teste.tecnicounisomaweb.models.dto.FuncionarioDTO;
import com.teste.tecnicounisomaweb.services.EnderecoService;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.Assertions.assertThat;


import java.time.LocalDate;
import java.util.Optional;

@DataJpaTest
@ActiveProfiles("test")
public class FuncionarioRepositoryTest extends TestCase {

    @Autowired
    EnderecoService enderecoService;

    @Autowired
    FuncionarioRepository funcionarioRepository;

    @Test
    @DisplayName("Deve obter o usuário com sucesso do banco de dados")
    public void testFindByCpfCase1() {

            Endereco endereco = new Endereco(
                    null,
                    "13073-220",
                    "Rua Rocha Camargo",
                    "",
                    "Jardim Guanabara",
                    "Campinas",
                    "SP");

            System.out.println(endereco);
            FuncionarioDTO dto = new FuncionarioDTO();
            dto.setNome("Amanda Alves");
            dto.setCpf("821.943.350-72");
            dto.setDataNascimento(LocalDate.of(1987,06,05));
            dto.setTelefone("(11)98853-7865");
            dto.setSalario(3650.95);
            dto.setEndereco(endereco);
            this.criarFuncionario(dto);

            Optional<Funcionario> funcionario = this.funcionarioRepository.findByCpf(dto.getCpf());

            assertThat(funcionario.isPresent()).isTrue();


    }

    private Funcionario criarFuncionario(FuncionarioDTO dto){
        Endereco endereco = this.enderecoService.buscarEndereco(dto.getEndereco().getCep());
        Funcionario funcionario =
                new Funcionario(null,
                        dto.getNome(),
                        dto.getCpf(),
                        dto.getDataNascimento(),
                        dto.getTelefone(),
                        dto.getSalario()
                );
        funcionario.setEndereco(endereco);

      this.funcionarioRepository.save(funcionario);
      return funcionario;

    }

}