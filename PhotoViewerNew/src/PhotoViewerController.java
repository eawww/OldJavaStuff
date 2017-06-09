/**
 * Created by EW043872 on 10/4/2015.
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class PhotoViewerController implements ActionListener {
    int currentImage = 0;

    static PhotoViewerLayout photoView = new PhotoViewerLayout();
    static PhotoCollection collection = new PhotoCollection();
    int maxImages = collection.getCollectionSize();
    private final JFileChooser fc = new JFileChooser();

    public PhotoViewerController(){
        readCollection();
        maxImages = collection.getCollectionSize();
        if(maxImages <= 0){
            photoView.disableNext();
            photoView.disablePrev();
        }
        if(maxImages == 1){
            photoView.disableNext();
        }
        photoView.updatePhotoCount(maxImages);
        photoView.updatePhotoNum(currentImage + 1);
        System.out.print(collection.getCollectionSize());
        maxImages = collection.getCollectionSize();
        photoView.disablePrev();
        if (currentImage > 0) {
            photoView.disableNext();
            photoView.disableDelete();
        }
        else if (collection.getCollectionSize() > 0){
            currentImage = 1;
            collection.setCurrentPhotoNum(currentImage);
            collection.run();
            try {
                collection.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            photoView.updateDisplayPhoto(collection.getPhoto());
            photoView.setDescField(collection.getDescription());
            photoView.setDateField(collection.getDate());
        }


        photoView.deleteAddActionListener(new ActionListener() {        //delete action
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                collection.deletePhoto(currentImage);
                maxImages--;
                photoView.updatePhotoCount(maxImages);
                if(currentImage == maxImages + 1 && maxImages > 0){ //deleting last image in list
                    currentImage--;
                    photoView.updatePhotoNum(currentImage);
                    if(currentImage == 1){
                        photoView.disablePrev();
                    }
                    collection.setCurrentPhotoNum(currentImage);
                    collection.run();
                    try {
                        collection.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    photoView.updateDisplayPhoto(collection.getPhoto());
                    photoView.setDescField(collection.getDescription());
                    photoView.setDateField(collection.getDate());
                    photoView.disableNext();
                }
                else if (maxImages == 0){ //deleting last image altogether
                    photoView.disablePrev();
                    photoView.disableNext();
                    photoView.disableDelete();
                }
                else {
                    collection.setCurrentPhotoNum(currentImage);
                    collection.run();
                    try {
                        collection.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    photoView.updateDisplayPhoto(collection.getPhoto());
                    photoView.setDescField(collection.getDescription());
                    photoView.setDateField(collection.getDate());
                }
                photoView.updatePhotoNum(currentImage);
                /*if (currentImage == maxImages - 1 && maxImages > 1) {
                    photoView.updateDisplayPhoto(collection.getPhoto(currentImage));
                    maxImages = collection.getCollectionSize();
                    photoView.updatePhotoCount(maxImages);
                    photoView.updatePhotoNum(currentImage + 1);
                } else if (collection.getCollectionSize() == 0) { //if we're deleting the last photo,
                    photoView.updateDisplayPhoto(new Photograph());
                    photoView.disableDelete();
                    maxImages = collection.getCollectionSize();
                    photoView.updatePhotoCount(maxImages);
                    photoView.disableNext();
                    photoView.disablePrev();
                    photoView.updatePhotoNum(currentImage + 1);
                } else {
                    photoView.updateDisplayPhoto(collection.getPhoto(currentImage));
                    maxImages = collection.getCollectionSize();
                    photoView.updatePhotoCount(maxImages);
                    photoView.updatePhotoNum(currentImage + 1);
                }
                if (currentImage == 0) {
                    photoView.disablePrev();
                    if (maxImages == 1)
                        photoView.disableNext();
                }*/

            }
        });

        photoView.addAddActionListener(new ActionListener() {           //add action
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFileChooser chooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter(
                        "Images", "jpg", "gif", "bmp", "png");
                fc.setFileFilter(filter);
                int returnVal = fc.showOpenDialog(fc);
                BufferedImage img = null;
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    try {
                        img = ImageIO.read(file);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    ImageIcon image = new ImageIcon(img);
                    collection.addPhoto(file, currentImage + 1);
                    maxImages = collection.getCollectionSize();
                    photoView.updatePhotoCount(maxImages);
                }
                photoView.enableDelete();
                photoView.enableNext();

                if(maxImages == 1){
                    currentImage = 1;
                    photoView.disableNext();
                    photoView.disablePrev();
                    collection.setCurrentPhotoNum(currentImage);
                    collection.run();
                    try {
                        collection.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    photoView.updateDisplayPhoto(collection.getPhoto());
                    photoView.setDescField(collection.getDescription());
                    photoView.setDateField(collection.getDate());
                }
            }
        });

        photoView.saveAddActionListener(new ActionListener() {          //save action
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                collection.saveData(photoView.getDescField(), photoView.getDateField());
            }
        });

        photoView.nextAddActionListener(new ActionListener() {          //next button
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                currentImage++;
                photoView.enablePrev();
                if (currentImage == maxImages) photoView.disableNext();
                collection.setCurrentPhotoNum(currentImage);
                collection.run();
                try {
                    collection.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                photoView.updateDisplayPhoto(collection.getPhoto());
                photoView.setDescField(collection.getDescription());
                photoView.setDateField(collection.getDate());
                photoView.updatePhotoCount(maxImages);
                photoView.updatePhotoNum(currentImage);

                System.out.print(photoView.getDescField());
            }


        });

        photoView.prevAddActionListener(new ActionListener() {         //prev button
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                currentImage--;
                photoView.enableNext();
                if (currentImage == 1) photoView.disablePrev();
                collection.setCurrentPhotoNum(currentImage);
                collection.run();
                try {
                    collection.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                photoView.updateDisplayPhoto(collection.getPhoto());
                photoView.setDescField(collection.getDescription());
                photoView.setDateField(collection.getDate());
                photoView.updatePhotoCount(maxImages);
                photoView.updatePhotoNum(currentImage);

                System.out.print(photoView.getDescField());
            }
        });

        photoView.browseAddActionListener(new ActionListener() {       //browse menu
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                photoView.disableMaint();
            }
        });

        photoView.maintAddActionListener(new ActionListener() {         //maint menu
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                photoView.enableMaint();
            }
        });

        photoView.goToAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int ind = currentImage;

                try {
                    ind = photoView.getPictureNumber();
                } catch (NumberFormatException n){
                    photoView.updatePhotoNum(currentImage);
                }

                if (ind <= maxImages && ind > 0){
                    //change image and data
                    currentImage = ind;
                    collection.setCurrentPhotoNum(currentImage);
                    collection.run();
                    try {
                        collection.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    photoView.updateDisplayPhoto(collection.getPhoto());
                    photoView.setDescField(collection.getDescription());
                    photoView.setDateField(collection.getDate());
                    photoView.updatePhotoNum(currentImage);

                    //Manage buttons and stuff.
                    if (ind == 1){
                        photoView.disablePrev();
                        photoView.enableNext();
                    }
                    else if (ind == maxImages){
                        photoView.disableNext();
                        photoView.enablePrev();
                    }
                    else{
                        photoView.enablePrev();
                        photoView.enableNext();
                    }
                }
                else{
                    photoView.updatePhotoNum(currentImage);
                }

            }
        });

    }

    public JFrame getFrame(){
        return photoView;
    }

    private void readCollection(){

        /*try {
            FileInputStream fileIn = new FileInputStream("collection.ser");
            ObjectInputStream ObjIn = new ObjectInputStream(fileIn);
            collection = (PhotoCollection)ObjIn.readObject();
        } catch (FileNotFoundException e) {
            System.out.print("FILE IN NOT FOUND!");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }*/
    }

    /*private void writeCollection(){
        try {
            FileOutputStream fileOut = new FileOutputStream("collection.ser");
            ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
            objOut.writeObject(collection);
            objOut.close();
            System.out.print("Collection serialized to \"collection.ser\"");
        } catch (IOException i) {
            System.out.print("FILE OUT NOT FOUND\n");
        }
    }*/
    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }
    public static void main(String[] args) {
        JFrame frame = new PhotoViewerController().getFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
