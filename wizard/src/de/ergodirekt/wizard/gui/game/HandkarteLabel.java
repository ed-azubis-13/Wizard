package de.ergodirekt.wizard.gui.game;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.border.LineBorder;

import de.ergodirekt.wizard.logic.Karte;
/**
 * Diese Klasse bewegt die Karte an die vorgegebene Position wenn auf sie geklickt wurde.
 * @author Tobias
 *
 */
public class HandkarteLabel extends JLabel {

	private GamePanel panel;
	private int animationDuration = 1000;
	private boolean isDran = false;
	private Karte karte;

	/**
	 * Wird eine Karte angeklickt, so wird sie an die vorgegebene Posotion gelegt.
	 * @param panel
	 * @param karte
	 * @param startX
	 * @param startY
	 */
	public HandkarteLabel(GamePanel panel, Karte karte, int startX, int startY) {
		this.panel = panel;
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO
				if (isDran()) {
					bewege(GamePanel.PLAYER_LAYDOWN_POSITION);
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				entered();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				exited();
			}
		});
		this.setKarte(karte);
		setBorder(new LineBorder(Color.BLACK, 2));
		setIcon(karte.getBild());
		setBounds(startX, startY, GamePanel.CARD_WIDTH, GamePanel.CARD_HEIGHT);
	}

	protected void bewege(final Point toPos) {
		// final Timer timer = new Timer(animationDuration, new ActionListener()
		// {
		// @Override
		// public void actionPerformed(ActionEvent event) {
		// long animStartTime1 = System.nanoTime() / 1000000;
		// final long currentTime1 = System.nanoTime() / 1000000;
		// final long totalTime1 = currentTime1 - animStartTime1;
		// final float fraction = Math.min(1.0f, (float) totalTime1 /
		// animationDuration);
		// final int startX = getLocation().x;
		// final int startY = getLocation().y;
		// setLocation((int)(startX+fraction*(toPos.x - startX)),
		// (int)(startY+fraction*(toPos.y - startY)));
		// revalidate();
		// validate();
		// repaint();
		// }
		// });
		// timer.start();
		// timer.setRepeats(false);
		setLocation(toPos);
		panel.spieleKarte(this);
		setDran(false);
	}

	protected void exited() {
		setBorder(new LineBorder(Color.BLACK, 2));
		panel.lowlight(this);
	}

	protected void entered() {
		setBorder(new LineBorder(Color.BLUE, 2));
		panel.highlight(this);
	}

	public boolean isDran() {
		return isDran;
	}

	public void setDran(boolean isDran) {
		this.isDran = isDran;
	}

	public Karte getKarte() {
		return karte;
	}

	public void setKarte(Karte karte) {
		this.karte = karte;
	}
}
