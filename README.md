# Sistema de Gerenciamento de Investimentos

Este projeto é um **Sistema de Gerenciamento de Investimentos baseado em Java** projetado para gerenciar usuários, bancos, contas bancárias e investimentos. Ele fornece uma interface de linha de comando (CLI) para os usuários interagirem com o sistema e realizarem várias operações, como registro de usuários, criação de contas bancárias e gerenciamento de investimentos.

## Funcionalidades

### Gerenciamento de Usuários
- Registrar novos usuários com nome, e-mail e senha.
- Listar todos os usuários registrados com paginação.
- Pesquisar um usuário pelo e-mail.

### Gerenciamento de Bancos e Contas
- Adicionar novos bancos com nome e código.
- Pesquisar bancos pelo nome ou iniciais.
- Criar contas bancárias para usuários registrados.
- Listar e visualizar detalhes das contas bancárias.

### Gerenciamento de Investimentos
- Registrar novos investimentos.
- Fazer aportes em investimentos.
- Visualizar objetivos de investimento e saldos de longo prazo.

## Estrutura do Projeto

O projeto segue uma estrutura modular com os seguintes componentes principais:

- **Camada DAO**: Lida com operações no banco de dados (ex.: `ContaBancariaDao`, `UsuarioDao`).
- **Camada de Serviço**: Contém a lógica de negócios (ex.: `UsuarioService`, `BancosService`).
- **Camada de Modelo**: Representa os modelos de dados (ex.: `Usuario`, `Banco`, `ContaBancaria`).
- **Menu**: Fornece uma CLI para interação do usuário (`Menu.java`).
- **Exce��ões**: Exceções personalizadas para tratamento de erros (ex.: `UserNotFoundException`).

## Tecnologias Utilizadas

- **Linguagem de Programação**: Java
- **Ferramenta de Build**: Maven
- **Banco de Dados**: Oracle Database
- **IDE**: IntelliJ IDEA

## Configuração do Banco de Dados

Este projeto utiliza o **Oracle Database** para armazenamento de dados. Abaixo estão os passos para configurar o banco de dados:

1. **Configurar o Oracle Database**:
   - Instale e configure o Oracle Database.
   - Crie um esquema de banco de dados para a aplicação.

2. **Atualizar a Conexão com o Banco de Dados**:
   - Configure os detalhes da conexão na classe `ConnectionFactory`:
     ```java
     private static final String URL = "jdbc:oracle:thin:@<host>:<port>:<service_name>";
     private static final String USER = "<username>";
     private static final String PASSWORD = "<password>";
     ```
     Substitua `<host>`, `<port>`, `<service_name>`, `<username>` e `<password>` pelos detalhes do seu Oracle Database.

3. **Scripts SQL**:
   - Utilize os scripts SQL fornecidos para criar as tabelas necessárias. Certifique-se de que a sintaxe seja compatível com o Oracle (ex.: `VARCHAR2`, `SYSDATE`).

## Como Executar

1. **Clonar o Repositório**:
   ```bash
   git clone <repository-url>
   cd <repository-folder>