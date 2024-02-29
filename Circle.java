import java.awt.*;

public class Circle
{
   double x;
   double y;

   int r;

   int angle;



   public Circle(double x, double y, int r, int angle)
   {
      this.x = x;
      this.y = y;

      this.r = r;

      this.angle = angle;
   }


   
  public void moveForwardBy(int d)
  {
     x += d * Lookup.cos[angle];
     y += d * Lookup.sin[angle];

  }

  public void moveBackwardBy(int d)
  {
     x -= d * Lookup.cos[angle];
     y -= d * Lookup.sin[angle];

  }

  public void rotateLeftBy(int degrees)
  {
     angle -= degrees;

     if (angle < 0)  angle += 360;
  }

  public void rotateRightBy(int degrees)
  {
     angle += degrees;

     if(angle > 359)  angle-= 360;
  }


  public void moveBy(int dx, int dy)
  {
      x += dx;

      y += dy;
  }



   public void draw(Graphics g)
   {
      g.setColor(Color.lightGray);
      g.fillOval((int)x-r, (int)y-r, 2*r, 2*r);


      g.setColor(Color.black);
      g.drawOval((int)x-r, (int)y-r, 2*r, 2*r);

      g.drawLine((int)x, (int)y, (int)(r * Lookup.cos[angle] + x), (int)(r * Lookup.sin[angle] + y));
   }

}