package org.ats.phone.mao;

import org.ats.phone.Main;
import org.ats.phone.dao.DriverEntity;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

/**
 * Created by user on 12.03.17.
 */
public class DriverViewInformation {

        public void loadDriversInformation(JTable driverTables){

           driverTables.setModel(new AbstractTableModel() {

               ArrayList<DriverEntity> listOfDrivers = (ArrayList<DriverEntity>) Main.getSession().createCriteria(DriverEntity.class).list();

               public int getRowCount() {
                   return listOfDrivers.size();
               }

               public int getColumnCount() {
                   return 5;
               }

               public Object getValueAt(int rowIndex, int columnIndex) {
                   DriverEntity p = listOfDrivers.get(rowIndex);
                   Object[] values=new Object[]{p.getId(),p.getName(),p.getSecondName(),
                           p.getBornDate(),p.getSipLineId()};
                   return values[columnIndex];
               }

               @Override
               public String getColumnName(int column) {
                   String[] columnNames = {"Id",
                           "Имя",
                           "Фамилия",
                           "Телефон",
                           "SIP линия"};

                   return columnNames[column];
               }
           });

        }

}
