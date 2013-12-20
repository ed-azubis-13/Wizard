package de.ergodirekt.wizard.gui.main;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import de.ergodirekt.wizard.shared.Text;
import de.ergodirekt.wizard.shared.WizardLogger;
import java.awt.Font;
/**
 * Diese Klasse initialisiert das Startfenster welches sich öffnet, wenn man einer Lobby beitreten möchte.
 * @author LADMIN
 *
 */
public class LobbyBeitretenPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5519578604109143763L;

	private final JPanel contentPanel = new JPanel();
	private JTextField nameField;
	private JTextField ipField;

	private MaskFormatter mf2;

	public JButton okButton;

	/**
	 * Create the dialog.
	 */

	// Konstruktoren
	public LobbyBeitretenPanel(final WizardGUI gui) {
		setBounds(100, 100, 369, 179);
		setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 0, 0 };
		gbl_contentPanel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0 };
		gbl_contentPanel.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblEinemServerBeitreten = new JLabel("Einem Server beitreten");
			lblEinemServerBeitreten.setFont(new Font("Tahoma", Font.PLAIN, 14));
			GridBagConstraints gbc_lblEinemServerBeitreten = new GridBagConstraints();
			gbc_lblEinemServerBeitreten.insets = new Insets(0, 0, 5, 0);
			gbc_lblEinemServerBeitreten.gridx = 0;
			gbc_lblEinemServerBeitreten.gridy = 0;
			contentPanel.add(lblEinemServerBeitreten, gbc_lblEinemServerBeitreten);
		}
		{
			JLabel lblDeinName = new JLabel("Dein Name");
			GridBagConstraints gbc_lblDeinName = new GridBagConstraints();
			gbc_lblDeinName.insets = new Insets(0, 0, 5, 0);
			gbc_lblDeinName.gridx = 0;
			gbc_lblDeinName.gridy = 1;
			contentPanel.add(lblDeinName, gbc_lblDeinName);
		}
		{
			nameField = new JTextField();
			GridBagConstraints gbc_nameField = new GridBagConstraints();
			gbc_nameField.insets = new Insets(0, 0, 5, 0);
			gbc_nameField.fill = GridBagConstraints.HORIZONTAL;
			gbc_nameField.gridx = 0;
			gbc_nameField.gridy = 2;
			contentPanel.add(nameField, gbc_nameField);
			nameField.setColumns(10);
		}
		{
			JLabel lblLobbyipadresse = new JLabel("Server-IP-Adresse");
			GridBagConstraints gbc_lblLobbyipadresse = new GridBagConstraints();
			gbc_lblLobbyipadresse.insets = new Insets(0, 0, 5, 0);
			gbc_lblLobbyipadresse.gridx = 0;
			gbc_lblLobbyipadresse.gridy = 3;
			contentPanel.add(lblLobbyipadresse, gbc_lblLobbyipadresse);
		}
		{
			ipField = new JFormattedTextField(getMf2());
			ipField.setText("127  0  0  1");
			GridBagConstraints gbc_ipField = new GridBagConstraints();
			gbc_ipField.fill = GridBagConstraints.HORIZONTAL;
			gbc_ipField.gridx = 0;
			gbc_ipField.gridy = 4;
			contentPanel.add(ipField, gbc_ipField);
			ipField.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// Lobby nach IP suchen und dieser beitreten, sonst
						// Fehlermeldung
						findServer(gui);
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
			}
		}
	}

	// Methoden
	private MaskFormatter getMf2() {
		if (mf2 == null) {
			try {
				mf2 = new MaskFormatter("***.***.***.***");
				mf2.setValidCharacters(" 0123456789");
			} catch (Exception e) {
				WizardLogger.error("Error creating IP-Field-Formatter", e);
			}
		}
		return mf2;
	}

	protected void findServer(WizardGUI gui) {
		String ip = ipField.getText();
		ip = ip.replaceAll("\\s+", "");
		if (!Text.isLeer(nameField.getText())) {
			try {
				WizardLogger.info("Calling joinServer");
				gui.joinServer(ip, nameField.getText());
				// TODO
			} catch (IOException e) {
				new PopupDialog(gui.getFrame(), PopupDialog.IS_ERROR_MESSAGE,
						"Lobby beitreten fehlgeschlagen: " + e.getMessage());
				WizardLogger.error(e.getMessage(), e);
			}
		} else {
			new PopupDialog(gui.getFrame(), PopupDialog.IS_ERROR_MESSAGE,
					"Bitte geben Sie einen Namen ein!");
			WizardLogger.error("Empty name");
		}
	}
}
