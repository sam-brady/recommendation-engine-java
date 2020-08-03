
/**
 * Write a description of ThirdRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;


public class ThirdRatings {

    
    private ArrayList<Rater> myRaters;
    
    public ThirdRatings() {
        // default constructor
        this("data/ratings_short.csv");
    }
    
    
    public ThirdRatings(String ratingsfile) {
    
        FirstRatings fr = new FirstRatings();
        
        myRaters = fr.loadRaters(ratingsfile);
        
    
    }
    
    public int getRaterSize(){
    
        int size = myRaters.size();
        
        return size;
    
    }
    
    private double getAverageByID(String id, int minimalRaters){
    
        double avg_rating = 0.0;
        double running_total = 0.0;
        int count = 0;
        
        
        for (Rater rater : myRaters){           
            
            if(rater.getItemsRated().contains(id)){
                
                double movie_rating = rater.getRating(id);
                
                running_total += movie_rating;
                
                count += 1;
                
                //System.out.println(movie_rating);

            }
        }

        if(count >= minimalRaters){
            avg_rating = running_total / count;
        }

            
            return avg_rating;  
    
    }
    
    public void tester(){
    
        String ratingsfile = "data/ratings_short.csv";
    
        ThirdRatings tr = new ThirdRatings(ratingsfile);            
        
         //System.out.println(tr.getAverageByID("68646", 1));
         System.out.println(getAverageRatings(1));
    
    }
     
     
    public ArrayList<Rating> getAverageRatings(int minimalRaters){
    
        
        ArrayList<Rating> ratings_list = new ArrayList<Rating>();
        
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        
        for(String mov_id : movies){
        
            
                double curr_rating = getAverageByID(mov_id  , minimalRaters);
        
                Rating rating = new Rating(mov_id, curr_rating);
                
                ratings_list.add(rating);
        
      
        }

        return ratings_list;
    
    }
    

    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria){
    
        ArrayList<Rating> filtered_list = new ArrayList<Rating>();
        
        ArrayList<String> id_list = MovieDatabase.filterBy(filterCriteria);
                
                for(String mov_id : id_list){
        
            
                double curr_rating = getAverageByID(mov_id  , minimalRaters);
        
                Rating rating = new Rating(mov_id, curr_rating);
                
                filtered_list.add(rating);
        
      
        }
        
        return filtered_list;
           
    }
    
    
    
    
}
