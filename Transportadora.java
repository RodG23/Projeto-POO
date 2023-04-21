public class Transportadora {
    
    private double custoPequena; //Guarda o valor base cobrado pela transportadora numa encomenda pequena.
    private double custoMedia; //Guarda o valor base cobrado pela transportadora numa encomenda media.
    private double custoGrande; //Guarda o valor base cobrado pela transportadora numa encomenda grande.
    private double imposto; //Guarda o valor do imposto.
    private double totalAuferido; //Guarda o total ganho pela transportadora.

    /**
     * Construtor vazio.
     */
    public Transportadora() {
        this.custoPequena = 0;
        this.custoMedia = 0;
        this.custoGrande = 0;
        this.imposto = 0;
        this.totalAuferido = 0;
    }

    /**
     * Construtor parametrizado. Total auferido não é parâmetro pois numa transportadora nova é 0.
     */
    public Transportadora(double cp, double cm, double cg, double imp) {
        this.custoPequena = cp;
        this.custoMedia = cm;
        this.custoGrande = cg;
        this.imposto = imp;
        this.totalAuferido = 0;
    }

    /**
     * Construtor de cópia.
     */
    public Transportadora(Transportadora umaTransportadora) {
        this.custoPequena = umaTransportadora.getCustoPequena();
        this.custoMedia = umaTransportadora.getCustoMedia();
        this.custoGrande = umaTransportadora.getCustoGrande();
        this.imposto = umaTransportadora.getImposto();
        this.totalAuferido = umaTransportadora.getTotalAuferido();
    }

    /**
     * Getters das variáveis de instância de uma transportadora.
     */
    public double getCustoPequena() {
        return this.custoPequena;
    }

    public double getCustoMedia() {
        return this.custoMedia;
    }

    public double getCustoGrande() {
        return this.custoGrande;
    }

    public double getImposto() {
        return this.imposto;
    }

    public double getTotalAuferido() {
        return this.totalAuferido;
    }

    /**
     * Setters das variáveis de instância de uma transportadora.
     * Os de custo e imposto servirão para alterar a fórmula de cálculo de transporte.
     */
    public void setCustoPequena(double cp) {
        this.custoPequena = cp;
    }
    
    public void setCustoMedia(double cm) {
        this.custoMedia = cm;
    }

    public void setCustoGrande(double cg) {
        this.custoGrande = cg;
    }

    public void setImposto(double imp) {
        this.imposto = imp;
    }

    /**
     * Adiciona ao total auferido o valor que irá cobrar ao comprador.
     */
    public void addTotalAuferido(double income) {
        this.totalAuferido += income;
    }

    /**
     * Método clone.
     */
    @Override
    public Transportadora clone() {
        return new Transportadora(this);
    }

    /**
     * Método toString.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("| Transportadora |\n");
        sb.append(" Custo base por encomenda pequena -> " + this.getCustoPequena() + "€\n");
        sb.append(" Custo base por encomenda media -> " + this.getCustoMedia() + "€\n");
        sb.append(" Custo base por encomenda grande -> " + this.getCustoGrande() + "€\n");
        sb.append(" Valor de imposto -> " + this.getImposto() + "%\n");
        sb.append(" Total auferido -> " + this.getTotalAuferido() + "€\n");

        return sb.toString();
    }

    /**
     * Método equals.
     */
    @Override
    public boolean equals(Object obj) {
        if(obj==this) 
            return true;
        if(obj==null || obj.getClass() != this.getClass()) 
            return false;
        Transportadora transportadora = (Transportadora) obj;
        return transportadora.getCustoPequena() == this.getCustoPequena() &&
               transportadora.getCustoMedia() == this.getCustoMedia() &&
               transportadora.getCustoGrande() == this.getCustoGrande() &&
               transportadora.getImposto() == this.getImposto() &&
               transportadora.getTotalAuferido() == this.getTotalAuferido();
    }
}
