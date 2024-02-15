import java.util.*;
public class date_sleep_date {

   public static void main(String args[]) {
      try { 
         System.out.println(new Date( ) + "\n"); 
         Thread.sleep(5*60*10); 
         System.out.println(new Date( ) + "\n"); 
      } catch (Exception e) {
         System.out.println("Got an exception!"); 
      }
   }
}
