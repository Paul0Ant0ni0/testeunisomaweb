package com.teste.tecnicounisomaweb.services;


import com.teste.tecnicounisomaweb.models.Endereco;
import com.teste.tecnicounisomaweb.models.dto.EnderecoDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;


@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class EnderecoServiceTest{

    @Autowired
    EnderecoService enderecoService;

    @Test
    @DisplayName("Deve dar sucesso ao buscar um cep na api")
    public void testFindByCepCase1() {
        Endereco endLocalizado = this.enderecoService.buscarEnderecoApi("13073-220");
        assertNotNull(endLocalizado);

    }


    @Test
    @DisplayName("Deve dar erro ao buscar um cep inválido na api")
    public void testFindByCepCase2() {
        Endereco endLocalizado = this.enderecoService.buscarEnderecoApi("13073-2000");;
        assertNull(endLocalizado);

    }

    @Test
    @DisplayName("Deve dar sucesso ao salvar um endereco no DB")
    public void testAddressSavingCase1() {
        EnderecoDTO enderecoDTO = new EnderecoDTO();
        enderecoDTO.setCep("13073-222");
        enderecoDTO.setLogradouro("Rua São José");
        enderecoDTO.setComplemento("123");
        enderecoDTO.setBairro("Vila Nova");
        enderecoDTO.setLocalidade("Campinas");
        enderecoDTO.setUf("SP");

        Endereco endLocalizado = this.enderecoService.salvarEndereco(enderecoDTO);
        assertNotNull(endLocalizado);

    }
}