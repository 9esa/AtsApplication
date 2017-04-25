package org.ats.phone.dao;

import javax.persistence.*;
import java.util.Date;
import java.util.Collection;

/**
 * Created by user on 11.04.17.
 */
@Entity
@Table(name = "driver", schema = "db_ats_app", catalog = "")
public class DriverEntity {
    private Integer id;
    private String name;
    private String secondName;
    private String phone;
    private Integer sipLineId;
    private Date bornDate;
    private Collection<TradeOrderEntity> tradeOrdersById;

    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "Name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "SecondName")
    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    @Basic
    @Column(name = "Phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "SipLineId")
    public Integer getSipLineId() {
        return sipLineId;
    }

    public void setSipLineId(Integer sipLineId) {
        this.sipLineId = sipLineId;
    }

    @Basic
    @Column(name = "BornDate")
    public Date getBornDate() {
        return bornDate;
    }

    public void setBornDate(Date bornDate) {
        this.bornDate = bornDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DriverEntity that = (DriverEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (secondName != null ? !secondName.equals(that.secondName) : that.secondName != null) return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;
        if (sipLineId != null ? !sipLineId.equals(that.sipLineId) : that.sipLineId != null) return false;
        if (bornDate != null ? !bornDate.equals(that.bornDate) : that.bornDate != null) return false;

        return true;
    }

    @Override
    public String toString() {
        return getName() + " " + getSecondName() + " " + getPhone() ;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (secondName != null ? secondName.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (sipLineId != null ? sipLineId.hashCode() : 0);
        result = 31 * result + (bornDate != null ? bornDate.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "driverByDriverId")
    public Collection<TradeOrderEntity> getTradeOrdersById() {
        return tradeOrdersById;
    }

    public void setTradeOrdersById(Collection<TradeOrderEntity> tradeOrdersById) {
        this.tradeOrdersById = tradeOrdersById;
    }
}
