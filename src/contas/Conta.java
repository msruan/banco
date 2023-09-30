package contas;
public class Conta {
    @Override
    public String toString(){
        return String.format("Conta %s\nCliente: %s\nSaldo atual: %f",this.consultarNumero(),this.consultarNome(),this.consultarSaldo());
    }
    private final String numero;
    private final String nome;
    private double saldo;


    public Conta(String numero, String nome, double saldo){
        this(numero,nome);
        this.saldo = saldo;
    }
    public Conta(String numero, String nome){
        this.numero = numero;
        this.nome = nome;
    }

    public String consultarNumero() {
        return numero;
    }public String consultarNome() {
        return nome;
    }
    boolean sacar(double valor){
        if (valor > 0 && this.saldo >= valor) {
            this.saldo -= valor;
            return true;
        } return false;
    }

    boolean depositar(double valor){
        if (valor > 0) {
            this.saldo += valor;
            return true;
        }return false;
    }

    public double consultarSaldo(){
        return this.saldo;
    }

    boolean transferir(Conta contaDestino, double valor){
        if (this.sacar(valor)) {
            return contaDestino.depositar(valor);
        }return false;
    }
    public static void main(String[] args){
        Conta c1 = new Conta("1", "Bianca", 100);
        Conta c2 = new Conta("2", "Lindinho");


        // Operação de transferência
        System.out.println("Operação de transferência");
        c1.transferir(c2, 200); // Erro
        c2.transferir(c1, 100);
        System.out.println(c1.consultarSaldo());
        System.out.println(c2.consultarSaldo());

    }
}