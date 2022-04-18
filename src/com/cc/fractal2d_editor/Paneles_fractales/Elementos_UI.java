package com.cc.fractal2d_editor.Paneles_fractales;

import com.cc.fractal2d_editor.Eventos_fractales.Eventos;
import com.cc.fractal2d_editor.Eventos_fractales.EventosRedoUndo;
import com.cc.fractal2d_editor.IO_fractales.Abrir_fractales;
import com.cc.fractal2d_editor.IO_fractales.Guardar_fractales;
import com.cc.fractal2d_editor.IO_fractales.Guardar_fractales_como_PNG;
import com.cc.fractal2d_editor.Paneles_fractales.Patron_de_dibujo.Crear_Algoritmo;
import com.cc.fractal2d_editor.Paneles_fractales.Patron_de_dibujo.Panel_resultado;
import com.cc.fractal2d_editor.Paneles_fractales.Patron_inicial.Panel_patron_inicial;
import org.w3c.dom.Document;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.geom.Point2D;
import java.math.BigInteger;
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

        public Panel_patron_inicial panel_patron_inicial;
        public int PANEL_PATRON_INICIAL;

        //public Panel_patron_recursivo panel_patron_recursivo;
        public Panel_patron_inicial panel_patron_recursivo;
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
            panel_patron_inicial=new Panel_patron_inicial(this);
            panel_patron_recursivo=new Panel_patron_inicial(this);
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

                    Dimension dim=Toolkit.getDefaultToolkit().getScreenSize();
                    jf_principal.setSize(new Dimension(dim.width-100,dim.height-100));
                    center();
                    break;
                }
                case PERSPECTIVA_UN_SOLO_PANEL:
                {
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
                    splitPanePatrones.setDividerLocation((int)(jf_principal.getWidth()*1/3));

                    splitPane.setLeftComponent(splitPanePatrones);
                    splitPane.setRightComponent(panel_de_dibujo);
                    splitPane.setDividerLocation((int)(jf_principal.getWidth()*2/3));

                    panelUnoSolo.add(splitPane);
//				panelUnoSolo.repaint();
//				splitPanePatrones.repaint();
//				splitPane.repaint();

                    jf_principal.add(panelUnoSolo);
//				jf_principal.repaint();

                    Dimension dim=Toolkit.getDefaultToolkit().getScreenSize();
                    jf_principal.setSize(new Dimension(dim.width-10,(int)(dim.height/1.5)));
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
            g.setColor(panel_de_dibujo.panel_de_dibujo.getColorLineas());
            System.out.println(this.getClass().getName()+".calcular_fractales() Color"+g.getColor());
            //((Graphics2D)(g)).setBackground(Color.WHITE);
            int orden=panel_de_dibujo.jcb_nivel.getSelectedIndex();
            if (orden == 0) return;

            ca=null;
            ca=new Crear_Algoritmo(pb,pr,g,orden,this);
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
            System.out.println(this.getClass().getName()+":detenerHilo(); 1");
            //ca.detenerHilo();
            if(ca!=null)
            {
                ca.set_detener(true);
            }

            // la barra de progreso al 0%
            //panel_de_dibujo.progresoFractal.setValue(0);

            System.out.println(this.getClass().getName()+":detenerHilo(); 2");
        }

        public void continuarHilo()
        {
            System.out.println(this.getClass().getName()+":detenerHilo(); 1");
            //ca.continuarHilo();
            this.setCalculandoFractales(true);
            ca.set_detener(false);
            ca.set_continuar(true);
            Thread hilo = new Thread(ca);
            hilo.start();
        }

        public void clear()
        {
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
        }

        public void setColorLineas_Panel_resultado(Color color1)
        {
            panel_de_dibujo.color_lineas.setBackground(color1);
            panel_de_dibujo.panel_de_dibujo.setColorLineas(color1);
            //panel_de_dibujo.panel_de_dibujo.repaint();
        }

        ////
        boolean isGradiente = true;
        Color[] coloresGradientes = {Color.WHITE, Color.BLACK};
        //Color colorIni = Color.WHITE;
        //Color colorFin = Color.BLACK;
        //int nroIteraciones = 50;
        int nroDeGradientes = 3;//(int)(panel_patron_inicial.js_VAL_MAX/nroIteraciones);
        int nivelDeRecursividad = 2;
        int porcentajeZoomMin = 1;
        int porcentajeZoomMax = 100;
        //// rutina 1
        public void ejecutarRutina1(boolean isGradiente,
                                    Color[] coloresGradiente,
                /*int nroIteraciones,*/
                                    int nroDeGradientes,
                                    int nivelDeRecursividad,
                                    int porcentajeZoomMin,
                                    int porcentajeZoomMax
        )
        {
            this.isGradiente = isGradiente;
            coloresGradientes = coloresGradiente;
            //this.colorIni = colorIni;
            //this.colorFin = colorFin;
            //this.nroIteraciones = nroIteraciones;
            this.nroDeGradientes = nroDeGradientes;
            this.nivelDeRecursividad = nivelDeRecursividad;
            this.porcentajeZoomMin = porcentajeZoomMin;
            this.porcentajeZoomMax = porcentajeZoomMax;

            detenerRutina1 = false;

            Thread hiloRutina1 = new Thread(this);
            hiloRutina1.start();
        }

        public void run()
        {
            ejecutarRutina1_run();
        }

        //Thread hiloRutina1;
        public boolean detenerRutina1 = false;

        public void detenerRutina1()
        {
            detenerRutina1 = true;
            //hiloRutina1.interrupt();
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
            //panel_patron_inicial.panel_de_dibujo.setOldZoom(1.0);
            //System.out.println("+panel_patron_inicial.js_zoom.getValue()"+panel_patron_inicial.js_zoom.getValue());
            //panel_patron_inicial.aplicarZoom(""+panel_patron_inicial.js_zoom.getValue());
            //calcular_fractales();
            ///////
            ////
            int nroIteraciones = (porcentajeZoomMax-porcentajeZoomMin)/nroDeGradientes;
            ////
            settearBarraDeProgresoValoresIniciales(nroDeGradientes*nroIteraciones);


            for(int j=0;j<nroDeGradientes;j++)
            {

                double saltoR = (double) ((-coloresGradientes[j].getRed()+coloresGradientes[(j+1)%nroDeGradientes].getRed())/nroIteraciones) ;
                double saltoG = (double) ((-coloresGradientes[j].getGreen()+coloresGradientes[(j+1)%nroDeGradientes].getGreen())/nroIteraciones) ;
                double saltoB = (double) ((-coloresGradientes[j].getBlue()+coloresGradientes[(j+1)%nroDeGradientes].getBlue())/nroIteraciones) ;

                for(int i=0;i<nroIteraciones;i++)
                {
                    if(detenerRutina1)
                    {
                        return;
                    }

                    Color colorTemp;

                    if(isGradiente)
                    {
//              gradiente

                        int colorR = (int)Math.nextUp(coloresGradientes[j].getRed()+(double)saltoR*i);
                        int colorG = (int)Math.nextUp(coloresGradientes[j].getGreen()+(double)saltoG*i);
                        int colorB = (int)Math.nextUp(coloresGradientes[j].getBlue()+(double)saltoB*i);

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
                        colorTemp = new Color(colorR,
                                colorG,
                                colorB);

                    }
                    else
                    {
                        // random
                        colorTemp = new Color((int)(Math.random()*255),
                                (int)(Math.random()*255),
                                (int)(Math.random()*255));

                    }



                    // evento del zoom
                    panel_patron_inicial.js_zoom.setValue(panel_patron_inicial.js_zoom.getValue()+ZoomSalto);
                    //panel_patron_inicial.js_zoom.setValue(panel_patron_inicial.js_POS_INI+ZoomSalto*i);

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

                    // incrementamos la barra de progreso de rutina en uno
                    incrementarBarraDeProgresoEnUno();
                }
            }

        }

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

