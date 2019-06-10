package com.cc.fractal2d_editor.Paneles_fractales.Patron_inicial;

import com.cc.fractal2d_editor.Eventos_fractales.Eventos;
import com.cc.fractal2d_editor.Eventos_fractales.Eventos_Panel_de_dibujo;
import com.cc.fractal2d_editor.Eventos_fractales.RedoUndoKeyListener;
import com.cc.fractal2d_editor.Paneles_fractales.Elementos_UI;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.geom.Point2D;

public class Panel_patron_inicial extends JPanel {

    JScrollPane scroll_panel_de_controles;
    JInternalFrame panel_de_controles;
    public Panel_de_dibujo panel_de_dibujo;

    public JTextArea jta_nombre_del_modelo;
    public JTextArea jta_estado;

    //public JTextArea jta_zoom;
    //public JComboBox jcb_zoom;
    public JSlider js_zoom;
    int js_VAL_MIN =0;
    public int totalZoom = 100;
    // al cambiar 'p_ini' aqui hacemos que el zoom maximo sea
    // 3 --> 200%
    // 4 --> 300%
    // 5 --> 400% = 100*(5-1) : punto_inicial_el_100%
    // porque la posicion inicial es el 100%
    int p_ini = 6;
    public int js_POS_INI =totalZoom+js_VAL_MIN;
    public int js_VAL_MAX =totalZoom*(p_ini-1)+js_VAL_MIN;

    public JLabel jl_zoom;

    Elementos_UI elementos_UI;

    public static String PUNTOS = "puntos";
    public static String DISTANCIAS = "distancias";
    public static String ANGULOS_EJE_X = "angulos eje X";
    public static String ANGULOS_ENTRE_LINEAS = "angulos entre lineas";


    //public Vector v_puntos=new Vector();

    public Panel_patron_inicial(Elementos_UI elementos_ui)
    {
        elementos_UI=elementos_ui;

        panel_grafico();
        //setBackground(Color.white);
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
        panel_de_controles.setLayout( new BoxLayout( panel_de_controles.getContentPane(), BoxLayout.Y_AXIS));
        //panel_de_controles.setBounds(5,5,(int)(ancho/4-10),(int)(alto-100));
        panel_de_controles.setVisible(true);


        JPanel panelNombreModelo = new JPanel();
        TitledBorder titleNombreModelo;
        titleNombreModelo = BorderFactory.createTitledBorder("Nombre del modelo");
        panelNombreModelo.setBorder(titleNombreModelo);
        panelNombreModelo.setLayout(new BorderLayout());
        //JLabel L_0=new JLabel("Nombre del modelo");
        //L_0.setBounds(10,10,200,10);
        jta_nombre_del_modelo=new JTextArea(nombre_del_modelo);
        //jta_nombre_del_modelo.setBounds(10,30,150,20);

        //panel_de_controles.add(L_0);
        panelNombreModelo.add(jta_nombre_del_modelo, BorderLayout.CENTER);
        panel_de_controles.add(panelNombreModelo);

        JPanel panelEstado = new JPanel();
        TitledBorder titleEstado;
        titleEstado = BorderFactory.createTitledBorder("Estado");
        panelEstado.setBorder(titleEstado);
        panelEstado.setLayout(new BorderLayout());
        //JLabel L_1=new JLabel("Estado");
        //L_1.setBounds(10,10+20+20+10,200,10);
        jta_estado=new JTextArea("???");
        //jta_estado.setBounds(10,30+20*2+10,150,20);

        //panel_de_controles.add(L_1);
        //panel_de_controles.add(jta_estado);
        panelEstado.add(jta_estado, BorderLayout.CENTER);
        panel_de_controles.add(panelEstado);

////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////


        JPanel panelBotones = new JPanel();
        TitledBorder titleBotones;
        titleBotones = BorderFactory.createTitledBorder("Botones");
        panelBotones.setBorder(titleBotones);
        panelBotones.setLayout(new GridLayout(4,1) );
        //panelBotones.setLayout(new BoxLayout( panelBotones, BoxLayout.Y_AXIS));

        JButton colocar_puntos=new JButton("colocar_puntos");
        //colocar_puntos.setBounds(10,150,150,40);
        colocar_puntos.addMouseListener(new Eventos(this, elementos_UI));
        panelBotones.add(colocar_puntos);
        //panel_de_controles.add(colocar_puntos);


        JButton mover_puntos=new JButton("mover_puntos");
        //mover_puntos.setBounds(10,210,150,30);
        mover_puntos.addMouseListener(new Eventos(this, elementos_UI));
        panelBotones.add(mover_puntos);
        //panel_de_controles.add(mover_puntos);

        JButton borrar_puntos=new JButton("borrar_puntos");
        //borrar_puntos.setBounds(10,250,150,30);
        borrar_puntos.addMouseListener(new Eventos(this, elementos_UI));
        panelBotones.add(borrar_puntos);
        //panel_de_controles.add(borrar_puntos);

        JButton borrar_todo=new JButton("borrar_todo");
        //borrar_todo.setBounds(10,290,150,30);
        borrar_todo.addMouseListener(new Eventos(this, elementos_UI));
        panelBotones.add(borrar_todo);
        //panel_de_controles.add(borrar_todo);

        panel_de_controles.add(panelBotones);

        //// panel para el zoom
        JPanel panelZoom = new JPanel();
        TitledBorder titleZoom;
        titleZoom = BorderFactory.createTitledBorder("Zoom %");
        panelZoom.setBorder(titleZoom);
        panelZoom.setLayout(new BorderLayout() );

	/* si fuera un combo
	String elementos[]= {"10","20","30","40","50","60","70","80","90",
						 "100","120","150","180","200","250","300",
						 "350","400","450","500","550","600","650"};
	jcb_zoom = new JComboBox(elementos);
	jcb_zoom.setSelectedIndex(9);
	jcb_zoom.addActionListener(new Eventos(this, elementos_UI));
	panelZoom.add(jcb_zoom, BorderLayout.CENTER);
	*/
        js_zoom = new JSlider( SwingConstants.HORIZONTAL, js_VAL_MIN,
                js_VAL_MAX,
                js_POS_INI );
        js_zoom.setPaintTicks( true );
        js_zoom.setMajorTickSpacing( (int)((js_VAL_MAX-js_VAL_MIN)/2) );
        js_zoom.setMinorTickSpacing( (int)((js_VAL_MAX-js_VAL_MIN)/4) );
        js_zoom.setPaintLabels(true);

        panelZoom.add(js_zoom, BorderLayout.CENTER);

        // registrar componente de escucha de eventos de JSlider
        js_zoom.addChangeListener(new Eventos(this, elementos_UI));

        jl_zoom = new JLabel(js_zoom.getValue()+"%");
        panelZoom.add(jl_zoom, BorderLayout.SOUTH);

        panel_de_controles.add(panelZoom);
        /////

        ///// panel para ocultar o mostrar los puntos
        JPanel panelPuntosDeControl = new JPanel();
        TitledBorder titlePuntosDeControl;
        titlePuntosDeControl = BorderFactory.createTitledBorder("Ocultar los puntos de control");
        panelPuntosDeControl.setBorder(titlePuntosDeControl);
        panelPuntosDeControl.setLayout( new GridLayout(4, 1));

        // para ver los puntos de control
        JCheckBox jcb_mostrar_puntos_de_control = new JCheckBox(PUNTOS);
        jcb_mostrar_puntos_de_control.setSelected(true);
        jcb_mostrar_puntos_de_control.addMouseListener(new Eventos(this, elementos_UI));
        panelPuntosDeControl.add(jcb_mostrar_puntos_de_control);

        // para ver las distancias entre puntos
        JCheckBox jcb_mostrar_distancias_entre_puntos = new JCheckBox(DISTANCIAS);
        jcb_mostrar_distancias_entre_puntos.setSelected(true);
        jcb_mostrar_distancias_entre_puntos.addMouseListener(new Eventos(this, elementos_UI));
        panelPuntosDeControl.add(jcb_mostrar_distancias_entre_puntos);

        // para para ver los angulos con el eje X
        JCheckBox jcb_mostrar_angulos_con_el_eje_x = new JCheckBox(ANGULOS_EJE_X);
        jcb_mostrar_angulos_con_el_eje_x.setSelected(true);
        jcb_mostrar_angulos_con_el_eje_x.addMouseListener(new Eventos(this, elementos_UI));
        panelPuntosDeControl.add(jcb_mostrar_angulos_con_el_eje_x);

        // para ver los angulos entre lineas
        JCheckBox jcb_mostrar_angulos_entre_lineas = new JCheckBox(ANGULOS_ENTRE_LINEAS);
        jcb_mostrar_angulos_entre_lineas.setSelected(true);
        jcb_mostrar_angulos_entre_lineas.addMouseListener(new Eventos(this, elementos_UI));
        panelPuntosDeControl.add(jcb_mostrar_angulos_entre_lineas);


        panel_de_controles.add(panelPuntosDeControl);
        /////

        ////// truco para que encaje ... que porqueria!!!!
        //JTable tableEspacio = new JTable();
        //JScrollPane scrollEspacio = new JScrollPane(tableEspacio,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        //panel_de_controles.add(scrollEspacio);
        //////

        //JInternalFrame jif_panel_de_dibujo=new JInternalFrame("panel_de_dibujo");
        //jif_panel_de_dibujo.setLayout(new BorderLayout());
        panel_de_dibujo=new Panel_de_dibujo("panel_de_dibujo", this);
        //panel_de_dibujo.setLayout(null);
        //panel_de_dibujo.setBounds(5+(int)(ancho/4),5,(int)(ancho*3/4-40),(int)(alto-100) );
        panel_de_dibujo.addMouseListener(new Eventos_Panel_de_dibujo(this, elementos_UI));
        panel_de_dibujo.addMouseMotionListener(new Eventos_Panel_de_dibujo(this, elementos_UI));
        panel_de_dibujo.setFocusable(true);
        panel_de_dibujo.addKeyListener(new RedoUndoKeyListener());
        panel_de_dibujo.setVisible(true);
        //jif_panel_de_dibujo.setVisible(true);
        //jif_panel_de_dibujo.setContentPane(panel_de_dibujo);
        //panel_de_controles.pack();
        //scroll_panel_de_controles = new JScrollPane(panel_de_controles);
        //Dimension dim = new Dimension(Elementos_UI.splitDividerLoation,
        //			);
        //scroll_panel_de_controles.setSize();


        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        //splitPane.setLeftComponent(scroll_panel_de_controles);
        splitPane.setLeftComponent(panel_de_controles);
        //splitPane.setRightComponent(jif_panel_de_dibujo);
        splitPane.setRightComponent(panel_de_dibujo);
        splitPane.setDividerLocation(Elementos_UI.splitDividerLoation);

        //add(panel_de_controles);
        //add(panel_de_dibujo);
        this.add(splitPane, BorderLayout.CENTER);
    }




    public void set_nombre_del_modelo( String s )
    {
        jta_nombre_del_modelo.setText(s);
    }
    public String get_nombre_del_modelo()
    {
        return jta_nombre_del_modelo.getText();
    }
    public void pintar_puntos()
    {
        panel_de_dibujo.repaint();
    }

    public void dibujar_punto(double x, double y)
    {
        panel_de_dibujo.dibujar_punto(x,y);
    }

    public void mover_punto(double x, double y)
    {
        panel_de_dibujo.mover_punto(x,y);
    }

    public void mover_todos_los_puntos(double x, double y)
    {
        panel_de_dibujo.mover_todos_los_puntos(x,y);
    }

    public void set_punto_inicial_puntos(Point2D point2D)
    {
        panel_de_dibujo.set_punto_inicial_puntos(point2D);
    }

    public boolean esta_este_punto_en_la_lista(double x, double y)
    {
        return panel_de_dibujo.esta_este_punto_en_la_lista(x,y);
    }

    public void aplicarZoom(String zoom)
    {
        panel_de_dibujo.aplicarZoom(zoom);
        jl_zoom.setText(zoom+"%");
    }

    public void mostrarPuntosDeControl(boolean isSelected)
    {
        panel_de_dibujo.mostrarPuntosDeControl(isSelected);
    }

    public void mostrarDistancias(boolean isSelected)
    {
        panel_de_dibujo.mostrarDistancias(isSelected);
    }

    public void mostrarAngulosEjeX(boolean isSelected)
    {
        panel_de_dibujo.mostrarAngulosEjeX(isSelected);
    }

    public void mostrarAngulosEntreLineas(boolean isSelected)
    {
        panel_de_dibujo.mostrarAngulosEntreLineas(isSelected);
    }



    public void setSliderPOS_INI(int posInicial)
    {
        js_zoom.setValueIsAdjusting(true);
        js_zoom.setValue(posInicial);
        js_zoom.setValueIsAdjusting(false);
    }

    public void calcularEstrella(int nroPuntas, int lado, int salto)
    {
        panel_de_dibujo.calcularEstrella(nroPuntas, lado, salto);
    }
    public void calcularEstrella1(int nroPuntas, int lado, int salto)
    {
        panel_de_dibujo.calcularEstrella1(nroPuntas, lado, salto);
    }

    public void calcularEneagono(int nroPuntas, int lado, int salto)
    {
        panel_de_dibujo.calcularEneagono(nroPuntas, lado, salto);
    }

    public void calcularEneagono1(int nroPuntas, int lado, int salto)
    {
        panel_de_dibujo.calcularEneagono1(nroPuntas, lado, salto);
    }


}