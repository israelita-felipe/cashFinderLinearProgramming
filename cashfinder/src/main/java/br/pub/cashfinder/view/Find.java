/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.pub.cashfinder.view;

import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.text.DecimalFormat;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

/**
 *
 * @author Israel Araújo
 */
public class Find extends javax.swing.JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1051969490847246585L;
	private final DefaultListModel<BigDecimal> list;

	/**
	 * Creates new form Find
	 *
	 * @param parent
	 * @param modal
	 * @param r
	 */
	public Find(java.awt.Frame parent, boolean modal, DefaultListModel<BigDecimal> list) {
		super(parent, modal);
		initComponents();
		this.list = list;
	}

	private void initComponents() {

		jLabel1 = new javax.swing.JLabel();
		valorTextField = new JNumberFormatField(new DecimalFormat("0.00"));
		valorTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int r = e.getKeyCode();
				if (r == KeyEvent.VK_ENTER) {
					e.consume();
					list.addElement(new BigDecimal(valorTextField.getText().replace(",", ".")));
					dispose();
				} else if (r == KeyEvent.VK_ESCAPE) {
					e.consume();
					dispose();
				}
			}
		});

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("Valor");
		setModal(true);
		setModalExclusionType(java.awt.Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
		setResizable(false);

		jLabel1.setText("Valor: (R$ 00.00)");

		valorTextField.setText("0,00");

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap()
						.addGroup(layout.createParallelGroup(Alignment.TRAILING)
								.addGroup(layout.createSequentialGroup().addComponent(jLabel1).addGap(0, 330,
										Short.MAX_VALUE))
								.addComponent(valorTextField, 444, 444, 444))
						.addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap().addComponent(jLabel1)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(valorTextField,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addContainerGap(59, Short.MAX_VALUE)));
		getContentPane().setLayout(layout);

		setSize(new Dimension(470, 107));
		setLocationRelativeTo(null);
	}// </editor-fold>//GEN-END:initComponents

	private javax.swing.JLabel jLabel1;
	private javax.swing.JTextField valorTextField;
	// End of variables declaration//GEN-END:variables
}
