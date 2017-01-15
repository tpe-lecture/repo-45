package tpe.testate.crypter;

import java.util.HashMap;

/**
 * Klasse die das Interface der speziellen TPE-Verschlüsselung implementiert.
 *
 *  Die Verschlüsselung funktioniert so, dass bestimmte
 *  Zeichen durch andere ersetzt werden.
 *
 *  Hierbei gilt die folgende Übersetzungstabelle:
 *
 *  <code>
 *    e -> 3
 *    3 -> e
 *    l -> 1
 *    1 -> l
 *    o -> 0
 *    0 -> o
 *    a -> 4
 *    4 -> a
 *    t -> 7
 *    7 -> t
 *  </code>
 *
 *  Aus dem Text "tpe im sose 2016 ist eine super vorlesung" würde nach
 *  dieser Regel "7p3 im s0s3 2ol6 is7 3in3 sup3r v0r13sung".
 *
 *  Bei der Verschlüsselung sind nur die Zeichen a-z, die Zahlen 0-9 und
 *  das Leerzeichen erlaubt.
 */
public class CrypterImpl implements Crypter {

	private final HashMap<Character, Character> encodeTable = new HashMap<>();
	/**
	* Erstellt eine neue Instanz der CrypterImpl Klasse mit der Standart Ver/Entschlüsselungstabelle.
	*/
	public CrypterImpl() {
		encodeTable.put('e', '3');
		encodeTable.put('3', 'e');
		encodeTable.put('l', '1');
		encodeTable.put('1', 'l');
		encodeTable.put('o', '0');
		encodeTable.put('0', 'o');
		encodeTable.put('a', '4');
		encodeTable.put('4', 'a');
		encodeTable.put('t', '7');
		encodeTable.put('7', 't');
	}
	
	/**
	* Verschlüsselt den gegebenen Text. Enthält die Eingabe Großbuchstaben,
	* so werden diese in Kleinbuchstaben umgewandelt. Ungültige Zeichen
	* werden ignoriert und nicht in den verschlüsselten Text übernommen.
	*
	* @param input Text, der verschlüsselt werden soll.
	* @return Verschlüsselter Text.
	*/
	@Override
	public String encrypt(String input) {
		input = input.toLowerCase();
		String output = "";
		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);
			if (!Character.isLetterOrDigit(c) && c != ' ') {
				continue;
			}

			if (encodeTable.containsKey(c)) {
				c = encodeTable.get(c);
			}

			output += c;
		}

		return output;
	}

	/**
	* Entschlüsselt den gegebenen Text. Enthält die Eingabe Großbuchstaben,
	* oder andere ungültige Zeichen, so wirft die Methode eine Ausnahme.
	*
	* @param input Text, der entschlüsselt werden soll.
	* @return Entschlüsselter Text.
	* @throws IllegalArgumentException Wird geworfen, wenn die Eingabe
	*      ungültige Zeichen (Großbuchstaben) enthält.
	*/
	@Override
	public String decrypt(String input) throws IllegalArgumentException {
		String output = "";
		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);
			if ((!Character.isLetterOrDigit(c) && c != ' ') || Character.isUpperCase(c)) {
				throw new IllegalArgumentException();
			}

			if (encodeTable.containsKey(c)) {
				c = encodeTable.get(c);
			}

			output += c;
		}

		return output;
	}
}
