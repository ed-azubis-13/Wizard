package de.ergodirekt.wizard.gui.main;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import de.ergodirekt.wizard.server.LobbyClient;
import de.ergodirekt.wizard.shared.User;
import de.ergodirekt.wizard.shared.WizardLogger;

/**
 * Diese Klasse setzt das Layout des "LobbyBeitretenDialogs" und leitet den Client zur Lobby weiter.
 * @author Konstantin Straub, Tobias Arnold, Philipp Bruckner, Manuel Böhm
 * 
 */
public class WizardGUI {
	// ----
	private JFrame frame;
	private JPanel cardPanel;
	

	private HostPanel hostPanel;
	private static final String HOST_PANEL_NAME = "hostPanel";
	private static final String JOIN_PANEL_NAME = "joinPanel";
	private LobbyBeitretenPanel startPanel;

	/**
	 * Create the application.
	 */
	
	// Konstruktoren
	public WizardGUI() {
		initialize();
	}

	// ----
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Wizard");
		getFrame().setSize(301, 192);
		getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		frame.getContentPane().add(getCardPanel());
		frame.getRootPane().setDefaultButton(startPanel.okButton);
		frame.setPreferredSize(new Dimension(300, 190));
		frame.pack();
	}

	/**
	 * generated
	 * @return
	 */
	
	/**
	 * Card-Panel erzeugen
	 * 
	 * @return
	 */
	public JPanel getCardPanel() {
		if (cardPanel == null) {
			cardPanel = new JPanel();
			cardPanel.setLayout(new CardLayout(0, 0));
			cardPanel.add(getStartPanel(), JOIN_PANEL_NAME);
		}
		return cardPanel;
	}
	
	/**
	 * generated
	 * @return
	 */
	
	/**
	 * Host-Panel erzeugen, falls schon erzeugt einfach zur�ckgeben Aufruf f�r
	 * Erstellen
	 * 
	 * @param user
	 * @return
	 */
	private HostPanel getHostPanel(User user) {
		if (hostPanel == null) {
			hostPanel = new HostPanel(user, this);
		}
		return hostPanel;
	}
	
	// Getter und Setter
	/**
	 * Get frame
	 * 
	 * @return
	 */
	public JFrame getFrame() {
		return frame;
	}
	
	// Methoden
	/**
	 * Einem Server als der gegebene User mit der gegeben IP beitreten
	 * 
	 * @param ip
	 * @param user
	 * @throws IOException
	 */
	public void joinServer(String ip, String userName) throws IOException {
		try {
			User user = new User(userName, ip);
			// Client-Panel erzeugen und anzeigen
			// Client erzeugen
			LobbyClient client = new LobbyClient(ip, getHostPanel(user));
			// Client der Liste des Client-Panels hinzuf�gen
			hostPanel.setClient(client);
			getCardPanel().add(getHostPanel(user), HOST_PANEL_NAME);
			((CardLayout) getCardPanel().getLayout()).show(getCardPanel(),
					HOST_PANEL_NAME);
			frame.getRootPane().setDefaultButton(getHostPanel(user).getbSenden());
			frame.setPreferredSize(new Dimension(500, 300));
			hostPanel.addUser(user);
			frame.pack();
			frame.validate();
			frame.repaint();
			new PopupDialog(frame,
					PopupDialog.IS_INFORMATION_MESSAGE,
					"Lobby beigetreten!");
		} catch (IOException e) {
			WizardLogger.error("Joining Server failed", e);
			new PopupDialog(frame, PopupDialog.IS_ERROR_MESSAGE, e.getMessage());
		}
	}
	private JPanel getStartPanel() {
		if (startPanel == null) {
			startPanel = new LobbyBeitretenPanel(this);
		}
		return startPanel;
	}
}
