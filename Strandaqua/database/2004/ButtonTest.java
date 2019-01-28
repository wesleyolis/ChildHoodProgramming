import java.awt.*;
import java.awt.event.*;

class ButtonTest extends Frame implements ActionListener {

    /* The Graphic warning Program    by J M Bishop Oct 1996
     *                         Java 1.1 by T Abbott Oct 1997
     *                         updated 1.2 by J Bishop May 2000
     * produces a warning message but when a warning
     * message is pressed, it turns the window red.
     * Illustrates buttons, listeners and the handling of events.
     */

    Button waitButton;
    Button rebootButton;

    String[] message = {
      "W A R N I N G",
      "Possible virus detected.",
      "Reboot and run virus",
      "remover software" };

    ButtonTest ( ) {
   /* The constructor is responsible for setting
    * up the initial buttons and colour background.
    */
    setBackground(Color.cyan);
    setForeground(Color.black);
    setLayout(new FlowLayout(FlowLayout.CENTER, 15, 10));
    for (int i = 0; i < message.length; i++)
      add(new Label(message[i]));
    waitButton = new Button("Wait");
      waitButton.addActionListener(this);
      add(waitButton);
    rebootButton = new Button("Reboot");
      rebootButton.addActionListener(this);
      add(rebootButton);
    setTitle("Draw Warning");
    setSize(180,200);
    setBackground(Color.cyan);
    setForeground(Color.black);
    addWindowListener(new WindowAdapter () {
      public void windowClosing(WindowEvent e) {
	System.exit(0);
      }
    });
    setVisible(true);
  }

  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == rebootButton) {
      setVisible(false);
      dispose();
      System.exit (0);
    } else
    if (e.getSource() == waitButton) {
      setForeground(Color.white);
      setBackground(Color.red);
    }
  }

  public static void main(String[] args) {
    new ButtonTest ();
  }
}
