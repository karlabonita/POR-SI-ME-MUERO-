
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JComponent;


public class Escenario extends JComponent implements Constantes {
    
    public Celda[][]celdas;
    public Jugador jugador;
    public Lienzo dondeSeDibuja;
    public Adversario[] adversarios;     
    public static Escenario escenario;
        
    public Escenario(Lienzo lienzo){
        escenario = this;
        dondeSeDibuja=lienzo;
        celdas = new Celda[NUMERO_CELDAS_ANCHO][NUMERO_CELDAS_LARGO];
        //inicializar array
        for(int i=0; i<NUMERO_CELDAS_ANCHO; i++)
                 for(int j=0; j< NUMERO_CELDAS_LARGO;j++)
                celdas[i][j] = new Celda(i, j,'V');
        
        for (int i = 0; i < NUMERO_CELDAS_ANCHO; i++) {
            for (int j = 0; j < NUMERO_CELDAS_LARGO; j++) {
                if (i == 0 || j == 0) {
                    celdas[i][j].esPared();
                }
                if (i == NUMERO_CELDAS_ANCHO - 1 || j == NUMERO_CELDAS_LARGO - 1) {
                    celdas[i][j].esPared();
                }
            }
        }
      
        
        celdas[4][5].esRecompensa(); //2,7
        celdas[9][7].esRecompensa();//2,11
        celdas[4][12].esRecompensa();//5,13
        celdas[9][18].esRecompensa();//24,0
        //celdas[6][2].esRecompensa();  //LINEA PARA DETENER 6,2 
        
   
        
        
        celdas[9][1].esPared();
        celdas[2][2].esPared();
        celdas[3][2].esPared();
        celdas[5][2].esPared();
        celdas[6][2].esPared();
        celdas[7][2].esPared();
        celdas[9][2].esPared();
        celdas[11][2].esPared();
        celdas[12][2].esPared();
        celdas[13][2].esPared();
        celdas[15][2].esPared();
        celdas[16][2].esPared();
        celdas[2][4].esPared();
        celdas[3][4].esPared();
        celdas[5][4].esPared();
        celdas[7][4].esPared();
        celdas[8][4].esPared();
        celdas[9][4].esPared();
        celdas[10][4].esPared();
        celdas[11][4].esPared();
        celdas[13][4].esPared();
        celdas[15][4].esPared();
        celdas[16][4].esPared();
        celdas[5][5].esPared();
        celdas[9][5].esPared();
        celdas[13][5].esPared();
        celdas[0][6].esPared();
        celdas[1][6].esPared();
        celdas[2][6].esPared();
        celdas[3][6].esPared();
        celdas[5][6].esPared();
        celdas[6][6].esPared();
        celdas[7][6].esPared();
        celdas[9][6].esPared();
        celdas[11][6].esPared();
        celdas[12][6].esPared();
        celdas[13][6].esPared();
        celdas[15][6].esPared();
        celdas[16][6].esPared();
        celdas[17][6].esPared();
        celdas[0][7].esPared();
        celdas[1][7].esPared();
        celdas[2][7].esPared();
        celdas[3][7].esPared();
        celdas[5][7].esPared();
        celdas[13][7].esPared();
        celdas[15][7].esPared();
        celdas[16][7].esPared();
        celdas[17][7].esPared();
        celdas[7][8].esPared();
        celdas[11][8].esPared();
        celdas[5][9].esPared();
        celdas[7][9].esPared();
        celdas[8][9].esPared();
        celdas[9][9].esPared();
        celdas[10][9].esPared();
        celdas[11][9].esPared();
        celdas[13][9].esPared();
        celdas[0][10].esPared();
        celdas[1][10].esPared();
        celdas[2][10].esPared();
        celdas[3][10].esPared();
        celdas[5][10].esPared();
        celdas[13][10].esPared();
        celdas[15][10].esPared();
        celdas[16][10].esPared();
        celdas[17][10].esPared();
        celdas[0][11].esPared();
        celdas[1][11].esPared();
        celdas[2][11].esPared();
        celdas[3][11].esPared();
        celdas[5][11].esPared();
        celdas[6][11].esPared();
        celdas[7][11].esPared();
        celdas[9][11].esPared();
        celdas[11][11].esPared();
        celdas[12][11].esPared();
        celdas[13][11].esPared();
        celdas[15][11].esPared();
        celdas[16][11].esPared();
        celdas[17][11].esPared();
        celdas[9][12].esPared();
        celdas[2][13].esPared();
        celdas[3][13].esPared();
        celdas[5][13].esPared();
        celdas[6][13].esPared();
        celdas[7][13].esPared();
        celdas[9][13].esPared();
        celdas[11][13].esPared();
        celdas[12][13].esPared();
        celdas[13][13].esPared();
        celdas[15][13].esPared();
        celdas[16][13].esPared();
        celdas[3][14].esPared();
        celdas[15][14].esPared();
        celdas[1][15].esPared();
        celdas[3][15].esPared();
        celdas[5][15].esPared();
        celdas[7][15].esPared();
        celdas[8][15].esPared();
        celdas[9][15].esPared();
        celdas[10][15].esPared();
        celdas[11][15].esPared();
        celdas[13][15].esPared();
        celdas[15][15].esPared();
        celdas[17][15].esPared();
        celdas[5][16].esPared();
        celdas[9][16].esPared();
        celdas[13][16].esPared();
        celdas[2][17].esPared();
        celdas[3][17].esPared();
        celdas[4][17].esPared();
        celdas[5][17].esPared();
        celdas[6][17].esPared();
        celdas[7][17].esPared();
        celdas[9][17].esPared();
        celdas[11][17].esPared();
        celdas[12][17].esPared();
        celdas[13][17].esPared();
        celdas[14][17].esPared();
        celdas[15][17].esPared();
        celdas[16][17].esPared();
        
        //destino
        
       // celdas[24][19].esFinal();
       
        jugador = new JugadorInformado(this);        
        //jugador = new JugadorAnchura(this);
        
        adversarios = new Adversario[3];
        
        adversarios[0] = new Adversario(this,8,8); 
        
        adversarios[1] = new Adversario(this,9,8); 
        
        adversarios[2] = new Adversario(this,10,8);  
        
        
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
