package com.cc.fractal2d_editor.Eventos_fractales;

import com.cc.fractal2d_editor.IO_fractales.Abrir_fractales;
import com.cc.fractal2d_editor.IO_fractales.Guardar_fractales;
import com.cc.fractal2d_editor.IO_fractales.Guardar_fractales_como_PNG;
import com.cc.fractal2d_editor.Paneles_fractales.Patron_de_dibujo.Panel_resultado;
import com.cc.fractal2d_editor.Rutinas.VentaDeCrearEneagono;
import com.cc.fractal2d_editor.Rutinas.VentaDeCrearEstrella;
import com.cc.fractal2d_editor.command.ListaDeAcciones;
import com.cc.fractal2d_editor.command.StatePointsButtonCommand;
import com.cc.fractal2d_editor.Paneles_fractales.Elementos_UI;
import com.cc.fractal2d_editor.Paneles_fractales.Patron_inicial.Panel_patron_inicial;
import com.cc.fractal2d_editor.Rutinas.VentanaDeCrearRutina;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;

public class Eventos implements ActionListener, MouseListener, MouseMotionListener, ChangeListener//KeyListener
{
    Elementos_UI elementos_ui;
    Panel_patron_inicial panel_patron;

    //boolean estamos_en_Panel_patron_inicial=false;


    public Eventos(Panel_patron_inicial panel_patron_inicial,Elementos_UI elementos)
    {
        elementos_ui=elementos;
        this.panel_patron = panel_patron_inicial;
    }

    public void actionPerformed(ActionEvent e)
    {
        elementos_ui.panel_patron_inicial.panel_de_dibujo.repaint();//.pintar_puntos();

        Object o=e.getSource();

        if(o instanceof JMenuItem)
        {
            JMenuItem jmi=(JMenuItem)o;

            if(jmi.getText().equals("Abrir"))
            {System.out.println ("Abrir");
                elementos_ui.abrir_fractales=new Abrir_fractales(elementos_ui);
            }

            else if(jmi.getText().equals("Guardar"))
            {System.out.println ("Guardar");
                elementos_ui.guardar_fractales=new Guardar_fractales(elementos_ui);
            }

            else if(jmi.getText().equals("Guardar como PNG"))
            {System.out.println ("Guardar como PNG");
                elementos_ui.guardar_fractales_como_PNG=new Guardar_fractales_como_PNG(elementos_ui);
            }

            else if(jmi.getText().equals("Salir"))
            {
                int i=JOptionPane.showConfirmDialog(null,"Los Archivos que no se hayan guardado se perderan  \n desea Salir de todas formas?");

                if(i==0)
                {
                    System.exit(0);
                }
            }


            else if(jmi.getText().equals("rutina 1"))
            {
                //elementos_ui.ejecutarRutina1();
                VentanaDeCrearRutina.getInstance().setElementosUI(elementos_ui);
                VentanaDeCrearRutina.getInstance().mostrar();
            }

            else if(jmi.getText().equals("estrellas"))
            {
			 /*
			 String nroDePuntas = JOptionPane.showInputDialog(null,
					  										  "Dibujamos una Estrella",
					  										  "numero de puntas",
					  										  JOptionPane.QUESTION_MESSAGE);

			 String lado = JOptionPane.showInputDialog(null,
						  							   "logitud del lado",
						  							   "longitud de lado",
						  							   JOptionPane.QUESTION_MESSAGE);

			 String salto = JOptionPane.showInputDialog(null,
					   								   "salto",
					   								   "salto valor",
					   								   JOptionPane.QUESTION_MESSAGE);
			 */
                VentaDeCrearEstrella.getInstance().setElementosUI(elementos_ui);
                VentaDeCrearEstrella.getInstance().mostrar();
                //elementos_ui.panel_patron_inicial.calcularEstrella(Integer.parseInt(nroDePuntas),
                //												   Integer.parseInt(lado),
                //												   Integer.parseInt(salto));
            }

            else if(jmi.getText().equals("eNeagonos"))
            {
                VentaDeCrearEneagono.getInstance().setElementosUI(elementos_ui);
                VentaDeCrearEneagono.getInstance().mostrar();
			/*
			 String nroDePuntas = JOptionPane.showInputDialog(null,
					  										  "Dibujamos un eNeagono",
					  										  "numero de aristas(lados) o esquinas",
					  										  JOptionPane.QUESTION_MESSAGE);

			 String lado = JOptionPane.showInputDialog(null,
						  							   "logitud del lado",
						  							   "longitud de lado",
						  							   JOptionPane.QUESTION_MESSAGE);

			 String salto = JOptionPane.showInputDialog(null,
						   							   "salto",
						   							   "salto valor",
						   							   JOptionPane.QUESTION_MESSAGE);


			elementos_ui.panel_patron_inicial.calcularEneagono(Integer.parseInt(nroDePuntas),
															   Integer.parseInt(lado),
															   Integer.parseInt(salto));
			*/

            }

        }

        if(o instanceof JRadioButtonMenuItem)
        {
            JRadioButtonMenuItem jrbmi=(JRadioButtonMenuItem)o;

            if(jrbmi.getText().equals(Elementos_UI.TRES_TABBEDS))
            {
                elementos_ui.switchPERSPECTIVA(elementos_ui.PERSPECTIVA_TRES_TABBEDS);
            }
            if(jrbmi.getText().equals(Elementos_UI.UN_SOLO_PANEL))
            {
                elementos_ui.switchPERSPECTIVA(elementos_ui.PERSPECTIVA_UN_SOLO_PANEL);
            }
        }

		/*
		// esto es para el zoom
		if( o instanceof JComboBox )
		{
			JComboBox temp=(JComboBox) o;
			String actionCommand = temp.getActionCommand();

			if(panel_patron == elementos_ui.panel_patron_inicial)
			{
			elementos_ui.panel_patron_inicial.aplicarZoom((String)elementos_ui.panel_patron_inicial.jcb_zoom.getSelectedItem());
			}
			else
			if(panel_patron == elementos_ui.panel_patron_recursivo)
			{
			elementos_ui.panel_patron_recursivo.aplicarZoom((String)elementos_ui.panel_patron_recursivo.jcb_zoom.getSelectedItem());
			}
		}
		*/
    }

    //MouseListener
    public synchronized void mouseClicked(MouseEvent e)
    {
        //System.out.println ( "mouseClicked en Eventos \n   "+e.getSource() );


        if( e.getSource() instanceof JButton )
        {
            JButton temp=(JButton)e.getSource();
            String aux=temp.getText();

            //pintar_puntos();


            if(aux.equals("colocar_puntos"))
            {
                StatePointsButtonCommand insertpoint = new StatePointsButtonCommand(panel_patron,
                        false,
                        true,
                        false,
                        false);
                insertpoint.execute();
                ListaDeAcciones.getInstance().undoStackPush(insertpoint);
				/*
				undoStack.push(insertCommand);
				redoStack.clear();
				*/

				/*
				if(panel_patron == elementos_ui.panel_patron_inicial)
				//if(elementos_ui.tabbedpane.getSelectedIndex()==elementos_ui.PANEL_PATRON_INICIAL)
				{
				elementos_ui.panel_patron_inicial.panel_de_dibujo.borrar_puntos=false;
				elementos_ui.panel_patron_inicial.panel_de_dibujo.mas_puntos=true;
				elementos_ui.panel_patron_inicial.panel_de_dibujo.mover_puntos=false;
				elementos_ui.panel_patron_inicial.panel_de_dibujo.borrar_todo=false;
				}
				else
				if(panel_patron == elementos_ui.panel_patron_recursivo)
				//if(elementos_ui.tabbedpane.getSelectedIndex()==elementos_ui.PANEL_PATRON_RECURSIVO)
				{
				elementos_ui.panel_patron_recursivo.panel_de_dibujo.borrar_puntos=false;
				elementos_ui.panel_patron_recursivo.panel_de_dibujo.mas_puntos=true;
				elementos_ui.panel_patron_recursivo.panel_de_dibujo.mover_puntos=false;
				elementos_ui.panel_patron_recursivo.panel_de_dibujo.borrar_todo=false;
				}
				*/
            }

            if(aux.equals("mover_puntos"))
            {
                StatePointsButtonCommand movepoint = new StatePointsButtonCommand(panel_patron,
                        false,
                        false,
                        true,
                        false);
                movepoint.execute();
                ListaDeAcciones.getInstance().undoStackPush(movepoint);

				/*
				//if(elementos_ui.tabbedpane.getSelectedIndex()==elementos_ui.PANEL_PATRON_INICIAL)
				if(panel_patron == elementos_ui.panel_patron_inicial)
				{
				elementos_ui.panel_patron_inicial.panel_de_dibujo.borrar_puntos=false;
				elementos_ui.panel_patron_inicial.panel_de_dibujo.mover_puntos=true;
				elementos_ui.panel_patron_inicial.panel_de_dibujo.mas_puntos=false;
				elementos_ui.panel_patron_inicial.panel_de_dibujo.borrar_todo=false;
				}
				else
				//if(elementos_ui.tabbedpane.getSelectedIndex()==elementos_ui.PANEL_PATRON_RECURSIVO)
				if(panel_patron == elementos_ui.panel_patron_recursivo)
				{
				elementos_ui.panel_patron_recursivo.panel_de_dibujo.borrar_puntos=false;
				elementos_ui.panel_patron_recursivo.panel_de_dibujo.mover_puntos=true;
				elementos_ui.panel_patron_recursivo.panel_de_dibujo.mas_puntos=false;
				elementos_ui.panel_patron_recursivo.panel_de_dibujo.borrar_todo=false;
				}
				*/
            }

            if(aux.equals("borrar_puntos"))
            {
                StatePointsButtonCommand deletepoint = new StatePointsButtonCommand(panel_patron,
                        true,
                        false,
                        false,
                        false);
                deletepoint.execute();
                ListaDeAcciones.getInstance().undoStackPush(deletepoint);

				/*
				//if(elementos_ui.tabbedpane.getSelectedIndex()==elementos_ui.PANEL_PATRON_INICIAL)
				if(panel_patron == elementos_ui.panel_patron_inicial)
				{
				elementos_ui.panel_patron_inicial.panel_de_dibujo.borrar_puntos=true;
				elementos_ui.panel_patron_inicial.panel_de_dibujo.mas_puntos=false;
				elementos_ui.panel_patron_inicial.panel_de_dibujo.mover_puntos=false;
				elementos_ui.panel_patron_inicial.panel_de_dibujo.borrar_todo=false;
				}
				else
				//if(elementos_ui.tabbedpane.getSelectedIndex()==elementos_ui.PANEL_PATRON_RECURSIVO)
				if(panel_patron == elementos_ui.panel_patron_recursivo)
				{
				elementos_ui.panel_patron_recursivo.panel_de_dibujo.borrar_puntos=true;
				elementos_ui.panel_patron_recursivo.panel_de_dibujo.mas_puntos=false;
				elementos_ui.panel_patron_recursivo.panel_de_dibujo.mover_puntos=false;
				elementos_ui.panel_patron_recursivo.panel_de_dibujo.borrar_todo=false;
				}
				*/
            }

            if(aux.equals("borrar_todo"))
            {
                StatePointsButtonCommand deleteallpoints = new StatePointsButtonCommand(panel_patron,
                        false,
                        false,
                        false,
                        true);
                deleteallpoints.execute();
                ListaDeAcciones.getInstance().undoStackPush(deleteallpoints);

				/*
				//if(elementos_ui.tabbedpane.getSelectedIndex()==elementos_ui.PANEL_PATRON_INICIAL)
				if(panel_patron == elementos_ui.panel_patron_inicial)
				{
				elementos_ui.panel_patron_inicial.panel_de_dibujo.borrar_puntos=false;
				elementos_ui.panel_patron_inicial.panel_de_dibujo.mas_puntos=false;
				elementos_ui.panel_patron_inicial.panel_de_dibujo.mover_puntos=false;
				elementos_ui.panel_patron_inicial.panel_de_dibujo.borrar_todo=true;

				elementos_ui.panel_patron_inicial.dibujar_punto(0, 0);
				}
				else
				//if(elementos_ui.tabbedpane.getSelectedIndex()==elementos_ui.PANEL_PATRON_RECURSIVO)
				if(panel_patron == elementos_ui.panel_patron_recursivo)
				{
				elementos_ui.panel_patron_recursivo.panel_de_dibujo.borrar_puntos=false;
				elementos_ui.panel_patron_recursivo.panel_de_dibujo.mas_puntos=false;
				elementos_ui.panel_patron_recursivo.panel_de_dibujo.mover_puntos=false;
				elementos_ui.panel_patron_recursivo.panel_de_dibujo.borrar_todo=true;

				elementos_ui.panel_patron_recursivo.dibujar_punto(0, 0);
				}
				*/
                //elementos_ui.repaintPaneles_patrones();
                //elementos_ui.dibujar_punto(0, 0);
                //elementos_ui.panel_patron_recursivo.dibujar_punto(0, 0);
            }

//			if(aux.equals("aplicar zoom"))
//			{
//				if(panel_patron == elementos_ui.panel_patron_inicial)
//				{
//				elementos_ui.panel_patron_inicial.aplicarZoom(elementos_ui.panel_patron_inicial.jta_zoom.getText());
//				}
//				else
//				if(panel_patron == elementos_ui.panel_patron_recursivo)
//				{
//				elementos_ui.panel_patron_recursivo.aplicarZoom(elementos_ui.panel_patron_recursivo.jta_zoom.getText());
//				}
//			}

            if(aux.equals(Panel_resultado.CALCULAR))
            {
                //if(elementos_ui.tabbedpane.getSelectedIndex()==elementos_ui.PANEL_DE_DIBUJO)
                {
                    //set_calcular_fractales(true);
                    elementos_ui.calcular_fractales();
                }
            }
            if(aux.equals(Panel_resultado.DETENER))
            {
                System.out.println(this.getClass().getName()+":elementos_ui.detenerHilo();");
                elementos_ui.detenerHilo();
                //set_calcular_fractales(true);
                elementos_ui.detenerRutina1();
            }
            if(aux.equals(Panel_resultado.CONTINUAR))
            {
                //System.out.println(this.getClass().getName()+":elementos_ui.detenerHilo();");
                elementos_ui.continuarHilo();
                //set_calcular_fractales(true);
            }
            if(aux.equals(Panel_resultado.CLEAR))
            {
                //System.out.println(this.getClass().getName()+":elementos_ui.detenerHilo();");
                elementos_ui.clear();
                //set_calcular_fractales(true);
            }
            if(aux.equals(Panel_resultado.COLOR_LINEAS))
            {
                //System.out.println(this.getClass().getName()+":elementos_ui.detenerHilo();");
                Color color1 = elementos_ui.panel_de_dibujo.color_lineas.getBackground() ;
                color1 = JColorChooser.showDialog( null, "Seleccione un color", color1 );
                System.out.println(this.getClass().getName()+":color1="+color1);
                elementos_ui.setColorLineas_Panel_resultado(color1);
                //set_calcular_fractales(true);
            }

        }

        if( e.getSource() instanceof JCheckBox )
        {
            JCheckBox temp=(JCheckBox)e.getSource();
            boolean isSelected = temp.isSelected();

            if(temp.getText().equalsIgnoreCase(Panel_patron_inicial.PUNTOS))
            {
                if(panel_patron == elementos_ui.panel_patron_inicial)
                {
                    elementos_ui.panel_patron_inicial.mostrarPuntosDeControl(isSelected);
                }
                else
                if(panel_patron == elementos_ui.panel_patron_recursivo)
                {
                    elementos_ui.panel_patron_recursivo.mostrarPuntosDeControl(isSelected);
                }
            }

            if(temp.getText().equalsIgnoreCase(Panel_patron_inicial.DISTANCIAS))
            {
                if(panel_patron == elementos_ui.panel_patron_inicial)
                {
                    elementos_ui.panel_patron_inicial.mostrarDistancias(isSelected);
                }
                else
                if(panel_patron == elementos_ui.panel_patron_recursivo)
                {
                    elementos_ui.panel_patron_recursivo.mostrarDistancias(isSelected);
                }
            }

            if(temp.getText().equalsIgnoreCase(Panel_patron_inicial.ANGULOS_EJE_X))
            {
                if(panel_patron == elementos_ui.panel_patron_inicial)
                {
                    elementos_ui.panel_patron_inicial.mostrarAngulosEjeX(isSelected);
                }
                else
                if(panel_patron == elementos_ui.panel_patron_recursivo)
                {
                    elementos_ui.panel_patron_recursivo.mostrarAngulosEjeX(isSelected);
                }
            }

            if(temp.getText().equalsIgnoreCase(Panel_patron_inicial.ANGULOS_ENTRE_LINEAS))
            {
                if(panel_patron == elementos_ui.panel_patron_inicial)
                {
                    elementos_ui.panel_patron_inicial.mostrarAngulosEntreLineas(isSelected);
                }
                else
                if(panel_patron == elementos_ui.panel_patron_recursivo)
                {
                    elementos_ui.panel_patron_recursivo.mostrarAngulosEntreLineas(isSelected);
                }
            }
        }


    }

	/*
	public void set_calcular_fractales(boolean b)
	{
		elementos_ui.set_calcular_fractales(b);
	}
	*/

    public void mouseEntered(MouseEvent e)
    {
        //System.out.println ( "mouseEntered \n   "+ e.getSource() );
	/*	Object o=e.getSource();
		if(o==elementos_gui.)
		{
		}

	   else

	*/
    }
    public void mouseExited(MouseEvent e)
    {
        //System.out.println ( "mouseExited \n   "+ e.getSource() );
    }
    public void mousePressed(MouseEvent e)
    {
        //	System.out.println ( "mousePressed \n   "+ e.getSource() );
    }

    public void mouseReleased(MouseEvent e)
    {
        //	System.out.println ( "mouseReleased \n   "+ e.getSource() );
    }

    //ChangeListener
    int vez=0;

    public void stateChanged( ChangeEvent e )
    {
        if( e.getSource() instanceof JSlider )
        {
            // esto es para el zoom
            JSlider temp=(JSlider) e.getSource();
            String value = ""+temp.getValue();
            if(temp.getValue()==0)
            {
                value=""+1;
            }

            if(panel_patron == elementos_ui.panel_patron_inicial)
            {
                //System.out.println(this.getClass().getName()+":stateChanged temp.getValue()="+temp.getValue());
                elementos_ui.panel_patron_inicial.aplicarZoom(""+value);
            }
            else
            if(panel_patron == elementos_ui.panel_patron_recursivo)
            {
                //System.out.println(this.getClass().getName()+":stateChanged temp.getValue()="+temp.getValue());
                elementos_ui.panel_patron_recursivo.aplicarZoom(""+value);
            }
        }
    }

    public void mouseMoved( MouseEvent e )
    {
        vez++;
        pintar_puntos();
    }
    public void mouseDragged( MouseEvent e )
    {
    }
    public void keyReleased( KeyEvent e )
    {
    }


    public void pintar_puntos()
    {


        {
            if(elementos_ui.tabbedpane.getSelectedIndex()==elementos_ui.PANEL_PATRON_INICIAL)
            {
                if(vez>2)
                    elementos_ui.panel_patron_inicial.panel_de_dibujo.repaint();//pintar_puntos();
            }
            else
            if(elementos_ui.tabbedpane.getSelectedIndex()==elementos_ui.PANEL_PATRON_RECURSIVO)
            {
                if(vez>2)//la primera vez da nullPointerException porque algun objeto no se a ejemplarizado (instanciado)
                    elementos_ui.panel_patron_recursivo.panel_de_dibujo.repaint();//.pintar_puntos();
            }
        }

    }


}