import contas.AppFeatures;
import contas.Conta;
import contas.Banco;
import my_utils.ConsoleColors;
import my_utils.MenuUtils;

import java.util.Scanner;

import static my_utils.MenuUtils.*;
//Usar JLINE depois
public class App {
    public static final int SAIDA = 0;
    public static final int CADASTRO = 1;
    public static final int CONSULTA = 2;
    public static final int SAQUE = 3;
    public static final int DEPOSITO = 4;
    public static final int TRANSFERENCIA = 5;
    public static final int EXCLUSAO = 6;
    public static final int TOTAL = 7;
    public static int lerTeclaOpcao(int maximo,String menu) {
        //só tá considerando menus de atpe 10 opceos
        //hum, depois fazer um menu interativo, onde as opcoes vao subindo e descendo, tipo, nao mostrar todas de uma vez
        //adicionar um fail fast dps pra caso só haja a opcao sair
        int opcao = 1;
        limparConsole();
        String sub_menu = (menu.replace("1", ConsoleColors.RED_BACKGROUND+"1"));

        //por enquanto, vou considerar q as opcoes nao estao no formato 01, 02 etd
        sub_menu = (sub_menu.contains("2") ? sub_menu.replace("2",ConsoleColors.RESET+"2") :
                sub_menu.replace("0",ConsoleColors.RESET+"0"));

        try {
            while (true) {
                System.out.print(sub_menu);
                char seta = (char)System.in.read();
                if(seta == '\n'){
                    return opcao;
                }
                if(seta == 'w'){
                    if(opcao == 0){
                        sub_menu = menu.replace(String.format("%d",maximo),ConsoleColors.RED_BACKGROUND+"%d".formatted(maximo)).replace("0",ConsoleColors.RESET+"0");
                        opcao = maximo;
                    }else if(opcao == 1){
                        sub_menu = menu.replace("0",ConsoleColors.RED_BACKGROUND+"0");
                        opcao = 0;
                    }else{
                        sub_menu = menu.replace("%d".formatted(opcao-1),ConsoleColors.RED_BACKGROUND+"%d".formatted(opcao-1)).replace("%d".formatted(opcao),ConsoleColors.RESET+"%d".formatted(opcao));
                        opcao--;
                    }

                }else if(seta == 's'){
                    opcao = (opcao == maximo ? 0 : opcao+1);
                }
            }
        }catch(Exception e){
            return lerTeclaOpcao(maximo,menu);
        }finally {
            limparConsole();
        }
    }
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        String nome_banco = "";

        while(nome_banco.isEmpty() || nome_banco.isBlank()){
            System.out.print("Digite o nome do seu banco: ");
            nome_banco = input.nextLine().trim();
        }//limparConsole();

        Banco banco = new Banco(nome_banco);
        String menu_principal = gerarMenu(nome_banco,"Cadastrar, Consultar, Sacar, Depositar, Transferir, Excluir, Totalizações");
        String menu_basico = gerarMenu(nome_banco, "Cadastrar");
        boolean ha_contas = false;
        int opcao;
        do{
            if(!ha_contas) {
                //opcao = MenuUtils.obterOpcao(menu_basico);
                opcao = lerTeclaOpcao(1,menu_basico);
                if (opcao != 0 && opcao != 1)
                    continue;
            }
            else{
                //opcao = MenuUtils.obterOpcao(menu_principal);
                opcao = lerTeclaOpcao(7,menu_principal);
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