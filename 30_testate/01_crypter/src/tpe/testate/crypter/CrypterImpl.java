package tpe.testate.crypter;

import java.util.HashMap;

public class CrypterImpl implements Crypter {

	private final HashMap<Character, Character> encodeTable = new HashMap<>();

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
