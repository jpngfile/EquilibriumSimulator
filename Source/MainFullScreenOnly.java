import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 * Main Program for running the bouncing ball as a standalone application,
 * in Full-Screen mode (if full-screen mode is supported).
 * Use ESC Key to quit (need to handle key event). 
 * 
 * @author Hock-Chuan Chua
 * @version v0.4 (31 October 2010)
 */
public class MainFullScreenOnly extends JFrame {
   // Current display device's width & height
   private int displayWidth;  
   private int displayHeight;

   private GraphicsDevice device;  // For entering full screen mode

   /** Constructor to initialize UI */
   public MainFullScreenOnly() {
      // Find out the current display width and height
      Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
      displayWidth = dim.width;
      displayHeight = dim.height;
      
      // Get the default graphic device and try full screen mode
      device = GraphicsEnvironment.getLocalGraphicsEnvironment()
            .getDefaultScreenDevice();
      if (device.isFullScreenSupported()) { // Go for full-screen mode
         this.setUndecorated(true);         // Don't show title and border
         this.setResizable(false);
         //this.setIgnoreRepaint(true);     // Ignore OS re-paint request
         device.setFullScreenWindow(this);
      } else {    // Run in windowed mode if full screen is not supported
         this.setSize(displayWidth, displayHeight - 40); // minus task bar
         this.setResizable(true);
      }
      
      // Allocate the game panel to fill the current screen 
      BallWorld ballWorld = new BallWorld(this.getWidth(), this.getHeight());
      this.setContentPane(ballWorld); // Set as content pane for this JFrame

      // To handle key events
      this.addKeyListener(new KeyAdapter() {
         @Override
         public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch (keyCode) {
            case KeyEvent.VK_ESCAPE:   // ESC to quit
               System.exit(0);
               break;
            }
         }
      });
      this.setFocusable(true);  // To receive key event

      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setTitle("A World of Balls");
      this.pack();            // Pack to preferred size
      this.setVisible(true);  // Show it
   }
   
   /** Entry main program */
   public static void main(String[] args) {
      // Run UI in the Event Dispatcher Thread (EDT), instead of Main thread
      javax.swing.SwingUtilities.invokeLater(new Runnable() {
         public void run() {
            new MainFullScreenOnly();
         }
      });
   }
}