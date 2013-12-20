package de.ergodirekt.wizard.test;

import java.util.ArrayList;
import java.util.List;

import de.ergodirekt.wizard.logic.Karte;
import de.ergodirekt.wizard.logic.Spiel;
import de.ergodirekt.wizard.logic.Kartenstapel;
import de.ergodirekt.wizard.logic.Spieler;
import de.ergodirekt.wizard.logic.Stich;

public class LogikGesamtTest {

	public static void main(String[] args) {
		
		Spieler tobias = new Spieler("tobias");
		Spieler manuel = new Spieler("manuel");
		Spieler philipp = new Spieler("philipp");
		Spieler konstantin = new Spieler("konstantin");
		List<Spieler> spielerListe = new ArrayList<Spieler>();
		spielerListe.add(tobias);
		spielerListe.add(manuel);
		spielerListe.add(philipp);
		spielerListe.add(konstantin);

		Spiel spiel = new Spiel(spielerListe);
		
		for (int i = 0; i < 4; i++) {
			int posi = spiel.getStarter() + i;
			if (spiel.getStarter() + i >= 4) {
				posi -= 4;
			}
			if (!spiel.ansagen(spielerListe.get(posi).getName(), 1)) {
				System.err.println("Fehler bei Ansage!");

			}
		}

		for (int i = 0; i < 4; i++) {
			int posi = spiel.getStarter() + i;
			if (spiel.getStarter() + i >= 4) {
				posi -= 4;
			}

		}

		
		spiel.beendeRunde();

		for (Spieler spieler : spielerListe) {
			System.out
					.println("name: " + spieler.getName() + " - " + "hand: "
							 + "stiche: "
							+ spieler.getStiche() + "   -   Ansage: "
							+ spieler.getAnsage() + "   -  Punkte: "+spieler.getPunkte());
		}
	}
}
