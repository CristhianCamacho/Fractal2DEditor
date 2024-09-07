package com.cc.fractal2d_editor.utils;

import com.cc.fractal2d_editor.Paneles_fractales.Elementos_UI;
import com.cc.fractal2d_editor.Paneles_fractales.Patron_de_dibujo.Panel_de_dibujo_resultado;
import com.cc.fractal2d_editor.Paneles_fractales.Patron_de_disenio.Panel_de_dibujo;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Vector;

public class Constants {

    public static File DIR_ACTUAL;
    public static File DIR_DE_PROPIEDADES;

    public static void set_Directorios()
    {
        DIR_ACTUAL = new File(System.getProperty("user.dir"));

        DIR_DE_PROPIEDADES = new File(DIR_ACTUAL, "propiedades");
    }

    public static Vector calcularEneagono1(
            int centroX,
            int centroY,
            int nroPuntas,
            int lado,
            int salto)
    {


        double saltoAngulo =2 * Math.PI / nroPuntas;

        // para que se vea vertical y no chueca
        double correccionAngulo = Math.PI/2 - saltoAngulo;

        Vector v_puntos = new Vector();
        Point2D[] puntos = new Point2D[nroPuntas];

        for(int i=0; i<nroPuntas; i++)
        {
            double _cos = Math.cos(correccionAngulo + saltoAngulo*i);
            double _sin = Math.sin(correccionAngulo + saltoAngulo*i);

            double x = centroX + lado*_cos;
            double y = centroY + lado*_sin;

            puntos[i]=new Point2D.Double( x , y );

            //System.out.println("("+x+", "+y+")");

//v_puntos.add(puntos[i].clone());
        }


//		para hallar las intersecciones entre lineas que parten de puntos adyacentes
        Point2D[] puntos3 = new Point2D[nroPuntas+1];
        if (salto>1) {
            for (int i = 0; i < nroPuntas + 1; i++) {

                if (i+salto==7) {
                    int a = 0;
                }

                puntos3[i] = inseccionEntre(puntos[(i) % nroPuntas],
                        puntos[(i + salto) % nroPuntas],
                        puntos[(i + 1) % nroPuntas],
                        puntos[(nroPuntas + i + 1 - salto) % nroPuntas]
                );
            }
        }

        int i_real = 0;
        // agregamos los puntos
        for(int i=0; i<nroPuntas; i++)
        {
            v_puntos.add(puntos[(i)%nroPuntas].clone());
            System.out.println("P("+(i_real++)+")= ("+puntos[(i)%nroPuntas].getX()+", "
                    +puntos[(i)%nroPuntas].getY()+")");
            if (salto>1) {
                v_puntos.add(puntos3[(i) % nroPuntas].clone());
                System.out.println("P("+(i_real++)+")= ("+puntos3[(i)%nroPuntas].getX()+", "
                        +puntos3[(i)%nroPuntas].getY()+")");
            }
            //v_puntos.add(puntos3[(i)%nroPuntas].clone());
            //v_puntos.add(puntos[(i+1)%nroPuntas].clone());
        }
        v_puntos.add(puntos[0].clone());
        System.out.println("P("+0+")= P("+(2*nroPuntas)+") = ("+puntos[(0)%nroPuntas].getX()+", "
                +puntos[(0)%nroPuntas].getY()+")");
        //v_puntos.add(puntos3[0].clone());




		/*
		//// addding n+1 points to the vector
		//v_puntos.add(new Point2D.Double( puntos[0].getX()+1 , puntos[0].getY()+1 ));
		int cont = 0;
		while(cont < nroPuntas)
		{
			v_puntos.add(puntos[(cont*salto)%nroPuntas]);
			cont++;
		}
		v_puntos.add(puntos[0]);

		this.repaint();
		*/
		return v_puntos;
    }

    public static Point2D inseccionEntre(Point2D punto1,
                                  Point2D punto2,
                                  Point2D punto3,
                                  Point2D punto4)
    {
        Point2D result = null;

        double m1;
        if( (punto1.getX() - punto2.getX())==0 )
        {
            m1 = 0;
        }
        else
        {
            m1 = ( punto1.getY() - punto2.getY() ) /
                    ( punto1.getX() - punto2.getX() );

        }

        double m2;
        if( (punto3.getX() - punto4.getX())==0 )
        {
            m2 = 0;
        }
        else
        {
            m2 = ( punto3.getY() - punto4.getY() ) /
                    ( punto3.getX() - punto4.getX() );

        }



        double x = ( m1 * punto2.getX() - punto2.getY() -
                m2 * punto4.getX() + punto4.getY()   ) /
                ( m1 - m2 );

        double y = m1 * (x - punto2.getX()) + punto2.getY();


        if ( m1 > 4.97582161916076E10 ) { // inifinite?
//x=punto3.getX();
            y = m2 * (punto2.getX() - punto3.getX()) + punto3.getY();
        }

        if ( m1==0 && m2>0.0) {
            x = punto2.getX();
            y = m2 * (x - punto3.getX()) + punto3.getY();
        }

        if ( m1<-0.0 && m2==0) {
            x = punto3.getX();
            y = m1 * (x - punto2.getX()) + punto2.getY();
        }

        if ( m1==-0.0 && Double.isInfinite(m2)) {
            x = punto3.getX();
            y = punto2.getY();
        }

        if ( m1==0.0 && m2==0) {
            x = punto2.getX();
            y = punto3.getY();
        }

        System.out.println("m1="+m1+", m2="+m2 + " --> ("+x+", "+y+")");


        result = new Point2D.Double(x, y);

        return result;
    }

    public static Shape crear_POLIGONO_CERRADO(Vector v_puntos, boolean cerrado)
    {
        int n=v_puntos.size();
        float[][] mPoints=new float[2][n];

        Iterator<Point2D> it = v_puntos.iterator();
        Point2D aux;
        int cont=0;
        while(it.hasNext())
        {
            aux = it.next();
            if(aux instanceof Point2D)
            {
                mPoints[0][cont]=(float)aux.getX();
                mPoints[1][cont]=(float)aux.getY();
            }
            cont++;
        }

        GeneralPath path = new GeneralPath(GeneralPath.WIND_NON_ZERO,
                mPoints.length);

        path.moveTo(mPoints[0][0], mPoints[1][0]);

        for (int i = 0; i < n ; i += 1)
        {
            path.lineTo(mPoints[0][i], mPoints[1][i]);
        }
        if (cerrado)
        {
            path.closePath();
        }

        return path;
    }

    public static Shape crear_POLIGONO_CERRADO_LinkedList(LinkedList<Point2D> lista_Point2D)
    {
        int n=lista_Point2D.size();
        float[][] mPoints=new float[2][n];
//System.out.println ( "Figura:crear_ARCO n="+n );

        Iterator<Point2D> it = lista_Point2D.iterator();
        Point2D aux;
        int cont=0;
        while(it.hasNext())
        {
            aux = it.next();
            if(aux instanceof Point2D)
            {
                mPoints[0][cont]=(float)aux.getX();
                mPoints[1][cont]=(float)aux.getY();
            }
            cont++;
        }

        GeneralPath path = new GeneralPath(GeneralPath.WIND_NON_ZERO,
                mPoints.length);

        path.moveTo(mPoints[0][0], mPoints[1][0]);

        for (int i = 0; i < n ; i += 1)
        {
            path.lineTo(mPoints[0][i], mPoints[1][i]);
        }
        path.closePath();

        return path;
    }

    public static int getMaxSalto(int n)
    {
        int iReturn = 1;

        //int n = Integer.parseInt((String)jcb_NroDePuntas.getSelectedItem());

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

    public static final float InitialBasicStrokeWidth = 1;
    public static final float InitialBasicStrokeMiterlimit = 3;
    public static final float[] InitialBasicStrokeDashArray = new float[] { 0, 1 };
    public static final float InitialBasicStrokeDash_phase = 0;
    public static BasicStroke initialBasicStroke =
            new BasicStroke(
                    InitialBasicStrokeWidth,
                    BasicStroke.CAP_ROUND, //BasicStroke.CAP_SQUARE,
                    BasicStroke.JOIN_ROUND, //BasicStroke.JOIN_MITER,
                    InitialBasicStrokeMiterlimit,
                    InitialBasicStrokeDashArray,
                    InitialBasicStrokeDash_phase);

    public static BasicStroke dashedBasicStroke =
            new BasicStroke(
                    2,
                    BasicStroke.CAP_BUTT,
                    BasicStroke.JOIN_BEVEL,
                    5,
                    new float[] { 5 },
                    5);

    public static BasicStroke getInitialBasicStroke()
    {
        return initialBasicStroke;
    }

    public static void setRenderingHConstants(Graphics2D g2d, boolean all) {
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));
        if (all)
        {
            //g2d.setRenderingHints(new RenderingHints(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON));
            //g2d.setRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));

            g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            //g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
            g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
            g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
            g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);

            g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE );
        }
    }

    public static void setRenderingHConstantsANTIALIAS_OFF(Graphics2D g2d) {
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
        g2d.setRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF));
    }

    public static void updatePanel_de_dibujoBackground()
    {
        Panel_de_dibujo_resultado panel_de_dibujo_resultado = Elementos_UI.instance.panel_de_dibujo.panel_de_dibujo;
        Panel_de_dibujo panel_de_dibujo_patron_inicial = Elementos_UI.instance.panel_patron_inicial.panel_de_dibujo;
        //Panel_de_dibujo panel_de_dibujo_patron_recursivo = elementos_UI.panel_patron_recursivo.panel_de_dibujo;

        try {

            Rectangle rectangle = new Rectangle(panel_de_dibujo_resultado.getLocationOnScreen().x,
                    panel_de_dibujo_resultado.getLocationOnScreen().y,
                    panel_de_dibujo_resultado.getSize().width,
                    panel_de_dibujo_resultado.getSize().height);
            Robot robot;
            try {
                robot = new Robot();
                BufferedImage bufferedImage = robot.createScreenCapture(rectangle);

                Image image = Toolkit.getDefaultToolkit().createImage(bufferedImage.getSource());
                panel_de_dibujo_resultado.setBackgroundBufferedImage(bufferedImage);
                panel_de_dibujo_resultado.setBackgroundImage(image);
                panel_de_dibujo_patron_inicial.setBackgroundImage(image);

            } catch (Exception e) {

                System.out.println(e);
            }

            //panel_de_dibujo_patron_recursivo.setBackgroundImage(image);

            //File file;
            // Save the screenshot as a png
            //file = new File("C:/Documents and Settings/Administrador/Escritorio/screen.png");
            //ImageIO.write(bufferedImage, "png", file);

            Elementos_UI.instance.setCalculandoFractales(false);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Vector aplicarRotacion(Vector v_puntos, int rotarGrados, int TAM)
    {
        Point2D punto_centro_de_gravedad = getCentroDeGravedad(v_puntos, TAM);

        Vector v_result = new Vector();
        for(int i=0; i<v_puntos.size(); i++)
        {
            Point2D punto_i = (Point2D) v_puntos.get(i);
            Double alpha_radianes = Math.toRadians( rotarGrados );

//System.out.println("Constants:aplicarRotacion alpha_radianes="+alpha_radianes);

            Double x = punto_centro_de_gravedad.getX() +
                    (punto_i.getX() - punto_centro_de_gravedad.getX() )*Math.cos( alpha_radianes )
                    - (punto_i.getY() - punto_centro_de_gravedad.getY() )*Math.sin( alpha_radianes );
            Double y = punto_centro_de_gravedad.getY() +
                    (punto_i.getX() - punto_centro_de_gravedad.getX() )*Math.sin( alpha_radianes )
                    + (punto_i.getY() - punto_centro_de_gravedad.getY() )*Math.cos( alpha_radianes );

            Point2D punto_fin = new Point2D.Double( x,y);
            v_result.add(punto_fin);
            //v_puntos.setElementAt(punto_fin, i);
        }

        return v_result;
    }

    public static Point2D getCentroDeGravedad(Vector v_puntos, int TAM)
    {
        Point2D result = null;
        double x_centro_de_gravedad = 0;
        double y_centro_de_gravedad = 0;

        int max_i=v_puntos.size();
        if(!v_puntos.isEmpty())
            if(get_punto_de_control((Point2D)v_puntos.get(0), TAM)
                    .contains((Point2D)v_puntos.get(v_puntos.size()-1)))
            {
                max_i=v_puntos.size()-1;
            }

        for(int i=0; i<max_i; i++)
        {
            Point2D punto_i = (Point2D) v_puntos.get(i);

            x_centro_de_gravedad += punto_i.getX();
            y_centro_de_gravedad += punto_i.getY();
        }
        x_centro_de_gravedad = x_centro_de_gravedad/max_i;
        y_centro_de_gravedad = y_centro_de_gravedad/max_i;

        result = new Point2D.Double(x_centro_de_gravedad ,
                y_centro_de_gravedad );

        return result;
    }

    public static Shape get_punto_de_control(Point2D p, int tam)
    {
        int lado=tam;
        return new Rectangle2D.Double( p.getX()-lado/2 , p.getY()-lado/2 , lado , lado );
    }

    public static boolean contieneElIndice(LinkedList<Integer> index_ListSelectedPoints, int i)
    {
        boolean result = false;
        for (Integer inte: index_ListSelectedPoints) {
            if (inte.intValue() == i) {
                result = true;
                break;
            }
        }

        return result;
    }

    public static void verificarLiveDrawing()
    {
        //
        if( Elementos_UI.instance.panel_de_dibujo.check_liveDrawing.isSelected() && !Elementos_UI.instance.getCalculandoFractales() ) {

            //if (Math.random()>0.75)
            {
                Elementos_UI.instance.clear();
            }

            Elementos_UI.instance.calcular_fractales();

        }
    }

    public static void DETENER_TODO()
    {
        Elementos_UI.instance.setCalculandoFractales(false);
        Elementos_UI.instance.detenerHilo();
        Elementos_UI.instance.detenerRutina1();
        Elementos_UI.instance.detenerRutina2();

        Elementos_UI.instance.detenerRutina1_tiling();
    }
}
