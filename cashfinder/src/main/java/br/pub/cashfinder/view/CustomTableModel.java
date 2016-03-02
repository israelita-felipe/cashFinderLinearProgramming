/**
 * 
 */
package br.pub.cashfinder.view;

import javax.swing.table.AbstractTableModel;

/**
 * @author israel
 *
 */
public class CustomTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5518794230960781845L;
	private double[] header;
	private double[][] data;

	/**
	 * 
	 */
	public CustomTableModel(double[] header, double[][] data) {
		// TODO Auto-generated constructor stub
		this.header = header;
		this.data = data;
	}

	/* Retorna a quantidade de colunas. */
	@Override
	public int getColumnCount() {
		// EstÃ¡ retornando o tamanho do array "colunas".
		return header.length;
	}

	/* Retorna a quantidade de linhas. */
	@Override
	public int getRowCount() {
		// Retorna o tamanho da lista de Cliente.
		return data.length;
	}

	@Override
	public String getColumnName(int columnIndex) {
		// Retorna o conteÃºdo do Array que possui o nome das colunas
		return String.valueOf(header[columnIndex]);
	};

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return String.class;
	};

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return data[rowIndex][columnIndex];
	}

	@Override
	// modifica na linha e coluna especificada
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		data[rowIndex][columnIndex] = (double) aValue;
		fireTableCellUpdated(rowIndex, columnIndex);
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}
}
