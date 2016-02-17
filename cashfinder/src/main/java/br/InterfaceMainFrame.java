/**
 * 
 */
package br;

import java.math.BigDecimal;

import javax.swing.DefaultListModel;
import javax.swing.JProgressBar;

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
