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
     * @param cp -> Custo base de encomenda pequena.
     * @param cm -> Custo base de encomenda média.
     * @param cg -> Custo base de encomenda grande.
     * @param imp -> Valor de imposto praticado pela transportadora.
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
     * @param umaTransportadora -> Transportadora a copiar.
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
     * @return
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
    public Transportadora clone() {
        return new Transportadora(this);
    }

    /**
     * Método toString.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("| Transportadora |\n");
        sb.append(" Custo base por encomenda pequena -> " + this.custoPequena + "€\n");
        sb.append(" Custo base por encomenda media -> " + this.custoMedia + "€\n");
        sb.append(" Custo base por encomenda grande -> " + this.custoGrande + "€\n");
        sb.append(" Valor de imposto -> " + this.imposto + "%\n");
        sb.append(" Total auferido -> " + this.totalAuferido + "€\n");

        return sb.toString();
    }

    /**
     * Método equals.
     */
    public boolean equals(Object obj) {
        if(obj==this) 
            return true;
        if(obj==null || obj.getClass() != this.getClass()) 
            return false;
        Transportadora transportadora = (Transportadora) obj;
        return transportadora.custoPequena == this.custoPequena &&
               transportadora.custoMedia == this.custoMedia &&
               transportadora.custoGrande == this.custoGrande &&
               transportadora.imposto == this.imposto &&
               transportadora.totalAuferido == this.totalAuferido;
    }
}
