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
        String header = "┌──────────────────── Fatura ───────────────────\n";
        String footer = "└────────────────────────────────────────────────\n";
        
        String numEmissao = String.format("│ Número de emissão: %d\n", this.getNumEmissao());
        String tipo = String.format("│ Tipo:   %s\n", this.getTipo().toString());
        String encomenda = String.format("│ Id da encomenda:   %d\n", (this.encomenda != null ? this.encomenda.getId() : "Não associada"));
        String valorTotal = String.format("│ Valor Total:     %.2f€\n", this.getValorTotal());
        
        return header + numEmissao + tipo + encomenda + valorTotal + footer;
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

    public void calculaValorFatura() {
        this.valorTotal = this.encomenda.getPrecoFinal();
    }

}