package com.cc.fractal2d_editor.Eventos_fractales;

import com.cc.fractal2d_editor.Paneles_fractales.Elementos_UI;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class EventosKeyListenerMoverTodosLosPuntos implements KeyListener {

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println(" : keyTyped()="+ this.getClass() + e.getKeyChar());
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println(" : keyPressed()="+ this.getClass() + e.getKeyChar());

        if  (e.getKeyCode() == KeyEvent.VK_UP ||
                e.getKeyCode() == KeyEvent.VK_DOWN ||
                e.getKeyCode() == KeyEvent.VK_RIGHT ||
                e.getKeyCode() == KeyEvent.VK_LEFT
        ) {

            if(e.getSource() == Elementos_UI.instance.panel_patron_inicial.panel_de_dibujo ) {
                Elementos_UI.instance.panel_patron_inicial.mover_todos_los_puntos(e.getKeyCode());
            }
            if(e.getSource() == Elementos_UI.instance.panel_patron_recursivo.panel_de_dibujo) {
                Elementos_UI.instance.panel_patron_recursivo.mover_todos_los_puntos(e.getKeyCode());
            }

        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        //System.out.println(" : keyReleased()="+ this.getClass() + e.getKeyChar());
    }
}
