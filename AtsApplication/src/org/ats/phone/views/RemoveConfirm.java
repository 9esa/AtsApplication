package org.ats.phone.views;

import org.ats.phone.Main;
import org.ats.phone.dao.LinkSmsEntity;
import org.ats.phone.mao.SmsViewInformation;
import org.ats.phone.utils.Constant;
import org.ats.phone.utils.HibernateSessionFactory;
import org.ats.phone.utils.IOnClickOk;
import org.hibernate.Session;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class RemoveConfirm extends JDialog {

    private JPanel contentPane;
    private JButton btnYes;
    private JButton btnNo;
    private JLabel labelMessage;

    private IOnClickOk iOnClickOk;

    public RemoveConfirm(String sValue) {

        this.setSize(600, 170);
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

        labelMessage.setText(sValue);

    }

    private void onYes() {
        iOnClickOk.onClickOk();
        dispose();

    }

    private void onNo() {
        // add your code here if necessary
        dispose();
    }


    public void setiOnClickOk(IOnClickOk iOnClickOk) {
        this.iOnClickOk = iOnClickOk;
    }
}
