package modell;

import java.io.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner; 
import java.awt.EventQueue;
import java.util.Scanner; 
import javax.swing.JFrame;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JTextArea;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import java.awt.event.ActionListener;
import javax.swing.JEditorPane;
import javax.swing.JTextPane;
import javax.swing.text.JTextComponent;
import datenstruktur.PLZ_Verzeichnis;
import javax.swing.DropMode;
import javax.swing.SwingConstants;
public class UI {
	
	private JFrame frame;
	 Scanner scanner; 
	 private JTextField textField;
	 private final Action action = new SwingAction();
	public Component textPane;
	PLZ_Verzeichnis Verzeichnis;
	int i=0;

	String search = new String ();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UI window = new UI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	/**
	 * Create the application.
	 */
	public UI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 11));
		frame.getContentPane().setForeground(Color.LIGHT_GRAY);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setText(search);
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setDropMode(DropMode.INSERT);
		textField.setBounds(166, 11, 96, 20);
		textField.setColumns(5);
		frame.getContentPane().add(textField);
		
		JTextPane txtpnsearch = new JTextPane();
		txtpnsearch.setEnabled(false);
		txtpnsearch.setEditable(false);
		txtpnsearch.setBounds(135, 101, 147, 77);
		frame.getContentPane().add(txtpnsearch);
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String [] texte = new String [1];
				
				
				Scanner scan = new Scanner(System.in);
				txtpnsearch.setEnabled(true);
				
				txtpnsearch.setText(Verzeichnis.suchePLZ(search).toString());
			}
		});
		btnNewButton.setAction(action);
		btnNewButton.setBounds(166, 42, 96, 23);
		frame.getContentPane().add(btnNewButton);
		
		
		
		
		
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	private class SwingAction extends AbstractAction {
		UI ui;
		public SwingAction() {
			putValue(NAME, "suchen");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		
		public void actionPerformed(ActionEvent e) {
			
				
		}
	}
	}



