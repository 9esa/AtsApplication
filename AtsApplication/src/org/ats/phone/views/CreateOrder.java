package org.ats.phone.views;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.event.*;

public class CreateOrder extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JDateChooser jDatePicker;
    private JCheckBox деньПоставкиCheckBox;
    private JCheckBox вМоментРаспределенияCheckBox;
    private JList list1;
    private JLabel jNumberRequest;
    private JButton изменитьКлиентаButton;

    public CreateOrder() {

        this.setSize(650,250);

        setResizable(false);

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        CreateOrder dialog = new CreateOrder();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
