
/**
 * Write a description of MovieRunnerAverage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class MovieRunnerAverage {
    
    public void printAverageRatings(){
    
        //String moviefile = "data/ratedmoviesfull.csv";
        //String ratingsfile = "data/ratings.csv";
        
        String moviefile = "data/ratedmovies_short.csv";
        String ratingsfile = "data/ratings_short.csv";
        
        
        SecondRatings sr = new SecondRatings(moviefile, ratingsfile);
        
        int movie_size = sr.getMovieSize();
        
        int rating_size = sr.getRaterSize();
        
        System.out.println("Movie size : "+ movie_size +" and Rating size : "+ rating_size);
        
        ArrayList<Rating> ratings_list = sr.getAverageRatings(3);
        
        HashMap<String, Double> map = new HashMap<String, Double>();
        
        Collections.sort(ratings_list);
                
        
        for(Rating rating : ratings_list){
        
            String title = sr.getTitle(rating.getItem());
            
            double val = rating.getValue();
            
            if(val != 0.0 ){
            
                map.put(title , val);
            
                System.out.println(val + "\t" + title );
            }
        
        }
       
    
    }
    
    public double getAverageRatingOneMovie(){
    
        String moviefile = "data/ratedmovies_short.csv";
        String ratingsfile = "data/ratings_short.csv";
        
        String movie_title = "The Godfather";
        
        SecondRatings sr = new SecondRatings(moviefile, ratingsfile);    
        
        String mov_id = sr.getID(movie_title);
            
        ArrayList<Rating> ratings_list = sr.getAverageRatings(0);   
        
        double movie_rating = 0.0;
                
            for(Rating rating : ratings_list){
            
                 String curr_title = sr.getTitle(rating.getItem());
                 
                 double val = rating.getValue();
                
                 if(curr_title.equals(movie_title)){
                    
                    movie_rating = val;
                    
                    }
            }
            
            System.out.println("The movie "+ movie_title + " has a rating of : " + movie_rating);
            
        return movie_rating; 
    
    }
    


}


