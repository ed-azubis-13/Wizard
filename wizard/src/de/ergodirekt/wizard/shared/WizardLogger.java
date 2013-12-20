package de.ergodirekt.wizard.shared;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * Diese Klasse implementiert Methoden welche dann auf der Konsole ausgegeben werden.
 * @author Tobias
 *
 */
public class WizardLogger {

	private static final boolean DO_LOG_INFO = true;
	private static final boolean DO_LOG_DEBUG = true;
	private static final boolean DO_LOG_ERROR = true;
	
	public static String info(String message) {
		String timeString = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss")
				.format(new Date());
		String output = timeString + ": <INFO> - " + message;
		if (DO_LOG_INFO) {
			System.out.println(output);
		}
		return output;
	}

	public static String debug(String message) {
		String timeString = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss")
				.format(new Date());
		String output = timeString + ": <DEBUG> - " + message;
		if (DO_LOG_DEBUG) {
			System.out.println(output);
		}
		return output;
	}

	public static String error(String message) {
		String timeString = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss")
				.format(new Date());
		String output = timeString + ": <ERROR> - " + message;
		if (DO_LOG_ERROR) {
			System.err.println(output);
		}
		return output;
	}

	public static String error(String message, Exception e) {
		String timeString = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss")
				.format(new Date());
		String output = timeString + ": <ERROR> - " + message + "\n";
		output += timeString + ": <ERROR> - caused by Exception: "
				+ e.toString() + "\n";
		StackTraceElement[] stackTrace = e.getStackTrace();
		for (int i = 0; i < stackTrace.length; i++) {
			if (stackTrace[i].getClassName().contains(
					"de.ergodirekt.wizard")) {
				output += timeString + ": <ERROR> - in Class: "
						+ stackTrace[i].getClassName() + "\n";
				output += timeString
						+ ": <ERROR> - \tLine-Number: "
						+ stackTrace[i].getLineNumber() + "\n";
			}
		}
		if (DO_LOG_ERROR) {
			System.err.println(output);
		}
		return output;
	}
}
