package de.ergodirekt.wizard.gui.game;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.FlowLayout;
/**
 * In dieser Klasse wurde das Spiel-Ende Fenster implementiert (Ob man nochmal spielen will).
 * @author Tobias
 *
 */
public class GameEnde {

	private JFrame frmEnde;
	private JPanel textPanel;
	private JPanel buttonPanel;
	private JLabel lblSpielZuEnde;
	private JButton btnJa;
	private JButton btnNein;

	/**
	 * Create the application.
	 */
	public GameEnde() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmEnde = new JFrame();
		frmEnde.setTitle("Ende");
		frmEnde.setBounds(100, 100, 365, 126);
		frmEnde.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmEnde.getContentPane().setLayout(new BorderLayout(0, 0));
		frmEnde.getContentPane().add(getTextPanel(), BorderLayout.CENTER);
		frmEnde.getContentPane().add(getButtonPanel(), BorderLayout.SOUTH);
	}
	/**
	 * 
	 * 
	 */
	private JPanel getTextPanel() {
		if (textPanel == null) {
			textPanel = new JPanel();
			textPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			textPanel.add(getLblSpielZuEnde());
		}
		return textPanel;
	}
	private JPanel getButtonPanel() {
		if (buttonPanel == null) {
			buttonPanel = new JPanel();
			buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			buttonPanel.add(getBtnJa());
			buttonPanel.add(getBtnNein());
		}
		return buttonPanel;
	}
	private JLabel getLblSpielZuEnde() {
		if (lblSpielZuEnde == null) {
			lblSpielZuEnde = new JLabel("Spiel zu Ende! Sie haben <g/v>! Nochmal?");
		}
		return lblSpielZuEnde;
	}
	private JButton getBtnJa() {
		if (btnJa == null) {
			btnJa = new JButton("Ja");
		}
		return btnJa;
	}
	private JButton getBtnNein() {
		if (btnNein == null) {
			btnNein = new JButton("Nein");
		}
		return btnNein;
	}
}
