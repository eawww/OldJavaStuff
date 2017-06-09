/**
 * Created by EW043872 on 9/19/2015.
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class PhotoViewer extends JFrame implements ActionListener {

    JButton prev = null;
    JButton next = null;
    int imageNum = 1;
    JTextArea photoNumber = new JTextArea(Integer.toString(imageNum), 1, 2);

    public PhotoViewer() {
        Container contentPane = getContentPane();
        BorderLayout bottomBorderLayout = new BorderLayout();
        BorderLayout infoBorderLayout = new BorderLayout();

        //Create and Populate infoLabel
        JPanel infoLabel = new JPanel(new BorderLayout());
        JLabel descriptionLabel = new JLabel("Description: ");
        JLabel dateLabel = new JLabel("Date:");
        infoLabel.add(descriptionLabel, BorderLayout.NORTH);
        infoLabel.add(dateLabel, BorderLayout.SOUTH);

        //Create and populate infoDisplay
        JPanel infoDisplay = new JPanel(new BorderLayout());
        JTextArea description = new JTextArea("INFO INFO INFO", 3, 1);
        JTextArea dateText = new JTextArea("DATE DATE DATE");
        infoDisplay.add(description, BorderLayout.CENTER);
        infoDisplay.add(dateText, BorderLayout.SOUTH);

        //Create and populate infoPanel
        JPanel infoPanel = new JPanel(new BorderLayout());
        infoPanel.add(infoLabel, BorderLayout.WEST);
        infoPanel.add(infoDisplay, BorderLayout.CENTER);

        //Create and populate ctrlPnl
        JPanel ctrlPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        //JTextArea photoNumber = new JTextArea(Integer.toString(imageNum), 1, 2);
        JLabel totalPhotos = new JLabel(" of 5 ");
        prev = new JButton("<Prev");
        prev.addActionListener(this);
        next = new JButton("Next>");
        next.addActionListener(this);
        ctrlPanel.add(photoNumber);
        ctrlPanel.add(totalPhotos);
        ctrlPanel.add(prev);
        ctrlPanel.add(next);

        //Create and populate bottomPanel
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(infoPanel, BorderLayout.CENTER);
        bottomPanel.add(ctrlPanel, BorderLayout.SOUTH);

        //Create and populate imgPanel
        JLabel imgLabel = new JLabel("", SwingConstants.CENTER);
        JScrollPane scrollPane = new JScrollPane(imgLabel);

        ImageIcon image = new ImageIcon("C:\\Users\\EW043872\\IdeaProjects\\PhotoViewer\\src\\image.jpg");
        imgLabel.setIcon(image);


        //Add imgPanel and bottomPanel to contentPane
        contentPane.add(scrollPane, BorderLayout.CENTER);
        contentPane.add(bottomPanel, BorderLayout.SOUTH);
    }

    public void actionPerformed(ActionEvent evt){
        if (evt.getSource() == prev){
            if (imageNum <= 1){ //reaches beginning of photoset
                prev.setEnabled(false);
            }
            else { //decrementing counter
                imageNum--;
                photoNumber.setText(Integer.toString(imageNum));
                if (imageNum == 1) {
                    prev.setEnabled(false);
                }
            }
            if (imageNum < 5) //enable next button if we're no longer at the end.
                next.setEnabled(true);

        }
        else if (evt.getSource() == next){
            if(imageNum >= 5){//reaches end of photoset
                next.setEnabled(false);
            }
            else {//incrementing counter
                imageNum++;
                photoNumber.setText(Integer.toString(imageNum));
                if (imageNum == 5){
                    next.setEnabled(false);
                }
            }
            if (imageNum > 1) //enable prev button if we're no longer at the beginning
                prev.setEnabled(true);
        }
    }

    public static void main(String[] args){
        JFrame frame = new PhotoViewer();
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e){
                System.exit(0);
            }
        });
        frame.setSize(600, 400);
        frame.setVisible(true);

    }
}
