import java.util.TimerTask;

public class PerdidaEnergia extends TimerTask 
{
    private JugadorAnchura jugador;    
    public PerdidaEnergia(JugadorAnchura jugador) 
    {    
        this.jugador = jugador;
    }

    @Override
    public void run() 
    {
        if(jugador.energia > 0)
        {
            jugador.energia = jugador.energia - 5;
        }
    }
}
