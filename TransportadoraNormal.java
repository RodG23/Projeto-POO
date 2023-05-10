public class TransportadoraNormal extends Transportadora implements TNormal {

        /**
         * Construtor vazio.
         */
        public TransportadoraNormal() {
            super();
        }
    
        /**
         * Construtor parametrizado. Total auferido não é parâmetro pois numa transportadora nova é 0.
         */
        public TransportadoraNormal(String nome, double cp, double cm, double cg, double imp) {
            super(nome, cp, cm, cg, imp);
        }
    
        /**
         * Construtor de cópia.
         */
        public TransportadoraNormal(TransportadoraNormal umaTransportadora) {
            super(umaTransportadora);
        }
    
        /**
         * Método clone.
         */
        @Override
        public TransportadoraNormal clone() {
            return new TransportadoraNormal(this);
        }
    
        /**
         * Método toString.
         */
        @Override
        public String toString() {
            return super.toString() + " └── Tipo de Transportadora: Normal \n";
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this)
                return true;
            if (obj == null || obj.getClass() != this.getClass())
                return false;
            TransportadoraNormal tp = (TransportadoraNormal) obj;
            return super.equals(tp);
        }

        /**
         * Cálculo do valor de expedição de uma encomenda pequena com a transportadora normal.
         */
        @Override
        public double calcularValorExpedicaoPequeno() {
            double precoBase = this.getCustoPequena();
            double valorFinal = (precoBase * 0.50 * (1 + super.getImposto()))*0.5;
            return  Math.round(valorFinal * 100.0) / 100.0; // arredondar para 2 casas decimais
        }
    
        /**
         * Cálculo do valor de expedição de uma encomenda média com a transportadora normal.
         */
        @Override
        public double calcularValorExpedicaoMedio() {
            double precoBase = super.getCustoMedia();
            double valorFinal = (precoBase * 0.50 * (1 + super.getImposto())) * 0.6;
            return Math.round(valorFinal * 100.0) / 100.0;
        }

        /**
         * Cálculo do valor de expedição de uma encomenda grande com a transportadora normal.
         */
        @Override
        public double calcularValorExpedicaoGrande(){
            double precoBase = super.getCustoGrande();
            double valorFinal = (precoBase * 0.50 * (1 + super.getImposto()))*0.9;
            return  Math.round(valorFinal * 100.0) / 100.0; // arredondar para 2 casas decimais
        }

        @Override
        public double calculaExpedicaoNormal(long numeroArtigos){
            double valorFinal =0.0;
            if(numeroArtigos == 1){
                valorFinal = this.calcularValorExpedicaoPequeno();
            }
            else if(numeroArtigos > 1 && numeroArtigos<=5){
                valorFinal = this.calcularValorExpedicaoMedio();
            }
            else if(numeroArtigos > 5){
                valorFinal = this.calcularValorExpedicaoGrande();
            } 
            super.setTotalAuferido(valorFinal);
            return valorFinal;
        }
    }
