package com.cc.fractal2d_editor.Eventos_fractales;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.cc.fractal2d_editor.command.ListaDeAcciones;

public class RedoUndoKeyListener implements KeyListener{
	
	int vk_anterior = 0; 
	 
	public void keyReleased(KeyEvent e)
	{}
	 
	public void keyPressed(KeyEvent e)
	{
		System.out.println("e.getKeyChar()="+e.getKeyChar());
	 
		if(e.getKeyCode() == e.VK_Z && vk_anterior == e.VK_CONTROL)
			ListaDeAcciones.getInstance().undo();
		 
		if(e.getKeyCode() == e.VK_Y && vk_anterior == e.VK_CONTROL)
			ListaDeAcciones.getInstance().redo();
		 
		if(vk_anterior != e.VK_CONTROL)
		 
		vk_anterior = e.getKeyCode();	
		 
		}
	 
	public void keyTyped(KeyEvent e)
	{}
}
