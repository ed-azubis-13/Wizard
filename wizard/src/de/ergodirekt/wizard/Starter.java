package de.ergodirekt.wizard;

import java.awt.EventQueue;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import de.ergodirekt.wizard.gui.main.WizardGUI;
/**
 * Diese Klasse öffnet einen Lobby-Beitreten-Dialog.
 * @author Tobias
 *
 */
public class Starter {
	/**
	 * Launch the application.
	 * 
	 * @throws UnsupportedLookAndFeelException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws ClassNotFoundException
	 */
	public static void main(String[] args) throws Exception {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WizardGUI window = new WizardGUI();
					window.getFrame().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
