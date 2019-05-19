package uniandes.isis2304.parranderos.interfazApp;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import uniandes.isis2304.parranderos.negocio.Cliente;

import javax.swing.JFrame; 
import java.awt.*; 
import java.awt.event.*;
import java.util.LinkedList;

public class tabla extends JFrame {

	public tabla(java.util.List<Cliente> clientes ) { 
		super("Ejemplo 1");

		//Array bidimensional de objetos con los datos de la tabla 
		Cliente[][] datos = new Cliente[5][clientes.size()];
		for (int i = 0; i < clientes.size(); i++) {
			Cliente actual = clientes.get(i);
			datos[i][0].setNombre(actual.getNombre());
			datos[i][1].setId(actual.getId());
			datos[i][2].setTipoDocumento(actual.getTipoDocumento());
			datos[i][3].setNumeroDocumento(actual.getNumeroDocumento());
			datos[i][4].setCorreo(actual.getCorreo());
		}
		

		//Array de ‘String’ con los titulos de las columnas 
		String[] columnNames = {"Nombre", "Id", "Tipo documento", "Numero de documento", "Correo"};

		//Creacion de la tabla 
		final JTable table = new JTable(datos, columnNames); 
		table.setPreferredScrollableViewportSize(new Dimension(500, 80));

		//Creamos un scrollpanel y se lo agregamos a la tabla 
		JScrollPane scrollpane = new JScrollPane(table);

		//Agregamos el scrollpanel al contenedor 
		getContentPane().add(scrollpane, BorderLayout.CENTER);

		//manejamos la salida 
		addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent e) { 
				System.exit(0); 
			} 
		}); 
	}
}