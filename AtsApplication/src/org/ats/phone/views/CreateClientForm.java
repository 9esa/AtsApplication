package org.ats.phone.views;

import com.toedter.calendar.JDateChooser;
import org.ats.phone.dao.ClientEntity;
import org.ats.phone.utils.Constant;
import org.ats.phone.utils.HibernateSessionFactory;
import org.hibernate.Session;

import javax.swing.*;
import java.awt.event.*;
import java.util.Date;

public class CreateClientForm extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField tvFio;
    private JDateChooser jDatePicker;
    private JFormattedTextField tvPhone;
    private JComboBox cbAddressOrder;
    private JTextField textField1;
    private JButton btnCreateOrder;
    private JButton btnAddAddress;
    private JButton удалитьАдресButton;
    private JTextPane tvAddress;

    private boolean bChanged;

    public CreateClientForm(boolean bChanged) {

        this.bChanged = bChanged;
        this.setSize(550,250);
        this.setLocationRelativeTo(OrdersView.getInstance());

        setResizable(false);

        setTitle(Constant.TITLE_CREATE_CUSTOMER);
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

        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        ClientEntity userEntity = new ClientEntity();
        userEntity.setUserFio(tvFio.getText());
        userEntity.setPhoneNumber(tvPhone.getText().toString());
        userEntity.setAddress(tvAddress.getText().toString());
        userEntity.setBornDate(jDatePicker.getDate());
        userEntity.setDateOfCreate(new Date());

        session.save(userEntity);
        session.getTransaction().commit();

        session.close();


        dispose();
    }

    private void onCancel() {
//todo Ожидание одобрение подписи
//          new SmsConnectionService().getInterfaceRegistrationCode()
//                    .smsNotification(Constant.I_ID_SMS_SERVICE, Constant.KEY_SMS_SERVICE, "89818926024", Constant.SIGNATURE_SMS_SERVICE,"mess")
//                    .enqueue(new Callback<String>() {
//                        public void onResponse(Call<String> call, Response<String> response) {
//                            System.out.print("ok");
//                        }
//                        public void onFailure(Call<String> call, Throwable throwable) {
//                            System.out.print("bad");
//                        }
//                    });
// Запись в базу данных нового водителя

            dispose();

    }

}
