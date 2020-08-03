
/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public SecondRatings() {
        // default constructor
        this("data/ratedmovies_short.csv", "data/ratings_short.csv");
    }
    
    
    public SecondRatings(String moviefile, String ratingsfile) {
    
        FirstRatings fr = new FirstRatings();
        
        myMovies = fr.loadMovies(moviefile);
        myRaters = fr.loadRaters(ratingsfile);
        
    
    }
    
    public int getMovieSize(){
    
        int size = myMovies.size();
    
        return size;
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
    
    
        String moviefile = "data/ratedmovies_short.csv";
        String ratingsfile = "data/ratings_short.csv";
    
        
        SecondRatings sr = new SecondRatings(moviefile, ratingsfile);            
        
         //System.out.println(sr.getAverageByID("68646", 1));
         System.out.println(getAverageRatings(1));
    
    }
     
     
    public ArrayList<Rating> getAverageRatings(int minimalRaters){
    
        
        ArrayList<Rating> ratings_list = new ArrayList<Rating>();
        
        for(Movie mov : myMovies){
        
            
                double curr_rating = getAverageByID(mov.getID()  , minimalRaters);
        
                Rating rating = new Rating(mov.getID(), curr_rating);
                
                ratings_list.add(rating);
        
      
        }

        
        return ratings_list;
    
    }
    
    
    public String getTitle(String id){
    
        String title = "ID not found";
        
        for (Movie mov : myMovies){
            
                if(mov.getID().equals(id)){
                
                    title = mov.getTitle();
                }
        }
                
        return title;
    
    }
    
    public String getID(String title){
    
        String id = "NO SUCH TITLE";
        
        for (Movie mov : myMovies){
        
            if(mov.getTitle().equals(title)){
            
                id = mov.getID();
            
            }
        
        }
    
        return id;
    }
    
    
    
}
