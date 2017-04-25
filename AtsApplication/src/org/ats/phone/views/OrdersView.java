package org.ats.phone.views;

import com.toedter.calendar.JDateChooser;
import org.ats.phone.Main;
import org.ats.phone.dao.ClientEntity;
import org.ats.phone.dao.DriverEntity;
import org.ats.phone.dao.LinkSmsEntity;
import org.ats.phone.dao.TradeOrderEntity;
import org.ats.phone.mao.ClientViewInformation;
import org.ats.phone.mao.DriverViewInformation;
import org.ats.phone.mao.OrderViewInformation;
import org.ats.phone.mao.SmsViewInformation;
import org.ats.phone.utils.Constant;
import org.ats.phone.utils.IOnClickOk;
import org.hibernate.Session;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
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
    private JCheckBox CheckBoxAcceptDate;
    private JCheckBox CheckBoxAcceptDateTrade;
    private JDateChooser jDateTrade;
    private JPanel СМСоповещение1;
    private JTabbedPane tabbedPane1;
    private JTable table2;
    private JFormattedTextField etPhone;
    private JButton btnFindClient;
    private JTable table1;
    private JDateChooser jDatePickerOrder;

    private OrderViewInformation oOrderViewInformation;
    private ClientViewInformation oClientViewInformation;
    private DriverViewInformation oDriverViewInformation;
    private SmsViewInformation oSmsViewInformation;
    private static OrdersView   instance;
    public int iOrderRow, iClientRow, iDriverRow, iSmsRow;

    private ArrayList<TradeOrderEntity> listOfAllOrders;

    public OrdersView() {

        oOrderViewInformation = new OrderViewInformation(jDateOrder, cbEnableDriver, cbDrivers,
                CheckBoxAcceptDate, ordersTables, CheckBoxAcceptDateTrade, jDateTrade);
        listOfAllOrders = oOrderViewInformation.loadOrderInformation();

        oClientViewInformation = new ClientViewInformation(etPhone, btnFindClient, jUserTable);
        oDriverViewInformation = new DriverViewInformation();
        oSmsViewInformation = new SmsViewInformation();
        this.setLocationRelativeTo(null);

        setTitle(Constant.TITLE_ROUTE_ORDER);
        setSize(900, 800);
        setContentPane(rootPanel);

        setVisible(true);

        jDateTrade.setDate(new Date());
        jDateOrder.setDate(new Date());

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        loadCbDriver();

        tablepane.setSelectedIndex(0);

        tablepane.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {

                int iPage = ((JTabbedPane)e.getSource()).getSelectedIndex();

                if(iPage == 0){
                    listOfAllOrders = oOrderViewInformation.loadOrderInformation();
                }else if(iPage == 1){
                    oClientViewInformation.loadClientInformation(false);
                }else if(iPage == 2){
                    oDriverViewInformation.loadDriversInformation(jDriverTable);
                }else{
                    oSmsViewInformation.loadSmsInformation(jSmsTable);
                }

            }
        });


        jUserTable.addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent e)
            {
                Point p = e.getPoint();
                iClientRow = jUserTable.rowAtPoint(p);

                if (e.getClickCount() == 2)
                {
                    CreateClientForm oCreateClientForm  = new CreateClientForm(true);
                    oCreateClientForm.setVisible(true);
                }
            }
        });

        ordersTables.addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent e)
            {
                Point p = e.getPoint();
                iOrderRow = ordersTables.rowAtPoint(p);

                if (e.getClickCount() == 2)
                {
                    new CreateOrder(true, listOfAllOrders).setVisible(true);
                }
            }
        });

        jSmsTable.addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent e)
            {
                Point p = e.getPoint();
                iSmsRow = jSmsTable.rowAtPoint(p);

                if (e.getClickCount() == 2)
                {
                    new CreateOrChangeSMSForm(true).setVisible(true);
                }
            }
        });

        jDriverTable.addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent e)
            {
                Point p = e.getPoint();
                iDriverRow = jDriverTable.rowAtPoint(p);

                if (e.getClickCount() == 2)
                {

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

    public int getiOrderRow() {
        return iOrderRow;
    }

    public int getiClientRow() {
        return iClientRow;
    }

    public int getiDriverRow() {
        return iDriverRow;
    }

    public int getiSmsRow() {
        return iSmsRow;
    }

    private void loadCbDriver(){
        ArrayList<DriverEntity> listOfDrivers = (ArrayList<DriverEntity>) Main.getSession().createCriteria(DriverEntity.class).list();
        cbDrivers.setModel(new DefaultComboBoxModel(listOfDrivers.toArray()));
    }

    public void actionPerformed(ActionEvent e) {

        RemoveConfirm oRemoveConfirm;

        if(e.getSource() == btnAddOrder){
          CreateOrder oCreateOrder =  new CreateOrder(false, listOfAllOrders);
          oCreateOrder.setiOnClickOk(new IOnClickOk() {
                                         public void onClickOk() {
                                             oOrderViewInformation.loadOrderInformation();
                                         }
                                     });
            oCreateOrder.setVisible(true);
        }else if(e.getSource() == btnAddUser){
            new CreateClientForm(false).setVisible(true);
        }else if(e.getSource() == btnAddSms){
            CreateOrChangeSMSForm oCreateOrChangeSMSForm = new CreateOrChangeSMSForm(false);
            oCreateOrChangeSMSForm.setiOnClickOk(new IOnClickOk() {
                public void onClickOk() {
                    oSmsViewInformation.loadSmsInformation(jSmsTable);
                }
            });
            oCreateOrChangeSMSForm.setVisible(true);
        }else if(e.getSource() == btnAdd){
            new CreateDriverForm().setVisible(true);
        }else if(e.getSource() == btnChangeOrder){
            new CreateOrder(true, listOfAllOrders).setVisible(true);
        }else if(e.getSource() == btnChangeUser){

        }else if(e.getSource() == btnChangeSms){
            CreateOrChangeSMSForm oCreateOrChangeSMSForm =  new CreateOrChangeSMSForm(true);
            oCreateOrChangeSMSForm.setiOnClickOk(new IOnClickOk() {
                public void onClickOk() {
                    oSmsViewInformation.loadSmsInformation(jSmsTable);
                }
            });
            oCreateOrChangeSMSForm.setVisible(true);
        }else if(e.getSource() == btnChange){

        }else if(e.getSource() == btnRemoveOrder){
            oRemoveConfirm = new RemoveConfirm("Вы действительно хотите удалить заказ номер №"+
                    String.valueOf(ordersTables.getValueAt(iOrderRow, 0)) +
                    ".\n По адресу " +
                    (String) ordersTables.getValueAt(iOrderRow, 1) +
                    "?");
            oRemoveConfirm.setiOnClickOk(new IOnClickOk() {
                public void onClickOk() {
                    Session session = Main.getSession();
                    session.beginTransaction();

                    ArrayList<TradeOrderEntity> orderList = (ArrayList<TradeOrderEntity>) (session.createCriteria(TradeOrderEntity.class).list());
                    TradeOrderEntity orderEntity = orderList.get(iOrderRow);
                    session.delete(orderEntity);
                    session.getTransaction().commit();

                    oOrderViewInformation.loadOrderInformation();
                }
            });
            oRemoveConfirm.setVisible(true);
        }else if(e.getSource() == btnRemoveUser){
           oRemoveConfirm = new RemoveConfirm("Вы действительно хотите удалить клиента "+
                    jUserTable.getValueAt(iClientRow, 2) +
                    "?");
            oRemoveConfirm.setiOnClickOk(new IOnClickOk() {
                public void onClickOk() {
                    Session session = Main.getSession();
                    session.beginTransaction();

                    ArrayList<ClientEntity> orderList = (ArrayList<ClientEntity>) (session.createCriteria(ClientEntity.class).list());
                    ClientEntity clientEntity = orderList.get(iClientRow);
                    session.delete(clientEntity);
                    session.getTransaction().commit();

                    oClientViewInformation.loadClientInformation(false);
                }
            });
            oRemoveConfirm.setVisible(true);
        }else if(e.getSource() == btnRemoveSms){
            oRemoveConfirm = new RemoveConfirm("Вы действительно хотите удалить сообщение \""+
                    (String) jSmsTable.getValueAt(iSmsRow, 1) +
                    "?\"");
            oRemoveConfirm.setiOnClickOk(new IOnClickOk() {
                public void onClickOk() {
                    Session session = Main.getSession();
                    session.beginTransaction();

                    ArrayList<LinkSmsEntity> smsList = (ArrayList<LinkSmsEntity>) (session.createCriteria(LinkSmsEntity.class).list());
                    LinkSmsEntity smsEntity = smsList.get(OrdersView.getInstance().getiSmsRow());

                    session.delete(smsEntity);
                    session.getTransaction().commit();

                    oSmsViewInformation.loadSmsInformation(jSmsTable);
                }
            });
            oRemoveConfirm.setVisible(true);
        }else if(e.getSource() == btnDelete){
            oRemoveConfirm = new RemoveConfirm("Вы действительно хотите удалить водителя "+
                    jDriverTable.getValueAt(iDriverRow, 1) + " " +jDriverTable.getValueAt(iDriverRow, 2) +
                    "?");
            oRemoveConfirm.setiOnClickOk(new IOnClickOk() {
                public void onClickOk() {

                    Session session = Main.getSession();
                    session.beginTransaction();

                    ArrayList<DriverEntity> driverList = (ArrayList<DriverEntity>) (session.createCriteria(DriverEntity.class).list());
                    DriverEntity driverEntity = driverList.get(iDriverRow);

                    session.delete(driverEntity);
                    session.getTransaction().commit();

                    oDriverViewInformation.loadDriversInformation(jDriverTable);
                }
            });
            oRemoveConfirm.setVisible(true);
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
