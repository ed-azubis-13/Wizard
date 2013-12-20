package de.ergodirekt.wizard.server;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.TitledBorder;

import de.ergodirekt.wizard.shared.WizardLogger;
/**
 * Die Klasse initialisiert den Server (Layout + Inhalt)
 * @author Tobias
 *
 */
public class ServerConsole {

	public JFrame frame;
	private WizardServer server;
	private JTextArea textArea;

	/**
	 * Create the application.
	 */
	public ServerConsole() {
		initialize();
		server = new WizardServer(this);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 650, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JScrollPane scrolli = new JScrollPane(getTextArea());
		scrolli.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrolli.setAutoscrolls(true);
		frame.getContentPane().add(scrolli, BorderLayout.CENTER);
		frame.setTitle("Wizard Server Console");
	}

	private JTextArea getTextArea() {
		if (textArea == null) {
			textArea = new JTextArea();
			textArea.setLineWrap(true);
			textArea.setBackground(Color.BLACK);
			textArea.setForeground(Color.LIGHT_GRAY);
			textArea.setEditable(false);
			textArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
			textArea.setAutoscrolls(true);
		}
		return textArea;
	}

	public void write(String string) {
		textArea.append(WizardLogger.info(string) + "\n");
	}

	public void writeDebug(String string) {
		textArea.append(WizardLogger.debug(string) + "\n");
	}

	public void writeError(String string) {
		textArea.append(WizardLogger.error(string) + "\n");
	}

	public void writeErrorEx(String string, Exception e) {
		textArea.append(WizardLogger.error(string, e) + "\n");
	}
}
