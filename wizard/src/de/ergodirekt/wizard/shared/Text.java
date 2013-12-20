package de.ergodirekt.wizard.shared;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * Diese Klasse überprüft die Eingabe des Clients.
 * @author Tobias 
 *
 */
public class Text {

	public static void main(String[] args) {
		System.out.println(isLeer(""));
		System.out.println(isLeer(" "));
		System.out.println(isLeer("Mit Text"));
		System.out.println(isLeer(null));
		try {
			isValidPassword("Abcdefghi1!");
		} catch (ValidationException e) {
			e.printStackTrace();
		}
	}

	public static boolean isLeer(String s) {
		boolean ok = false;
		if (s == null || s.trim().equals("")) {
			ok = true;
		}

		return ok;
	}

	/**
	 * Counts the occurences of the given character in the given string.
	 * 
	 * @param string
	 *            the string in which the character should be counted
	 * @param buchstabe
	 *            the character to count
	 * @return the number of characters in the string
	 * @author Manuel B�hm
	 */
	public static int buchstabenzaehler(String string, char buchstabe) {
		int counter = 0;
		for (int i = 0; i < string.length(); i++) {
			if (string.charAt(i) == buchstabe)
				counter++;
		}
		return counter;
	}

	/**
	 * Checks if a string is NOT a valid EMail-Adress, which means it must
	 * contain exactly 1 [@] and exactly 1 [.] after the [@].
	 * 
	 * @param text
	 *            the string to check
	 * @return true, if the string is NO valid email.
	 */
	public static boolean isNoEmail(String text) {
		String reg = "[\\w\\.-]+[@][\\w\\.-]+[.][a-zA-Z]{2,4}";
		Pattern p = Pattern.compile(reg);
		Matcher m = p.matcher(text);
		return !m.matches();
	}

	/**
	 * Checks if a String equals NOT another.
	 * 
	 * @param t1
	 *            the first String
	 * @param t2
	 *            the second String
	 * @return true, if strings are NOT equal
	 * @author Manuel B�hm
	 */
	public static boolean textEqualsNot(String t1, String t2) {
		boolean ok = true;
		if (t1.equals(t2)) {
			ok = false;
		}
		return ok;
	}

	/**
	 * Checks if the text is a valid password.
	 * 
	 * @param text
	 *            the text to check
	 * @throws ValidationException
	 *             , if the text is no valid password.
	 * @author Manuel B�hm
	 */
	public static void isValidPassword(String text) throws ValidationException {
		String errorMessage = "<html>Passwort muss:";
		boolean throwIt = false;
		if (text.length() < 8) {
			throwIt = true;
			errorMessage += "<blockquote>-mindestens 8 Zeichen enthalten</blockquote>";
		}
		if (!Text.containsUpperCaseLetter(text)) {
			throwIt = true;
			errorMessage += "<blockquote>-mindestens einen Gro�buchstaben enthalten</blockquote>";
		}
		if (!Text.containsLowerCaseLetter(text)) {
			throwIt = true;
			errorMessage += "<blockquote>-mindestens einen Kleinbuchstaben enthalten</blockquote>";
		}
		if (!Text.containsNumber(text)) {
			throwIt = true;
			errorMessage += "<blockquote>-mindestens eine Ziffer enthalten</blockquote>";
		}
		if (!Text.containsSpecialCharacter(text)) {
			throwIt = true;
			errorMessage += "<blockquote>-mindestens ein Sonderzeichen enthalten</blockquote>";
		}
		if(throwIt) {
			throw new ValidationException(errorMessage + "</html>");
		}
	}

	/**
	 * Checks if a string contains 1 or more numbers.
	 * 
	 * @param text
	 *            the string to check.
	 * @return true, if string contains a number.
	 * @author Manuel B�hm
	 */
	private static boolean containsNumber(String text) {
		for (int i = 0; i < text.length(); i++) {
			if (Character.isDigit(text.charAt(i))) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Checks if a string contains 1 or more lower-case-letters.
	 * 
	 * @param text
	 *            the string to check
	 * @return true, if the string contains a lower-case-letter.
	 * @author Manuel B�hm
	 */
	private static boolean containsLowerCaseLetter(String text) {
		for (int i = 0; i < text.length(); i++) {
			if (Character.isLowerCase(text.charAt(i))) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Checks if a string contains 1 or more upper-case-letters.
	 * 
	 * @param text
	 *            the string to check
	 * @return true, if the string contains an upper-case-letter.
	 * @author Manuel B�hm
	 */
	private static boolean containsUpperCaseLetter(String text) {
		for (int i = 0; i < text.length(); i++) {
			if (Character.isUpperCase(text.charAt(i))) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Checks if a string contains 1 or more special characters. A special
	 * character is a non-word character, a character which is not [a-z], [A-Z]
	 * or [0-9].
	 * 
	 * @param text
	 *            the string to check
	 * @return true, if the string contains a special character.
	 * @author Manuel B�hm
	 */
	private static boolean containsSpecialCharacter(String text) {
		String reg = "[\\w]*[\\W]+[\\w]*";
		Pattern p = Pattern.compile(reg);
		Matcher m = p.matcher(text);
		return m.matches();
	}
}
