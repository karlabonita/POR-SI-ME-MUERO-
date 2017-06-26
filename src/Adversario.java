import java.util.ArrayList;
import java.util.TimerTask;


public class Adversario extends TimerTask implements Constantes 
{
    public int posicionX;
    public int posicionY;
    public Escenario escenario;
    public BusquedaAnchuraAdversario inteligencia;    
    private char tipoComido = CAMINO;
    public Adversario(Escenario escenario, int x, int y)
    {        
        posicionX=x;
        posicionY=y;
        this.escenario=escenario;        
        escenario.celdas[posicionX][posicionY].tipo = ADVERSARIO;
        
        
        inteligencia = new BusquedaAnchuraAdversario(escenario);    
    }
    
    public boolean puedeMoverse(int posicionX, int posicionY)
    {
       return (posicionX < NUMERO_CELDAS_ANCHO && posicionX >-1
               && posicionY <NUMERO_CELDAS_LARGO && posicionY >-1
               && escenario.celdas[posicionX][posicionY].tipo != OBSTACULO);
    }
    
    public void moverArriba()
    {        
        if(posicionY>-1 && puedeMoverse (posicionX, posicionY-1 ))
        {   
            if(escenario.jugador.posicionX == posicionX && escenario.jugador.posicionY == posicionY-1){
                escenario.celdas[posicionX][posicionY-1].tipo='T';                
                escenario.dondeSeDibuja.cancel();
            } else {
                escenario.celdas[posicionX][posicionY].tipo = tipoComido;
                tipoComido = escenario.celdas[posicionX][--posicionY].tipo;
                escenario.celdas[posicionX][posicionY].tipo='A';
            }
        }
    }
    
    public void moverAbajo()
    {           
        if(posicionY<NUMERO_CELDAS_LARGO && puedeMoverse(posicionX,posicionY +1))
        {       
            if(escenario.jugador.posicionX == posicionX && escenario.jugador.posicionY == posicionY+1){
                escenario.celdas[posicionX][posicionY+1].tipo='T';                
                escenario.dondeSeDibuja.cancel();
            } else {
                escenario.celdas[posicionX][posicionY].tipo= tipoComido;
                tipoComido = escenario.celdas[posicionX][++posicionY].tipo;
                escenario.celdas[posicionX][posicionY].tipo='A';
            }
    
        }
    }
    
    public void moverIzquierda()
    {     
        if(posicionX>0 && puedeMoverse(posicionX -1,posicionY))
        {          
            if(escenario.jugador.posicionX == posicionX-1 && escenario.jugador.posicionY == posicionY){
                escenario.celdas[posicionX-1][posicionY].tipo='T';                
                escenario.dondeSeDibuja.cancel();
            } else {
                escenario.celdas[posicionX][posicionY].tipo=tipoComido;
                tipoComido = escenario.celdas[--posicionX][posicionY].tipo;
                escenario.celdas[posicionX][posicionY].tipo='A';
            }
            
        }
    }
    
    public void moverDerecha()
    {     
        if(posicionX< NUMERO_CELDAS_ANCHO && puedeMoverse(posicionX +1,posicionY))
        {
            if(escenario.jugador.posicionX == posicionX+1 && escenario.jugador.posicionY == posicionY){
                escenario.celdas[posicionX+1][posicionY].tipo='T';                
                escenario.dondeSeDibuja.cancel();
            } else {
                escenario.celdas[posicionX][posicionY].tipo   = tipoComido;
                tipoComido = escenario.celdas[++posicionX][posicionY].tipo;
                escenario.celdas[posicionX][posicionY].tipo = 'A';
            }
        }
    }
    
    public void run()
    {
        ArrayList<Character> ruta = inteligencia.buscar(posicionX, posicionY, escenario.jugador.posicionX, escenario.jugador.posicionY);
        
        if(ruta != null && ruta.size() > 0)
        {
//            if(escenario.jugador.energia <= 0)
//            {
//                this.cancel();
//            }
            
            switch(ruta.get(ruta.size()-1))
            {            
                 case 'D': moverAbajo(); break;
                 case 'U': moverArriba(); break;
                 case 'R': moverDerecha(); break;
                 case 'L': moverIzquierda(); break;
            }
                        
            escenario.dondeSeDibuja.repaint();
        }
    }
    
}
