import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.*;

public class PhotoViewerLayout extends JFrame{

    JButton deleteButton = new JButton("Delete");
    JButton saveButton = new JButton("Save Data");
    JButton addButton = new JButton("Add Photo");
    JButton prevButton = new JButton("<prev");
    JButton nextButton = new JButton("next>");
    ImageIcon image = new ImageIcon();
    JLabel imageLabel = new JLabel("", SwingConstants.CENTER);
    JMenuItem browseMode = new JMenuItem("Browse Mode");
    JMenuItem maintMode = new JMenuItem("Maintenance Mode");
    JTextField pictureNumberTextField = new JTextField("3",4);
    JLabel pictureCountLabel = new JLabel(" of 3");
    JTextField dateTextField = new JTextField("1/1/2014");
    JTextArea descriptionTextArea = new JTextArea("jklkjhkl",4,20);
    JButton goToButton = new JButton("Go To");


    public PhotoViewerLayout() {

        Container contentPane = getContentPane();
        descriptionTextArea.setEditable(true);
        JScrollPane scrollPane = new JScrollPane(imageLabel);

        JMenuBar menuBar = new JMenuBar();                                     //menu
        setJMenuBar(menuBar);
        JMenu viewMenu = new JMenu("View");
        menuBar.add(viewMenu);

        viewMenu.add(browseMode);

        viewMenu.add(maintMode);


        imageLabel.setIcon(image);

        contentPane.add(scrollPane, BorderLayout.CENTER);

        JPanel controlPane = new JPanel();
        controlPane.setLayout(new BoxLayout(controlPane, BoxLayout.PAGE_AXIS));

        JPanel descriptionPane = new JPanel();
        descriptionPane.setLayout(new FlowLayout(FlowLayout.LEFT));

        JLabel descriptionLabel = new JLabel("Description:");
        descriptionPane.add(descriptionLabel);
        descriptionPane.add(descriptionTextArea);

        JPanel datePane = new JPanel();

        JLabel dateLabel = new JLabel("Date:");
        dateLabel.setPreferredSize(new Dimension(descriptionLabel.getPreferredSize().width, dateLabel.getPreferredSize().height));

        datePane.add(dateLabel);
        datePane.add(dateTextField);
        JPanel buttonPane = new JPanel();

        buttonPane.add(deleteButton);
        buttonPane.add(saveButton);
        buttonPane.add(addButton);


        JPanel leftRightPane = new JPanel();
        leftRightPane.setLayout(new BorderLayout());
        leftRightPane.add(datePane,BorderLayout.WEST);
        leftRightPane.add(buttonPane,BorderLayout.EAST);


        JPanel southButtonPanel = new JPanel();



        southButtonPanel.add(pictureNumberTextField);
        southButtonPanel.add(pictureCountLabel);
        southButtonPanel.add(prevButton);
        southButtonPanel.add(nextButton);
        southButtonPanel.add(goToButton);
        FlowLayout flowLayout = (FlowLayout) southButtonPanel.getLayout();
        flowLayout.setAlignment(FlowLayout.LEFT);


        controlPane.add(descriptionPane);
        controlPane.add(leftRightPane);
        controlPane.add(southButtonPanel);

        contentPane.add(controlPane, BorderLayout.SOUTH); // Or PAGE_END
    }

    public void updateDisplayPhoto (Photograph photo){
        image = photo.getImage();
        imageLabel.setIcon(image);

    }
    public void updateDisplayPhotoTest (ImageIcon photo){
        image = photo;
        imageLabel.setIcon(image);

    }

    public void updateDescription(String desc){
        descriptionTextArea.setText(desc);
    }

    public void disablePrev(){prevButton.setEnabled(false);}

    public void enablePrev(){prevButton.setEnabled(true);}

    public void disableNext(){nextButton.setEnabled(false);}

    public void enableNext(){nextButton.setEnabled(true);}

    public void disableDelete() {deleteButton.setEnabled(false);}

    public void enableDelete() {deleteButton.setEnabled(true);}

    public void enableMaint(){
        deleteButton.setEnabled(true);
        addButton.setEnabled(true);
        saveButton.setEnabled(true);
    }

    public void disableMaint(){
        deleteButton.setEnabled(false);
        addButton.setEnabled(false);
        saveButton.setEnabled(false);
    }

    public void updatePhotoCount(int count) {
        pictureCountLabel.setText(Integer.toString(count));
    }

    public void updatePhotoNum(int count) {
        pictureNumberTextField.setText(Integer.toString(count));
    }

    public void deleteAddActionListener(ActionListener al){
        deleteButton.addActionListener(al);
    }

    public void addAddActionListener(ActionListener al){ addButton.addActionListener(al); }

    public void saveAddActionListener(ActionListener al){ saveButton.addActionListener(al); }

    public void prevAddActionListener(ActionListener al){ prevButton.addActionListener(al); }

    public void nextAddActionListener(ActionListener al){ nextButton.addActionListener(al); }

    public void browseAddActionListener(ActionListener al){ browseMode.addActionListener(al);}

    public void maintAddActionListener(ActionListener al){ maintMode.addActionListener(al);}

    public void goToAddActionListener(ActionListener al){ goToButton.addActionListener(al);}

    public String getDescField(){
        return descriptionTextArea.getText();
    }

    public void setDescField(String string){
        descriptionTextArea.setText(string);
    }

    public void setDateField(String newDate){dateTextField.setText(newDate);System.out.print("NEWDATE: " + newDate + "\n");}

    public String getDateField(){return dateTextField.getText();}

    public int getPictureNumber() throws NumberFormatException{
        String fieldValue = pictureNumberTextField.getText();

        return Integer.parseInt(fieldValue);
    }
}