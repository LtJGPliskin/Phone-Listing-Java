package phonelisting;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JFrame;

/**
 *
 * @author Austin Boucher
 */
public class PhoneListing extends JFrame{
    //constructor
    public PhoneListing() {
        super("Phone List");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().add(new PhonePanel());
        pack();
        setLocationRelativeTo(null);    //put the gui in the center of the screen
        setVisible(true);
    }
    
    public static void main(String[] args) {
        PhoneListing newGame = new PhoneListing(); 
    }
}

