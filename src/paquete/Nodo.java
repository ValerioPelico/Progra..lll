package paquete;

/**
 *
 * @author Giancarlo
 */
public class Nodo {
    
    private String String_ver_todo;
    private Nodo izquierda_nodo, derecha_nodo;

    public Nodo(String dato, Nodo izquierda, Nodo derecha) {
        this.String_ver_todo = dato;
        this.izquierda_nodo = izquierda;
        this.derecha_nodo = derecha;
    }

    public String getDato() {
        return String_ver_todo;
    }

    public void setDato(String dato) {
        this.String_ver_todo = dato;
    }

    public Nodo getIzq() {
        return izquierda_nodo;
    }

    public void setIzq(Nodo izq) {
        this.izquierda_nodo = izq;
    }

    public Nodo getDer() {
        return derecha_nodo;
    }

    public void setDer(Nodo der) {
        this.derecha_nodo = der;
    } 

}
