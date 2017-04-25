package org.ats.phone.views;

import com.toedter.calendar.JDateChooser;
import org.ats.phone.Main;
import org.ats.phone.dao.ClientEntity;
import org.ats.phone.dao.DriverEntity;
import org.ats.phone.dao.TradeEntity;
import org.ats.phone.dao.TradeOrderEntity;
import org.ats.phone.utils.Constant;
import org.ats.phone.utils.IOnClickOk;
import org.ats.phone.utils.SmsConnectionService;
import org.hibernate.Session;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import javax.swing.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CreateOrder extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JDateChooser jDatePickerOrder;
    private JCheckBox cbDateTrade;
    private JCheckBox cbTimeChange;
    private JLabel jNumberRequest;
    private JComboBox cbDriver;
    private JComboBox cbClient;
    private JLabel jTitleNumberOrder;
    private JDateChooser jDatePickerTrade;
    private JTextArea taAddress;
    private JButton добавитьКлиентаButton;

    private ArrayList<DriverEntity> listOfDrivers;
    private ArrayList<ClientEntity> listOfClients;
    private ArrayList<TradeOrderEntity> listOfAllOrders;

    private IOnClickOk iOnClickOk;

    public CreateOrder(boolean bChange, ArrayList<TradeOrderEntity> listOfAllOrders) {

        this.setSize(720, 250);
        this.setLocationRelativeTo(OrdersView.getInstance());
        this.listOfAllOrders = listOfAllOrders;

        jTitleNumberOrder.setVisible(bChange);

        if (bChange) {
            setTitle(Constant.TITLE_CHANGE_ORDER);
        } else {
            setTitle(Constant.TITLE_CREATE_ORDER);
        }

        setResizable(false);

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        jDatePickerOrder.setDate(new Date());

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

        loadCbDriver();
        loadCbClient();
    }

    private void loadCbDriver() {
        listOfDrivers = (ArrayList<DriverEntity>) Main.getSession().createCriteria(DriverEntity.class).list();
        cbDriver.setModel(new DefaultComboBoxModel(listOfDrivers.toArray()));
    }

    private void loadCbClient() {
        listOfClients = (ArrayList<ClientEntity>) Main.getSession().createCriteria(ClientEntity.class).list();
        cbClient.setModel(new DefaultComboBoxModel(listOfClients.toArray()));
    }


    private void onOK() {

        Session session = Main.getSession();

        session.beginTransaction();

        TradeEntity tradeEntity = new TradeEntity();
        tradeEntity.setDateOfCreate(jDatePickerOrder.getDate());
        tradeEntity.setDateOfTrade(jDatePickerTrade.getDate());

        Integer id = (Integer) session.save(tradeEntity);
        session.getTransaction().commit();
        session.close();

        int iCountOrder = 1;
        for (int iCount = 0; iCount < listOfAllOrders.size(); iCount++) {
            int iDayOfMonth = listOfAllOrders.get(iCount).getTradeByTradeId().getDateOfTrade().getDay();
            int iYear = listOfAllOrders.get(iCount).getTradeByTradeId().getDateOfTrade().getYear();
            if(iDayOfMonth == jDatePickerTrade.getDate().getDay() && iYear == jDatePickerTrade.getDate().getYear()){
                iCountOrder++;
            }
        }

        session = Main.getSession();
        session.beginTransaction();

        TradeOrderEntity orderEntity = new TradeOrderEntity();
        orderEntity.setAddress(taAddress.getText().toString());
        orderEntity.setDriverByDriverId((DriverEntity) cbDriver.getSelectedItem());
        orderEntity.setClientByUserId((ClientEntity) cbClient.getSelectedItem());
        orderEntity.setNumberOfTrade(iCountOrder);
        orderEntity.setTradeByTradeId(tradeEntity);

        id = (Integer) session.save(orderEntity);
        session.getTransaction().commit();

        session.close();

        //sentMessageClient(id , (ClientEntity) cbClient.getSelectedItem(), tradeEntity);

        iOnClickOk.onClickOk();

        dispose();

    }

    public void setiOnClickOk(IOnClickOk iOnClickOk) {
        this.iOnClickOk = iOnClickOk;
    }

    private void sentMessageClient(int iOrderId , ClientEntity oClientEntity, TradeEntity oTradeEntity){

                  SimpleDateFormat dtFormat = new SimpleDateFormat(Constant.DATE_FORMAT);

                  String sValue = "Номер вашего заказа:" + iOrderId + ". Дата поставки: " + dtFormat.format(oTradeEntity.getDateOfTrade()) + ". Cпасибо за выбор нас.";

                  new SmsConnectionService().getInterfaceRegistrationCode()
                    .smsNotification(Constant.I_ID_SMS_SERVICE, Constant.KEY_SMS_SERVICE, oClientEntity.getPhoneNumber(), Constant.SIGNATURE_SMS_SERVICE, sValue)
                    .enqueue(new Callback<String>() {
                        public void onResponse(Call<String> call, Response<String> response) {
                            System.out.print("ok");
                        }
                        public void onFailure(Call<String> call, Throwable throwable) {
                            System.out.print("bad");
                        }
                    });
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

}
