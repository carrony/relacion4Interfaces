package clases;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import net.miginfocom.swing.MigLayout;

public class VentanaProfesores extends JFrame {

	private JPanel contentPane;
	private JTextField txtDni;
	private JTextField txtNombre;
	private JTextField txtSalario;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private List<Profesor> listaProfesores;
	private JRadioButton rdbFijo;
	private JRadioButton rdbTemporal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaProfesores frame = new VentanaProfesores();
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
	public VentanaProfesores() {
		this.listaProfesores = new ArrayList<Profesor>();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][][grow][grow][41.00][]", "[][][][][][][grow]"));
		
		JLabel lblNewLabel = new JLabel("Dni:");
		contentPane.add(lblNewLabel, "cell 1 1,alignx trailing");
		
		txtDni = new JTextField();
		contentPane.add(txtDni, "cell 2 1 2 1,growx");
		txtDni.setColumns(10);
		
		JButton btnAnadir = new JButton("Añadir");
		btnAnadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertarProfesor();
			}
		});
		contentPane.add(btnAnadir, "cell 5 1,growx");
		
		JLabel lblNewLabel_1 = new JLabel("Nombre:");
		contentPane.add(lblNewLabel_1, "cell 1 2,alignx trailing");
		
		txtNombre = new JTextField();
		contentPane.add(txtNombre, "cell 2 2 2 1,growx");
		txtNombre.setColumns(10);
		
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarDatos();
			}
		});
		contentPane.add(btnLimpiar, "cell 5 2,growx");
		
		JLabel lblNewLabel_2 = new JLabel("Salario:");
		contentPane.add(lblNewLabel_2, "cell 1 3,alignx trailing");
		
		txtSalario = new JTextField();
		contentPane.add(txtSalario, "cell 2 3 2 1,growx");
		txtSalario.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscarProfesor();
			}
		});
		contentPane.add(btnBuscar, "cell 5 3,growx");
		
		JButton btnSalir = new JButton("Salir");
		contentPane.add(btnSalir, "cell 5 4,growx");
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Tipo de Contrato", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		contentPane.add(panel, "cell 1 6 2 1,grow");
		panel.setLayout(new MigLayout("", "[30px][]", "[][]"));
		
		rdbFijo = new JRadioButton("Fijo");
		rdbFijo.setSelected(true);
		buttonGroup.add(rdbFijo);
		panel.add(rdbFijo, "cell 1 0");
		
		rdbTemporal = new JRadioButton("Temporal");
		buttonGroup.add(rdbTemporal);
		panel.add(rdbTemporal, "cell 1 1");
	}

	protected void buscarProfesor() {
		String dni=txtDni.getText();
		Profesor p = new Profesor();
		p.setDni(dni);
		
		int indice = listaProfesores.indexOf(p);
		if (indice!=-1) {
			p= listaProfesores.get(indice);
			txtNombre.setText(p.getNombre());
			txtSalario.setText(""+p.getSalario());
			if (p.isFijo()) {
				rdbFijo.setSelected(true);
				rdbTemporal.setSelected(false);
			} else {
				rdbFijo.setSelected(false);
				rdbTemporal.setSelected(true);
			}
		} else {
			JOptionPane.showMessageDialog(this, 
				"No se ha encontrado el profesor con el dni "+dni, 
				"Profesor no encontrado", JOptionPane.ERROR_MESSAGE);
		}
		
	}

	protected void limpiarDatos() {
		txtDni.setText("");
		txtNombre.setText("");
		txtSalario.setText("");
		rdbTemporal.setSelected(false);
		rdbFijo.setSelected(true);
	}

	protected void insertarProfesor() {

		String dni=txtDni.getText();
		String nombre=txtNombre.getText();
		double salario = Double.parseDouble(txtSalario.getText());
		boolean fijo = rdbFijo.isSelected();
		
		if (dni==null || dni.isBlank() ||
			nombre==null || nombre.isBlank())  {
			JOptionPane.showMessageDialog(this, 
					"Introduzca el nombre y el dni", 
					"Faltan datos", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		Profesor p = new Profesor(dni,nombre,salario, fijo);
		if (!listaProfesores.contains(p)) {
			listaProfesores.add(p);
		} else {
			JOptionPane.showMessageDialog(this, 
					"Ya existe un profesor con ese dni",
					"DNI ya existe", JOptionPane.ERROR_MESSAGE);
		}
		
	}

}
