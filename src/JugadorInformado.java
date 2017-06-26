
import java.util.ArrayList;




public class JugadorInformado extends Jugador implements Constantes  {
    
    //public BusquedaAnchuraInformada inteligencia;
    public BusquedaAStar inteligencia;

    public JugadorInformado( Escenario escenario) {
        super(escenario, JUGADOR_INFORMADO);
        posicionX = 11;
        posicionY =18;
        this.escenario = escenario;
        //inteligencia = new BusquedaAnchuraInformada(escenario);
        inteligencia = new BusquedaAStar(escenario);
    }
    
    private Ruta buscarRutaCorta(Ruta ruta1, Ruta ruta2)
    {
        Ruta menor = ruta1;
        if(menor == null) menor = ruta2;
        if(ruta2 != null && (menor.calidad > ruta2.calidad))        
        {
            System.out.println(menor.calidad);
            System.out.println(ruta2.calidad);            
            menor = ruta2;
        }         
        return menor;
    }
    
     private Ruta buscarRutaLarga(Ruta ruta1, Ruta ruta2)
    {
        Ruta menor = ruta1;
        if(menor == null) menor = ruta2;
        //if(ruta2 != null && (menor.calidad > ruta2.calidad))
        if(ruta2 != null && (menor.calidad < ruta2.calidad))
        {
            System.out.println(menor.calidad);
            System.out.println(ruta2.calidad);            
            menor = ruta2;
        }         
        return menor;
    }
    
    @Override
    public void run() 
    {
         ArrayList<Celda> recompensas = escenario.obtenerRecompensas();
        Ruta ruta = null;
        if(recompensas.size() > 0)
        {
            for(Celda celda: recompensas)
            {
                Ruta aux = inteligencia.buscar(posicionX, posicionY, celda.i, celda.j);
                //System.out.println("celda"+celda.i+" "+celda.j);
                //System.out.println(aux);
                ruta = buscarRutaCorta(ruta, aux);                  
            }
        }
        else
        {           
            Ruta aux;
            
            if(posicionX != Constantes.NUMERO_CELDAS_ANCHO - 1)
            {
                aux = inteligencia.buscar(posicionX, posicionY, Constantes.NUMERO_CELDAS_ANCHO-2, posicionY);
                ruta = buscarRutaLarga(ruta, aux);
            }
            
            if(posicionX != 0)
            {
                aux = inteligencia.buscar(posicionX, posicionY, 1, posicionY);
                ruta = buscarRutaLarga(ruta, aux);
            }
            
            if(posicionY != Constantes.NUMERO_CELDAS_LARGO-1)
            {
                aux = inteligencia.buscar(posicionX, posicionY, posicionX, Constantes.NUMERO_CELDAS_LARGO-2);
                ruta = buscarRutaLarga(ruta, aux);
            }
            
            if(posicionY-1 != 0)
            {
                aux = inteligencia.buscar(posicionX, posicionY, posicionX, 1);
                ruta = buscarRutaLarga(ruta, aux);            
            }
            
            aux = inteligencia.buscar(posicionX, posicionY, Constantes.NUMERO_CELDAS_ANCHO/2, Constantes.NUMERO_CELDAS_LARGO/2);
            ruta = buscarRutaLarga(ruta, aux);
        }
        System.out.println(ruta);        
        
        if(ruta != null && ruta.pasos.size() > 0)
        {
            if(escenario.jugador.energia <= 0)
            {
               escenario.dondeSeDibuja.cancel();
            }
            switch(ruta.pasos.get(ruta.pasos.size()-1))
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