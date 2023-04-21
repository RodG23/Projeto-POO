import java.time.LocalDate;

public class Tempo {

    private int dia;
    private int mes;
    private int ano;
    
    public Tempo(){
        LocalDate dataAtual = LocalDate.now();
        this.dia = dataAtual.getDayOfMonth();
        this.mes = dataAtual.getMonthValue();
        this.ano = dataAtual.getYear();
    }

    public Tempo(int dia, int mes, int ano) {
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
    }

    public Tempo(Tempo t){
        this.dia = t.getDia();
        this.mes = t.getMes();
        this.ano = t.getAno();
    }
    
    public int getDia() {
        return this.dia;
    }
    
    public int getMes() {
        return this.mes;
    }
    
    public int getAno() {
        return this.ano;
    }

    private int diasNoMes() {
        if (this.mes == 2) {
            if (this.ano % 4 == 0 && (this.ano % 100 != 0 || this.ano % 400 == 0)) {
                return 29;
            } else {
                return 28;
            }
        } else if (this.mes == 4 || this.mes == 6 || this.mes == 9 || this.mes == 11) {
            return 30;
        } else {
            return 31;
        }
    }
    
    public Tempo avancaDia() {
        this.dia++;
        // verificar se o dia atual ultrapassou o número de dias do mês atual
        if (this.dia > diasNoMes()) {
            this.dia = 1;
            this.mes++;
            // verificar se o mês atual ultrapassou o número de meses no ano atual
            if (this.mes > 12) {
                this.mes = 1;
                this.ano++;
            }
        }
        return this;
    }
    @Override
    public Tempo clone() {
        return new Tempo(this);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (( o == null ) || ( this.getClass () != o.getClass ()))  return false ;
        Tempo t = (Tempo) o;
        return t.getDia() == this.dia && t.getMes() == this.mes && t.getAno() == this.ano;
    }

    @Override
    public String toString() {
        return String.format("%02d/%02d/%04d", dia, mes, ano);
    }
}

