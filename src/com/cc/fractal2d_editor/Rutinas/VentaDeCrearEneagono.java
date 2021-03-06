package com.cc.fractal2d_editor.Rutinas;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import com.cc.fractal2d_editor.Paneles_fractales.Elementos_UI;

public class VentaDeCrearEneagono extends JFrame{
	public static VentaDeCrearEneagono instance;
	
	Elementos_UI elementosUI;
	
	JComboBox jcb_TipoDeEneagono;
	String TIPO1="tipo1";
	String TIPO2="tipo2";
	JComboBox jcb_NroDePuntas;
	JComboBox jcb_lado;
	JComboBox jcb_salto;
	
	private VentaDeCrearEneagono()
	{
		super("VentaDeEneagono");
		initialize();
	}
	
	public static VentaDeCrearEneagono getInstance()
	{
		if(instance == null)
		{
			instance = new VentaDeCrearEneagono();
		}
		
		return instance;
	}
	
	private void initialize()
	{
		setLayout( new BoxLayout( this.getContentPane(), BoxLayout.Y_AXIS));
		
		JPanel panelDeTypoEeneagono = crearPanelDeTypoEeneagono();
		add(panelDeTypoEeneagono);
		
		JPanel panelNroDePuntas = crearPanelNroDePuntas();
		add(panelNroDePuntas);
		
		JPanel panelLongitudLado = crearLongitudLado();
		add(panelLongitudLado);
		
		JPanel panelSalto = crearSalto();
		add(panelSalto); 
		
		JPanel panelCalcular = crearPanelCalcular();
		add(panelCalcular);
		
		darTamanio();
		
		this.setVisible(true);
	}
	
	private JPanel crearPanelDeTypoEeneagono()
	{
		JPanel result = new JPanel();
		result.setLayout(new BorderLayout());
		TitledBorder titleNombreModelo;
		titleNombreModelo = BorderFactory.createTitledBorder("Tipo De Eneagono para el salto");
		result.setBorder(titleNombreModelo);
		result.setLayout(new BorderLayout());
		
		String elementos[]={TIPO1, TIPO2};
		
		jcb_TipoDeEneagono = new JComboBox(elementos);
		jcb_TipoDeEneagono.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent arg0) {
					String sItem = (String) jcb_TipoDeEneagono.getSelectedItem();
				}
			}	
		);
		jcb_TipoDeEneagono.setSelectedIndex(1);
		result.add(jcb_TipoDeEneagono);
		
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
			elementos[i]=""+(i+1);
		
		jcb_NroDePuntas = new JComboBox(elementos);
		jcb_NroDePuntas.setSelectedIndex(6-1);
		jcb_NroDePuntas.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent arg0) {
						String sItem = (String) jcb_NroDePuntas.getSelectedItem();
						
						int max = getMaxSalto();
						String elementos[]=new String[max];
						for(int i=0; i<elementos.length ; i++ )
						{
							elementos[i]=""+(i+1);
							//System.out.println(this.getClass()+" elementos["+i+"]"+elementos[i]);
						}	
						
						JPanel c = (JPanel) VentaDeCrearEneagono.this.jcb_salto.getParent();
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
	
	private JPanel crearSalto()
	{
		JPanel result = new JPanel();
		result.setLayout(new BorderLayout());
		TitledBorder titleNombreModelo;
		titleNombreModelo = BorderFactory.createTitledBorder("Salto entre puntos");
		result.setBorder(titleNombreModelo);
		result.setLayout(new BorderLayout());
		
		int max = getMaxSalto();
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
	
	private JPanel crearPanelCalcular()
	{
		JPanel result = new JPanel();
		
		result.setLayout(new BorderLayout());
		TitledBorder titleNombreModelo;
		titleNombreModelo = BorderFactory.createTitledBorder("Calcular");
		result.setBorder(titleNombreModelo);
		result.setLayout(new BorderLayout());
		
		JButton jb_botonCalcular =new JButton("Calcular");
		jb_botonCalcular.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent arg0) {
						instance.setVisible(false);
						
						String sLado = (String) jcb_lado.getSelectedItem();
						String sNroPuntas = (String) jcb_NroDePuntas.getSelectedItem();
						String sSalto = (String) jcb_salto.getSelectedItem();
						
						int lado = Integer.parseInt(sLado);
						int nroPuntas = Integer.parseInt(sNroPuntas); 
						int salto = Integer.parseInt(sSalto); 
						
						String tipoEstrella = (String) jcb_TipoDeEneagono.getSelectedItem();
						if(tipoEstrella.equalsIgnoreCase(TIPO1))
						{
							elementosUI.panel_patron_inicial.calcularEneagono(nroPuntas, lado, salto);
						}
						if(tipoEstrella.equalsIgnoreCase(TIPO2))
						{
							elementosUI.panel_patron_inicial.calcularEneagono1(nroPuntas, lado, salto);
						}
						
						
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
	
	private int getMaxSalto()
	{
		int iReturn = 1;
		
		int n = Integer.parseInt((String)jcb_NroDePuntas.getSelectedItem());
		
		if(n%2==1)
		{
			iReturn = (int)(n/2);
		}
		else
		{
			iReturn = (int)(n/2) - 1;
		}
		
		return iReturn;
	}
}
