public class BadCircle extends Circle
{
   public BadCircle(double x, double y, int r, int angle)
   {
      super(x, y, r, angle);
   }


   public double leftOrRight(int x, int y)
   {
      return (x - this.x) * Lookup.sin[angle] - (y - this.y) * Lookup.cos[angle];
   }

   public double distanceTo(int x, int y)
   {
      return (x - this.x) * Lookup.cos[angle] + (y - this.y) * Lookup.sin[angle];
   }



}