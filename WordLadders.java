import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;



public class WordLadders {
	
	HashMap<String,Word> words = new HashMap<>();
	
    private static void readInput() throws CustomException {
        Scanner scanner = null;
        try {
            scanner = new Scanner(System.in);
            //readN(scanner);
            //readPersons(scanner);
            scanner.nextLine();
            //readPreferences(scanner);
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }
    
    public static void main(String[] args) throws CustomException {
    	WordLadders w = new WordLadders();
System.out.println(checkLadder("other", "there"));
w.addWord("there");
    	//    	w.words.add(new Word("there"));
    	w.addWord("which");
    	w.addWord("their");
    	w.addWord("about");
    	w.addWord("these");
    	w.addWord("words");
    	w.addWord("would");
    	w.addWord("other");
    	w.addWord("write");
    	w.addWord("could");
    	w.printGraph();
    	
    }
    public void addWord(String word1) {
		Word new_word = new Word(word1);
		//loop all the existing words in the ladder to find potential matches.
		for (Iterator  it = words.values().iterator();  it.hasNext();) {
			Word oldWord= (Word) it.next();
			//look through the last 4 letters in the new word
			if(checkLadder(new_word.name, oldWord.name)) {
				new_word.link.add(oldWord);
			}
			//look through the last 4 letters in the old word
			if(checkLadder(oldWord.name,new_word.name)) {
				oldWord.link.add(new_word);
			}
		}
		words.put(word1, new_word);
		
		
	}
    public void printGraph() {
		Set<String> wordset=words.keySet();
		for (Iterator iterator = wordset.iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();
			System.out.println(words.get(string).toString());
		}
	}
    public static boolean checkLadder(String linkFrom, String linkTo) {
		boolean isLadder=false;
		String temp=linkTo;
		for (int i = 1; i < 5; i++) {
//			System.out.println("word :" + linkFrom +" letter at:" + i + " char= "+linkFrom.substring(i, i+1));
			temp= temp.replaceFirst(linkFrom.substring(i, i+1), "");
		}
		isLadder=(temp.length()==1);
		return isLadder;
	}
    private static class CustomException extends Throwable {
        public CustomException(String message) {
            System.out.println(message);
        }
    }
    
    

    private static class Word {
    	
    	public String name;
    	HashMap <Character,Integer> letters = new HashMap <>();
    	private ArrayList<Word> link = new ArrayList<Word> ();
    	
    	public Word (String word) {
    		name = word;
    	}
    	
    	private void decodeWord () {
    		
    	}
    	@Override
    	public String toString() {
    		String out;
    		out="name: "+name+" links:";
    		for (Word word : link) {
				out+="\t"+word.name;
			}
    		return out;
    	}
    	
    	
    }
}

