//import java.io.IOException;

import java.io.Serializable;
import java.time.LocalDate;

public class Main implements Serializable{

    public static void main(String[] args) throws ExceptionData{// throws IOException, InterruptedException{

        Vintage model = new Vintage();
        Controller controller = new Controller(model);
        View view = new View(controller);

        //Colocar uma data no sistema
        controller.setDataSistema(LocalDate.now());

        //caso a informação inicial seja dada por um ficheiro
        if (args.length > 0) {
            String fileName = args[0];
            controller.loadficheiroSistema(fileName);
        }

        //caso contrário
        view.run();
    }
}

