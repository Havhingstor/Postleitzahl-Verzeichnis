package datenstruktur;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.DropMode;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
public class UI {
	
	private JFrame frame;
	 Scanner scanner; 
	 private JTextField textField;
//	 private final Action action = new SwingAction();
	public Component textPane;
	PLZ_Verzeichnis verzeichnis;
	int i=0;

	String search = new String ();
	

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					UI window = new UI();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//		
//	}

	public static void main(String[] args) {
		new UI("D:\\Users\\Dokumente\\Schule\\11. Klasse\\Info\\Infoprojekt Postleitzahlen\\plz_de.csv");
	}
	
	/**
	 * Create the application.
	 */
	public UI(String pfad) {
		verzeichnis=new PLZ_Verzeichnis(pfad);
		initialize();
		frame.setVisible(true);
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
        AbstractDocument doc = (AbstractDocument) textField.getDocument();
        doc.setDocumentFilter(new IntegerFilter(textField));
		frame.getContentPane().add(textField);
		
		JTextPane txtpnsearch = new JTextPane();
		txtpnsearch.setEnabled(false);
		txtpnsearch.setEditable(false);
		txtpnsearch.setBounds(135, 101, 147, 77);
		frame.getContentPane().add(txtpnsearch);
		JButton btnNewButton = new JButton("Suchen");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtpnsearch.setEnabled(true);
				
				int suchPLZ=Integer.parseInt(textField.getText());
				ConsoleOutputCapturer coc=new ConsoleOutputCapturer();
				coc.start();
				verzeichnis.plzAusgeben(suchPLZ);
				txtpnsearch.setText(coc.stop());
			}
		});
//		btnNewButton.setAction(action);
		btnNewButton.setBounds(166, 42, 96, 23);
		frame.getContentPane().add(btnNewButton);
		
		
		
		
		
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
//	private class SwingAction extends AbstractAction {
//		UI ui;
//		public SwingAction() {
//			putValue(NAME, "suchen");
//			putValue(SHORT_DESCRIPTION, "Some short description");
//		}
//		
//		public void actionPerformed(ActionEvent e) {
//			
//				
//		}
//	}
	
	 class IntegerFilter extends DocumentFilter {
		 	JTextField textField;
		 	
		 	IntegerFilter(JTextField textField){
		 		this.textField=textField;
		 	}
		 
	        @Override
	        public void insertString(DocumentFilter.FilterBypass fb, int offset, String text, AttributeSet attr) throws BadLocationException {
	            if (isNumeric(text)&&textField.getText().length()+text.length()<=5) {
	               super.insertString(fb, offset, text, attr);
	            }
	        }

	        @Override
	        public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attr) throws BadLocationException {
	            if (isNumeric(text)&&textField.getText().length()+text.length()<=5) {
	               super.replace(fb, offset, length, text, attr);
	            }
	        }

	        private boolean isNumeric(String text) {
	            return text != null && text.matches("\\d*");
	        }
	    }
	
}



