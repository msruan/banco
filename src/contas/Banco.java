package contas;
import java.util.ArrayList;
public class Banco {
    public static void main(String[] args) {

    }
    @Override
    public String toString(){
        return String.format("\nTotal de contas: %d\nDinheiro total: R$%f\nMédia de dinheiro por conta: R$ %f\n\n",this.consultarTotalDeContas(),this.consultarTotalDeDinheiro(),this.consultarMediaDeDinheiro());
    }
    public Banco(String nome){
        this.nome = nome;
    }
    public String nome;
    private ArrayList <Conta> contas = new ArrayList<>();
    public int consultarTotalDeContas(){
        return contas.size();
    }
    public double consultarTotalDeDinheiro(){
        double saldo_total = 0;
        for(Conta conta: contas){
            saldo_total += conta.consultarSaldo();
        }return saldo_total;
    }
    public double consultarMediaDeDinheiro(){
        return consultarTotalDeDinheiro() / consultarTotalDeContas();
    }

    public void cadastrarConta(Conta conta){
        if(!estaCadastrada(conta.consultarNumero())){
            contas.add(conta);
        }
    }
    public boolean estaCadastrada(String numero){
        for(Conta conta: contas){
            if(conta.consultarNumero().equals(numero))
                return true;
        }return false;
    }
    public boolean transferir(String fonte, String destino, double valor){
        int index_fonte = obterIndicePorNumero(fonte);
        if(index_fonte!= -1){
            int index_destino = obterIndicePorNumero(destino);
            if(index_destino!= -1){
                return contas.get(index_fonte).transferir(contas.get(index_destino),valor);
            }
        }return false;
    }

    public boolean sacar(String numero, double valor){
        Conta alvo = consultarPorNumero(numero);
        if(alvo != null){
            return alvo.sacar(valor);
        }return false;
    }
    public boolean depositar(String numero, double valor){
        Conta alvo = consultarPorNumero(numero);
        if(alvo != null) {
            return alvo.depositar(valor);
        }return false;
    }
    public void excluirContaPorNumero(String numero){
        int index = obterIndicePorNumero(numero);
        if(index != -1){
            contas.remove(index);
        }
    }
    public Conta consultarPorNumero(String numero) {
        for (Conta item : this.contas) {
            if (item.consultarNumero().equals(numero))
                return item;
        }
        return null;
    }
    public int obterIndicePorNumero(String numero){
        for(int i =0; i < contas.size(); i++){
            if(numero.equals(contas.get(i).consultarNumero()))
                return i;
        }return -1;
    }
    public void alterar(Conta conta) {
        int indice_procurado = obterIndicePorNumero(conta.consultarNumero());
        if (indice_procurado != -1)
            contas.set(indice_procurado, conta);
    }

}