import java.util.*;

public class EfficientWordMarkov extends BaseWordMarkov {
	HashMap<WordGram, ArrayList<String>> myMap;
	public EfficientWordMarkov(int order) {											//constructor to initialize myMap and set the order
		super(order);
		myMap = new HashMap<WordGram, ArrayList<String>>();
	}
	@Override
	public void setTraining(String text) {											//trains the markov machine
		int size = getOrder();
		myMap.clear();
		myWords = text.split("\\s+");
		for (int i = 0; (i + size) < myWords.length; i ++) {
			WordGram wg = new WordGram(myWords, i, size);							//wg is the WordGram object that will become the key in myMap
			String next = "";														//next is the string immediately following myWords
			if ((i + size) == (myWords.length - 1)) {
				next = PSEUDO_EOS;
			} else {
				next = myWords[i + size];
			}
			if (! myMap.containsKey(wg)) {											//creates initial mapping
				ArrayList<String> follow = new ArrayList<String>();
				follow.add(next);
				myMap.put(wg, follow);
			} else {																	//updates the value arrayList with additional next strings
				ArrayList<String> value = new ArrayList<String>(myMap.get(wg));
				value.add(next);
				myMap.put(wg, value);
			}
		}
	}
	@Override
	public ArrayList<String> getFollows(WordGram key) {								//returns the arrayList of possible next strings for a given WordGram
		try {
			return myMap.get(key);
		} catch(NoSuchElementException e) {											//catches the error for no key value mapping pair
			System.out.println(e.getMessage());
			return null;
		}
	}
}