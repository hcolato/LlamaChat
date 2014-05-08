package server;

/* 
 * Proyecto: LlamaChat
 * Programa: Lee configuraciones del servidor LlamaChat y
 * almacena las modificaciones.
 * 
 * Universisdad Francisco Gavidia
 * Mayo 7 del 2014
 */



//Eventos de de los controles.
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Para el Manejo de archivos, lecturas y escriitura
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.FileReader;


//Para el manejo de de expresiones
import java.util.regex.Matcher;
import java.util.regex.Pattern;


//Para el manejo de controles form
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;






public class EditarConfiguracion {

	//Declaracion de los componentes
	private JFrame frame;
	private JTextField txtPuerto;
	private JTextField txtArchivoLog;
	private JTextField txtRutaLog;
	private JLabel lblNewLabel_5;
	private JTextField txtContrasena;
	private JLabel lblNewLabel_6;
	private JTextField txtArchivoUsuarios;
	private JLabel lblSala;
	private JTextField txtSala;
	private JLabel lblNewLabel_8;
	private JTextField txtMensaje;

	//Variables usadas para almacenar los
	//valores de lectura
	static String strPort="";
	static String strSysLogFile="";
	static String strChatLogPath="";
	static String strAllowAdmin="";
	static String strPassPhrase="";
	static String strUserExportFile="";
	static String strDefaultChannel="";
	static String strWelcomeMessage="";
	
	//Definicion del archivo de configuracion.
	static String strFile="llamachatconf.xml";
	 
	
	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditarConfiguracion window = new EditarConfiguracion();
					window.frame.setVisible(true);
					
			
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	
	private JButton btnCargar;
	{
		
		
	}
	
	public  boolean vacio()
	{
		boolean r = txtArchivoLog.getText().isEmpty();
		r |= txtArchivoUsuarios.getText().isEmpty();
		r |= txtContrasena.getText().isEmpty();
		r |= txtMensaje.getText().isEmpty();
		r |= txtPuerto.getText().isEmpty();
		r |= txtRutaLog.getText().isEmpty();
		r |= txtSala.getText().isEmpty();
		
		return r;
		
		
	}
	
	//Proceso que Lee un archivo XML irregular
	static void LeerchivoXML() 
	{
		int Sigue=0;
		FileInputStream fstream = null;
		try {
			fstream = new FileInputStream(strFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
		String strLine;
	
		//Patron para lectura los valores guardados entre comillas dobles
		 Pattern pattern = Pattern.compile("\"(.*?)\"");
		
		try {
			while ((strLine = br.readLine()) != null)   {
				//Filtrado segun el patron
			    Matcher matcher = pattern.matcher(strLine);
			    	while (matcher.find()) {
			    		
			    		//Nos aseguramos que esta en la linea correcta para obtener el valor
			    		//del patron y asigna el valor a las variables definidas en el scope.
			    		if (strLine.indexOf("Port")>0)
			    		{
			    			strPort=matcher.group(1).toString(); 
			    		}
			    		else if (strLine.indexOf("SysLogFile") >0)
			    		{
			    			strSysLogFile=matcher.group(1).toString(); 
			    		}
			    		else if (strLine.indexOf("ChatLogPath") >0)
			    		{
			    			strChatLogPath=matcher.group(1).toString(); 
			    		}
			    		else if (strLine.indexOf("AllowAdmin") >0)
			    		{
			    			strAllowAdmin=matcher.group(1).toString(); 
			    		}
			    		else if (strLine.indexOf("PassPhrase") >0)
			    		{
			    			strPassPhrase=matcher.group(1).toString(); 
			    		}
			    		else if (strLine.indexOf("UserExportFile") >0)
			    		{
			    			strUserExportFile=matcher.group(1).toString(); 
			    		}
			    		else if (strLine.indexOf("DefaultChannel") >0)
			    		{
			    			strDefaultChannel=matcher.group(1).toString(); 
			    		}
			       }
			    	
			    
			    	//Por ser un xml irregular cambiamos el metodo de busqueda.
			    	//para encontrar el ultimo valor.
			    	if (Sigue==1)
			    	{
			    		strWelcomeMessage=strLine.toString();
			    		Sigue+=1;
			    	}
			    	else
			    	{
			    		if (strLine.indexOf("WelcomeMessage")>1)
				    	{
				    		Sigue+=1;
				    		System.out.println(strLine);
				    	}
			    		
			    	}
			}  	
			
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
	
		} catch (IOException e) {
			e.printStackTrace();
		}
     }

	/**
	 * Create the application.
	 */
	public EditarConfiguracion() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 511, 425);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null); //centramos el frame en la pantalla.
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("LlamaChat Configuracion");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(108, 27, 290, 16);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Puerto:");
		lblNewLabel_1.setBounds(18, 81, 61, 16);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Archivo Log:");
		lblNewLabel_2.setBounds(18, 107, 88, 16);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Ruta del Log:");
		lblNewLabel_3.setBounds(18, 135, 88, 16);
		frame.getContentPane().add(lblNewLabel_3);
		
		//Valido que solo permita numeros.
		txtPuerto = new JTextField();
		txtPuerto.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				char c = arg0.getKeyChar();
			      if (!((c >= '0') && (c <= '9') ||
			         (c == KeyEvent.VK_BACK_SPACE) ||
			         (c == KeyEvent.VK_DELETE))) {
			        arg0.consume();
			      }
			}
		});
		txtPuerto.setBounds(153, 75, 72, 28);
		frame.getContentPane().add(txtPuerto);
		txtPuerto.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Permite Admin:");
		lblNewLabel_4.setBounds(18, 163, 96, 16);
		frame.getContentPane().add(lblNewLabel_4);
		
		txtArchivoLog = new JTextField();
		txtArchivoLog.setBounds(153, 101, 314, 28);
		frame.getContentPane().add(txtArchivoLog);
		txtArchivoLog.setColumns(10);
		
		txtRutaLog = new JTextField();
		txtRutaLog.setBounds(153, 129, 314, 28);
		frame.getContentPane().add(txtRutaLog);
		txtRutaLog.setColumns(10);
		
		lblNewLabel_5 = new JLabel("Contrase\u00F1a:");
		lblNewLabel_5.setBounds(18, 191, 96, 16);
		frame.getContentPane().add(lblNewLabel_5);
		
		txtContrasena = new JTextField();
		txtContrasena.setBounds(153, 185, 134, 28);
		frame.getContentPane().add(txtContrasena);
		txtContrasena.setColumns(10);
		
		lblNewLabel_6 = new JLabel("Archivo de Usuarios:");
		lblNewLabel_6.setBounds(18, 223, 130, 16);
		frame.getContentPane().add(lblNewLabel_6);
		
		txtArchivoUsuarios = new JTextField();
		txtArchivoUsuarios.setBounds(153, 217, 314, 28);
		frame.getContentPane().add(txtArchivoUsuarios);
		txtArchivoUsuarios.setColumns(10);
		
		lblSala = new JLabel("Sala por Defecto:");
		lblSala.setBounds(18, 251, 130, 16);
		frame.getContentPane().add(lblSala);
		
		txtSala = new JTextField();
		txtSala.setBounds(153, 245, 134, 28);
		frame.getContentPane().add(txtSala);
		txtSala.setColumns(10);
		
		lblNewLabel_8 = new JLabel("Mensaje:");
		lblNewLabel_8.setBounds(18, 283, 61, 16);
		frame.getContentPane().add(lblNewLabel_8);
		
		txtMensaje = new JTextField();
		txtMensaje.setBounds(153, 277, 314, 56);
		frame.getContentPane().add(txtMensaje);
		txtMensaje.setColumns(10);
		
		//este no puede ser reasignado con otros valores por eso
		//se declara como final.
		final JComboBox cboPermiteAdmin = new JComboBox();
		cboPermiteAdmin.setModel(new DefaultComboBoxModel(new String[] {"", "YES", "NO"}));
		cboPermiteAdmin.setBounds(153, 159, 134, 27);
		frame.getContentPane().add(cboPermiteAdmin);
		
		
		//Guardar la informacion 
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			//inicio
				
				if (vacio()==true)
				{
					
					JOptionPane.showMessageDialog(frame,
							"Existen campos vacios!, favor completar." ,
		        			 "LlamaChat Configuracion",
		        			    JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				try
	             {
					//Leemos el archivo a modificar y guardamosbel contenido
					//en la variable oldtext
					File file = new File(strFile);
					BufferedReader reader = new BufferedReader(new FileReader(file));
					String line = "", oldtext = "";
					while((line = reader.readLine()) != null)
	                 {
						oldtext += line + "\r\n";
	                 }
					//cerramos la lectura
					reader.close();
	             
					//Procedemos a buscar y reeemplazar los valores modificados
					//en el archivo a escribir.
					FileWriter writer = new FileWriter(strFile);
	             
					//Buscamos con las variables leidas en un inicio y las reemplazamos
					//por los valores actuales de los controles o cajas de texto.
					String newtext = oldtext.replaceAll(strPort, txtPuerto.getText());
					newtext =newtext.replaceAll(strSysLogFile, txtArchivoLog.getText());
					//newtext =newtext.replaceAll(strAllowAdmin, txtPermiteAdmin.getText());
					newtext =newtext.replaceAll(strAllowAdmin,  cboPermiteAdmin.getSelectedItem().toString());
					newtext =newtext.replaceAll(strDefaultChannel, txtSala.getText());
					newtext =newtext.replaceAll(strPassPhrase, txtContrasena.getText());
					newtext =newtext.replaceAll(strUserExportFile, txtArchivoUsuarios.getText());
					newtext=newtext.replaceAll(strWelcomeMessage, txtMensaje.getText());
	             
					writer.write(newtext);
					writer.close();
					JOptionPane.showMessageDialog(frame,
						    "Registro Actualizado.",
						    "LlamaChat Configuracion",
						    JOptionPane.WARNING_MESSAGE);
					
					
	         }
	         catch (IOException ioe)
	             {
	        	 
	        	 JOptionPane.showMessageDialog(frame,
	        			 ioe.getMessage() ,
	        			 "LlamaChat Configuracion",
	        			    JOptionPane.ERROR_MESSAGE);
	        	 
	        	 	
	             }
				
			//fin	
			}
		});
		btnGuardar.setBounds(281, 357, 117, 29);
		frame.getContentPane().add(btnGuardar);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnSalir.setBounds(388, 357, 117, 29);
		frame.getContentPane().add(btnSalir);
		
		
		btnCargar = new JButton("Cargar");
		btnCargar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Proceso de hace el Pars del archivo xml.
				LeerchivoXML();
				
				//Asigna los valores a las cajas de texto
				txtPuerto.setText(strPort); 
				txtArchivoLog.setText(strSysLogFile);
				txtRutaLog.setText(strChatLogPath);
				cboPermiteAdmin.setSelectedItem(strAllowAdmin.toUpperCase());
				txtContrasena.setText(strPassPhrase);
				txtArchivoUsuarios.setText(strUserExportFile);
				txtSala.setText(strDefaultChannel);
				txtMensaje.setText(strWelcomeMessage);
				
			}
		});
		btnCargar.setBounds(6, 357, 117, 29);
		frame.getContentPane().add(btnCargar);
		
		
	}
}

