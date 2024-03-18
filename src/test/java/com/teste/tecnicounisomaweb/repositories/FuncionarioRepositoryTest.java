package com.teste.tecnicounisomaweb.repositories;

import com.teste.tecnicounisomaweb.models.Endereco;
import com.teste.tecnicounisomaweb.models.Funcionario;
import com.teste.tecnicounisomaweb.models.dto.FuncionarioDTO;
import com.teste.tecnicounisomaweb.services.EnderecoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;


import java.time.LocalDate;
import java.util.Optional;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class FuncionarioRepositoryTest{

    @Autowired
    EnderecoService enderecoService;

    @Autowired
    FuncionarioRepository funcionarioRepository;

    @Test
    @DisplayName("Deve obter o funcionário com sucesso do banco de dados")
    public void testFindByCpfCase1() {

            Endereco endereco = this.enderecoService.buscarEnderecoApi("13073-220");
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
        Endereco endereco = this.enderecoService.buscarEnderecoApi(dto.getEndereco().getCep());
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