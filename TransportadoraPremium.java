public class TransportadoraPremium extends Transportadora implements TPremium {
    private double custoAdicional; // custo adicional para transportar artigos premium

    public TransportadoraPremium(String nome, double cp, double cm, double cg, double imp, double custoAdicional) {
        super(nome, cp, cm, cg, imp);
        this.custoAdicional = custoAdicional;
    }

    public TransportadoraPremium(TransportadoraPremium tp) {
        super(tp);
        this.custoAdicional = tp.getCustoAdicional();
    }

    public double getCustoAdicional() {
        return this.custoAdicional;
    }

    public void setCustoAdicional(double custoAdicional) {
        this.custoAdicional = custoAdicional;
    }

    @Override
    public TransportadoraPremium clone() {
        return new TransportadoraPremium(this);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(" Custo adicional por artigo premium -> " + this.getCustoAdicional() + "€\n");
        return sb.toString() + " Tipo de Transportadora: Premium\n";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (obj == null || obj.getClass() != this.getClass())
            return false;
        TransportadoraPremium tp = (TransportadoraPremium) obj;
        return super.equals(tp) && tp.getCustoAdicional() == this.getCustoAdicional();
    }


    /**
     * Cálculo do valor de expedição de uma encomenda pequena com a transportadora premium.
     */
    @Override
    public double calcularValorExpedicaoPequeno() {
        double precoBase = this.getCustoPequena();
        double valorFinal = (precoBase * 0.60 * (1 + super.getImposto()))*0.5;
        return  Math.round(valorFinal * 100.0) / 100.0; // arredondar para 2 casas decimais
    }

    /**
     * Cálculo do valor de expedição de uma encomenda média com a transportadora premium.
     */
    @Override
    public double calcularValorExpedicaoMedio() {
        double precoBase = super.getCustoMedia();
        double valorFinal = (precoBase * 0.60 * (1 + super.getImposto())) * 0.6;
        return Math.round(valorFinal * 100.0) / 100.0;
    }

    /**
     * Cálculo do valor de expedição de uma encomenda grande com a transportadora premium.
     */
    @Override
    public double calcularValorExpedicaoGrande(){
        double precoBase = super.getCustoGrande();
        double valorFinal = (precoBase * 0.60 * (1 + super.getImposto()))*0.9;
        return  Math.round(valorFinal * 100.0) / 100.0; // arredondar para 2 casas decimais
    }

    @Override
    public double calculaExpedicaoPremium(long numeroArtigos){
        double valorFinal =0.0;
        if(numeroArtigos == 1){
            valorFinal = this.calcularValorExpedicaoPequeno() + this.custoAdicional;
        }
        else if(numeroArtigos > 1 && numeroArtigos<=5){
            valorFinal = this.calcularValorExpedicaoMedio() + this.custoAdicional*1.2;
        }
        else if(numeroArtigos > 5){
            valorFinal = this.calcularValorExpedicaoGrande() + this.custoAdicional*1.5;
        } 
        double aux = super.getTotalAuferido();
        aux += valorFinal;
        super.setTotalAuferido(aux);
        return valorFinal;
    }
}
