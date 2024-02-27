public class CosExample {
   public static void main(String[] args) {

       int x1 = 1;
       double x2 = 0.5;
       double x3 = Math.PI;

       //using java.lang.Math.cos() for 1, 0.5 and PI rad

       System.out.println("cosine of " + x1 + " rads = " + Math.cos(x1));
       System.out.println("cosine of  " + x2 + " rads = " + Math.cos(0));
       System.out.println("cosine  " + x3 + " rads = " + Math.exp(x3));


       //here we declare an 60 degrees angle

       double degree = 60;
       //here we use Math.toRadians to convert 60 degrees to radians, use the cos() method
       //to calculate the cosine of 60 degrees angle and print the result out
       System.out.println("cosine of " + degree + " degrees = " + Math.cos(Math.toRadians(degree)));

   }
}
