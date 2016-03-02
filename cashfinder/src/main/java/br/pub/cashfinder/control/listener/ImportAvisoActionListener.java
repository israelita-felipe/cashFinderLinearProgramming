/**
 * 
 */
package br.pub.cashfinder.control.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.text.ParseException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import br.pub.cashfinder.model.Retorno;
import br.pub.cashfinder.view.interfaces.InterfaceMainFrame;

/**
 * @author israel
 *
 */
public class ImportAvisoActionListener implements ActionListener {

	private final InterfaceMainFrame frame;

	/**
	 * 
	 */
	public ImportAvisoActionListener(InterfaceMainFrame frame) {
		// TODO Auto-generated constructor stub
		this.frame = frame;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JFileChooser file = new JFileChooser();
		file.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int i = file.showOpenDialog(null);
		if (i == JFileChooser.APPROVE_OPTION) {
			try {
				frame.setAviso(Retorno.getRetorno(file.getSelectedFile().toString()));
				frame.getAvisoList().clear();
				for (BigDecimal valor : frame.getAviso().list()) {
					frame.getAvisoList().addElement(valor);
				}
			} catch (FileNotFoundException ex) {
				JOptionPane.showMessageDialog(null, "Arquivo não encontrado");
			} catch (ParseException ex) {
				JOptionPane.showMessageDialog(null, "Data do arquivo inválida");
			} catch (UnsupportedOperationException ex) {
				JOptionPane.showMessageDialog(null,
						"Erro ocorrido ao adicionar valores no arquivo\n" + ex.getMessage());
			}
		}
	}

}
