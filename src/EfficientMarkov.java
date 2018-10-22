import java.util.*;

public class EfficientMarkov extends BaseMarkov{
	
	HashMap<String, ArrayList<String>> myMap;
	
	
	//initialize myMap and set order
	public EfficientMarkov(int o) {
		super(o);
		myMap = new HashMap<String, ArrayList<String>>();
	}
	
	@Override
	//method to train the markov machine where k is myMap's key and n is the string following myWords, method maps then updates using an Arraylist with the next strings
	public void setTraining (String t) {
		
		int a = getOrder();
		myMap.clear();															
		myText = t;
		
		for (int i = 0; (i+a) < t.length(); i ++) {
			String k= "";
			String n= "";
			k= k+ t.substring(i,i+a);
			
			if ((t.length() - 1) == (i+a)) {
				n = PSEUDO_EOS;}
			else {
				n = t.substring(i+a,i+a+1);}
			
			if (! myMap.containsKey(k)) {
				ArrayList<String> follow = new ArrayList<String>();
				follow.add(n);
				myMap.put(k, follow);
			} else {
				ArrayList<String> value = myMap.get(k);
				value.add(n);
				myMap.remove(k);
				myMap.put(k, value);
			}
		}
	}
	
	@Override
	//returns the possible next strings in an arraylist
	public ArrayList<String> getFollows (String s) {
		
		try {
			return myMap.get(s);
		} catch(NoSuchElementException e) {	
			System.out.println(e.getMessage());
			return null;
		}
	}
}
