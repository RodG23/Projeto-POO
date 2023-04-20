package Trabalhopratico;

import java.util.Set;
import java.util.HashSet;

public class Faturas {
    

    private static int numFaturas = 0;

    enum Tp {
        COMPRA,
        VENDA
    }

    private final int numEmissao;
    private final Tp tipo;
    private Set<Artigo> artigos;
    private double valorTotal;

    // Getters
    
    public static int get_NumFaturas() {
        return numFaturas;
    }
    
    public int get_NumEmissao() {
        return this.numEmissao;
    }
    
    public Tp get_tipo() {
        return this.tipo;
    }

    public Set<Artigo> get_Artigos() {
        return artigos;
    } 

    public double get_ValorTotal() {
        return this.valorTotal;
    }

    // Setters

    public void set_Artigos(Set<Artigo> artigos) {
        this.artigos = artigos; 
    }

    public void set_ValorTotal(double valor) {
        this.valorTotal = valor;
    }

    // Construtores 

    public Faturas() {
        this.numEmissao = 0;
        this.tipo = null;
        this.artigos = new HashSet<>();
        this.valorTotal = 0;

        numFaturas++;
    }

    public Faturas(int numEmissao, Tp tipo, Set<Artigo> artigos, double valorTotal) {
        this.numEmissao = numEmissao;
        this.tipo = tipo;
        this.artigos = artigos;
        this.valorTotal = valorTotal;

        numFaturas++;
    }

    public Faturas(Faturas fatura) {
        this.numEmissao = fatura.get_NumEmissao();
        this.tipo = fatura.get_tipo();
        this.artigos = fatura.get_Artigos();
        this.valorTotal = fatura.get_ValorTotal();

        numFaturas++;
    }

    @Override
    public Faturas clone() {
        return new Faturas(this);
    }

    @Override
    public String toString() {
        return "\n--- Faturas ---\n" + "Por fazer... = ";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true ;
        if (( o == null ) || ( this.getClass () != o.getClass ()))  return false ;

        Faturas f = (Faturas) o ;
        return this.numEmissao == f.get_NumEmissao() && this.tipo == f.get_tipo() && this.artigos.equals(f.get_Artigos()) &&
               this.valorTotal == f.get_ValorTotal();
    }

}
