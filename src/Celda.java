import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JOptionPane;

public class Celda extends JComponent implements Constantes
{
    public static BufferedImage jugador, obstaculo, camino, adversario,cueva,comida;
    static
    {        
        try{
            jugador = ImageIO.read(new File("imagenes/jugador40.png"));
            obstaculo = ImageIO.read(new File("imagenes/obstaculo40.png"));
            camino = ImageIO.read(new File("imagenes/camino.jpg"));
            adversario = ImageIO.read(new File("imagenes/adversarioMedusa40.png"));
            comida = ImageIO.read(new File("imagenes/comida240.png"));
            cueva= ImageIO.read(new File("imagenes/cueva40.png"));
        }catch (IOException e){
            System.out.println(e.toString());
        }
    }
    public int i;
    public int j;
    public char tipo;
    
    //constructor 
    public Celda(int i, int j, char tipo)
    {
        this.i=i;
        this.j=j;
        this.tipo=tipo;   
    }
    
    public void esPared(){
        tipo='O';
    
    }
    
    public void esFinal(){
        tipo='F';
    
    }
    @Override
    public void update(Graphics g){        
        switch(tipo){
            case 'T':
                EventQueue.invokeLater(new Runnable(){
                @Override
                  public void run()
                  {
                    Escenario.escenario.dondeSeDibuja.timerCrono.cancel();
                    JOptionPane.showMessageDialog(Celda.this, "Perdiste");                    
                    //break;
                  }
            });
            case 'J':
                g.setColor(Color.BLACK);
                g.drawString(String.valueOf(Jugador.energia), i* PIXEL_CELDA + 10, j * PIXEL_CELDA - 10);
                //g.setColor(COLOR_JUGADOR);break;                
                g.drawImage(jugador, (i*PIXEL_CELDA), (j*PIXEL_CELDA), null);break;
            case 'I':
                g.setColor(Color.BLACK);
                
                g.drawImage(jugador, (i*PIXEL_CELDA), (j*PIXEL_CELDA), null);
                               
                if(j==0)
                    g.drawString(String.valueOf(Jugador.energia), i* PIXEL_CELDA+5, j * PIXEL_CELDA+15);
                else
                    g.drawString(String.valueOf(Jugador.energia), i* PIXEL_CELDA + 10, j * PIXEL_CELDA - 10);
                break;

                //g.setColor(COLOR_JUGADOR);
            case 'O':
                //g.setColor(COLOR_OBSTACULO);            
                g.drawImage(obstaculo, (i*PIXEL_CELDA), (j*PIXEL_CELDA), null);break;
            case 'V':
                //g.setColor(COLOR_CAMINO);break;
                g.drawImage(camino, (i*PIXEL_CELDA), (j*PIXEL_CELDA), this); break;
               
            case 'A':
                //g.setColor(COLOR_ADVERSARIO);
                 g.drawImage(adversario, (i*PIXEL_CELDA), (j*PIXEL_CELDA), this); break;
            case 'F':
                //g.setColor(COLOR_FINAL);
                g.drawImage(cueva, (i*PIXEL_CELDA), (j*PIXEL_CELDA), this); break;
            case 'R':
                //g.setColor(Color.PINK);break;
                 g.drawImage(comida, (i*PIXEL_CELDA), (j*PIXEL_CELDA), this); break;
       
        }
        //g.fillRect(i+(i*PIXEL_CELDA),j+(j*PIXEL_CELDA), PIXEL_CELDA, PIXEL_CELDA);
    
        
    }
    //metodo para dibujar casilla
    @Override
    public void paintComponent(Graphics g)
    {
        update(g);
    }

    void esRecompensa() {
        tipo='R';
    }
}
