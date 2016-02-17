/**
 * 
 */
package br;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

/**
 * @author israel
 *
 */
public class FindActionListener implements ActionListener {

	private InterfaceMainFrame frame;
	private PrintWriter writer;

	/**
	 * @throws FileNotFoundException
	 * 
	 */
	public FindActionListener(InterfaceMainFrame frame) throws FileNotFoundException {
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
		this.frame.getProgress().setIndeterminate(true);
		File f = new File("dieta.run");
		try {
			fillEntry();
			Executer exec = new Executer(f.getCanonicalPath(), this.frame.getProgress());
			exec.start();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "FindeActionListener: " + e1.getMessage());
			this.frame.getProgress().setIndeterminate(false);
		}
	}

	private void fillEntry() throws IOException {
		File f = new File("entrada.crp");
		this.writer = new PrintWriter(f);

		int find, aviso;

		find = this.frame.getFindList().getSize();
		aviso = this.frame.getAvisoList().getSize();

		this.writer.write(find + "\n");
		this.writer.write(aviso + "\n");

		for (int i = 0; i < find; i++) {
			this.writer.write(this.frame.getFindList().get(i) + "\n");
		}
		for (int i = 0; i < aviso; i++) {
			this.writer.write(this.frame.getAvisoList().get(i) + "\n");
		}
		this.writer.close();
	}

}
