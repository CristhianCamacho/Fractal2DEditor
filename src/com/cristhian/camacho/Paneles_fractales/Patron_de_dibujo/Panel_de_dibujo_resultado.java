package com.cristhian.camacho.Paneles_fractales.Patron_de_dibujo;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Stroke;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class Panel_de_dibujo_resultado extends JPanel{

	Image backgroundImage;
	BufferedImage backgroundBufferedImage;
	
	//color de las lineas
	Color color_lineas=Color.BLACK;

	public void paint(Graphics g)
	{
		Graphics2D g2d = (Graphics2D)g;
		//g2d.setBackground(java.awt.Color.WHITE);
		g2d.clearRect(0,0,getWidth(), getHeight());
		
		if(backgroundImage!=null)
		{
			System.out.println("backgroundImage"+backgroundImage);
			g2d.drawImage(backgroundImage, 0, 0, null);
		}
	}
	
	public Image getBackgroundImage() {
		return backgroundImage;
	}

	public void setBackgroundImage(Image backgroundImage) {
		this.backgroundImage = backgroundImage;
	}

	public BufferedImage getBackgroundBufferedImage() {
		return backgroundBufferedImage;
	}

	public void setBackgroundBufferedImage(BufferedImage backgroundBufferedImage) {
		this.backgroundBufferedImage = backgroundBufferedImage;
	}
	
	public void setColorLineas(Color color1)
	{
		color_lineas = color1;
		//getGraphics().setColor(color1);
	}
	public Color getColorLineas()
	{
		return color_lineas;
		//getGraphics().setColor(color1);
	}
	/*
	public void updateColorLineas()
	{
		getGraphics().setColor(color_lineas);
	}
	*/
	/*
	//// componente personalizado
	private int diametro = 10;
	
	// dibujar un �valo del di�metro especificado
	public void paintComponent( Graphics g )
	{
		g.fillOval( 10, 10, diametro, diametro );
		g.setColor(Color.RED);
		g.drawString("AAAAAAAA",200, 200);
		
		super.paintComponent( g );
	}
	// utilizado por el administrador de esquemas para determinar el tama�o preferido
	public Dimension getPreferredSize()
	{
		return new Dimension( 200, 200 );
	}
	
	// utilizado por el administrador de esquemas para determinar el tama�o m�nimo
	public Dimension getMinimumSize()
	{
		return getPreferredSize();
	}
	*/


}
