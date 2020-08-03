
/**
 * Write a description of DirectorsFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class DirectorsFilter implements Filter{

        private String[] myDirector;
    
    
    public DirectorsFilter(String[] director){
    
        myDirector = director;
    
    }
    
    
    	@Override
	public boolean satisfies(String id) {
		
	    for (String dir : myDirector ){
	    
            	    if(MovieDatabase.getDirector(id).contains(dir)){
            	       
            	       return true;
            	       }
    
	   }
	    return false;
	}
    
}
