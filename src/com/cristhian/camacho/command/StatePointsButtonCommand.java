package com.cristhian.camacho.command;

import java.awt.geom.Point2D;
import java.util.Vector;

import com.cristhian.camacho.Paneles_fractales.Patron_inicial.Panel_patron_inicial;
import com.cristhian.camacho.command.Command;

public class StatePointsButtonCommand implements Command {

	Panel_patron_inicial panel_patron_inicial;
	
	boolean borrar_puntos_valorOriginal;
	boolean mas_puntos_valorOriginal;
	boolean mover_puntos_valorOriginal;
	boolean borrar_todo_valorOriginal;
	
	boolean borrar_puntos_valorNuevo;
	boolean mas_puntos_valorNuevo;
	boolean mover_puntos_valorNuevo;
	boolean borrar_todo_valorNuevo;
	
	Vector v_puntos_anterior;
	//Point2D pointAnterior; 
	
	public StatePointsButtonCommand(Panel_patron_inicial ppi,
									 boolean borrar_puntos_valorNuevo,
									 boolean mas_puntos_valorNuevo,
									 boolean mover_puntos_valorNuevo,
									 boolean borrar_todo_valorNuevo)
	{
		panel_patron_inicial = ppi;

		this.borrar_puntos_valorNuevo = borrar_puntos_valorNuevo;
		this.mas_puntos_valorNuevo = mas_puntos_valorNuevo;
		this.mover_puntos_valorNuevo = mover_puntos_valorNuevo;
		this.borrar_todo_valorNuevo = borrar_todo_valorNuevo;
		
		borrar_puntos_valorOriginal = panel_patron_inicial.panel_de_dibujo.borrar_puntos;
		mas_puntos_valorOriginal = panel_patron_inicial.panel_de_dibujo.mas_puntos;
		mover_puntos_valorOriginal = panel_patron_inicial.panel_de_dibujo.mover_puntos;
		borrar_todo_valorOriginal = panel_patron_inicial.panel_de_dibujo.borrar_todo;
	} 
	
	public void execute() {
		
		panel_patron_inicial.panel_de_dibujo.borrar_puntos=borrar_puntos_valorNuevo;
		panel_patron_inicial.panel_de_dibujo.mas_puntos=mas_puntos_valorNuevo;
		panel_patron_inicial.panel_de_dibujo.mover_puntos=mover_puntos_valorNuevo;
		panel_patron_inicial.panel_de_dibujo.borrar_todo=borrar_todo_valorNuevo;
		
		if(borrar_todo_valorNuevo)
		{
			v_puntos_anterior = (Vector)panel_patron_inicial.panel_de_dibujo.v_puntos.clone();
			panel_patron_inicial.dibujar_punto(0, 0);
		}
		
		if(borrar_puntos_valorNuevo)
		{
			//pointAnterior = 
		}	
	}

	public void undo() {
		
		panel_patron_inicial.panel_de_dibujo.borrar_puntos=borrar_puntos_valorOriginal;
		panel_patron_inicial.panel_de_dibujo.mas_puntos=mas_puntos_valorOriginal;
		panel_patron_inicial.panel_de_dibujo.mover_puntos=mover_puntos_valorOriginal;
		panel_patron_inicial.panel_de_dibujo.borrar_todo=borrar_todo_valorOriginal;
		
		if(borrar_todo_valorNuevo)
		{
			panel_patron_inicial.panel_de_dibujo.requestFocus();
			//panel_patron_inicial.panel_de_dibujo.repaint();
			panel_patron_inicial.panel_de_dibujo.v_puntos=v_puntos_anterior;
			panel_patron_inicial.panel_de_dibujo.repaint();
		}
	}

	public void redo() {
		
		panel_patron_inicial.panel_de_dibujo.borrar_puntos=borrar_puntos_valorNuevo;
		panel_patron_inicial.panel_de_dibujo.mas_puntos=mas_puntos_valorNuevo;
		panel_patron_inicial.panel_de_dibujo.mover_puntos=mover_puntos_valorNuevo;
		panel_patron_inicial.panel_de_dibujo.borrar_todo=borrar_todo_valorNuevo;
		
		if(borrar_todo_valorNuevo)
		{
			v_puntos_anterior = (Vector)panel_patron_inicial.panel_de_dibujo.v_puntos.clone();
			panel_patron_inicial.dibujar_punto(0, 0);
		}
	}

}
