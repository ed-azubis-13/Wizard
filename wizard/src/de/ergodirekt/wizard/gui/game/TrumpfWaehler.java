package de.ergodirekt.wizard.gui.game;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class TrumpfWaehler extends JDialog {

	private final JPanel contentPanel = new JPanel();

	private GamePanel parent;

	private JComboBox<String> comboBox;
	/**
	 * Create the dialog.
	 */
	public TrumpfWaehler(GamePanel parent) {
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.parent = parent;
		setTitle("Bitte Trumpf wählen");
		setBounds(100, 100, 215, 112);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			comboBox = new JComboBox<String>();
			comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"rot", "gelb", "gruen", "blau"}));
			contentPanel.add(comboBox);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						chose();
					}
				});
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
		setModal(true);
		setLocationRelativeTo(parent);
		setVisible(true);
	}
	
	protected void chose() {
		parent.trumpfWaehlen((String) comboBox.getSelectedItem());
		dispose();
	}

}
