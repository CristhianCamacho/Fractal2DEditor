package com.cristhian.camacho.Paneles_fractales.Patron_de_dibujo;

import com.cristhian.camacho.Paneles_fractales.Elementos_UI;
import com.cristhian.camacho.Eventos_fractales.*;
import com.cristhian.camacho.Paneles_fractales.Elementos_UI;

import java.io.*;
import java.util.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.table.*;
import javax.swing.border.TitledBorder;

public class Panel_resultado extends JPanel {
	
	JInternalFrame panel_de_controles;		
	public Panel_de_dibujo_resultado panel_de_dibujo;
			
	public JTextArea jta_nombre_del_modelo;
	public JTextArea jta_estado;
	
	public JComboBox jcb_nivel;
	
	public JButton color_lineas;
	
	public JProgressBar progresoFractal;
	public JLabel tiempoRestanteFractal;
	
	public JProgressBar progresoRutina;
	public JLabel tiempoRestanteRutina;
		
	Elementos_UI elementos_UI;
	
	Tabla tabla;
	boolean datos_por_defecto=true;
	//String signos[];
	
	public Vector v_puntos=new Vector();
	
	public static String DETENER = "detener";
	public static String CALCULAR = "calcular";
	public static String CONTINUAR = "continuar";
	public static String CLEAR = "Clear Screen";
	public static String COLOR_LINEAS = "Color";
	public Panel_resultado(Elementos_UI elementos_ui)
	{
		elementos_UI=elementos_ui;		
		
		panel_grafico();
		setBackground(Color.white);	
	}
	
	public void panel_grafico()
	{
	createUI("Sin_nombre");	
	}
	
	public void createUI(String nombre_del_modelo)
	{	
	this.setLayout(new BorderLayout());
	
		
	//Dimension Size = elementos_UI.jf_principal.getSize();;
	
	//int ancho=Size.width;
	//int alto=Size.height;
		
	//setSize(ancho,alto);
	
	panel_de_controles=new JInternalFrame("panel_de_controles");
	//panel_de_controles.setLayout(new BorderLayout());
	panel_de_controles.setLayout( new BoxLayout( panel_de_controles.getContentPane(), BoxLayout.PAGE_AXIS));
	//panel_de_controles.setLayout(new FlowLayout(FlowLayout.LEFT));
	//panel_de_controles.setBounds(5,5,(int)(ancho/4-10),(int)(alto-100));
	panel_de_controles.setVisible(true);
			
	
	JPanel panelNombreModelo = new JPanel();
	TitledBorder titleNombreModelo;
	titleNombreModelo = BorderFactory.createTitledBorder("Nombre del modelo");
	panelNombreModelo.setBorder(titleNombreModelo);
	panelNombreModelo.setLayout(new BorderLayout());
	//JLabel L_0=new JLabel("Nombre del modelo");
	jta_nombre_del_modelo=new JTextArea(nombre_del_modelo);
	
	//panelNombreModelo.add(L_0, BorderLayout.WEST);
	panelNombreModelo.add(jta_nombre_del_modelo, BorderLayout.CENTER);
	panel_de_controles.add(panelNombreModelo);
	
	JPanel panelEstado = new JPanel();
	TitledBorder titleEstado;
	titleEstado = BorderFactory.createTitledBorder("Estado");
	panelEstado.setBorder(titleEstado);
	panelEstado.setLayout(new BorderLayout());
	//JLabel L_1=new JLabel("Estado");
	//L_1.setBounds(10,60,200,10);	
	jta_estado=new JTextArea("***");
	//jta_estado.setBounds(10,80,150,20);
	
	//panelEstado.add(L_1, BorderLayout.WEST);
	panelEstado.add(jta_estado, BorderLayout.CENTER);
	panel_de_controles.add(panelEstado);
		
////////////////////////////////////////////////////////////////////////////////
	
	String elementos[]=new String[8];
	for(int i=0; i<elementos.length ; i++ )
		elementos[i]=""+i;
	
	JPanel panelNivel = new JPanel();
	TitledBorder titleNivel;
	titleNivel = BorderFactory.createTitledBorder("nivel de recursividad");
	panelNivel.setBorder(titleNivel);
	panelNivel.setLayout(new BorderLayout());
	//JLabel L_2=new JLabel("nivel de recursividad");
	//L_2.setBounds(20,120,200,30);	
	jcb_nivel = new JComboBox(elementos);
	jcb_nivel.setSelectedIndex(0);
	jcb_nivel.addActionListener(new Eventos(null, elementos_UI));
	//jcb_nivel.setBounds(20,160,100,20);
	
	//panelNivel.add(L_2, BorderLayout.WEST);
	panelNivel.add(jcb_nivel, BorderLayout.CENTER);
	panel_de_controles.add(panelNivel);
	
////////////////////////////////////////////////////////////////////////////////	
	
	JPanel panelBotones = new JPanel();
	TitledBorder titleBotones;
	titleBotones = BorderFactory.createTitledBorder("Botones");
	panelBotones.setBorder(titleBotones);
	//panelBotones.setBackground(Color.RED);
	//panelBotones.setLayout(new BorderLayout());
	//panelBotones.setLayout( new BoxLayout( panelBotones, BoxLayout.Y_AXIS));
	panelBotones.setLayout( new GridLayout(4,1));
	
	
	JButton calcular=new JButton(CALCULAR);
	//calcular.setBounds(10,220,150,40);
	calcular.addMouseListener(new Eventos(null, elementos_UI));	
	panelBotones.add(calcular);
		
	JButton detener=new JButton(DETENER);
	//detener.setBounds(10,270,150,40);
	detener.addMouseListener(new Eventos(null, elementos_UI));	
	panelBotones.add(detener);
	
	JButton continuar=new JButton(CONTINUAR);
	//detener.setBounds(10,270,150,40);
	continuar.addMouseListener(new Eventos(null, elementos_UI));	
	//panelBotones.add(continuar, BorderLayout.SOUTH);
	
	JButton clear=new JButton(CLEAR);
	//detener.setBounds(10,270,150,40);
	clear.addMouseListener(new Eventos(null, elementos_UI));	
	panelBotones.add(clear);
	
	color_lineas=new JButton(COLOR_LINEAS);
	color_lineas.addMouseListener(new Eventos(null, elementos_UI));	
	panelBotones.add(color_lineas);
	
	panel_de_controles.add(panelBotones);
	
	JPanel panelProgreso = new JPanel();
	TitledBorder titleProgreso;
	titleProgreso = BorderFactory.createTitledBorder("progreso fractal");
	panelProgreso.setBorder(titleProgreso);
	panelProgreso.setLayout( new GridLayout(2,1));
	
	progresoFractal = new JProgressBar();
	progresoFractal.setMinimum(0); 
	progresoFractal.setMaximum(100); 
	progresoFractal.setStringPainted(true); 
	panelProgreso.add(progresoFractal);
	
	tiempoRestanteFractal = new JLabel("");
	panelProgreso.add(tiempoRestanteFractal);
	
	panel_de_controles.add(panelProgreso);
	
	JPanel panelProgresoRutina = new JPanel();
	TitledBorder titleProgresoRutina;
	titleProgresoRutina = BorderFactory.createTitledBorder("progreso rutina");
	panelProgresoRutina.setBorder(titleProgresoRutina);
	panelProgresoRutina.setLayout( new GridLayout(2,1));
	
	progresoRutina = new JProgressBar();
	progresoRutina.setMinimum(0); 
	progresoRutina.setMaximum(100); 
	progresoRutina.setStringPainted(true); 
	panelProgresoRutina.add(progresoRutina);
	
	tiempoRestanteRutina = new JLabel("");
	panelProgresoRutina.add(tiempoRestanteRutina);
	
	panel_de_controles.add(panelProgresoRutina);
	
	//panel_de_dibujo=new JInternalFrame("panel_de_dibujo");
	panel_de_dibujo=new Panel_de_dibujo_resultado();
	//panel_de_dibujo.setLayout(null);
	//panel_de_dibujo.setBounds(5+(int)(ancho/4),5,(int)(ancho*3/4-40),(int)(alto-100) );
	//panel_de_dibujo.addMouseListener(new Eventos_I(elementos_UI));
	//panel_de_dibujo.addMouseMotionListener(new Eventos_I(elementos_UI));
	panel_de_dibujo.setBackground(Color.white);
	panel_de_dibujo.setVisible(true);
	
	////
	tabla=new Tabla(  this , 0 );//numero_de_filas
	////	
	
	JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
	splitPane.setLeftComponent(panel_de_controles);
	splitPane.setRightComponent(panel_de_dibujo);
	splitPane.setDividerLocation(Elementos_UI.splitDividerLoation);
	//	.setDividerLocation(0.2);
		
	//add(panel_de_controles);
	//add(panel_de_dibujo);
	this.add(splitPane, BorderLayout.CENTER);	
	}
	
	//public boolean mas_puntos=false;
	//public boolean mover_puntos=false;
	//public boolean borrar_puntos=false;
	//public boolean borrar_todo=false;
	
	//Point2D mSelectedPoint;
	//int i_mSelectedPoint;


	public void set_nombre_del_modelo( String s )
{
	jta_nombre_del_modelo.setText(s);		
}
	public String get_nombre_del_modelo()
{
	return jta_nombre_del_modelo.getText();		
}
	
/*
	public String[] get_signos()
{	
		return tabla.get_signos();		
}
*/

}

			