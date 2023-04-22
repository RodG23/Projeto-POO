import java.util.Map;
import java.util.HashMap;

public class Fatura {
    
    private static int numFaturas = 0;

    enum Tp {
        COMPRA,
        VENDA
    }

    private final int numEmissao;
    private Tp tipo;
    private Map<Integer,Artigo> artigos;
    private double valorTotal;

    /**
     * Construtores
     */
    public Fatura() {
        numFaturas++;
        this.numEmissao = numFaturas;
        this.tipo = null;
        this.artigos = new HashMap<>();
        this.valorTotal = 0;
    }

    public Fatura(Tp tipo, Map<Integer,Artigo> art, double valorTotal) {
        numFaturas++;
        this.numEmissao = numFaturas;
        this.tipo = tipo;
        this.artigos = new HashMap<Integer,Artigo>();
        for(Map.Entry<Integer,Artigo> e : art.entrySet())
        {
            this.artigos.put(e.getKey(), e.getValue().clone());
        }
        this.valorTotal = valorTotal;
    }

    public Fatura(Fatura f) {
        this.numEmissao = f.getNumEmissao();
        this.tipo = f.getTipo();
        this.artigos = f.getArtigos();
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

    public Map<Integer,Artigo> getArtigos() {
        Map<Integer,Artigo> map = new HashMap<Integer,Artigo>();
        for (Map.Entry<Integer,Artigo> e : this.artigos.entrySet())
        {
            map.put(e.getKey(),e.getValue().clone());
        }
        return map;
    } 

    public double getValorTotal() {
        return this.valorTotal;
    }

    /**
     * Setters.
     */
    public void setArtigos(Map<Integer,Artigo> art) {
        Map<Integer,Artigo> map = new HashMap<Integer,Artigo>();
        for (Map.Entry<Integer,Artigo> e : art.entrySet())
        {
            map.put(e.getKey(),e.getValue().clone());
        }
        this.artigos = map;
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

        sb.append(" | Fatura |\n");
        sb.append(" Número de emissão -> " + this.getNumEmissao() + "\n");
        sb.append(" Tipo -> " + this.getTipo().toString() + "\n");
        sb.append(" Artigos:\n");
        for(Artigo a : this.getArtigos().values())
        {
            sb.append(a.toString());
        }
        sb.append(" ValorTotal -> " + this.getValorTotal() + "\n");

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
                this.getTipo().equals(f.getTipo()) && 
                this.getArtigos().equals(f.getArtigos()) &&
                this.getValorTotal() == f.getValorTotal();
    }
}
