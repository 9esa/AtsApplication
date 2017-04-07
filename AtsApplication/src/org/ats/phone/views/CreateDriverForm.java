package org.ats.phone.views;

import com.toedter.calendar.JDateChooser;
import org.ats.phone.dao.DriverEntity;
import org.ats.phone.utils.Constant;
import org.ats.phone.utils.HibernateSessionFactory;
import org.hibernate.Session;

import javax.accessibility.AccessibleComponent;
import javax.swing.*;
import java.awt.event.*;

public class CreateDriverForm extends JDialog {

    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JSpinner spinner;
    private JFormattedTextField phone;
    private JFormattedTextField secondName;
    private JFormattedTextField firstName;
    private JDateChooser jDatePicker;

    public CreateDriverForm() {

        this.setSize(500,170);

        setResizable(false);
        setTitle(Constant.TITLE_CREATE_DRIVER);
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
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        DriverEntity driverEntity = new DriverEntity();
        driverEntity.setName(firstName.getText());
        driverEntity.setSecondName(secondName.getText());
        driverEntity.setPhone(phone.getText());
        driverEntity.setBornDate(jDatePicker.getDate());

        session.save(driverEntity);
        session.getTransaction().commit();

        session.close();

        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        CreateDriverForm dialog = new CreateDriverForm();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

    public CreateDriverForm setData() {
        return this;
    }
}
