


public class BattleLord extends Sprite
{

   private static final String[] action =
   {
      "up",
      "dn",
      "lt",
      "rt",
   };


   public BattleLord(int x, int y)
   {
      super(x, y, 110, 115, "bl", 5, 10, action);
   }

}