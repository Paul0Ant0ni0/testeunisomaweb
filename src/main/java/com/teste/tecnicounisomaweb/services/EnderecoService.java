package com.teste.tecnicounisomaweb.services;


import com.google.gson.Gson;
import com.teste.tecnicounisomaweb.models.Endereco;
import com.teste.tecnicounisomaweb.models.dto.EnderecoDTO;
import com.teste.tecnicounisomaweb.repositories.EnderecoRepository;
import com.teste.tecnicounisomaweb.services.errors.RecursoNaoEncontradoError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import java.io.IOException;
import java.util.Optional;


@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    public final String URL_API_VIACEP = "https://viacep.com.br/ws/";

    public Endereco salvarEndereco(EnderecoDTO dto) {
        Optional<Endereco> enderecoExistente = enderecoRepository.findByCep(dto.getCep());

        if (enderecoExistente.isPresent()) {
            return enderecoExistente.get();
        } else {
            Endereco endereco = new Endereco(
                    null,
                    dto.getCep(),
                    dto.getLogradouro(),
                    dto.getComplemento(),
                    dto.getBairro(),
                    dto.getLocalidade(),
                    dto.getUf()
            );
            return enderecoRepository.save(endereco);
        }
    }
    public Endereco buscarEnderecoApi(String cep) throws RecursoNaoEncontradoError {
        String regex = "\\d{5}-?\\d{3}";
        EnderecoDTO endereco = new EnderecoDTO();

        if (!cep.matches(regex)){
            throw new RecursoNaoEncontradoError("CEP inválido: " + cep);
        } else {
            HttpGet request = new HttpGet(this.URL_API_VIACEP + cep + "/json/");

            try (CloseableHttpClient httpClient = HttpClientBuilder.create().disableRedirectHandling().build();
                 CloseableHttpResponse response = httpClient.execute(request)) {

                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    String result = EntityUtils.toString(entity);
                    Gson gson = new Gson();
                    endereco = gson.fromJson(result, EnderecoDTO.class);

                }// Verificar se os campos do EnderecoDTO não estão nulos
                if (endereco.getLogradouro() == null || endereco.getBairro() == null || endereco.getLocalidade() == null || endereco.getUf() == null) {
                    throw new RecursoNaoEncontradoError("CEP inválido: " + cep);
                }

            } catch (IOException e) {
                System.out.println("Erro: " + e.getMessage());
            }

            return this.salvarEndereco(endereco);
        }
    }






}
