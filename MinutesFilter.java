
/**
 * Write a description of MinutesFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class MinutesFilter implements Filter{
    
        private int min;
        private int max;
    
    
    public MinutesFilter(int min_min, int max_min){
    
        min = min_min;
        max = max_min;
        
    }
    
    
        @Override
    public boolean satisfies(String id) {
        
        if(MovieDatabase.getMinutes(id) < min || MovieDatabase.getMinutes(id) > max){
           
           return false;
           }

        
        return true;
    }

}
