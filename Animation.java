import java.awt.*;


public class Animation
{
   private Image[] image;

   private int current = 0;

   private int duration;
   private int countdown;



   public Animation(String name, int count, int duration)
   {
      image = new Image[count];

      for(int i = 0; i < count; i++)

         image[i] = Toolkit.getDefaultToolkit().getImage(name + i + ".gif");

      this.duration = duration;

      countdown = duration;
   }


   public Image nextImage()
   {
      countdown--;

      if(countdown == 0)
      {
         countdown = duration;

         current++;

         if(current == image.length)   current = 1;
      }


      return image[current];
   }


   public Image staticImage()
   {
      return image[0];
   }




}