package br.com.vidarica.menu;

import br.com.vidarica.exceptions.UserNotFoundException;
import br.com.vidarica.model.*;
import br.com.vidarica.services.BancosService;
import br.com.vidarica.services.DespesasService;
import br.com.vidarica.services.InvestimentoService;
import br.com.vidarica.services.UsuarioService;

import java.util.List;
import java.util.Scanner;

public class Menu {
    public void exibirMenuPrincipal() {
        System.out.println("Bem-vindo ao sistema de investimentos!");
        Scanner scanner = new Scanner(System.in);
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("=== Menu Principal ===");
            System.out.println("Escolha uma opção:");
            System.out.println("1. Gestão de Usuários");
            System.out.println("2. Gestão de Bancos e Contas");
            System.out.println("3. Gestão de Despesas");
            System.out.println("4. Gestão de Investimentos");
            System.out.println("0. Sair");

            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    this.usuarioSubmenu();
                    break;
                case 2:
                    this.bancosSubMenu();
                    break;
                case 3:
                    this.despesasSubMenu();
                    break;
                case 4:
                    this.investimentosSubMenu();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }

    private void investimentosSubMenu()
    {


        Scanner scanner = new Scanner(System.in);
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("=== Submenu de Investimentos ===");
            System.out.println("1. Cadastrar Investimento");
            System.out.println("2. Fazer Aporte");
            System.out.println("3. Consultar Objetivo");
            System.out.println("4. Ver Saldo Longo Prazo");
            System.out.println("0. Voltar ao menu principal");

            opcao = scanner.nextInt();
            switch (opcao) {
                case 1:
                    this.cadastrarInvestimento();
                    break;
                case 2:
                    this.fazerAporte();
                    break;
                case 3:
                    this.consultarObjetivos();
                    break;
                case 4:
                    this.verSaldoLongoPrazo();
                    break;
                case 0:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");

            }
        }
    }

    private void usuarioSubmenu()
    {

        Scanner scanner = new Scanner(System.in);
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("=== Submenu de Usuário ===");
            System.out.println("1. Cadastrar Usuário");
            System.out.println("2. Listar Usuários");
            System.out.println("3. Listar Informações de um Usuário");
            System.out.println("0. Voltar ao menu principal");

            opcao = scanner.nextInt();
            switch (opcao) {
                case 1:
                    this.cadastrarUsuario();
                    break;
                case 2:
                    this.listarUsuarios();
                    break;
                case 3:
                    this.consultarUsuario();
                    break;
                case 0:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }

    private void bancosSubMenu()
    {


        Scanner scanner = new Scanner(System.in);
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("=== Submenu de Bancos e Contas ===");
            System.out.println("1. Cadastrar Banco");
            System.out.println("2. Buscar Banco");
            System.out.println("3. Cadastrar Conta");
            System.out.println("4. Listar Contas");
            System.out.println("5. Consultar Conta");
            System.out.println("0. Voltar ao menu principal");

            opcao = scanner.nextInt();
            switch (opcao) {
                case 1:
                    this.adicionarBanco();
                    break;
                case 2:
                    this.buscarBanco();
                    break;
                case 3:
                    this.cadastrarContaBancaria();
                    break;
                case 4:
                    this.listarContas();
                    break;
                case 0:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");

            }
        }
    }

    private void despesasSubMenu()
    {


        Scanner scanner = new Scanner(System.in);
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("=== Submenu de Investimentos ===");
            System.out.println("1. Cadastrar Despesa");
            System.out.println("2. Listar Despesas");
            System.out.println("3. Total Despesas");
            System.out.println("0. Voltar ao menu principal");

            opcao = scanner.nextInt();
            switch (opcao) {
                case 1:
                    this.cadastrarDespesa();
                    break;
                case 2:
                    this.listarDespesas();
                    break;
                case 3:
                    this.totalDespesas();
                    break;
                case 0:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");

            }
        }
    }

    private void cadastrarUsuario() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Cadastro de Usuário ===");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        UsuarioService.cadastrarUsuario(nome, email, senha);
    }

    private void listarUsuarios() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Listar Usuários ===");
        System.out.print("Digite o número de usuários por página: ");
        int pageSize = scanner.nextInt();
        System.out.print("Digite o número da página: ");
        int pageNumber = scanner.nextInt();

        this.listarUsuarios(pageSize, pageNumber);

    }

    private void listarUsuarios(int pageSize, int pageNumber) {
        Scanner scanner = new Scanner(System.in);
        int nextPage = pageNumber + 1;
        int offset = (pageNumber - 1) * pageSize;
        System.out.println(offset);
        int total = UsuarioService.listarUsuarios(pageSize, offset);
        if (pageSize * pageNumber < total) {
            System.out.print("Deseja ver mais usuários? (s/n): ");
            String resposta = scanner.next();
            if (resposta.equalsIgnoreCase("s")) {
                listarUsuarios(pageSize, nextPage);
            }
        }

    }

    private void consultarUsuario()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Consultar Usuário ===");
        System.out.print("Email: ");
        String email = scanner.nextLine();
        try {
            UsuarioService.consultarUsuarioPorEmail(email);
        } catch (UserNotFoundException e) {
            System.out.println("Algo de errado não está certo " + e.getMessage());
        }


    }

    private void cadastrarContaBancaria() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Cadastro de Conta Bancária ===");
        System.out.print("Qual o email do usuário para quem a conta será criada?\n");
        String email = scanner.nextLine();
        try {
            Usuario usuario = UsuarioService.consultarUsuarioPorEmail(email);
            System.out.print("Qual Banco? Escolha uma opção da lista:\n");
            List<Banco> bancos = BancosService.listarBancos();
            int opcaoBanco = 1;

            if (bancos == null || bancos.isEmpty()) {
                System.out.println("Nenhum banco encontrado.");
                return;
            }

            for (Banco banco : bancos) {
                System.out.println(opcaoBanco + ". " + banco.getCodigo() + ". " + banco.getNome());
                opcaoBanco++;
            }
            int opcaoEscolhida = scanner.nextInt();
            Banco bancoEscolhido = bancos.get(opcaoEscolhida - 1);
            System.out.println("Nome: ");
            scanner.nextLine(); // Limpar o buffer do scanner
            String nome = scanner.nextLine();
            System.out.println("Agência: ");
            String agencia = scanner.nextLine();
            System.out.println("Dígito da Agência: (opcional): ");
            String digitoAgenciaInput = scanner.nextLine();
            Integer digitoAgencia = digitoAgenciaInput.isEmpty() ? null : Integer.parseInt(digitoAgenciaInput);
            System.out.println("Número da Conta: ");
            String numeroConta = scanner.nextLine();
            System.out.println("Dígito da Conta: ");
            int digitoConta = scanner.nextInt();
            System.out.println("Tipo de Conta (Corrente/Poupança): ");
            scanner.nextLine(); // Limpar o buffer do scanner
            String tipoConta = scanner.nextLine();

            BancosService.criarContaBancaria(usuario, bancoEscolhido, nome, tipoConta, agencia, digitoAgencia, numeroConta, digitoConta);
            System.out.println("Conta criada com sucesso!");

        } catch (UserNotFoundException e) {
            System.out.println("Usuário não encontrado, quer adicioná-o primeiro? (s/n) ");
            String resposta = scanner.nextLine();
            if (resposta.equalsIgnoreCase("s")) {
                this.cadastrarUsuario();
            } else {
                System.out.println("Voltando ao menu de contas...");
            }
        } finally {
            System.out.println("Voltando ao menu de contas...");
        }


    }

    private void adicionarBanco() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Cadastro de Banco ===");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Código: ");
        String codigo = scanner.nextLine();

        BancosService.adicionarBanco(nome, codigo);

    }

    private void buscarBanco() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Buscar Banco ===");
        System.out.print("Digite o nome ou as iniciais do banco: ");
        String termo = scanner.nextLine();
        BancosService.procurarBanco(termo);
    }

    private void listarContas() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Listar Contas Bancárias ===");
        System.out.print("Digite o Email do usuário para listar as contas: ");
        String email = scanner.nextLine();
        try {
            Usuario usuario = UsuarioService.consultarUsuarioPorEmail(email);
            BancosService.listarContasBancarias(usuario);

            System.out.println("Voltando ao menu de contas...");
        } catch (UserNotFoundException e) {
            System.out.println("Usuário não encontrado. Tente outro email. ");
        } catch (Exception e) {
            System.out.println("Erro ao listar contas: " + e.getMessage());
        }
    }

    private void cadastrarDespesa() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Cadastro de Despesa ===");
        System.out.print("Digite o email do usuário: ");
        String email = scanner.nextLine();

        try {
            Usuario usuario = UsuarioService.consultarUsuarioPorEmail(email);
            System.out.print("Nome: ");
            String nome = scanner.nextLine();
            System.out.print("Valor: ");
            double valor = scanner.nextDouble();

            DespesasService.registrarDespesa(nome, valor, usuario);

        } catch (UserNotFoundException e) {
            System.out.println("Usuário não encontrado. Tente outro email. ");
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar despesa: " + e.getMessage());
        }



        // Aqui você pode adicionar a lógica para salvar a despesa no banco de dados ou em uma lista
    }
    private void listarDespesas() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Listar Despesas ===");
        System.out.print("Digite o email do usuário: ");
        String email = scanner.nextLine();

        try {
            Usuario usuario = UsuarioService.consultarUsuarioPorEmail(email);
            DespesasService.listarDespesas(usuario);
        } catch (UserNotFoundException e) {
            System.out.println("Usuário não encontrado. Tente outro email. ");
        } catch (Exception e) {
            System.out.println("Erro ao listar despesas: " + e.getMessage());
        }


    }
    private void totalDespesas() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Total de Despesas ===");
        System.out.print("Digite o email do usuário: ");
        String email = scanner.nextLine();

        try {
            Usuario usuario = UsuarioService.consultarUsuarioPorEmail(email);
            DespesasService.totalDespesas(usuario);
        } catch (UserNotFoundException e) {
            System.out.println("Usuário não encontrado. Tente outro email. ");
        } catch (Exception e) {
            System.out.println("Erro ao calcular total de despesas: " + e.getMessage());
        }


    }

    public void cadastrarInvestimento() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Cadastro de Investimento ===");
        System.out.print("Digite o email do usuário para o investimento: ");
        String email = scanner.nextLine();

        try {
            Usuario usuario = UsuarioService.consultarUsuarioPorEmail(email);

            System.out.print("Qual Conta? Escolha uma opção da lista:\n");
            List<ContaBancaria> contas = BancosService.listarContasBancarias(usuario);
            int opcaoConta = 1;

            if (contas == null || contas.isEmpty()) {
                System.out.println("Nenhum banco encontrado.");
                return;
            }

            for (ContaBancaria conta : contas) {
                System.out.println(opcaoConta + ". " + conta.getNome());
                opcaoConta++;
            }
            int opcaoEscolhida = scanner.nextInt();
            ContaBancaria contaEscolhida = contas.get(opcaoEscolhida - 1);

            System.out.print("Tipo de Investimento: Digite 1 para objetivos ou 2 para longo prazo): ");
            scanner.nextLine(); // Limpar o buffer do scanner
            int tipoInvestimento = scanner.nextInt();

            System.out.print("Nome do Investimento: ");
            scanner.nextLine(); // Limpar o buffer do scanner
            String nome = scanner.nextLine();
            System.out.print("Aporte Incial: ");
            double valor = scanner.nextDouble();

            if (tipoInvestimento == 1 ) {
                scanner.nextLine(); // Limpar o buffer do scanner
                System.out.print("Descrição do Objetivo: ");
                String descricao = scanner.nextLine();
                System.out.print("Valor total do Investimento: ");
                double valorFinal = scanner.nextDouble();

                InvestimentoService.criarObjetivo(nome, descricao, valorFinal, valor, usuario, contaEscolhida);
            } else if (tipoInvestimento == 2 ) {
                System.out.print("Descrição do Investimento de longo prazo: ");
                String descricao = scanner.nextLine();

                InvestimentoService.criarInvestimentoLongoPrazo(nome, descricao, valor, usuario, contaEscolhida);
            } else {
                System.out.println("Tipo de investimento inválido. Tente novamente.");
            }


        } catch (UserNotFoundException e) {
            System.out.println("Usuário não encontrado. Tente outro email. ");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void fazerAporte() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Fazer Aporte ===");
        System.out.print("Digite o email do usuário: ");
        String email = scanner.nextLine();

        try {
            Usuario usuario = UsuarioService.consultarUsuarioPorEmail(email);


            System.out.print("Tipo de Investimento: Digite 1 para objetivos ou 2 para longo prazo): ");
            int tipoInvestimento = scanner.nextInt();

            if (tipoInvestimento == 1) {
                System.out.print("Qual Objetivo? Escolha uma opção da lista:\n");
                List<Objetivo> objetivos = InvestimentoService.listarObjetivos(usuario);

                int opcaoObjetivo = 1;
                if (objetivos == null || objetivos.isEmpty()) {
                    System.out.println("Nenhum objetivo encontrado.");
                    return;
                }

                for (Objetivo objetivo : objetivos) {
                    System.out.println(opcaoObjetivo + ". " + objetivo.getNome());
                    opcaoObjetivo++;
                }
                int opcaoEscolhida = scanner.nextInt();
                Objetivo objetivo = objetivos.get(opcaoEscolhida - 1);
                System.out.print("Valor do aporte: ");
                double valorAporte = scanner.nextDouble();
                InvestimentoService.adicionarAporteObjetivo(valorAporte, objetivo);

            } else if (tipoInvestimento == 2) {
                System.out.print("Qual Longo Prazo? Escolha uma opção da lista:\n");
                List<LongoPrazo> longoPrazos = InvestimentoService.listarLongoPrazo(usuario);
                if (longoPrazos == null || longoPrazos.isEmpty()) {
                    System.out.println("Nenhum longo prazo encontrado.");
                    return;
                }

                int opcaoLongoPrazo = 1;
                for (LongoPrazo longoPrazo : longoPrazos) {
                    System.out.println(opcaoLongoPrazo + ". " + longoPrazo.getNome());
                    opcaoLongoPrazo++;
                }
                int opcaoEscolhida = scanner.nextInt();
                LongoPrazo longoPrazo = longoPrazos.get(opcaoEscolhida - 1);
                System.out.print("Valor do aporte: ");
                double valorAporte = scanner.nextDouble();
                InvestimentoService.adicionarAporteLongoPrazo(valorAporte, longoPrazo);

            }

        } catch (UserNotFoundException e) {
            System.out.println("Usuário não encontrado. Tente outro email. ");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
    public void consultarObjetivos() {}
    public void verSaldoLongoPrazo() {}
}
