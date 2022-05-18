package com.cc.fractal2d_editor.Paneles_fractales.Patron_de_dibujo;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Panel_de_dibujo_resultado extends JPanel{

	Image backgroundImage;
	BufferedImage backgroundBufferedImage;
	
	//color de las lineas
	Color color_lineas=Color.BLACK;
	Color color_fondo=Color.WHITE;

	public void paint(Graphics g)
	{
		Graphics2D g2d = (Graphics2D)g;
		//g2d.setBackground(java.awt.Color.WHITE);
		g2d.clearRect(0,0,getWidth(), getHeight());

g.setColor(color_fondo);
g.fillRect(0, 0, getWidth(), getHeight());

		if(backgroundImage!=null)
		{
			System.out.println("backgroundImage"+backgroundImage);
			g2d.drawImage(backgroundImage, 0, 0, null);
		}

		pintarPanelSizeEnLaEsquinaInferiorDerecha(g);
	}

	public void pintarPanelSizeEnLaEsquinaInferiorDerecha(Graphics g)
	{
		FontMetrics fm=g.getFontMetrics();
		String panelSize = "("+(int)getSize().getWidth()
				+","
				+(int)getSize().getHeight()+")";

		int textWidth = fm.stringWidth(panelSize);
		///// to fill a rectagle background
		Graphics2D g2=(Graphics2D)g;
		g2.setPaint(Color.GRAY);
		g2.fillRect((int)(getSize().getWidth()-textWidth),
				(int)(getSize().getHeight()-5-fm.getHeight()),
				textWidth,
				fm.getHeight());
		/////
		g2.setPaint(Color.WHITE);
		g2.drawString(panelSize,
				(float)getSize().getWidth()-textWidth,
				(float)getSize().getHeight()-10);
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
	public Color getColor_lineas()
	{
		return color_lineas;
		//getGraphics().setColor(color1);
	}
	public void setColor_fondo(Color color1)
	{
		color_fondo = color1;

		//Color previousColor = getGraphics().getColor();
		//getGraphics().setColor(color_fondo);
		//getGraphics().fillRect(0, 0, getWidth(), getHeight());
		//getGraphics().setColor(previousColor);
		//getGraphics().setColor(color1);
		this.repaint();
	}
	public Color getColor_fondo()
	{
		return color_fondo;
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
