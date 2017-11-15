package sanapala;

import java.util.*;
import java.io.*;

public class Sanapala {

    public static void main(String[] args) {
        String searchTerm = args[1];
        int wordlen = Integer.parseInt(args[2]);

        Map<String, List<String>> m = new HashMap<String, List<String>>();

        try {
            Scanner s = new Scanner(new File(args[0]));
            while (s.hasNextLine()) {
                String word = s.nextLine();
                String alpha = alphabetize(word);
                List<String> l = m.get(alpha);
                if (l == null)
                    m.put(alpha, l=new ArrayList<String>());
                l.add(word);
            }
        } catch (FileNotFoundException e) {
            System.err.println(e);
            System.exit(1);
        }

        List<String> searchCombinations = combinations(searchTerm, wordlen);
        for (String sk : searchCombinations) {              
            if (m.containsKey(alphabetize(sk))) { 
                System.out.println("Etsitään " + sk);
                System.out.println("Löytyi:");
                List<String> values = m.get(alphabetize(sk));
                for (String val : values) {
                    System.out.println(val);
                } 
            }
        }
    }

    private static List<String> combinations(String s, int len) {
        List<String> combinations = new ArrayList<>();
        char[] elements = s.toCharArray();
        int[] indices;
        CombinationGenerator x = new CombinationGenerator (elements.length, len);
        StringBuffer combination;
        while (x.hasMore ()) {
          combination = new StringBuffer ();
          indices = x.getNext ();
          for (int i = 0; i < indices.length; i++) {
              combination.append (elements[indices[i]]);
          }
          combinations.add(combination.toString());          
        }
        return combinations;
    }
            
    private static String alphabetize(String s) {
        char[] a = s.toCharArray();
        Arrays.sort(a);
        return new String(a);
    }
}
