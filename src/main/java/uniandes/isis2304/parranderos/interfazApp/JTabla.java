package uniandes.isis2304.parranderos.interfazApp;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.plaf.synth.SynthSeparatorUI;
import javax.swing.table.DefaultTableModel;

import uniandes.isis2304.parranderos.negocio.Cliente;

import javax.swing.JFrame; 
import java.awt.*; 
import java.awt.event.*;
import java.util.LinkedList;

public class JTabla extends JFrame {

	public JTabla(java.util.List<Cliente> clientes ) { 
		super("Ejemplo 1");

		//Array bidimensional de objetos con los datos de la tabla 
		
		
		Object[][] data =	
			{ 
				{
				
				}
				}; 
//		for (int i = 0; i < clientes.size(); i++) {
//			
//			Cliente actual = clientes.get(i);
//			String nombre = actual.getNombre();
//			System.out.println(nombre);
//			datos[0][1].setNombre(nombre);
//			System.out.println(datos[0][1].getNombre());
////			datos[i][1].setId(actual.getId());
//			datos[i][2].setTipoDocumento(actual.getTipoDocumento());	
//			datos[i][3].setNumeroDocumento(actual.getNumeroDocumento());
//			datos[i][4].setCorreo(actual.getCorreo());
//		}

		//Array de ‘String’ con los titulos de las columnas 
		String[] columnNames = {"#","Nombre", "Tipo documento", "Numero de documento", "Correo"};
		
		//creamos el modelo de tabla con los datos anteriores 
		DefaultTableModel dtm = new DefaultTableModel(data , columnNames); 
		//se crea la tabla con el defaultablemodel 
		JTable tabla = new JTable(dtm);

		 
//		//filas 
//		Object[] newRow = {"Pepe", "Grillo", "Tenis", new Integer(5), new Boolean(false), "Pera" }; 
//		dtm.addRow(newRow); 
		Cliente[][] datos = new Cliente[5][clientes.size()];
		System.out.println(datos[1].length);
		Cliente actual = null;
//		for (int i = 0; i < datos.length; i++) 
//		{
			for (int j = 0; j < clientes.size(); j++)
				{
				actual = clientes.get(j);
//				datos[i][j]= new Cliente(actual.getNombre(), actual.getId(), actual.getTipoDocumento(), actual.getNumeroDocumento(), actual.getCorreo());
//				System.out.println(datos[i][j].getNombre());
				Object[] newRow = {j+1,actual.getNombre(), actual.getTipoDocumento(), actual.getNumeroDocumento(), actual.getCorreo()};
				dtm.addRow(newRow);
				
			}
			
//		}
//		//o modificar una celda en especifico 
//		dtm.setValueAt("Catherine", 1, 1); 
		//se define el tamaño 
		tabla.setPreferredScrollableViewportSize(new Dimension(500, 70));

		//Creamos un JscrollPane y le agregamos la JTable 
		JScrollPane scrollPane = new JScrollPane(tabla);

		//Agregamos el JScrollPane al contenedor 
		getContentPane().add(scrollPane, BorderLayout.CENTER);

		//manejamos la salida 
		addWindowListener(new WindowAdapter() { 
		public void windowClosing(WindowEvent e) { 
		System.exit(0); 
		} 
		}); 
		}

//		public static void main(String ar[]) { 
//		JTabla frame = new JTabla(); 
//		frame.pack(); 
//		frame.setVisible(true);
//
//		} 
		
}