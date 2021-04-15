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
import javax.swing.JScrollPane;
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
		//Hier das eigene Verzeichnis einfügen
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
		textField.setColumns(5);
        AbstractDocument doc = (AbstractDocument) textField.getDocument();
        doc.setDocumentFilter(new IntegerFilter(textField));
		
		JTextPane txtpnsearch = new JTextPane();
		txtpnsearch.setEnabled(false);
		txtpnsearch.setEditable(false);
		JScrollPane plzSearch=new JScrollPane(txtpnsearch);
		JButton btnNewButton = new JButton("PLZ suchen");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtpnsearch.setEnabled(true);
				
				if(textField.getText().length()>0) {
					int suchPLZ=Integer.parseInt(textField.getText());
					ConsoleOutputCapturer coc=new ConsoleOutputCapturer();
					coc.start();
					verzeichnis.plzAusgeben(suchPLZ);
					txtpnsearch.setText(coc.stop());
				}
			}
		});
		
		
		JTextField stadtField = new JTextField();
		stadtField.setText(search);
		stadtField.setHorizontalAlignment(SwingConstants.CENTER);
		stadtField.setDropMode(DropMode.INSERT);
		stadtField.setColumns(5);
		
		JTextPane stadtSearchText = new JTextPane();
		stadtSearchText.setEnabled(false);
		stadtSearchText.setEditable(false);
		JScrollPane stadtSearch=new JScrollPane(stadtSearchText);
		
		JButton stadtBtn = new JButton("Stadt suchen");
		stadtBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stadtSearchText.setEnabled(true);
				
				ConsoleOutputCapturer coc=new ConsoleOutputCapturer();
				coc.start();
				verzeichnis.ortsnameAusgeben(stadtField.getText());
				stadtSearchText.setText(coc.stop());
			}
		});
		
		
		JTextField vorwahlField = new JTextField();
		vorwahlField.setText(search);
		vorwahlField.setHorizontalAlignment(SwingConstants.CENTER);
		vorwahlField.setDropMode(DropMode.INSERT);
		vorwahlField.setColumns(5);
        AbstractDocument docVW = (AbstractDocument) textField.getDocument();
        docVW.setDocumentFilter(new IntegerFilterLoose());
		
		JTextPane vorwahlSearchText = new JTextPane();
		vorwahlSearchText.setEnabled(false);
		vorwahlSearchText.setEditable(false);
		JScrollPane vorwahlSearch=new JScrollPane(vorwahlSearchText);
		
		JButton vorwahlBtn = new JButton("Vorwahl suchen");
		vorwahlBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vorwahlSearchText.setEnabled(true);
				
				if(vorwahlField.getText().length()>0) {
					int suchVW=Integer.parseInt(vorwahlField.getText());
					ConsoleOutputCapturer coc=new ConsoleOutputCapturer();
					coc.start();
					verzeichnis.vorwahlAusgeben(suchVW);
					vorwahlSearchText.setText(coc.stop());
				}
				
			}
		});
		
		frame.add(textField);
		textField.setBounds(25,25,200,20);
		frame.add(btnNewButton);
		btnNewButton.setBounds(25,50,200,20);
		frame.add(plzSearch);
		plzSearch.setBounds(25,75,200,200);

		frame.add(stadtField);
		stadtField.setBounds(250,25,200,20);
		frame.add(stadtBtn);
		stadtBtn.setBounds(250,50,200,20);
		frame.add(stadtSearch);
		stadtSearch.setBounds(250,75,200,200);
		
		frame.add(vorwahlField);
		vorwahlField.setBounds(475,25,200,20);
		frame.add(vorwahlBtn);
		vorwahlBtn.setBounds(475,50,200,20);
		frame.add(vorwahlSearch);
		vorwahlSearch.setBounds(475,75,200,200);
		
		
		frame.setBounds(100, 100, 715, 340);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Postleitzahlen durchsuchen");
	}
	
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
	 
	 class IntegerFilterLoose extends DocumentFilter {
	        @Override
	        public void insertString(DocumentFilter.FilterBypass fb, int offset, String text, AttributeSet attr) throws BadLocationException {
	            if (isNumeric(text)) {
	               super.insertString(fb, offset, text, attr);
	            }
	        }

	        @Override
	        public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attr) throws BadLocationException {
	            if (isNumeric(text)) {
	               super.replace(fb, offset, length, text, attr);
	            }
	        }

	        private boolean isNumeric(String text) {
	            return text != null && text.matches("\\d*");
	        }
	    }
	 	 
}



