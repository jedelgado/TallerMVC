package co.unicauca.clientes.client;

import co.unicauca.clientes.controller.GUIBusquedaClientesController;
import co.unicauca.clientes.modelo.ClientesDB;
import co.unicauca.clientes.vista.GUIBusquedaClientes;
import co.unicauca.clientes.vista.GUIClientesProfesion;
import co.unicauca.clientes.vista.GuiEstadisticaPorSexoGrafica;
import co.unicauca.clientes.vista.GUIEstadisticaPorSexo;

/**
 *
 * @author Libardo Pantoja, Ricardo Zambrano
 */
public class RunMVC {

    public RunMVC() {
        //create Model and View

        ClientesDB myModel = new ClientesDB();
        GUIBusquedaClientes myView = new GUIBusquedaClientes(400, 50);
        
        GUIClientesProfesion myView2 = new GUIClientesProfesion(400,250);
        
        GuiEstadisticaPorSexoGrafica myView3 = new GuiEstadisticaPorSexoGrafica(400, 250);
        
        GUIEstadisticaPorSexo myView4 = new GUIEstadisticaPorSexo(400, 250);

        myModel.addObserver(myView);
        myModel.addObserver(myView2);
        myModel.addObserver(myView3);
        myModel.addObserver(myView4);

        GUIBusquedaClientesController myController = new GUIBusquedaClientesController();
        myController.addModel(myModel);
        myController.addView(myView);
        myView.addController(myController);
    }

}
