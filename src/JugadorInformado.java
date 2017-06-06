
import java.util.ArrayList;




public class JugadorInformado extends Jugador implements Constantes  {
    
    //public BusquedaAnchuraInformada inteligencia;
    public BusquedaAStar inteligencia;

    public JugadorInformado( Escenario escenario) {
        super(escenario, JUGADOR_INFORMADO);
        posicionX = 0;
        posicionY = 0;
        this.escenario = escenario;
        //inteligencia = new BusquedaAnchuraInformada(escenario);
        inteligencia = new BusquedaAStar(escenario);
    }
    
    private ArrayList<Character> buscarRutaCorta(ArrayList<Character> ruta1, ArrayList<Character> ruta2)
    {
        ArrayList<Character> menor = ruta1;
        if(menor == null) menor = ruta2;
        if(ruta2 != null && menor.size() > ruta2.size())
        {
            menor = ruta2;
        }        
        return menor;
    }
    
    @Override
    public void run() 
    {
        ArrayList<Celda> recompensas = escenario.obtenerRecompensas();
        ArrayList<Character> ruta = null;
        if(recompensas.size() > 0)
        {
            for(Celda celda: recompensas)
            {
                ArrayList<Character> aux = inteligencia.buscar(posicionX, posicionY, celda.i, celda.j);
                //System.out.println("celda"+celda.i+" "+celda.j);
                //System.out.println(aux);
                ruta = buscarRutaCorta(ruta, aux);                  
            }
        }
        else
        {           
            ruta = inteligencia.buscar(posicionX, posicionY, 24, 19);            
        }
        System.out.println(ruta);        
        
        if(ruta != null && ruta.size() > 0)
        {
            if(escenario.jugador.energia <= 0)
            {
               //this.cancel();
            }
            switch(ruta.get(ruta.size()-1))
            {
                 case 'D': escenario.jugador.moverAbajo(); break;
                 case 'U': escenario.jugador.moverArriba(); break;
                 case 'R': escenario.jugador.moverDerecha(); break;
                 case 'L': escenario.jugador.moverIzquierda(); break;
            }
            escenario.dondeSeDibuja.repaint();
        }
    }
    
    
}

