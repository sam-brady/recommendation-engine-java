
/**
 * Write a description of GenreFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class GenreFilter implements Filter{
    
    private String myGenre;
    
    
    public GenreFilter(String genre){
    
        myGenre = genre;
    
    }
    
    
    	@Override
	public boolean satisfies(String id) {
		
	    if(! MovieDatabase.getGenres(id).contains(myGenre)){
	       
	       return false;
	       }

	    
	    return true;
	}

}
