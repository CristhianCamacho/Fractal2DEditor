package com.cc.fractal2d_editor.Rutinas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import com.cc.fractal2d_editor.Paneles_fractales.Elementos_UI;

public class VentanaDeCrearRutina extends JFrame{

	public static VentanaDeCrearRutina instance;
	
	Elementos_UI elementosUI;
	
	public String ALEATORIO = "Aleatorio";
	public String GRADIENTE = "Gradiente";
	public String FIJO = "Fijo";
	public String GRADIENTE_ALEATORIO = "Gradiente Aleatorio";
	
	JComboBox jcb_tipoDeDibujoDeColores;
	
	//JButton jb_botonIni;
	//JButton jb_botonFin;
	JButton[] jb_botonesColoresGradientes = new JButton[2];
	Color[] coloresGradientes = {Color.WHITE, Color.BLACK};
	JPanel p_botonesColoresGradiente;
	//Color colorInicial=Color.WHITE;
	//Color colorFinal=Color.BLACK;
	
	Color[] coloresAleatorios = {Color.WHITE,
								 Color.BLUE,
								 Color.CYAN,
								 Color.GREEN,
								 Color.MAGENTA,
								 Color.ORANGE,
								 Color.PINK,
								 Color.RED,
								 Color.YELLOW,
								 Color.BLACK};
	
	JComboBox jcb_numeroDeGradientes;
	JComboBox jcb_numeroDeLineas;
	
	//JComboBox jcb_nroDeIteraciones;
	JComboBox jcb_nivelDeRecursividad;
	JComboBox jcb_porcentajeIniZoom;
	JComboBox jcb_porcentajeFinZoom;

	JComboBox jcb_porcentajeIniRotacion;
	JComboBox jcb_porcentajeFinRotacion;

	private VentanaDeCrearRutina()
	{
		super("Ventana para Crear Rutina");
		//initialize();
	}
	
	public static VentanaDeCrearRutina getInstance()
	{
		if(instance == null)
		{
			instance = new VentanaDeCrearRutina();
		}
		
		return instance;
	}
	
	private void initialize()
	{
		setLayout( new BoxLayout( this.getContentPane(), BoxLayout.Y_AXIS));
		
		//JPanel panelNroDeIteraciones = crearPanelNroDeIteraciones();
		
		JPanel panelColores = crearPanelDeColores();
		add(panelColores);
		
		//add(panelNroDeIteraciones);
		
		JPanel panelNivelDeRecursividad = crearPanelNivelDeRecursividad();
		add(panelNivelDeRecursividad);
		
		JPanel panelPorcentajeZoom = crearPanelPorcentajeZoom();
		add(panelPorcentajeZoom);
		
		JPanel panelPorcentajeRotacion = crearPanelPorcentajeRotacion();
		add(panelPorcentajeRotacion);

		JPanel panelCalcular = crearPanelCalcular();
		add(panelCalcular);
		
		darTamanio();
		
		this.setVisible(true);
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
	
	private JPanel crearPanelDeColores()
	{
		JPanel result = new JPanel();
		result.setLayout(new BorderLayout());
		TitledBorder titleNombreModelo;
		titleNombreModelo = BorderFactory.createTitledBorder("Seleccione los colores");
		result.setBorder(titleNombreModelo);

		JPanel contenedor = new JPanel();
		contenedor.setLayout( new BoxLayout( contenedor, BoxLayout.Y_AXIS));
		
		String elementos[]={ALEATORIO, GRADIENTE, GRADIENTE_ALEATORIO, FIJO};
		jcb_tipoDeDibujoDeColores = new JComboBox(elementos);
		jcb_tipoDeDibujoDeColores.setSelectedIndex(1);
		jcb_tipoDeDibujoDeColores.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent arg0) {
					
					String sItem = (String) jcb_tipoDeDibujoDeColores.getSelectedItem();
					if(sItem.equalsIgnoreCase(ALEATORIO))
					{
						p_botonesColoresGradiente.setVisible(false);
						jcb_numeroDeGradientes.setVisible(false);
						jcb_numeroDeLineas.setVisible(false);
					}
					else
					if(sItem.equalsIgnoreCase(GRADIENTE))
					{
						p_botonesColoresGradiente.setVisible(true);
						jcb_numeroDeGradientes.setVisible(true);
						jcb_numeroDeLineas.setVisible(false);
					}
					else
					if(sItem.equalsIgnoreCase(GRADIENTE_ALEATORIO))
					{
						p_botonesColoresGradiente.setVisible(true);
						jcb_numeroDeGradientes.setVisible(true);
						jcb_numeroDeLineas.setVisible(false);
						
						updatePanelBotonesColorGradienteAleatorio();
					}
					else
					if(sItem.equalsIgnoreCase(FIJO))
					{
						p_botonesColoresGradiente.setVisible(true);
						jcb_numeroDeGradientes.setVisible(true);
						jcb_numeroDeLineas.setVisible(true);

						instance.pack();
					}
				}
			}	
		);
		contenedor.add(jcb_tipoDeDibujoDeColores);
		
		p_botonesColoresGradiente = new JPanel();
		p_botonesColoresGradiente.setLayout( new GridLayout(1, 2));
		
		
		jb_botonesColoresGradientes[0] = new JButton(""+0);
		jb_botonesColoresGradientes[0].setBackground(coloresGradientes[0]);
		jb_botonesColoresGradientes[0].setForeground(getColorOpuesto(coloresGradientes[0]));
		jb_botonesColoresGradientes[0].addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0) {
				coloresGradientes[0] = JColorChooser.showDialog( null, "Seleccione un color", coloresGradientes[0] );
				jb_botonesColoresGradientes[0].setBackground(coloresGradientes[0]);
				jb_botonesColoresGradientes[0].setForeground(getColorOpuesto(coloresGradientes[0]));
			}
		}
		);
	p_botonesColoresGradiente.add(jb_botonesColoresGradientes[0]);
	
	jb_botonesColoresGradientes[1] = new JButton(""+1);
	jb_botonesColoresGradientes[1].setBackground(coloresGradientes[1]);
	jb_botonesColoresGradientes[1].setForeground(getColorOpuesto(coloresGradientes[1]));
	jb_botonesColoresGradientes[1].addActionListener(new ActionListener()
	{
		public void actionPerformed(ActionEvent arg0) {
			coloresGradientes[1] = JColorChooser.showDialog( null, "Seleccione un color", coloresGradientes[1] );
			jb_botonesColoresGradientes[1].setBackground(coloresGradientes[1]);
			jb_botonesColoresGradientes[1].setForeground(getColorOpuesto(coloresGradientes[1]));
		}
	}
	);
	p_botonesColoresGradiente.add(jb_botonesColoresGradientes[1]);
		
		
	contenedor.add(p_botonesColoresGradiente);
		/*
		jb_botonIni = new JButton("color inicial");
		jb_botonIni.setBackground(colorInicial);
		jb_botonIni.setForeground(getColorOpuesto(colorInicial));
		jb_botonIni.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent arg0) {
					colorInicial = JColorChooser.showDialog( null, "Seleccione un color", colorInicial );
					jb_botonIni.setBackground(colorInicial);
					jb_botonIni.setForeground(getColorOpuesto(colorInicial));
				}
			}
		);
		contenedor.add(jb_botonIni);
		
		jb_botonFin = new JButton("color final");
		jb_botonFin.setBackground(colorFinal);
		jb_botonFin.setForeground(getColorOpuesto(colorFinal));
		jb_botonFin.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent arg0) {
					colorFinal = JColorChooser.showDialog( null, "Seleccione un color", colorFinal );
					jb_botonFin.setBackground(colorFinal);
					jb_botonFin.setForeground(getColorOpuesto(colorFinal));
				}
			}
		);
		contenedor.add(jb_botonFin);
		*/
		//int a = elementosUI.panel_patron_inicial.js_VAL_MAX;
		//int b = Integer.parseInt((String) jcb_nroDeIteraciones.getSelectedItem());
		int n = 50;//(int)(a/b);
		elementos=new String[n];////String sItem = ;
		for(int i=0; i<elementos.length ; i++ )
			elementos[i]=""+(i+1);
		
		JPanel panelNroGradientes = new JPanel();
		panelNroGradientes.setLayout( new BoxLayout( panelNroGradientes, BoxLayout.X_AXIS));
		JLabel labelGradientes = new JLabel("Numero de gradientes");
		panelNroGradientes.add(labelGradientes);
		jcb_numeroDeGradientes = new JComboBox(elementos);
		jcb_numeroDeGradientes.setSelectedItem(elementos[0]);
		jcb_numeroDeGradientes.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent arg0) {
					String sItem = (String) jcb_tipoDeDibujoDeColores.getSelectedItem();
					if(sItem.equalsIgnoreCase(GRADIENTE))
					{
						updatePanelBotonesColorGradiente();
					}
					else
					if( sItem.equalsIgnoreCase(GRADIENTE_ALEATORIO) )
					{
						String s_value = (String)jcb_numeroDeGradientes.getSelectedItem();
						int nroBotones = Integer.parseInt(s_value) + 1;
						
						//p_botonesColoresGradiente.removeAll();		
						//p_botonesColoresGradiente.setLayout(new GridLayout(1,nroBotones));
						
						jb_botonesColoresGradientes = new JButton[nroBotones];
						coloresGradientes = new Color[nroBotones];
						
						for(int i=0;i<nroBotones;i++)
						{
							jb_botonesColoresGradientes[i] = new JButton(""+i);
						}	
						
						updatePanelBotonesColorGradienteAleatorio();
					}
					else
					if( sItem.equalsIgnoreCase(FIJO) )
					{
						String s_value = (String)jcb_numeroDeGradientes.getSelectedItem();
						int nroBotones = Integer.parseInt(s_value) + 1;

						//p_botonesColoresGradiente.removeAll();
						//p_botonesColoresGradiente.setLayout(new GridLayout(1,nroBotones));

						//JButton[] tmpBotones = jb_botonesColoresGradientes;

						jb_botonesColoresGradientes = new JButton[nroBotones];
						coloresGradientes = new Color[nroBotones];

						for(int i=0;i<nroBotones;i++)
						{
							jb_botonesColoresGradientes[i] = new JButton(""+i);
						}

						updatePanelBotonesColorGradienteAleatorio();
					}
				}
			}	
		);
		panelNroGradientes.add(jcb_numeroDeGradientes);
		contenedor.add(panelNroGradientes);

		n = 500;//(int)(a/b);
		elementos=new String[n];////String sItem = ;
		for(int i=0; i<elementos.length ; i++ )
			elementos[i]=""+(i+1);

		JPanel panelNroLineas = new JPanel();
		panelNroLineas.setLayout( new BoxLayout( panelNroLineas, BoxLayout.X_AXIS));
		JLabel labelLineas = new JLabel("Numero de Lineas");
		panelNroLineas.add(labelLineas);
		jcb_numeroDeLineas = new JComboBox(elementos);
		jcb_numeroDeLineas.setVisible(false);
		jcb_numeroDeLineas.setSelectedItem(elementos[0]);
		jcb_numeroDeLineas.addActionListener(new ActionListener()
												 {
													 public void actionPerformed(ActionEvent arg0) {
													 	/*
														 String sItem = (String) jcb_tipoDeDibujoDeColores.getSelectedItem();
														 if(sItem.equalsIgnoreCase(GRADIENTE))
														 {
															 updatePanelBotonesColorGradiente();
														 }
														 else
														 if(sItem.equalsIgnoreCase(GRADIENTE_ALEATORIO))
														 {
															 String s_value = (String)jcb_numeroDeGradientes.getSelectedItem();
															 int nroBotones = Integer.parseInt(s_value) + 1;

															 //p_botonesColoresGradiente.removeAll();
															 //p_botonesColoresGradiente.setLayout(new GridLayout(1,nroBotones));

															 jb_botonesColoresGradientes = new JButton[nroBotones];
															 coloresGradientes = new Color[nroBotones];

															 for(int i=0;i<nroBotones;i++)
															 {
																 jb_botonesColoresGradientes[i] = new JButton(""+i);
															 }

															 updatePanelBotonesColorGradienteAleatorio();

														 }*/
													 }
												 }
		);
		panelNroLineas.add(jcb_numeroDeLineas);
		contenedor.add(panelNroLineas);
		
		result.add(contenedor);
		
		return result;
	}
	
	private Color getColorOpuesto( Color color1 )
	{
		Color colorOpuesto = new Color((int)(255-color1.getRed()),
									   (int)(255-color1.getGreen()),
									   (int)(255-color1.getBlue()));
		
		return colorOpuesto;
	}
	/*
	private JPanel crearPanelNroDeIteraciones()
	{
		JPanel result = new JPanel();
		result.setLayout(new BorderLayout());
		TitledBorder titleNombreModelo;
		titleNombreModelo = BorderFactory.createTitledBorder("Numero de Iteraciones de zoom");
		result.setBorder(titleNombreModelo);
		result.setLayout(new BorderLayout());
		
		String elementos[]=new String[100];
		for(int i=0; i<elementos.length ; i++ )
			elementos[i]=""+(i+1);
		
		jcb_nroDeIteraciones = new JComboBox(elementos);
		jcb_nroDeIteraciones.setSelectedIndex(49);
		jcb_nroDeIteraciones.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent arg0) {
					String sItem = (String) jcb_nroDeIteraciones.getSelectedItem();
				}
			}	
		);
		result.add(jcb_nroDeIteraciones);
		
		return result;
	}
	*/
	private JPanel crearPanelNivelDeRecursividad()
	{
		JPanel result = new JPanel();
		result.setLayout(new BorderLayout());
		TitledBorder titleNombreModelo;
		titleNombreModelo = BorderFactory.createTitledBorder("Nivel de Recursividad ");
		result.setBorder(titleNombreModelo);

		int nivelDeR = elementosUI.panel_de_dibujo.jcb_nivel.getMaximumRowCount();
		String elementos[]=new String[nivelDeR];
		for(int i=0; i<elementos.length ; i++ )
			elementos[i]=""+(i);
		
		jcb_nivelDeRecursividad = new JComboBox(elementos);
		jcb_nivelDeRecursividad.setSelectedIndex(elementosUI.panel_de_dibujo.jcb_nivel.getSelectedIndex());
		jcb_nivelDeRecursividad.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent arg0) {
					String sItem = (String) jcb_nivelDeRecursividad.getSelectedItem();
				}
			}	
		);
		result.add(jcb_nivelDeRecursividad);
		
		return result;
	}
	
	private JPanel crearPanelPorcentajeZoom()
	{
		JPanel result = new JPanel();
		result.setLayout(new BoxLayout( result, BoxLayout.X_AXIS));
		TitledBorder titleNombreModelo;
		titleNombreModelo = BorderFactory.createTitledBorder("porcentaje inicial-final de zoom");
		result.setBorder(titleNombreModelo);

		int nivelDeZ = elementosUI.panel_patron_inicial.js_VAL_MAX;
		String elementos[]=new String[nivelDeZ];//js_POS_INI js_VAL_MAX
		for(int i=0; i<elementos.length ; i++ )
			elementos[i]=""+(i+1);
		
		jcb_porcentajeIniZoom = new JComboBox(elementos);
		try{
			int posIni = elementosUI.panel_patron_inicial.js_zoom.getMinimum();//elementosUI.panel_patron_inicial.js_zoom.getValue()-1;
			jcb_porcentajeIniZoom.setSelectedItem(elementos[posIni>0? posIni: 0]);//elementosUI.panel_patron_inicial.js_zoom.getValue();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		jcb_porcentajeIniZoom.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent arg0) {
					String sItem = (String) jcb_porcentajeIniZoom.getSelectedItem();
				}
			}	
		);
		result.add(jcb_porcentajeIniZoom);

		int nivelDeZ1 = elementosUI.panel_patron_inicial.js_VAL_MAX;
		String elementos1[]=new String[nivelDeZ1];//js_POS_INI js_VAL_MAX
		for(int i=0; i<elementos1.length ; i++ )
			elementos1[i]=""+(i+1);

		jcb_porcentajeFinZoom = new JComboBox(elementos1);
		try {
			jcb_porcentajeFinZoom.setSelectedItem(elementos1[elementosUI.panel_patron_inicial.js_zoom.getValue()-1]);
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
		jcb_porcentajeFinZoom.addActionListener(new ActionListener()
												{
													public void actionPerformed(ActionEvent arg0) {
														String sItem = (String) jcb_porcentajeFinZoom.getSelectedItem();
													}
												}
		);
		result.add(jcb_porcentajeFinZoom);
		
		return result;
	}

	private JPanel crearPanelPorcentajeRotacion()
	{
		JPanel result = new JPanel();
		result.setLayout(new BoxLayout( result, BoxLayout.X_AXIS));
		TitledBorder titleIniRotacion;
		titleIniRotacion = BorderFactory.createTitledBorder("porcentaje inicial-final de rotacion");
		result.setBorder(titleIniRotacion);

		int nivelDeZ1 = elementosUI.panel_patron_inicial.js_VAL_MAX_ROTACION+1;
		String elementos1[]=new String[nivelDeZ1];
		for(int i=0; i<elementos1.length ; i++ )
			elementos1[i]=""+(i);

		jcb_porcentajeIniRotacion = new JComboBox(elementos1);
		try{
			int posIni = elementosUI.panel_patron_inicial.js_rotar.getValue()-1;
			jcb_porcentajeIniRotacion.setSelectedItem(elementos1[posIni>0? posIni: 0]);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		jcb_porcentajeIniRotacion.addActionListener(new ActionListener()
												{
													public void actionPerformed(ActionEvent arg0) {
														String sItem = (String) jcb_porcentajeIniRotacion.getSelectedItem();
													}
												}
		);
		result.add(jcb_porcentajeIniRotacion);

		int nivelDeZ = elementosUI.panel_patron_inicial.js_VAL_MAX_ROTACION+1;//elementosUI.panel_de_dibujo.jcb_nivel.getMaximumRowCount();
		String elementos[]=new String[nivelDeZ];//js_POS_INI js_VAL_MAX
		for(int i=0; i<elementos.length ; i++ )
			elementos[i]=""+(i);

		jcb_porcentajeFinRotacion = new JComboBox(elementos);
		//jcb_porcentajeFinZoom.setSelectedItem(elementos[2*elementosUI.panel_patron_inicial.js_zoom.getValue()-1]);//elementosUI.panel_patron_inicial.js_zoom.getValue();
		jcb_porcentajeFinRotacion.setSelectedItem(elementos[elementosUI.panel_patron_inicial.js_rotar.getValue()]);
		jcb_porcentajeFinRotacion.addActionListener(new ActionListener()
													{
														public void actionPerformed(ActionEvent arg0) {
															String sItem = (String) jcb_porcentajeFinRotacion.getSelectedItem();
														}
													}
		);
		result.add(jcb_porcentajeFinRotacion);

		jcb_porcentajeIniRotacion.setSelectedIndex(elementosUI.panel_patron_inicial.js_rotar.getValue());
		jcb_porcentajeFinRotacion.setSelectedIndex(elementosUI.panel_patron_inicial.js_rotar.getValue());

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

						// if current tab is not panel de dibujo, change to show it
						elementosUI.checkCurrentTabSelectedAndOpen(2);

						elementosUI.setCalculandoFractales(false);
						
						elementosUI.ejecutarRutina1( ((String)jcb_tipoDeDibujoDeColores.getSelectedItem()) // GRADIENTE, FIJO, GRADIENTE_ALEATORIO
								                     ,
								coloresGradientes,
								Integer.parseInt((String)jcb_numeroDeLineas.getSelectedItem()),
								//Integer.parseInt((String)jcb_nroDeIteraciones.getSelectedItem()),
								coloresGradientes.length,//Integer.parseInt((String)jcb_numeroDeGradientes.getSelectedItem()+1),//int nroDeGradientes,
								Integer.parseInt((String)jcb_nivelDeRecursividad.getSelectedItem()),//int nivelDeRecursividad,
								Integer.parseInt((String)jcb_porcentajeIniZoom.getSelectedItem()),//int porcentajeZoomMin);
								Integer.parseInt((String)jcb_porcentajeFinZoom.getSelectedItem()),
								Double.parseDouble((String)jcb_porcentajeIniRotacion.getSelectedItem()),
								Double.parseDouble((String)jcb_porcentajeFinRotacion.getSelectedItem())
								);
					}
				}
		);
		result.add(jb_botonCalcular);
		
		return result;
	}
	
	public void updatePanelBotonesColorGradiente()
	{
		String s_value = (String)jcb_numeroDeGradientes.getSelectedItem();
		int nroBotones = Integer.parseInt(s_value) + 1;
		
		p_botonesColoresGradiente.removeAll();		
		p_botonesColoresGradiente.setLayout(new GridLayout(1,nroBotones));
		
		//// para ajustar el tamanio del arreglo de botones
		if(coloresGradientes.length<nroBotones)
		{
			Color[] c_tmp = new Color[nroBotones];
			int i;
			for(i=0;i<coloresGradientes.length;i++)
			{
				c_tmp[i]=coloresGradientes[i];
			}
			
			for(int j=i; j<nroBotones; j++)
			{
				c_tmp[j]=Color.WHITE;
			}
			
			coloresGradientes = c_tmp;
		}
		else
		{
			Color[] c_tmp = new Color[nroBotones];
			int i;
			for(i=0;i<nroBotones;i++)
			{
				c_tmp[i]=coloresGradientes[i];
			}
			
			coloresGradientes = c_tmp;
		}
		////
		
		//// para ajustar el tamanio de arreglo de botones colores gradientes
		p_botonesColoresGradiente.removeAll();
		jb_botonesColoresGradientes = new JButton[nroBotones];
		////
		
		for(int i=0;i<nroBotones;i++)
		{
			jb_botonesColoresGradientes[i] = new JButton(""+i);
			jb_botonesColoresGradientes[i].setBackground(coloresGradientes[i]);
			jb_botonesColoresGradientes[i].setForeground(getColorOpuesto(coloresGradientes[i]));
			
			final int i_ = i;
			jb_botonesColoresGradientes[i].addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent arg0) {
					coloresGradientes[i_] = JColorChooser.showDialog( null, "Seleccione un color", coloresGradientes[i_] );
					jb_botonesColoresGradientes[i_].setBackground(coloresGradientes[i_]);
					jb_botonesColoresGradientes[i_].setForeground(getColorOpuesto(coloresGradientes[i_]));
				}
			}
			);
			
			p_botonesColoresGradiente.add(jb_botonesColoresGradientes[i]);
			p_botonesColoresGradiente.updateUI();
		}
	}
	
	public void updatePanelBotonesColorGradienteAleatorio()
	{
		String s_value = (String)jcb_numeroDeGradientes.getSelectedItem();
		int nroBotones = Integer.parseInt(s_value) + 1;
		
		p_botonesColoresGradiente.removeAll();		
		p_botonesColoresGradiente.setLayout(new GridLayout(1,nroBotones));
				
		int nro = nroBotones;//jb_botonesColoresGradientes.length;
		
		for(int i=0; i<nro; i++)
		{
			coloresGradientes[i] = coloresAleatorios[(int)(Math.random()*coloresAleatorios.length)]; 
			
			// para no tener colores iguales adyacentes
			if(i>0)
			{
				if(coloresGradientes[i].getRGB() == coloresGradientes[(i-1)%nro].getRGB())
				{
					coloresGradientes[i] = coloresAleatorios[(int)(Math.random()*coloresAleatorios.length)]; 
				}
			}
			
			jb_botonesColoresGradientes[i] = new JButton(""+i);
			jb_botonesColoresGradientes[i].setBackground(coloresGradientes[i]);
			jb_botonesColoresGradientes[i].setForeground(getColorOpuesto(coloresGradientes[i]));
			
			final int i_ = i;
			jb_botonesColoresGradientes[i].addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent arg0) {
					coloresGradientes[i_] = JColorChooser.showDialog( null, "Seleccione un color", coloresGradientes[i_] );
					jb_botonesColoresGradientes[i_].setBackground(coloresGradientes[i_]);
					jb_botonesColoresGradientes[i_].setForeground(getColorOpuesto(coloresGradientes[i_]));
				}
			}
			);
			
			p_botonesColoresGradiente.add(jb_botonesColoresGradientes[i]);
			p_botonesColoresGradiente.updateUI();
		}
	}
	
	public void mostrar()
	{
		if(this.jcb_tipoDeDibujoDeColores==null)
		{
			initialize();
		}
		updateValues();
		this.setVisible(true);		
	}
	
	private void updateValues()
	{
		int nivel = elementosUI.panel_de_dibujo.jcb_nivel.getSelectedIndex();
		jcb_nivelDeRecursividad.setSelectedIndex(nivel);
		//int iniZoom = elementosUI.panel_patron_inicial.js_zoom.getMinimum();//elementosUI.panel_patron_inicial.js_zoom.getValue()-1;
		//jcb_porcentajeIniZoom.setSelectedIndex(iniZoom);

		jcb_porcentajeIniRotacion.setSelectedIndex(elementosUI.panel_patron_inicial.js_rotar.getValue());
		jcb_porcentajeFinRotacion.setSelectedIndex(elementosUI.panel_patron_inicial.js_rotar.getValue());
	}
	
	public void setElementosUI( Elementos_UI elementosUI )
	{
		this.elementosUI = elementosUI;
	}
	
	public static void main (String Arg[])
	{
		(new VentanaDeCrearRutina()).setVisible(true);	
	}	
}
