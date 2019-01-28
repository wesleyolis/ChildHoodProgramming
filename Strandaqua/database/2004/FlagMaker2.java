import java.awt.*;
import java.awt.event.*;

public class FlagMaker2 extends Frame {

  /** General Flag drawing program       J M Bishop April 2000
   *  ====================
   * Works for several flags as well as different designs.
   * Illustrated inhertiance
   **/

  public FlagMaker2() {
    FlagCanvas flag;
    flag = new FlagCanvas
           ("Germany",Color.black,Color.red,Color.yellow,0);
    makeFlag(flag);
    flag = new FlagCanvas
           ("The Netherlands",Color.red,Color.white,Color.blue,1);
    makeFlag(flag);
    flag = new FlagCanvas
           ("Ethiopia",Color.green,Color.yellow,Color.red,2);
    makeFlag(flag);
  }

  public static void main(String [] args) {
     new FlagMaker2();
  }

  public static void makeFlag (FlagCanvas canvas) {
    Frame f = new Frame();
    f.add(canvas);
    f.setTitle(canvas.country);
    f.setSize(300,250);
    f.setLocation(75*canvas.flagNo,75*canvas.flagNo);
    f.setVisible(true);
    f.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        e.getWindow().dispose();
      }
    });
  }
}

//===========================
  class FlagCanvas extends Canvas {

    String country;
    Color bar1, bar2, bar3;
    int flagNo;

     FlagCanvas (String c, Color b1, Color b2, Color b3,int f) {
       country = c;
       bar1 = b1;
       bar2 = b2;
       bar3 = b3;
       flagNo = f;
     }

     public void paint (Graphics g) {
      // Draw the flag from coloured rectangles
      g.setColor (bar1);
      g.fillRect (40,40,200,40);
      g.setColor (bar2);
      g.fillRect (40,80,200,40);
      g.setColor (bar3);
      g.fillRect (40,120,200,40);
      // Label the drawing
      g.setColor (Color.black);
      g.drawString(country,100,180);
    }
  }


