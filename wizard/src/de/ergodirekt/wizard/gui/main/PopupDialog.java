package de.ergodirekt.wizard.gui.main;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
/**
 * Diese Klasse implementiert das Popup Fenster, wenn keine Lobby zu der zugehörigen IP gefunden wurde.
 * @author Tobias
 *
 */
public class PopupDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 455968577526268740L;

	private final JPanel contentPanel = new JPanel();
	private JLabel icon;

	public static final int IS_ERROR_MESSAGE = 0;
	public static final int IS_INFORMATION_MESSAGE = 1;
	private JLabel messageLabel;

	/**
	 * Create the dialog.
	 */
	// Konstruktoren
	public PopupDialog(Component parent, int messageType, String message) {
		setTitle("Message");
		setResizable(false);
		getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER));
		getContentPane().add(getIcon(messageType));
		getContentPane().add(getMessage(message));
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		pack();
		setLocationRelativeTo(parent);
		setVisible(true);
		final Timer closeTimer = new Timer(2000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				dispose();
			}
		});
		closeTimer.setRepeats(false);
		closeTimer.start();
		requestFocus();
		addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				dispose();
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
			}
		});
	}
	// Komponenten-Getter
	/**
	 * generated
	 * @return
	 */
	private JLabel getIcon(int messageType) {
		if (icon == null) {
			icon = new JLabel("");
			if (messageType == IS_INFORMATION_MESSAGE) {
				icon.setIcon(new ImageIcon(
						PopupDialog.class
								.getResource("/javax/swing/plaf/metal/icons/Inform.gif")));
			}
			if (messageType == IS_ERROR_MESSAGE) {
				icon.setIcon(new ImageIcon(PopupDialog.class
						.getResource("/javax/swing/plaf/metal/icons/Error.gif")));
			}
		}
		return icon;
	}

	// Methoden
	private JLabel getMessage(String message) {
		if (messageLabel == null) {
			messageLabel = new JLabel(message);
		}
		return messageLabel;
	}
}
