package org.ats.phone.dao;

import javax.persistence.*;
import java.util.Date;
import java.util.Collection;

/**
 * Created by user on 11.04.17.
 */
@Entity
@Table(name = "trade", schema = "db_ats_app", catalog = "")
public class TradeEntity {
    private Integer id;
    private Date dateOfCreate;
    private Date dateOfTrade;
    private Integer salary;
    private Integer sale;
    private Collection<TradeOrderEntity> tradeOrdersById;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    @Basic
    @Column(name = "Sale")
    public Integer getSale() {
        return sale;
    }

    public void setSale(Integer sale) {
        this.sale = sale;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TradeEntity that = (TradeEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (dateOfCreate != null ? !dateOfCreate.equals(that.dateOfCreate) : that.dateOfCreate != null) return false;
        if (dateOfTrade != null ? !dateOfTrade.equals(that.dateOfTrade) : that.dateOfTrade != null) return false;
        if (salary != null ? !salary.equals(that.salary) : that.salary != null) return false;
        if (sale != null ? !sale.equals(that.sale) : that.sale != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (dateOfCreate != null ? dateOfCreate.hashCode() : 0);
        result = 31 * result + (dateOfTrade != null ? dateOfTrade.hashCode() : 0);
        result = 31 * result + (salary != null ? salary.hashCode() : 0);
        result = 31 * result + (sale != null ? sale.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "tradeByTradeId")
    public Collection<TradeOrderEntity> getTradeOrdersById() {
        return tradeOrdersById;
    }

    public void setTradeOrdersById(Collection<TradeOrderEntity> tradeOrdersById) {
        this.tradeOrdersById = tradeOrdersById;
    }
}
