package com.cc.fractal2d_editor.Eventos_fractales;

import com.cc.fractal2d_editor.Paneles_fractales.Elementos_UI;
import com.cc.fractal2d_editor.Paneles_fractales.Patron_de_disenio.Panel_patron_disenio;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Evento_SeleccionDePuntos_Panel_de_dibujo extends MouseAdapter {

    Elementos_UI elementos_ui;
    Panel_patron_disenio panel_patron;

    public Evento_SeleccionDePuntos_Panel_de_dibujo(Panel_patron_disenio panel_patron_inicial, Elementos_UI elementos)
    {
        elementos_ui=elementos;
        this.panel_patron = panel_patron_inicial;
    }

    public void mousePressed(MouseEvent e)
    {
        setStartPoint(e.getX(), e.getY());
    }

    public void mouseDragged(MouseEvent e)
    {
        setEndPoint(e.getX(), e.getY());
        panel_patron.panel_de_dibujo.repaint();
    }

    public void mouseReleased(MouseEvent e)
    {
        setEndPoint(e.getX(), e.getY());
        panel_patron.panel_de_dibujo.seleccionarVariosPuntos();
        panel_patron.panel_de_dibujo.repaint();
    }

    public void setStartPoint(int x, int y)
    {
        panel_patron.panel_de_dibujo.x_selection_1 = x;
        panel_patron.panel_de_dibujo.y_selection_1 = y;
    }

    public void setEndPoint(int x, int y)
    {
        panel_patron.panel_de_dibujo.x_selection_2 = (x);
        panel_patron.panel_de_dibujo.y_selection_2 = (y);
    }


}
