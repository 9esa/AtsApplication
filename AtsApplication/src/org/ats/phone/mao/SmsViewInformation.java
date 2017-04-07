package org.ats.phone.mao;

import org.ats.phone.Main;
import org.ats.phone.dao.LinkSmsEntity;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by Никита on 03.04.2017.
 */
public class SmsViewInformation {

    public void loadSmsInformation(JTable smsTable){

        smsTable.setModel(new AbstractTableModel() {

            ArrayList<LinkSmsEntity> smsList = (ArrayList<LinkSmsEntity>) Main.getSession().createCriteria(LinkSmsEntity.class).list();


            public int getRowCount() {
                return smsList.size();
            }

            public int getColumnCount() {
                return 3;
            }

            public Object getValueAt(int rowIndex, int columnIndex) {

                LinkSmsEntity p = smsList.get(rowIndex);
                SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
                Object[] values=new Object[]{p.getId(),p.getMessage(),format.format(p.getCreationDate())};

                return values[columnIndex];

            }

            @Override
            public String getColumnName(int column) {
                String[] columnNames = {"Id",
                        "Сообщение",
                        "Дата создания"};

                return columnNames[column];
            }

        });

    }
}
