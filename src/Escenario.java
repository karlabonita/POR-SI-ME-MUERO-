
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JComponent;


public class Escenario extends JComponent implements Constantes {
    
    public Celda[][]celdas;
    public Jugador jugador;
    public Lienzo dondeSeDibuja;
    public Adversario[] adversarios;     
        
    public Escenario(Lienzo lienzo){
    
        dondeSeDibuja=lienzo;
        celdas = new Celda[NUMERO_CELDAS_ANCHO][NUMERO_CELDAS_LARGO];
        //inicializar array
        for(int i=0; i<NUMERO_CELDAS_ANCHO; i++)
                 for(int j=0; j< NUMERO_CELDAS_LARGO;j++)
                celdas[i][j] = new Celda(i, j,'V');
        //celdas[2][7].esRecompensa();
        //celdas[2][11].esRecompensa();
        //celdas[5][13].esRecompensa();
        //celdas[10][13].esRecompensa();
        //celdas[6][2].esRecompensa();  //LINEA PARA DETENER 
        celdas[2][3].esPared();
        celdas[3][4].esPared();
        celdas[4][5].esPared();
        celdas[5][6].esPared();
        celdas[7][2].esPared();
        celdas[8][2].esPared();
        celdas[9][2].esPared();
        
        celdas[5][9].esPared();
        celdas[5][10].esPared();
        celdas[5][11].esPared();
        celdas[5][12].esPared();
        
        celdas[2][13].esPared();
        celdas[2][14].esPared();
        celdas[3][15].esPared();
        
        celdas[4][16].esPared();
        celdas[10][16].esPared();
        celdas[11][16].esPared();
        celdas[12][16].esPared();
        celdas[13][16].esPared();
        
        celdas[12][11].esPared();
        celdas[13][12].esPared();
        celdas[14][13].esPared();
        celdas[13][6].esPared();
        celdas[14][5].esPared();
        celdas[15][4].esPared();
        
        celdas[20][3].esPared();
        celdas[21][3].esPared();
        celdas[19][10].esPared();
        celdas[20][10].esPared();
        celdas[21][10].esPared();
        celdas[19][14].esPared();
        celdas[20][14].esPared();
        
        celdas[7][1].esPared();
        celdas[8][6].esPared();
        celdas[9][7].esPared();
        celdas[10][7].esPared();
        celdas[11][7].esPared();
        
        celdas[16][7].esPared();
        celdas[17][8].esPared();
        celdas[18][9].esPared();
                
        
        //destino
        
        celdas[24][19].esFinal();
        
        jugador = new JugadorInformado(this);        
        //jugador = new JugadorAnchura(this);
        
        adversarios = new Adversario[0];
        
        //adversarios[0] = new Adversario(this,10,10); 
        
        //adversarios[1] = new Adversario(this,18,4); 
        
        //adversarios[2] = new Adversario(this,11,5); 
        
    }   

    
    @Override
    public void update(Graphics g){
    for(int i=0; i<NUMERO_CELDAS_ANCHO; i++)
        {
            for(int j=0; j<NUMERO_CELDAS_LARGO;j++)
            {
                celdas[i][j].paintComponent(g);                
            }
    
    }
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        update(g);
     
        /*synchronized(messages){
        ArrayList<Message> delete = new ArrayList<Message>();
        for(Message m:messages)
        {
            g.setColor(m.color);
            g.drawString(m.message, m.x, m.y);
            m.currentTime = System.currentTimeMillis();
            if(m.currentTime - m.startTime > 200)
            {
                delete.add(m);
            }
        }
        messages.removeAll(delete);
        }*/
    }

    public boolean esRecompensa(int x, int y) 
    {
        return celdas[x][y].tipo == RECOMPENSA;        
    }
    
    
    public ArrayList<Celda> obtenerRecompensas()
    {
        ArrayList<Celda> recompensas = new ArrayList<Celda>();
        for(int i=0; i<NUMERO_CELDAS_ANCHO; i++)
        {
            for(int j=0; j<NUMERO_CELDAS_LARGO;j++)
            {
               if(esRecompensa(i,j)) recompensas.add(celdas[i][j]);
            }
        }        
        return recompensas;
    }
    
   /* public void drawDebug(String message, int x, int y) 
    {
        drawDebug(message, x, y, Color.BLACK);
    }*/

   /* public void drawDebug(String message, int x, int y, Color color) 
    {
        synchronized(messages)
                {
        Message m = new Message();
        m.x = x;
        m.y = y;
        m.message = message;
        m.startTime = System.currentTimeMillis();
        m.color = color;
        messages.add(m);
                }
    }
    
    public ArrayList<Message> messages = new ArrayList<Message>();
    private class Message
    {
        public int x, y;
        public String message;        
        public long startTime;
        public long currentTime;
        public Color color;
    }*/
   
}
