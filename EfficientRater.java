
/**
 * Write a description of EfficientRater here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

    import java.util.*;
    
    
public class EfficientRater implements Rater{
    
    private String myID;
    private HashMap<String,Rating> myRatings;

    public EfficientRater(String id) {
        myID = id;
        myRatings = new HashMap<String,Rating>();
    }
 
    public void addRating(String item, double rating) {
        
        Rating mov_rating = new Rating(item, rating);
         
        myRatings.put(mov_rating.getItem() , mov_rating);
    }
  
    public boolean hasRating(String item) {

            if (myRatings.keySet().contains(item)){
                return true;
            
           }
        
        return false;
    }
 
    public String getID() {
        return myID; 
    }
 
    public double getRating(String item) {
        for(String k : myRatings.keySet()){
            if (myRatings.get(k).getItem().equals(item)){
                return myRatings.get(k).getValue();
            }
        }
        
        return -1;
    }

    public int numRatings() {
        return myRatings.size();
    } 

    public ArrayList<String> getItemsRated() {
        ArrayList<String> list = new ArrayList<String>();
        for(String k : myRatings.keySet()){
            list.add(myRatings.get(k).getItem());
            
        }
        
        return list;
    }
}


