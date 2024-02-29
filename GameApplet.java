//----------------------------------------------------------------------------//
// Copyright - Brian Murphy - 2004                                            //
//----------------------------------------------------------------------------//
//----------------------------------------------------------------------------//
// Abstract class providing a framework for Applet based Video Games          //
//----------------------------------------------------------------------------//
// Does away with the need to recode many of the features commonly required   //
// by video games (still lots more to add)                                    //
//----------------------------------------------------------------------------//

import java.awt.*;
import java.awt.event.*;
import java.applet.Applet;

//----------------------------------------------------------------------------//

public abstract class GameApplet extends Applet implements KeyListener, Runnable
{
   //-------------------------------------------------------------------------//


   boolean[] input = new boolean[1024];


   // Number of miliseconds to wait between frames (default = 15)
   // Can be altered in the setDuration method
   private int duration = 15;

   // Color to use for screen clearing
   Color background = Color.white;

   // Offsreen image and its Graphics Context
   Image    offscreen_image;
   Graphics offscreen_g;

   // Booleans for pausing and ending the Game Loop
   boolean paused   = false;
   boolean finished = false;

   // The thread in which the Game Loop will execute
   Thread timer;

   //-------------------------------------------------------------------------//

   public final static int UP = KeyEvent.VK_UP;
   public final static int DN = KeyEvent.VK_DOWN;
   public final static int LT = KeyEvent.VK_LEFT;
   public final static int RT = KeyEvent.VK_RIGHT;

   public final static int CTRL   = KeyEvent.VK_CONTROL;
   public final static int SHIFT  = KeyEvent.VK_SHIFT;
   public final static int COMMA  = KeyEvent.VK_COMMA;
   public final static int PERIOD = KeyEvent.VK_PERIOD;

   public final static int _A = KeyEvent.VK_A;
   public final static int _B = KeyEvent.VK_B;
   public final static int _C = KeyEvent.VK_C;
   public final static int _D = KeyEvent.VK_D;
   public final static int _E = KeyEvent.VK_E;
   public final static int _F = KeyEvent.VK_F;
   public final static int _G = KeyEvent.VK_G;
   public final static int _H = KeyEvent.VK_H;
   public final static int _I = KeyEvent.VK_I;
   public final static int _J = KeyEvent.VK_J;
   public final static int _K = KeyEvent.VK_K;
   public final static int _L = KeyEvent.VK_L;
   public final static int _M = KeyEvent.VK_M;
   public final static int _N = KeyEvent.VK_N;
   public final static int _O = KeyEvent.VK_O;
   public final static int _P = KeyEvent.VK_P;
   public final static int _Q = KeyEvent.VK_Q;
   public final static int _R = KeyEvent.VK_R;
   public final static int _S = KeyEvent.VK_S;
   public final static int _T = KeyEvent.VK_T;
   public final static int _U = KeyEvent.VK_U;
   public final static int _V = KeyEvent.VK_V;
   public final static int _W = KeyEvent.VK_W;
   public final static int _X = KeyEvent.VK_X;
   public final static int _Y = KeyEvent.VK_Y;
   public final static int _Z = KeyEvent.VK_Z;

   public final static int _0 = KeyEvent.VK_0;
   public final static int _1 = KeyEvent.VK_1;
   public final static int _2 = KeyEvent.VK_2;
   public final static int _3 = KeyEvent.VK_3;
   public final static int _4 = KeyEvent.VK_4;
   public final static int _5 = KeyEvent.VK_5;
   public final static int _6 = KeyEvent.VK_6;
   public final static int _7 = KeyEvent.VK_7;
   public final static int _8 = KeyEvent.VK_8;
   public final static int _9 = KeyEvent.VK_9;

   //-------------------------------------------------------------------------//
   // Called in the init method which is final in this class, so that         //
   // can add functionality to the init method without the abiltiy to         //
   // disrupt the functionality already build in                              //
   //-------------------------------------------------------------------------//

   public abstract void initialize();// {}

   //-------------------------------------------------------------------------//
   // GameApplets init method.  Preps required game features and allows for   //
   // extention through the initialize method.                                //
   //-------------------------------------------------------------------------//

   public final void init()
   {
      // Color to use for clearing screen
      // Only needed if full screen not covered
      setBackground(background);

      // Setup Offscreen Buffer
      setupDoubleBuffering();


      // Abstract Method defined in Subclasses
      initialize();


      // Aquire the focus for the Applet so keypresses are accepted
      requestFocus();

      // Attach the KeyListener to the Applet in order to monitor keypresses
      addKeyListener(this);

      // Create the thread for the main loop
      timer = new Thread(this);
   }

   //-------------------------------------------------------------------------//

   public void preGameLoop () {}

   //-------------------------------------------------------------------------//

   public abstract void respondToInput();

   //-------------------------------------------------------------------------//

   public abstract void moveGameObjects();

   //-------------------------------------------------------------------------//

   public abstract void handleCollisions();

   //-------------------------------------------------------------------------//
   // The main loop of the program.                                           //
   // Executed once every milisecond (default) in a seperate thread.          //
   //-------------------------------------------------------------------------//
   // Each iteration:                                                         //
   // Moves the on screen game objects to their next positions.               //
   // Does collision detection which does nothing at the moment.              //
   // Repaints the the visuals for each object on the court.                  //
   // Waits before repeating loop to allow for controlled game speed.         //
   //-------------------------------------------------------------------------//

   public final void inGameLoop()
   {
      respondToInput();

      moveGameObjects();

      handleCollisions();
   }

   //-------------------------------------------------------------------------//

   public void postGameLoop() {}

   //-------------------------------------------------------------------------//

   public final void run()
   {
      preGameLoop();

      while ( !finished )
      {
         if ( !paused )  inGameLoop();

         repaint();

         sleep(duration);
      }

      postGameLoop();

      System.exit(0);
   }

   //-------------------------------------------------------------------------//

   public final void keyPressed(KeyEvent e)
   {
      int code = e.getKeyCode();

      if (code == _P)  paused   = true;
      if (code == _C)  paused   = false;

      if (code == _Q)  finished = true;

      input[code] = true;
   }

   //-------------------------------------------------------------------------//

   public final void keyReleased(KeyEvent e)
   {
      int code = e.getKeyCode();
      input[code] = false;
   }

   //-------------------------------------------------------------------------//

   public final void keyTyped(KeyEvent e) {}

   //-------------------------------------------------------------------------//
   // Applets call their start method after their init method                 //
   //-------------------------------------------------------------------------//

   public final void start()
   {
      timer.start();
   }

   //-------------------------------------------------------------------------//

   public final void stop()
   {
      finished = true; // timer.stop(); Deprecated
   }

   //-------------------------------------------------------------------------//

   public final void setDuration(int duration)
   {
      this.duration = duration;
   }

   //-------------------------------------------------------------------------//
   // Put the thread into a sleep state for a given number of miliseconds.    //
   //-------------------------------------------------------------------------//


   public final void sleep(int miliseconds)
   {
      try
      {
         timer.sleep(miliseconds);
      }
      catch(InterruptedException e)
      {
      }
   }

   //-------------------------------------------------------------------------//

   public final void setupDoubleBuffering()
   {
       offscreen_image = createImage(getWidth(), getHeight());

       offscreen_g     = offscreen_image.getGraphics();
   }

   //-------------------------------------------------------------------------//

   public final void update(Graphics g)
   {
      offscreen_g.clearRect(0, 0, getWidth(), getHeight());

      paint(offscreen_g);

      g.drawImage(offscreen_image, 0, 0, null);
   }

   //-------------------------------------------------------------------------//

   public final void repaint()
   {
      Graphics g = getGraphics();

      update(g);

      g.dispose();
   }

   //-------------------------------------------------------------------------//

}

//----------------------------------------------------------------------------//
