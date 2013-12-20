package de.ergodirekt.wizard.gui.game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.text.MaskFormatter;

import de.ergodirekt.wizard.logic.Karte;
import de.ergodirekt.wizard.logic.Spieler;
import de.ergodirekt.wizard.server.Kommando;
import de.ergodirekt.wizard.server.LobbyClient;
import de.ergodirekt.wizard.shared.User;
import de.ergodirekt.wizard.shared.WizardLogger;

/**
 * Diese Klasse initialisert die GUI, indem das Game-Panel liegt.
 * 
 * @author Tobias
 * 
 */
public class WizardGameGUI {

	private JFrame frame;
	private GamePanel gamePanel;
	private JPanel blockPanel;
	private JPanel chatPanel;
	private JScrollPane tablePanel;
	private JPanel stichPanel;
	private JTextArea chatArea;
	private JTextField tfChat;
	private JButton bSenden;
	private JFormattedTextField tfAnsage;
	private JLabel lAngesagte;
	private JTextField tfAngesagt;
	private JButton bAnsage;
	private MaskFormatter mf2;
	private JTable table;
	private LobbyClient client;
	private User user;
	private List<User> userList;
	private int numberOfUsers;
	private Object[] titels;
	private Object[][] inhalt;

	private int tablePanelWidth = 30;
	private int tablePanelHeight = 20;

	/**
	 * Create the application.
	 */
	public WizardGameGUI(User user, int users, List<User> userList,
			LobbyClient client) {
		this.user = user;
		this.numberOfUsers = users;
		this.userList = userList;
		this.client = client;
		initialize();
		getFrame().pack();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setFrame(new JFrame());
		getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getFrame().getContentPane().setLayout(new BorderLayout(0, 0));
		getFrame().getContentPane().add(getGamePanel(), BorderLayout.CENTER);
		getFrame().getContentPane().add(getBlockPanel(), BorderLayout.EAST);
		getFrame().getContentPane().add(getChatPanel(), BorderLayout.SOUTH);
		getFrame().getRootPane().setDefaultButton(bSenden);
		getFrame().setTitle("Wizard - " + user.getName());
		getFrame().setResizable(false);
		getFrame().revalidate();
		getFrame().validate();
		getFrame().repaint();
		Kommando kdo = new Kommando();
		kdo.setKommando("init");
		client.sende(kdo);
	}

	public GamePanel getGamePanel() {
		if (gamePanel == null) {
			gamePanel = new GamePanel(numberOfUsers, this);
		}
		return gamePanel;
	}

	private JPanel getBlockPanel() {
		if (blockPanel == null) {
			blockPanel = new JPanel();
			blockPanel.setLayout(new BorderLayout(0, 0));
			blockPanel.add(getTablePanel(), BorderLayout.CENTER);
			blockPanel.add(getStichPanel(), BorderLayout.SOUTH);
		}
		return blockPanel;
	}

	private JPanel getChatPanel() {
		if (chatPanel == null) {
			chatPanel = new JPanel();
			chatPanel.setBorder(new TitledBorder(UIManager
					.getBorder("TitledBorder.border"), "Chat",
					TitledBorder.LEADING, TitledBorder.TOP, null,
					Color.DARK_GRAY));
			chatPanel.setLayout(new BorderLayout());
			JScrollPane scrolli = new JScrollPane(getChatArea());
			scrolli.setPreferredSize(new Dimension(600, 100));
			scrolli.setAutoscrolls(true);
			chatPanel.add(scrolli, BorderLayout.CENTER);
			chatPanel.add(getTfChat(), BorderLayout.SOUTH);
			chatPanel.add(getBSenden(), BorderLayout.EAST);
		}
		return chatPanel;
	}

	private JScrollPane getTablePanel() {
		if (tablePanel == null) {
			tablePanel = new JScrollPane(getTable());
			tablePanel
					.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
			tablePanel
					.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			tablePanel.setBorder(new TitledBorder(null, "Block der Wahrheit",
					TitledBorder.LEADING, TitledBorder.TOP, null, null));
			tablePanel.setPreferredSize(new Dimension(tablePanelWidth,
					tablePanelHeight));
		}
		return tablePanel;
	}

	private JPanel getStichPanel() {
		if (stichPanel == null) {
			stichPanel = new JPanel();
			stichPanel.setBorder(new TitledBorder(null, "Stich-Ansage:",
					TitledBorder.LEADING, TitledBorder.TOP, null, null));
			GridBagLayout gbl_stichPanel = new GridBagLayout();
			gbl_stichPanel.columnWidths = new int[] { 0, 0, 0 };
			gbl_stichPanel.rowHeights = new int[] { 0, 0, 0, 0 };
			gbl_stichPanel.columnWeights = new double[] { 1.0, 1.0,
					Double.MIN_VALUE };
			gbl_stichPanel.rowWeights = new double[] { 0.0, 0.0, 0.0,
					Double.MIN_VALUE };
			stichPanel.setLayout(gbl_stichPanel);
			GridBagConstraints gbc_tfAnsage = new GridBagConstraints();
			gbc_tfAnsage.gridwidth = 2;
			gbc_tfAnsage.insets = new Insets(5, 5, 5, 5);
			gbc_tfAnsage.fill = GridBagConstraints.HORIZONTAL;
			gbc_tfAnsage.gridx = 0;
			gbc_tfAnsage.gridy = 0;
			stichPanel.add(getTfAnsage(), gbc_tfAnsage);
			GridBagConstraints gbc_bAnsage = new GridBagConstraints();
			gbc_bAnsage.fill = GridBagConstraints.HORIZONTAL;
			gbc_bAnsage.gridwidth = 2;
			gbc_bAnsage.insets = new Insets(5, 5, 5, 5);
			gbc_bAnsage.gridx = 0;
			gbc_bAnsage.gridy = 1;
			stichPanel.add(getBAnsage(), gbc_bAnsage);
			GridBagConstraints gbc_lAngesagte = new GridBagConstraints();
			gbc_lAngesagte.anchor = GridBagConstraints.WEST;
			gbc_lAngesagte.insets = new Insets(5, 5, 5, 5);
			gbc_lAngesagte.gridx = 0;
			gbc_lAngesagte.gridy = 2;
			stichPanel.add(getLAngesagte(), gbc_lAngesagte);
			GridBagConstraints gbc_tfAngesagt = new GridBagConstraints();
			gbc_tfAngesagt.insets = new Insets(5, 5, 5, 5);
			gbc_tfAngesagt.fill = GridBagConstraints.HORIZONTAL;
			gbc_tfAngesagt.gridx = 1;
			gbc_tfAngesagt.gridy = 2;
			stichPanel.add(getTfAngesagt(), gbc_tfAngesagt);
		}
		return stichPanel;
	}

	private JTextArea getChatArea() {
		if (chatArea == null) {
			chatArea = new JTextArea();
			chatArea.setEditable(false);
			chatArea.setFont(new Font("Tahoma", Font.PLAIN, 11));
			chatArea.setAutoscrolls(true);
		}
		return chatArea;
	}

	private JTextField getTfChat() {
		if (tfChat == null) {
			tfChat = new JTextField();
			tfChat.setColumns(10);
		}
		return tfChat;
	}

	private JButton getBSenden() {
		if (bSenden == null) {
			bSenden = new JButton("Senden");
			bSenden.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					send();
				}
			});
		}
		return bSenden;
	}

	private JTextField getTfAnsage() {
		if (tfAnsage == null) {
			tfAnsage = new JFormattedTextField(getMf2());
			tfAnsage.setHorizontalAlignment(SwingConstants.CENTER);
			tfAnsage.setColumns(2);
		}
		return tfAnsage;
	}

	private JLabel getLAngesagte() {
		if (lAngesagte == null) {
			lAngesagte = new JLabel("angesagte Stiche:");
		}
		return lAngesagte;
	}

	private JTextField getTfAngesagt() {
		if (tfAngesagt == null) {
			tfAngesagt = new JTextField();
			tfAngesagt.setHorizontalAlignment(SwingConstants.CENTER);
			tfAngesagt.setEditable(false);
			tfAngesagt.setColumns(2);
		}
		return tfAngesagt;
	}

	private JButton getBAnsage() {
		if (getbAnsage() == null) {
			setbAnsage(new JButton("Ansage"));
			getbAnsage().setEnabled(false);
			getbAnsage().addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// TODO
					try {
						String text = tfAnsage.getText();
						System.out.println(text);
						text = text.replaceAll("\\s+", "");
						stichansage(Integer.parseInt(text));
						tfAnsage.setText("  ");
					} catch (Exception e1) {
						// do nothing
					}
				}
			});
		}
		return getbAnsage();
	}

	protected void stichansage(int stiche) {
		Kommando kdo = new Kommando();
		kdo.setKommando("stichAnsage");
		kdo.addParameter(user.getName());
		kdo.addParameter(stiche);
		client.sende(kdo);
		gamePanel.isDran(false);
		tfAngesagt.setText("" + stiche);
		gamePanel.setSticheAngesagt(true);
	}

	private MaskFormatter getMf2() {
		if (mf2 == null) {
			try {
				mf2 = new MaskFormatter("**");
				mf2.setValidCharacters(" 0123456789");
			} catch (Exception e) {
				WizardLogger.error("Error creating Numberfield-Formatter", e);
			}
		}
		return mf2;
	}

	private JTable getTable() {
		if (table == null) {
			// TODO Tabelle anständig konfigurieren
			table = initTable();
		}
		return table;
	}

	private JTable initTable() {
		JTable table = null;
		if (numberOfUsers == 3) {
			titels = new Object[4];
			titels[0] = "";
			inhalt = new Object[20][4];
		}
		if (numberOfUsers == 4) {
			titels = new Object[5];
			titels[0] = "";
			inhalt = new Object[15][5];
		}
		if (numberOfUsers == 5) {
			titels = new Object[6];
			titels[0] = "";
			inhalt = new Object[12][6];
		}
		if (numberOfUsers == 6) {
			titels = new Object[7];
			titels[0] = "";
			inhalt = new Object[10][7];
		}
		for (int i = 0; i < inhalt.length; i++) {
			inhalt[i][0] = i + 1;
		}
		for (int i = 0; i < userList.size(); i++) {
			titels[i + 1] = userList.get(i).getName();
		}

		TableModel model = new DefaultTableModel(inhalt, titels) {
			public boolean isCellEditable(int row, int col) {
				return false;
			}
		};
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
		table = new JTable(model);
		for (int i = 0; i < table.getColumnCount(); i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(rightRenderer);
		}
		for (int i = 1; i <= numberOfUsers; i++) {
			setColumnWidth(table, i, 75);
		}
		setColumnWidth(table, 0, 30);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		return table;
	}

	private void setColumnWidth(JTable table, int columnIndex, int width) {
		TableColumn column = null;
		tablePanelWidth += width;
		column = table.getColumnModel().getColumn(columnIndex);
		column.setMinWidth(width);
		column.setMaxWidth(width);
		column.setPreferredWidth(width);
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
	 * Die Spielernamen werden in die Spalten der Tabelle eingetragen.
	 * 
	 * @param list
	 */
	public void setUserList(List<User> list) {
		this.userList = list;
		int i = 1;
		for (User user : userList) {
			titels[i] = user.getName();
			i++;
		}
		TableModel model = new DefaultTableModel(inhalt, titels) {
			public boolean isCellEditable(int row, int col) {
				return false;
			}
		};
		table.setModel(model);
		for (int j = 1; j <= numberOfUsers; j++) {
			setColumnWidth(table, j, 75);
		}
		setColumnWidth(table, 0, 30);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.updateUI();
	}

	/**
	 * Ansagen der Spieler werden in die Tabelle eingetragen.
	 * 
	 * @param spieler
	 * @param runde
	 */
	public void setzeSpieleransage(Spieler spieler, int runde) {
		TableColumn column = table.getColumn(spieler.getName());
		TableModel model = table.getModel();
		model.setValueAt("" + " | " + spieler.getAnsage(), runde - 1,
				column.getModelIndex());
		table.setModel(model);
	}

	/**
	 * Kommando "spieleKarte" wird an den Server gesendet.
	 * 
	 * @param karte
	 */
	public void spieleKarte(Karte karte) {
		Kommando kdo = new Kommando();
		kdo.setKommando("spieleKarte");
		kdo.addParameter(karte);
		kdo.addParameter(user.getName());
		client.sende(kdo);
	}

	/**
	 * Spieler legt die jeweilige Karte
	 * 
	 * @param karte
	 * @param playerPos
	 * @param currentPos
	 */
	public void legeKarteVon(Karte karte, Integer playerPos, Integer currentPos) {
		gamePanel.legeKarteVon(karte, playerPos, currentPos, userList.size());
	}

	/**
	 * Punkte der Spieler werden in die Tabelle geschrieben.
	 * 
	 * @param list
	 * @param runde
	 */
	public void punkteSchreiben(List<Spieler> list, int runde) {
		for (Spieler spieler : list) {
			TableColumn column = table.getColumn(spieler.getName());
			TableModel model = table.getModel();
			model.setValueAt(
					""
							+ spieler.getPunkte()
							+ model.getValueAt(runde - 1,
									column.getModelIndex()), runde - 1,
					column.getModelIndex());
			table.setModel(model);
		}
	}

	public void trumpfWaehlen(String selectedItem) {
		Kommando kdo = new Kommando();
		kdo.setKommando("waehleTrumpf");
		kdo.addParameter(selectedItem);
		kdo.addParameter(user.getName());
		client.sende(kdo);
	}

	public void setzeNamen() {
		int index = 0;
		for (int i = 0; i < userList.size(); i++) {
			if (userList.get(i).getName().equals(user.getName())) {
				index = i;
				break;
			}
		}
		System.out.println(index);
		System.out.println("Hi!");
		for (int i = 1; i < userList.size(); i++) {
			int index2 = i + index;
			if (index2 >= userList.size()) {
				index2 = index2 - userList.size();
			}
			gamePanel.setzeNamen(userList.get(index2).getName(), index2, index,
					userList.size());
		}
	}
	
	public void sticheSchreiben(List<Spieler> list) {
		int index = 0;
		for (int i = 0; i < userList.size(); i++) {
			if (userList.get(i).getName().equals(user.getName())) {
				index = i;
				break;
			}
		}
		System.out.println(index);
		System.out.println("Hi!");
		for (int i = 1; i < userList.size(); i++) {
			int index2 = i + index;
			if (index2 >= userList.size()) {
				index2 = index2 - userList.size();
			}
			gamePanel.setzeStiche(list.get(index2).getStiche(), index2, index,
					userList.size());
		}
	}

	public JButton getbAnsage() {
		return bAnsage;
	}

	public void setbAnsage(JButton bAnsage) {
		this.bAnsage = bAnsage;
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
}
