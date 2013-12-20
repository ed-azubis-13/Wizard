package de.ergodirekt.wizard;

import java.awt.EventQueue;

import javax.swing.UnsupportedLookAndFeelException;

import de.ergodirekt.wizard.server.ServerConsole;
/**
 * Die Klasse startet den Server.
 * @author Tobias
 *
 */
public class StarterServer {
	/**
	 * Launch the application.
	 * 
	 * @throws UnsupportedLookAndFeelException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws ClassNotFoundException
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ServerConsole window = new ServerConsole();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
