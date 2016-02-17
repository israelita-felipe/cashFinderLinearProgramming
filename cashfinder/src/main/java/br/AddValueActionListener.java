/**
 * 
 */
package br;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author israel
 *
 */
public class AddValueActionListener implements ActionListener {

	private final InterfaceMainFrame frame;
	private final Find find;

	/**
	 * 
	 */
	public AddValueActionListener(InterfaceMainFrame frame) {
		// TODO Auto-generated constructor stub
		this.frame = frame;
		this.find = new Find(null, true, frame.getFindList());
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
		this.find.setVisible(true);
	}

}
