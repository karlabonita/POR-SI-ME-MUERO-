
import java.awt.Canvas;
import java.awt.Graphics;
import java.util.Timer;
import java.util.TimerTask;


public class Lienzo extends Canvas implements Constantes {    
    
    public Escenario escenario;
    public Timer lanzadorTareas;
    public Timer cronometro;
    public Timer timerCrono;
    public TimerTask perdidaEnergia;
    public TimerTask ttask;
    public int milseg=0,seg = 0,min = 0;
    public Lienzo(){        
        escenario = new Escenario(this);
        cronometro = new Timer();
        cronometro.scheduleAtFixedRate(escenario.jugador.perdidaEnergia,0,5000);

        lanzadorTareas = new Timer();
        timerCrono = new Timer();
        lanzadorTareas.scheduleAtFixedRate(escenario.jugador, 0, 1000);
        //lanzadorTareas.scheduleAtFixedRate(escenario.jugador.inteligencia, 0, 1000);


        for(int i=0; i < escenario.adversarios.length; i++)
        {
            if(escenario.adversarios[i] != null)
            {
                lanzadorTareas.scheduleAtFixedRate(escenario.adversarios[i], 0, 1000);
            }
        }
        
        TimerTask tt1 = new TimerTask(){
            @Override
            public void run() {
                milseg+=10;
                if(milseg==1000){
                    seg++;
                    milseg=0;
                }
                if(seg==60){
                    min++;
                    seg=0;
                }
                VentanaPrincipal.ventana.setTitle("Tiempo "+min+":"+seg+":"+milseg);
            }
        };
        timerCrono.scheduleAtFixedRate(tt1, 0, 10);
        
    }
    
    
    
    @Override
    public void update(Graphics g){
        escenario.paintComponent(g);
//        g.setColor(Color.BLACK);
//        g.drawString("Tiempo:"+min+":"+seg+":"+milseg, 10, 15);
        
    }
    @Override
    public void paint(Graphics g){
        update(g);
    
    }

    public void cancel() 
    {
        timerCrono.cancel();
        cronometro.cancel();
        lanzadorTareas.cancel();
    }
    
    
    
}
