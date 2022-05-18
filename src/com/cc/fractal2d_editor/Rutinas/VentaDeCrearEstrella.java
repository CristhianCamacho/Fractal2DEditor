package com.cc.fractal2d_editor.Rutinas;

import com.cc.fractal2d_editor.Paneles_fractales.Elementos_UI;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentaDeCrearEstrella extends JFrame{

		public static VentaDeCrearEstrella instance;
		
		Elementos_UI elementosUI;
		
		JComboBox jcb_TipoDeEstrella;
		String TIPO1="tipo1";
		String TIPO2="tipo2";
		JComboBox jcb_NroDePuntas;
		JComboBox jcb_lado;
		JComboBox jcb_salto;

		JComboBox jcb_sentido_de_agregado;
		
		private VentaDeCrearEstrella()
		{
			super("VentaDeCrearEstrella");
			initialize();
		}
		
		public static VentaDeCrearEstrella getInstance()
		{
			if(instance == null)
			{
				instance = new VentaDeCrearEstrella();
			}
			
			return instance;
		}
		
		private void initialize()
		{
			setLayout( new BoxLayout( this.getContentPane(), BoxLayout.Y_AXIS));
			
			JPanel panelTipoEstrella = crearPanelDeTypoEstrella();
			add(panelTipoEstrella);
			
			JPanel panelNroDePuntas = crearPanelNroDePuntas();
			add(panelNroDePuntas);
			
			JPanel panelLongitudLado = crearLongitudLado();
			add(panelLongitudLado);
			
			JPanel panelSalto = crearSalto();
			add(panelSalto);

			JPanel panelSentidoDeAgregado = crearPanelSentidoDeAgregado();
			add(panelSentidoDeAgregado);

			JPanel panelCalcular = crearPanelCalcular();
			add(panelCalcular);
			
			darTamanio();
			
			this.setVisible(true);
		}
		
		private JPanel crearPanelDeTypoEstrella()
		{
			JPanel result = new JPanel();
			result.setLayout(new BorderLayout());
			TitledBorder titleNombreModelo;
			titleNombreModelo = BorderFactory.createTitledBorder("Tipo De Estrella");
			result.setBorder(titleNombreModelo);
			result.setLayout(new BorderLayout());
			
			String elementos[]={TIPO1, TIPO2};
			
			jcb_TipoDeEstrella = new JComboBox(elementos);
			//jcb_TipoDeEstrella.setSelectedIndex(elementosUI.panel_de_dibujo.jcb_nivel.getSelectedIndex());
			jcb_TipoDeEstrella.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent arg0) {
						String sItem = (String) jcb_TipoDeEstrella.getSelectedItem();
					}
				}	
			);
			result.add(jcb_TipoDeEstrella);
			
			return result;
		}
		
		private JPanel crearPanelNroDePuntas()
		{
			JPanel result = new JPanel();
			result.setLayout(new BorderLayout());
			TitledBorder titleNombreModelo;
			titleNombreModelo = BorderFactory.createTitledBorder("Nro De Puntas ");
			result.setBorder(titleNombreModelo);
			result.setLayout(new BorderLayout());
			
			int max = 200;
			String elementos[]=new String[max];
			for(int i=0; i<elementos.length ; i++ )
				elementos[i]=""+(2*i+1);
			
			jcb_NroDePuntas = new JComboBox(elementos);
			jcb_NroDePuntas.setSelectedIndex(3);
			jcb_NroDePuntas.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent arg0) {
						String sItem = (String) jcb_NroDePuntas.getSelectedItem();
						
						int max = (int)((Integer.parseInt((String)jcb_NroDePuntas.getSelectedItem()))/2);
						String elementos[]=new String[max];
						for(int i=0; i<elementos.length ; i++ )
						{
							elementos[i]=""+(i+1);
							//System.out.println(this.getClass()+" elementos["+i+"]"+elementos[i]);
						}	
						
						JPanel c = (JPanel) VentaDeCrearEstrella.this.jcb_salto.getParent();
						c.remove(jcb_salto);
						jcb_salto = new JComboBox(elementos);
						jcb_salto.setSelectedIndex(max-1);
						c.add(jcb_salto);
						
						c.updateUI();
					}
				}	
			);
			result.add(jcb_NroDePuntas);
			
			return result;
		}
		
		private JPanel crearLongitudLado()
		{
			JPanel result = new JPanel();
			result.setLayout(new BorderLayout());
			TitledBorder titleNombreModelo;
			titleNombreModelo = BorderFactory.createTitledBorder("Longitud Lado");
			result.setBorder(titleNombreModelo);
			result.setLayout(new BorderLayout());
			
			int max = 1000;
			String elementos[]=new String[max];
			for(int i=0; i<elementos.length ; i++ )
				elementos[i]=""+(i+1);
			
			jcb_lado = new JComboBox(elementos);
			jcb_lado.setSelectedIndex(99);
			jcb_lado.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent arg0) {
						String sItem = (String) jcb_lado.getSelectedItem();
					}
				}	
			);
			result.add(jcb_lado);
			
			return result;
		}
		
		private JPanel crearSalto()
		{
			JPanel result = new JPanel();
			result.setLayout(new BorderLayout());
			TitledBorder titleNombreModelo;
			titleNombreModelo = BorderFactory.createTitledBorder("Salto entre puntos");
			result.setBorder(titleNombreModelo);
			result.setLayout(new BorderLayout());
			
			int max = (int)(Integer.parseInt((String)jcb_NroDePuntas.getSelectedItem())/2);
			String elementos[]=new String[max];
			for(int i=0; i<elementos.length ; i++ )
				elementos[i]=""+(i+1);
			
			jcb_salto = new JComboBox(elementos);
			jcb_salto.setSelectedIndex(max-1);
			jcb_salto.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent arg0) {
						String sItem = (String) jcb_salto.getSelectedItem();
					}
				}	
			);
			result.add(jcb_salto);
			
			return result;
		}

		public JPanel crearPanelSentidoDeAgregado() {

			JPanel result = new JPanel();
			result.setLayout(new BorderLayout());
			TitledBorder titleSentidoDeAgregado;
			titleSentidoDeAgregado = BorderFactory.createTitledBorder("Sentido De Agregado");
			result.setBorder(titleSentidoDeAgregado);

			String elementos[]={"Horario","Antihorario"};

			jcb_sentido_de_agregado = new JComboBox(elementos);
			jcb_sentido_de_agregado.setSelectedIndex(0);
			jcb_sentido_de_agregado.addActionListener(new ActionListener()
										{
											public void actionPerformed(ActionEvent arg0) {
												String sItem = (String) jcb_salto.getSelectedItem();
											}
										}
			);
			result.add(jcb_sentido_de_agregado);

			return result;
		}
		
		private JPanel crearPanelCalcular()
		{
			JPanel result = new JPanel();
			
			result.setLayout(new BorderLayout());
			TitledBorder titleNombreModelo;
			titleNombreModelo = BorderFactory.createTitledBorder(Elementos_UI.CALCULAR);
			result.setBorder(titleNombreModelo);
			result.setLayout(new BorderLayout());
			
			JButton jb_botonCalcular =new JButton(Elementos_UI.CALCULAR);
			jb_botonCalcular.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent arg0) {
							instance.setVisible(false);

							// if current tab is not Patron Inicial, change to show it
							elementosUI.checkCurrentTabSelectedAndOpen(0);

							String tipoEstrella = (String) jcb_TipoDeEstrella.getSelectedItem();
							if(tipoEstrella.equalsIgnoreCase(TIPO1))
							{
								elementosUI.panel_patron_inicial.calcularEstrella(Integer.parseInt((String)jcb_NroDePuntas.getSelectedItem()),
																				  Integer.parseInt((String)jcb_lado.getSelectedItem()),
																				  Integer.parseInt((String)jcb_salto.getSelectedItem()),
																				  jcb_sentido_de_agregado.getSelectedIndex()
										);
							  
							}
							if(tipoEstrella.equalsIgnoreCase(TIPO2))
							{
								elementosUI.panel_patron_inicial.calcularEstrella1(Integer.parseInt((String)jcb_NroDePuntas.getSelectedItem()),
																				   Integer.parseInt((String)jcb_lado.getSelectedItem()),
																				   Integer.parseInt((String)jcb_salto.getSelectedItem()),
																					jcb_sentido_de_agregado.getSelectedIndex());
							  
							}
							
							//elementosUI.ejecutarRutina1;
						}
					}
			);
			result.add(jb_botonCalcular);
			
			return result;
		}
		
		private void darTamanio()
		{
			this.pack();
			
			Dimension dim=Toolkit.getDefaultToolkit().getScreenSize();
			
			Dimension ventanaDim = this.getSize();
			//Dimension ventanaDim = new Dimension((dim.width/4),(int)(dim.height/1.8));
			//setSize(ventanaDim);
			
			setLocation((int)( (dim.getWidth()-ventanaDim.getWidth())/2 ),
						(int)( (dim.getHeight()-ventanaDim.getHeight())/2 ));
		}
		
		public void setElementosUI( Elementos_UI elementosUI )
		{
			this.elementosUI = elementosUI;
		}
		
		public void mostrar()
		{
			this.setVisible(true);		
		}
}
