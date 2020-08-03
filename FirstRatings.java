
/**
 * Write a description of FirstRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class FirstRatings {
    
    public ArrayList<Movie> loadMovies(String filename){
    
       ArrayList<Movie> loadedMovies =  new ArrayList<Movie>();
        
       FileResource fr = new FileResource(filename);
       CSVParser parser = fr.getCSVParser();
       try{
       for (CSVRecord record : parser.getRecords()){
       
           Movie new_movie = new Movie(record.get("id"), record.get("title"),
           record.get("year"), record.get("genre"), record.get("director"), 
           record.get("country"), record.get("poster"), 
           Integer.parseInt(record.get("minutes").trim()));   
           
           loadedMovies.add(new_movie);
       }
      }
      catch(Exception ioe){
        System.out.println("IOException caught");
        }
    
    
       return loadedMovies;
    }
    
    
    public void testLoadMovies(){
    
        //ArrayList<Movie> movieData = loadMovies("data/ratedmovies_short.csv");
        ArrayList<Movie> movieData = loadMovies("data/ratedmoviesfull.csv");
        
        
        System.out.println("There are: " + movieData.size() + " movies in the file. They are:\n" );
        //for(Movie m : movieData) {
        //    System.out.println(m);
        //}
        
        int genre_count = 0;
        int min_count = 0;
        int max_dir = 0;
        int dir_count = 0;
        HashMap<String, ArrayList<String>> dirCount = new HashMap<String, ArrayList<String>>();        
        
        for (Movie movie : movieData){       
            
            if(movie.getGenres().contains("Comedy")){
            
                genre_count += 1;
            }
            
            if (movie.getMinutes() > 150){
            
                min_count += 1;
            }
            
            ArrayList<String> dirList = new ArrayList<String>();
            
            String dir = movie.getDirector();
            
            for (String d : dir.split(",")){
                dirList.add(dir);
            }          
            
            dirCount.put(movie.getTitle(), dirList);
            
            if (dirList.size() >= max_dir){
            
                max_dir = dirList.size();
            }
            
        }
        
        System.out.println("Genre count = " + genre_count);
        System.out.println("Minute count = " + min_count);
        
        
        for(String key: dirCount.keySet()){
        
            ArrayList<String> currDir = dirCount.get(key);
            
            if(currDir.size() == max_dir){
            
                dir_count += 1;
            }
        
        }
        
        
        System.out.println("The max number of directors on any movie is = " + max_dir);
        System.out.println("And the number of movies with this many is  = " + dir_count);
        
        
        HashMap<String, Integer> unique_dir_map = new HashMap<String, Integer>();
        
        for(String key: dirCount.keySet()){
            
            ArrayList<String> currDir = dirCount.get(key);
            
            for(String d : currDir){
                
                if( ! unique_dir_map.containsKey(d)){
                
                     unique_dir_map.put(d, 1);
                }
                else{
                
                     unique_dir_map.put(d , unique_dir_map.get(d) + 1);
                }
            }
        
        }
         
        int max= 0;
        String director_max = "";
        
        for(String key: unique_dir_map.keySet()){
        
            int currMax = unique_dir_map.get(key);
            
            if(currMax > max){
            
                max = currMax;
                director_max = key;
            }
        }
        
        
        
        System.out.println("The most number of films by one director is : " + max + " by " +  director_max);
        
    
    }
    
    
    public ArrayList<Rater> loadRaters(String filename){

        ArrayList<Rater> loadedRaters =  new ArrayList<Rater>();
        ArrayList<String> raterIds =  new ArrayList<String>();

        
       FileResource fr = new FileResource(filename);
       CSVParser parser = fr.getCSVParser();
       try{
       for (CSVRecord record : parser.getRecords()){
       
            String raterID = record.get(0);
            String movieID = record.get(1);
            double rating = Double.parseDouble(record.get(2));
           
            if(! raterIds.contains(raterID)){
            
               raterIds.add(raterID); 
               
               Rater currRater = new EfficientRater(raterID);
               currRater.addRating(movieID, rating);
               loadedRaters.add(currRater);
            
            }
            else{
            
                loadedRaters.get(raterIds.indexOf(raterID)).addRating(movieID, rating);
            
            }


       }
      }
      catch(Exception ioe){
        System.out.println("IOException caught");
        }

       return loadedRaters;       
    
    }
    
    
    public void testLoadRaters(){
        
        ArrayList<Rater> raterData = loadRaters("data/ratings.csv");
        
        HashMap<String, ArrayList<Rater>> map = new HashMap<String, ArrayList<Rater>>();
        
        for (Rater r : raterData){
        
            if( ! map.containsKey(r.getID())){
                
                ArrayList<Rater> raterList = new ArrayList<Rater>();
                
                raterList.add(r);
                
                map.put(r.getID(), raterList);                
                
            }
            else{
            
                ArrayList<Rater> currRater = map.get(r.getID());
                
                currRater.add(r);
                
                map.put(r.getID(), currRater);               
                
            }                    
            
        }
        
        
        ////////////////////////////////////////////////////////////////////////////////////////////////
        //FIND RATER AND NUMBER OF RATINGS PLUS EACH MOVIE RATED WITH RATING      
        
        //System.out.println("There are: " + map.keySet().size() + " raters in the file. They are:\n" );
        
        for(String key : map.keySet()) {
                     
            for (int i = 0; i < map.get(key).size(); i++){
                
                ArrayList<String> currMovies = map.get(key).get(i).getItemsRated();
                
                //System.out.println("Rater # " + key + " Number of ratings = " + currMovies.size());
                
                for(String mov : currMovies){
                
                    double rating = map.get(key).get(i).getRating(mov);
                    
                    //System.out.println("Movie ID : "+ mov +" with a rating of : "+ rating);
                                       
                }
                            
            }
            
        }
    
        
        
        ////////////////////////////////////////////////////////////////////////////////////////////////
        //FIND RATINGS FOR PARTICULAR RATER        
        
        String rater_num = "193";
        
        for(int i = 0; i < map.get(rater_num).size(); i++){
                
            ArrayList<String> currMovies = map.get(rater_num).get(i).getItemsRated();
                
            System.out.println("Rater # " + rater_num + " Number of ratings = " + currMovies.size());
                
                for(String mov : currMovies){
                
                    double rating = map.get(rater_num).get(i).getRating(mov);
                    
                    System.out.println("Movie ID : "+ mov +" with a rating of : "+ rating);
                                       
                }
        
        }
        
        
        
        ////////////////////////////////////////////////////////////////////////////////////////////////
        //FIND MOST ACTIVE RATER AND RATINGS COUNT
        
        int max_ratings = 0;
        
        ArrayList<String> maxRaters = new ArrayList<String>();
        
        for(String key : map.keySet()) {
                     
            for (int i = 0; i < map.get(key).size(); i++){
                
                ArrayList<String> currMovies = map.get(key).get(i).getItemsRated();
                
                int curr_max = currMovies.size();
                
                if(curr_max > max_ratings){
                
                   max_ratings = curr_max;
                
                }
                            
            }
            
        }               
        
            for(String key : map.keySet()) {    
                    for (int i = 0; i < map.get(key).size(); i++){
              
               if(map.get(key).get(i).getItemsRated().size() == max_ratings){
            
                  maxRaters.add(key);
            
                }
            }
            
        }  
        
        System.out.println("The mast active rater is rater # " + maxRaters + " with a rating count of : " + max_ratings);

        
        ////////////////////////////////////////////////////////////////////////////////////////////////
        //FIND MOVIE COUNT FOR PARTICULAR MOVIE        
        
        String movie = "1798709";
        int movie_count = 0;
        
        for (Rater r : raterData){
        
            ArrayList<String> mov_list = r.getItemsRated();
            
            for (String mov : mov_list){
            
                if(mov.equals(movie) ){
                
                    movie_count += 1;
                }
            
            }
        
        }
        
        System.out.println("Movie ID : "+ movie + " has a movie rating count of " + movie_count);
        
        
        ////////////////////////////////////////////////////////////////////////////////////////////////
        //COUNT UNIQUE NUMBER OF MOVIES IN DATA
        
        ArrayList<String> unique_mov_count = new ArrayList<String>();
        
        for (Rater r : raterData){
        
            ArrayList<String> mov_list = r.getItemsRated();
            
            for (String mov : mov_list){
                
                if( ! unique_mov_count.contains(mov)){
                
                    unique_mov_count.add(mov);
                }
            
            }
            
            
        }
    
        System.out.println("The unique number of movies rated is : " + unique_mov_count.size());
        
    }
    
}
