package de.ergodirekt.wizard.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import org.junit.BeforeClass;
import org.junit.Test;

import de.ergodirekt.wizard.logic.Karte;
import de.ergodirekt.wizard.logic.Kartenstapel;
import de.ergodirekt.wizard.logic.Spiel;
import de.ergodirekt.wizard.logic.Spieler;
import de.ergodirekt.wizard.logic.Spiellogik;
import de.ergodirekt.wizard.logic.Stich;

public class LogicTestNeu {

	@Test
	public void sortierenTest() {

		/**
		 * 0 - 12 = gruen 1 - 13 13 - 25 = gelb 1 - 13 26 - 38 = rot 1 - 13 39 -
		 * 51 = blau 1 - 13 52, 54, 56, 58 = narren 53, 55, 57, 59 = zauberer
		 */

		List<Spieler> spielerListe = new ArrayList<Spieler>();
		
		Spieler konstantin = new Spieler("Konstantin");
		Spieler manuel = new Spieler("Manuel");
		Spieler philipp = new Spieler("Philipp");
		Spieler tobias = new Spieler("Tobias");
		
		spielerListe.add(konstantin);
		spielerListe.add(manuel);
		spielerListe.add(philipp);
		spielerListe.add(tobias);
		
		Spiel spiel = new Spiel(spielerListe);
		spiel.setRunde(9);
		for(Spieler spieler : spiel.getSpielerListe()){
			spieler.getHand().clear();
		}
		spiel.starteNeueRunde();
		
		assertEquals(10, spiel.getSpielerListe().get(0).getHand().size());
		
		System.out.println("Trumpf: " + spiel.getTrumpf().getFarbe());
		for(Karte karte : spiel.getSpielerListe().get(0).getHand()){
			System.out.println(karte.getWert() + " - " + karte.getFarbe());
		}

	}
}
