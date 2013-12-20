package de.ergodirekt.wizard.server;

import java.io.IOException;
import java.io.Serializable;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import de.ergodirekt.wizard.logic.Karte;
import de.ergodirekt.wizard.logic.Kartenstapel;
import de.ergodirekt.wizard.logic.Spiel;
import de.ergodirekt.wizard.logic.Spieler;
import de.ergodirekt.wizard.logic.Stich;
import de.ergodirekt.wizard.shared.User;
import de.ergodirekt.wizard.shared.WizardLogger;
/**
 * Diese Klasse erzeugt einen neuen Server und nimmt Befehle entgegen und verarbeitet diese.
 * @author Tobias
 *
 */
public class WizardServer implements Runnable {

	private List<Verbindung> verbindungen;
	private boolean running = true;
	private ServerConsole console;

	private List<User> userList = new ArrayList<User>();

	private Spiel spiel;

	private int amZug;
	private Stich stich;

	public WizardServer(ServerConsole serverConsole) {
		// neu 1:n:
		verbindungen = new ArrayList<Verbindung>();
		setConsole(serverConsole);

		// Erzeuge Thread
		Thread t = new Thread(this);
		t.start();
	}

	public void run() {
		// Warte auf Verbindung
		try {
			ServerSocket server = new ServerSocket(4444);
			while (running) {
				getConsole().write(Inet4Address.getLocalHost().getHostAddress()
						+ " running, waiting for clients...");
				Socket socket = server.accept();
				InetAddress adr = socket.getInetAddress();
				getConsole().write("IP connected: " + adr.getHostAddress());
				Verbindung v = new Verbindung(socket, this);
				verbindungen.add(v);
			}
		} catch (IOException e) {
			getConsole().write("A Player has disconnected from the server.");
		}
	}

	public void sendeAnAlle(Kommando kdo) {
		for (int i = 0; i < verbindungen.size(); ++i) {
			Verbindung v = (Verbindung) verbindungen.get(i);
			v.sende(kdo);
		}
	}

	public void addUser(User user) {
		if (userList.size() <= 6) {
			userList.add(user);
			getConsole().writeDebug("Add User, Anzahl: " + userList.size());
			Kommando kdo2 = new Kommando();
			kdo2.setKommando("sendeUserlist");
			kdo2.addParameter((Serializable) new ArrayList<User>(userList));
			sendeAnAlle(kdo2);
		} else {
			// TODO Server voll
		}
	}

	public void setUserReady(User user) {
		for (User currentUser : userList) {
			if (currentUser.getName().equals(user.getName())) {
				if (currentUser.isReady()) {
					currentUser.setReady(false);
					getConsole().write("User " + currentUser.getName()
							+ " is not ready!");
				} else {
					currentUser.setReady(true);
					getConsole().write("User " + currentUser.getName()
							+ " is ready!");
				}
			}
		}
		Kommando kdo2 = new Kommando();
		kdo2.setKommando("sendeUserlist");
		kdo2.addParameter((Serializable) cloneUserList());
		sendeAnAlle(kdo2);
	}

	public List<User> cloneUserList() {
		List<User> newUserList = new ArrayList<User>();
		for (User u : userList) {
			newUserList.add(new User(u.getName(), u.isReady(), u.getIp()));
		}
		return newUserList;
	}
	
	public List<Karte> cloneKarteListe(List<Karte> karteList) {
		List<Karte> newKarteList = new ArrayList<Karte>();
		for (Karte h : karteList) {
			newKarteList.add(new Karte(h.getWert(), h.getFarbe(), h.getBild()));
		}
		return newKarteList;
	}
	
	public List<Spieler> cloneSpielerListe(List<Spieler> spielerList) {
		List<Spieler> newSpielerList = new ArrayList<Spieler>();
		for (Spieler spieler : spielerList) {
			newSpielerList.add(new Spieler(spieler.getName(), spieler.getHand(), spieler.getPunkte(), spieler.getAnsage(), spieler.getStiche()));
		}
		return newSpielerList;
	}
	

	public void removeUser(String hostAddress) {
		for (User u : userList) {
			if (u.getIp().equals(hostAddress) || u.getIp().equals("127.0.0.1")
					|| u.getIp().equals("localhost")) {
				userList.remove(u);
				break;
			}
		}
		getConsole().write("User-List-Size: " + userList.size());
	}

	public void startGame() {
		List<Spieler> playerList = new ArrayList<Spieler>();
		for (User u : userList) {
			playerList.add(new Spieler(u.getName()));
		}
		spiel = new Spiel(playerList);
		spiel.setTrumpf(new Karte(14, Kartenstapel.KEINE_FARBE, new ImageIcon("img/z4.png")));
		Kommando kdo = new Kommando();
		kdo.setKommando("startGame");
		sendeAnAlle(kdo);
	}

	public synchronized void initializeGame(Verbindung v) {
		int index = verbindungen.indexOf(v);
		Kommando kdo2 = new Kommando();
		kdo2.setKommando("erhalteKarten");
		kdo2.addParameter((Serializable) cloneKarteListe(spiel.getSpielerListe().get(index).getHand()));
		v.sende(kdo2);
		Kommando kdo3 = new Kommando();
		kdo3.setKommando("setzeTrumpf");
		kdo3.addParameter(spiel.getTrumpf());
		v.sende(kdo3);
		int index1 = spiel.getStarter();
		if (index1 == index) {
			Kommando kdo4 = new Kommando();
			kdo4.setKommando("dran");
			amZug = index1;
			v.sende(kdo4);
		}
		if (index == spiel.getKartengeber() && spiel.getTrumpf().getWert() == 14) {
			Kommando kdo5 = new Kommando();
			kdo5.setKommando("trumpfWaehlen");
			v.sende(kdo5);
		}
	}

	private void sendeAnName(Kommando kdo, int index) {
		Verbindung v = (Verbindung) verbindungen.get(index);
		v.sende(kdo);
	}

	public void getListe() {
		Kommando kdo2 = new Kommando();
		kdo2.setKommando("sendeUserlist");
		kdo2.addParameter((Serializable) cloneUserList());
		sendeAnAlle(kdo2);
	}

	public void stichAnsage(String name, int stiche) {
		spiel.ansagen(name, stiche);
		Spieler ansager = spiel.getSpielerListe().get(
				spiel.getPositionVonName(name));
		Spieler ansagerReturn = new Spieler(ansager.getName(), ansager.getHand(), ansager.getPunkte(), ansager.getAnsage(), ansager.getStiche());
		Kommando kdo = new Kommando();
		kdo.setKommando("holeAnsage");
		kdo.addParameter(ansagerReturn);
		kdo.addParameter(spiel.getRunde());
		sendeAnAlle(kdo);
		naechster();
		Kommando kdo4 = new Kommando();
		kdo4.setKommando("dran");
		sendeAnName(kdo4, amZug);
	}

	public void naechster() {
		amZug++;
		if (amZug >= userList.size()) {
			amZug = 0;
		}
	}

	public void spieleKarte(Karte karte, String name, Verbindung leger) {
		spiel.spieleKarte(name, karte);
		for (int i = 0; i < verbindungen.size(); ++i) {
			Verbindung v = (Verbindung) verbindungen.get(i);
			if (!v.equals(leger)) {
				Kommando kdo = new Kommando();
				kdo.setKommando("empfangeGespielteKarte");
				kdo.addParameter(karte);
				kdo.addParameter(verbindungen.indexOf(leger));
				kdo.addParameter(verbindungen.indexOf(v));
				v.sende(kdo);
			}
		}
		stich = spiel.getStichListe().get(spiel.getStichListe().size() - 1);
		if (stich.getGespielteKarten().size() < userList.size()) {
			naechster();
			Verbindung v = verbindungen.get(amZug);
			Kommando kdo = new Kommando();
			kdo.setKommando("dran");
			v.sende(kdo);
		} else {
			try {
				// warte einen Moment mit dem aufräumen, damit alle die gelegten Karten sehen
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Kommando kdo1 = new Kommando();
			kdo1.setKommando("raeumeAuf");
			sendeAnAlle(kdo1);
			if (spiel.getSpielerListe().get(0).getHand().isEmpty()) {
				spiel.beendeRunde();
				Kommando kdo = new Kommando();
				kdo.setKommando("punkteSchreiben");
				kdo.addParameter((Serializable) cloneSpielerListe(spiel.getSpielerListe()));
				kdo.addParameter(spiel.getRunde());
				sendeAnAlle(kdo);
				spiel.starteNeueRunde();
				for(Verbindung v : verbindungen) {
					initializeGame(v);
				}
			} else {
				amZug = spiel.getStichSieger();
				Kommando kdo2 = new Kommando();
				kdo2.setKommando("sticheSchreiben");
				kdo2.addParameter((Serializable) cloneSpielerListe(spiel.getSpielerListe()));
				for (Spieler s : spiel.getSpielerListe()) {
					WizardLogger.info("Stiche: " + s.getStiche());
				}
				sendeAnAlle(kdo2);
				spiel.setStarter(amZug);
				Verbindung v = verbindungen.get(amZug);
				Kommando kdo = new Kommando();
				kdo.setKommando("dran");
				v.sende(kdo);
			}
		}
	}

	public void sendeAnAlleAusser(Kommando kdo, Verbindung ausser) {
		for (int i = 0; i < verbindungen.size(); ++i) {
			Verbindung v = (Verbindung) verbindungen.get(i);
			if (!v.equals(ausser)) {
				v.sende(kdo);
			}
		}
	}

	public void waehleTrumpf(String trumpfFarbe, String spielerName) {
		// TODO 
		spiel.setTrumpf(trumpfFarbe);
		Kommando kdo = new Kommando();
		kdo.setKommando("zTrumpfTooltipSetzen");
		kdo.addParameter(spiel.getTrumpf().getFarbe());
		kdo.addParameter(spielerName);
		sendeAnAlle(kdo);
	}

	public ServerConsole getConsole() {
		return console;
	}

	public void setConsole(ServerConsole console) {
		this.console = console;
	}
}
