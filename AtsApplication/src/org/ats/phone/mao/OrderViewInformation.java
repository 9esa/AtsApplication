package org.ats.phone.mao;

import org.ats.phone.Main;
import org.ats.phone.dao.OrderEntity;
import org.ats.phone.dao.UserEntity;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

/**
 * Created by user on 09.04.17.
 */
public class OrderViewInformation {

    public void loadOrderInformation(JTable orderTables) {

        orderTables.setModel(new AbstractTableModel() {

            ArrayList<OrderEntity> listOfOrders = (ArrayList<OrderEntity>) Main.getSession().createCriteria(OrderEntity.class).list();

            public int getRowCount() {
                return listOfOrders.size();
            }

            public int getColumnCount() {
                return 2;
            }

            public Object getValueAt(int rowIndex, int columnIndex) {
                OrderEntity p = listOfOrders.get(rowIndex);
                Object[] values = new Object[]{p.getId(), p.getAddress()};
                return values[columnIndex];
            }

            @Override
            public String getColumnName(int column) {
                String[] columnNames = {"Id",
                        "Адрес"};

                return columnNames[column];
            }
        });


    }

}
