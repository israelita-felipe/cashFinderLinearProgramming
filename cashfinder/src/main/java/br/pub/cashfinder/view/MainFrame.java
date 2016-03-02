package br.pub.cashfinder.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.FileNotFoundException;
import java.math.BigDecimal;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;

import br.pub.cashfinder.control.listener.AddValueActionListener;
import br.pub.cashfinder.control.listener.FindActionListener;
import br.pub.cashfinder.control.listener.ImportAvisoActionListener;
import br.pub.cashfinder.control.listener.RemoveFromValuesActionListener;
import br.pub.cashfinder.model.interfaces.InterfaceAviso;
import br.pub.cashfinder.view.interfaces.InterfaceMainFrame;

import java.awt.Dimension;

public class MainFrame extends JFrame implements InterfaceMainFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7237200191282203031L;
	private JPanel contentPane;
	private DefaultListModel<BigDecimal> findAviso;
	private DefaultListModel<BigDecimal> findValue;
	private InterfaceAviso aviso;
	private JList<BigDecimal> list_1;
	private JList<BigDecimal> list;
	private JButton btnImport;
	private JButton btnAdd;
	private JProgressBar progressBar;
	private JButton btnFind;
	private JButton btnRemove_1;
	private JButton btnRemove;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Main: " + e.getMessage());
				}
			}
		});
	}

	private void init() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		progressBar = new JProgressBar();
		contentPane.add(progressBar, BorderLayout.SOUTH);

		JDesktopPane desktopPane = new JDesktopPane();
		contentPane.add(desktopPane, BorderLayout.CENTER);

		JInternalFrame internalFrame = new JInternalFrame("Report");
		internalFrame.setMaximizable(true);
		internalFrame.setIconifiable(true);
		internalFrame.setResizable(true);
		internalFrame.setBounds(288, 12, 488, 505);
		desktopPane.add(internalFrame);

		JToolBar toolBar_1 = new JToolBar();
		toolBar_1.setFloatable(false);
		internalFrame.getContentPane().add(toolBar_1, BorderLayout.NORTH);

		btnImport = new JButton("Import");
		btnImport.setMaximumSize(new Dimension(84, 24));
		btnImport.setMinimumSize(new Dimension(84, 24));
		btnImport.setPreferredSize(new Dimension(84, 24));
		btnImport.setSize(new Dimension(84, 24));
		toolBar_1.add(btnImport);

		btnRemove_1 = new JButton("Remove");
		btnRemove_1.setMaximumSize(new Dimension(84, 24));
		btnRemove_1.setMinimumSize(new Dimension(84, 24));
		btnRemove_1.setPreferredSize(new Dimension(84, 24));
		btnRemove_1.setSize(new Dimension(84, 24));
		toolBar_1.add(btnRemove_1);

		JScrollPane scrollPane = new JScrollPane();
		internalFrame.getContentPane().add(scrollPane, BorderLayout.CENTER);

		list = new JList<BigDecimal>();
		scrollPane.setViewportView(list);

		JInternalFrame internalFrame_1 = new JInternalFrame("Values");
		internalFrame_1.setMaximizable(true);
		internalFrame_1.setIconifiable(true);
		internalFrame_1.setResizable(true);
		internalFrame_1.setBounds(12, 12, 264, 505);
		desktopPane.add(internalFrame_1);

		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		internalFrame_1.getContentPane().add(toolBar, BorderLayout.NORTH);

		btnAdd = new JButton("Add");
		btnAdd.setMaximumSize(new Dimension(84, 24));
		btnAdd.setMinimumSize(new Dimension(84, 24));
		btnAdd.setPreferredSize(new Dimension(84, 24));
		btnAdd.setSize(new Dimension(84, 24));
		toolBar.add(btnAdd);

		btnRemove = new JButton("Remove");
		btnRemove.setMaximumSize(new Dimension(84, 24));
		btnRemove.setMinimumSize(new Dimension(84, 24));
		btnRemove.setPreferredSize(new Dimension(84, 24));
		btnRemove.setSize(new Dimension(84, 24));
		toolBar.add(btnRemove);

		JScrollPane scrollPane_1 = new JScrollPane();
		internalFrame_1.getContentPane().add(scrollPane_1, BorderLayout.CENTER);

		list_1 = new JList<BigDecimal>();
		scrollPane_1.setViewportView(list_1);

		JToolBar toolBar_2 = new JToolBar();
		toolBar_2.setFloatable(false);
		contentPane.add(toolBar_2, BorderLayout.NORTH);

		btnFind = new JButton("Find");
		btnFind.setPreferredSize(new Dimension(84, 24));
		btnFind.setMinimumSize(new Dimension(84, 24));
		btnFind.setMaximumSize(new Dimension(84, 24));
		toolBar_2.add(btnFind);
		internalFrame_1.setVisible(true);
		internalFrame.setVisible(true);

		this.findAviso = new DefaultListModel<>();
		this.findValue = new DefaultListModel<>();

		this.list_1.setModel(findValue);
		this.list.setModel(findAviso);
	}

	/**
	 * Create the frame.
	 * 
	 * @throws FileNotFoundException
	 */
	public MainFrame() throws FileNotFoundException {
		init();
		addListeners();
	}

	private void addListeners() throws FileNotFoundException {
		getImportButton().addActionListener(new ImportAvisoActionListener(this));
		getAddButton().addActionListener(new AddValueActionListener(this));
		getFindButton().addActionListener(new FindActionListener(this));
		getRemoveAvisoButton().addActionListener(new RemoveFromValuesActionListener(list));
		getRemoveValueButton().addActionListener(new RemoveFromValuesActionListener(list_1));
	}

	@Override
	public InterfaceAviso getAviso() {
		// TODO Auto-generated method stub
		return this.aviso;
	}

	@Override
	public DefaultListModel<BigDecimal> getAvisoList() {
		// TODO Auto-generated method stub
		return this.findAviso;
	}

	@Override
	public void setAviso(InterfaceAviso aviso) {
		// TODO Auto-generated method stub
		this.aviso = aviso;
	}

	public JButton getImportButton() {
		return btnImport;
	}

	public JButton getAddButton() {
		return btnAdd;
	}

	@Override
	public JProgressBar getProgress() {
		return progressBar;
	}

	public JButton getFindButton() {
		return btnFind;
	}

	@Override
	public DefaultListModel<BigDecimal> getFindList() {
		// TODO Auto-generated method stub
		return this.findValue;
	}

	public JButton getRemoveAvisoButton() {
		return btnRemove_1;
	}

	public JButton getRemoveValueButton() {
		return btnRemove;
	}
}
