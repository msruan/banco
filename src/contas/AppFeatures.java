package contas;

import java.util.Scanner;

import static my_utils.MenuUtils.*;
import static my_utils.MenuUtils.lerSimOuNao;

public class AppFeatures {
    public static void cadastrar(Scanner input, Banco banco) {
        String nome;
        double saldo;
        String numero_cadastro = lerString("\nDigite o número da conta desejada: ", input);
        while (!(banco.consultarPorNumero(numero_cadastro) == null)) {
            numero_cadastro = lerString("Este número já está cadastrado!\nPor favor, digite outro: ", input);
        }
        nome = lerString("Digite seu nome: ", input);
        saldo = lerDoublePositivo("\nDigite seu saldo atual: ", input);
        banco.cadastrarConta(new Conta(numero_cadastro, nome, saldo));
    }

    public static void excluirConta(Banco banco, Scanner input) {
        Conta alvo;
        do{
            alvo = banco.consultarPorNumero(lerString("Digite o numero da conta desejada: ", input));
            if(alvo == null){
                System.out.println("Conta não encontrada!");
            }else{
                banco.excluirContaPorNumero(alvo.consultarNumero());
                System.out.println("Exclusão concluída com sucesso!");
                break;
            }
        }while(lerSimOuNao("Deseja tentar novamente?", input));
    }

    public static void transferir(Scanner input, Banco banco) {
        String numero_fonte, numero_destino;
        double valor_transferencia;
        do {
            numero_fonte = lerString("Digite o número da conta fonte: ", input);
            numero_destino = lerString("Digite o número da conta destino: ", input);
            valor_transferencia = lerDoublePositivo("Digite o valor a ser transferido: ", input);

            if (!banco.transferir(numero_fonte, numero_destino, valor_transferencia)) {
                System.out.println("Operação falhou!");
            } else {
                System.out.printf("Transferência de R$ %f para %s realizada com sucesso!", valor_transferencia, banco.consultarPorNumero(numero_destino).consultarNome());
                break;
            }
        }while(lerSimOuNao("Repetir operação? ", input));
    }

    public static void depositar(Scanner input, Banco banco) {
        String numero_depositada;
        do {
            numero_depositada = lerString("\nDigite o número da sua conta: ", input);
            if(banco.estaCadastrada(numero_depositada)){
                double valor_deposito = lerDoublePositivo("\nDigite o valor do saque: ", input);
                if(banco.depositar(numero_depositada,valor_deposito)){
                    System.out.println("Depósito de R$ "+valor_deposito+" realizado com sucesso!");
                    break;
                }
                else
                    System.out.println("Depósito falhou!");
            }else
                System.out.println("Conta não encontrada!");
            System.out.println();
        }while(lerSimOuNao("Repetir operação? ", input));
    }

    public static void consultar(Banco banco, Scanner input) {
        Conta procurada;
        do {
            procurada = banco.consultarPorNumero(lerString("Digite o numero da conta desejada: ", input));
            if (procurada == null)
                System.out.println("Conta não encontrada!");
            else
                System.out.println(procurada);
            System.out.println();
        }while (lerSimOuNao("Deseja realizar uma nova consulta?", input));
    }

    public static void sacar(Scanner input, Banco banco) {
        String numero_sacada;
        double valor_saque;
        do {
            numero_sacada = lerString("\nDigite o número da sua conta: ", input);
            if(banco.estaCadastrada(numero_sacada)){
                valor_saque = lerDoublePositivo("\nDigite o valor do saque: ", input);
                if(banco.sacar(numero_sacada,valor_saque)){
                    System.out.println("Saque de R$ "+valor_saque+" realizado com sucesso!");
                    break;
                }
                else
                    System.out.println("Saldo insuficiente! ");
            }else
                System.out.println("Conta não encontrada!");
            System.out.println();
        }while(lerSimOuNao("Repetir operação? ", input));//Limpar tela quando quiser repteir operação...
    }
}
