package clases;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import net.miginfocom.swing.MigLayout;

public class VentanaAgenda extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField txtNombre;
	private JTextField txtTelefono;
	private JButton btnAnadir;
	private JButton btnEliminar;
	private JButton btnSalir;
	
	// Lista de contactos
	private List<Contacto> listaContactos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaAgenda frame = new VentanaAgenda();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaAgenda() {
		listaContactos = new ArrayList<Contacto> ();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[259.00][][grow][grow]", "[grow][][][grow][][][][grow][]"));
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, "cell 0 0 1 9,grow");
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nombre", "Tel\u00E9fono"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
		JLabel lblNewLabel = new JLabel("Nombre:");
		contentPane.add(lblNewLabel, "cell 1 1,alignx trailing");
		
		txtNombre = new JTextField();
		contentPane.add(txtNombre, "cell 2 1,growx");
		txtNombre.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Teléfono:");
		contentPane.add(lblNewLabel_1, "cell 1 2,alignx trailing");
		
		txtTelefono = new JTextField();
		contentPane.add(txtTelefono, "cell 2 2,growx");
		txtTelefono.setColumns(10);
		
		btnAnadir = new JButton("Añadir");
		btnAnadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertarContacto();
			}
		});
		contentPane.add(btnAnadir, "cell 2 4,growx");
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminarContacto();
			}
		});
		contentPane.add(btnEliminar, "cell 2 5,growx");
		
		btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		contentPane.add(btnSalir, "cell 2 6,growx");
	}

	protected void eliminarContacto() {
		int seleccionado=table.getSelectedRow();
		if (seleccionado==-1) {
			JOptionPane.showMessageDialog(this, 
				"Debe seleccionar un contacto de la tabla a borrar", 
				"Seleccione  un contacto",
				JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		// borrado de la lista
		listaContactos.remove(seleccionado);
		
		//mostrarContactos();
		
		// borrado de la tabla
		DefaultTableModel modelo = (DefaultTableModel) 					table.getModel();
		modelo.removeRow(seleccionado);
	}

	protected void insertarContacto() {
		String nombre=txtNombre.getText();
		String telefono = txtTelefono.getText();
		
		if (nombre==null || nombre.isBlank() ||
			telefono==null || telefono.isBlank()) {
			JOptionPane.showMessageDialog(this, 
					"Introduzca el nombre y el téfono", 
					"Faltan datos", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		Contacto c = new Contacto(nombre,telefono);
		if (!listaContactos.contains(c)) {
			listaContactos.add(c);
			mostrarContactos();
		} else {
			JOptionPane.showMessageDialog(this, 
					"Ya existe un contacto con ese teléfono",
					"Teléfono ya existe", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void mostrarContactos() {
		// REcogemos el modelo de la tabla para añadir los elementos
		DefaultTableModel modelo = (DefaultTableModel) 					table.getModel();
		// limpiamos la tabla de datos
		modelo.setRowCount(0);
		for (Contacto contacto : listaContactos) {
		// creamos un vector con una fila que contiene los datos 
		//de una persona
			Object fila [] = {
					contacto.getNombre(), contacto.getTelefono()
			};
			modelo.addRow(fila);
		}
		
	}

}
