import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class WordLadders {
	
	public static HashMap<String,Word> words = new HashMap<>();
	
    private static void readInput(String file) throws CustomException  {
       	Scanner scanner = null;
       	int i;
       	String [] nodes;
        try {
            scanner = new Scanner(new File (file));
            
            while (scanner.hasNext()) {
            	//String line = scanner.next();
            	//System.out.println(line);
            	//nodes = line.split(" ");
            	i = calculatePath(scanner.next(), scanner.next());
            	System.out.println(""+ i);
            }
        } catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }
    
    private static void readData (String file) throws CustomException{
    	Scanner scanner = null;
        try {
            scanner = new Scanner(new File (file));
            while (scanner.hasNext()) {
            	addWord(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }
    
    
    
    public static void main(String[] args) throws CustomException {
    	//readInput();
    	readData (args[0]);
    	readInput(args[1]);
		/*addWord("there");
    	addWord("which");
    	addWord("their");
    	addWord("about");
    	addWord("these");
    	addWord("words");
    	addWord("would");
    	addWord("other");
    	addWord("write");
    	addWord("could");*/
    	//printGraph();
    	/*int i = calculatePath("other", "there");
    	System.out.println(""+ i);
    	i= calculatePath("other", "their");
    	System.out.println(""+ i);
    	i= calculatePath("could", "would");
    	System.out.println(""+ i);
    	i= calculatePath("would", "could");
    	System.out.println(""+ i);
    	i= calculatePath("there", "other");
    	System.out.println(""+ i);
    	i= calculatePath("about", "there");
    	System.out.println(""+ i);*/
    	
    	//printGraph();
    }
    
    
public static int calculatePath(String from, String to){
    	
    	//Remember to check that a node is only revised once !
    	Queue<String> next_words = new LinkedList<String>();
    	
    	next_words.add(from);
    	
    	int count = 1;
    	while (next_words.isEmpty()==false){
    		Word next = words.get(next_words.poll());
    		for (Word w: next.link){
    			if(w.name.equals(to)) return count;
    			else{
    				next_words.add(next.name);
    			}
    		}
    		count +=1;
    	}
    	
    	return -1;
    
}    
    
    public static void addWord(String word1) {
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
    
    
    public static void printGraph() {
		Set<String> wordset = words.keySet();
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

