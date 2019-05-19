package uniandes.isis2304.parranderos.interfazApp;

/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad	de	los	Andes	(Bogotá	- Colombia)
 * Departamento	de	Ingeniería	de	Sistemas	y	Computación
 * Licenciado	bajo	el	esquema	Academic Free License versión 2.1
 * 		
 * Curso: isis2304 - Sistemas Transaccionales
 * Proyecto: Parranderos Uniandes
 * @version 1.0
 * @author Germán Bravo
 * Julio de 2018
 * 
 * Revisado por: Claudia Jiménez, Christian Ariza
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */



import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import uniandes.isis2304.parranderos.negocio.Cliente;

/**
 * Clase de interfaz para mostrar los resultados de la ejecución de las 
 * demostraciones implementadas
 *
 * @author Germán Bravo
 */
@SuppressWarnings("serial")
public class PanelDatos extends JPanel
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------


    // -----------------------------------------------------------------
    // Atributos de interfaz
    // -----------------------------------------------------------------
	/**
	 * �?rea de texto con barras de deslizamiento
	 */
	private JTextArea textArea;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el panel
     * 
     */
    public PanelDatos ()
    {
        setBorder (new TitledBorder ("Traza de las demostraciones"));
        setLayout( new BorderLayout( ) );
        
        textArea = new JTextArea("Aquí sale el resultado de la ejecución de las demos");
        textArea.setEditable(false);
        add (new JScrollPane(textArea), BorderLayout.CENTER);
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Actualiza el panel con la información recibida por parámetro.
     * @param texto El texto con el que actualiza el área
     */
    public void actualizarInterfaz (String texto)
    {
    	textArea.setText(texto);
    }
    
    public void actuInterfazCliente(List<Cliente> texto)
    {
    	
    	Cliente actual = null;
    	for (int i = 0; i < texto.size(); i++) 
    	{
    		actual = texto.get(i);
    		textArea.setText("El id del cliente: "+ actual.getId());
    		textArea.setText("Nombre cliente: " +  actual.getNombre() );
    		textArea.setText("Tipo de documento: "+ actual.getTipoDocumento());
    		textArea.setText("El numero de documento: "+ actual.getNumeroDocumento());
    		textArea.setText("El correo es: "+ actual.getCorreo());
    		textArea.setText("-------------------------------------------------------------------");
    		
		}
    }

}
