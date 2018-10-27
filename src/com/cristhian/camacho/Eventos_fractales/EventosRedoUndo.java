package com.cristhian.camacho.Eventos_fractales;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import com.cristhian.camacho.Paneles_fractales.Elementos_UI;
import com.cristhian.camacho.command.ListaDeAcciones;

public class EventosRedoUndo implements ActionListener{

	
	public EventosRedoUndo(Elementos_UI elementos_UI, JMenuItem redo, JMenuItem undo)
	{
		if(redo == null)
		{
			ListaDeAcciones.getInstance().setUndoMenu(undo);
		}
		if(undo == null)
		{
			ListaDeAcciones.getInstance().setRedoMenu(redo);
		}
		
	}

	public void actionPerformed(ActionEvent arg0) {
		
		JMenuItem jmi =	(JMenuItem) arg0.getSource();
		String str = jmi.getText();
		
		if(str.equalsIgnoreCase("Redo"))
		{
			com.cristhian.camacho.command.ListaDeAcciones.getInstance().redo();
		}	
		
		if(str.equalsIgnoreCase("Undo"))
		{
			com.cristhian.camacho.command.ListaDeAcciones.getInstance().undo();
		}
	}
}
