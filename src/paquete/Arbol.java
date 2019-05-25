
package paquete;

import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author Giancarlo
 */
public class Arbol {
    private Nodo Nodo_raiz;
    int int_cc=0;
    
    public Arbol()
    {
        this.Nodo_raiz = new Nodo("",null,null);
    }
    
    public boolean agregar_al_arbol(int n,ArrayList <String> T) {
        try{
            boolean bool_variable =true;
            this.Nodo_raiz.setDer(null);
            this.Nodo_raiz.setIzq(null);
            int iiiiii=0;
            int largo=0;
            int_cc=n;
            while(bool_variable){
                if(n==Math.pow(2, iiiiii)){
                    largo=iiiiii;
                    bool_variable=false;
                }
                else if(n>Math.pow(2, iiiiii) && n<Math.pow(2, iiiiii+1)){
                    largo=iiiiii+1;
                    bool_variable=false; 
                }
                else if(n==0){
                    largo=0;
                    bool_variable=false;
                }
                iiiiii++;
            }
            this.Nodo_raiz.setDato("");
            insertar(this.Nodo_raiz,largo,T);
            return true;
        }catch (Exception e) {
            return false;            
        }
    }
    
    public Nodo padre(Nodo x, String info) {
        if (x == null) {
            return null;
        }
        if ((x.getIzq() != null && x.getIzq().getDato().equals(info)) || (x.getDer() != null && x.getDer().getDato().equals(info))) {
            return (x);
        }
        Nodo y = padre(x.getIzq(), info);
        if (y == null) {
            return (padre(x.getDer(), info));
        } else {
            return (y);
        }
    }

    public void insertar(Nodo nodo, int L,ArrayList <String> T) {
        int int_ploc = 0;
        if(int_ploc==L){
            if(int_cc==0){
                nodo=null;                
            }else{
                int_cc--;
                nodo.setDato(T.get(int_cc));
            }           
        }else{
            Nodo w= new Nodo("",null,null);
            Nodo r= new Nodo("",null,null);
            nodo.setIzq(w);
            nodo.setDer(r);
            insertar(nodo.getIzq(),L-1,T);
            insertar(nodo.getDer(),L-1,T);
        }
    }
    public Nodo getRaiz() {
        return this.Nodo_raiz;
    }
    
    public void setRaiz(Nodo r) {
        this.Nodo_raiz = r;
    }
    
    public JPanel getDibujo() {
        return new grafica_del_arbol(this);
    }
}
