import java.util.TimerTask;
import javax.swing.JOptionPane;

public abstract class Jugador extends TimerTask implements Constantes {
    public int posicionX;
    public int posicionY;
    public static int energia=11;    
    public char JUGADOR_TIPO;
    public Escenario escenario;
    public TimerTask perdidaEnergia;
    
    public Jugador(Escenario escenario, char jugador_tipo){
        this.escenario = escenario;
        this.JUGADOR_TIPO = jugador_tipo;
        perdidaEnergia = new PerdidaEnergia(this);
    }
    
    public boolean puedeMoverse(int posicionX, int posicionY)
    {
        return (posicionX<NUMERO_CELDAS_ANCHO && posicionX>-1 && posicionY<NUMERO_CELDAS_LARGO && posicionY>-1 && escenario.celdas[posicionX][posicionY].tipo!= OBSTACULO && escenario.celdas[posicionX][posicionY].tipo!=ADVERSARIO);
    }
   
    public void moverArriba()
    {
        if(posicionY>-1 && puedeMoverse (posicionX, posicionY-1 ))
        {   
            if(escenario.esRecompensa(posicionX,posicionY-1)){
                 energia+=10;
             }
                if(escenario.celdas[posicionX][posicionY-1].tipo=='F'){
               escenario.celdas[posicionX][posicionY].tipo='V';
               escenario.celdas[posicionX][posicionY-1].tipo=JUGADOR_TIPO;
               escenario.dondeSeDibuja.repaint();
               Escenario.escenario.dondeSeDibuja.timerCrono.cancel();
               JOptionPane.showMessageDialog(null, "Has ganado", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
               System.exit(0);
           
           }
            
            escenario.celdas[posicionX][posicionY].tipo = 'V';
            escenario.celdas[posicionX][--posicionY].tipo = JUGADOR;
        }
    }
    
    public void moverAbajo(){
        if(posicionY<NUMERO_CELDAS_LARGO && puedeMoverse(posicionX,posicionY +1)){  
            if(escenario.esRecompensa(posicionX,posicionY+1)){
                 energia+=10;
             }
            
            if(escenario.celdas[posicionX][posicionY+1].tipo=='F'){
                escenario.celdas[posicionX][posicionY].tipo='V';
                escenario.celdas[posicionX][posicionY+1].tipo=JUGADOR_TIPO;
                escenario.dondeSeDibuja.repaint();
                Escenario.escenario.dondeSeDibuja.timerCrono.cancel();
                JOptionPane.showMessageDialog(null, "Has ganado", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                System.exit(0);
            
            
            }
            escenario.celdas[posicionX][posicionY].tipo='V';
            escenario.celdas[posicionX][++posicionY].tipo = JUGADOR_TIPO;
    
        }
    }
    
    public void moverIzquierda(){
        if(posicionX>0 && puedeMoverse(posicionX -1,posicionY)){
            if(escenario.esRecompensa(posicionX-1,posicionY)){
                 energia+=10;
             }
             if(escenario.celdas[posicionX-1][posicionY].tipo=='F'){
                escenario.celdas[posicionX][posicionY].tipo='V';
                escenario.celdas[posicionX-1][posicionY].tipo=JUGADOR_TIPO;
                escenario.dondeSeDibuja.repaint();
                Escenario.escenario.dondeSeDibuja.timerCrono.cancel();
                JOptionPane.showMessageDialog(null, "Has ganado", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                System.exit(0);
            
            }
             
            escenario.celdas[posicionX][posicionY].tipo='V';
            escenario.celdas[--posicionX][posicionY].tipo = JUGADOR_TIPO;
            
        }
    }
    
    public void moverDerecha(){
        if(posicionX< NUMERO_CELDAS_ANCHO && puedeMoverse(posicionX +1,posicionY)){
            if(escenario.esRecompensa(posicionX+1,posicionY)){
                 energia+=10;
             }
            if(escenario.celdas[posicionX+1][posicionY].tipo=='F'){
               escenario.celdas[posicionX][posicionY].tipo='V';
               escenario.celdas[posicionX+1][posicionY].tipo=JUGADOR_TIPO;
               escenario.dondeSeDibuja.repaint();
               Escenario.escenario.dondeSeDibuja.timerCrono.cancel();
               JOptionPane.showMessageDialog(null, "Has ganado", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
               System.exit(0);
           
           }
            escenario.celdas[posicionX][posicionY].tipo='V';
            escenario.celdas[++posicionX][posicionY].tipo = JUGADOR_TIPO;
   
        }
    }
}
