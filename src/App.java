import contas.AppFeatures;
import contas.Conta;
import contas.Banco;
import my_utils.ConsoleColors;
import my_utils.MenuUtils;

import java.util.Scanner;

import static my_utils.MenuUtils.*;

public class App {
    public static final int SAIDA = 0;
    public static final int CADASTRO = 1;
    public static final int CONSULTA = 2;
    public static final int SAQUE = 3;
    public static final int DEPOSITO = 4;
    public static final int TRANSFERENCIA = 5;
    public static final int EXCLUSAO = 6;
    public static final int TOTAL = 7;
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String nome_banco = "";
        while(nome_banco.isEmpty() || nome_banco.isBlank()){
            System.out.print("Digite o nome do seu banco: ");
            nome_banco = input.nextLine().trim();
        }limparConsole();
        Banco banco = new Banco(nome_banco);
        String menu_principal = gerarMenu(nome_banco,"Cadastrar, Consultar, Sacar, Depositar, Transferir, Excluir, Totalizações");
        String menu_basico = gerarMenu(nome_banco, "Cadastrar");
        boolean ha_contas = false;
        int opcao;
        do{
            if(!ha_contas) {
                opcao = MenuUtils.obterOpcao(menu_basico);
                if (opcao != 0 && opcao != 1)
                    continue;
            }
            else{
                opcao = MenuUtils.obterOpcao(menu_principal);
                if (opcao < 0 || opcao > 7)
                    continue;
            }


            switch (opcao) {
                case CADASTRO:
                    AppFeatures.cadastrar(input, banco);
                    ha_contas = true;
                    break;
                case CONSULTA:
                    AppFeatures.consultar(banco, input);
                    break;
                case SAQUE:
                    AppFeatures.sacar(input, banco);
                    break;
                case DEPOSITO:
                    AppFeatures.depositar(input, banco);
                    break;
                case TRANSFERENCIA:
                    AppFeatures.transferir(input, banco);
                    break;
                case EXCLUSAO:
                    AppFeatures.excluirConta(banco, input);
                    break;
                case TOTAL:
                    System.out.println(banco);
                    break;
            }
            limparConsole();
        }while(opcao != SAIDA);
        input.close();

    }




}

/* import prompt from "prompt-sync";
import { Conta, Banco } from "./banco";

let input = prompt();
let b: Banco = new Banco();
let opcao: String = '';
do {
console.log('\nBem vindo\nDigite uma opção:');
console.log('1 - Cadastrar 2 - Consultar 3 - Sacar\n' +
'4 - Depositar 5 - Excluir 6 - Transferir\n' +
'7 – Totalizações' +
'0 - Sair\n');
opcao = input("Opção:");
switch (opcao) {
case "1":
inserir();
break
case "2":
consultar();
break
//...
}
input("Operação finalizada. Digite <enter>");
} while (opcao != "0");
console.log("Aplicação encerrada");

function inserir(): void {
console.log("\nCadastrar conta\n");
let numero: string = input('Digite o número da conta:');
let conta: Conta;
conta = new Conta(numero, 0);
b.inserir(conta);
}
//...*/