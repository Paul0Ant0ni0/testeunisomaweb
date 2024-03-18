package com.teste.tecnicounisomaweb.services;

import com.teste.tecnicounisomaweb.models.Endereco;
import com.teste.tecnicounisomaweb.models.Funcionario;
import com.teste.tecnicounisomaweb.models.dto.FuncionarioDTO;
import com.teste.tecnicounisomaweb.repositories.FuncionarioRepository;
import com.teste.tecnicounisomaweb.services.EnderecoService;
import com.teste.tecnicounisomaweb.services.FuncionarioService;
import com.teste.tecnicounisomaweb.services.errors.FuncionarioExistenteError;
import com.teste.tecnicounisomaweb.services.errors.RecursoNaoEncontradoError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class FuncionarioServiceTest {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Mock
    private EnderecoService enderecoService;

   @Autowired
    private FuncionarioService funcionarioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

    }


    @Test
    @DisplayName("Deve salvar um funcionário")
    void testSalvarFuncionario() {
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

        FuncionarioDTO dto = new FuncionarioDTO();
        dto.setNome("João");
        dto.setCpf("12345678901");
        dto.setDataNascimento(LocalDate.of(1990, 1, 1));
        dto.setTelefone("123456789");
        Double aDouble = BigDecimal.valueOf(2000.00).doubleValue();
        dto.setSalario(aDouble);
        dto.setEndereco(endereco);
        when(funcionarioRepository.findByCpf(dto.getCpf())).thenReturn(Optional.empty());
        when(enderecoService.buscarEnderecoApi(anyString())).thenReturn(new Endereco());

        Funcionario funcionarioSalvo = funcionarioService.salvarFuncionario(dto);

        assertNotNull(funcionarioSalvo);
        assertEquals("João", funcionarioSalvo.getNome());
        assertEquals("12345678901", funcionarioSalvo.getCpf());
        assertEquals(LocalDate.of(1990, 1, 1), funcionarioSalvo.getDataNascimento());
        assertEquals("123456789", funcionarioSalvo.getTelefone());
        assertEquals(aDouble, funcionarioSalvo.getSalario());

        verify(funcionarioRepository, times(1)).findByCpf(dto.getCpf());
        verify(enderecoService, times(1)).buscarEnderecoApi(anyString());
        verify(funcionarioRepository, times(1)).save(any(Funcionario.class));
    }

    @Test
    @DisplayName("Deve dar erro ao tentar salvar um funcionário já existente")
    void testSalvarFuncionarioExistente() {
        FuncionarioDTO dto = new FuncionarioDTO();
        dto.setNome("João");
        dto.setCpf("12345678901");
        dto.setDataNascimento(LocalDate.of(1990, 1, 1));
        dto.setTelefone("123456789");
        dto.setSalario(Double.valueOf(BigDecimal.valueOf(2000.00).toString()));

        when(funcionarioRepository.findByCpf(dto.getCpf())).thenReturn(Optional.of(new Funcionario()));

        assertThrows(FuncionarioExistenteError.class, () -> funcionarioService.salvarFuncionario(dto));

        verify(funcionarioRepository, times(1)).findByCpf(dto.getCpf());
        verifyNoMoreInteractions(enderecoService);
        verifyNoMoreInteractions(funcionarioRepository.save(any(Funcionario.class)));
    }

    @Test
    @DisplayName("Deve dar erro ao tentar calcular o reajuste salarial para um funcionário não encontrado")
    void testCalcReajusteSalarialFuncionarioNaoEncontrado() {
        String cpf = "12345678901";
        when(funcionarioRepository.findByCpf(cpf)).thenReturn(Optional.empty());

        assertThrows(RecursoNaoEncontradoError.class, () -> funcionarioService.calcReajusteSalarial(cpf));

        verify(funcionarioRepository, times(1)).findByCpf(cpf);
        verifyNoMoreInteractions(funcionarioRepository);
    }

    // Teste para os outros métodos da classe FuncionarioService...

}
