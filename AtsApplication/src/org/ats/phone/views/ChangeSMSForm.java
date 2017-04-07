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
import java.util.Calendar;

import static org.ats.phone.Main.oOrdersView;

public class ChangeSMSForm extends JDialog{

    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField smsText;

    public ChangeSMSForm() {

        this.setSize(400, 150);
        this.setLocationRelativeTo(oOrdersView);
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setTitle(Constant.TITLE_CHANGE_SMS);

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

        smsText.setText((String) oOrdersView.jSmsTable.getValueAt(oOrdersView.row, 1));

    }

    private void onOK() {

        Session session = HibernateSessionFactory.getSessionFactory().openSession();

        ArrayList<LinkSmsEntity> smsList = (ArrayList<LinkSmsEntity>) (session.createCriteria(LinkSmsEntity.class).list());
        LinkSmsEntity smsEntity = smsList.get(oOrdersView.row);

        session.beginTransaction();
        smsEntity.setMessage(smsText.getText());
        smsEntity.setCreationDate(Calendar.getInstance().getTime());

        session.save(smsEntity);
        session.getTransaction().commit();

        session.close();

        SmsViewInformation oSmsViewInformation = new SmsViewInformation();
        oSmsViewInformation.loadSmsInformation(oOrdersView.jSmsTable);

        dispose();

    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        ChangeSMSForm dialog = new ChangeSMSForm();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
