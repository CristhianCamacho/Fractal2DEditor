package com.cc.fractal2d_editor.IO_fractales;


import com.cc.fractal2d_editor.Paneles_fractales.Elementos_UI;

import javax.swing.*;
import java.io.File;

public class Guardar_fractales
{
	
	
	
	JFileChooser filechooser;
	private Filtro_fractales filtro = new Filtro_fractales();
	
	Elementos_UI elementos_UI;
	
	
	int valor;
	
	String nomb_model;

	public Guardar_fractales(Elementos_UI elementos)
	{
		
		elementos_UI=elementos;
		
		filechooser = new JFileChooser();
		
		filechooser.setFileFilter(filtro);
		
		File aux=new File(".");
		
		String aus=aux.getAbsolutePath();
		System.out.println("aus "+aus);
		
////////////////////////////////////////////////////////////////////////////////		
//		Administrador_de_E_S aes=new Administrador_de_E_S();
		
//		String dir_base=aes.obtener_directorio_base( aus );//"Prototipo_progLineal_0.9"
//		System.out.println("dir_base "+dir_base);
////////////////////////////////////////////////////////////////////////////////		
		
//		for(int i=0;i<aus.length()-dir_base.length();i++)
//			{
//				if( (aus.substring(i,i+dir_base.length())).equalsIgnoreCase(dir_base) )	
//				aus=aus.substring(0,i+dir_base.length());	
//			}				
//		System.out.println("actionPerformed:Abrir "+aus);
		
				
		//aus=aus.substring(0,aus.length()-2);
		//aux=null;
		//aux=new File(aus+"/src/com/cristhian/camacho/Modelos_fractales/");

		System.out.println( "System.getProperty(\"user.dir\")"+System.getProperty("user.dir") );
		String aux_2=System.getProperty("user.dir");

		if( aux_2.contains("out\\production") )
		{
			aux_2=aux_2.substring( 0 , aux_2.indexOf("out\\production") );
			aux_2+="Modelos_fractales";
		}
		else
		{
			aux_2+="/Modelos_fractales";
		}

		filechooser.setCurrentDirectory(new File(aux_2));
				
		nomb_model=elementos_UI.get_nombre_del_modelo();
		
		File aux_=new File (aux_2+"/"+nomb_model);
		
		filechooser.setSelectedFile(aux_);


		
		valor=filechooser.showSaveDialog(elementos_UI.jf_principal);
		
		try{
		nomb_model=filechooser.getSelectedFile().getName();
		
		elementos_UI.set_nombre_del_modelo(nomb_model);
		
		System.out.println(nomb_model);
		}catch(Exception e){System.out.println("Guardar:ERROR:"+e);} 
		
		verificar_valor();
		
		
		
		
	}
	
	public void guardar(String path)
	{
		File aux=new File(path);
		System.out.println(aux);
		
		String datos=elementos_UI.datos_para_guardar();
		
		System.out.println(datos);
		
		if(aux.exists())
		{

			int i=JOptionPane.showConfirmDialog(elementos_UI.jf_principal,"El Archivo ya existe \n desea Reemplazarlo?");

			if(i==0)
			{

				Flujo.escribir(elementos_UI.datos_para_guardar(),aux);
				Archivos_recientes.agregar_Archivo_reciente(aux);
			}
		}	
		else
		{
			Flujo.escribir(elementos_UI.datos_para_guardar(),aux);
			Archivos_recientes.agregar_Archivo_reciente(aux);
		}
	}
	public void verificar_valor()
	{
	
		
		if(valor==JFileChooser.APPROVE_OPTION)//aceptar
		{	
			//System.out.println(filechooser.getCurrentDirectory().getName());
			
			//guardar(filechooser.getCurrentDirectory().getName()+ File.separatorChar+nomb_model+".lineal");
					
			String aux=filechooser.getSelectedFile().getName();
			System.out.println(aux);
			
			try{
			if(aux.substring( aux.length()-filtro.extension.length() ).equalsIgnoreCase( filtro.extension ) )
			{ aux=aux.substring( 0 ,aux.length()-filtro.extension.length() );
				
			}
				}catch(Exception e){}
			 
			
			System.out.println(nomb_model);
			guardar(filechooser.getCurrentDirectory().getAbsolutePath()+File.separatorChar
			+aux+"."+filtro.extension);
			
			nomb_model=filechooser.getSelectedFile().getName();
			elementos_UI.set_nombre_del_modelo(nomb_model);
		
		}
	
	}
	
	public static void main(String arg[])
	{

	}

	
}