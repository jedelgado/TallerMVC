package co.unicauca.clientes.vista;

import co.unicauca.clientes.modelo.Cliente;
import co.unicauca.clientes.modelo.ClientesDB;
import java.awt.Color;
import java.awt.Graphics;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Observable;
/**
 *
 * @author Javier
 */
public class GuiEstadisticaPorSexoGrafica extends javax.swing.JFrame implements java.util.Observer {

    /**
     * Constructor
     *
     * @param h horizontal position
     * @param v vertical position
     */
    public GuiEstadisticaPorSexoGrafica(int h, int v) {
        
        initComponents();
        setSize(370, 230); // Tamaño de la vista principal.  Alto y ancho
        setLocation(h, v);
        setVisible(true);
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanelDatos = new java.awt.Panel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout jPanelDatosLayout = new javax.swing.GroupLayout(jPanelDatos);
        jPanelDatos.setLayout(jPanelDatosLayout);
        jPanelDatosLayout.setHorizontalGroup(
            jPanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 397, Short.MAX_VALUE)
        );
        jPanelDatosLayout.setVerticalGroup(
            jPanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelDatos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(365, Short.MAX_VALUE))
            .addComponent(jPanelDatos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
       
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GuiEstadisticaPorSexoGrafica(400, 400).setVisible(true);
            }
        });   
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private java.awt.Panel jPanelDatos;
    // End of variables declaration//GEN-END:variables

    @Override
    public void update(Observable obs, Object obj) {
        ClientesDB cliDB = (ClientesDB) obs;
        ArrayList<Cliente> clientes = cliDB.getClientesPorProfesion();
        this.setTitle("Clientes por profesión: " + cliDB.getProfesion());
        dibujarGrafica(cliDB.getTotalHombres(), cliDB.getTotalMujeres());
    }
    
    
    public void dibujarGrafica(int parHombres, int parMujeres){
        
        ClientesDB c = new  ClientesDB();
        
        int ancho = 50;
        int alto = 130;
        int tope = 20;
        int espacio = 100; 
        int moverColumnas = 100;
        
        Graphics draw = jPanelDatos.getGraphics();
        
        //Limpiar todo
        draw.setColor(Color.white);
        draw.fillRect(0, 0, this.getSize().width, this.getSize().height);
        
        //Fondo blanco
        draw.setColor(Color.white);
        draw.fillRect(moverColumnas + 1, tope + 1, ancho - 1, alto - 1);
        draw.fillRect(moverColumnas + 1 + espacio, tope + 1, ancho - 1, alto - 1);
        
        //Linea de las columnas rojas
        draw.setColor(Color.RED);
        draw.drawRect(moverColumnas, tope, ancho, alto);
        draw.drawRect(moverColumnas + espacio, tope, ancho, alto);
        
        //Variables para los porcentajes
        float total = parHombres + parMujeres;
        float totHombres = parHombres / total;
        float totMujeres = parMujeres / total;
        long colTope = (long) (alto - alto * totHombres); //Para saber el total de porcentaje a pintar
        long coltope2 = (long) (alto - alto * totMujeres);
        
        //Rellenar las columnas de color
        draw.setColor(Color.BLUE);
        //Para la columna Hombres 
        draw.fillRect(moverColumnas + 1, tope + (int) colTope, ancho - 1, alto - (int) colTope);
        //Para la columna Mujeres
        draw.fillRect(moverColumnas + 101, tope + (int) coltope2, ancho - 1, alto - (int) coltope2);
        
        //Nombres de las columnas y resultados
        draw.setColor(Color.black);
        draw.drawString("<Hombres>> ", moverColumnas, tope + alto + 20);
        DecimalFormat formato = new DecimalFormat(" ##.00");
        draw.drawString("" + formato.format(totHombres * 100) + "%", moverColumnas, tope - 5);
        draw.drawString("<Mujeres>> ", moverColumnas + espacio, tope + alto + 20);
        draw.drawString("" + formato.format(totMujeres * 100) +"%", moverColumnas + espacio, tope - 5); 
        
    }
    
}
