package de.ergodirekt.wizard.gui.main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.ScrollPaneConstants;

public class RegelnFenster extends JFrame {

	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JLabel lRegeln1;
	private JLabel lRegeln2;
	private JPanel panel;

	/**
	 * Create the frame.
	 */
	public RegelnFenster() {
		setTitle("Wizard - Regeln");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(getScrollPane(), BorderLayout.CENTER);
		pack();
		setVisible(true);
	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane(getPanel());
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPane.setPreferredSize(new Dimension(880, 680));
		}
		return scrollPane;
	}
	private JLabel getLRegeln1() {
		if (lRegeln1 == null) {
			lRegeln1 = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("img/regeln1.png")));
			lRegeln1.setSize(new Dimension(870, 2477));
		}
		return lRegeln1;
	}
	private JLabel getLRegeln2() {
		if (lRegeln2 == null) {
			lRegeln2 = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("img/regeln2.png")));
			lRegeln2.setSize(new Dimension(871, 2473));
		}
		return lRegeln2;
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(new BorderLayout(0, 0));
			panel.add(getLRegeln1(), BorderLayout.CENTER);
			panel.add(getLRegeln2(), BorderLayout.SOUTH);
		}
		return panel;
	}
}
