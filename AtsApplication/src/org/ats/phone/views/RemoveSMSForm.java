package org.ats.phone.views;

import org.ats.phone.Main;
import org.ats.phone.dao.LinkSmsEntity;
import org.ats.phone.mao.SmsViewInformation;
import org.ats.phone.utils.Constant;
import org.ats.phone.utils.HibernateSessionFactory;
import org.hibernate.Session;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class RemoveSMSForm extends JDialog {

    private JPanel contentPane;
    private JButton btnYes;
    private JButton btnNo;
    private JLabel labelMessage;

    public RemoveSMSForm() {

        this.setSize(400, 150);
        this.setLocationRelativeTo(OrdersView.getInstance());
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(btnYes);
        setTitle(Constant.TITLE_REMOVE_SMS);

        btnYes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onYes();
            }
        });

        btnNo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onNo();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onNo();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onNo();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        labelMessage.setText("Вы действительно хотите удалить сообщение: \"" + (String) OrdersView.getInstance().getJSmsTable().getValueAt(OrdersView.getInstance().row, 1) + "\"?");

    }

    private void onYes() {

        Session session = Main.getSession();
        session.beginTransaction();

        ArrayList<LinkSmsEntity> smsList = (ArrayList<LinkSmsEntity>) (session.createCriteria(LinkSmsEntity.class).list());
        LinkSmsEntity smsEntity = smsList.get(OrdersView.getInstance().row);

        session.delete(smsEntity);
        session.getTransaction().commit();

        SmsViewInformation oSmsViewInformation = new SmsViewInformation();
        oSmsViewInformation.loadSmsInformation(OrdersView.getInstance().getJSmsTable());

        dispose();

    }

    private void onNo() {
        // add your code here if necessary
        dispose();
    }

//    public static void main(String[] args) {
//        RemoveSMSForm dialog = new RemoveSMSForm();
//        dialog.pack();
//        dialog.setVisible(true);
//        System.exit(0);
//    }

}
