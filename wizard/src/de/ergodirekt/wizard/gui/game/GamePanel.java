package de.ergodirekt.wizard.gui.game;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.border.LineBorder;

import de.ergodirekt.wizard.gui.main.PopupDialog;
import de.ergodirekt.wizard.logic.Karte;

/**
 * In dieser Klasse wird das Game-Panel initialisiert (Layout + Spiellogik).
 * 
 * @author Tobias
 * 
 */
public class GamePanel extends JLayeredPane {
	private JLabel lBg;

	public static final Point STACK_POSITION = new Point(315, 250);
	public static final Point PLAYER_LAYDOWN_POSITION = new Point(315, 361);
	public static final Point PLAYER_HAND_POSITION = new Point(10, 489);
	public static final Point P3_E1_LAYDOWN_POSITION = new Point(183, 139);
	public static final Point P3_E2_LAYDOWN_POSITION = new Point(437, 139);
	public static final Point P4_E1_LAYDOWN_POSITION = new Point(185, 250);
	public static final Point P4_E2_LAYDOWN_POSITION = new Point(315, 104);
	public static final Point P4_E3_LAYDOWN_POSITION = new Point(443, 250);
	public static final Point P5_E1_LAYDOWN_POSITION = new Point(185, 304);
	public static final Point P5_E2_LAYDOWN_POSITION = new Point(185, 102);
	public static final Point P5_E3_LAYDOWN_POSITION = new Point(441, 102);
	public static final Point P5_E4_LAYDOWN_POSITION = new Point(441, 304);
	public static final Point P6_E1_LAYDOWN_POSITION = new Point(185, 304);
	public static final Point P6_E2_LAYDOWN_POSITION = new Point(185, 102);
	public static final Point P6_E3_LAYDOWN_POSITION = new Point(315, 102);
	public static final Point P6_E4_LAYDOWN_POSITION = new Point(438, 102);
	public static final Point P6_E5_LAYDOWN_POSITION = new Point(438, 304);
	public static final Point P3_E1_HAND_POSITION = new Point(10, 11);
	public static final Point P3_E2_HAND_POSITION = new Point(620, 11);
	public static final Point P4_E1_HAND_POSITION = new Point(10, 204);
	public static final Point P4_E2_HAND_POSITION = new Point(315, 11);
	public static final Point P4_E3_HAND_POSITION = new Point(620, 204);
	public static final Point P5_E1_HAND_POSITION = new Point(10, 361);
	public static final Point P5_E2_HAND_POSITION = new Point(10, 11);
	public static final Point P5_E3_HAND_POSITION = new Point(620, 11);
	public static final Point P5_E4_HAND_POSITION = new Point(620, 361);
	public static final Point P6_E1_HAND_POSITION = new Point(10, 361);
	public static final Point P6_E2_HAND_POSITION = new Point(10, 11);
	public static final Point P6_E3_HAND_POSITION = new Point(315, 11);
	public static final Point P6_E4_HAND_POSITION = new Point(620, 11);
	public static final Point P6_E5_HAND_POSITION = new Point(620, 361);
	
	public static final Point P3_E1_NAME_POSITION = new Point(106, 11);
	public static final Point P3_E2_NAME_POSITION = new Point(537, 11);
	public static final Point P4_E1_NAME_POSITION = new Point(10, 162);
	public static final Point P4_E2_NAME_POSITION = new Point(231, 11);
	public static final Point P4_E3_NAME_POSITION = new Point(625, 162);
	public static final Point P5_E1_NAME_POSITION = new Point(10, 305);
	public static final Point P5_E2_NAME_POSITION = new Point(10, 125);
	public static final Point P5_E3_NAME_POSITION = new Point(625, 125);
	public static final Point P5_E4_NAME_POSITION = new Point(625, 305);
	public static final Point P6_E1_NAME_POSITION = new Point(10, 305);
	public static final Point P6_E2_NAME_POSITION = new Point(10, 125);
	public static final Point P6_E3_NAME_POSITION = new Point(231, 11);
	public static final Point P6_E4_NAME_POSITION = new Point(625, 125);
	public static final Point P6_E5_NAME_POSITION = new Point(625, 305);
	
	public static final Point PX_S_STICHE_POSITION = new Point(10, 430);
	public static final Point P3_E1_STICHE_POSITION = new Point(10, 164);
	public static final Point P3_E2_STICHE_POSITION = new Point(644, 164);
	public static final Point P4_E1_STICHE_POSITION = new Point(10, 294);
	public static final Point P4_E2_STICHE_POSITION = new Point(316, 124);
	public static final Point P4_E3_STICHE_POSITION = new Point(644, 294);
	public static final Point P5_E1_STICHE_POSITION = new Point(53, 282);
	public static final Point P5_E2_STICHE_POSITION = new Point(53, 124);
	public static final Point P5_E3_STICHE_POSITION = new Point(588, 124);
	public static final Point P5_E4_STICHE_POSITION = new Point(588, 282);
	public static final Point P6_E1_STICHE_POSITION = new Point(53, 282);
	public static final Point P6_E2_STICHE_POSITION = new Point(53, 124);
	public static final Point P6_E3_STICHE_POSITION = new Point(316, 124);
	public static final Point P6_E4_STICHE_POSITION = new Point(588, 124);
	public static final Point P6_E5_STICHE_POSITION = new Point(588, 282);
	

	private List<HandkarteLabel> handkarteLabels = new ArrayList<HandkarteLabel>();

	public static final int CARD_HEIGHT = 102;
	public static final int CARD_WIDTH = 67;

	private static int highestLayer = 1;

	private int numberOfPlayers;

	private boolean isDran = false;
	private boolean sticheAngesagt = false;

	private WizardGameGUI gui;

	private JLabel layedCardP;
	private JLabel layedCardE1;
	private JLabel layedCardE2;
	private JLabel layedCardE3;
	private JLabel layedCardE4;
	private JLabel layedCardE5;
	
	private JLabel sticheP;
	private JLabel sticheE1;
	private JLabel sticheE2;
	private JLabel sticheE3;
	private JLabel sticheE4;
	private JLabel sticheE5;

	private JLabel lTrumpf;

	private Karte trumpf;

	/**
	 * Größe des Fensters wird gesetzt und aktualisiert
	 * 
	 * @author Tobias
	 */
	public GamePanel(int numberOfPlayers, WizardGameGUI gui) {
		this.numberOfPlayers = numberOfPlayers;
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setSize(new Dimension(700, 600));
		setPreferredSize(new Dimension(700, 600));
		this.gui = gui;
		// TEST
		// TEST ENDE
		initHands();
		// TEST
		revalidate();
		validate();
		repaint();
	}

	/**
	 * Trumpf wird an vorgegebene Position gesetzt
	 * 
	 * @param trumpf
	 */
	public void setTrumpf(Karte trumpf) {
		this.trumpf = trumpf;
		lTrumpf = new JLabel(trumpf.getBild());
		lTrumpf.setLocation(STACK_POSITION);
		lTrumpf.setSize(CARD_WIDTH, CARD_HEIGHT);
		lTrumpf.setToolTipText("Trumpf: " + trumpf.getFarbe());
		add(lTrumpf, 1);
	}

	private void initHands() {
		sticheP = new JLabel();
		sticheP.setText("0");
		sticheP.setSize(new Dimension(50, 50));
		sticheP.setFont(new Font("Tahoma", Font.PLAIN, 18));
		sticheP.setForeground(Color.YELLOW);
		sticheP.setLocation(PX_S_STICHE_POSITION);
		add(sticheP, 1);
		switch (numberOfPlayers) {
		case 3:
			JLabel bgLabel1_1 = new JLabel(new ImageIcon(
					"img/kartenruecken.png"));
			bgLabel1_1.setLocation(P3_E1_HAND_POSITION);
			bgLabel1_1.setSize(CARD_WIDTH, CARD_HEIGHT);
			add(bgLabel1_1, 1);
			JLabel bgLabel1_2 = new JLabel(new ImageIcon(
					"img/kartenruecken.png"));
			bgLabel1_2.setLocation(P3_E2_HAND_POSITION);
			bgLabel1_2.setSize(CARD_WIDTH, CARD_HEIGHT);
			add(bgLabel1_2, 1);
			sticheE1 = new JLabel();
			sticheE1.setText("0");
			sticheE1.setSize(new Dimension(50, 50));
			sticheE1.setFont(new Font("Tahoma", Font.PLAIN, 18));
			sticheE1.setForeground(Color.YELLOW);
			sticheE1.setLocation(P3_E1_STICHE_POSITION);
			add(sticheE1, 1);
			sticheE2 = new JLabel();
			sticheE2.setText("0");
			sticheE2.setSize(new Dimension(50, 50));
			sticheE2.setFont(new Font("Tahoma", Font.PLAIN, 18));
			sticheE2.setForeground(Color.YELLOW);
			sticheE2.setLocation(P3_E2_STICHE_POSITION);
			add(sticheE2, 1);
			break;
		case 4:
			JLabel bgLabel2_1 = new JLabel(new ImageIcon(
					"img/kartenruecken.png"));
			bgLabel2_1.setLocation(P4_E1_HAND_POSITION);
			bgLabel2_1.setSize(CARD_WIDTH, CARD_HEIGHT);
			add(bgLabel2_1, 1);
			JLabel bgLabel2_2 = new JLabel(new ImageIcon(
					"img/kartenruecken.png"));
			bgLabel2_2.setLocation(P4_E2_HAND_POSITION);
			bgLabel2_2.setSize(CARD_WIDTH, CARD_HEIGHT);
			add(bgLabel2_2, 1);
			JLabel bgLabel2_3 = new JLabel(new ImageIcon(
					"img/kartenruecken.png"));
			bgLabel2_3.setLocation(P4_E3_HAND_POSITION);
			bgLabel2_3.setSize(CARD_WIDTH, CARD_HEIGHT);
			add(bgLabel2_3, 1);
			sticheE1 = new JLabel();
			sticheE1.setText("0");
			sticheE1.setSize(new Dimension(50, 50));
			sticheE1.setFont(new Font("Tahoma", Font.PLAIN, 18));
			sticheE1.setForeground(Color.YELLOW);
			sticheE1.setLocation(P4_E1_STICHE_POSITION);
			add(sticheE1, 1);
			sticheE2 = new JLabel();
			sticheE2.setText("0");
			sticheE2.setSize(new Dimension(50, 50));
			sticheE2.setFont(new Font("Tahoma", Font.PLAIN, 18));
			sticheE2.setForeground(Color.YELLOW);
			sticheE2.setLocation(P4_E2_STICHE_POSITION);
			add(sticheE1, 1);
			sticheE3 = new JLabel();
			sticheE3.setText("0");
			sticheE3.setSize(new Dimension(50, 50));
			sticheE3.setFont(new Font("Tahoma", Font.PLAIN, 18));
			sticheE3.setForeground(Color.YELLOW);
			sticheE3.setLocation(P4_E3_STICHE_POSITION);
			add(sticheE3, 1);
			break;
		case 5:
			JLabel bgLabel3_1 = new JLabel(new ImageIcon(
					"img/kartenruecken.png"));
			bgLabel3_1.setLocation(P5_E1_HAND_POSITION);
			bgLabel3_1.setSize(CARD_WIDTH, CARD_HEIGHT);
			add(bgLabel3_1, 1);
			JLabel bgLabel3_2 = new JLabel(new ImageIcon(
					"img/kartenruecken.png"));
			bgLabel3_2.setLocation(P5_E2_HAND_POSITION);
			bgLabel3_2.setSize(CARD_WIDTH, CARD_HEIGHT);
			add(bgLabel3_2, 1);
			JLabel bgLabel3_3 = new JLabel(new ImageIcon(
					"img/kartenruecken.png"));
			bgLabel3_3.setLocation(P5_E3_HAND_POSITION);
			bgLabel3_3.setSize(CARD_WIDTH, CARD_HEIGHT);
			add(bgLabel3_3, 1);
			JLabel bgLabel3_4 = new JLabel(new ImageIcon(
					"img/kartenruecken.png"));
			bgLabel3_4.setLocation(P5_E4_HAND_POSITION);
			bgLabel3_4.setSize(CARD_WIDTH, CARD_HEIGHT);
			add(bgLabel3_4, 1);
			sticheE1 = new JLabel();
			sticheE1.setText("0");
			sticheE1.setSize(new Dimension(50, 50));
			sticheE1.setFont(new Font("Tahoma", Font.PLAIN, 18));
			sticheE1.setForeground(Color.YELLOW);
			sticheE1.setLocation(P5_E1_STICHE_POSITION);
			add(sticheE1, 1);
			sticheE2 = new JLabel();
			sticheE2.setText("0");
			sticheE2.setSize(new Dimension(50, 50));
			sticheE2.setFont(new Font("Tahoma", Font.PLAIN, 18));
			sticheE2.setForeground(Color.YELLOW);
			sticheE2.setLocation(P5_E2_STICHE_POSITION);
			add(sticheE2, 1);
			sticheE3 = new JLabel();
			sticheE3.setText("0");
			sticheE3.setSize(new Dimension(50, 50));
			sticheE3.setFont(new Font("Tahoma", Font.PLAIN, 18));
			sticheE3.setForeground(Color.YELLOW);
			sticheE3.setLocation(P5_E3_STICHE_POSITION);
			add(sticheE3, 1);
			sticheE4 = new JLabel();
			sticheE4.setText("0");
			sticheE4.setSize(new Dimension(50, 50));
			sticheE4.setFont(new Font("Tahoma", Font.PLAIN, 18));
			sticheE4.setForeground(Color.YELLOW);
			sticheE4.setLocation(P5_E4_STICHE_POSITION);
			add(sticheE4, 1);
			break;
		case 6:
			JLabel bgLabel4_1 = new JLabel(new ImageIcon(
					"img/kartenruecken.png"));
			bgLabel4_1.setLocation(P6_E1_HAND_POSITION);
			bgLabel4_1.setSize(CARD_WIDTH, CARD_HEIGHT);
			add(bgLabel4_1, 1);
			JLabel bgLabel4_2 = new JLabel(new ImageIcon(
					"img/kartenruecken.png"));
			bgLabel4_2.setLocation(P6_E2_HAND_POSITION);
			bgLabel4_2.setSize(CARD_WIDTH, CARD_HEIGHT);
			add(bgLabel4_2, 1);
			JLabel bgLabel4_3 = new JLabel(new ImageIcon(
					"img/kartenruecken.png"));
			bgLabel4_3.setLocation(P6_E3_HAND_POSITION);
			bgLabel4_3.setSize(CARD_WIDTH, CARD_HEIGHT);
			add(bgLabel4_3, 1);
			JLabel bgLabel4_4 = new JLabel(new ImageIcon(
					"img/kartenruecken.png"));
			bgLabel4_4.setLocation(P6_E4_HAND_POSITION);
			bgLabel4_4.setSize(CARD_WIDTH, CARD_HEIGHT);
			add(bgLabel4_4, 1);
			JLabel bgLabel4_5 = new JLabel(new ImageIcon(
					"img/kartenruecken.png"));
			bgLabel4_5.setLocation(P6_E5_HAND_POSITION);
			bgLabel4_5.setSize(CARD_WIDTH, CARD_HEIGHT);
			add(bgLabel4_5, 1);
			sticheE1 = new JLabel();
			sticheE1.setText("0");
			sticheE1.setSize(new Dimension(50, 50));
			sticheE1.setFont(new Font("Tahoma", Font.PLAIN, 18));
			sticheE1.setForeground(Color.YELLOW);
			sticheE1.setLocation(P6_E1_STICHE_POSITION);
			add(sticheE1, 1);
			sticheE2 = new JLabel();
			sticheE2.setText("0");
			sticheE2.setSize(new Dimension(50, 50));
			sticheE2.setFont(new Font("Tahoma", Font.PLAIN, 18));
			sticheE2.setForeground(Color.YELLOW);
			sticheE2.setLocation(P6_E2_STICHE_POSITION);
			add(sticheE2, 1);
			sticheE3 = new JLabel();
			sticheE3.setText("0");
			sticheE3.setSize(new Dimension(50, 50));
			sticheE3.setFont(new Font("Tahoma", Font.PLAIN, 18));
			sticheE3.setForeground(Color.YELLOW);
			sticheE3.setLocation(P6_E3_STICHE_POSITION);
			add(sticheE3, 1);
			sticheE4 = new JLabel();
			sticheE4.setText("0");
			sticheE4.setSize(new Dimension(50, 50));
			sticheE4.setFont(new Font("Tahoma", Font.PLAIN, 18));
			sticheE4.setForeground(Color.YELLOW);
			sticheE4.setLocation(P6_E4_STICHE_POSITION);
			add(sticheE4, 1);
			sticheE5 = new JLabel();
			sticheE5.setText("0");
			sticheE5.setSize(new Dimension(50, 50));
			sticheE5.setFont(new Font("Tahoma", Font.PLAIN, 18));
			sticheE5.setForeground(Color.YELLOW);
			sticheE5.setLocation(P6_E5_STICHE_POSITION);
			add(sticheE5, 1);
			break;
		}
		setCursor(new Cursor(Cursor.WAIT_CURSOR));
		revalidate();
		validate();
		repaint();
	}

	private JLabel getLBg() {
		if (lBg == null) {
			lBg = new JLabel(new ImageIcon("img/bg.png"));
			lBg.setBounds(0, 0, 700, 600);
			setLayer(lBg, 0);
		}
		return lBg;
	}

	/**
	 * 
	 * @param label
	 */
	public void highlight(HandkarteLabel label) {
		label.setLocation(label.getLocation().x, label.getLocation().y - 30);
	}

	public void lowlight(HandkarteLabel label) {
		label.setLocation(label.getLocation().x, label.getLocation().y + 30);
	}

	/**
	 * Handkarten werden an eine vorgegebene Position angeordnet.
	 * 
	 * @param handkarten
	 */
	public void addHandCards(List<Karte> handkarten) {
		remove(getLBg());
		int counter = 0;
		handkarteLabels = new ArrayList<HandkarteLabel>();
		setSticheAngesagt(false);
		for (Karte karte : handkarten) {
			handkarteLabels.add(new HandkarteLabel(this, karte,
					PLAYER_HAND_POSITION.x + counter, PLAYER_HAND_POSITION.y));
			counter += 25;
			highestLayer++;
		}
		for (HandkarteLabel k : handkarteLabels) {
			add(k, handkarteLabels.indexOf(k) + 5);
		}
		add(getLBg());
		revalidate();
		validate();
		repaint();
	}

	/**
	 * Wenn der Spieler angesagt hat wird der Cursor so veränder, dass er eine
	 * Karte legen darf.
	 * 
	 * @param isDran
	 */
	public void isDran(boolean isDran) {
		if (isSticheAngesagt()) {
			for (HandkarteLabel k : handkarteLabels) {
				k.setDran(isDran);
			}
		}
		this.isDran = isDran;
		setCursor(isDran);
	}

	/**
	 * Cursor wird verändert, wenn der Spieler nicht mit der Ansage dran ist.
	 * 
	 * @param isDran
	 */
	private void setCursor(boolean isDran) {
		if (isDran == true) {
			setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			if (!isSticheAngesagt()) {
				gui.getbAnsage().setEnabled(true);
			}
		} else {
			setCursor(new Cursor(Cursor.WAIT_CURSOR));
			gui.getbAnsage().setEnabled(false);
		}
	}

	/**
	 * Karte des Spielers wird zentral unter der Trumpfkarte hingelegt. (Wenn er
	 * dran ist)
	 * 
	 * @param karte
	 */
	public void spieleKarte(HandkarteLabel karte) {
		isDran = false;
		for (HandkarteLabel l : handkarteLabels) {
			l.setDran(false);
		}
		setCursor(isDran);
		this.layedCardP = karte;
		gui.spieleKarte(karte.getKarte());
	}

	/**
	 * Die Karte wird je nach Anzahl der Spieler wird auf den Bildschirmen der
	 * Mitspieler die Karte an unterschiedlichen Positionen angezeigt.
	 * 
	 * @param karte
	 * @param playerPos
	 * @param currentPos
	 * @param listSize
	 */
	public void legeKarteVon(Karte karte, Integer playerPos,
			Integer currentPos, int listSize) {
		remove(getLBg());
		JLabel card = new JLabel(karte.getBild());
		card.setSize(CARD_WIDTH, CARD_HEIGHT);
		switch (numberOfPlayers) {
		case 3:
			if (playerPos == currentPos + 1
					|| (currentPos == listSize - 1 && playerPos == 0)) {
				card.setLocation(P3_E1_LAYDOWN_POSITION);
				layedCardE1 = card;
			} else {
				card.setLocation(P3_E2_LAYDOWN_POSITION);
				layedCardE2 = card;
			}
			break;
		case 4:
			if (playerPos == currentPos + 1
					|| (currentPos == listSize - 1 && playerPos == 0)) {
				card.setLocation(P4_E1_LAYDOWN_POSITION);
				layedCardE1 = card;
			} else {
				int x = currentPos + 2;
				if (x >= listSize)
					x = x - listSize;
				if (playerPos == x
						|| (currentPos == listSize - 1 && playerPos == 1)) {
					card.setLocation(P4_E2_LAYDOWN_POSITION);
					layedCardE2 = card;
				} else {
					card.setLocation(P4_E3_LAYDOWN_POSITION);
					layedCardE3 = card;
				}
			}
			break;
		case 5:
			if (playerPos == currentPos + 1
					|| (currentPos == listSize - 1 && playerPos == 0)) {
				card.setLocation(P5_E1_LAYDOWN_POSITION);
				layedCardE1 = card;
			} else {
				int x = currentPos + 2;
				if (x >= listSize)
					x = x - listSize;
				if (playerPos == x
						|| (currentPos == listSize - 1 && playerPos == 1)) {
					card.setLocation(P5_E2_LAYDOWN_POSITION);
					layedCardE2 = card;
				} else {
					int y = currentPos + 3;
					if (y >= listSize)
						y = y - listSize;
					if (playerPos == y
							|| (currentPos == listSize - 1 && playerPos == 2)) {
						card.setLocation(P5_E3_LAYDOWN_POSITION);
						layedCardE3 = card;
					} else {
						card.setLocation(P5_E4_LAYDOWN_POSITION);
						layedCardE4 = card;
					}
				}
			}
			break;
		case 6:
			if (playerPos == currentPos + 1
					|| (currentPos == listSize - 1 && playerPos == 0)) {
				card.setLocation(P6_E1_LAYDOWN_POSITION);
				layedCardE1 = card;
			} else {
				int x = currentPos + 2;
				if (x >= listSize)
					x = x - listSize;
				if (playerPos == x
						|| (currentPos == listSize - 1 && playerPos == 1)) {
					card.setLocation(P6_E2_LAYDOWN_POSITION);
					layedCardE2 = card;
				} else {
					int y = currentPos + 3;
					if (y >= listSize)
						y = y - listSize;
					if (playerPos == y
							|| (currentPos == listSize - 1 && playerPos == 2)) {
						card.setLocation(P6_E3_LAYDOWN_POSITION);
						layedCardE3 = card;
					} else {
						int z = currentPos + 4;
						if (z >= listSize)
							z = z - listSize;
						if (playerPos == z
								|| (currentPos == listSize - 1 && playerPos == 3)) {
							card.setLocation(P6_E4_LAYDOWN_POSITION);
							layedCardE4 = card;
						} else {
							card.setLocation(P6_E5_LAYDOWN_POSITION);
							layedCardE5 = card;
						}
					}
				}
			}
			break;
		}
		add(card, 1);
		add(getLBg());
		revalidate();
		validate();
		repaint();
	}

	/**
	 * Nach ein paar Sekunden verschwinden die Karten und die neuen Handkarten erscheinen links unten.
	 */
	public void raeumaAuf() {
		try {
			remove(layedCardP);
			remove(layedCardE1);
			remove(layedCardE2);
			remove(layedCardE3);
			remove(layedCardE4);
			remove(layedCardE5);
		} catch (NullPointerException e) {
			// do nothing, try to remove everything
		}
		revalidate();
		validate();
		repaint();
	}

	public void trumpfWaehlen(String selectedItem) {
		gui.trumpfWaehlen(selectedItem);
	}
	
	public void zTrumpfTooltipSetzen(String trumpfFarbe, String spielerName) {
		lTrumpf.setToolTipText("Trumpf: " + trumpfFarbe);
		lTrumpf.setText(trumpfFarbe);
		new PopupDialog(this, PopupDialog.IS_INFORMATION_MESSAGE, spielerName + " setzt den Trumpf auf " + trumpfFarbe);
		if (trumpfFarbe.equals("rot")) 
			lTrumpf.setForeground(Color.RED);
		if (trumpfFarbe.equals("gruen")) 
			lTrumpf.setForeground(Color.GREEN);
		if (trumpfFarbe.equals("blau")) 
			lTrumpf.setForeground(Color.BLUE);
		if (trumpfFarbe.equals("gelb")) 
			lTrumpf.setForeground(Color.YELLOW);
	}

	public void fordereWaehlen() {
		new TrumpfWaehler(this);
	}

	public void setzeNamen(String string, Integer playerPos, Integer currentPos, int listSize) {
		remove(getLBg());
		JLabel nameLabel = new JLabel(string);
		nameLabel.setSize(100, 20);
		nameLabel.setForeground(Color.LIGHT_GRAY);
		switch (numberOfPlayers) {
		case 3:
			if (playerPos == currentPos + 1
					|| (currentPos == listSize - 1 && playerPos == 0)) {
				nameLabel.setLocation(P3_E1_NAME_POSITION);
			} else {
				nameLabel.setLocation(P3_E2_NAME_POSITION);
			}
			break;
		case 4:
			if (playerPos == currentPos + 1
					|| (currentPos == listSize - 1 && playerPos == 0)) {
				nameLabel.setLocation(P4_E1_NAME_POSITION);
			} else {
				int x = currentPos + 2;
				if (x >= listSize)
					x = x - listSize;
				if (playerPos == x
						|| (currentPos == listSize - 1 && playerPos == 1)) {
					nameLabel.setLocation(P4_E2_NAME_POSITION);
				} else {
					nameLabel.setLocation(P4_E3_NAME_POSITION);
				}
			}
			break;
		case 5:
			if (playerPos == currentPos + 1
					|| (currentPos == listSize - 1 && playerPos == 0)) {
				nameLabel.setLocation(P5_E1_NAME_POSITION);
			} else {
				int x = currentPos + 2;
				if (x >= listSize)
					x = x - listSize;
				if (playerPos == x
						|| (currentPos == listSize - 1 && playerPos == 1)) {
					nameLabel.setLocation(P5_E2_NAME_POSITION);
				} else {
					int y = currentPos + 3;
					if (y >= listSize)
						y = y - listSize;
					if (playerPos == y
							|| (currentPos == listSize - 1 && playerPos == 2)) {
						nameLabel.setLocation(P5_E3_NAME_POSITION);
					} else {
						nameLabel.setLocation(P5_E4_NAME_POSITION);
					}
				}
			}
			break;
		case 6:
			if (playerPos == currentPos + 1
					|| (currentPos == listSize - 1 && playerPos == 0)) {
				nameLabel.setLocation(P6_E1_NAME_POSITION);
			} else {
				int x = currentPos + 2;
				if (x >= listSize)
					x = x - listSize;
				if (playerPos == x
						|| (currentPos == listSize - 1 && playerPos == 1)) {
					nameLabel.setLocation(P6_E2_NAME_POSITION);
				} else {
					int y = currentPos + 3;
					if (y >= listSize)
						y = y - listSize;
					if (playerPos == y
							|| (currentPos == listSize - 1 && playerPos == 2)) {
						nameLabel.setLocation(P6_E3_NAME_POSITION);
					} else {
						int z = currentPos + 4;
						if (z >= listSize)
							z = z - listSize;
						if (playerPos == z
								|| (currentPos == listSize - 1 && playerPos == 3)) {
							nameLabel.setLocation(P6_E4_NAME_POSITION);
						} else {
							nameLabel.setLocation(P6_E5_NAME_POSITION);
						}
					}
				}
			}
			break;
		}
		add(nameLabel, 1);
		add(getLBg());
		revalidate();
		validate();
		repaint();
	}
		
	public boolean isSticheAngesagt() {
		return sticheAngesagt;
	}

	public void setSticheAngesagt(boolean sticheAngesagt) {
		this.sticheAngesagt = sticheAngesagt;
	}

	public void setzeStiche(int stiche, Integer playerPos, Integer currentPos, int listSize) {
		remove(getLBg());
		switch (numberOfPlayers) {
		case 3:
			if (playerPos == currentPos + 1
					|| (currentPos == listSize - 1 && playerPos == 0)) {
				sticheE1.setText(""+stiche);
			} else {
				sticheE2.setText(""+stiche);
			}
			break;
		case 4:
			if (playerPos == currentPos + 1
					|| (currentPos == listSize - 1 && playerPos == 0)) {
				sticheE1.setText(""+stiche);
			} else {
				int x = currentPos + 2;
				if (x >= listSize)
					x = x - listSize;
				if (playerPos == x
						|| (currentPos == listSize - 1 && playerPos == 1)) {
					sticheE2.setText(""+stiche);
				} else {
					sticheE3.setText(""+stiche);
				}
			}
			break;
		case 5:
			if (playerPos == currentPos + 1
					|| (currentPos == listSize - 1 && playerPos == 0)) {
				sticheE1.setText(""+stiche);
			} else {
				int x = currentPos + 2;
				if (x >= listSize)
					x = x - listSize;
				if (playerPos == x
						|| (currentPos == listSize - 1 && playerPos == 1)) {
					sticheE2.setText(""+stiche);
				} else {
					int y = currentPos + 3;
					if (y >= listSize)
						y = y - listSize;
					if (playerPos == y
							|| (currentPos == listSize - 1 && playerPos == 2)) {
						sticheE3.setText(""+stiche);
					} else {
						sticheE4.setText(""+stiche);
					}
				}
			}
			break;
		case 6:
			if (playerPos == currentPos + 1
					|| (currentPos == listSize - 1 && playerPos == 0)) {
				sticheE1.setText(""+stiche);
			} else {
				int x = currentPos + 2;
				if (x >= listSize)
					x = x - listSize;
				if (playerPos == x
						|| (currentPos == listSize - 1 && playerPos == 1)) {
					sticheE2.setText(""+stiche);
				} else {
					int y = currentPos + 3;
					if (y >= listSize)
						y = y - listSize;
					if (playerPos == y
							|| (currentPos == listSize - 1 && playerPos == 2)) {
						sticheE3.setText(""+stiche);
					} else {
						int z = currentPos + 4;
						if (z >= listSize)
							z = z - listSize;
						if (playerPos == z
								|| (currentPos == listSize - 1 && playerPos == 3)) {
							sticheE4.setText(""+stiche);
						} else {
							sticheE5.setText(""+stiche);
						}
					}
				}
			}
			break;
		}
		add(getLBg());
		revalidate();
		validate();
		repaint();
	}
}
