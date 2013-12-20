package de.ergodirekt.wizard.shared;

import java.io.Serializable;
/**
 * Diese Klasse enthält die Eigenschaften eines Benutzers.
 * @author LADMIN
 *
 */
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8283193287035385140L;

	private String name;
	private boolean isReady;
	private String ip;

	public User(String name, String ip) {
		super();
		this.name = name;
		this.ip = ip;
		this.setReady(false);
	}

	public User(String name, boolean ready, String ip) {
		super();
		this.name = name;
		this.isReady = ready;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isReady() {
		return isReady;
	}

	public void setReady(boolean isReady) {
		this.isReady = isReady;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
}
