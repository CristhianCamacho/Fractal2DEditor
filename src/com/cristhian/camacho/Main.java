package com.cristhian.camacho;

import com.cristhian.camacho.Paneles_fractales.Elementos_UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {

    public JFrame jf_principal;
    public Elementos_UI elementos_UI;


    public Main()
    {
        this.Principal_Fractales("Main Window");
        jf_principal.setBackground(Color.white);
    }

    public void Principal_Fractales(String s)
    {
        jf_principal=new JFrame("");
        Dimension dim=Toolkit.getDefaultToolkit().getScreenSize();
        jf_principal.setSize(new Dimension(dim.width-100,dim.height-100));

        jf_principal.setTitle(s);
        createUI();
    }

    public void createUI()
    {

        elementos_UI = new Elementos_UI(jf_principal);
        elementos_UI.center();


        jf_principal.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                //dispose();
                System.exit(0);
            }
        });


        jf_principal.setVisible(true);

    }

    public static void main(String[] args) {
        new Main();
    }
}
