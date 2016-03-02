/**
 * 
 */
package br.pub.cashfinder.control.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

import javax.swing.DefaultListModel;
import javax.swing.JList;

/**
 * @author israel
 *
 */
public class RemoveFromValuesActionListener implements ActionListener {

	private JList<BigDecimal> list;

	/**
	 * 
	 */
	public RemoveFromValuesActionListener(JList<BigDecimal> list) {
		// TODO Auto-generated constructor stub
		this.list = list;
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
		int index = list.getSelectedIndex();
		if (index != -1) {
			DefaultListModel<BigDecimal> model = (DefaultListModel<BigDecimal>) list.getModel();
			model.removeElementAt(index);
		}
	}

}
