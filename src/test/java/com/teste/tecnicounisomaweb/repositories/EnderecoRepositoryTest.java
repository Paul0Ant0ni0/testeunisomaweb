package com.teste.tecnicounisomaweb.repositories;

import com.teste.tecnicounisomaweb.models.Endereco;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;


@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class EnderecoRepositoryTest{
    @Autowired
    EnderecoRepository enderecoRepository;


    @Test
    @DisplayName("Deve obter o endereço com sucesso do banco de dados")
    public void testFindByCepCase1() {
        Endereco endereco =
                new Endereco(
                        null,
                        "13073-221",
                        "Rua Frei Manoel da Ressurreição",
                        "até 1367/1368",
                        "Jardim Guanabara",
                        "Campinas",
                        "SP"
                );

        this.salvarEnderecoDB(endereco);
        Optional<Endereco> end = this.enderecoRepository.findByCep("13073-221");
        assertThat(end.isPresent()).isTrue();
        System.out.println(end.get().getLogradouro());

    }


    private Endereco salvarEnderecoDB(Endereco endereco){
        this.enderecoRepository.save(endereco);
        return endereco;
    }


}