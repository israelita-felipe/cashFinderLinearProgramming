/**
 * 
 */
package br.pub.cashfinder.view.interfaces;

import java.math.BigDecimal;

import javax.swing.DefaultListModel;
import javax.swing.JProgressBar;

import br.pub.cashfinder.model.interfaces.InterfaceAviso;

/**
 * @author israel
 *
 */
public interface InterfaceMainFrame {

	InterfaceAviso getAviso();

	void setAviso(InterfaceAviso aviso);

	DefaultListModel<BigDecimal> getAvisoList();

	DefaultListModel<BigDecimal> getFindList();

	JProgressBar getProgress();
}
