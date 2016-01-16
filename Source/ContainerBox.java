import java.awt.Color;
import java.awt.Graphics;
/**
 * A rectangular container box, containing the bouncing ball.  
 * 
 * @author Hock-Chuan Chua  by Jason P'ng
 * @version June 2015
 */
public class ContainerBox {
   int minX, maxX, minY, maxY;  // Box's bounds (package access)
   boolean clicked;
   private Color colorFilled;   // Box's filled color (background)
   private Color colorBorder;   // Box's border color
   private static final Color DEFAULT_COLOR_FILLED = Color.BLACK;
   private static final Color DEFAULT_COLOR_BORDER = Color.YELLOW;
   public static final boolean OUTER = true;
   public static final boolean INNER = false;
   
   /** Constructors */
   public ContainerBox(int x, int y, int width, int height, Color colorFilled, Color colorBorder) {
      minX = x;
      minY = y;
      maxX = x + width - 1;
      maxY = y + height - 1;
      this.colorFilled = colorFilled;
      this.colorBorder = colorBorder;
   }
   /** Constructor with the default color */
   public ContainerBox(int x, int y, int width, int height) {
      this(x, y, width, height, DEFAULT_COLOR_FILLED, DEFAULT_COLOR_BORDER);
   }
   
   /** Set or reset the boundaries of the box. */
   public void set(int x, int y, int width, int height) {
      minX = x;
      minY = y;
      maxX = x + width - 1;
      maxY = y + height - 1;
   }

   public boolean contains (int x, int y)
   {
     return (x >= minX && x <= maxX && y >= minY && y <= maxY);
   }
   
   public int getHeight (){
     return maxY - minY + 1;
   }
   
   public int getWidth (){
     return maxX - minX + 1;
   }
   
   /** Draw itself using the given graphic context. */
   public void draw(Graphics g) {
      g.setColor(colorFilled);
      g.fillRect(minX, minY, maxX - minX - 1, maxY - minY - 1);
      g.setColor(colorBorder);
      g.drawRect(minX, minY, maxX - minX - 1, maxY - minY - 1);
   }
}