package GUI;

import Constractor.ConsgnmentModel;
import Constractor.Record;
import Database.RecordDB;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Date;
import java.util.Vector;

/**
 * Created by mash4 on 4/30/2017.
 */



public class ConsgnmentGUI extends JFrame implements WindowListener {
    private JPanel rootPanel;
    private JTextField consignorNametextField;
    private JTextField saletextField;
    private JTextField itemstextField;
    private JTable consgnmenttable;
    private JButton addRecordButton;
    private JButton saleItemButton;

    static int items;
    static double salePrx;
    Vector<Record> recordVector;

    public ConsgnmentGUI(ConsgnmentModel consM) {
        setTitle("Consignment Project !!!!!!");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(rootPanel);
        pack();
        addWindowListener(this);
        setVisible(true);

       consgnmenttable.setModel(consM);
        //
        consgnmenttable.setGridColor(Color.BLACK);
        consgnmenttable.getColumnModel().getColumn(0).setPreferredWidth(300);


        addRecordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = consignorNametextField.getText();
                if (name.trim().length() == 0) {
                    JOptionPane.showMessageDialog(ConsgnmentGUI.this, "Please enter consignor name ");
                    return;

                }

                try {
                    items = Integer.parseInt(itemstextField.getText());
                    if (items < 0) {
                        JOptionPane.showMessageDialog(ConsgnmentGUI.this,
                                "Enter a positive number !!");
                        return;


                    }


                } catch (NumberFormatException nf) {
                    JOptionPane.showMessageDialog(ConsgnmentGUI.this, "Please enter a positive number ");
                }

                try {
                    salePrx = Double.parseDouble(saletextField.getText());
                    if (salePrx < 0) {
                        JOptionPane.showMessageDialog(ConsgnmentGUI.this, "Sale price cant be less than ZERO");
                        return;
                    }
                } catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(ConsgnmentGUI.this, "Please enter a positive number !!!");
                }
                /*
                for(Record rdb : recordVector){
                    if(rdb.getName().equalsIgnoreCase(name)){

                        items += items;
                        salePrx += salePrx;
                    }

                }
                */
                Date date = new Date();

                Record record = new Record(name, items, salePrx, date);
                RecordDB rd = new RecordDB();
                rd.createNewRecordTable();
                rd.addNewRecords(record);
                //recordVector.addElement(rd);

                //Clears all entries
                consignorNametextField.setText("");
                itemstextField.setText("");
                saletextField.setText("");


            }
        });
        saleItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(consgnmenttable.getSelectedColumn()== -1){
                    JOptionPane.showMessageDialog(ConsgnmentGUI.this, "Select an item to sell !!");
                    return;
                }

                RecordDB rd = new RecordDB();
                rd.createSaleTable();
                rd.saleItems();
            }
        });
    }


    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}

