    import java.util.ArrayList;
    import java.util.List;
    import java.util.Map;
    import java.util.Scanner;
    import java.util.HashMap;
    
    public class MyMiniSearchEngine {
        // default solution. OK to change.
        // do not change the signature of index()
        private Map<String, List<List<Integer>>> indexes;
    
        // disable default constructor
        private MyMiniSearchEngine() {
        }
    
        public MyMiniSearchEngine(List<String> documents) {
            this.indexes = new HashMap<String, List<List<Integer>>>();
            index(documents);
        }
    
        // each item in the List is considered a document.
        // assume documents only contain alphabetical words separated by white spaces.
        /*This method is going to scan through all of the documents, scan through each word in it, and loop through saying if the key
         * is in the map store it at what number the counter( doc number ) its at. if it is already in there, we add the current number
         * into the list of the value.
         */
        private void index(List<String> texts) {
            for(int doci = 0; doci < texts.size(); doci++)
            {
                List<Integer> list = new ArrayList<>();
                Scanner scan = new Scanner(texts.get(doci));
                int wordi = 0;
                while(scan.hasNext())
                {
                    String hold = scan.next();
                    hold = hold.toLowerCase();
                    /*List<Integer> nxt = new ArrayList<>();
                    nxt.add(wordi);*/
                    if(indexes.containsKey(hold))
                    {
                        List<List<Integer>> curr = indexes.get(hold);//might change since we dont know list of list
                        List<Integer> thisDoc = curr.get(doci);
                        //curr.add(nxt); 
                        thisDoc.add(wordi);
                        curr.set(doci, thisDoc);
                        indexes.replace(hold, curr); 
                    }
                    else
                    {
                        //create new mapped value
                        List<List<Integer>> newVal = new ArrayList<>();
                        for(int i = 0; i < texts.size(); i++)
                        {
                            newVal.add(new ArrayList<>());
                        }
                        List<Integer> thisDoc = newVal.get(doci);
                        thisDoc.add(wordi);
                        newVal.set(doci, thisDoc);
                        indexes.put(hold, newVal);
                    }
                    wordi++;
                }
            }
        }
    
         /*
          * search(key) return all the document ids where the given key phrase appears.
         key phrase can have one or two words in English alphabetic characters.
         return an empty list if search() finds no match in all documents.
         - Done by creating a new list and having 3 imbedded loops scanning through the: number of words, number of documents,
         and one to place place the index in the new map or increment. First if statement is to just return empty if the keyPhrase
         does not exist or if given an empty string
         */
       public List<Integer> search(String keyPhrase) {
            keyPhrase = keyPhrase.toLowerCase();//Making the input case insensitive
            String[] keys = keyPhrase.split(" ");
            List<Integer> result = new ArrayList<>();
            
            
            List<List<Integer>> val = indexes.get(keys[0]);
            if(keyPhrase.length() < 1 || !indexes.containsKey(keys[0]))
            {
                    return result;
            }
            for(int j = 0; j < val.size(); j++)
            {
                List<Integer> thisDoc = val.get(j);
                for(int k = 0; k < thisDoc.size(); k++)
                {
                    int doci = j;
                    int wordi = thisDoc.get(k);
                    for(int l = 0; l < keys.length; l++)
                    {
                        List<List<Integer>> nextVal = indexes.get(keys[l]);
                        List<Integer> nextDoc = nextVal.get(j);
                        if(nextDoc.contains(wordi))
                        {
                            //System.out.println("found the word");
                            if(l == keys.length -1)
                            {
                                result.add(doci);
                            }
                            else
                            {
                                wordi++;
                            }
                        }
                        else
                        {
                            break;
                        }
                    }
                }
            }
        
        
            return result;
    }
    public String toString()
    {
        String result = "";
        for(String s : indexes.keySet())
        {
            result += s + ": " + indexes.get(s).toString() + "\n";
        }
        return result; 
    }
}