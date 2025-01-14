package com.cc.fractal2d_editor.Eventos_fractales;

import com.cc.fractal2d_editor.Paneles_fractales.Elementos_UI;
import com.cc.fractal2d_editor.Paneles_fractales.Patron_de_disenio.Panel_patron_disenio;
import com.cc.fractal2d_editor.command.ListaDeAcciones;
import com.cc.fractal2d_editor.command.MouseClickedCommand;
import com.cc.fractal2d_editor.command.MouseDraggedCommand;

import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;

public class Eventos_Panel_de_dibujo implements InternalFrameListener,MouseListener,MouseMotionListener,KeyListener,ListSelectionListener
{	
	//Elementos_UI elementos_ui;
	Panel_patron_disenio panel_patron;
	
	public Eventos_Panel_de_dibujo(Panel_patron_disenio panel_patron_inicial, Elementos_UI elem)
	{
		//elementos_ui=elem;
		this.panel_patron = panel_patron_inicial;
	}
	public void actionPerformed(ActionEvent e)
	{		
	}
	
	public void internalFrameDeactivated(InternalFrameEvent ife)
	{
		//System.out.println (ife);
	}
	
	public void internalFrameActivated(InternalFrameEvent ife)
	{
		//elementos_ui.pintar_puntos();
		//System.out.println (ife);
	}
	
	public void internalFrameDeiconified(InternalFrameEvent ife)
	{
		//System.out.println (ife);
	}
	
	public void internalFrameIconified(InternalFrameEvent ife)
	{
		//System.out.println (ife);
	}
	
	public void internalFrameClosed(InternalFrameEvent ife)
	{
		//System.out.println (ife);
	}
	
	public void internalFrameOpened(InternalFrameEvent ife)
	{
		//elementos_ui.pintar_puntos();
		panel_patron.pintar_puntos();
		//System.out.println (ife);
	}
	
	public void internalFrameOpening(InternalFrameEvent ife)
	{
		//elementos_ui.pintar_puntos();
		//System.out.println (ife);
	}
	
	public void internalFrameClosing(InternalFrameEvent ife)
	{
		//System.out.println (ife);
	}
	
	//MouseListener
	public void mouseClicked(MouseEvent e)
	{
		MouseClickedCommand mouseClickedCommand = new MouseClickedCommand(panel_patron,
																		  e.getPoint().getX(),
																		  e.getPoint().getY() );
		mouseClickedCommand.execute();
		ListaDeAcciones.getInstance().undoStackPush(mouseClickedCommand);
		
		//System.out.println("e.getSource()="+e.getSource());
		//System.out.println("e.getSource().hasFocus="+((JPanel)(e.getSource())).hasFocus());
		//((JPanel)(e.getSource())).requestFocus();
		//System.out.println("e.getSource().hasFocus="+((JPanel)(e.getSource())).hasFocus());
		/*
		panel_patron.dibujar_punto(e.getPoint().getX(),e.getPoint().getY());//si agregamos puntos
		panel_patron.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		*/
	}
	
	public void mouseEntered(MouseEvent e)
	{
		//System.out.println ( "mouse entro" );
	}
	public void mouseExited(MouseEvent e)
	{
		//System.out.println ( "mouse salio" );
	}
    public void mousePressed(MouseEvent e)
    {
    	panel_patron.set_punto_inicial_puntos(null);    	
    }
    public void mouseReleased(MouseEvent e)
    {
    	//System.out.println ( e.getSource() );
    }
    
	//MouseMotionListener
	public void mouseMoved(MouseEvent e)
	{
		panel_patron.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}
	
	public void mouseDragged(MouseEvent e)
	{
		//if ( panel_patron.panel_de_dibujo.getSelectedPoint() != null ) {

			MouseDraggedCommand mouseDraggedCommand = new MouseDraggedCommand(panel_patron,
					e.getPoint().getX(),
					e.getPoint().getY() );
			mouseDraggedCommand.execute();
			ListaDeAcciones.getInstance().undoStackPush(mouseDraggedCommand);

		//}

		/*
		//System.out.println("mouseDragged");
		if(panel_patron.esta_este_punto_en_la_lista(e.getX(), e.getY()))
		{
			panel_patron.mover_punto(e.getPoint().getX(), e.getPoint().getY());
		}
		else
		// esto es para hacer un drag de todos los puntos 
		{
			panel_patron.mover_todos_los_puntos(e.getPoint().getX(), e.getPoint().getY());
		}
		//System.out.println ( e.getSource() );
		panel_patron.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		*/
	}
	
	public void keyPressed(KeyEvent e)
	{
		System.out.println ( e.getSource() );
	}
	
	public void keyReleased(KeyEvent e){
	//	System.out.println ( e );
	}
	public void keyTyped(KeyEvent e){
		System.out.println ( e );
	}
	
	public void valueChanged(ListSelectionEvent e)
	{
		//System.out.println ( e.getSource() );
	}
	
}