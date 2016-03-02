/**
 * 
 */
package br.pub.cashfinder.control;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import br.pub.cashfinder.model.Model;

/**
 * @author israel
 *
 */
public class Executer extends Thread {
	
	private JProgressBar progress;
	private JFrame board;	
	private JTable table;

	/**
	 * 
	 */
	public Executer(JProgressBar progress) {
		// TODO Auto-generated constructor stub
		this.progress = progress;		
		init();
	}

	private void init() {
		this.board = new JFrame("Solutions");		
		this.board.setExtendedState(JFrame.MAXIMIZED_BOTH);

		JScrollPane sp = new JScrollPane();
		this.table = new JTable();
		sp.setViewportView(this.table);
		this.board.setContentPane(sp);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {

			Model m = new Model("entrada.crp");
			
			this.table.setModel(m.solve());
			this.table.updateUI();
			this.board.setVisible(true);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Executer: " + e.getMessage());
		} finally {
			this.progress.setIndeterminate(false);
			this.board.setVisible(true);
		}
	}	
}
