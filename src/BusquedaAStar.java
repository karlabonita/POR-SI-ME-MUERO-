
import java.awt.Color;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;

public class BusquedaAStar implements Constantes {
    public Escenario escenario;
    public Queue<Estado> colaEstados;
    public ArrayList<Estado> historial;
    
    public Estado inicial;
    public Estado objetivo;
    public Estado temp;
    public boolean exito;
    
    public BusquedaAStar(Escenario escenario){
        this.escenario=escenario;
        exito=false;
    }
    
    //funcio de costo actual
    public double g(double costo, Estado proximo){
        return 10 + costo;        
    }
    
    // funcion heuristica
    public double h(Estado proximo) 
    {
        double parte1 = Math.pow(proximo.x - objetivo.x, 2);
        double parte2 = Math.pow(proximo.y - objetivo.y, 2);
        double calidad = Math.sqrt(parte1 + parte2);        
        for(Adversario adversario: escenario.adversarios)
        {       
            double parteA = Math.pow(proximo.x - adversario.posicionX,2);
            double parteB = Math.pow(proximo.y - adversario.posicionY,2);
            double valor = Math.sqrt(parteA + parteB);            
            if(valor<=1){
                calidad*=1000;
            }else
            if(valor<=2)
            {
                calidad*=6;
            }else
            if(valor<=3)
            {
                calidad*=4;
            }else
            if(valor<=4)
            {
                calidad*=2;
            }
        }
        
        return calidad;
    }
    
    public Ruta buscar(int x1, int y1, int x2, int y2){        
        exito = false;
        colaEstados = new PriorityQueue<>();
        historial=new ArrayList<>();
        
        inicial = new Estado(x1,y1,'N',null);
        objetivo=new Estado(x2,y2,'P',null);
        //calidad
        inicial.distancia(objetivo);
        
        colaEstados.add(inicial);
        historial.add(inicial);
        
        if(inicial.equals(objetivo)) exito =true;
        
        while(!colaEstados.isEmpty() && !exito){
            temp = colaEstados.poll();
//            escenario.drawDebug(""+(int)temp.calidad, temp.x*PIXEL_CELDA+temp.x+10, temp.y*PIXEL_CELDA+10+temp.y);
            //System.out.println(temp.calidad);
            //System.out.println(temp.toString());
            //colaEstados.remove(0);
            moverArriba(temp);
            moverAbajo(temp);
            moverIzquierda(temp);
            moverDerecha(temp);
        }
        System.out.println("************************");
        
        if(exito){            
        
            Ruta ruta = new Ruta();           
            ArrayList<Character> pasos = new ArrayList<>();
            ruta.pasos = pasos;
            Estado predecesor=objetivo;
            do{
                if(predecesor.oper != 'N')
                {
                    pasos.add(predecesor.oper);
                }
                ruta.calidad += predecesor.calidad;
//                escenario.drawDebug(""+(int)predecesor.calidad, predecesor.x*PIXEL_CELDA+predecesor.x+10, predecesor.y*PIXEL_CELDA+10+predecesor.y, Color.RED);
                predecesor=predecesor.predecesor;                
            }while(predecesor !=null);
            return ruta;
        }
        else
        {
          return null;
        }
    }
    
     private void moverDerecha(Estado e){
        
        if(e.x< NUMERO_CELDAS_ANCHO -1){
            if(escenario.celdas[e.x+1][e.y].tipo != 'O'){
            Estado derecha = new Estado(e.x+1, e.y,'R',e);            
            derecha.calidad = g(e.calidad, derecha) + h(derecha);
            if(!historial.contains(derecha)){
                colaEstados.add(derecha);
                historial.add(derecha);
                
                if(derecha.equals(objetivo)){
                    objetivo=derecha;
                    exito=true;
                
                }
            }
        }    
      }
    }
    
    private void moverIzquierda(Estado e){
        if(e.x > 0){
            if(escenario.celdas[e.x-1][e.y].tipo!='O'){
            Estado izquierda = new Estado(e.x-1,e.y,'L',e);
            izquierda.calidad = g(e.calidad, izquierda) + h(izquierda);
            if(!historial.contains(izquierda)){
                colaEstados.add(izquierda);
                historial.add(izquierda);
            if(izquierda.equals(objetivo)){
                objetivo = izquierda;
                exito=true;
            }
            
             }
           }
        }
    }
    
    private void moverAbajo(Estado e){
        if(e.y+1 < NUMERO_CELDAS_LARGO){
            if(escenario.celdas[e.x][e.y+1].tipo!='O'){
            Estado abajo = new Estado(e.x,e.y+1,'D',e);
            abajo.calidad=g(e.calidad,abajo)+ h(abajo);
            if(!historial.contains(abajo)){
                colaEstados.add(abajo);
                historial.add(abajo);
             if(abajo.equals(objetivo)){
                 objetivo=abajo;
                 exito=true;
             
               }
             }
            
            }
        }
    }
    
    private void moverArriba(Estado e){
        if(e.y>0){
            if(escenario.celdas[e.x][e.y-1].tipo!='O'){
            Estado arriba = new Estado(e.x,e.y-1,'U',e);
            arriba.calidad = g(e.calidad, arriba) + h(arriba);
            if(!historial.contains(arriba)){
                colaEstados.add(arriba);
                historial.add(arriba);
            if(arriba.equals(objetivo)){
                objetivo=arriba;
                exito=true;
 
             }
            }           
           }
        }   
    }
}