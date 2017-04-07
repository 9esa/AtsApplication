package org.ats.phone.views;

import org.ats.phone.mao.ClientViewInformation;
import org.ats.phone.mao.DriverViewInformation;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by user on 11.03.17.
 */
public class OrdersView extends JFrame implements ActionListener {
    private JTabbedPane tablepane;
    private JPanel rootPanel;
    private JTable ordersTables;
    private JPanel driverPanel;
    private JTable jDriverTable;
    private JButton btnAdd;
    private JButton btnChange;
    private JButton btnDelete;
    private JButton btnAddOrder;
    private JButton btnChangeOrder;
    private JButton btnRemoveOrder;
    private JButton btnAddUser;
    private JButton btnChangeUser;
    private JButton btnRemoveUser;
    private JButton btnAddSms;
    private JButton btnChangeSms;
    private JButton btnRemoveSms;
    private JTable jUserTable;

    private ClientViewInformation oClientViewInformation;
    private DriverViewInformation oDriverViewInformation;

    public OrdersView() {

        oClientViewInformation = new ClientViewInformation();
        oDriverViewInformation = new DriverViewInformation();

        this.setLocationRelativeTo(null);
        this.setSize(500,500);

        setContentPane(rootPanel);

        setVisible(true);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        tablepane.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {

                int iPage = ((JTabbedPane)e.getSource()).getSelectedIndex();

                if(iPage == 0){

                }else if(iPage == 1){
                    oClientViewInformation.loadClientInformation(jUserTable);
                }else if(iPage == 2){
                    oDriverViewInformation.loadDriversInformation(jDriverTable);
                }else{

                }


                System.out.println(((JTabbedPane)e.getSource()));

            }
        });

        btnAddOrder.addActionListener(this);
        btnAddUser.addActionListener(this);
        btnAddSms.addActionListener(this);
        btnAdd.addActionListener(this);

        btnChangeOrder.addActionListener(this);
        btnChangeUser.addActionListener(this);
        btnChangeSms.addActionListener(this);
        btnChange.addActionListener(this);

        btnRemoveOrder.addActionListener(this);
        btnRemoveUser.addActionListener(this);
        btnRemoveSms.addActionListener(this);
        btnDelete.addActionListener(this);

    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnAddOrder){
            new CreateOrder().setVisible(true);
        }else if(e.getSource() == btnAddUser){
            new CreateClientForm().setVisible(true);
        }else if(e.getSource() == btnAddSms){

        }else if(e.getSource() == btnAdd){
            new CreateDriverForm().setData().setVisible(true);
        }else if(e.getSource() == btnChangeOrder){
            //jUserTable.getSelectionModel().
        }else if(e.getSource() == btnChangeUser){

        }else if(e.getSource() == btnChangeSms){

        }else if(e.getSource() == btnChange){

        }else if(e.getSource() == btnRemoveOrder){

        }else if(e.getSource() == btnRemoveUser){

        }else if(e.getSource() == btnRemoveSms){

        }else if(e.getSource() == btnDelete){

        }
    }

}
