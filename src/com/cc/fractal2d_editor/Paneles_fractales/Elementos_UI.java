package com.cc.fractal2d_editor.Paneles_fractales;

import com.cc.fractal2d_editor.Eventos_fractales.Eventos;
import com.cc.fractal2d_editor.Eventos_fractales.EventosRedoUndo;
import com.cc.fractal2d_editor.IO_fractales.Abrir_fractales;
import com.cc.fractal2d_editor.IO_fractales.Guardar_fractales;
import com.cc.fractal2d_editor.IO_fractales.Guardar_fractales_como_PNG;
import com.cc.fractal2d_editor.Paneles_fractales.Patron_de_dibujo.Crear_Algoritmo;
import com.cc.fractal2d_editor.Paneles_fractales.Patron_de_dibujo.Panel_resultado;
import com.cc.fractal2d_editor.Paneles_fractales.Patron_de_disenio.Panel_patron_disenio;
import com.cc.fractal2d_editor.utils.Constants;
import org.w3c.dom.Document;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.math.BigInteger;
import java.util.Iterator;
import java.util.Vector;

public class Elementos_UI implements Runnable {


        public JFrame jf_principal;
        public String nombre_del_modelo="sin_nombre";

        public JMenuBar jm_barra_de_menu;
        public JRadioButtonMenuItem tresTabbeds;
        public static String TRES_TABBEDS = "Tres tabbeds";
        public static String UN_SOLO_PANEL = "Un solo panel";
        public JRadioButtonMenuItem unSoloPanel;

        /////
        public JTabbedPane tabbedpane;

        public Panel_patron_disenio panel_patron_inicial;
        public int PANEL_PATRON_INICIAL;

        public Panel_patron_disenio panel_patron_recursivo;
        public int PANEL_PATRON_RECURSIVO;

        public Panel_resultado panel_de_dibujo;
        public int PANEL_DE_DIBUJO;

        /////
        public JPanel panelUnoSolo;
        public JSplitPane splitPanePatrones;
        public JSplitPane splitPane;
        /////

        public int ancho;
        public int alto;

        public Abrir_fractales abrir_fractales;
        public Guardar_fractales guardar_fractales;
        public Guardar_fractales_como_PNG guardar_fractales_como_PNG;

        public String[] signos;

        public final int PERSPECTIVA_TRES_TABBEDS = 1;
        public final int PERSPECTIVA_UN_SOLO_PANEL = 2;
        public int PERSPECTIVA_actual = PERSPECTIVA_UN_SOLO_PANEL;

        public static int splitDividerLoation = 100;

        public static Elementos_UI instance;

        public static String COLOCAR_PUNTOS = "colocar_puntos";
        public static String MOVER_PUNTOS = "mover_puntos";
        public static String BORRAR_PUNTOS = "borrar_puntos";
        public static String BORRAR_TODO = "borrar_todo";

        public static String ULTIMA_DISTANCIA = "Ult_dist";

        public static String RutinaActual = "";
        public static String RUTINA_1 = "RUTINA_1";
        public static String RUTINA_2 = "RUTINA_2";

        public Point2D.Double puntoAleatorio = null;

        public boolean dibujarTriangulos = false;
        public boolean dibujarCircunferenciaInscrita = false;
        public boolean dibujarNoRectaDeEuler_linea = false;
        public boolean dibujarNoRectaDeEuler_puntos = false;
        public int timerRutina2 = 0;

        public int nroIteracionesMaxRutina2 = 20000;

        public final String SIERPINSKI = "Ratio por ejem: Punto Medio, Sierpinski";
        public Double ratioOfMiddlePoint = 1.0;
        public final  String INCENTRO = "Incentro, Circ. Inscrita, Bisectricez";
        public final  String BARICENTRO = "Baricentro, Medianas";
        public Double nroPuntosBaricentro = 3.0;
        public final  String CIRCUNCENTRO = "Circuncentro, Circ. que contiene, Mediatricez";
        public final  String ORTOCENTRO = "Ortocentro, Alturas";
        public String TipoDeCalculo = INCENTRO;

        public String ALEATORIO_RUTINA_2 = "Colores Aleatorios";
        public String DEFINIDO_POR_EL_PANEL_RUTINA_2 = "Color Definido por el Panel de dibujo";

        public boolean coloresAletoriosRutina2 = false;

        public static String CALCULAR = "Calcular";

        public static String AL_INICIO = "Al inicio";
        public static String AL_FINAL = "Al final";
        public String sentidoColocadoDePuntos = AL_FINAL;

        public static int dimensionPrefPanel_de_controles_width = 80;

        public Elementos_UI(JFrame f)
        {
            instance = this;

            jf_principal=f;

            Dimension Size = f.getSize();

            ancho=Size.width;
            alto=Size.height;

            jm_barra_de_menu=new JMenuBar();

            JMenu archivo=new JMenu("Archivo");
            archivo.setMnemonic(KeyEvent.VK_A);
            archivo.setToolTipText("opciones de archivo");

            jm_barra_de_menu.add(archivo);

            JMenuItem m;

            m=new JMenuItem("Abrir",KeyEvent.VK_B);
            //m.setIcon(new ImageIcon("Imagenes"+File.separatorChar+"icono_nuevo.gif"));
            m.setAccelerator (KeyStroke.getKeyStroke (KeyEvent.VK_B, ActionEvent.ALT_MASK));
            m.setToolTipText("abre un archivo");
            m.addActionListener(new Eventos(null,this));
            archivo.add(m);
            archivo.addSeparator();

            m=new JMenuItem("Guardar",KeyEvent.VK_G);
            //m.setIcon(new ImageIcon("Imagenes"+File.separatorChar+"icono_nuevo.gif"));
            m.setAccelerator (KeyStroke.getKeyStroke (KeyEvent.VK_G,ActionEvent.ALT_MASK));
            m.setToolTipText("guarda un archivo");
            m.addActionListener(new Eventos(null,this));
            archivo.add(m);
            archivo.addSeparator();

            m=new JMenuItem("Guardar como PNG",KeyEvent.VK_P);
            //m.setIcon(new ImageIcon("Imagenes"+File.separatorChar+"icono_nuevo.gif"));
            m.setAccelerator (KeyStroke.getKeyStroke (KeyEvent.VK_P,ActionEvent.ALT_MASK));
            m.setToolTipText("guarda un archivo como PNG");
            m.addActionListener(new Eventos(null,this));
            archivo.add(m);
            archivo.addSeparator();

            m=new JMenuItem("Salir",KeyEvent.VK_S);
            //m.setIcon(new ImageIcon("Imagenes"+File.separatorChar+"icono_nuevo.gif"));
            m.setAccelerator (KeyStroke.getKeyStroke (KeyEvent.VK_S,ActionEvent.ALT_MASK));
            m.setToolTipText("salir del programa");
            m.addActionListener(new Eventos(null,this));
            archivo.add(m);
            archivo.addSeparator();

            /////
            // para los eventos de undo redo
            /////
            archivo=new JMenu("Edit");
            archivo.setMnemonic(KeyEvent.VK_E);
            //archivo.setToolTipText("para cambiar la vista");

            m=new JMenuItem("Undo",KeyEvent.VK_U);
            m.setAccelerator (KeyStroke.getKeyStroke (KeyEvent.VK_U,ActionEvent.ALT_MASK));
            m.setToolTipText("para deshacer la accion");
            m.addActionListener(new EventosRedoUndo(this, null, m));
            archivo.add(m);
            archivo.addSeparator();

            m=new JMenuItem("Redo",KeyEvent.VK_R);
            m.setAccelerator (KeyStroke.getKeyStroke (KeyEvent.VK_R,ActionEvent.ALT_MASK));
            m.setToolTipText("para rehacer la accion");
            m.addActionListener(new EventosRedoUndo(this, m, null));
            archivo.add(m);
            archivo.addSeparator();

            m=new JMenuItem("Mover pts Patron Inicial --> Patron Recursivo",KeyEvent.VK_P);
            m.setAccelerator (KeyStroke.getKeyStroke (KeyEvent.VK_P,ActionEvent.ALT_MASK));
            m.setToolTipText("para mover los puntos de Patron Inicial al Patron Recursivo");
            m.addActionListener(new Eventos(null,this));
            archivo.add(m);

            archivo.addSeparator();

            m=new JMenuItem("Imagen de Fondo Panel Patron Recursivo",KeyEvent.VK_R);
            m.setAccelerator (KeyStroke.getKeyStroke (KeyEvent.VK_R,ActionEvent.ALT_MASK));
            m.setToolTipText("para selecciona una imagen y ponerla al Patron Recursivo");
            m.addActionListener(new Eventos(null,this));
            archivo.add(m);

            jm_barra_de_menu.add(archivo);

            m=new JMenuItem("Imagen de Fondo Panel Patron Inicial",KeyEvent.VK_I);
            m.setAccelerator (KeyStroke.getKeyStroke (KeyEvent.VK_I,ActionEvent.ALT_MASK));
            m.setToolTipText("para selecciona una imagen y ponerla al Patron Recursivo");
            m.addActionListener(new Eventos(null,this));
            archivo.add(m);

            jm_barra_de_menu.add(archivo);

            //jf_principal.addKeyListener(new RedoUndoKeyListener());

            /////
            // para cambiar la vista de tabbeds a un solo panel
            /////
            archivo=new JMenu("vista");
            archivo.setMnemonic(KeyEvent.VK_V);
            archivo.setToolTipText("para cambiar la vista");

//	m=new JMenuItem("Orden de los Paneles",KeyEvent.VK_O);
//	//m.setIcon(new ImageIcon("Imagenes"+File.separatorChar+"icono_nuevo.gif"));
//	m.setAccelerator (KeyStroke.getKeyStroke (KeyEvent.VK_O,ActionEvent.ALT_MASK));
//	m.setToolTipText("orden de los Paneles");
//	m.addActionListener(new Eventos(null,this));
//	archivo.add(m);
//	archivo.addSeparator();
//
            ButtonGroup group = new ButtonGroup();
            tresTabbeds = new JRadioButtonMenuItem(TRES_TABBEDS);
            tresTabbeds.setMnemonic(KeyEvent.VK_T);
            tresTabbeds.setToolTipText("para cambiar la vista a Tres tabbeds");
            tresTabbeds.addActionListener(new Eventos(null,this));
            group.add(tresTabbeds);
            archivo.add(tresTabbeds);
            archivo.addSeparator();

            unSoloPanel = new JRadioButtonMenuItem(UN_SOLO_PANEL);
            unSoloPanel.setMnemonic(KeyEvent.VK_U);
            unSoloPanel.setToolTipText("para cambiar la vista a Un solo panel");
            unSoloPanel.addActionListener(new Eventos(null,this));
            group.add(unSoloPanel);
            archivo.add(unSoloPanel);
            archivo.addSeparator();

            jm_barra_de_menu.add(archivo);

            //// menus de rutinas
            archivo=new JMenu("rutinas");
            archivo.setMnemonic(KeyEvent.VK_R);
            archivo.setToolTipText("para ejecutar alguna rutina de dibujo");

            m=new JMenuItem("rutina 1",KeyEvent.VK_1);
            m.setAccelerator (KeyStroke.getKeyStroke (KeyEvent.VK_1,ActionEvent.ALT_MASK));
            m.setToolTipText("para ejecutar al rutina 1");
            m.addActionListener(new Eventos(null,this));
            archivo.add(m);
            archivo.addSeparator();

            m=new JMenuItem("rutina 2",KeyEvent.VK_2);
            m.setAccelerator (KeyStroke.getKeyStroke (KeyEvent.VK_2,ActionEvent.ALT_MASK));
            m.setToolTipText("para ejecutar al rutina 2");
            m.addActionListener(new Eventos(null,this));
            archivo.add(m);

            archivo.addSeparator();

            jm_barra_de_menu.add(archivo);
            /////

            ////menus de figuras
            archivo=new JMenu("figuras");
            archivo.setMnemonic(KeyEvent.VK_F);
            archivo.setToolTipText("Menu para dibujar figuras predefinidas");

            m=new JMenuItem("estrellas",KeyEvent.VK_P);
            m.setAccelerator (KeyStroke.getKeyStroke (KeyEvent.VK_P,ActionEvent.ALT_MASK));
            m.setToolTipText("dibujar una estrellas");
            m.addActionListener(new Eventos(null,this));
            archivo.add(m);
            archivo.addSeparator();

            m=new JMenuItem("eNeagonos",KeyEvent.VK_E);
            m.setAccelerator (KeyStroke.getKeyStroke (KeyEvent.VK_E,ActionEvent.ALT_MASK));
            m.setToolTipText("dibujar un 'n' agono");
            m.addActionListener(new Eventos(null,this));
            archivo.add(m);
            archivo.addSeparator();

            jm_barra_de_menu.add(archivo);
            /////

            jf_principal.add(jm_barra_de_menu,BorderLayout.NORTH);

            // creamos los paneles
            panel_patron_inicial=new Panel_patron_disenio(this);
            panel_patron_recursivo=new Panel_patron_disenio(this);
            //panel_patron_recursivo=new Panel_patron_recursivo(this);
            panel_de_dibujo=new Panel_resultado(this);

            switchPERSPECTIVA(PERSPECTIVA_UN_SOLO_PANEL);
            //actualizamos el JRadioButtonMenuItem
            unSoloPanel.setSelected(true);
        }

        public void switchPERSPECTIVA(int perspectiva)
        {
            PERSPECTIVA_actual = perspectiva;

            switch(PERSPECTIVA_actual){

                case PERSPECTIVA_TRES_TABBEDS:
                {
                    //Dimension dim=Toolkit.getDefaultToolkit().getScreenSize();
                    //jf_principal.setSize(new Dimension(dim.width-100,dim.height-100));
                    //jf_principal.setSize(new Dimension(1280+20,720+10));
                    Dimension dim=Toolkit.getDefaultToolkit().getScreenSize();
                    jf_principal.setSize(new Dimension(dim.width-20,720+10));
                    //jf_principal.pack();

                    if(panelUnoSolo!=null)
                    {
                        jf_principal.remove(panelUnoSolo);
                        splitPanePatrones.remove(panel_patron_recursivo);
                        splitPane.remove(splitPanePatrones);
                        splitPane.remove(panel_de_dibujo);

                        //splitPane.addKeyListener(new RedoUndoKeyListener());

                    }
                    //panel_patron_inicial
                    //tresTabbeds.setSelected(true);
                    if(tabbedpane==null)
                    {
                        tabbedpane = new JTabbedPane();
                        tabbedpane.addChangeListener(new Eventos(null,this));

                        //tabbedpane.addKeyListener(new RedoUndoKeyListener());
                    }

                    tabbedpane.addTab("patron inicial",null,panel_patron_inicial,"para definir el patron inicial");

                    tabbedpane.addTab("patron recursivo",null,panel_patron_recursivo,"para definir el patron recursivo");

                    tabbedpane.addTab("Dibujar",null,panel_de_dibujo,"para dibujar el fractal");

                    jf_principal.add(tabbedpane);
                    llenar_CONSTANTES();//para saber en que panel estamos

                    tabbedpane.updateUI();


                    center();
                    break;
                }
                case PERSPECTIVA_UN_SOLO_PANEL:
                {
                    //Dimension dim=Toolkit.getDefaultToolkit().getScreenSize();
                    //jf_principal.setSize(new Dimension(dim.width-10,(int)(dim.height/1.5)));
                    //jf_principal.setSize(new Dimension(1280+20,720+10));
                    Dimension dim=Toolkit.getDefaultToolkit().getScreenSize();
                    jf_principal.setSize(new Dimension(dim.width-20,720+10));
                    //jf_principal.pack();

                    if(tabbedpane!=null)
                    {
                        jf_principal.remove(tabbedpane);
                        tabbedpane.remove(panel_patron_inicial);
                        tabbedpane.remove(panel_patron_recursivo);
                        tabbedpane.remove(panel_de_dibujo);
                    }

                    //unSoloPanel.setSelected(true);
                    if(panelUnoSolo==null)
                    {
                        panelUnoSolo = new JPanel();
                        panelUnoSolo.setLayout(new BoxLayout(panelUnoSolo,BoxLayout.X_AXIS));
                        splitPanePatrones = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);

                        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);

                        //panelUnoSolo.addKeyListener(new RedoUndoKeyListener());
                    }
                    splitPanePatrones.setLeftComponent(panel_patron_inicial);
                    splitPanePatrones.setRightComponent(panel_patron_recursivo);
                    splitPanePatrones.setDividerLocation((int)(jf_principal.getWidth()*1/3)-splitPanePatrones.getDividerSize());

                    splitPane.setLeftComponent(splitPanePatrones);
                    splitPane.setRightComponent(panel_de_dibujo);
                    splitPane.setDividerLocation((int)(jf_principal.getWidth()*2/3)-2*splitPanePatrones.getDividerSize());

                    panelUnoSolo.add(splitPane);

                    jf_principal.add(panelUnoSolo);
                    panel_patron_inicial.updateUI();
                    panel_patron_recursivo.updateUI();
                    panel_de_dibujo.updateUI();
//				jf_principal.repaint();



                    //jf_principal.setSize(new Dimension(dim.width-10,(int)(dim.height/1.5)));
                    //jf_principal.pack();
                    center();
                    break;
                }
            }
        }

        public void center()
        {
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            Dimension frameSize = jf_principal.getSize();
            int x = (screenSize.width - frameSize.width) / 2;
            int y = (screenSize.height - frameSize.height) / 2;
            jf_principal.setLocation(x, y);
        }

        public void llenar_CONSTANTES()
        {
            PANEL_PATRON_INICIAL=tabbedpane.indexOfComponent(panel_patron_inicial);
            PANEL_PATRON_RECURSIVO=tabbedpane.indexOfComponent(panel_patron_recursivo);
            PANEL_DE_DIBUJO=tabbedpane.indexOfComponent(panel_de_dibujo);
        }

        public void repaintPaneles_patrones()
        {
            panel_patron_inicial.setSliderPOS_INI(panel_patron_inicial.js_POS_INI);
            panel_patron_recursivo.setSliderPOS_INI(panel_patron_inicial.js_POS_INI);

            panel_patron_inicial.panel_de_dibujo.repaint();
            panel_patron_recursivo.panel_de_dibujo.repaint();
        }
        /*
        public void pintar_puntos()
        {
            switch(PERSPECTIVA_actual){

            case PERSPECTIVA_TRES_TABBEDS:
                {
                pintar_puntosPERSPECTIVA_TRES_TABBEDS();
                break;
                }
            case PERSPECTIVA_UN_SOLO_PANEL:
                {
                pintar_puntosPERSPECTIVA_UN_SOLO_PANEL();
                break;
                }
            }
        }

        public void pintar_puntosPERSPECTIVA_TRES_TABBEDS()
        {
            if(tabbedpane.getSelectedIndex()==PANEL_PATRON_INICIAL)
            {
                panel_patron_inicial.panel_de_dibujo.repaint();//.pintar_puntos();
            }
            else
            if(tabbedpane.getSelectedIndex()==PANEL_PATRON_RECURSIVO)
            {
                panel_patron_recursivo.panel_de_dibujo.repaint();//.pintar_puntos();
            }
        }

        public void pintar_puntosPERSPECTIVA_UN_SOLO_PANEL()
        {
            panel_patron_inicial.panel_de_dibujo.repaint();
            panel_patron_recursivo.panel_de_dibujo.repaint();
        }

        public void dibujar_punto(double x, double y)
        {
            switch(PERSPECTIVA_actual){

            case PERSPECTIVA_TRES_TABBEDS:
                {
                dibujar_puntoPERSPECTIVA_TRES_TABBEDS(x,y);
                break;
                }
            case PERSPECTIVA_UN_SOLO_PANEL:
                {
                dibujar_puntoPERSPECTIVA_UN_SOLO_PANEL(x,y);
                break;
                }
            }
        }

        public void dibujar_puntoPERSPECTIVA_TRES_TABBEDS(double x, double y)
        {
            if(tabbedpane.getSelectedIndex()==PANEL_PATRON_INICIAL)
            {
                panel_patron_inicial.panel_de_dibujo.dibujar_punto(x,y);
            }
            else
            if(tabbedpane.getSelectedIndex()==PANEL_PATRON_RECURSIVO)
            {
                panel_patron_recursivo.panel_de_dibujo.dibujar_punto(x,y);
            }
        }

        public void dibujar_puntoPERSPECTIVA_UN_SOLO_PANEL(double x, double y)
        {
            if(panel_patron_inicial.hasFocus())
            {
                panel_patron_inicial.panel_de_dibujo.dibujar_punto(x,y);
            }
            else
            if(panel_patron_recursivo.hasFocus())
            {
                panel_patron_recursivo.panel_de_dibujo.dibujar_punto(x,y);
            }
        }

        public void mover_punto(double x, double y)
        {
            if(tabbedpane.getSelectedIndex()==PANEL_PATRON_INICIAL)
            {
                panel_patron_inicial.panel_de_dibujo.mover_punto(x,y);
            }
            else
            if(tabbedpane.getSelectedIndex()==PANEL_PATRON_RECURSIVO)
            {
                panel_patron_recursivo.panel_de_dibujo.mover_punto(x,y);
            }
        }
        */
        public void set_nombre_del_modelo(String s)
        {
            nombre_del_modelo=s;
            panel_patron_inicial.jta_nombre_del_modelo.setText(s);
            panel_patron_recursivo.jta_nombre_del_modelo.setText(s);
            panel_de_dibujo.jta_nombre_del_modelo.setText(s);
        }

        public String get_nombre_del_modelo()
        {
            return nombre_del_modelo;
        }

        public void datos_para_abrir(Vector v)
        {
            Vector v_patron_inicial = new Vector() ;
            Vector v_patron_recursivo = new Vector() ;

            String inicio=(String)v.get(0);

            String etiqueta="_PATRON_INICIAL";

            int tam_pi=0;
            int tam_pr=0;

            if( inicio.endsWith(etiqueta) )
            {
                String segundo=inicio.substring(0,inicio.length()-etiqueta.length());

                tam_pi=Integer.parseInt(segundo);
                System.out.println("Elementos_UI:datos_para_abrir:tam_pi"+tam_pi);

                for(int i=0;i<tam_pi;i++)
                {
                    String tercero=(String)v.get(i+1);

                    String s_x="";
                    String s_y="";
                    Point2D punto;

                    for(int j=1;j<tercero.length()-1;j++)
                    {
                        {
                            System.out.println("Elementos_UI:datos_para_abrir:tercero.l="+tercero);

                            if ( tercero.substring(j-1,j).equalsIgnoreCase("X") )
                            {
                                s_x=concatenar_hasta( tercero.substring(j+1,tercero.length()) , "_" );
                                System.out.println("Elementos_UI:datos_para_abrir:s_x="+s_x);
                            }
                            else
                            if ( tercero.substring(j-1,j).equalsIgnoreCase("Y") )
                            {
                                s_y=concatenar_hasta( tercero.substring(j+1,tercero.length()) , "_" );
                                System.out.println("Elementos_UI:datos_para_abrir:s_y="+s_y);
                            }
                        }
                    }

                    punto=new Point2D.Double( Double.parseDouble(s_x), Double.parseDouble(s_y) );
                    v_patron_inicial.add(punto);
                }
            }

            inicio=(String)v.get(tam_pi+1);
            etiqueta="_PATRON_RECURSIVO";

            if( inicio.endsWith(etiqueta) )
            {
                String segundo=inicio.substring(0,inicio.length()-etiqueta.length());

                tam_pr=Integer.parseInt(segundo);
                System.out.println("Elementos_UI:datos_para_abrir:tam_pr"+tam_pr);

                for(int i=0;i<tam_pr;i++)
                {
                    String tercero=(String)v.get(i+tam_pi+2);

                    String s_x="";
                    String s_y="";
                    Point2D punto;

                    for(int j=1;j<tercero.length();j++)
                    {


                        if ( tercero.substring(j-1,j).equalsIgnoreCase("X") )
                            s_x=concatenar_hasta( tercero.substring(j+1,tercero.length()) , "_" );
                        if ( tercero.substring(j-1,j).equalsIgnoreCase("Y") )
                            s_y=concatenar_hasta( tercero.substring(j+1,tercero.length()) , "_" );
                    }

                    punto=new Point2D.Double( Double.parseDouble(s_x), Double.parseDouble(s_y) );
                    v_patron_recursivo.add(punto);
                }
            }


            panel_patron_inicial.panel_de_dibujo.v_puntos=v_patron_inicial;
            panel_patron_inicial.panel_de_dibujo.mas_puntos=false;
            panel_patron_inicial.panel_de_dibujo.mover_puntos=true;
            panel_patron_inicial.panel_de_dibujo.borrar_puntos=false;
            panel_patron_inicial.panel_de_dibujo.borrar_todo=false;

            panel_patron_recursivo.panel_de_dibujo.v_puntos=v_patron_recursivo;
            panel_patron_recursivo.panel_de_dibujo.mas_puntos=false;
            panel_patron_recursivo.panel_de_dibujo.mover_puntos=true;
            panel_patron_recursivo.panel_de_dibujo.borrar_puntos=false;
            panel_patron_recursivo.panel_de_dibujo.borrar_todo=false;

            //panel_de_dibujo.jcb_nivel.setSelectedIndex(2);
            //tabbedpane.setSelectedIndex(this.PANEL_DE_DIBUJO);
        }

        public String datos_para_guardar()
        {
            String s_salida="";

            Vector v_patron_inicial = panel_patron_inicial.panel_de_dibujo.v_puntos;
            Vector v_patron_recursivo = panel_patron_recursivo.panel_de_dibujo.v_puntos;

            s_salida=s_salida+v_patron_inicial.size()+"_PATRON_INICIAL\n";

            for(int i=0;i<v_patron_inicial.size();i++)
            {
                Point2D p=(Point2D)v_patron_inicial.get(i);

                s_salida=s_salida+"X="+p.getX()+"_"+"Y="+p.getY()+"_\n";

            }

            s_salida=s_salida+v_patron_recursivo.size()+"_PATRON_RECURSIVO\n";

            for(int i=0;i<v_patron_recursivo.size();i++)
            {
                Point2D p=(Point2D)v_patron_recursivo.get(i);

                s_salida=s_salida+"X="+p.getX()+"_"+"Y="+p.getY()+"_\n";

            }


            return s_salida;
        }

        public String datosParaGuardarXML()
        {
            String result = "";

            try {

                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder documentBuilder;
                documentBuilder = dbf.newDocumentBuilder();
                Document document = documentBuilder.newDocument();


            } catch (ParserConfigurationException e) {

                e.printStackTrace();
            }



            return result;
		/*
		try {
			  File file = new File("c:\\MyXMLFile.xml");
			  DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			  DocumentBuilder db = dbf.newDocumentBuilder();
			  Document doc = db.parse(file);
			  doc.getDocumentElement().normalize();
			  System.out.println("Root element " + doc.getDocumentElement().getNodeName());
			  NodeList nodeLst = doc.getElementsByTagName("employee");
			  System.out.println("Information of all employees");

			  for (int s = 0; s < nodeLst.getLength(); s++) {

			    Node fstNode = nodeLst.item(s);

			    if (fstNode.getNodeType() == Node.ELEMENT_NODE) {

			           Element fstElmnt = (Element) fstNode;
			      NodeList fstNmElmntLst = fstElmnt.getElementsByTagName("firstname");
			      Element fstNmElmnt = (Element) fstNmElmntLst.item(0);
			      NodeList fstNm = fstNmElmnt.getChildNodes();
			      System.out.println("First Name : "  + ((Node) fstNm.item(0)).getNodeValue());
			      NodeList lstNmElmntLst = fstElmnt.getElementsByTagName("lastname");
			      Element lstNmElmnt = (Element) lstNmElmntLst.item(0);
			      NodeList lstNm = lstNmElmnt.getChildNodes();
			      System.out.println("Last Name : " + ((Node) lstNm.item(0)).getNodeValue());
			    }
			  }

	  } catch (Exception e) {
	  e.printStackTrace();
	  }
	  */
        }

        public String concatenar_hasta(String f, String tope)
        {
            String frase=f;
            String salida="";
            int cont=1;

            while( !(frase.substring(cont-1,cont).equalsIgnoreCase(tope)) )
            {
                salida=salida+frase.substring(cont-1,cont);
                cont++;
            }

            System.out.println("Elementos_UI:concatenar_hasta:salida"+salida);

            return salida;
        }

        //boolean calcular_fractales=false;
        //Crear_Algoritmo ca=new Crear_Algoritmo(pb,pr,g,orden);
        Crear_Algoritmo ca;

        public boolean calculandoFractales = false;

        public void setCalculandoFractales(boolean calculandoFract) {
            calculandoFractales = calculandoFract;
        }

        public boolean getCalculandoFractales() {
            return calculandoFractales;
        }

        public synchronized void calcular_fractales()
        {
            setCalculandoFractales(true);
            //if(calcular_fractales)
            //{
            Point2D[] pb=panel_patron_inicial.panel_de_dibujo.get_puntos();
            Point2D[] pr=panel_patron_recursivo.panel_de_dibujo.get_puntos();
            //String[] si=panel_de_dibujo.get_signos();
            //Graphics g=panel_de_dibujo.panel_de_dibujo.getGraphics();
            //Image offscreen = panel_de_dibujo.panel_de_dibujo.createImage(panel_de_dibujo.panel_de_dibujo.getSize().width,
            //Graphics g = offscreen.getGraphics();

            //panel_de_dibujo.panel_de_dibujo.setBackgroundImage(null);
            //panel_de_dibujo.panel_de_dibujo.repaint();

            //panel_de_dibujo.panel_de_dibujo.updateColorLineas();
            Graphics g = panel_de_dibujo.panel_de_dibujo.getGraphics();
            //((Graphics2D)(g)).setBackground(panel_de_dibujo.panel_de_dibujo.getColor_fondo());
//g.setColor(panel_de_dibujo.panel_de_dibujo.getColor_fondo());
//g.fillRect(0, 0, panel_de_dibujo.panel_de_dibujo.getWidth(), panel_de_dibujo.panel_de_dibujo.getHeight());
g.setColor(panel_de_dibujo.panel_de_dibujo.getColor_lineas());
((Graphics2D)g).setStroke(panel_de_dibujo.panel_de_dibujo.getStroke());

            boolean dibujarPrimeraLineaComoShape = panel_de_dibujo.check_dibujarPrimeraLineaComoShape.isSelected();
            boolean no_dibujarPrimeraLineaPatronRecursivo = panel_de_dibujo.check_no_dibujarPrimeraLineaPatronRecursivo.isSelected();

            System.out.println(this.getClass().getName()+".calcular_fractales() Color"+g.getColor());
            //((Graphics2D)(g)).setBackground(Color.WHITE);
            int orden=panel_de_dibujo.jcb_nivel.getSelectedIndex();
            if (orden == 0)
                return;

            if (orden == 1 && dibujarPrimeraLineaComoShape)
            {
                dibujarNivel_1(g);
                return;
            }
            else
            {
                ca=null;
                ca=new Crear_Algoritmo(pb,pr,g,orden,this, no_dibujarPrimeraLineaPatronRecursivo);
                ca.set_detener(false);
                ca.set_continuar(false);
                //ca.run();
                Thread hilo = new Thread(ca);
                hilo.start();
                //ca.curvaKoch();
                //}
                //panel_de_dibujo.panel_de_dibujo.getContentPane();
                //Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            }

        }

        public void dibujarNivel_1(Graphics g)
        {  /*
            Thread hilo = new Thread(new Runnable() {
                @Override
                public void run() {
                    */
                    ((Graphics2D)g)
                            .draw(Constants
                                    .crear_POLIGONO_CERRADO(
                                            panel_patron_inicial
                                                    .panel_de_dibujo.v_puntos,
                                            false));
                    setCalculandoFractales(false);

                    Constants.updatePanel_de_dibujoBackground();

                    /*
                }
            });
            hilo.start();
            */
        }

        /*
        public synchronized void set_calcular_fractales(boolean b)
        {
            calcular_fractales = b;
            if(ca!=null)
            {ca.set_detener(b);

            }
        }
        */
        public void detenerHilo()
        {
            System.out.println(this.getClass().getName()+":detenerHilo(); inicio");
            //ca.detenerHilo();
            if(ca!=null)
            {
                ca.set_detener(true);
            }

            // la barra de progreso al 0%
            //panel_de_dibujo.progresoFractal.setValue(0);

            System.out.println(this.getClass().getName()+":detenerHilo(); fin");
        }

        public void continuarHilo()
        {
            System.out.println(this.getClass().getName()+":continuarHilo(); inicio");
            //ca.continuarHilo();
            this.setCalculandoFractales(true);
            ca.set_detener(false);
            ca.set_continuar(true);
            Thread hilo = new Thread(ca);
            hilo.start();
        }

        public synchronized void clear()
        {
            //detenerHilo();

            //Thread hilo = new Thread(new Runnable() {
            //    @Override
            //    public void run() {
                    panel_de_dibujo.panel_de_dibujo.setBackgroundImage(null);
                    panel_patron_inicial.panel_de_dibujo.setBackgroundImage(null);
                    //panel_patron_recursivo.panel_de_dibujo.setBackgroundImage(null);

                    panel_de_dibujo.panel_de_dibujo.repaint();
                    panel_patron_inicial.panel_de_dibujo.repaint();
                    //panel_patron_recursivo.panel_de_dibujo.repaint();

                    // la barra de progreso al 0%
                    panel_de_dibujo.progresoFractal.setValue(0);
                    panel_de_dibujo.progresoRutina.setValue(0);

                    // para el tiempo que falta
                    panel_de_dibujo.tiempoRestanteFractal.setText("");
                    panel_de_dibujo.tiempoRestanteRutina.setText("");
            //    }
            //});
            //hilo.start();
        }
/*
        public void setStroke(Stroke stroke)
        {
            panel_de_dibujo.panel_de_dibujo.setStroke(stroke);
        }
*/      public void setColorLineas_Panel_resultado(Color color1)
        {
            panel_de_dibujo.color_lineas.setBackground(color1);
            panel_de_dibujo.panel_de_dibujo.setColorLineas(color1);

            Color colorOpuesto = new Color((int)(255-color1.getRed()),
                    (int)(255-color1.getGreen()),
                    (int)(255-color1.getBlue()));

            panel_de_dibujo.color_lineas.setForeground(colorOpuesto);
            //panel_de_dibujo.panel_de_dibujo.repaint();
        }

        public void setColorFondo_Panel_resultado(Color color1)
        {
            panel_de_dibujo.color_fondo.setBackground(color1);
            panel_de_dibujo.panel_de_dibujo.setColor_fondo(color1);
            //panel_de_dibujo.panel_de_dibujo.setBackground(color1);
        }

        public void tiling(String tipoDeTiling)
        {
            setCalculandoFractales(true);

            Vector oldPoints =  (Vector)panel_patron_inicial.panel_de_dibujo.v_puntos.clone();

            // mover teseando

            Vector v_puntos = panel_patron_inicial.panel_de_dibujo.v_puntos;

            // mover los puntos
            double xMin = ((Point2D)v_puntos.get(0)).getX();
            double yMin = ((Point2D)v_puntos.get(0)).getY();

            double xMax = ((Point2D)v_puntos.get(0)).getX();
            double yMax = ((Point2D)v_puntos.get(0)).getY();

            double diferenciaAnteriorYmaxActualYmax = 0;
            double anteriorYMax = ((Point2D)v_puntos.get(0)).getY();

            for(int i=0; i<v_puntos.size(); i++) {

                Point2D valPoint2D = ((Point2D)v_puntos.get(i));
                if ( valPoint2D.getX() < xMin )
                {
                    xMin = valPoint2D.getX();
                }
                if ( valPoint2D.getY() < yMin )
                {
                    yMin = valPoint2D.getY();
                }

                if ( valPoint2D.getX() > xMax )
                {
                    xMax = valPoint2D.getX();
                }
                if ( valPoint2D.getY() > yMax )
                {
                    anteriorYMax = yMax;
                    yMax = valPoint2D.getY();
                }
            }

            diferenciaAnteriorYmaxActualYmax = yMax - anteriorYMax;

            double anchoFigura = xMax - xMin;
            double altoFigura = yMax - yMin;

            int MAX_X_SCREEN = (int)(panel_patron_inicial.panel_de_dibujo.getWidth()/anchoFigura);
            int MAX_Y_SCREEN = (int)(panel_patron_inicial.panel_de_dibujo.getHeight()/altoFigura);

            // por si pasa un error feo
            if (MAX_X_SCREEN > 1000 ||
                MAX_Y_SCREEN > 1000) {
               return;
            }

            for(int fila=-1; fila<=MAX_X_SCREEN+1; fila++)
            {
                for(int columna=-1; columna<=MAX_Y_SCREEN+1; columna++)
                {
                    // para que no hagan mas calculos si se presiono el boton de Stop
                    if ( !getCalculandoFractales() ) {
                        return;
                    }

                    Vector nuevosPuntos = new Vector();

                    for(int i=0; i<v_puntos.size(); i++)
                    {
                        Point2D valPoint2D = ((Point2D)v_puntos.get(i));

                        double correccionX = 0;
                        double correccionY = 0;


                        switch (tipoDeTiling) {

                            case Panel_resultado.TILING_CUADRADO:

                                break;
                            case Panel_resultado.TILING_HEXAGONAL:

                                correccionY = columna*diferenciaAnteriorYmaxActualYmax;
                                if (columna%2==0)
                                {
                                    correccionX = anchoFigura / 2;
                                }

                                break;

                            default:
                                throw new IllegalArgumentException("Invalid tipoDeTiling ");
                        }

                        Point2D newPoint2D = new Point2D.Double(
                                valPoint2D.getX() - xMin + fila*anchoFigura - correccionX,
                                valPoint2D.getY() - yMin + columna*altoFigura - correccionY
                        );
                        nuevosPuntos.add(newPoint2D);
                    }
                    panel_patron_inicial.panel_de_dibujo.v_puntos = nuevosPuntos;


                    calcular_fractales();
                }
            }


            panel_patron_inicial.panel_de_dibujo.v_puntos = oldPoints;

        }

        ////
        //boolean isGradiente = true;
        String tipo;
        Color[] coloresGradientes = {Color.WHITE, Color.BLACK};
        //Color colorIni = Color.WHITE;
        //Color colorFin = Color.BLACK;
        //int nroIteraciones = 50;
        int nroDeGradientes = 3;//(int)(panel_patron_inicial.js_VAL_MAX/nroIteraciones);
        int nroLineas = -1;
        int nivelDeRecursividad = 2;
        int porcentajeZoomMin = 0;
        int porcentajeZoomMax = 100;
        Double porcentajeRotacionMin = 0.0;
        Double porcentajeRotacionMax = 0.0;
        //// rutina 1
        // tipo = GRADIENTE, GRADIENTE_ALEATORIO, FIJO,
        public void ejecutarRutina1(String tipo,
                                    Color[] coloresGradiente,
                                    int nroLineas,
                /*int nroIteraciones,*/
                                    int nroDeGradientes,
                                    int nivelDeRecursividad,
                                    int porcentajeZoomMin,
                                    int porcentajeZoomMax,
                                    Double porcentajeRotacionMin,
                                    Double porcentajeRotacionMax
        )
        {
            RutinaActual = RUTINA_1;

            this.tipo = tipo;
            //this.isGradiente = isGradiente;
            coloresGradientes = coloresGradiente;
            //this.colorIni = colorIni;
            //this.colorFin = colorFin;
            //this.nroIteraciones = nroIteraciones;
            this.nroLineas = nroLineas;
            this.nroDeGradientes = nroDeGradientes;
            this.nivelDeRecursividad = nivelDeRecursividad;
            this.porcentajeZoomMin = porcentajeZoomMin;
            this.porcentajeZoomMax = porcentajeZoomMax;
            this.porcentajeRotacionMin = porcentajeRotacionMin;
            this.porcentajeRotacionMax = porcentajeRotacionMax;

            detenerRutina1 = false;

            Thread hiloRutina1 = new Thread(this);
            hiloRutina1.start();
        }

        public void checkCurrentTabSelectedAndOpen(int select) {
            if (PERSPECTIVA_actual == PERSPECTIVA_TRES_TABBEDS) {
                if (tabbedpane.getSelectedIndex() != select) {
                    tabbedpane.setSelectedIndex(select);
                }
            }
        }

        int count = 0;
        public void run()
        {
            if(RutinaActual == RUTINA_1) {
                ejecutarRutina1_run();
            } else
            if(RutinaActual == RUTINA_2) {
                count = 0;
                Thread hilo = new Thread() {
                    public void run () {
                        while(!detenerRutina2 && count<=nroIteracionesMaxRutina2) {
                            ejecutarRutina2_run();
                            count++;
                        }
                    }
                };
                hilo.start();
            }
        }

        public boolean detenerRutina1 = false;
        public boolean detenerRutina2 = false;
        public boolean isDentro = false;
        public boolean isFuera = false;

        public void detenerRutina1()
        {
            detenerRutina1 = true;
        }

        public void detenerRutina2()
        {
            detenerRutina2 = true;
        }

        public synchronized void ejecutarRutina1_run()
        {


//		Color colorIni = panel_de_dibujo.panel_de_dibujo.getColorLineas();//new Color(255,0,0);// JColorChooser.showDialog( null, "Seleccione un color", Color.BLACK );//new Color(255,0,0);
//		Color colorFin = new Color(255-colorIni.getRed(),
//								   255-colorIni.getGreen(),
//								   255-colorIni.getBlue());

//		Color colorFin = new Color((int)(Math.random()*255),
//								   (int)(Math.random()*255),
//								   (int)(Math.random()*255));//new Color(0,0,255);// JColorChooser.showDialog( null, "Seleccione un color", Color.BLACK );//new Color(0,255,0);
//
            //int nroIteraciones = 50;

            //int ZoomIni = panel_patron_inicial.js_zoom.getValue();
            int ZoomSalto = 1;//(int)(panel_patron_inicial.totalZoom/(2*nroIteraciones));
            Double RotarSalto = 1.0;


            /// fijamos el mivel de recursividad si es 0
            //if(nivel==0)
            //{
            panel_de_dibujo.jcb_nivel.setSelectedIndex(nivelDeRecursividad);
            //}
            //int nivel = Integer.parseInt((String) panel_de_dibujo.jcb_nivel.getSelectedItem());
            ///

            /////// para pintar desde el min zoom
            //panel_patron_inicial.js_zoom.setValue(panel_patron_inicial.js_POS_INI);
            //panel_patron_inicial.js_zoom.setValue(1);
panel_patron_inicial.setSliderPOS_INI(porcentajeZoomMin);

panel_patron_inicial.setSliderROTACION_INI(porcentajeRotacionMin.intValue());

if ( !getCalculandoFractales() )
{
    //set_calcular_fractales(true);
    calcular_fractales();
}
            //panel_patron_inicial.panel_de_dibujo.setOldZoom(1.0);
            //System.out.println("+panel_patron_inicial.js_zoom.getValue()"+panel_patron_inicial.js_zoom.getValue());
            //panel_patron_inicial.aplicarZoom(""+panel_patron_inicial.js_zoom.getValue());
            //calcular_fractales();
            ///////
            ////
            double nroIteraciones = (porcentajeZoomMax-porcentajeZoomMin)/nroDeGradientes;
            ////
            settearBarraDeProgresoValoresIniciales( (int)(nroIteraciones*nroDeGradientes) );
            RotarSalto = (this.porcentajeRotacionMax-this.porcentajeRotacionMin)/ nroIteraciones;
            Double RotarAcumulado = 0.0;

            int rotacionInicial = (panel_patron_inicial.js_rotar.getValue());

            for(int j=0;j<nroDeGradientes;j++)
            {
                double saltoR = -1;
                double saltoG = -1;
                double saltoB = -1;

                // transparente viene null del dialogo de colores
                if (coloresGradientes[j] != null && coloresGradientes[(j+1)%nroDeGradientes] != null) {
                    saltoR = (double) ((-coloresGradientes[j].getRed()+coloresGradientes[(j+1)%nroDeGradientes].getRed())/nroIteraciones) ;
                    saltoG = (double) ((-coloresGradientes[j].getGreen()+coloresGradientes[(j+1)%nroDeGradientes].getGreen())/nroIteraciones) ;
                    saltoB = (double) ((-coloresGradientes[j].getBlue()+coloresGradientes[(j+1)%nroDeGradientes].getBlue())/nroIteraciones) ;
                }


                int i_cont_lineas = 0;
                int i_cont_colores_lineas = 0;

                double _AcumuladoColorR = 0;
                double _AcumuladoColorG = 0;
                double _AcumuladoColorB = 0;

                for(int i=0;i<=nroIteraciones;i++) {
                    if (detenerRutina1) {
                        return;
                    }

                    Color colorTemp = null;

                    //if(VentanaDeCrearRutina.instance.GRADIENTE.equalsIgnoreCase(tipo))
                    if ("Gradiente Aleatorio".equalsIgnoreCase(tipo) || "Gradiente".equalsIgnoreCase(tipo)) {
//              gradiente

                        int colorR;
                        int colorG;
                        int colorB;

                        // transparente viene null del dialogo de colores
                        if (coloresGradientes[j] != null) {
                            //colorR = (int) Math.nextUp(coloresGradientes[j].getRed() + (double) saltoR * i);
                            //colorG = (int) Math.nextUp(coloresGradientes[j].getGreen() + (double) saltoG * i);
                            //colorB = (int) Math.nextUp(coloresGradientes[j].getBlue() + (double) saltoB * i);

                            //colorTemp = new Color(colorR, colorG, colorB);

                            _AcumuladoColorR = _AcumuladoColorR + saltoR;
                            _AcumuladoColorG = _AcumuladoColorG + saltoG;
                            _AcumuladoColorB = _AcumuladoColorB + saltoB;

//System.out.println(String.format("(%1s , %2s , %3s), salto=%4s",_AcumuladoColorR,_AcumuladoColorG,_AcumuladoColorB, saltoR));

                            colorR = (int) Math.floor(coloresGradientes[j].getRed() + _AcumuladoColorR);
                            colorG = (int) Math.floor(coloresGradientes[j].getGreen() + _AcumuladoColorG);
                            colorB = (int) Math.floor(coloresGradientes[j].getBlue() + _AcumuladoColorB);

//System.out.println(String.format("(%1s , %2s , %3s) ",colorR,colorG,colorB));

                            colorR = colorR>255?255:colorR;
                            colorR = colorR<0?0:colorR;
                            colorG = colorG>255?255:colorG;
                            colorG = colorG<0?0:colorG;
                            colorB = colorB>255?255:colorB;
                            colorB = colorB<0?0:colorB;

//System.out.println(String.format("(%1s , %2s , %3s) ",colorR,colorG,colorB));

                            colorTemp = new Color(colorR, colorG, colorB);
                        }

                        //System.out.println("colorR="+colorR);
                        //System.out.println("colorG="+colorG);
                        //System.out.println("colorB="+colorB);

                        // para controlar que no se salgan del rango
				/*
				colorR = (colorR<0)? 0 : colorR ;
				colorG = (colorR<0)? 0 : colorG ;
				colorB = (colorR<0)? 0 : colorB ;

				colorR = (colorR>255)? 255 : colorR ;
				colorG = (colorR>255)? 255 : colorG ;
				colorB = (colorR>255)? 255 : colorB ;
				*/


                    } else
                        //if(VentanaDeCrearRutina.instance.GRADIENTE_ALEATORIO.equalsIgnoreCase(tipo) ||
                        //        VentanaDeCrearRutina.instance.ALEATORIO.equalsIgnoreCase(tipo))
                        if ("Aleatorio".equalsIgnoreCase(tipo)) {
                            // random
                            colorTemp = new Color((int) (Math.random() * 255),
                                    (int) (Math.random() * 255),
                                    (int) (Math.random() * 255));

                        } else
                            //if(VentanaDeCrearRutina.instance.FIJO.equalsIgnoreCase(tipo))
                            if ("Fijo".equalsIgnoreCase(tipo)) {


                                // colores fijos deacuerdo al numerso de lineas
                                if (i_cont_lineas < nroLineas) {

                                    i_cont_lineas++;
                                } else {
                                    i_cont_lineas = 0;
                                    i_cont_colores_lineas++;
                                }

                                // transparente viene null del dialogo de colores
                                if (coloresGradientes[(i_cont_colores_lineas) % nroDeGradientes] != null) {
                                    colorTemp = coloresGradientes[(i_cont_colores_lineas) % nroDeGradientes];
                                }

                            } else {
                                colorTemp = null;//new Color(0, 0, 0);
                            }


                    // evento del zoom
                    panel_patron_inicial.js_zoom.setValue(panel_patron_inicial.js_zoom.getValue() + ZoomSalto);
                    //panel_patron_inicial.js_zoom.setValue(panel_patron_inicial.js_POS_INI+ZoomSalto*i);



                    // evento de rotacion
                    //panel_patron_inicial.js_rotar.setValue((int) (rotacionInicial + i * RotarSalto));
                    RotarAcumulado += RotarSalto;
                    panel_patron_inicial.js_rotar.setValue((int) (rotacionInicial + RotarAcumulado));

                    if (/*coloresGradientes[j] == null ||*/ colorTemp != null)
                    {
                        // evento de seleccion de color de lineas
                        setColorLineas_Panel_resultado(colorTemp);

                        // to draw one fractal until the end before to begin to draw another one, when doing Rutina1
                        while (getCalculandoFractales()) {
                            try {
                                Thread.sleep(1);
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                        }


                        // evento del boton de calcular
                        calcular_fractales();
                    }

                    // incrementamos la barra de progreso de rutina en uno
                    incrementarBarraDeProgresoEnUno();
                }
            }

        }

    //// rutina 1
    public void ejecutarRutina2(boolean isDentro,
                                boolean isFuera)
    {
        RutinaActual = RUTINA_2;

        detenerRutina2 = false;
        this.isDentro = isDentro;
        this.isFuera = isFuera;


            // calcular Punto Aletorio inicial
            if (isDentro) {
                //panel_patron_inicial.calcularPuntoAleatorioDentro();
                puntoAleatorio = (Point2D.Double)panel_patron_inicial.panel_de_dibujo.getPuntoAleatorioDentro();
            } else if(isFuera) {
                //panel_patron_inicial.calcularPuntoAleatorioFuera();
                puntoAleatorio = (Point2D.Double)panel_patron_inicial.panel_de_dibujo.getPuntoAleatorioFuera();
            }


        Thread hiloRutina2 = new Thread(this);
        hiloRutina2.start();
    }

    public synchronized void ejecutarRutina2_run()
    {
        if(detenerRutina2)
        {
            return;
        }

        int v_puntos_incial_size = panel_patron_inicial.panel_de_dibujo.v_puntos.size();
        if (v_puntos_incial_size!=0) {

            Shape s_p0 = panel_patron_inicial.panel_de_dibujo.get_punto_de_control(
                    ((Point2D.Double) panel_patron_inicial.panel_de_dibujo.v_puntos.get(0) ), panel_patron_inicial.panel_de_dibujo.TAM
            );

            /*
            if (((Point2D.Double) panel_patron_inicial.panel_de_dibujo.v_puntos.get(0) ).x ==
               ((Point2D.Double) panel_patron_inicial.panel_de_dibujo.v_puntos.get(v_puntos_incial_size-1) ).x &&
               ((Point2D.Double) panel_patron_inicial.panel_de_dibujo.v_puntos.get(0) ).y ==
               ((Point2D.Double) panel_patron_inicial.panel_de_dibujo.v_puntos.get(v_puntos_incial_size-1) ).y )
               */
            if (s_p0.contains(((Point2D.Double) panel_patron_inicial.panel_de_dibujo.v_puntos.get(v_puntos_incial_size-1) )))
            {
                v_puntos_incial_size = panel_patron_inicial.panel_de_dibujo.v_puntos.size()-1;
            }
        }

        Point2D.Double v1 = new Point2D.Double();
        Point2D.Double v2 = new Point2D.Double();
        // como hacer y controlar una lista de vertices para el centroide o baricentro
        Point2D.Double[] v_array = new Point2D.Double[this.nroPuntosBaricentro.intValue()-1];
        if (TipoDeCalculo == BARICENTRO && this.nroPuntosBaricentro.intValue() > 3) {

            int randomArrayPos = (int)(Math.random()*v_puntos_incial_size);
            int ptsBariIndex = 0;
            v_array[ptsBariIndex] = (Point2D.Double)panel_patron_inicial.panel_de_dibujo.v_puntos.get( randomArrayPos );
            ptsBariIndex++;
            int randomPointLeftRight = Math.random()>0.5?1:-1;

            while (ptsBariIndex<v_array.length) {
                if ( randomPointLeftRight < 0 )
                {
                    if ( randomArrayPos-1 < 0 )
                    {
                        randomArrayPos = v_puntos_incial_size-1;

                    } else
                    {
                        randomArrayPos -= 1;
                    }
                    v_array[ptsBariIndex] = (Point2D.Double)panel_patron_inicial.panel_de_dibujo.v_puntos.get( randomArrayPos );
                    ptsBariIndex++;
                }
                else
                if  (randomPointLeftRight > 0)
                {
                    v_array[ptsBariIndex] = (Point2D.Double)panel_patron_inicial.panel_de_dibujo.v_puntos.get( (randomArrayPos+1)%v_puntos_incial_size );
                    ptsBariIndex++;
                }
            }

            // Type 1 // random Points
            /*
            int ptsBariIndex = 0;
            v_array[ptsBariIndex] = (Point2D.Double)panel_patron_inicial.panel_de_dibujo.v_puntos.get( (int)(Math.random()*v_puntos_incial_size) );
            ptsBariIndex++;
            v_array[ptsBariIndex] = (Point2D.Double)panel_patron_inicial.panel_de_dibujo.v_puntos.get( (int)(Math.random()*v_puntos_incial_size) );

            while( ptsBariIndex < (this.nroPuntosBaricentro.intValue()-2) ) {
                while (v_array[ptsBariIndex-1].equals(v_array[ptsBariIndex])) {
                    v_array[ptsBariIndex-1] = (Point2D.Double)panel_patron_inicial.panel_de_dibujo.v_puntos.get( (int)(Math.random()*v_puntos_incial_size) );
                    v_array[ptsBariIndex] = (Point2D.Double)panel_patron_inicial.panel_de_dibujo.v_puntos.get( (int)(Math.random()*v_puntos_incial_size) );
                }
                ptsBariIndex++;
                v_array[ptsBariIndex] = (Point2D.Double)panel_patron_inicial.panel_de_dibujo.v_puntos.get( (int)(Math.random()*v_puntos_incial_size) );
            }
            */
        } else {
            // Type 2 // a random side
            int randomArrayPos = (int)(Math.random()*v_puntos_incial_size);
            v1 = (Point2D.Double)panel_patron_inicial.panel_de_dibujo.v_puntos.get( randomArrayPos );
            int randomPointLeftRight = Math.random()>0.5?1:-1;
            if (randomArrayPos+randomPointLeftRight < 0) {
                v2 = (Point2D.Double)panel_patron_inicial.panel_de_dibujo.v_puntos.get( v_puntos_incial_size-1 );
            } else {
                v2 = (Point2D.Double)panel_patron_inicial.panel_de_dibujo.v_puntos.get( (randomArrayPos+randomPointLeftRight)%v_puntos_incial_size );
            }
            // Type 1 // random Points
            /*
            v1 = (Point2D.Double)panel_patron_inicial.panel_de_dibujo.v_puntos.get( (int)(Math.random()*v_puntos_incial_size) );
            v2 = (Point2D.Double)panel_patron_inicial.panel_de_dibujo.v_puntos.get( (int)(Math.random()*v_puntos_incial_size) );
            while (v1.equals(v2)) {
                v1 = (Point2D.Double)panel_patron_inicial.panel_de_dibujo.v_puntos.get( (int)(Math.random()*v_puntos_incial_size) );
                v2 = (Point2D.Double)panel_patron_inicial.panel_de_dibujo.v_puntos.get( (int)(Math.random()*v_puntos_incial_size) );
            }
            */
        }

        Point2D.Double inPunto_a_calcular = new Point2D.Double();


        // calcular punto a marcar por ejemplo incentro con dos vertices aleatorios
        switch(TipoDeCalculo) {

            case SIERPINSKI: {
                inPunto_a_calcular = new Point2D.Double(
                        (puntoAleatorio.x+ratioOfMiddlePoint*v1.x)/(1+ratioOfMiddlePoint),
                        (puntoAleatorio.y+ratioOfMiddlePoint*v1.y)/(1+ratioOfMiddlePoint));
                //(ratioOfMiddlePoint*puntoAleatorio.x+v1.x)/(1+ratioOfMiddlePoint),
                //(ratioOfMiddlePoint*puntoAleatorio.y+v1.y)/(1+ratioOfMiddlePoint));
                break;
            }

            case INCENTRO: {
                inPunto_a_calcular = calcular_incentro(v1, v2, puntoAleatorio);
                break;
            }

            case BARICENTRO: {
                inPunto_a_calcular = calcular_baricentro(v1, v2, puntoAleatorio, v_array);
                break;
            }

            case CIRCUNCENTRO: {
                inPunto_a_calcular = calcular_circuncentro(v1, v2, puntoAleatorio);
                break;
            }

            case ORTOCENTRO: {
                inPunto_a_calcular = calcular_ortocentro(v1, v2, puntoAleatorio);
                break;
            }

            default: {
                break;
            }
        }

        //
/*
        if (inPunto_a_calcular.x==0 || inPunto_a_calcular.y==0)
        {
            return;
        }
*/
        int lado = 1;
        //Shape incentroShape = new Ellipse2D.Double( incentro.getX()-lado/2 , incentro.getY()-lado/2 , lado , lado );

        // dibujar incentro
        Graphics g = panel_de_dibujo.panel_de_dibujo.getGraphics();
        Graphics2D g2d = (Graphics2D)g;
        String iterationMessage = "iterationNro = " + count;
        if(count % 2 == 0) {
            int _width = g.getFontMetrics().stringWidth(iterationMessage);
            int _height = g.getFontMetrics().getHeight();
            g2d.clearRect(0,0, _width + 5, _height);
        }
        g.drawString(iterationMessage,5,15);

        if (coloresAletoriosRutina2) {
            g.setColor(
                    new Color((int)(Math.random()*255),
                            (int)(Math.random()*255),
                            (int)(Math.random()*255))
            );
        }
        else {
        g.setColor(panel_de_dibujo.panel_de_dibujo.getColor_lineas());
        }

        g.drawOval((int)(inPunto_a_calcular.getX()-lado/2) , (int)(inPunto_a_calcular.getY()-lado/2) , lado , lado);

        if(count % 1000 == 0) {
            // this is to keep the dot visible if there is repaint or rezise o se cambia a tabbeds
            Rectangle rectangle = new Rectangle(panel_de_dibujo.panel_de_dibujo.getLocationOnScreen().x,
                    panel_de_dibujo.panel_de_dibujo.getLocationOnScreen().y,
                    panel_de_dibujo.panel_de_dibujo.getSize().width,
                    panel_de_dibujo.panel_de_dibujo.getSize().height);
            Robot robot;
            try {
                robot = new Robot();
                BufferedImage bufferedImage = robot.createScreenCapture(rectangle);

                Image image = Toolkit.getDefaultToolkit().createImage(bufferedImage.getSource());
                panel_de_dibujo.panel_de_dibujo.setBackgroundBufferedImage(bufferedImage);
                panel_de_dibujo.panel_de_dibujo.setBackgroundImage(image);

                panel_patron_inicial.panel_de_dibujo.setBackgroundImage(image);
                panel_patron_inicial.panel_de_dibujo.repaint();

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        Paint p = g2d.getPaint();

        if (dibujarTriangulos)
        {
            g2d.setPaint(Color.RED);

            // no dibujar to do el triangulo solo dos puntos para el punto medio
            if (TipoDeCalculo == SIERPINSKI) {
                g2d.draw(new Line2D.Double(puntoAleatorio, v1));
            }
            if (TipoDeCalculo == BARICENTRO && nroPuntosBaricentro > 3) {
                g2d.draw(new Line2D.Double(puntoAleatorio, v_array[0]));
                for(int i=0;i<v_array.length-1;i++) {
                    g2d.draw(new Line2D.Double(v_array[i], v_array[(i + 1) % v_array.length]));
                }
                g2d.draw(new Line2D.Double(v_array[v_array.length-1], puntoAleatorio));
            }
            else {
                g2d.draw(new Line2D.Double(puntoAleatorio, v1));
                g2d.draw(new Line2D.Double(v1, v2));
                g2d.draw(new Line2D.Double(v2, puntoAleatorio));
            }

        }

        if (dibujarCircunferenciaInscrita)
        {
            g2d.setPaint(Color.GREEN);

           if (TipoDeCalculo == INCENTRO) {
               Double radio = getRadioCircInscrita(v1,v2,puntoAleatorio, inPunto_a_calcular);
               g2d.draw(new Ellipse2D.Double(inPunto_a_calcular.x-radio, inPunto_a_calcular.y-radio, 2*radio, 2*radio));
           }
           else
           if (TipoDeCalculo == CIRCUNCENTRO) {
               Double radio = getRadioCircCircunscrita(v1,v2,puntoAleatorio);
               g2d.draw(new Ellipse2D.Double(inPunto_a_calcular.x-radio, inPunto_a_calcular.y-radio, 2*radio, 2*radio));
           }

        }


        if ( (dibujarNoRectaDeEuler_puntos || dibujarNoRectaDeEuler_linea) &&
                (TipoDeCalculo == INCENTRO || TipoDeCalculo == BARICENTRO) )
        {
                g2d.setPaint(Color.MAGENTA);

                Point2D _incentro = calcular_incentro(v1, v2, puntoAleatorio);
                Point2D.Double[] _v_array = new Point2D.Double[0];
                Point2D _baricentro = calcular_baricentro(v1, v2, puntoAleatorio, _v_array);

//g2d.draw(new Line2D.Double(_incentro, _baricentro));

                //Point2D _circuncentro = calcular_circuncentro(v1, v2, puntoAleatorio);
//g2d.draw(new Line2D.Double(_incentro, _baricentro));

                //Point2D _ortocentro = calcular_ortocentro(v1, v2, puntoAleatorio);
//g2d.draw(new Line2D.Double(_incentro, _ortocentro));

                g2d.setPaint(Color.ORANGE);
                //g2d.draw(new Line2D.Double(_incentro, _circuncentro));
                //g2d.draw(new Line2D.Double(_incentro, _ortocentro));
                //g2d.draw(new Line2D.Double(_circuncentro, _baricentro));
                //g2d.draw(new Line2D.Double(_circuncentro, _ortocentro));
if(dibujarNoRectaDeEuler_linea) {
    g2d.draw(new Line2D.Double(_incentro, _baricentro));
}
//g2d.draw(new Line2D.Double(_incentro, _ortocentro));
//g2d.draw(new Line2D.Double(_baricentro, _ortocentro));
            if(dibujarNoRectaDeEuler_puntos) {
                g2d.setPaint(new Color(50, 100 , 255));
                g.drawOval((int)(_incentro.getX()-lado) , (int)(_incentro.getY()-lado) , 2*lado , 2*lado);
                g.drawOval((int)(_baricentro.getX()-lado) , (int)(_baricentro.getY()-lado) , 2*lado , 2*lado);
            }
        }

        g2d.setPaint(p);

        if (timerRutina2 != 0)
        {
            try {
                Thread.sleep(timerRutina2);
            }
            catch (Exception e) {
                System.out.println(e);
            }
        }


        puntoAleatorio = inPunto_a_calcular;
    }

    public Double getRadioCircInscrita(Point2D.Double p1, Point2D.Double p2, Point2D.Double p3, Point2D.Double inc) {

           return Math.abs( (p2.y - p1.y)*inc.x - (p2.x - p1.x)*inc.y + (p2.x - p1.x)*p1.y - (p2.y - p1.y)*p1.x )
                    / Math.sqrt( Math.pow((p2.y - p1.y),2) + Math.pow((p2.x - p1.x),2) );


    }

    public Double getRadioCircCircunscrita(Point2D.Double p1, Point2D.Double p2, Point2D.Double p3) {

        Double a = p1.distance(p2);
        Double b = p2.distance(p3);
        Double c = p3.distance(p1);
        Double s = ( a + b + c )/2;
        return a*b*c/(4*Math.sqrt(s*(s-a)*(s-b)*(s-c)));
    }

    public Point2D.Double calcular_incentro(Point2D.Double p1, Point2D.Double p2, Point2D.Double p3) {

        Double d1 = p1.distance(p2); //calcular_distancia(p1, p2);
        Double d2 = p2.distance(p3); //calcular_distancia(p2, p3);
        Double d3 = p3.distance(p1); //calcular_distancia(p3, p1);

        // debe ser la longitud de lado opuesto al vertice
        Double x = (d1*p3.x + d2*p1.x + d3*p2.x)/(d1 + d2 + d3);
        Double y = (d1*p3.y + d2*p1.y + d3*p2.y)/(d1 + d2 + d3);

        // interesantes
        //Double x = (d1*p1.x + d2*p2.x + d3*p3.x)/(d1 + d2 + d3);
        //Double y = (d1*p1.y + d2*p2.y + d3*p3.y)/(d1 + d2 + d3);
        //Double x = (d1*p2.x + d2*p3.x + d3*p1.x)/(d1 + d2 + d3);
        //Double y = (d1*p2.y + d2*p3.y + d3*p1.y)/(d1 + d2 + d3);
        //Double x = (d1*p2.x + d2*p3.x + d3*p1.x)/(d1 + d2 + d3);
        //Double y = (d1*p2.y + d2*p3.y + d3*p1.y)/(d1 + d2 + d3);

        // cualquier cosa
        //Double x = (d1*p2.x + d2*p1.x + d3*p3.x)/(d1 + d2 + d3);
        //Double y = (d1*p2.y + d2*p1.y + d3*p3.y)/(d1 + d2 + d3);





        return new Point2D.Double(x,y);
    }


    public Point2D.Double calcular_baricentro(Point2D.Double p1, Point2D.Double p2, Point2D.Double p3, Point2D.Double[] pArray) {

        Double x = 0.0;
        Double y = 0.0;

        if (nroPuntosBaricentro > 3) {
            Double xSum = 0.0;
            Double ySum = 0.0;
            for(int i=0;i<pArray.length;i++) {
                xSum += pArray[i].x;
                ySum += pArray[i].y;
            }
            x = xSum/pArray.length;
            y = ySum/pArray.length;
        } else {
            x = (p1.x + p2.x + p3.x)/3;
            y = (p1.y + p2.y + p3.y)/3;
        }

        return new Point2D.Double(x,y);
    }

    public Point2D.Double calcular_circuncentro(Point2D.Double p1, Point2D.Double p2, Point2D.Double p3) {

        Double m1 = (p1.y - p2.y)/(p1.x - p2.x);
        Double m2 = (p2.y - p3.y)/(p2.x - p3.x);
        Double m3 = (p3.y - p1.y)/(p3.x - p1.x);

        Point2D.Double p1_medio = new Point2D.Double((p1.x + p2.x)/2, (p1.y + p2.y)/2);
        Point2D.Double p2_medio = new Point2D.Double((p2.x + p3.x)/2, (p2.y + p3.y)/2);
        Point2D.Double p3_medio = new Point2D.Double((p3.x + p1.x)/2, (p3.y + p1.y)/2);

        //Double m1_perpendicular = -1/m1;
        Double m2_perpendicular = -1/m2;
        Double m3_perpendicular = -1/m3;

        if (p1.x == p2.x) {
            // TODO implementar control para pendientes infinitas o 0
            Double x, y;
            y = p1_medio.y;
            x = (m2_perpendicular*p2_medio.x - p2_medio.y + p1_medio.y)/m2_perpendicular ;

            return new Point2D.Double(x,y);
        }

        if (p2.x == p3.x) {
            // TODO implementar control para pendientes infinitas o 0
            Double x, y;
            y = p1_medio.y;
            x = (m3_perpendicular*p3_medio.x - p3_medio.y + p2_medio.y)/m3_perpendicular ;

            return new Point2D.Double(x,y);
        }

        // ecuacion 1
        //y-p1_medio.y = m1_perpendicular *(x - p1_medio.x);
        // ecuacion 2
        //y-p2_medio.y = m2_perpendicular *(x - p2_medio.x);

        //m1_perpendicular *x - y = m1_perpendicular*p1_medio.x - p1_medio.y ;
        //m2_perpendicular *x - y = m2_perpendicular*p2_medio.x - p2_medio.y ;

        /*

        Double x = deteminate(
                m1_perpendicular*p1_medio.x - p1_medio.y,
                m2_perpendicular*p2_medio.x - p2_medio.y,
                -1.0,
                -1.0)/
                    deteminate(
                            m1_perpendicular,
                            m2_perpendicular,
                            -1.0,
                            -1.0);
        Double y = deteminate(
                m1_perpendicular,
                m2_perpendicular,
                m1_perpendicular*p1_medio.x - p1_medio.y,
                m2_perpendicular*p2_medio.x - p2_medio.y)/
                    deteminate(
                            m1_perpendicular,
                            m2_perpendicular,
                            -1.0,
                            -1.0);

        */

        //(y-p1_medio.y)*(-m1) = (x - p1_medio.x);
        //(y-p2_medio.y)*(-m2) = (x - p2_medio.x);

        //(-m1*y + m1*p1_medio.y) = (x - p1_medio.x);
        //(-m2*y + m2*p2_medio.y) = (x - p2_medio.x);

        //m1_perpendicular *x - y = m1_perpendicular*p1_medio.x - p1_medio.y ;
        //m2_perpendicular *x - y = m2_perpendicular*p2_medio.x - p2_medio.y ;

        //x + m1*y = m1*p1_medio.y+p1_medio.x;
        //x + m2*y = m2*p2_medio.y+p2_medio.x;



        Double x = deteminate(
                m1*p1_medio.y+p1_medio.x,
                m2*p2_medio.y+p2_medio.x,
                m1,
                m2
        )/deteminate(
                1.0,
                1.0,
                m1,
                m2
        );

        Double y = deteminate(
                1.0,
                1.0,
                m1*p1_medio.y+p1_medio.x,
                m2*p2_medio.y+p2_medio.x
        )/deteminate(
                1.0,
                1.0,
                m1,
                m2
        );

        // if NaN
        // TODO implementar control para pendientes infinitas o 0
        return new Point2D.Double(x,y);
    }

    public Double deteminate(Double a11, Double a21, Double a12, Double a22) {
            Double result = a11*a22 - a21*a12;
        return result;
    }

    public Point2D.Double calcular_ortocentro(Point2D.Double p1, Point2D.Double p2, Point2D.Double p3) {

        Double m1 = (p1.y - p2.y)/(p1.x - p2.x);
        Double m2 = (p2.y - p3.y)/(p2.x - p3.x);
        Double m3 = (p3.y - p1.y)/(p3.x - p1.x);

        //Point2D.Double p1_medio = new Point2D.Double((p1.x + p2.x)/2, (p1.y + p2.y)/2);
        //Point2D.Double p2_medio = new Point2D.Double((p2.x + p3.x)/2, (p2.y + p3.y)/2);
        //Point2D.Double p3_medio = new Point2D.Double((p3.x + p1.x)/2, (p3.y + p1.y)/2);

        Double m1_perpendicular = -1/m1;
        Double m2_perpendicular = -1/m2;
        Double m3_perpendicular = -1/m3;

        if (p1.x == p2.x) {
            // TODO implementar control para pendientes infinitas o 0
            Double x, y;
            y = p1.y;
            x = (m2_perpendicular*p2.x - p2.y + p1.y)/m2_perpendicular ;

            return new Point2D.Double(x,y);
        }

        if (p2.x == p3.x) {
            // TODO implementar control para pendientes infinitas o 0
            Double x, y;
            y = p1.y;
            x = (m3_perpendicular*p3.x - p3.y + p2.y)/m3_perpendicular ;

            return new Point2D.Double(x,y);
        }

        // m1_perpendicular * x - y = m1_perpendicular * p3.x - p3.y
        // m2_perpendicular * x - y = m2_perpendicular * p1.x - p1.y

        //a1 x + b1 y = c1
        //a2 x + b2 y = c2
        // resolver sistema de ecuaciones 2 ecuaciones 2 incognitas
        //resolverSitemaDeEcuaciones(a1, a2, b1, b2, c1, c2);

        // if NaN
        // TODO implementar control para pendientes infinitas o 0
        return resolverSitemaDeEcuaciones(m1_perpendicular, m2_perpendicular, -1.0, -1.0, m1_perpendicular * p3.x - p3.y, m2_perpendicular * p1.x - p1.y);
    }



    public Point2D.Double resolverSitemaDeEcuaciones(Double a11,
                                              Double a21,
                                              Double a12,
                                              Double a22,
                                              Double a13,
                                              Double a23) {
            Double detSistema = deteminate(
                a11,
                a21,
                a12,
                a22
        );

        Double detX = deteminate(
                a13,
                a23,
                a12,
                a22
        );

        Double detY = deteminate(
                a11,
                a21,
                a13,
                a23
        );

        Double x = detX/detSistema;

        Double y = detY/detSistema;

        return new Point2D.Double(x,y);
    }

/*
    public Double calcular_distancia(Point2D.Double p1, Point2D.Double p2) {
         return Math.sqrt( Math.pow(p1.x - p2.x,2) + Math.pow(p1.y - p2.y,2) );
    }
*/



        public void settearBarraDeProgresoValoresIniciales(int maximo)
        {
            //// esto es para settear la barra de progreso VALORES INICIALES
            panel_de_dibujo.progresoRutina.setMinimum(0);
            panel_de_dibujo.progresoRutina.setMaximum(maximo);
            panel_de_dibujo.progresoRutina.setValue(0);
            //System.out.println(this.getClass().getName()+":curvaKoch() maximo="+maximo);
            //System.out.println(this.getClass().getName()+":curvaKoch() elementos_UI.panel_de_dibujo.progresoDibujo.getMaximum()="+elementos_UI.panel_de_dibujo.progresoFractal.getMaximum());
            ////

            inicioParaCalcTime = -1;
            finParaCalcTime = -1;
            diferenciaParaCalcTime = -1;
        }
        long inicioParaCalcTime = -1;
        long finParaCalcTime = -1;
        long diferenciaParaCalcTime = -1;
        public void incrementarBarraDeProgresoEnUno()
        {
            //// para settear la barra de progreso
            int valIni = panel_de_dibujo.progresoRutina.getValue();
            panel_de_dibujo.progresoRutina.setValue(valIni+1);
            //System.out.println(this.getClass().getName()+":curvaKoch() elementos_UI.panel_de_dibujo.progresoDibujo.getValue()="+elementos_UI.panel_de_dibujo.progresoFractal.getValue());
            //System.out.println(this.getClass().getName()+":curvaKoch() valIni="+valIni);
            ////

            // para setear el tiempo que falta
            if(inicioParaCalcTime == -1)
            {
                inicioParaCalcTime = System.nanoTime();
                return;
            }
            if(finParaCalcTime == -1)
            {
                finParaCalcTime = System.nanoTime();
                return;
            }
            if(diferenciaParaCalcTime == -1)
            {
                diferenciaParaCalcTime = finParaCalcTime - inicioParaCalcTime;
            }
            long actual = panel_de_dibujo.progresoRutina.getValue();
            long maximo = panel_de_dibujo.progresoRutina.getMaximum();
            BigInteger tiempoRestante = BigInteger.valueOf(((maximo-actual)*diferenciaParaCalcTime));

            BigInteger tiempoRestanteEnSeg = BigInteger.valueOf((long) (tiempoRestante.longValue()/Math.pow(10,6)) );

            long tiempoRestanteMin = (long)(tiempoRestanteEnSeg.longValue()/60);
            long tiempoRestanteSeg = (long)(tiempoRestanteEnSeg.longValue()%60);

            //panel_de_dibujo.tiempoRestanteRutina.setText("faltan "+tiempoRestanteMin+":"+tiempoRestanteSeg);
            panel_de_dibujo.tiempoRestanteRutina.setText("faltan "+tiempoRestanteEnSeg.longValue());
        }

    }

