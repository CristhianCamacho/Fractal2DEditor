package com.cc.fractal2d_editor.Rutinas;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentListener;
import javax.swing.event.DocumentEvent;

import com.cc.fractal2d_editor.Paneles_fractales.Elementos_UI;

public class VentanaDeCrearRutina2 extends JFrame{

	public static VentanaDeCrearRutina2 instance;

	Elementos_UI elementosUI;

	public String DENTRO_DE_LA_FIGURA = "Dentro de la Figura";
	public String FUERA_DE_LA_FIGURA = "Fuera de la Figura";

	JTextField jtf_nroIteracionesMaxRutina2;

	JComboBox jcb_DentroFueraDeLaFigura;
	JCheckBox jc_dibujarTriangulos;
	JCheckBox jc_dibujarCircunferenciaInscrita;
	JPanel p_dibujarNoRectaDeEuler;
	JCheckBox jc_dibujarNoRectaDeEuler_puntos;
	JCheckBox jc_dibujarNoRectaDeEuler_linea;

	JComboBox jcb_Timer;

	JComboBox jcb_tipoDeCalculo;

	JComboBox jcb_showRatioOfMiddlePointInteger;
	JComboBox jcb_showRatioOfMiddlePointDecimal;
	JTextField jtf_showRatioOfMiddlePoint;

	JComboBox jcb_nroPuntosBaricentro;

	JComboBox jcb_coloresAletorioODefinidoPorElPanel;

	private VentanaDeCrearRutina2()
	{
		super("Ventana para Crear Rutina 2");
	}

	public static VentanaDeCrearRutina2 getInstance()
	{
		if(instance == null)
		{
			instance = new VentanaDeCrearRutina2();
		}

		return instance;
	}

	private void initialize()
	{
		setLayout( new BoxLayout( this.getContentPane(), BoxLayout.Y_AXIS));

		JPanel panelCheck = crearPanelDeCheck();
		add(panelCheck);

		JPanel panelCalcular = crearPanelCalcular();
		add(panelCalcular);

		darTamanio();

		//this.setVisible(true);
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

	private JPanel crearPanelDeCheck()
	{
		JPanel result = new JPanel();
		result.setLayout(new BorderLayout());
		TitledBorder titleNombreModelo;
		titleNombreModelo = BorderFactory.createTitledBorder("Seleccione la ubicacion inicial del punto aleatorio");
		result.setBorder(titleNombreModelo);
		result.setLayout(new BorderLayout());

		JPanel contenedor = new JPanel();
		contenedor.setLayout( new BoxLayout( contenedor, BoxLayout.Y_AXIS));

		jtf_nroIteracionesMaxRutina2 = new JTextField("20000");
		TitledBorder titlenroIteracionesMax;
		titlenroIteracionesMax = BorderFactory.createTitledBorder("Numero Maximo de Iteraciones");
		jtf_nroIteracionesMaxRutina2.setBorder(titlenroIteracionesMax);
		jtf_nroIteracionesMaxRutina2.setHorizontalAlignment(JTextField.RIGHT);
		jtf_nroIteracionesMaxRutina2.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				try {
					Elementos_UI.instance.nroIteracionesMaxRutina2 = Integer.parseInt(jtf_nroIteracionesMaxRutina2.getText());
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null,
							"Para el "+ "Numero Maximo de Iteraciones" + " ingrese valores enteros unicamente", "Error Message",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		contenedor.add(jtf_nroIteracionesMaxRutina2);

		String elementos[]={DENTRO_DE_LA_FIGURA, FUERA_DE_LA_FIGURA};
		jcb_DentroFueraDeLaFigura = new JComboBox(elementos);
		jcb_DentroFueraDeLaFigura.setSelectedIndex(0);
		jcb_DentroFueraDeLaFigura.addActionListener(new ActionListener()
													{
														public void actionPerformed(ActionEvent arg0) {

														}
													}
		);
		contenedor.add(jcb_DentroFueraDeLaFigura);

		jc_dibujarTriangulos = new JCheckBox("Dibujar Triangulos");
		jc_dibujarTriangulos.setSelected(false);
		jc_dibujarTriangulos.addActionListener(new ActionListener()
						   {
							   public void actionPerformed(ActionEvent arg0) {
									Elementos_UI.instance.dibujarTriangulos = jc_dibujarTriangulos.isSelected();
							   }
						   }
		);
		contenedor.add(jc_dibujarTriangulos);

		jc_dibujarCircunferenciaInscrita = new JCheckBox("Dibujar Circunferencia Inscrita");
		jc_dibujarCircunferenciaInscrita.setSelected(false);
		jc_dibujarCircunferenciaInscrita.addActionListener(new ActionListener()
											   {
												   public void actionPerformed(ActionEvent arg0) {
													   Elementos_UI.instance.dibujarCircunferenciaInscrita = jc_dibujarCircunferenciaInscrita.isSelected();
												   }
											   }
		);
		contenedor.add(jc_dibujarCircunferenciaInscrita);

		p_dibujarNoRectaDeEuler = new JPanel();
		p_dibujarNoRectaDeEuler.setLayout( new BoxLayout( p_dibujarNoRectaDeEuler, BoxLayout.X_AXIS));
		TitledBorder titleNoRectaDeEuler;
		titleNoRectaDeEuler = BorderFactory.createTitledBorder("Dibujar No Recta de Euler (Incentro-Baricentro), Puntos y/o lineas");
		p_dibujarNoRectaDeEuler.setBorder(titleNoRectaDeEuler);

		jc_dibujarNoRectaDeEuler_puntos = new JCheckBox("Dibujar Puntos, (Incentro-Baricentro)");
		jc_dibujarNoRectaDeEuler_puntos.setSelected(false);
		jc_dibujarNoRectaDeEuler_puntos.addActionListener(new ActionListener()
														   {
															   public void actionPerformed(ActionEvent arg0) {
																   Elementos_UI.instance.dibujarNoRectaDeEuler_puntos = jc_dibujarNoRectaDeEuler_puntos.isSelected();
															   }
														   }
		);

		p_dibujarNoRectaDeEuler.add(jc_dibujarNoRectaDeEuler_puntos);

		jc_dibujarNoRectaDeEuler_linea = new JCheckBox("Dibujar Linea, (Incentro-Baricentro)");
		jc_dibujarNoRectaDeEuler_linea.setSelected(false);
		jc_dibujarNoRectaDeEuler_linea.addActionListener(new ActionListener()
														  {
															  public void actionPerformed(ActionEvent arg0) {
																  Elementos_UI.instance.dibujarNoRectaDeEuler_linea = jc_dibujarNoRectaDeEuler_linea.isSelected();
															  }
														  }
		);

		p_dibujarNoRectaDeEuler.add(jc_dibujarNoRectaDeEuler_linea);


		contenedor.add(p_dibujarNoRectaDeEuler);

		String elementosTimer[] = {""+0, ""+1, ""+10, ""+50, ""+100, ""+200, ""+500, ""+1000, ""+2000, ""+5000};

		jcb_Timer = new JComboBox(elementosTimer);
		TitledBorder titleTimer;
		titleTimer = BorderFactory.createTitledBorder("Tiempo entre Iteraciones");
		jcb_Timer.setBorder(titleTimer);
		jcb_Timer.setSelectedIndex(0);
		jcb_Timer.addActionListener(new ActionListener()
													{
														public void actionPerformed(ActionEvent arg0) {
															Elementos_UI.instance.timerRutina2 = Integer.parseInt(
																	(String)jcb_Timer.getItemAt(jcb_Timer.getSelectedIndex()));
														}
													}
		);
		contenedor.add(jcb_Timer);

		JPanel contenedorSeccionTipoDeCalculo = new JPanel();
		contenedorSeccionTipoDeCalculo.setLayout( new BoxLayout( contenedorSeccionTipoDeCalculo, BoxLayout.X_AXIS));

		String elementosTipoDeCalculo[] =
				{
					Elementos_UI.instance.SIERPINSKI,
					Elementos_UI.instance.INCENTRO,
					Elementos_UI.instance.BARICENTRO,
					Elementos_UI.instance.CIRCUNCENTRO,
					Elementos_UI.instance.ORTOCENTRO
				};
		jcb_tipoDeCalculo = new JComboBox(elementosTipoDeCalculo);
		TitledBorder titletipoDeCalculo;
		titletipoDeCalculo = BorderFactory.createTitledBorder("Tipo De Calculo");
		jcb_tipoDeCalculo.setBorder(titletipoDeCalculo);
		jcb_tipoDeCalculo.setSelectedIndex(1);
		jcb_tipoDeCalculo.addActionListener(new ActionListener()
									{
										public void actionPerformed(ActionEvent arg0) {

											if ( (((String)jcb_tipoDeCalculo.getItemAt(jcb_tipoDeCalculo.getSelectedIndex())).
													equalsIgnoreCase(Elementos_UI.instance.SIERPINSKI)) )
											{
												jcb_showRatioOfMiddlePointInteger.setVisible(true);
												jcb_showRatioOfMiddlePointDecimal.setVisible(true);
												jtf_showRatioOfMiddlePoint.setVisible(true);
												instance.pack();
											} else {
												jcb_showRatioOfMiddlePointInteger.setVisible(false);
												jcb_showRatioOfMiddlePointDecimal.setVisible(false);
												jtf_showRatioOfMiddlePoint.setVisible(false);
												instance.pack();
											}

											// para mostar el select del Baricentro o Centroide
											if ( (((String)jcb_tipoDeCalculo.getItemAt(jcb_tipoDeCalculo.getSelectedIndex())).
													equalsIgnoreCase(Elementos_UI.instance.BARICENTRO)) )
											{
												jcb_nroPuntosBaricentro.setVisible(true);
												instance.pack();
											} else {
												jcb_nroPuntosBaricentro.setVisible(false);
												instance.pack();
											}

											if ( (((String)jcb_tipoDeCalculo.getItemAt(jcb_tipoDeCalculo.getSelectedIndex())).
													equalsIgnoreCase(Elementos_UI.instance.INCENTRO)) ||
											(((String)jcb_tipoDeCalculo.getItemAt(jcb_tipoDeCalculo.getSelectedIndex())).
													equalsIgnoreCase(Elementos_UI.instance.BARICENTRO)) )
											{
												p_dibujarNoRectaDeEuler.setVisible(true);
												instance.pack();
											} else {
												p_dibujarNoRectaDeEuler.setVisible(false);
												jc_dibujarNoRectaDeEuler_puntos.setSelected(false);
												jc_dibujarNoRectaDeEuler_linea.setSelected(false);
												Elementos_UI.instance.dibujarNoRectaDeEuler_puntos = false;
												Elementos_UI.instance.dibujarNoRectaDeEuler_linea = false;
												instance.pack();
											}

											if ( (((String)jcb_tipoDeCalculo.getItemAt(jcb_tipoDeCalculo.getSelectedIndex())).
													equalsIgnoreCase(Elementos_UI.instance.SIERPINSKI)) )
											{
												Elementos_UI.instance.TipoDeCalculo = Elementos_UI.instance.SIERPINSKI;
											}
											else
											if ( (((String)jcb_tipoDeCalculo.getItemAt(jcb_tipoDeCalculo.getSelectedIndex())).
													equalsIgnoreCase(Elementos_UI.instance.INCENTRO)) )
											{
												Elementos_UI.instance.TipoDeCalculo = Elementos_UI.instance.INCENTRO;
											}
											else
											if ( (((String)jcb_tipoDeCalculo.getItemAt(jcb_tipoDeCalculo.getSelectedIndex())).
													equalsIgnoreCase(Elementos_UI.instance.BARICENTRO)) )
											{
												Elementos_UI.instance.TipoDeCalculo = Elementos_UI.instance.BARICENTRO;
											}
											else
											if ( (((String)jcb_tipoDeCalculo.getItemAt(jcb_tipoDeCalculo.getSelectedIndex())).
													equalsIgnoreCase(Elementos_UI.instance.CIRCUNCENTRO)) )
											{
												Elementos_UI.instance.TipoDeCalculo = Elementos_UI.instance.CIRCUNCENTRO;
											}
											else
											if ( (((String)jcb_tipoDeCalculo.getItemAt(jcb_tipoDeCalculo.getSelectedIndex())).
													equalsIgnoreCase(Elementos_UI.instance.ORTOCENTRO)) )
											{
												Elementos_UI.instance.TipoDeCalculo = Elementos_UI.instance.ORTOCENTRO;
											}
										}
									}
		);
		contenedorSeccionTipoDeCalculo.add(jcb_tipoDeCalculo);

		String elementos_showRatioOfMiddlePointInteger[] =
				{
						""+0,""+1,""+2,""+3,""+4,""+5,""+6,""+7,""+8,""+9,""+10
				};
		jcb_showRatioOfMiddlePointInteger = new JComboBox(elementos_showRatioOfMiddlePointInteger);
		TitledBorder titleshowRatioOfMiddlePoint = BorderFactory.createTitledBorder("Radio entre segmentos, 1=punto medio");
		jcb_showRatioOfMiddlePointInteger.setBorder(titleshowRatioOfMiddlePoint);
		jcb_showRatioOfMiddlePointInteger.setSelectedIndex(1);
		jcb_showRatioOfMiddlePointInteger.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Double tmp = Double.parseDouble((String)jcb_showRatioOfMiddlePointInteger.getSelectedItem()) +
						     Double.parseDouble((String)jcb_showRatioOfMiddlePointDecimal.getSelectedItem());
				jtf_showRatioOfMiddlePoint.setText(tmp.toString());
				Elementos_UI.instance.ratioOfMiddlePoint = tmp;
			}
		});
		jcb_showRatioOfMiddlePointInteger.setVisible(false);
		contenedorSeccionTipoDeCalculo.add(jcb_showRatioOfMiddlePointInteger);

		String elementos_showRatioOfMiddlePointDecimal[] =
				{
						""+0.0,""+0.1,""+0.2,""+0.25,""+0.3,""+0.4,""+0.5,
						""+0.55,""+0.6,""+0.65,""+0.7,""+0.75,""+0.8,""+0.85,""+0.9,""+0.95
				};
		jcb_showRatioOfMiddlePointDecimal = new JComboBox(elementos_showRatioOfMiddlePointDecimal);
		TitledBorder titleshowRatioOfMiddlePointDecimal = BorderFactory.createTitledBorder("Decimales");
		jcb_showRatioOfMiddlePointDecimal.setBorder(titleshowRatioOfMiddlePointDecimal);
		jcb_showRatioOfMiddlePointDecimal.setSelectedIndex(0);
		jcb_showRatioOfMiddlePointDecimal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Double tmp = Double.parseDouble((String)jcb_showRatioOfMiddlePointInteger.getSelectedItem()) +
						     Double.parseDouble((String)jcb_showRatioOfMiddlePointDecimal.getSelectedItem());
				jtf_showRatioOfMiddlePoint.setText(tmp.toString());
				Elementos_UI.instance.ratioOfMiddlePoint = tmp;
			}
		});
		jcb_showRatioOfMiddlePointDecimal.setVisible(false);
		contenedorSeccionTipoDeCalculo.add(jcb_showRatioOfMiddlePointDecimal);

		Double tmp = Double.parseDouble((String)jcb_showRatioOfMiddlePointInteger.getSelectedItem()) +
					  Double.parseDouble((String)jcb_showRatioOfMiddlePointDecimal.getSelectedItem());
		jtf_showRatioOfMiddlePoint = new JTextField(tmp.toString());
		jtf_showRatioOfMiddlePoint.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Elementos_UI.instance.ratioOfMiddlePoint = Double.parseDouble((String)jtf_showRatioOfMiddlePoint.getText());
			}
		});
		jtf_showRatioOfMiddlePoint.setVisible(false);
		contenedorSeccionTipoDeCalculo.add(jtf_showRatioOfMiddlePoint);

		contenedor.add(contenedorSeccionTipoDeCalculo);

		// para editar con cuantos puntos quremos calcular el Centroide o Baricentro
		String elementos_nroPuntosBaricentro[] =
				{
						""+3,""+4,""+5,""+6,""+7,""+8,""+9,""+10,""+11,""+12,""+13,""+14,""+15
				};
		jcb_nroPuntosBaricentro = new JComboBox(elementos_nroPuntosBaricentro);
		TitledBorder titlenroPuntosBaricentro = BorderFactory.createTitledBorder("Nro Puntos, Centroide");
		jcb_nroPuntosBaricentro.setBorder(titlenroPuntosBaricentro);
		jcb_nroPuntosBaricentro.setSelectedIndex(0);
		jcb_nroPuntosBaricentro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Double tmp = Double.parseDouble((String)jcb_nroPuntosBaricentro.getSelectedItem()) ;
				Elementos_UI.instance.nroPuntosBaricentro = tmp;
			}
		});
		jcb_nroPuntosBaricentro.setVisible(false);
		contenedorSeccionTipoDeCalculo.add(jcb_nroPuntosBaricentro);

		String elementoscoloresAletorioODefinidoPorElPanel[] =
				{Elementos_UI.instance.ALEATORIO_RUTINA_2,
				Elementos_UI.instance.DEFINIDO_POR_EL_PANEL_RUTINA_2};
		jcb_coloresAletorioODefinidoPorElPanel = new JComboBox(elementoscoloresAletorioODefinidoPorElPanel);
		TitledBorder titleticoloresAletorioODefinidoPorElPanel;
		titleticoloresAletorioODefinidoPorElPanel = BorderFactory.createTitledBorder("Colores Aleatorios O Color definido por El Panel Patron de dibujo");
		jcb_coloresAletorioODefinidoPorElPanel.setBorder(titleticoloresAletorioODefinidoPorElPanel);
		jcb_coloresAletorioODefinidoPorElPanel.setSelectedIndex(1);
		jcb_coloresAletorioODefinidoPorElPanel.addActionListener(new ActionListener()
											{
												public void actionPerformed(ActionEvent arg0) {
													if ( (((String)jcb_coloresAletorioODefinidoPorElPanel.getItemAt(jcb_coloresAletorioODefinidoPorElPanel.getSelectedIndex())).
															equalsIgnoreCase(Elementos_UI.instance.ALEATORIO_RUTINA_2)) )
													{
														Elementos_UI.instance.coloresAletoriosRutina2 = true;
													}
													else
													if ( (((String)jcb_coloresAletorioODefinidoPorElPanel.getItemAt(jcb_coloresAletorioODefinidoPorElPanel.getSelectedIndex())).
															equalsIgnoreCase(Elementos_UI.instance.DEFINIDO_POR_EL_PANEL_RUTINA_2)) )
													{
														Elementos_UI.instance.coloresAletoriosRutina2 = false;
													}
												}
											}
		);
		contenedor.add(jcb_coloresAletorioODefinidoPorElPanel);

		result.add(contenedor);
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

												   elementosUI.ejecutarRutina2(
												   		((String)jcb_DentroFueraDeLaFigura.getSelectedItem()).equalsIgnoreCase(DENTRO_DE_LA_FIGURA),
														   ((String)jcb_DentroFueraDeLaFigura.getSelectedItem()).equalsIgnoreCase(FUERA_DE_LA_FIGURA)
														   );
											   }
										   }
		);
		result.add(jb_botonCalcular);

		return result;
	}

	public void mostrar()
	{
		if(this.jcb_DentroFueraDeLaFigura==null)
		{
			initialize();
		}

		this.setVisible(true);
	}

	public void setElementosUI( Elementos_UI elementosUI )
	{
		this.elementosUI = elementosUI;
	}

	public static void main (String Arg[])
	{
		(new VentanaDeCrearRutina2()).setVisible(true);
	}
}
