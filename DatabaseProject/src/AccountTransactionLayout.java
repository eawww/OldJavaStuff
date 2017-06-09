// This code gives the layout for the UI and
//   demonstrates two ways of updating the data
//   in a JTable.
// Another option to consider when using JTable is
//   creating your own data model by overriding
//   AbstractTableModel. You might use this option
//   if data for table was coming from say a DB.
//   One example: http://www.java2s.com/Code/Java/Swing-JFC/CreatingsimpleJTableusingAbstractTableModel.htm

import java.awt.*;

import java.awt.event.ActionListener;

import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class AccountTransactionLayout extends JFrame {

    private JTextField fromField = new JTextField("3",8);
    private JTextField toField = new JTextField("4",8);
    private JTextField amountField = new JTextField("100",8);

    JButton clearButton = new JButton("Clear");
    JButton transferButton = new JButton("Transfer");


    private JTable table;
    private Vector<String> columnNames = new Vector();

    JFrame frame = this;

    public AccountTransactionLayout(Vector<Vector<Object>> results) {
        //DefaultTableModel dtm = tabMod;
        columnNames.add("Accout ID");
        columnNames.add("Account Name");
        columnNames.add("Balance");

        table = new JTable(results, columnNames );
        Container contentPane = getContentPane();
        contentPane.setLayout(new GridBagLayout());


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        // The default size of a JTable is something like
        // 450 X 400.
        Dimension smallerSize = new Dimension(450, 50);
        table.setPreferredScrollableViewportSize(smallerSize );

        JScrollPane scrollPaneForTable = new JScrollPane(table);

        GridBagConstraints constraints = new GridBagConstraints();

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.gridheight = 1;
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.insets = new Insets(4, 4, 4, 4);
        constraints.fill = GridBagConstraints.BOTH;

        contentPane.add(scrollPaneForTable,constraints);

        constraints.gridx = 0;
//		constraints.gridy = 1;
        constraints.weighty = 0;
        constraints.gridy = GridBagConstraints.RELATIVE;
        constraints.insets = new Insets(2, 4, 2, 4);
        constraints.fill = GridBagConstraints.NONE;
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.NORTHEAST;
        JLabel toLabel = new JLabel("From:");
        contentPane.add(toLabel,constraints);

        constraints.gridx = 1;
        constraints.anchor = GridBagConstraints.NORTHWEST;

        // Workaround, because of: http://bugs.java.com/bugdatabase/view_bug.do?bug_id=4247013
        fromField.setMinimumSize(fromField.getPreferredSize());
        contentPane.add(fromField,constraints);

        constraints.gridx = 0;
//		constraints.gridy = 2;
        constraints.anchor = GridBagConstraints.NORTHEAST;
        JLabel fromLabel = new JLabel("To:");
        contentPane.add(fromLabel,constraints);

        constraints.gridx = 1;
//		constraints.gridy = 2;
        constraints.anchor = GridBagConstraints.NORTHWEST;

        toField.setMinimumSize(toField.getPreferredSize());
        contentPane.add(toField,constraints);

        constraints.gridx = 0;
//		constraints.gridy = 2;
        constraints.anchor = GridBagConstraints.NORTHEAST;
        JLabel amountLabel = new JLabel("Amount:");
        contentPane.add(amountLabel,constraints);

        constraints.gridx = 1;
//		constraints.gridy = 2;
        constraints.anchor = GridBagConstraints.NORTHWEST;

        amountField.setMinimumSize(amountField.getPreferredSize());
        contentPane.add(amountField,constraints);

        constraints.gridx = 0;
//		constraints.gridy = 2;
        constraints.anchor = GridBagConstraints.NORTHEAST;

        contentPane.add(clearButton,constraints);
        // ATTENTION!!! The action here is just another
        //   example of how to update JTable. It is
        //   certainly not the logic for clearing the
        //   values in the GUI.




        constraints.gridx = 1;
//		constraints.gridy = 2;
        constraints.anchor = GridBagConstraints.NORTHWEST;

        contentPane.add(transferButton,constraints);

        frame.pack();
        frame.setVisible(true);
    }

    public void transferAddActionListener(ActionListener al){transferButton.addActionListener(al);}
    public void clearAddActionListener(ActionListener al){clearButton.addActionListener(al);}
    public void clearInputFields(){fromField.setText(""); toField.setText(""); amountField.setText("");}
    public int getfromNum(){
        return Integer.parseInt(fromField.getText());
    }
    public int getToNum(){
        return Integer.parseInt(toField.getText());
    }
    public int getAmount(){
        return Integer.parseInt(amountField.getText());
    }
   public void updateWholeTable(Vector<Vector<Object>> newVector){
       DefaultTableModel newModel = new DefaultTableModel(newVector, columnNames);
       table.setModel(newModel);
       table.updateUI();
    }
}