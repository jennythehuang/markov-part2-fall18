import java.util.*;

public class EfficientWordMarkov extends BaseWordMarkov {
	
	HashMap<WordGram, ArrayList<String>> myMap;
	
	//initialize mymap and set order
	public EfficientWordMarkov(int o) {
		super(o);
		myMap = new HashMap<WordGram, ArrayList<String>>();
	}
	
	@Override
	//trains markov machine where a is the wordgram that is the key and n is the string following mywords, method maps inital map then updates using an arraylist with the next strings
	public void setTraining(String t) {	
		int s= getOrder();
		myMap.clear();
		myWords = t.split("\\s+");
		for (int i = 0; (i+s) < myWords.length;i++) {
			WordGram a= new WordGram(myWords,i,s);
			String n= "";
			if ((i+s)==(myWords.length-1)) {
				n= PSEUDO_EOS;}
			 else {
				n= myWords[i+s];}
			
			if (! myMap.containsKey(a)) {
				ArrayList<String> b= new ArrayList<String>();
				b.add(n);
				myMap.put(a,b);
			} else {
				ArrayList<String> x= new ArrayList<String>(myMap.get(a));
				x.add(n);
				myMap.put(a,x);
			}
		}
	}
	
	@Override
	//returns next possible strings in an arraylist
	public ArrayList<String> getFollows(WordGram wg) {
		try {
			return myMap.get(wg);
		} catch(NoSuchElementException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
}