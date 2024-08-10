package com.cc.fractal2d_editor.IO_fractales;

import java.io.*;
import javax.swing.filechooser.FileFilter;

public class Filtro_fractales extends FileFilter{

	public String extension="FRACTALES";
	
	public String getDescription(){
		return "."+extension;
	}
	
	public boolean accept( File archivo ){
		boolean res = false;
		
		String nombre = archivo.getName();

		if( nombre.endsWith("."+extension) || archivo.isDirectory() /*|| archivo.isFile()*/ )
		{
			res = true;

			System.out.println(nombre);
		}

		return res;
	}
	
}