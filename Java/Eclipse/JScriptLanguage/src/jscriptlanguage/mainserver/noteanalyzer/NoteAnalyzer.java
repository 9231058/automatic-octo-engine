package jscriptlanguage.mainserver.noteanalyzer;

import java.util.ArrayList;

public class NoteAnalyzer {

	ArrayList<String> wordStringArr;
	static ArrayList<String> searchableWords;

	public NoteAnalyzer(ArrayList<String> note) {

		this.wordStringArr = note;

		correctnote();

		addToSearchable();
	}

	private void correctnote() {
		// pr2
		ArrayList<String> arr = new ArrayList<String>();
		String s;

		for (int i = 0; i < wordStringArr.size(); i++) {
			s = "";
			for (int j = 0; j < wordStringArr.get(i).length(); j++) {
				if (wordStringArr.get(i).charAt(j) != '	')
					s += wordStringArr.get(i).charAt(j);
			}
			arr.add(s);
		}

		wordStringArr = arr;
	}

	private void addToSearchable() {
		// pr2
		searchableWords.add("int");
		searchableWords.add("String");
		searchableWords.add("char");
		searchableWords.add("long");
		searchableWords.add("short");
		searchableWords.add("byte");
		searchableWords.add("float");
		searchableWords.add("double");
		searchableWords.add("boolean");
		addClassNames();
	}

	private void addClassNames() {
		// pr2
		ArrayList<String> arr = new ArrayList<String>();
		boolean b = false;
		int counter = 0;

		for (int i = 0; i < wordStringArr.size(); i++) {

			arr.add(wordStringArr.get(i));

			if (haveOpenBlock(wordStringArr.get(i))) {

				if (counter == 0)
					if (isClass(arr)) {
						String s = "";

						for (int j = 0; j < arr.get(findInt(arr) + 1).length(); j++) {

							if (arr.get(findInt(arr) + 1).charAt(j) != '{')
								s += arr.get(findInt(arr) + 1).charAt(j);
							else
								break;
						}

						searchableWords.add(s);
					}

				counter++;
				b = true;
			}

			if (haveSemicalemn(wordStringArr.get(i)))
				b = true;

			if (haveClosBlock(wordStringArr.get(i))) {

				counter--;
				b = true;
			}
			if (b) {

				arr.clear();
				b = false;
			}
		}
	}

	private boolean isClass(ArrayList<String> arr) {
		// pr2
		for (int i = 0; i < arr.size(); i++) {

			if (arr.get(i).equals("class")) {
				return true;
			}
			if (arr.get(i).equals("interface")) {
				return false;
			}
		}

		return false;
	}

	private boolean haveClosBlock(String s) {
		// pr2
		if (s.contains("}"))
			return true;
		return false;
	}

	private boolean haveOpenBlock(String s) {
		// pr2
		if (s.contains("{"))
			return true;
		return false;
	}

	private boolean haveSemicalemn(String s) {
		// pr2
		if (s.contains(";"))
			return true;
		return false;
	}

	private int findInt(ArrayList<String> arr) {
		// pr2
		for (int i = 0; i < arr.size(); i++)
			if (arr.get(i).equals("class"))
				return i;

		return 0;
	}

}