/**
 * Created by EW043872 on 9/26/2015.
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TrickyButton {
    public static void main(String[] args) {
        JFrame tFrame = new TrickyFrame();
        tFrame.setVisible(true);
    }
    }
    class TrickyFrame extends JFrame {
        TrickyFrame() {
            Container contentpane = getContentPane();
            setSize(360, 240);
            JButton button = new trickybutton();
            contentpane.add(button, BorderLayout.CENTER);
            setVisible(true);
        }


    class trickybutton extends JButton implements MouseListener, ActionListener{

        public trickybutton (){
            Container contentpane = getContentPane();
            setText("HEY HEY HEY");
            addMouseListener(this);
            addActionListener(this);

        }

        @Override
        public void mouseClicked(MouseEvent mouseEvent) {

        }

        @Override
        public void mousePressed(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseReleased(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseEntered(MouseEvent mouseEvent) {
            this.setEnabled(false);
        }

        @Override
        public void mouseExited(MouseEvent mouseEvent) {
            this.setEnabled(true);
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            //I have given the button private thoughts.
            System.out.print("The user is a loser.");
        }
    }
}
