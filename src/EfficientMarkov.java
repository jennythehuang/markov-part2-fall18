import java.util.*;
import java.util.Arrays;

public class EfficientMarkov extends BaseMarkov{
	HashMap<String, ArrayList<String>> myMap;
	public EfficientMarkov(int order) {											//constructor to initialize myMap and set the order
		super(order);
		myMap = new HashMap<String, ArrayList<String>>();
	}
	public void setTraining (String text) {										//trains the markov machine
		int k = getOrder();
		myMap.clear();															
		myText = text;
		for (int i = 0; (i + k) < text.length(); i ++) {
			String kgram = "";													//kgram is the string that will become the key in myMap
			String next = "";													//next is the string (of length one) immediately following myWords
			kgram = kgram + text.substring(i, i + k);
			if ((text.length() - 1) == (i + k)) {
				next = PSEUDO_EOS;
			} else {	
				next = text.substring(i + k, i + k + 1);
			}
			if (! myMap.containsKey(kgram)) {									//creates initial mapping
				ArrayList<String> follow = new ArrayList<String>();
				follow.add(next);
				myMap.put(kgram, follow);
			} else {																//updates the value arrayList with additional next strings
				ArrayList<String> value = myMap.get(kgram);
				value.add(next);
				myMap.remove(kgram);
				myMap.put(kgram, value);
			}
		}
	}
	public ArrayList<String> getFollows (String key) {							//returns the arrayList of possible next strings for a given WordGram
		try {
			return myMap.get(key);
		} catch(NoSuchElementException e) {										//catches the error for no key value mapping pair
			System.out.println(e.getMessage());
			return null;
		}
	}
}
