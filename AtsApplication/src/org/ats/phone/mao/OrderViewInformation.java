package org.ats.phone.mao;

import com.toedter.calendar.JDateChooser;
import org.ats.phone.Main;
import org.ats.phone.dao.DriverEntity;
import org.ats.phone.dao.TradeOrderEntity;
import org.ats.phone.utils.Constant;
import org.hibernate.criterion.Restrictions;
import retrofit2.Call;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.AbstractTableModel;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by user on 09.04.17.
 */
public class OrderViewInformation {

    private JDateChooser jDateOrder;
    private JCheckBox cbEnableDriver;

    private JDateChooser jDateOrderTrade;
    private JCheckBox cbAcceptDateTrade;

    private JComboBox cbDrivers;
    private JCheckBox cbAcceptDate;

    private JTable orderTables;
    private SimpleDateFormat dtFormat;
    private ArrayList<TradeOrderEntity> listOfOrders;
    private ArrayList<TradeOrderEntity> listOfOrdersNew;
    private ArrayList<TradeOrderEntity> listOfOrdersNewWithTrade;

    public OrderViewInformation(JDateChooser jDateOrder, JCheckBox cbEnableDriver,
                                JComboBox cbDrivers, JCheckBox cbAcceptDate, JTable orderTables,
                                JCheckBox cbAcceptDateTrade, JDateChooser jDateOrderTrade) {
        this.jDateOrder = jDateOrder;
        this.cbEnableDriver = cbEnableDriver;
        this.cbDrivers = cbDrivers;
        this.cbAcceptDate = cbAcceptDate;
        this.orderTables = orderTables;
        this.jDateOrderTrade = jDateOrderTrade;
        this.cbAcceptDateTrade = cbAcceptDateTrade;

        cbEnableDriver.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                loadOrderInformation();
            }
        });

        cbDrivers.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                loadOrderInformation();
            }
        });

        cbAcceptDate.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                loadOrderInformation();
            }
        });

        cbAcceptDateTrade.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                loadOrderInformation();
            }
        });
    }

    public ArrayList<TradeOrderEntity> loadOrderInformation() {

        listOfOrders = (ArrayList<TradeOrderEntity>) Main.getSession().createCriteria(TradeOrderEntity.class)
                .list();

        listOfOrdersNew = new ArrayList<TradeOrderEntity>();
        listOfOrdersNewWithTrade = new ArrayList<TradeOrderEntity>();

        dtFormat = new SimpleDateFormat(Constant.DATE_FORMAT);

        for (int iCount = 0; iCount < listOfOrders.size(); iCount++) {

            if (cbAcceptDate.isSelected()) {

                if (cbEnableDriver.isSelected()) {
                    if (jDateOrder.getDate().equals(listOfOrders.get(iCount).getTradeByTradeId().getDateOfCreate())
                            && ((DriverEntity) cbDrivers.getSelectedItem()).getId() == listOfOrders.get(iCount).getDriverByDriverId().getId()) {
                        listOfOrdersNew.add(listOfOrders.get(iCount));
                    }
                } else {
                    if (jDateOrder.getDate().equals(listOfOrders.get(iCount).getTradeByTradeId().getDateOfCreate())) {
                        listOfOrdersNew.add(listOfOrders.get(iCount));
                    }
                }
            } else {
                if (cbEnableDriver.isSelected()) {
                    if (((DriverEntity) cbDrivers.getSelectedItem()).getId() == listOfOrders.get(iCount).getDriverByDriverId().getId()) {
                        listOfOrdersNew.add(listOfOrders.get(iCount));
                    }
                } else {
                    listOfOrdersNew.add(listOfOrders.get(iCount));
                }
            }
        }

        if (cbAcceptDateTrade.isSelected()) {

            int iDay = jDateOrderTrade.getJCalendar().getDayChooser().getDay();
            int iMonth = jDateOrderTrade.getJCalendar().getMonthChooser().getMonth();
            int iYear =  jDateOrderTrade.getJCalendar().getYearChooser().getYear();

            Calendar oCalendarTrade = Calendar.getInstance();

            for (int iCount = 0; iCount < listOfOrdersNew.size(); iCount++) {
                oCalendarTrade.setTime(listOfOrdersNew.get(iCount).getTradeByTradeId().getDateOfTrade());
                if (iMonth == oCalendarTrade.get(Calendar.MONTH) &&
                        iYear ==  oCalendarTrade.get(Calendar.YEAR) &&
                        iDay ==  oCalendarTrade.get(Calendar.DAY_OF_MONTH)) {
                    listOfOrdersNewWithTrade.add(listOfOrdersNew.get(iCount));
                }
            }
        } else {
            listOfOrdersNewWithTrade = listOfOrdersNew;
        }


        orderTables.setModel(new AbstractTableModel() {


            public int getRowCount() {
                return listOfOrdersNewWithTrade.size();
            }

            public int getColumnCount() {
                return 9;
            }

            public Object getValueAt(int rowIndex, int columnIndex) {
                TradeOrderEntity p = listOfOrdersNewWithTrade.get(rowIndex);
                Object[] values = new Object[]{p.getId(), p.getAddress(),
                        p.getClientByUserId().getUserFio(), p.getClientByUserId().getPhoneNumber(),
                        p.getDriverByDriverId().getName() + " " + p.getDriverByDriverId().getSecondName(), p.getDriverByDriverId().getPhone(),
                        dtFormat.format(p.getTradeByTradeId().getDateOfCreate()), dtFormat.format(p.getTradeByTradeId().getDateOfTrade()), p.getNumberOfTrade()
                };
                return values[columnIndex];
            }

            @Override
            public String getColumnName(int column) {
                String[] columnNames = {"Id",
                        "Адрес доставки", "ФИО Клиента", "Номер клиента", "ФИО Водителя", "Номер водителя", "Дата оформления заказа", "Дата поставки", "Номер заказа в день"};

                return columnNames[column];
            }
        });

        return listOfOrders;

    }

}
