/**
 * 
 */
package br;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;

/**
 * @author israel
 *
 */
public class Executer extends Thread {

	private String comand;
	private JProgressBar progress;
	private JFrame board;
	private JEditorPane whiteBoard;

	/**
	 * 
	 */
	public Executer(String comand, JProgressBar progress) {
		// TODO Auto-generated constructor stub
		this.comand = comand;
		this.progress = progress;
		init();
	}

	private void init() {
		this.board = new JFrame("Solutions");
		this.whiteBoard = new JEditorPane();
		this.board.setExtendedState(JFrame.MAXIMIZED_BOTH);

		JScrollPane sp = new JScrollPane();
		sp.setViewportView(whiteBoard);
		this.board.setContentPane(sp);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {

			ProcessBuilder pb = new ProcessBuilder(comand);
			Process p = pb.start();
			p.waitFor();

			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
			StringBuffer sb = new StringBuffer();
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line).append("\n");
			}
			this.whiteBoard.setText(sb.toString());

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Executer: " + e.getMessage());
		} finally {
			this.progress.setIndeterminate(false);
			this.board.setVisible(true);
		}
	}

	public static void main(String[] args) {
		File f = new File("dieta.run");
		f.delete();
		try {
			f.createNewFile();
			Executer exec = new Executer(f.getCanonicalPath(), new JProgressBar());
			exec.start();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "FindeActionListener: " + e1.getMessage());
		}
	}
}
