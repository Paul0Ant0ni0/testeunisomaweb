package com.teste.tecnicounisomaweb.repositories;

import com.teste.tecnicounisomaweb.models.Endereco;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class EnderecoRepositoryTest extends TestCase {


    @Autowired
    EnderecoRepository enderecoRepository;

    @Test
    @DisplayName("Deve obter o endereço com sucesso do banco de dados")
    public void testFindByCepCase1() {
        Endereco end = new Endereco(
                null,
                "13073-220",
                "Rua Rocha Camargo",
                "",
                "Jardim Guanabara",
                "Campinas",
                "SP");

        this.salvarEndereco(end);
        Optional<Endereco> endereco = this.enderecoRepository.findByCep("13073-220");

        assertThat(endereco.isPresent()).isTrue();
    }


    private Endereco salvarEndereco(Endereco endereco){
        this.enderecoRepository.save(endereco);
        return endereco;
    }

}