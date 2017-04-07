package org.ats.phone.mao;

import org.ats.phone.Main;
import org.ats.phone.dao.DriverEntity;
import org.ats.phone.dao.UserEntity;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

/**
 * Created by user on 04.04.17.
 */
public class ClientViewInformation {

    public void loadClientInformation(JTable driverTables) {

        driverTables.setModel(new AbstractTableModel() {

            ArrayList<UserEntity> listOfUsers = (ArrayList<UserEntity>) Main.getSession().createCriteria(UserEntity.class).list();

            public int getRowCount() {
                return listOfUsers.size();
            }

            public int getColumnCount() {
                return 2;
            }

            public Object getValueAt(int rowIndex, int columnIndex) {
                UserEntity p = listOfUsers.get(rowIndex);
                Object[] values = new Object[]{p.getId(), p.getName()};
                return values[columnIndex];
            }

            @Override
            public String getColumnName(int column) {
                String[] columnNames = {"Id",
                        "ФИО"};

                return columnNames[column];
            }
        });


    }

}
