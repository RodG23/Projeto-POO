import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import javax.swing.tree.TreeNode;

public class Fatura {
    
    private static int numFaturas = 0;

    enum Tp {
        COMPRA,
        VENDA
    }

    private final int numEmissao;
    private Tp tipo;
    private Encomenda encomenda;
    private double valorTotal;

    /**
     * Construtores
     */
    public Fatura() {
        numFaturas++;
        this.numEmissao = numFaturas;
        this.tipo = null;
        this.encomenda = null;
        this.valorTotal = 0;
    }

    public Fatura(Tp tipo, Encomenda encomenda, Artigo artigo, double valorTotal) {
        numFaturas++;
        this.numEmissao = numFaturas;
        this.tipo = tipo;
        this.encomenda = encomenda;
        this.valorTotal = valorTotal;
    }

    public Fatura(Fatura f) {
        this.numEmissao = f.getNumEmissao();
        this.tipo = f.getTipo();
        this.encomenda = f.getEncomenda();
        this.valorTotal = f.getValorTotal();
    }

    /**
     * Getters
     */
    public int getNumEmissao() {
        return this.numEmissao;
    }
    
    public Tp getTipo() {
        return this.tipo;
    }

    public Encomenda getEncomenda(){
        return (this.encomenda != null ? this.encomenda.clone() : null);
    }

    public double getValorTotal() {
        return this.valorTotal;
    }

    /**
     * Setters.
     */

    public void setEncomenda(Encomenda encomenda) {
        this.encomenda = encomenda.clone();
    }

    public void setValorTotal(double valor) {
        this.valorTotal = valor;
    }

    public void setTipo(Tp tp) {
        this.tipo = tp;
    }

    /**
     * Método clone.
     */
    @Override
    public Fatura clone() {
        return new Fatura(this);
    }

    /**
     * Método toString
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("     | Fatura |\n");
        sb.append(" Número de emissão -> " + this.getNumEmissao() + "\n");
        sb.append(" Tipo -> " + this.getTipo().toString() + "\n");
        sb.append(" Encomenda -> \n" +(this.encomenda != null ? this.encomenda.toString() : null) + "\n");
        sb.append(" ValorTotal -> " + Math.round(this.getValorTotal()*100)/100 + "€\n");

        return sb.toString();
    }

    /**
     * Método equals.
     */
    @Override
    public boolean equals(Object obj) {
        if(this == obj) 
            return true;
        if(( obj == null ) || ( this.getClass () != obj.getClass ()))  
            return false;
        Fatura f = (Fatura) obj;

        return  this.getNumEmissao() == f.getNumEmissao() && 
                this.getEncomenda() == f.getEncomenda() &&
                this.getTipo().equals(f.getTipo()) && 
                this.getValorTotal() == f.getValorTotal();
    }

    //Encomenda feita pelo comprador
    public void addEncFatura(Encomenda encomenda){
        this.encomenda = encomenda.clone();
    }
    public void removeEncFatura(Encomenda encomenda){
        this.encomenda = null;
    }

    public void calculaFatura(int anoAtual, double taxaGSNovo, double taxaGSUsado, double taxaServiço) {

        //para quem venda nao tem taxas sobre a venda dos produtos por isso tem que se fazer de novo a conta com alterações na formula
        if (this.tipo == Tp.VENDA) {
            double x = this.encomenda.getArtigos().values().stream().mapToDouble(artigo -> {
                if (artigo instanceof ArtigoNovo) {
                    return ((ArtigoNovo) artigo).calcularValorArtigoNovo(anoAtual); // chama o método correto para ArtigoNovo
    
                } else if (artigo instanceof ArtigoUsado) {
                    return ((ArtigoUsado) artigo).calcularValorArtigoUsado(anoAtual); // chama o método correto para ArtigoUsado
    
                } else if(artigo instanceof ArtigoPremium){
                    return ((ArtigoPremium) artigo).calcularValorArtigoPremium(anoAtual); // chama o método correto para ArtigoPremium
                } else{
                    return 0.0;
                }
            }).sum();
            this.valorTotal = x * taxaServiço; // para garantir que envia a encomenda
            this.encomenda.setPrecoFinal(this.valorTotal);
        }
        else{
            this.valorTotal = this.encomenda.getPrecoFinal();
        }
    }

}