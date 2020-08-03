
/**
 * Write a description of MovieRunnerWithFilters here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class MovieRunnerWithFilters {

        public void printAverageRatings(){
    
        //String moviefile = "data/ratedmoviesfull.csv";
        //String ratingsfile = "data/ratings.csv";
        
        String moviefile = "data/ratedmovies_short.csv";
        String ratingsfile = "data/ratings_short.csv";
        
        
        ThirdRatings tr = new ThirdRatings(ratingsfile);
        
        int rating_size = tr.getRaterSize();
        
        System.out.println("The number of raters read is : "+ rating_size);
        
        MovieDatabase database = new MovieDatabase();
        
        database.initialize(moviefile);
        
        int movie_size = database.size();
        
        System.out.println("The number of movies read is : "+ movie_size);
        
        ArrayList<Rating> ratings_list = tr.getAverageRatings(3);
                
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
    
    
    public void printAverageRatingsByYear(){
    
        //String moviefile = "data/ratedmoviesfull.csv";
        //String ratingsfile = "data/ratings.csv";
        
        String moviefile = "data/ratedmovies_short.csv";
        String ratingsfile = "data/ratings_short.csv";
        
        
        ThirdRatings tr = new ThirdRatings(ratingsfile);
        
        int rating_size = tr.getRaterSize();
        
        System.out.println("The number of raters read is : "+ rating_size);
        
        MovieDatabase database = new MovieDatabase();
        
        database.initialize(moviefile);
        
        int movie_size = database.size();
        
        System.out.println("The number of movies read is : "+ movie_size);   
        
        int year = 2000;
        
        YearAfterFilter year_filter = new YearAfterFilter(year);
        
        ArrayList<Rating> ratings_list = tr.getAverageRatingsByFilter(1, year_filter);
        
        HashMap<String, Double> map = new HashMap<String, Double>();
        
        Collections.sort(ratings_list);
        
        int count = 0;   
        
        for(Rating rating : ratings_list){
        
            String title = database.getTitle(rating.getItem());
            
            double val = rating.getValue();
            
            int mov_year = database.getYear(rating.getItem());
            
            if(val != 0.0 ){
            
                map.put(title , val);
                
                count += 1;

                System.out.println(val + "\t" + mov_year + "\t" +title );
            }

        }
             
        System.out.println("The number of movies found with minimal number of ratings is : "+ count);      
    
    
    
    }
    
    public void printAverageRatingsByGenre(){
    
        //String moviefile = "data/ratedmoviesfull.csv";
        //String ratingsfile = "data/ratings.csv";
        
        String moviefile = "data/ratedmovies_short.csv";
        String ratingsfile = "data/ratings_short.csv";
        
        
        ThirdRatings tr = new ThirdRatings(ratingsfile);
        
        int rating_size = tr.getRaterSize();
        
        System.out.println("The number of raters read is : "+ rating_size);
        
        MovieDatabase database = new MovieDatabase();
        
        database.initialize(moviefile);
        
        int movie_size = database.size();
        
        System.out.println("The number of movies read is : "+ movie_size);   
        
        String genre = "Crime";
        
        GenreFilter genre_filter = new GenreFilter(genre);
        
        ArrayList<Rating> ratings_list = tr.getAverageRatingsByFilter(1, genre_filter);
        
        HashMap<String, Double> map = new HashMap<String, Double>();
        
        Collections.sort(ratings_list);
        
        int count = 0;   
        
        for(Rating rating : ratings_list){
        
            String title = database.getTitle(rating.getItem());
            
            double val = rating.getValue();
            
            String mov_genre = database.getGenres(rating.getItem());
            
            if(val != 0.0 ){
            
                map.put(title , val);
                
                count += 1;

                System.out.println(val + "\t" + mov_genre + "\t" +title );
            }

        }
             
        System.out.println("The number of movies found with minimal number of ratings is : "+ count);      
    
        
    }
    
    public void printAverageRatingsByMinutes(){
    
        //String moviefile = "data/ratedmoviesfull.csv";
        //String ratingsfile = "data/ratings.csv";
        
        String moviefile = "data/ratedmovies_short.csv";
        String ratingsfile = "data/ratings_short.csv";
        
        
        ThirdRatings tr = new ThirdRatings(ratingsfile);
        
        int rating_size = tr.getRaterSize();
        
        System.out.println("The number of raters read is : "+ rating_size);
        
        MovieDatabase database = new MovieDatabase();
        
        database.initialize(moviefile);
        
        int movie_size = database.size();
        
        System.out.println("The number of movies read is : "+ movie_size);   
        
        int min = 110;
        int max = 170;
        
        MinutesFilter mins_filter = new MinutesFilter(min, max);
        
        ArrayList<Rating> ratings_list = tr.getAverageRatingsByFilter(1, mins_filter);
        
        HashMap<String, Double> map = new HashMap<String, Double>();
        
        Collections.sort(ratings_list);
        
        int count = 0;   
        
        for(Rating rating : ratings_list){
        
            String title = database.getTitle(rating.getItem());
            
            double val = rating.getValue();
            
            int mov_time = database.getMinutes(rating.getItem());
            
            if(val != 0.0 ){
            
                map.put(title , val);
                
                count += 1;

                System.out.println(val + "\t" + mov_time + "\t" +title );
            }

        }
             
        System.out.println("The number of movies found with minimal number of ratings is : "+ count);      
    
            }
    
            
    public void printAverageRatingsByDirectors(){
    
        //String moviefile = "data/ratedmoviesfull.csv";
        //String ratingsfile = "data/ratings.csv";
        
        String moviefile = "data/ratedmovies_short.csv";
        String ratingsfile = "data/ratings_short.csv";
        
        
        ThirdRatings tr = new ThirdRatings(ratingsfile);
        
        int rating_size = tr.getRaterSize();
        
        System.out.println("The number of raters read is : "+ rating_size);
        
        MovieDatabase database = new MovieDatabase();
        
        database.initialize(moviefile);
        
        int movie_size = database.size();
        
        System.out.println("The number of movies read is : "+ movie_size);   
        
        String[] directors = {"Charles Chaplin","Michael Mann","Spike Jonze"};
        
        DirectorsFilter dir_filter = new DirectorsFilter(directors);
        
        ArrayList<Rating> ratings_list = tr.getAverageRatingsByFilter(1, dir_filter);
        
        HashMap<String, Double> map = new HashMap<String, Double>();
        
        Collections.sort(ratings_list);
        
        int count = 0;   
    
        for(Rating rating : ratings_list){
        
            String title = database.getTitle(rating.getItem());
            
            double val = rating.getValue();
            
            String mov_dir = database.getDirector(rating.getItem());
            
            if(val != 0.0 ){
            
                map.put(title , val);
                
                count += 1;

                System.out.println(val + "\t" + mov_dir + "\t" +title );
            }

        }
             
        System.out.println("The number of movies found with minimal number of ratings is : "+ count);      
        
    
    }        
            
    public void printAverageRatingsByYearAfterAndGenre(){
    
        //String moviefile = "data/ratedmoviesfull.csv";
        //String ratingsfile = "data/ratings.csv";
        
        String moviefile = "data/ratedmovies_short.csv";
        String ratingsfile = "data/ratings_short.csv";
        
        
        ThirdRatings tr = new ThirdRatings(ratingsfile);
        
        int rating_size = tr.getRaterSize();
        
        System.out.println("The number of raters read is : "+ rating_size);
        
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
        
        ArrayList<Rating> ratings_list = tr.getAverageRatingsByFilter(1, all_filter);
        
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
    
    public void printAverageRatingsByDirectorsAndMinutes(){
    
        //String moviefile = "data/ratedmoviesfull.csv";
        //String ratingsfile = "data/ratings.csv";
        
        String moviefile = "data/ratedmovies_short.csv";
        String ratingsfile = "data/ratings_short.csv";
        
        
        ThirdRatings tr = new ThirdRatings(ratingsfile);
        
        int rating_size = tr.getRaterSize();
        
        System.out.println("The number of raters read is : "+ rating_size);
        
        MovieDatabase database = new MovieDatabase();
        
        database.initialize(moviefile);
        
        int movie_size = database.size();
        
        System.out.println("The number of movies read is : "+ movie_size);   
        
        String[] directors = {"Spike Jonze","Michael Mann","Charles Chaplin","Francis Ford Coppola"};
        int min = 30;
        int max = 170;
        
        AllFilters all_filter = new AllFilters();
        MinutesFilter mins_filter = new MinutesFilter(min, max);
        DirectorsFilter dir_filter = new DirectorsFilter(directors);
        
        all_filter.addFilter(mins_filter);
        all_filter.addFilter(dir_filter);
        
        ArrayList<Rating> ratings_list = tr.getAverageRatingsByFilter(1, all_filter);
        
        HashMap<String, Double> map = new HashMap<String, Double>();
        
        Collections.sort(ratings_list);
        
        int count = 0;   
    
        for(Rating rating : ratings_list){
        
            String title = database.getTitle(rating.getItem());
            
            double val = rating.getValue();
            
            String mov_dirs = database.getDirector(rating.getItem());
            
            int mov_mins = database.getMinutes(rating.getItem());
            
            if(val != 0.0 ){
            
                map.put(title , val);
                
                count += 1;

                System.out.println(val + "\t" + mov_dirs + "\t" + mov_mins + "\t" + title );
            }

        }
             
        System.out.println("The number of movies found with minimal number of ratings is : "+ count);      
           
    }
    
}
