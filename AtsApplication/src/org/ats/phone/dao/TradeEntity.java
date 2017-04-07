package org.ats.phone.dao;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

/**
 * Created by user on 12.03.17.
 */
@Entity
@Table(name = "trade", schema = "db_ats_app", catalog = "")
public class TradeEntity {
    private int id;
    private Date dateOfCreate;
    private Date dateOfTrade;
    private int salary;
    private int sale;
    private Collection<OrderEntity> ordersById;

    @Id
    @Column(name = "Id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "DateOfCreate")
    public Date getDateOfCreate() {
        return dateOfCreate;
    }

    public void setDateOfCreate(Date dateOfCreate) {
        this.dateOfCreate = dateOfCreate;
    }

    @Basic
    @Column(name = "DateOfTrade")
    public Date getDateOfTrade() {
        return dateOfTrade;
    }

    public void setDateOfTrade(Date dateOfTrade) {
        this.dateOfTrade = dateOfTrade;
    }

    @Basic
    @Column(name = "Salary")
    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Basic
    @Column(name = "Sale")
    public int getSale() {
        return sale;
    }

    public void setSale(int sale) {
        this.sale = sale;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TradeEntity that = (TradeEntity) o;

        if (id != that.id) return false;
        if (salary != that.salary) return false;
        if (sale != that.sale) return false;
        if (dateOfCreate != null ? !dateOfCreate.equals(that.dateOfCreate) : that.dateOfCreate != null) return false;
        if (dateOfTrade != null ? !dateOfTrade.equals(that.dateOfTrade) : that.dateOfTrade != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (dateOfCreate != null ? dateOfCreate.hashCode() : 0);
        result = 31 * result + (dateOfTrade != null ? dateOfTrade.hashCode() : 0);
        result = 31 * result + salary;
        result = 31 * result + sale;
        return result;
    }

    @OneToMany(mappedBy = "tradeByTradeId")
    public Collection<OrderEntity> getOrdersById() {
        return ordersById;
    }

    public void setOrdersById(Collection<OrderEntity> ordersById) {
        this.ordersById = ordersById;
    }
}
