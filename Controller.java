import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.time.LocalDate;;
public class Controller{
    private Vintage model;

    public Controller(Vintage model) {
        this.model = model;
    }

    public void maiorVendedorAdmin(String dataInferior, String dataSuperior){
        model.maiorVendedorInterativo(dataInferior, dataSuperior);
    }

    public void maiorTransportadoraAdmin(){
        model.maiorTransportadora();
    }

    public void encomendasVendedorAdmin(String emailVendedor){
        model.encomendasVendedorInterativo(emailVendedor);
    }

    public void ordenarUtilizadoresPorFaturamento(String dataInferior, String dataSuperior){
    model.ordenarUtilizadoresPorFaturamentoInterativo(dataInferior, dataSuperior);
    }

    public double faturamentoSistema(){
        return model.getTotalAuferido();
    }

    public void registaUser(String email, String nome, String morada, int nif) {
        model.registaUtilizadorInterativo(email, nome, morada, nif);
    }

    public void removeUser(String emailUser) {
        model.removeUtilizadorInterativo(emailUser);
    }

    //inicializa e retornar as duas transportadoras por omissão
    public Map<String, Transportadora> transportadorasDisp(){
        return model.getTranspDisponiveis();
    }

    //inicializa e retornar as duas transportadoras por omissão
    public void addTranspAdmin(String nome, double cp,  double cm, double cg, double imposto, double custoAdicional){
        model.addTransportadoraInterativo(nome, cp, cm, cg, imposto, custoAdicional);
    }

    public void eliminaUserAdmin(String email){
        model.removeUtilizadorInterativo(email);
    }

    public Utilizador retornarLoggedUser(String email){
       return model.getCreds().get(email);
    }

    public void avancaDataSistema(String dataAtual){
        model.avancaData(dataAtual);
    }

    public void colocaAVendaUser(Utilizador user, String tipoArtigo, String classeArtigo, Artigo.St estado, int numDonos, String transportadora, String descricao, String marca, double precoBase, double correcaoPreco, Mala.Dim dimensao, String material, int anoLancamento, Boolean atacadores, String cor, int tamanho, Tshirt.Tam tamTshirt, Tshirt.Pad padTshirt){
        model.colocaAvendaUserInterativo(user, tipoArtigo, classeArtigo, estado, numDonos, transportadora, descricao, marca, precoBase, correcaoPreco, dimensao, material, anoLancamento, atacadores, cor, tamanho, tamTshirt, padTshirt);
    }

    public void encomendarArtigoUser(Utilizador user, int codBarras, LocalDate dataAtual){
        model.encomendarArtigoUserInterativo(user, codBarras, dataAtual);
    }

    //mostra a encomenda do utilizador
    public Map<Integer, Encomenda>  encomendaUser(Utilizador user){
        return model.encomendaUserInterativo(user.clone());
    }

    public void finalizaEncomendaUser(Utilizador user){
        model.finalizaEncomendaUserInterativo(user);
    }

    public Map<Integer, Artigo> artigosCompradosUser(Utilizador user){
        return model.getCreds().get(user.getEmail()).getComprou();
    }

    public List<String> utilizadoresDisponiveis() {
        return new ArrayList<>(model.getCreds().keySet());
    }

    public Map<Integer, Artigo>  artigosDisponiveis(Utilizador user){
        return model.getStockInterativo(user.clone());
    }

    //mostra os artigos à venda do utilizador
    public Map<Integer, Artigo>  artigosAvendaUser(Utilizador user){
        return model.artigosAVendaUserInterativo(user.clone());
    }

    public void devolverEncomendaUser(Utilizador user){
        model.devolveEncomendaInterativo(user);
    }
}
