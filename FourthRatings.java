
/**
 * Write a description of FourthRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;


public class FourthRatings {

    
    private double getAverageByID(String id, int minimalRaters){
    
        
        //String moviefile = "data/ratedmoviesfull.csv";
        //String ratingsfile = "data/ratings.csv";
        
        String moviefile = "data/ratedmovies_short.csv";
        String ratingsfile = "data/ratings_short.csv";
        
        RaterDatabase database = new RaterDatabase();
        database.initialize(ratingsfile);
        
        double avg_rating = 0.0;
        double running_total = 0.0;
        int count = 0;
        
        for (Rater rater : database.getRaters()){           
            
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
    
    private double dotProduct(Rater me, Rater r){
        
        ArrayList<String> me_items = me.getItemsRated();
        ArrayList<String> r_items = r.getItemsRated();
        
        double dot_product = 0.0;
        
        for(String me_item : me_items){
        
            for(String r_item : r_items){
            
                if(me_item.equals(r_item)){
                
                    double me_rating = me.getRating(me_item) - 5;
                                      
                    double r_rating = r.getRating(r_item) - 5;
                    
                    double curr_val = me_rating * r_rating;
                    
                    dot_product += curr_val;
                }
            
            }
            
        }

          return dot_product;
        
    }
    
    private ArrayList<Rating> getSimilarities(String id){
    
        //String moviefile = "data/ratedmoviesfull.csv";
        //String ratingsfile = "data/ratings.csv";
        
        String moviefile = "data/ratedmovies_short.csv";
        String ratingsfile = "data/ratings_short.csv";
        
        RaterDatabase database = new RaterDatabase();
        database.initialize(ratingsfile);
        
        ArrayList<Rating> dot_list = new ArrayList<Rating>();
        
        Rater me = database.getRater(id);
        
        for (Rater rater : database.getRaters()){
        
            if(rater != me){
            
                double dot_product = dotProduct(me, rater);
                
                if (dot_product >= 0.0){
                
                    Rating new_rating = new Rating(rater.getID() , dot_product);
                    
                    dot_list.add(new_rating);
                }
                               
            }
        
        }
        
        Collections.sort(dot_list , Collections.reverseOrder());
    
        return dot_list;
    
    }
    
    
    public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters){
        
        ArrayList<Rating> rating_list = getSimilarities(id);
        
	ArrayList<Rating> similar_list = new ArrayList<Rating>();
	
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        
        
        for(String mov_id : movies){
            
            int num_ratings = 0;
            
            double weight_total = 0.0;
            
            for(int i=0; i<numSimilarRaters; i++){
            
                Rating curr_rating = rating_list.get(i);
                
                String curr_rater_id = curr_rating.getItem();
                
                Rater curr_rater = RaterDatabase.getRater(curr_rater_id);
                
                if(curr_rater.hasRating(mov_id)){
                
                    num_ratings += 1;
                    
                    double similar_rating = curr_rater.getRating(mov_id);
                    
                    double weight = curr_rating.getValue();
                    
                    weight_total += similar_rating * weight;
                }
            
            }
            
            if(num_ratings >= minimalRaters){
                
                double weight_avg = weight_total / num_ratings;
                
                similar_list.add(new Rating(mov_id, weight_avg));
            
            
            }
            

        }

        Collections.sort(similar_list, Collections.reverseOrder());
        
        return similar_list;
        
    }
    
    
    public ArrayList<Rating> getSimilarRatingsByFilter(String id, int numSimilarRaters, int minimalRaters, Filter filterCriteria){
    
        ArrayList<Rating> rating_list = getSimilarities(id);
        
	ArrayList<Rating> similar_list = new ArrayList<Rating>();
	
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
        
        
        for(String mov_id : movies){
            
            int num_ratings = 0;
            
            double weight_total = 0.0;
            
            for(int i=0; i<numSimilarRaters; i++){
            
                Rating curr_rating = rating_list.get(i);
                
                String curr_rater_id = curr_rating.getItem();
                
                Rater curr_rater = RaterDatabase.getRater(curr_rater_id);
                
                if(curr_rater.hasRating(mov_id)){
                
                    num_ratings += 1;
                    
                    double similar_rating = curr_rater.getRating(mov_id);
                    
                    double weight = curr_rating.getValue();
                    
                    weight_total += similar_rating * weight;
                }
            
            }
            
            if(num_ratings >= minimalRaters){
                
                double weight_avg = weight_total / num_ratings;
                
                similar_list.add(new Rating(mov_id, weight_avg));
            
            
            }
            

        }

        Collections.sort(similar_list, Collections.reverseOrder());
        
        return similar_list;   
    
    }
    
}
