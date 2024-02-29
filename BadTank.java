public class BadTank extends Tank
{

   public BadTank(double x, double y, int angle)
   {
      super(x, y, angle);
   }

   public void track(PolygonModel2D p)
   {
       if(leftOrRight((int)p.x, (int)p.y) > 0)   rotateLeftBy(2);
       if(leftOrRight((int)p.x, (int)p.y) < 0)   rotateRightBy(2);
   }


   public void chase(PolygonModel2D p)
   {
       track(p);

       if(distanceTo((int)p.x, (int)p.y) > 20)   moveForwardBy(2);
   }



   public double leftOrRight(int x, int y)
   {
      return (x - this.x) * Lookup.sin[A] - (y - this.y) * Lookup.cos[A];
   }


   public double distanceTo(int x, int y)
   {
      return (x - this.x) * Lookup.cos[A] + (y - this.y) * Lookup.sin[A];
   }
}
