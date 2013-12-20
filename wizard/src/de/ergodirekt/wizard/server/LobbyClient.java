package de.ergodirekt.wizard.server;

import java.awt.CardLayout;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;

import javax.swing.JPanel;

import de.ergodirekt.wizard.gui.game.WizardGameGUI;
import de.ergodirekt.wizard.gui.main.HostPanel;
import de.ergodirekt.wizard.gui.main.PopupDialog;
import de.ergodirekt.wizard.logic.Karte;
import de.ergodirekt.wizard.logic.Spieler;
import de.ergodirekt.wizard.shared.User;
import de.ergodirekt.wizard.shared.WizardLogger;
/**
 * Diese Klasse verbindet den Client zum Server und gibt die Befehle an den Server weiter.
 * @author Tobias
 *
 */
public class LobbyClient implements Runnable {
	Socket socket;
	ObjectOutputStream out;
	ObjectInputStream in;
	private boolean running = true;
	private HostPanel hostPanel;
	private WizardGameGUI gui = null;
	private int index;

	public LobbyClient(String ip, HostPanel hostPanel) throws IOException {
		this.hostPanel = hostPanel;
		// Baue Verbindung zum Server auf
		try {
			socket = new Socket(ip, 4444);
			// Erzeuge Ausgabestrom
			out = new ObjectOutputStream(socket.getOutputStream());
			// Erzeuge Eingabestrom

			in = new ObjectInputStream(socket.getInputStream());

			// Thread erzeugen und starten
			Thread t = new Thread(this);
			t.start();

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			throw e;
		}

	}

	public void sende(Kommando obj) {
		try {
			// Sende zum Server
			out.writeObject(obj);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void beende() {
		try {
			Kommando kdo = new Kommando();
			kdo.setKommando("beende");
			sende(kdo);
			// und Ressourcen frei geben
			out.close();
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		// Erzeuge Ausgabestrom zum Client
		while (running) {
			// warte auf Input
			try {
				// es muss Serialisierbar sein
				Serializable gesendet = (Serializable) in.readObject();
				// Die Analyse des Objektes
				verarbeite(gesendet);
			} catch (ClassNotFoundException e) {
				System.out.println("Fehler beim Lesen: " + e.getMessage());
				e.printStackTrace();
			} catch (IOException e) {
				running = false;
				if (gui == null) {
					new PopupDialog(hostPanel,
							PopupDialog.IS_INFORMATION_MESSAGE,
							"Disconnected: Server shutting down.");
					hostPanel.getParentComponent().getCardPanel().add(new JPanel(), "empty");
					((CardLayout) hostPanel.getParentComponent().getCardPanel().getLayout())
							.show(hostPanel.getParentComponent().getCardPanel(), "empty");
				} else {
					new PopupDialog(gui.getFrame(),
							PopupDialog.IS_INFORMATION_MESSAGE,
							"Disconnected: Server shutting down.");
					gui.getFrame().dispose();
				}

			}
		}
		WizardLogger.info("Client shutting down...");

	}

	@SuppressWarnings("unchecked")
	private void verarbeite(Serializable gesendet) {
		Kommando kdo = (Kommando) gesendet;
		if (kdo.getKommando().equals("text")) {
			if (gui == null) {
				hostPanel.writeChatMessage(
						kdo.getParameter().get(0).toString(), kdo
								.getParameter().get(1).toString());
			} else {
				gui.writeChatMessage(kdo.getParameter().get(0).toString(), kdo
						.getParameter().get(1).toString());
			}
		}
		if (kdo.getKommando().equals("sendeUserlist")) {
			if (gui == null) {
				hostPanel.setUserList((List<User>) kdo.getParameter().get(0));
			} else {
				gui.setUserList((List<User>) kdo.getParameter().get(0));
			}
		}
		if (kdo.getKommando().equals("startGame")) {
			startGame();
		}
		if (kdo.getKommando().equals("erhalteKarten")) {
			List<Karte> erhalteneHand = (List<Karte>) kdo.getParameter().get(0);
			gui.getGamePanel().addHandCards(erhalteneHand);
		}
		if (kdo.getKommando().equals("setzeTrumpf")) {
			Karte trumpf = (Karte) kdo.getParameter().get(0);
			gui.getGamePanel().setTrumpf(trumpf);
		}
		if (kdo.getKommando().equals("dran")) {
			gui.getGamePanel().isDran(true);
		}
		if (kdo.getKommando().equals("holeAnsage")) {
			gui.setzeSpieleransage((Spieler) kdo.getParameter().get(0),
					(Integer) kdo.getParameter().get(1));
		}
		if (kdo.getKommando().equals("empfangeGespielteKarte")) {
			gui.legeKarteVon((Karte) kdo.getParameter().get(0), (Integer) kdo.getParameter().get(1), (Integer) kdo.getParameter().get(2));
		}
		if (kdo.getKommando().equals("raeumeAuf")) {
			gui.getGamePanel().raeumaAuf();
		}
		if (kdo.getKommando().equals("punkteSchreiben")) {
			gui.punkteSchreiben((List<Spieler>) kdo.getParameter().get(0), (Integer) kdo.getParameter().get(1));
		}
		if (kdo.getKommando().equals("trumpfWaehlen")) {
			gui.getGamePanel().fordereWaehlen();
		}
		if (kdo.getKommando().equals("zTrumpfTooltipSetzen")) {
			gui.getGamePanel().zTrumpfTooltipSetzen((String) kdo.getParameter().get(0), (String) kdo.getParameter().get(1));
		}
		if (kdo.getKommando().equals("sticheSchreiben")) {
			gui.sticheSchreiben((List<Spieler>) kdo.getParameter().get(0));
		}
	}

	private void startGame() {
		this.gui = new WizardGameGUI(hostPanel.getUser(),
				hostPanel.getUserList().size(), hostPanel.getUserList(), this);
		gui.getFrame().setVisible(true);
		gui.setzeNamen();
		hostPanel.getParentComponent().getFrame().dispose();
	}
}
