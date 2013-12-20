package de.ergodirekt.wizard.gui.main;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import de.ergodirekt.wizard.server.Kommando;
import de.ergodirekt.wizard.server.LobbyClient;
import de.ergodirekt.wizard.shared.User;
import de.ergodirekt.wizard.shared.WizardLogger;
import eu.hansolo.steelseries.extras.Led;

/**
 * Diese Klasse initialisert das Panel welches erscheint wenn Spieler der Lobby
 * beitreten.
 * 
 * @author Tobias
 * 
 */
public class HostPanel extends JPanel {
	protected JTextField tfChat;
	protected JButton bBereit;
	private JButton bSenden;
	protected JLabel lIp;
	protected JLabel lEingeloggter;
	protected JTextArea chatArea;
	protected Led lReadyIndicator5;
	protected Led lReadyIndicator4;
	protected Led lReadyIndicator3;
	protected Led lReadyIndicator2;
	protected Led lReadyIndicator1;
	protected JTextField tfSpieler5;
	protected JTextField tfSpieler4;
	protected JTextField tfSpieler3;
	protected JTextField tfSpieler2;
	protected JTextField tfSpieler1;
	protected JLabel lblChat;
	protected List<JTextField> tfList = new ArrayList<JTextField>();
	protected List<Led> readyList = new ArrayList<Led>();
	private WizardGUI parentComponent;

	protected User user;
	protected List<User> userList;
	private LobbyClient client;
	private Led lReadyIndicator6;
	private JTextField tfSpieler6;
	private JButton bStarten;

	/**
	 * Create the panel.
	 */

	// Konstruktoren
	public HostPanel(User user, WizardGUI parent) {
		this.setParentComponent(parent);
		setUser(user);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { -33, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 0.0, 0.0, 0.0,
				1.0, 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0, 0.0, 0.0, 1.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);
		GridBagConstraints gbc_lIp = new GridBagConstraints();
		gbc_lIp.gridwidth = 4;
		gbc_lIp.insets = new Insets(0, 0, 5, 5);
		gbc_lIp.gridx = 3;
		gbc_lIp.gridy = 1;
		add(getLIp(), gbc_lIp);
		GridBagConstraints gbc_lEingeloggter = new GridBagConstraints();
		gbc_lEingeloggter.insets = new Insets(0, 0, 5, 5);
		gbc_lEingeloggter.gridx = 1;
		gbc_lEingeloggter.gridy = 1;
		add(getLEingeloggter(user.getName()), gbc_lEingeloggter);
		GridBagConstraints gbc_lblChat = new GridBagConstraints();
		gbc_lblChat.insets = new Insets(0, 0, 5, 5);
		gbc_lblChat.gridx = 3;
		gbc_lblChat.gridy = 2;
		add(getLblChat(), gbc_lblChat);
		GridBagConstraints gbc_chatArea = new GridBagConstraints();
		gbc_chatArea.gridwidth = 4;
		gbc_chatArea.gridheight = 6;
		gbc_chatArea.insets = new Insets(0, 0, 5, 5);
		gbc_chatArea.fill = GridBagConstraints.BOTH;
		gbc_chatArea.gridx = 3;
		gbc_chatArea.gridy = 3;
		JScrollPane scrolli = new JScrollPane(getChatArea());
		scrolli.setAutoscrolls(true);
		add(scrolli, gbc_chatArea);
		GridBagConstraints gbc_lReadyIndicator1 = new GridBagConstraints();
		gbc_lReadyIndicator1.anchor = GridBagConstraints.WEST;
		gbc_lReadyIndicator1.insets = new Insets(0, 0, 5, 5);
		gbc_lReadyIndicator1.gridx = 0;
		gbc_lReadyIndicator1.gridy = 4;
		add(getLReadyIndicator1(), gbc_lReadyIndicator1);
		GridBagConstraints gbc_tfSpieler1 = new GridBagConstraints();
		gbc_tfSpieler1.insets = new Insets(0, 0, 5, 5);
		gbc_tfSpieler1.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfSpieler1.gridx = 1;
		gbc_tfSpieler1.gridy = 4;
		add(getTfSpieler1(), gbc_tfSpieler1);
		GridBagConstraints gbc_lReadyIndicator2 = new GridBagConstraints();
		gbc_lReadyIndicator2.anchor = GridBagConstraints.WEST;
		gbc_lReadyIndicator2.insets = new Insets(0, 0, 5, 5);
		gbc_lReadyIndicator2.gridx = 0;
		gbc_lReadyIndicator2.gridy = 5;
		add(getLReadyIndicator2(), gbc_lReadyIndicator2);
		GridBagConstraints gbc_tfSpieler2 = new GridBagConstraints();
		gbc_tfSpieler2.insets = new Insets(0, 0, 5, 5);
		gbc_tfSpieler2.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfSpieler2.gridx = 1;
		gbc_tfSpieler2.gridy = 5;
		add(getTfSpieler2(), gbc_tfSpieler2);
		GridBagConstraints gbc_lReadyIndicator3 = new GridBagConstraints();
		gbc_lReadyIndicator3.anchor = GridBagConstraints.WEST;
		gbc_lReadyIndicator3.insets = new Insets(0, 0, 5, 5);
		gbc_lReadyIndicator3.gridx = 0;
		gbc_lReadyIndicator3.gridy = 6;
		add(getLReadyIndicator3(), gbc_lReadyIndicator3);
		GridBagConstraints gbc_tfSpieler3 = new GridBagConstraints();
		gbc_tfSpieler3.insets = new Insets(0, 0, 5, 5);
		gbc_tfSpieler3.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfSpieler3.gridx = 1;
		gbc_tfSpieler3.gridy = 6;
		add(getTfSpieler3(), gbc_tfSpieler3);
		GridBagConstraints gbc_lReadyIndicator4 = new GridBagConstraints();
		gbc_lReadyIndicator4.anchor = GridBagConstraints.WEST;
		gbc_lReadyIndicator4.insets = new Insets(0, 0, 5, 5);
		gbc_lReadyIndicator4.gridx = 0;
		gbc_lReadyIndicator4.gridy = 7;
		add(getLReadyIndicator4(), gbc_lReadyIndicator4);
		GridBagConstraints gbc_tfSpieler4 = new GridBagConstraints();
		gbc_tfSpieler4.insets = new Insets(0, 0, 5, 5);
		gbc_tfSpieler4.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfSpieler4.gridx = 1;
		gbc_tfSpieler4.gridy = 7;
		add(getTfSpieler4(), gbc_tfSpieler4);
		GridBagConstraints gbc_lReadyIndicator5 = new GridBagConstraints();
		gbc_lReadyIndicator5.anchor = GridBagConstraints.WEST;
		gbc_lReadyIndicator5.insets = new Insets(0, 0, 5, 5);
		gbc_lReadyIndicator5.gridx = 0;
		gbc_lReadyIndicator5.gridy = 8;
		add(getLReadyIndicator5(), gbc_lReadyIndicator5);
		GridBagConstraints gbc_tfSpieler5 = new GridBagConstraints();
		gbc_tfSpieler5.insets = new Insets(0, 0, 5, 5);
		gbc_tfSpieler5.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfSpieler5.gridx = 1;
		gbc_tfSpieler5.gridy = 8;
		add(getTfSpieler5(), gbc_tfSpieler5);
		GridBagConstraints gbc_lReadyIndicator6 = new GridBagConstraints();
		gbc_lReadyIndicator6.insets = new Insets(0, 0, 5, 5);
		gbc_lReadyIndicator6.gridx = 0;
		gbc_lReadyIndicator6.gridy = 9;
		add(getLReadyIndicator6(), gbc_lReadyIndicator6);
		GridBagConstraints gbc_tfSpieler6 = new GridBagConstraints();
		gbc_tfSpieler6.insets = new Insets(0, 0, 5, 5);
		gbc_tfSpieler6.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfSpieler6.gridx = 1;
		gbc_tfSpieler6.gridy = 9;
		add(getTfSpieler6(), gbc_tfSpieler6);
		GridBagConstraints gbc_tfChat = new GridBagConstraints();
		gbc_tfChat.gridwidth = 3;
		gbc_tfChat.insets = new Insets(0, 0, 5, 5);
		gbc_tfChat.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfChat.gridx = 3;
		gbc_tfChat.gridy = 9;
		add(getTfChat(), gbc_tfChat);
		GridBagConstraints gbc_bSenden = new GridBagConstraints();
		gbc_bSenden.insets = new Insets(0, 0, 5, 5);
		gbc_bSenden.gridx = 6;
		gbc_bSenden.gridy = 9;
		add(getBSenden(), gbc_bSenden);
		GridBagConstraints gbc_bBereit = new GridBagConstraints();
		gbc_bBereit.insets = new Insets(0, 0, 5, 5);
		gbc_bBereit.fill = GridBagConstraints.BOTH;
		gbc_bBereit.gridx = 1;
		gbc_bBereit.gridy = 10;
		add(getBBereit(), gbc_bBereit);
		tfList.add(tfSpieler1);
		tfList.add(tfSpieler2);
		tfList.add(tfSpieler3);
		tfList.add(tfSpieler4);
		tfList.add(tfSpieler5);
		tfList.add(tfSpieler6);
		readyList.add(lReadyIndicator1);
		readyList.add(lReadyIndicator2);
		readyList.add(lReadyIndicator3);
		readyList.add(lReadyIndicator4);
		readyList.add(lReadyIndicator5);
		readyList.add(lReadyIndicator6);
		lReadyIndicator1.setPreferredSize(new Dimension(25, 25));
		lReadyIndicator2.setPreferredSize(new Dimension(25, 25));
		lReadyIndicator3.setPreferredSize(new Dimension(25, 25));
		lReadyIndicator4.setPreferredSize(new Dimension(25, 25));
		lReadyIndicator5.setPreferredSize(new Dimension(25, 25));
		lReadyIndicator6.setPreferredSize(new Dimension(25, 25));
		GridBagConstraints gbc_bStarten = new GridBagConstraints();
		gbc_bStarten.fill = GridBagConstraints.HORIZONTAL;
		gbc_bStarten.gridwidth = 4;
		gbc_bStarten.insets = new Insets(0, 0, 5, 5);
		gbc_bStarten.gridx = 3;
		gbc_bStarten.gridy = 10;
		add(getBStarten(), gbc_bStarten);
		repaint();
	}

	// Komponenten-Getter
	/**
	 * generated
	 * 
	 * @return
	 */
	private JTextField getTfChat() {
		if (tfChat == null) {
			tfChat = new JTextField();
			tfChat.setColumns(10);
		}
		return tfChat;
	}

	/**
	 * generated
	 * 
	 * @return
	 */
	protected JButton getBBereit() {
		if (bBereit == null) {
			bBereit = new JButton("Bereit");
			bBereit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					beReady();
				}
			});
		}
		return bBereit;
	}

	/**
	 * generated
	 * 
	 * @return
	 */
	private JButton getBSenden() {
		if (getbSenden() == null) {
			setbSenden(new JButton("senden"));
			getbSenden().addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					send();
				}
			});
		}
		return getbSenden();
	}

	/**
	 * generated
	 * 
	 * @return
	 */
	private JLabel getLIp() {
		if (lIp == null) {
			try {
				lIp = new JLabel(Inet4Address.getLocalHost().getHostAddress());
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
		}
		return lIp;
	}

	/**
	 * generated
	 * 
	 * @return
	 */
	private JLabel getLEingeloggter(String userName) {
		if (lEingeloggter == null) {
			lEingeloggter = new JLabel("Eingeloggt als " + userName);
		}
		return lEingeloggter;
	}

	/**
	 * generated
	 * 
	 * @return
	 */
	private JTextArea getChatArea() {
		if (chatArea == null) {
			chatArea = new JTextArea();
			chatArea.setLineWrap(true);
			chatArea.setEditable(false);
		}
		return chatArea;
	}

	/**
	 * generated
	 * 
	 * @return
	 */
	private Led getLReadyIndicator5() {
		if (lReadyIndicator5 == null) {
			lReadyIndicator5 = new Led();
		}
		return lReadyIndicator5;
	}

	/**
	 * generated
	 * 
	 * @return
	 */
	private Led getLReadyIndicator4() {
		if (lReadyIndicator4 == null) {
			lReadyIndicator4 = new Led();
		}
		return lReadyIndicator4;
	}

	/**
	 * generated
	 * 
	 * @return
	 */
	private Led getLReadyIndicator3() {
		if (lReadyIndicator3 == null) {
			lReadyIndicator3 = new Led();
		}
		return lReadyIndicator3;
	}

	/**
	 * generated
	 * 
	 * @return
	 */
	private Led getLReadyIndicator2() {
		if (lReadyIndicator2 == null) {
			lReadyIndicator2 = new Led();
		}
		return lReadyIndicator2;
	}

	/**
	 * generated
	 * 
	 * @return
	 */
	private Led getLReadyIndicator1() {
		if (lReadyIndicator1 == null) {
			lReadyIndicator1 = new Led();
		}
		return lReadyIndicator1;
	}

	/**
	 * generated
	 * 
	 * @return
	 */
	private JTextField getTfSpieler5() {
		if (tfSpieler5 == null) {
			tfSpieler5 = new JTextField();
			tfSpieler5.setText("<frei>");
			tfSpieler5.setEditable(false);
			tfSpieler5.setColumns(10);
		}
		return tfSpieler5;
	}

	/**
	 * generated
	 * 
	 * @return
	 */
	private JTextField getTfSpieler4() {
		if (tfSpieler4 == null) {
			tfSpieler4 = new JTextField();
			tfSpieler4.setText("<frei>");
			tfSpieler4.setEditable(false);
			tfSpieler4.setColumns(10);
		}
		return tfSpieler4;
	}

	/**
	 * generated
	 * 
	 * @return
	 */
	private JTextField getTfSpieler3() {
		if (tfSpieler3 == null) {
			tfSpieler3 = new JTextField();
			tfSpieler3.setText("<frei>");
			tfSpieler3.setEditable(false);
			tfSpieler3.setColumns(10);
		}
		return tfSpieler3;
	}

	/**
	 * generated
	 * 
	 * @return
	 */
	private JTextField getTfSpieler2() {
		if (tfSpieler2 == null) {
			tfSpieler2 = new JTextField();
			tfSpieler2.setText("<frei>");
			tfSpieler2.setEditable(false);
			tfSpieler2.setColumns(10);
		}
		return tfSpieler2;
	}

	/**
	 * generated
	 * 
	 * @return
	 */
	private JTextField getTfSpieler1() {
		if (tfSpieler1 == null) {
			tfSpieler1 = new JTextField();
			tfSpieler1.setText("<frei>");
			tfSpieler1.setEditable(false);
			tfSpieler1.setColumns(10);
		}
		return tfSpieler1;
	}

	/**
	 * generated
	 * 
	 * @return
	 */
	private JLabel getLblChat() {
		if (lblChat == null) {
			lblChat = new JLabel("Chat");
		}
		return lblChat;
	}

	/**
	 * generated
	 * 
	 * @return
	 */
	private Led getLReadyIndicator6() {
		if (lReadyIndicator6 == null) {
			lReadyIndicator6 = new Led();
		}
		return lReadyIndicator6;
	}

	/**
	 * generated
	 * 
	 * @return
	 */
	private JTextField getTfSpieler6() {
		if (tfSpieler6 == null) {
			tfSpieler6 = new JTextField();
			tfSpieler6.setText("<frei>");
			tfSpieler6.setEditable(false);
			tfSpieler6.setColumns(10);
		}
		return tfSpieler6;
	}

	/**
	 * generated
	 * 
	 * @return
	 */
	public void setUserList(List<User> userList) {
		this.userList = userList;
		int index = 0;
		for (User user : userList) {
			tfList.get(index).setText(user.getName());
			if (user.isReady()) {
				readyList.get(index).setLedOn(true);
			} else {
				readyList.get(index).setLedOn(false);
			}
			index++;
		}
	}

	// Getter und Setter
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setClient(LobbyClient client) {
		this.client = client;
	}

	// Komponenten-Getter mit Action-Listener
	private JButton getBStarten() {
		if (bStarten == null) {
			bStarten = new JButton("Start");
			bStarten.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					boolean allReady = true;
					for (User u : userList) {
						if (!u.isReady()) {
							allReady = false;
						}
					}
					if (userList.size() >= 3 && allReady) {
						/**
						 * TODO: - Spiel-Logik muss auf dem Server gestartet
						 * werden und das Spiel beginnen
						 */
						Kommando kdo = new Kommando();
						kdo.setKommando("startGame");
						client.sende(kdo);
					} else {
						chatArea.append("Es sind weniger als 3 Spieler im Spiel oder nicht alle bereit!\n");
					}
				}
			});
		}
		return bStarten;
	}

	// Methoden
	/**
	 * Schreibt jemand eine Nachricht im Chat so wird sie bei allen Spieler im
	 * Chat-Fenster angezeigt.
	 * 
	 * @param message
	 * @param name
	 */
	public void writeChatMessage(String message, String name) {
		WizardLogger.info("Chat-Message " + message + " appended to chat-area");
		chatArea.append("<" + name + ">: " + message + "\n");
	}

	/**
	 * Schreibt jemand eine Nachricht im Chat so wird sie bei allen Spieler im
	 * Chat-Fenster und auch im Konsolenfenster des Servers angezeigt.
	 * 
	 * @param message
	 */
	public void writeServerMessage(String message) {
		WizardLogger.info("Server-Message " + message
				+ " appended to chat-area");
		chatArea.append("<SERVER-LOG>: " + message + "\n");
	}

	protected void addUser(User user) {
		Kommando kdo = new Kommando();
		kdo.setKommando("neuerUser");
		kdo.addParameter(user);
		client.sende(kdo);
	}

	protected void beReady() {
		Kommando kdo = new Kommando();
		kdo.setKommando("seiReady");
		kdo.addParameter(user);
		client.sende(kdo);
	}

	protected void send() {
		String message = tfChat.getText();
		Kommando kdo = new Kommando();
		kdo.setKommando("text");
		kdo.addParameter(message);
		kdo.addParameter(user.getName());
		WizardLogger.info("User " + user.getName() + " sending message "
				+ message + " to Server.");
		client.sende(kdo);
		tfChat.setText("");
		tfChat.requestFocus();
	}

	public JButton getbSenden() {
		return bSenden;
	}

	public void setbSenden(JButton bSenden) {
		this.bSenden = bSenden;
	}

	public WizardGUI getParentComponent() {
		return parentComponent;
	}

	public void setParentComponent(WizardGUI parentComponent) {
		this.parentComponent = parentComponent;
	}
}
