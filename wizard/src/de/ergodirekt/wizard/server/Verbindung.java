package de.ergodirekt.wizard.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Inet4Address;
import java.net.Socket;
import java.net.UnknownHostException;

import de.ergodirekt.wizard.logic.Karte;
import de.ergodirekt.wizard.shared.User;
/**
 * Diese Klasse verarbeitet Befehle vom Client und leitet diese an den Server weiter.
 * @author Tobias
 *
 */
public class Verbindung implements Runnable {

	private Socket socket;
	private WizardServer socketServer;
	private boolean running = true;

	private ObjectOutputStream out;
	private ObjectInputStream in;

	// private String userName;

	public Verbindung(Socket socket, WizardServer socketServer) {
		this.socket = socket;
		this.socketServer = socketServer;
		Thread t = new Thread(this);
		t.start();
	}

	public void run() {
		// Erzeuge Ausgabestrom zum Client
		try {
			out = new ObjectOutputStream(socket.getOutputStream());
			// Erzeuge Eingabestrom zum Client
			in = new ObjectInputStream(socket.getInputStream());

			while (running) {
				// warte auf Input
				try {
					// es muss Serialisierbar sein
					Serializable gesendet = (Serializable) in.readObject();
					// Die Analyse des Objektes
					Serializable returnObj = verarbeite(gesendet);
					// Rückgabe des Objektes
					// out.writeObject(returnObj);
				} catch (ClassNotFoundException e) {
					socketServer.getConsole().write("Fehler beim Lesen: "
							+ e.getMessage());
					e.printStackTrace();
				}
			}
			// beenden
			in.close();
			out.close();
			socketServer.getConsole().write("...beendet");
		} catch (IOException e1) {
			try {
				socketServer.getConsole().write("User disconnected.");
				socketServer.removeUser(Inet4Address.getLocalHost()
						.getHostAddress());
			} catch (UnknownHostException e) {
				socketServer.getConsole().write("User disconnected.");
			}
		}

	}

	public void sende(Kommando obj) {
		try {
			// Sende zum Server
			// System.out.println(userName+" sendet...");
			out.writeObject(obj);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private Serializable verarbeite(Serializable gesendet) throws IOException {
		Kommando kdo = null;
		if (gesendet instanceof Kommando) {
			kdo = (Kommando) gesendet;
		} else {
			// unerwarteter Fehler
			kdo = new Kommando();
			kdo.setReturnWert("Fehlerhafter Aufruf!");
		}
		// beenden
		if (kdo.getKommando().equals("beende")) {
			running = false;
			socketServer.getConsole().write("Server shutting down...");
		}
		// Chatnachricht
		if (kdo.getKommando().equals("text")) {
			socketServer.sendeAnAlle(kdo);
		}
		// neuen User hinzuf�gen
		if (kdo.getKommando().equals("neuerUser")) {
			User u = (User) kdo.getParameter().get(0);
			socketServer.addUser(u);
		}
		if (kdo.getKommando().equals("seiReady")) {
			socketServer.setUserReady((User) kdo.getParameter().get(0));
		}
		if (kdo.getKommando().equals("startGame")) {
			socketServer.startGame();
		}
		if (kdo.getKommando().equals("init")) {
			socketServer.initializeGame(this);
		}
		if (kdo.getKommando().equals("holeListe")) {
			socketServer.getListe();
		}
		if (kdo.getKommando().equals("stichAnsage")) {
			socketServer.stichAnsage((String) kdo.getParameter().get(0),
					(Integer) kdo.getParameter().get(1));
		}
		if (kdo.getKommando().equals("spieleKarte")) {
			socketServer.spieleKarte((Karte) kdo.getParameter().get(0), (String) kdo.getParameter().get(1), this);
		}
		if (kdo.getKommando().equals("waehleTrumpf")) {
			socketServer.waehleTrumpf((String) kdo.getParameter().get(0), (String) kdo.getParameter().get(1));
		}
		return gesendet;
	}

	public void beende() {
		System.out.println("sterbe....");
		Kommando kdo = new Kommando();
		kdo.setKommando("beende");
		try {
			out.writeObject(kdo);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
