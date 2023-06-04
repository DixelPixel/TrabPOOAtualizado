package View;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Model.Observado;

import java.util.List;
import java.util.ArrayList;

public class NumeroInputComponent extends JComponent implements Observado {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private List<Observador> observadores;
	private int nInput;

    public NumeroInputComponent() {
        setLayout(new FlowLayout());
        observadores = new ArrayList<Observador>();

        textField = new JTextField(10);
        Document document = new PlainDocument();
        ((AbstractDocument) document).setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String text, AttributeSet attr) throws BadLocationException {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < text.length(); i++) {
                    if (Character.isDigit(text.charAt(i))) {
                        sb.append(text.charAt(i));
                    }
                }
                super.insertString(fb, offset, sb.toString(), attr);
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                if (text == null) {
                    super.replace(fb, offset, length, text, attrs);
                    return;
                }
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < text.length(); i++) {
                    if (Character.isDigit(text.charAt(i))) {
                        sb.append(text.charAt(i));
                    }
                }
                super.replace(fb, offset, length, sb.toString(), attrs);
            }
        });
        textField.setDocument(document);

        JButton button = new JButton("Obter Valor");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String valor = textField.getText();
                if (!valor.isEmpty()) {
                    nInput = Integer.parseInt(valor);
                    notificaObservadores();
//                    System.out.println("O número digitado foi: " + nInput);
                } else {
//                    System.out.println("Nenhum número foi digitado.");
                }
            }
        });

        add(textField);
        add(button);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(200, 50);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Input de Número Personalizado");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        NumeroInputComponent inputComponent = new NumeroInputComponent();
        frame.add(inputComponent);

        frame.pack();
        frame.setVisible(true);
    }

	@Override
	public void registraObservador(Observador observador) {
		// TODO Auto-generated method stub
		observadores.add(observador);
		
	}

	@Override
	public void removeObservador(Observador observador) {
		// TODO Auto-generated method stub
		observadores.remove(observador);
		
	}

	@Override
	public void notificaObservadores() {
		// TODO Auto-generated method stub
		for(Observador obs: observadores) {
			obs.update(nInput);
		}
		
	}
}

