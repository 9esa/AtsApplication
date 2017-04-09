package org.ats.phone.views;

import com.toedter.calendar.JDateChooser;
import org.ats.phone.mao.ClientViewInformation;
import org.ats.phone.mao.DriverViewInformation;
import org.ats.phone.mao.OrderViewInformation;
import org.ats.phone.mao.SmsViewInformation;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;

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
    private JTable jSmsTable;
    private JDateChooser jDateOrder;
    private JCheckBox cbEnableDriver;
    private JComboBox cbDrivers;

    private OrderViewInformation oOrderViewInformation;
    private ClientViewInformation oClientViewInformation;
    private DriverViewInformation oDriverViewInformation;
    private SmsViewInformation oSmsViewInformation;
    private static OrdersView   instance;
    public int                  row;

    public OrdersView() {

        oOrderViewInformation = new OrderViewInformation();
        oClientViewInformation = new ClientViewInformation();
        oDriverViewInformation = new DriverViewInformation();
        oSmsViewInformation = new SmsViewInformation();
        this.setLocationRelativeTo(null);
        this.setSize(500,500);

        setContentPane(rootPanel);

        setVisible(true);

        jDateOrder.setDate(new Date());

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        tablepane.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {

                int iPage = ((JTabbedPane)e.getSource()).getSelectedIndex();

                if(iPage == 0){
                    oOrderViewInformation.loadOrderInformation(ordersTables);

                }else if(iPage == 1){
                    oClientViewInformation.loadClientInformation(jUserTable);
                }else if(iPage == 2){
                    oDriverViewInformation.loadDriversInformation(jDriverTable);
                }else{
                    oSmsViewInformation.loadSmsInformation(jSmsTable);
                }

            }
        });


        jSmsTable.addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent e)
            {
                Point p = e.getPoint();
                row = jSmsTable.rowAtPoint(p);

                if (e.getClickCount() == 2)
                {
                    new CreateOrChangeSMSForm(true).setVisible(true);
                }
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

    public int getRow(){
        return row;
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnAddOrder){
            new CreateOrder().setVisible(true);
        }else if(e.getSource() == btnAddUser){
            new CreateClientForm().setVisible(true);
        }else if(e.getSource() == btnAddSms){
            new CreateOrChangeSMSForm(false).setVisible(true);
        }else if(e.getSource() == btnAdd){
            new CreateDriverForm().setVisible(true);
        }else if(e.getSource() == btnChangeOrder){
            //jUserTable.getSelectionModel().
        }else if(e.getSource() == btnChangeUser){

        }else if(e.getSource() == btnChangeSms){
            new CreateOrChangeSMSForm(true).setVisible(true);
        }else if(e.getSource() == btnChange){

        }else if(e.getSource() == btnRemoveOrder){

        }else if(e.getSource() == btnRemoveUser){

        }else if(e.getSource() == btnRemoveSms){
            new RemoveSMSForm().setVisible(true);
        }else if(e.getSource() == btnDelete){

        }
    }

    public JTable getJSmsTable() {
        return jSmsTable;
    }

    public static OrdersView getInstance() {
        if (instance == null) {
            instance = new OrdersView();
        }
        return instance;
    }
}
