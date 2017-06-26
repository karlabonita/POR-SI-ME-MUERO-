
import javax.swing.JFrame;


public class VentanaPrincipal extends JFrame implements Constantes {
    
    public static VentanaPrincipal ventana;
    
    public Lienzo lienzo;
     //constrcutor 
    public VentanaPrincipal(){
        ventana = this;
        lienzo = new Lienzo();
        this.getContentPane().add(lienzo);
        this.setSize(ANCHURA_ESCENARIO+40,LARGO_ESCENARIO+40);
    
    }
    
    
}
   
