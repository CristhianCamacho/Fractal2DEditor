package com.cristhian.camacho.Paneles_fractales.Patron_de_dibujo;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

public class Tabla {
    
    Panel_resultado panel_de_dibujo;
    
    JTable jt_tabla_de_variables;
	JScrollPane scroll_de_la_tabla;
	
	int numero_de_columnas;
	int numero_de_filas;
	
    
    Object[][] data={ {"max","-1","-3","0"} , {"E#1","1","-2","-4"}, {"E#2","3","1","9"} } ;
	
    public Tabla(Panel_resultado p , int nf)
    {
    panel_de_dibujo=p;		
    agregar_tabla(nf);
	}
	
	public void agregar_tabla( int nf )
	{
		
	
		
	JLabel L_10=new JLabel("signos de los angulos");
	L_10.setBounds(10,320-20+50,200,20);	
	jt_tabla_de_variables= new JTable(crear_tablita(nf,2));
	//jt_tabla_de_variables.setBounds(20,300,(int)(ancho/4),(int)(alto/1.2));
	
	
	scroll_de_la_tabla = new JScrollPane(jt_tabla_de_variables,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);		
	
	scroll_de_la_tabla.setBounds(10,320+50,150,150);
	
	panel_de_dibujo.panel_de_controles.add(L_10);
	panel_de_dibujo.panel_de_controles.add(scroll_de_la_tabla);
		
	}
	
	public TableModel crear_tablita(int nf,int nc)
	{
	final String[] headers = crear_headers(nc);
	data = crear_tabla_de_objetos(nf,nc);
	
	TableModel dataModel = new AbstractTableModel() {
					public int getColumnCount() {
						return headers.length; }
					public int getRowCount() { return data.length;}
					public Object getValueAt(int row, int col) {
						return data[row][col];}
					public String getColumnName(int column) {
						return headers[column];}
										 
					public boolean isCellEditable(int row, int col) {
						return (true/*col!=0*/);
						/*return (col==1);*/}
					public void setValueAt(Object aValue, int row,int column) {
						data[row][column] = aValue;}
						};
		
	return dataModel;				
		
	}
	
	public Object[][] crear_tabla_de_objetos( int f, int c)
	{
		Object[][] data_dalida= new Object[f][c];
		
		
		//for ( int i=0; i<data_dalida.length ; i++ )
		//	if(i==0) data_dalida[0][0]=""+funcion_objetivo;
		//	else	data_dalida[i][0]="E#"+(i);
			
		
		if(data.length<=data_dalida.length)
		{
			
			//System.out.println("Panel_grafico:crear_tabla_de_objetos data_dalida.length="+data_dalida.length+" data_dalida[0].length="+data_dalida[0].length);
			
		for ( int i=0; i<data.length ; i++ )
			for ( int j=1;j<data[0].length;j++ )
					data_dalida[i][j]=data[i][j];
		}
		else 
		if(data.length>data_dalida.length)		
		{
		
			//System.out.println("Panel_grafico:crear_tabla_de_objetos data_dalida.length="+data_dalida.length+" data_dalida[0].length="+data_dalida[0].length);
			
		for ( int i=0; i<data_dalida.length ; i++ )
			for ( int j=1;j<data_dalida[0].length;j++ )
					data_dalida[i][j]=data[i][j];
			
		}		
			
				
		return data_dalida;
		
	}
	
		public TableModel crear_tabla_con_objetos(Object[][] o, int nf, int nc)
	{
				
		numero_de_columnas=nc;
		System.out.println("numero_de_columnas "+numero_de_columnas);		
		numero_de_filas=nf;
		System.out.println("numero_de_filas "+numero_de_filas);
		
									
		final String[] headers = crear_headers(nc);
		
		data = crear_tabla_de_objetos(nf,nc);
		
		for ( int i=0;i<data.length;i++ )
			for ( int j=0;j<data[0].length;j++ )
				{if(i==0 && j==0) data[0][0]="";//+funcion_objetivo;
				else {
					  	   data[i][j]=o[i][j];
				  	 }
				}				
		TableModel dataModel = new AbstractTableModel() {
					public int getColumnCount() {
						return headers.length; }
					public int getRowCount() { return data.length;}
					public Object getValueAt(int row, int col) {
						return data[row][col];}
					public String getColumnName(int column) {
						return headers[column];}								 
					public boolean isCellEditable(int row, int col) {
						return (true/*col!=0*/);}
					public void setValueAt(Object aValue, int row,int column) {
						data[row][column] = aValue;}
						};
						
												
	return dataModel;
	
	}
	
	public void redimensionar_tabla(int nf, int nc)
{
	panel_de_dibujo.panel_de_controles.remove(scroll_de_la_tabla);
	
	TableModel t=crear_tablita(nf,nc);
	
	jt_tabla_de_variables.setModel(t);
	
	scroll_de_la_tabla=null;
	
	scroll_de_la_tabla = new JScrollPane(jt_tabla_de_variables,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);		
	
	scroll_de_la_tabla.setBounds(10,320,150,200);
	
	panel_de_dibujo.panel_de_controles.add(scroll_de_la_tabla);
	
		
}

	public boolean existen_datos()
	{ Boolean salida=new Boolean(true);
	
		for(int i=0;i<data.length;i++)
			for(int j=0;j<data[0].length;j++)
				if ( ((String)data[i][j]).equalsIgnoreCase("") )
					salida=new Boolean(false);
					
	return salida.booleanValue();
	}
	
	public String[] crear_headers( int c1 )
	{
		int c=c1;
		String[] salida=new String[c];
		
		salida[0]=" # ";
		
		for(int i=1;i<c-1;i++)
			{
				salida[i]="Y"+i;
			}
			
		salida[c-1]="Signo";
		
				
		return salida;
	}
    
    public String[] get_signos()
    {
    	String[] salida=new String[data.length];
    	
    	if(existen_datos())
    	{
    		for ( int i=0;i<data.length;i++ )
					salida[i]=String.valueOf(data[i][1]);    		
    		
    	}
    	
    	return salida;
    }
}
