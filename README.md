# Projeto de Gerenciamento de Funcionários

Este projeto tem como objetivo desenvolver uma API RESTful para o gerenciamento de funcionários de uma empresa fictícia chamada UniSoma. A API permitirá cadastrar funcionários, calcular e atualizar seus salários, calcular impostos de renda e fornecer informações sobre os funcionários.

## Funcionalidades Implementadas

1. **Modelagem de Banco de Dados**: Foi realizada a modelagem do banco de dados utilizando PostgreSQL. O modelo de dados inclui tabelas para armazenar informações sobre os funcionários e seus endereços.

2. **Diagrama de Classes**: Foi criado um diagrama de classes UML para representar a estrutura das entidades no sistema, incluindo as classes `Funcionario` e `Endereco`, juntamente com suas relações e métodos.

3. **Conexão com o Banco de Dados**: Foi implementada a conexão com o banco de dados PostgreSQL para persistência dos dados. Utilizamos o Hibernate como implementação JPA para facilitar o acesso aos dados.

4. **Implementações das Models, Repositories, Services, Controllers e DTOs**: Foram implementadas as classes de modelo (`Funcionario` e `Endereco`), repositórios (`FuncionarioRepository` e `EnderecoRepository`), serviços (`FuncionarioService` e `EnderecoService`), controladores REST (`FuncionarioController` e `EnderecoController`) e DTOs correspondentes para transferência de dados.

5. **Teste da API no Postman e Documentação**: A API foi testada no Postman para garantir o correto funcionamento de todos os endpoints. Além disso, a documentação da API foi elaborada utilizando o formato OpenAPI (anteriormente conhecido como Swagger) para facilitar o entendimento e utilização por parte dos desenvolvedores.

6. **Testes Unitários com JUnit e Mockito**: Foram criados testes unitários utilizando JUnit e Mockito para garantir a integridade e robustez do código. Os testes cobrem os principais cenários de uso da API, incluindo casos de sucesso e possíveis cenários de erro.

## Como Executar o Projeto

1. **Configuração do Ambiente**: Certifique-se de ter o JDK 8 ou superior e o Maven instalados em sua máquina.

2. **Configuração do Banco de Dados**: Crie um banco de dados PostgreSQL e configure as credenciais de acesso no arquivo `application.properties`.

3. **Compilação e Execução**: Utilize o Maven para compilar o projeto e gerar o arquivo JAR executável. Em seguida, execute o JAR para iniciar o servidor.

```bash
mvn clean package
java -jar target/nome-do-arquivo.jar
```

4. **Testando a API**: Utilize o Postman ou qualquer outra ferramenta de sua preferência para testar os endpoints da API. A documentação da API pode ser acessada em `<URL-da-API>/swagger-ui.html`.

5. **Execução dos Testes Unitários**: Utilize o Maven para executar os testes unitários.

```bash
mvn test
```

## Contribuição

Contribuições são sempre bem-vindas! Sinta-se à vontade para abrir uma issue ou enviar um pull request com sugestões, correções de bugs ou novas funcionalidades.

## Licença

Este projeto está licenciado sob a [MIT License](https://opensource.org/licenses/MIT).

---

Este README fornece uma visão geral do projeto, descrevendo as funcionalidades implementadas, instruções para execução do projeto, testes e contribuições. Certifique-se de personalizar o README de acordo com as características específicas do seu projeto.
