
package paquete;

  /**
 *
 * @author Giancarlo
 */

import java.awt.*;
import java.util.*;
import javax.swing.*;


public class grafica_del_arbol extends JPanel 
{
    private Arbol ARbol_mi_tree;
    private HashMap Mapa_hash_nodos = null;
    private HashMap Tamaño_subdirectorios = null;
    private boolean VAriable_implicada = true;
    private int int_para_2 = 20, child2child = 30;
    private Dimension vacia = new Dimension(0,0);
    private FontMetrics fuentes = null;
    
 
    public grafica_del_arbol(Arbol miArbol) 
    {
          this.ARbol_mi_tree = miArbol;
          this.setBackground(Color.WHITE);
          Mapa_hash_nodos = new HashMap();
          Tamaño_subdirectorios = new HashMap();
          VAriable_implicada = true;
          repaint();      
    }


    private void calcularPosiciones() 
    {
         Mapa_hash_nodos.clear();
         Tamaño_subdirectorios.clear();
         Nodo root = this.ARbol_mi_tree.getRaiz();
         if (root != null) 
         {
             calcularTamañoSubarbol(root);
             calcularPosicion(root, Integer.MAX_VALUE, Integer.MAX_VALUE, 0);
         }
    }
    
    private Dimension calcularTamañoSubarbol(Nodo n) 
    {
          if (n == null) 
              return new Dimension(0,0);
 
          Dimension ld = calcularTamañoSubarbol(n.getIzq());
          Dimension rd = calcularTamañoSubarbol(n.getDer());
          
          int h = fuentes.getHeight() + int_para_2 + Math.max(ld.height, rd.height);
          int w = ld.width + child2child + rd.width;
          
          Dimension d = new Dimension(w, h);
          Tamaño_subdirectorios.put(n, d);
          
          return d;
    }
    
    private void calcularPosicion(Nodo n, int left, int right, int top) 
    {
      if (n == null) 
          return;
      
      Dimension ld = (Dimension) Tamaño_subdirectorios.get(n.getIzq());
      if (ld == null) 
          ld = vacia;
      
      Dimension rd = (Dimension) Tamaño_subdirectorios.get(n.getDer());
      if (rd == null) 
          rd = vacia;
      
      int center = 0;
      
      if (right != Integer.MAX_VALUE)
          center = right - rd.width - child2child/2;
      else if (left != Integer.MAX_VALUE)
          center = left + ld.width + child2child/2;
      int width = fuentes.stringWidth(n.getDato()+"");
 
      Mapa_hash_nodos.put(n,new Rectangle(center - width/2 - 3, top, width + 6, fuentes.getHeight()));
      
      calcularPosicion(n.getIzq(), Integer.MAX_VALUE, center - child2child/2, top + fuentes.getHeight() + int_para_2);
      calcularPosicion(n.getDer(), center + child2child/2, Integer.MAX_VALUE, top + fuentes.getHeight() + int_para_2);
    }
    

    private void dibujarArbol(Graphics2D g, Nodo n, int puntox, int puntoy, int yoffs) 
    {
     if (n == null) 
         return;
     
     Rectangle r = (Rectangle) Mapa_hash_nodos.get(n);
     g.draw(r);
     g.drawString(n.getDato()+"", r.x + 3, r.y + yoffs);
   
     if (puntox != Integer.MAX_VALUE)
       
     g.drawLine(puntox, puntoy, (int)(r.x + r.width/2), r.y);
     
     dibujarArbol(g, n.getIzq(), (int)(r.x + r.width/2), r.y + r.height, yoffs);
     dibujarArbol(g, n.getDer(), (int)(r.x + r.width/2), r.y + r.height, yoffs);
     
   }
    


   public void paint(Graphics g) 
   {
         super.paint(g);
         fuentes = g.getFontMetrics();

         if (VAriable_implicada) 
         {
           calcularPosiciones();
           VAriable_implicada = false;
         }
         
         Graphics2D g2d = (Graphics2D) g;
         g2d.translate(getWidth() / 2, int_para_2);
         dibujarArbol(g2d, this.ARbol_mi_tree.getRaiz(), Integer.MAX_VALUE, Integer.MAX_VALUE, 
                  fuentes.getLeading() + fuentes.getAscent());
         fuentes = null;
   }
   
 }
