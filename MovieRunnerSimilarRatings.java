
/**
 * Write a description of MovieRunnerSimilarRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class MovieRunnerSimilarRatings {
    
    
            public void printAverageRatings(){
    
        //String moviefile = "data/ratedmoviesfull.csv";
        //String ratingsfile = "data/ratings.csv";
        
        String moviefile = "data/ratedmovies_short.csv";
        String ratingsfile = "data/ratings_short.csv";
        
        
        FourthRatings fr = new FourthRatings();
        
        MovieDatabase database = new MovieDatabase();
        
        database.initialize(moviefile);
        
        int movie_size = database.size();
        
        System.out.println("The number of movies read is : "+ movie_size);
        
        ArrayList<Rating> ratings_list = fr.getAverageRatings(3);
                
        HashMap<String, Double> map = new HashMap<String, Double>();
        
        Collections.sort(ratings_list);
        
        int count = 0;   
        
        for(Rating rating : ratings_list){
        
            String title = database.getTitle(rating.getItem());
            
            double val = rating.getValue();
            
            if(val != 0.0 ){
            
                map.put(title , val);
                
                count += 1;

                System.out.println(val + "\t" + title );
            }

        }
             
        System.out.println("The number of movies found with minimal number of ratings is : "+ count);      
    
    }

    
    
        public void printAverageRatingsByYearAfterAndGenre(){
    
        //String moviefile = "data/ratedmoviesfull.csv";
        //String ratingsfile = "data/ratings.csv";
        
        String moviefile = "data/ratedmovies_short.csv";
        String ratingsfile = "data/ratings_short.csv";
        
        
        FourthRatings fr = new FourthRatings();
        
        MovieDatabase database = new MovieDatabase();
        
        database.initialize(moviefile);
        
        int movie_size = database.size();
        
        System.out.println("The number of movies read is : "+ movie_size);   
        
        int year = 1980;
        String genre = "Romance";
        
        AllFilters all_filter = new AllFilters();
        GenreFilter gf = new GenreFilter(genre);
        YearAfterFilter yf = new YearAfterFilter(year);
        
        all_filter.addFilter(yf);
        all_filter.addFilter(gf);
        
        ArrayList<Rating> ratings_list = fr.getAverageRatingsByFilter(1, all_filter);
        
        HashMap<String, Double> map = new HashMap<String, Double>();
        
        Collections.sort(ratings_list);
        
        int count = 0;   
        
                for(Rating rating : ratings_list){
        
            String title = database.getTitle(rating.getItem());
            
            double val = rating.getValue();
            
            String mov_genre = database.getGenres(rating.getItem());    
            
            int mov_year = database.getYear(rating.getItem());
            
            if(val != 0.0 ){
            
                map.put(title , val);
                
                count += 1;

                System.out.println(val + "\t" + mov_genre + "\t" + mov_year + "\t" + title );
            }

        }
             
        System.out.println("The number of movies found with minimal number of ratings is : "+ count);      
        
    
    }
    
    
    public void printSimilarRatings(){
    
        String moviefile = "data/ratedmoviesfull.csv";
        String ratingsfile = "data/ratings.csv";
        
        //String moviefile = "data/ratedmovies_short.csv";
        //String ratingsfile = "data/ratings_short.csv";
        
        
        FourthRatings fr = new FourthRatings();
        
        MovieDatabase mov_database = new MovieDatabase();
        
        mov_database.initialize(moviefile);  
        
        RaterDatabase rat_database = new RaterDatabase();
        
        rat_database.initialize(ratingsfile);  
        
        String id = "65";
        int numSimilarRaters = 20;
        int minimalRaters = 5;
        
        ArrayList<Rating> ratings_list = fr.getSimilarRatings(id, numSimilarRaters, minimalRaters);
        
        for(Rating rating : ratings_list){
        
            String title = mov_database.getTitle(rating.getItem());
            
            double similarity_score = rating.getValue();
            
            System.out.println("MOVIE:  " + title + "\t" +"SIMILARITY SCORE: " + similarity_score);
    
        }
    
      }

      
    public void printSimilarRatingsByGenre(){
    
        String moviefile = "data/ratedmoviesfull.csv";
        String ratingsfile = "data/ratings.csv";
        
        //String moviefile = "data/ratedmovies_short.csv";
        //String ratingsfile = "data/ratings_short.csv";
        
        
        FourthRatings fr = new FourthRatings();
        
        MovieDatabase mov_database = new MovieDatabase();
        
        mov_database.initialize(moviefile);  
        
        RaterDatabase rat_database = new RaterDatabase();
        
        rat_database.initialize(ratingsfile);  
        
        String id = "65";
        int numSimilarRaters = 20;
        int minimalRaters = 5;
        String genre = "Action";
        
        GenreFilter genre_filter = new GenreFilter(genre);
        
        ArrayList<Rating> ratings_list = fr.getSimilarRatingsByFilter(id, numSimilarRaters, minimalRaters, genre_filter);
        
        for(Rating rating : ratings_list){
        
            String title = mov_database.getTitle(rating.getItem());
            
            double similarity_score = rating.getValue();
            
            System.out.println("MOVIE:  " + title + "\t"+"GENRE : " + genre +"\t" +"SIMILARITY SCORE: " + similarity_score );
    
        }    
    
    }  
    
    
    public void printSimilarRatingsByDirector(){
    
        String moviefile = "data/ratedmoviesfull.csv";
        String ratingsfile = "data/ratings.csv";
        
        //String moviefile = "data/ratedmovies_short.csv";
        //String ratingsfile = "data/ratings_short.csv";
        
        
        FourthRatings fr = new FourthRatings();
        
        MovieDatabase mov_database = new MovieDatabase();
        
        mov_database.initialize(moviefile);  
        
        RaterDatabase rat_database = new RaterDatabase();
        
        rat_database.initialize(ratingsfile);  
        
        String id = "1034";
        int numSimilarRaters = 10;
        int minimalRaters = 3;
        String[] directors = {"Clint Eastwood","Sydney Pollack","David Cronenberg","Oliver Stone"};
        
        DirectorsFilter dir_filter = new DirectorsFilter(directors);
        
        ArrayList<Rating> ratings_list = fr.getSimilarRatingsByFilter(id, numSimilarRaters, minimalRaters, dir_filter);
        
        for(Rating rating : ratings_list){
        
            String title = mov_database.getTitle(rating.getItem());
            
            double similarity_score = rating.getValue();
            
            System.out.println("MOVIE:  " + title  +"\t" +"SIMILARITY SCORE: " + similarity_score );
    
        }        
    
    }
    
    public void printSimilarRatingsByGenreAndMinutes(){
    
        String moviefile = "data/ratedmoviesfull.csv";
        String ratingsfile = "data/ratings.csv";
        
        //String moviefile = "data/ratedmovies_short.csv";
        //String ratingsfile = "data/ratings_short.csv";
        
        
        FourthRatings fr = new FourthRatings();
        
        MovieDatabase mov_database = new MovieDatabase();
        
        mov_database.initialize(moviefile);  
        
        RaterDatabase rat_database = new RaterDatabase();
        
        rat_database.initialize(ratingsfile);  
        
        String id = "65";
        int numSimilarRaters = 10;
        int minimalRaters = 5;

        String genre = "Adventure";
        int min = 100;
        int max = 200;
        
        AllFilters all_filter = new AllFilters();
        MinutesFilter mins_filter = new MinutesFilter(min, max);
        GenreFilter genre_filter = new GenreFilter(genre);
        
        all_filter.addFilter(mins_filter);
        all_filter.addFilter(genre_filter);
        
        ArrayList<Rating> ratings_list = fr.getSimilarRatingsByFilter(id, numSimilarRaters, minimalRaters, all_filter);
        
        for(Rating rating : ratings_list){
        
            String title = mov_database.getTitle(rating.getItem());
            
            double similarity_score = rating.getValue();
            
            System.out.println("MOVIE:  " + title + "\t"+"GENRE : " + genre + "\t" +"SIMILARITY SCORE: " + similarity_score );
    
        }        
    
    
    }
    
    
    public void printSimilarRatingsByYearAfterAndMinutes(){
    
        String moviefile = "data/ratedmoviesfull.csv";
        String ratingsfile = "data/ratings.csv";
        
        //String moviefile = "data/ratedmovies_short.csv";
        //String ratingsfile = "data/ratings_short.csv";
        
        
        FourthRatings fr = new FourthRatings();
        
        MovieDatabase mov_database = new MovieDatabase();
        
        mov_database.initialize(moviefile);  
        
        RaterDatabase rat_database = new RaterDatabase();
        
        rat_database.initialize(ratingsfile);  
        
        String id = "65";
        int numSimilarRaters = 10;
        int minimalRaters = 5;

        int year = 2000;
        int min = 80;
        int max = 100;
        
        AllFilters all_filter = new AllFilters();
        MinutesFilter mins_filter = new MinutesFilter(min, max);
        YearAfterFilter year_filter = new YearAfterFilter(year);
        
        all_filter.addFilter(mins_filter);
        all_filter.addFilter(year_filter);
        
        ArrayList<Rating> ratings_list = fr.getSimilarRatingsByFilter(id, numSimilarRaters, minimalRaters, all_filter);
        
        for(Rating rating : ratings_list){
        
            String title = mov_database.getTitle(rating.getItem());
            
            double similarity_score = rating.getValue();
            
            System.out.println("MOVIE:  " + title + "\t" +"SIMILARITY SCORE: " + similarity_score );
    
        }            
    
    
    }
    

}
