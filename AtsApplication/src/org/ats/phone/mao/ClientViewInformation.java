package org.ats.phone.mao;

import org.ats.phone.Main;
import org.ats.phone.dao.ClientEntity;
import org.ats.phone.dao.DriverEntity;
import org.ats.phone.utils.Constant;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by user on 04.04.17.
 */
public class ClientViewInformation {

    private JFormattedTextField etPhone;
    private JButton btnFindClient;
    private JTable driverTables;
    private ArrayList <ClientEntity> listOfClientsNew;
    public ClientViewInformation(JFormattedTextField etPhone, JButton btnFindClient, JTable driverTables) {

        this.etPhone = etPhone;
        this.driverTables = driverTables;
        this.btnFindClient = btnFindClient;

        btnFindClient.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadClientInformation(true);
            }
        });
    }

    public void loadClientInformation(boolean bReadFilter) {

        listOfClientsNew = new ArrayList<ClientEntity>();

        ArrayList<ClientEntity> listOfUsers = (ArrayList<ClientEntity>) Main.getSession().createCriteria(ClientEntity.class).list();

        if(bReadFilter){

            for(int iCount = 0; iCount <  listOfUsers.size(); iCount++){
                if(listOfUsers.get(iCount).getPhoneNumber().contains(etPhone.getText())){
                    listOfClientsNew.add(listOfUsers.get(iCount));
                }
            }

        }else{
            listOfClientsNew = listOfUsers;
        }

        driverTables.setModel(new AbstractTableModel() {

            SimpleDateFormat dtFormat = new SimpleDateFormat(Constant.DATE_FORMAT);


            public int getRowCount() {
                return listOfClientsNew.size();
            }

            public int getColumnCount() {
                return 6;
            }

            public Object getValueAt(int rowIndex, int columnIndex) {
                ClientEntity p = listOfClientsNew.get(rowIndex);
                Object[] values = new Object[]{p.getId(), dtFormat.format(p.getDateOfCreate()),
                        p.getUserFio(), dtFormat.format(p.getBornDate()), p.getAddress(), p.getPhoneNumber()};
                return values[columnIndex];
            }

            @Override
            public String getColumnName(int column) {
                String[] columnNames = {"Id", "Дата создания",
                        "ФИО Клиента","Дата рождения", "Адрес", "Телефон" };

                return columnNames[column];
            }
        });


    }

}
