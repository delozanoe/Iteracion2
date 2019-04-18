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
import java.awt.Color;
import java.awt.Desktop;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.jdo.JDODataStoreException;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import org.apache.log4j.Logger;

//import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import uniandes.isis2304.parranderos.negocio.CadenaHotelera;
import uniandes.isis2304.parranderos.negocio.Convencion;
import uniandes.isis2304.parranderos.negocio.Hotel;
import uniandes.isis2304.parranderos.negocio.TipoHabitacion;
import uniandes.isis2304.parranderos.negocio.TipoServicio;





/**
 * Clase principal de la interfaz
 * @author Germán Bravo
 */
@SuppressWarnings("serial")

public class InterfazHotelAndessApp extends JFrame implements ActionListener
{
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger log = Logger.getLogger(InterfazHotelAndessApp.class.getName());

	/**
	 * Ruta al archivo de configuración de la interfaz
	 */
	private static final String CONFIG_INTERFAZ = "./src/main/resources/config/interfaceConfigApp.json"; 

	/**
	 * Ruta al archivo de configuración de los nombres de tablas de la base de datos
	 */
	private static final String CONFIG_TABLAS = "./src/main/resources/config/TablaBD_A.json"; 

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * Objeto JSON con los nombres de las tablas de la base de datos que se quieren utilizar
	 */
	private JsonObject tableConfig;

	/**
	 * Asociación a la clase principal del negocio.
	 */
	private CadenaHotelera cadenaHotelera;

	/* ****************************************************************
	 * 			Atributos de interfaz
	 *****************************************************************/
	/**
	 * Objeto JSON con la configuración de interfaz de la app.
	 */
	private JsonObject guiConfig;

	/**
	 * Panel de despliegue de interacción para los requerimientos
	 */
	private PanelDatos panelDatos;

	/**
	 * Menú de la aplicación
	 */
	private JMenuBar menuBar;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * Construye la ventana principal de la aplicación. <br>
	 * <b>post:</b> Todos los componentes de la interfaz fueron inicializados.
	 */
	public InterfazHotelAndessApp( )
	{
		// Carga la configuración de la interfaz desde un archivo JSON
		guiConfig = openConfig ("Interfaz", CONFIG_INTERFAZ);

		// Configura la apariencia del frame que contiene la interfaz gráfica
		configurarFrame ( );
		if (guiConfig != null) 	   
		{
			crearMenu( guiConfig.getAsJsonArray("menuBar") );
		}

		tableConfig = openConfig ("Tablas BD", CONFIG_TABLAS);

		cadenaHotelera = new CadenaHotelera(tableConfig);

		String path = guiConfig.get("bannerPath").getAsString();
		panelDatos = new PanelDatos ( );

		setLayout (new BorderLayout());
		add (new JLabel (new ImageIcon (path)), BorderLayout.NORTH );          
		add( panelDatos, BorderLayout.CENTER );        
	}

	/* ****************************************************************
	 * 			Métodos de configuración de la interfaz
	 *****************************************************************/
	/**
	 * Lee datos de configuración para la aplicació, a partir de un archivo JSON o con valores por defecto si hay errores.
	 * @param tipo - El tipo de configuración deseada
	 * @param archConfig - Archivo Json que contiene la configuración
	 * @return Un objeto JSON con la configuración del tipo especificado
	 * 			NULL si hay un error en el archivo.
	 */
	private JsonObject openConfig (String tipo, String archConfig)
	{
		JsonObject config = null;
		try 
		{
			Gson gson = new Gson( );
			FileReader file = new FileReader(archConfig);

			JsonReader reader = new JsonReader ( file );
			config = gson.fromJson(reader, JsonObject.class);
			log.info ("Se encontró un archivo de configuración válido: " + tipo);
		} 
		catch (Exception e)
		{
			e.printStackTrace ();
			log.info ("NO se encontró un archivo de configuración válido");			
			JOptionPane.showMessageDialog(null, "No se encontró un archivo de configuración de interfaz válido: " + tipo, "Cadena hotelera App", JOptionPane.ERROR_MESSAGE);
		}	
		return config;
	}

	/**
	 * Método para configurar el frame principal de la aplicación
	 */
	private void configurarFrame(  )
	{
		int alto = 0;
		int ancho = 0;
		String titulo = "";	

		if ( guiConfig == null )
		{
			//    		log.info ( "Se aplica configuración por defecto" );			
			titulo = "CadenaHotelera APP Default";
			alto = 300;
			ancho = 500;
		}
		else
		{
			//			log.info ( "Se aplica configuración indicada en el archivo de configuración" );
			titulo = guiConfig.get("title").getAsString();
			alto= guiConfig.get("frameH").getAsInt();
			ancho = guiConfig.get("frameW").getAsInt();
		}

		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		setLocation (50,50);
		setResizable( true );
		setBackground( Color.WHITE );

		setTitle( titulo );
		setSize ( ancho, alto);        
	}

	/**
	 * Método para crear el menú de la aplicación con base em el objeto JSON leído
	 * Genera una barra de menú y los menús con sus respectivas opciones
	 * @param jsonMenu - Arreglo Json con los menùs deseados
	 */
	private void crearMenu(  JsonArray jsonMenu )
	{    	
		// Creación de la barra de menús
		menuBar = new JMenuBar();       
		for (JsonElement men : jsonMenu)
		{
			// Creación de cada uno de los menús
			JsonObject jom = men.getAsJsonObject(); 

			String menuTitle = jom.get("menuTitle").getAsString();        	
			JsonArray opciones = jom.getAsJsonArray("options");

			JMenu menu = new JMenu( menuTitle);

			for (JsonElement op : opciones)
			{       	
				// Creación de cada una de las opciones del menú
				JsonObject jo = op.getAsJsonObject(); 
				String lb =   jo.get("label").getAsString();
				String event = jo.get("event").getAsString();

				JMenuItem mItem = new JMenuItem( lb );
				mItem.addActionListener( this );
				mItem.setActionCommand(event);

				menu.add(mItem);
			}       
			menuBar.add( menu );
		}        
		setJMenuBar ( menuBar );	
	}

	/* ****************************************************************
	 * 			CRUD de TipoBebida
	 *****************************************************************/


	/* ****************************************************************
	 * 			Métodos administrativos
	 *****************************************************************/
	/**
	 * Muestra el log de Parranderos
	 */
	public void mostrarLogParranderos ()
	{
		mostrarArchivo ("cadenaHotelera.log");
	}

	/**
	 * Muestra el log de datanucleus
	 */
	public void mostrarLogDatanuecleus ()
	{
		mostrarArchivo ("datanucleus.log");
	}

	/**
	 * Limpia el contenido del log de parranderos
	 * Muestra en el panel de datos la traza de la ejecución
	 */
	public void limpiarLogParranderos ()
	{
		// Ejecución de la operación y recolección de los resultados
		boolean resp = limpiarArchivo ("cadenaHotelera.log");

		// Generación de la cadena de caracteres con la traza de la ejecución de la demo
		String resultado = "\n\n************ Limpiando el log de cadena Hotelera ************ \n";
		resultado += "Archivo " + (resp ? "limpiado exitosamente" : "NO PUDO ser limpiado !!");
		resultado += "\nLimpieza terminada";

		panelDatos.actualizarInterfaz(resultado);
	}

	/**
	 * Limpia el contenido del log de datanucleus
	 * Muestra en el panel de datos la traza de la ejecución
	 */
	public void limpiarLogDatanucleus ()
	{
		// Ejecución de la operación y recolección de los resultados
		boolean resp = limpiarArchivo ("datanucleus.log");

		// Generación de la cadena de caracteres con la traza de la ejecución de la demo
		String resultado = "\n\n************ Limpiando el log de datanucleus ************ \n";
		resultado += "Archivo " + (resp ? "limpiado exitosamente" : "NO PUDO ser limpiado !!");
		resultado += "\nLimpieza terminada";

		panelDatos.actualizarInterfaz(resultado);
	}

	/**
	 * Limpia todas las tuplas de todas las tablas de la base de datos de parranderos
	 * Muestra en el panel de datos el número de tuplas eliminadas de cada tabla
	 */
	public void limpiarBD ()
	{
		try 
		{
			// Ejecución de la demo y recolección de los resultados
			long eliminados [] = cadenaHotelera.limpiarCadenaHotelera();

			// Generación de la cadena de caracteres con la traza de la ejecución de la demo
			String resultado = "\n\n************ Limpiando la base de datos ************ \n";
			resultado += eliminados [0] + " Consumo habitacion de un servicio eliminados\n";
			resultado += eliminados [1] + " Producto consumo por habitacion eliminados\n";
			resultado += eliminados [2] + " Servicio consumo eliminados\n";
			resultado += eliminados [3] + " Servicio producto eliminadas\n";
			resultado += eliminados [4] + " Cliente eliminados\n";
			resultado += eliminados [5] + " Consumo por Habitacion eliminados\n";
			resultado += eliminados [6] + " Empleado eliminados\n";
			resultado += eliminados [7] + " Habitacion eliminados\n";
			resultado += eliminados [8] + " Hotel eliminados\n";
			resultado += eliminados [9] + " Plan consumo eliminados\n";
			resultado += eliminados [10] + " Producto eliminados\n";
			resultado += eliminados [11] + " Reserva Habitacion eliminados\n";
			resultado += eliminados [12] + " Reserva servicio eliminadas\n";
			resultado += eliminados [13] + " Servicio eliminados\n";
			resultado += eliminados [14] + " Usuario eliminados\n";
			resultado += "\nLimpieza terminada";

			panelDatos.actualizarInterfaz(resultado);
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/**
	 * Muestra la presentación general del proyecto
	 */
	public void mostrarPresentacionGeneral ()
	{
		mostrarArchivo ("data/00-ST-CadenaHoteleraJDO.pdf");
	}

	/**
	 * Muestra el modelo conceptual de Parranderos
	 */
	public void mostrarModeloConceptual ()
	{
		mostrarArchivo ("data/Modelo Conceptual Cadena Hotelera.pdf");
	}

	/**
	 * Muestra el esquema de la base de datos de Parranderos
	 */
	public void mostrarEsquemaBD ()
	{
		mostrarArchivo ("data/Esquema BD Cadena Hotelera.pdf");
	}

	/**
	 * Muestra el script de creación de la base de datos
	 */
	public void mostrarScriptBD ()
	{
		mostrarArchivo ("data/EsquemaCadenaHotelera.sql");
	}

	/**
	 * Muestra la arquitectura de referencia para Parranderos
	 */
	public void mostrarArqRef ()
	{
		mostrarArchivo ("data/ArquitecturaReferencia.pdf");
	}

	/**
	 * Muestra la documentación Javadoc del proyectp
	 */
	public void mostrarJavadoc ()
	{
		mostrarArchivo ("doc/index.html");
	}

	/**
	 * Muestra la información acerca del desarrollo de esta apicación
	 */
	public void acercaDe ()
	{
		String resultado = "\n\n ************************************\n\n";
		resultado += " * Universidad	de	los	Andes	(Bogotá	- Colombia)\n";
		resultado += " * Departamento	de	Ingeniería	de	Sistemas	y	Computación\n";

		resultado += " * \n";		
		resultado += " * Curso: isis2304 - Sistemas Transaccionales\n";
		resultado += " * Proyecto: Cadena Hotelera Uniandes\n";
		resultado += " * @version 1.0\n";
		resultado += " * @author Valentian Duarte, Daniel Lozano";
		resultado += " * Marzo de 2019\n";
		resultado += " * \n";

		resultado += "\n ************************************\n\n";

		panelDatos.actualizarInterfaz(resultado);		
	}

	public void reservarAlojamientoServicio()
	{

		try 
		{
			JPanel ventana = new JPanel(new BorderLayout());
			String[] items = {"1. Especial", "2. Ejecutivo", "3. Premium", "4. Platinum", "5. Diamante", "6. Rubí"};
			JComboBox<String> combo = new JComboBox<>(items);
			JTextField tematica = new JTextField( );
			JTextField numeroParticipantes = new JTextField( );
			JTextField fechaInicio = new JTextField( );
			JTextField fechaFin = new JTextField( );
			List<Hotel> hoteless =cadenaHotelera.darHoteles();
			if(hoteless.isEmpty())
			{
				System.out.println("Paila perro");
			}
			String[] hoteles = new String[hoteless.size()];
			for (int i = 0; i < hoteless.size(); i++) 
			{
				hoteles[i] = hoteless.get(i).getCiudad();
			}
			JComboBox<String> combo2 = new JComboBox<>(hoteles);

			JPanel datosConvencion = new JPanel(new GridLayout(0, 1));


			//            panel.add(combo);
			datosConvencion.add(new JLabel("1. Tematica:"));
			datosConvencion.add(tematica);
			datosConvencion.add(new JLabel("2. Numero Participantes:"));
			datosConvencion.add(numeroParticipantes);
			datosConvencion.add(new JLabel("3. Fecha inicio (Use el formato yyyy-MM-dd hh:mm:ss.SSS):"));
			datosConvencion.add(fechaInicio);
			datosConvencion.add(new JLabel("4. Fecha fin (Use el formato yyyy-MM-dd hh:mm:ss.SSS):"));
			datosConvencion.add(fechaFin);
			datosConvencion.add(new JLabel("5. Cuenta (INICIAR ESTO EN 0):"));

			datosConvencion.add(new JLabel("6. Paz y salvo (INICIAR EN TRUE)"));
			datosConvencion.add(new JLabel("7. Plan de consumo:"));
			datosConvencion.add(combo);
			datosConvencion.add(new JLabel("8. Selecione el hotel"));
			datosConvencion.add(combo2);
			
			datosConvencion.setBorder(new TitledBorder("Datos requeridos"));

			JCheckBox piscina = new JCheckBox("Piscina"); 
			JCheckBox gimnasio = new JCheckBox("Gimnasio"); 
			JCheckBox internet = new JCheckBox("Internet"); 
			JCheckBox bar = new JCheckBox("Bar"); 
			JCheckBox restaurante = new JCheckBox("Restaurante"); 
			JCheckBox supermercado = new JCheckBox("Supermercado"); 
			JCheckBox tienda = new JCheckBox("Tienda"); 
			JCheckBox spa = new JCheckBox("SPA"); 
			JCheckBox lavanderia = new JCheckBox("Lavanderia"); 
			JCheckBox prestamo = new JCheckBox("Prestamo");
			JCheckBox salonReuniones = new JCheckBox("Salon reuniones");
			JCheckBox salonConferencias = new JCheckBox("Salon conferencias");


			JPanel servicios = new JPanel(new GridLayout(0, 1));

			servicios.setBorder(new TitledBorder("Servicios requeridos"));
			servicios.add(piscina);
			servicios.add(gimnasio);
			servicios.add(internet);
			servicios.add(bar);
			servicios.add(restaurante);
			servicios.add(supermercado);
			servicios.add(tienda);
			servicios.add(spa);
			servicios.add(lavanderia);
			servicios.add(prestamo);
			servicios.add(salonReuniones);
			servicios.add(salonConferencias);

			JTextField SUITEPRESIDENCIAL = new JTextField( );
			JTextField SUITE = new JTextField( );
			JTextField FAMILIAR = new JTextField( );
			JTextField DOBLE = new JTextField( );
			JTextField SENCILLA = new JTextField( );

			JPanel habitacion = new JPanel(new GridLayout(0, 1));


			//            panel.add(combo);
			habitacion.setBorder(new TitledBorder("Habitacion requeridos"));
			habitacion.add(new JLabel("1. Suite Presicencial:"));
			habitacion.add(SUITEPRESIDENCIAL);
			habitacion.add(new JLabel("2. Suite"));
			habitacion.add(SUITE);
			habitacion.add(new JLabel("3. Familiar"));
			habitacion.add(FAMILIAR);
			habitacion.add(new JLabel("4. Doble"));
			habitacion.add(DOBLE);
			habitacion.add(new JLabel("5. Sencilla"));
			habitacion.add(SENCILLA);


			ventana.add(datosConvencion, BorderLayout.NORTH);
			ventana.add(servicios,BorderLayout.CENTER);
			ventana.add(habitacion, BorderLayout.SOUTH);

			Hashtable<Long, Long> tiposHabitacion = new Hashtable<>();
			tiposHabitacion.put(new Long(1),  new Long(Integer.parseInt(SUITEPRESIDENCIAL.getText())));
			tiposHabitacion.put(new Long(2),  new Long(Integer.parseInt(SUITE.getText())));
			tiposHabitacion.put(new Long(3),  new Long(Integer.parseInt(FAMILIAR.getText())));
			tiposHabitacion.put(new Long(4),  new Long(Integer.parseInt(DOBLE.getText())));
			tiposHabitacion.put(new Long(5),  new Long(Integer.parseInt(SENCILLA.getText())));

			ArrayList<Long> tiposServicios =  new ArrayList<>();
			if(piscina.isSelected())
			{
				tiposServicios.add(new Long(1));
			}
			if(gimnasio.isSelected())
			{
				tiposServicios.add(new Long(2));
			}

			if(internet.isSelected())
			{
				tiposServicios.add(new Long(3));
			}
			if(bar.isSelected())
			{
				tiposServicios.add(new Long(4));
			}
			if(restaurante.isSelected())
			{
				tiposServicios.add(new Long(5));
			}
			if(supermercado.isSelected())
			{tiposServicios.add(new Long(6));

			}
			if(tienda.isSelected())
			{
				tiposServicios.add(new Long(7));
			}
			if(spa.isSelected())
			{
				tiposServicios.add(new Long(8));
			}
			if(lavanderia.isSelected())
			{
				tiposServicios.add(new Long(9));
			}
			if(prestamo.isSelected())
			{
				tiposServicios.add(new Long(10));
			}
			if(salonReuniones.isSelected())
			{
				tiposServicios.add(new Long(11));
			}
			if(salonConferencias.isSelected())
			{
				tiposServicios.add(new Long(12));
			}


			int result = JOptionPane.showConfirmDialog(null, ventana, "Registrar la convencion",
					JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
			if (result == JOptionPane.OK_OPTION)
			{

				if (result != 0)
				{
					cadenaHotelera.registrarConvencion(tiposHabitacion, tiposServicios, tematica.getText(), new Long(Integer.parseInt(numeroParticipantes.getText())), convertirADate(fechaInicio.getText()), convertirADate(fechaFin.getText()), new BigDecimal(0), "T", "I", combo.getSelectedIndex(), combo2.getSelectedIndex()); 

					String resultado = "En adicionarHabitacionesConvencion\n\n";
					resultado += "Las habitaciones para convencion fue adicionada exitosamente: ";
					resultado += "\n Operación terminada";
					panelDatos.actualizarInterfaz(resultado);
				}
			}
			else
			{
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			}

		}
		catch(Exception e)
		{
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}

	}

	public void cancelarReservasConvencion()
	{
		//SELECCIONAR LA CONVENCION
		JPanel panel = new  JPanel(new GridLayout(0, 1 ));
		JLabel titulo = new JLabel("Desea modificar o eliminar la reserva");
		JCheckBox eliminar = new JCheckBox("Eliminar");
		JCheckBox modifcar = new JCheckBox("Modificar");
		panel.add(titulo);
		panel.add(eliminar);
		panel.add(modifcar);
		JOptionPane.showConfirmDialog(null, panel, "Modificar o eliminar reserva",
				JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
		if(eliminar.isSelected())
		{
			System.out.println("ELIMINADO");
		}
		else if(modifcar.isSelected())
		{
			//			habitacionConvencion(convencion, "Modificar");
			//		servicioConvencion(convencion, "Modificar");
			System.out.println("MODIFICAR");
		}

	}


	private void servicioConvencion(Convencion convencion, String tipo)
	{
		try 
		{
			JCheckBox piscina = new JCheckBox("Piscina"); 
			JCheckBox gimnasio = new JCheckBox("Gimnasio"); 
			JCheckBox internet = new JCheckBox("Internet"); 
			JCheckBox bar = new JCheckBox("Bar"); 
			JCheckBox restaurante = new JCheckBox("Restaurante"); 
			JCheckBox supermercado = new JCheckBox("Supermercado"); 
			JCheckBox tienda = new JCheckBox("Tienda"); 
			JCheckBox spa = new JCheckBox("SPA"); 
			JCheckBox lavanderia = new JCheckBox("Lavanderia"); 
			JCheckBox prestamo = new JCheckBox("Prestamo");
			JCheckBox salonReuniones = new JCheckBox("Salon reuniones");
			JCheckBox salonConferencias = new JCheckBox("Salon conferencias");


			JPanel panel = new JPanel(new GridLayout(0, 1));


			panel.add(piscina);
			panel.add(gimnasio);
			panel.add(internet);
			panel.add(bar);
			panel.add(restaurante);
			panel.add(supermercado);
			panel.add(tienda);
			panel.add(spa);
			panel.add(lavanderia);
			panel.add(prestamo);
			panel.add(salonReuniones);
			panel.add(salonConferencias);

			if(tipo.equals("Registrar"))
			{

				int result = JOptionPane.showConfirmDialog(null, panel, "Registrar necesarios servicios convencion",
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
				if (result == JOptionPane.OK_OPTION)
				{

					if (result != 0)
					{
						//					convencion = cadenaHotelera.adicionarHabitacionConvencion(); 
						//					if (convencion == null)
						//					{
						//						throw new Exception ("No se puedo reservar las habitacines para la convencion " );
						//					}
						String resultado = "En adicionarHabitacionesConvencion\n\n";
						resultado += "Las habitaciones para convencion fue adicionada exitosamente: ";
						resultado += "\n Operación terminada";
						panelDatos.actualizarInterfaz(resultado);
					}
				}
				else
				{
					panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
				}
			}
			else if(tipo.equals("Modificar"))
			{
				int result = JOptionPane.showConfirmDialog(null, panel, "Registrar necesarios servicios convencion",
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
				if (result == JOptionPane.OK_OPTION)
				{

					if (result != 0)
					{
						//LLAMAR AL METODO DE ELIMINAR SERVICIOS
						//					convencion = cadenaHotelera.adicionarHabitacionConvencion(); 
						//					if (convencion == null)
						//					{
						//						throw new Exception ("No se puedo reservar las habitacines para la convencion " );
						//					}
						String resultado = "En adicionarHabitacionesConvencion\n\n";
						resultado += "Las habitaciones para convencion fue adicionada exitosamente: ";
						resultado += "\n Operación terminada";
						panelDatos.actualizarInterfaz(resultado);
					}
				}
				else
				{
					panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
				}
			}
		}
		catch(Exception e)
		{
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}
	private void habitacionConvencion(Convencion convencion, String tipo)
	{
		try 
		{

			JTextField SUITEPRESIDENCIAL = new JTextField( );
			JTextField SUITE = new JTextField( );
			JTextField FAMILIAR = new JTextField( );
			JTextField DOBLE = new JTextField( );
			JTextField SENCILLA = new JTextField( );

			JPanel panel = new JPanel(new GridLayout(0, 1));


			//            panel.add(combo);
			panel.add(new JLabel("1. Suite Presicencial:"));
			panel.add(SUITEPRESIDENCIAL);
			panel.add(new JLabel("2. Suite"));
			panel.add(SUITE);
			panel.add(new JLabel("3. Familiar"));
			panel.add(FAMILIAR);
			panel.add(new JLabel("4. Doble"));
			panel.add(DOBLE);
			panel.add(new JLabel("5. Sencilla"));
			panel.add(SENCILLA);
			if(tipo.equals("Registrar"))
			{
				int result = JOptionPane.showConfirmDialog(null, panel, "Registrar habitaciones convencion",
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
				if (result == JOptionPane.OK_OPTION)
				{

					if (result != 0)
					{

						//						convencion = cadenaHotelera.regis; 
						if (convencion == null)
						{
							throw new Exception ("No se puedo reservar las habitacines para la convencion " );
						}
						String resultado = "En adicionarHabitacionesConvencion\n\n";
						resultado += "Las habitaciones para convencion fue adicionada exitosamente: ";
						resultado += "\n Operación terminada";
						panelDatos.actualizarInterfaz(resultado);
					}
				}
				else
				{
					panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
				}
			}
			else if(tipo.equals("Modificar"))
			{
				int result = JOptionPane.showConfirmDialog(null, panel, "Eliminar habitaciones convencion",
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
				if (result == JOptionPane.OK_OPTION)
				{

					if (result != 0)
					{
						//LLAMAR ELIMINAR CONVENCION

						//					convencion = cadenaHotelera.adicionarHabitacionConvencion(); 
						//					if (convencion == null)
						//					{
						//						throw new Exception ("No se puedo reservar las habitacines para la convencion " );
						//					}
						String resultado = "En adicionarHabitacionesConvencion\n\n";
						resultado += "Las habitaciones para convencion fue adicionada exitosamente: ";
						resultado += "\n Operación terminada";
						panelDatos.actualizarInterfaz(resultado);
					}
				}
				else
				{
					panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
				}
			}
		}
		catch(Exception e)
		{
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}


	private Convencion registrarConvencion( )
	{
		Convencion convencion = null; 
		try 
		{
			String[] items = {"1. Especial", "2. Ejecutivo", "3. Premium", "4. Platinum", "5. Diamante", "6. Rubí"};
			JComboBox<String> combo = new JComboBox<>(items);
			JTextField tematica = new JTextField( );
			JTextField numeroParticipantes = new JTextField( );
			JTextField fechaInicio = new JTextField( );
			JTextField fechaFin = new JTextField( );

			JPanel panel = new JPanel(new GridLayout(0, 1));


			//            panel.add(combo);
			panel.add(new JLabel("1. Tematica:"));
			panel.add(tematica);
			panel.add(new JLabel("2. Numero Participantes:"));
			panel.add(numeroParticipantes);
			panel.add(new JLabel("3. Fecha inicio (Use el formato yyyy-MM-dd hh:mm:ss.SSS):"));
			panel.add(fechaInicio);
			panel.add(new JLabel("4. Fecha fin (Use el formato yyyy-MM-dd hh:mm:ss.SSS):"));
			panel.add(fechaFin);
			panel.add(new JLabel("5. Cuenta (INICIAR ESTO EN 0):"));

			panel.add(new JLabel("6. Paz y salvo (INICIAR EN TRUE)"));
			panel.add(new JLabel("7. Plan de consumo:"));
			panel.add(combo);



			int result = JOptionPane.showConfirmDialog(null, panel, "Registrar convencion",
					JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
			if (result == JOptionPane.OK_OPTION)
			{
				System.out.println( " " + tematica.getText()   + " " + numeroParticipantes.getText());
				if (result != 0)
				{
					convencion = cadenaHotelera.adicionarConvencion(tematica.getText(), Integer.parseInt(numeroParticipantes.getText()), convertirADate(fechaInicio.getText()), convertirADate(fechaFin.getText()), new BigDecimal(0), "T", "I", combo.getSelectedIndex()); 
					if (convencion == null)
					{
						throw new Exception ("No se puedo adicionar la convencion " );
					}
					String resultado = "En adicionarConvencion\n\n";
					resultado += "La convencion fue adicionada exitosamente: " + convencion.getTematica() ;
					resultado += "\n Operación terminada";
					panelDatos.actualizarInterfaz(resultado);
				}
			}
			else
			{
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			}
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
		return convencion;
	}


	private  Timestamp convertirADate(String fecha)
	{

		Timestamp timestamp = null;
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
			java.util.Date parsedDate = dateFormat.parse(fecha);
			timestamp = new Timestamp(parsedDate.getTime());
		} catch(Exception e) { //this generic but you can control another types of exception
			// look the origin of excption 
		}
		return timestamp;
	}

	public void finalizarConvencion()
	{
		try
		{


			JPanel panel = new  JPanel(new GridLayout(0, 1 ));
			JLabel titulo = new JLabel("Check out de la convencion");
			List<Convencion> convenciones =cadenaHotelera.darConvenciones();
			if(convenciones.isEmpty())
			{
				System.out.println("Paila perro");
			}
			String[] items = new String[convenciones.size()];
			for (int i = 0; i < convenciones.size(); i++) 
			{
				items[i] = convenciones.get(i).getTematica();
			}

			JComboBox<String> combo = new JComboBox<>(items);

			panel.add(titulo);
			panel.add(combo);

			int result = JOptionPane.showConfirmDialog(null, panel, "Check out de la convencion",
					JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
			if (result == JOptionPane.OK_OPTION)
			{

			}

			else
			{
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}

	}

	public void registrarMantenimientoHabitacion()
	{
		JCheckBox piscina = new JCheckBox("Piscina"); 
		JCheckBox gimnasio = new JCheckBox("Gimnasio"); 
		JCheckBox internet = new JCheckBox("Internet"); 
		JCheckBox bar = new JCheckBox("Bar"); 
		JCheckBox restaurante = new JCheckBox("Restaurante"); 
		JCheckBox supermercado = new JCheckBox("Supermercado"); 
		JCheckBox tienda = new JCheckBox("Tienda"); 
		JCheckBox spa = new JCheckBox("SPA"); 
		JCheckBox lavanderia = new JCheckBox("Lavanderia"); 
		JCheckBox prestamo = new JCheckBox("Prestamo");
		JCheckBox salonReuniones = new JCheckBox("Salon reuniones");
		JCheckBox salonConferencias = new JCheckBox("Salon conferencias");

		JLabel tituloHab =  new JLabel("Dijite las habitaciones que se van a tener mantenimieto y seprarelas por una coma");
		JTextField habitacion = new JTextField( );


		JPanel servicios = new JPanel(new GridLayout(4, 2));
		JPanel habitaciones = new JPanel(new GridLayout(0,1));
		JPanel panel = new JPanel(new BorderLayout());

		servicios.add(piscina);
		servicios.add(gimnasio);
		servicios.add(internet);
		servicios.add(bar);
		servicios.add(restaurante);
		servicios.add(supermercado);
		servicios.add(tienda);
		servicios.add(spa);
		servicios.add(lavanderia);
		servicios.add(prestamo);
		servicios.add(salonReuniones);
		servicios.add(salonConferencias);

		servicios.setBorder(new TitledBorder("Servicios"));
		habitaciones.add(tituloHab);
		habitaciones.add(habitacion);
		habitaciones.setBorder(new TitledBorder("Habitaciones"));

		panel.add(servicios, BorderLayout.NORTH);
		panel.add(habitaciones, BorderLayout.SOUTH);


		int result = JOptionPane.showConfirmDialog(null, panel, "Selecione el servicio o habitacion en mantenimiento",
				JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

	}

	public void finalizarMantenimiento() 
	{

	}

	public void pocaDemandaServicios()
	{

	}

	public void analizarHotelAndes()
	{

	}

	public void buenosClientes()
	{

	}
	/* ****************************************************************
	 * 			Métodos privados para la presentación de resultados y otras operaciones
	 *****************************************************************/
	//    /**
	//     * Genera una cadena de caracteres con la lista de los tipos de bebida recibida: una línea por cada tipo de bebida
	//     * @param lista - La lista con los tipos de bebida
	//     * @return La cadena con una líea para cada tipo de bebida recibido
	//     */
	//    private String listarTiposBebida(List<VOTipoBebida> lista) 
	//    {
	//    	String resp = "Los tipos de bebida existentes son:\n";
	//    	int i = 1;
	//        for (VOTipoBebida tb : lista)
	//        {
	//        	resp += i++ + ". " + tb.toString() + "\n";
	//        }
	//        return resp;
	//	}

	/**
	 * Genera una cadena de caracteres con la descripción de la excepcion e, haciendo énfasis en las excepcionsde JDO
	 * @param e - La excepción recibida
	 * @return La descripción de la excepción, cuando es javax.jdo.JDODataStoreException, "" de lo contrario
	 */
	private String darDetalleException(Exception e) 
	{
		String resp = "";
		if (e.getClass().getName().equals("javax.jdo.JDODataStoreException"))
		{
			JDODataStoreException je = (javax.jdo.JDODataStoreException) e;
			return je.getNestedExceptions() [0].getMessage();
		}
		return resp;
	}

	/**
	 * Genera una cadena para indicar al usuario que hubo un error en la aplicación
	 * @param e - La excepción generada
	 * @return La cadena con la información de la excepción y detalles adicionales
	 */
	private String generarMensajeError(Exception e) 
	{
		String resultado = "************ Error en la ejecución\n";
		resultado += e.getLocalizedMessage() + ", " + darDetalleException(e);
		resultado += "\n\nRevise datanucleus.log y cadenaHotelera.log para más detalles";
		return resultado;
	}

	/**
	 * Limpia el contenido de un archivo dado su nombre
	 * @param nombreArchivo - El nombre del archivo que se quiere borrar
	 * @return true si se pudo limpiar
	 */
	private boolean limpiarArchivo(String nombreArchivo) 
	{
		BufferedWriter bw;
		try 
		{
			bw = new BufferedWriter(new FileWriter(new File (nombreArchivo)));
			bw.write ("");
			bw.close ();
			return true;
		} 
		catch (IOException e) 
		{
			//			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Abre el archivo dado como parámetro con la aplicación por defecto del sistema
	 * @param nombreArchivo - El nombre del archivo que se quiere mostrar
	 */
	private void mostrarArchivo (String nombreArchivo)
	{
		try
		{
			Desktop.getDesktop().open(new File(nombreArchivo));
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/* ****************************************************************
	 * 			Métodos de la Interacción
	 *****************************************************************/
	/**
	 * Método para la ejecución de los eventos que enlazan el menú con los métodos de negocio
	 * Invoca al método correspondiente según el evento recibido
	 * @param pEvento - El evento del usuario
	 */
	@Override
	public void actionPerformed(ActionEvent pEvento)
	{
		String evento = pEvento.getActionCommand( );		
		try 
		{
			Method req = InterfazHotelAndessApp.class.getMethod ( evento );			
			req.invoke ( this );
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		} 
	}

	/* ****************************************************************
	 * 			Programa principal
	 *****************************************************************/
	/**
	 * Este método ejecuta la aplicación, creando una nueva interfaz
	 * @param args Arreglo de argumentos que se recibe por línea de comandos
	 */
	public static void main( String[] args )
	{
		try
		{

			// Unifica la interfaz para Mac y para Windows.
			UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName( ) );
			InterfazHotelAndessApp interfaz = new InterfazHotelAndessApp( );
			interfaz.setVisible( true );
		}
		catch( Exception e )
		{
			e.printStackTrace( );
		}
	}
}
