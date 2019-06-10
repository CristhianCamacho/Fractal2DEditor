package com.cc.fractal2d_editor.Eventos_fractales;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import com.cc.fractal2d_editor.command.ListaDeAcciones;
import com.cc.fractal2d_editor.Paneles_fractales.Elementos_UI;

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
			ListaDeAcciones.getInstance().redo();
		}	
		
		if(str.equalsIgnoreCase("Undo"))
		{
			ListaDeAcciones.getInstance().undo();
		}
	}
}
