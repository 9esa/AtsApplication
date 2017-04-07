package org.ats.phone.dao;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by user on 12.03.17.
 */
@Entity
@Table(name = "driver", schema = "db_ats_app", catalog = "")
public class DriverEntity {
    private int id;
    private String name;
    private String secondName;
    private String phone;
    private int sipLineId;
    private java.util.Date bornDate;
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
    public int getSipLineId() {
        return sipLineId;
    }

    public void setSipLineId(int sipLineId) {
        this.sipLineId = sipLineId;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "BornDate")
    public java.util.Date getBornDate() {
        return bornDate;
    }

    public void setBornDate(java.util.Date bornDate) {
        this.bornDate = bornDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DriverEntity that = (DriverEntity) o;

        if (id != that.id) return false;
        if (sipLineId != that.sipLineId) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (secondName != null ? !secondName.equals(that.secondName) : that.secondName != null) return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;
        if (bornDate != null ? !bornDate.equals(that.bornDate) : that.bornDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (secondName != null ? secondName.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + sipLineId;
        result = 31 * result + (bornDate != null ? bornDate.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "driverByDriverId")
    public Collection<OrderEntity> getOrdersById() {
        return ordersById;
    }

    public void setOrdersById(Collection<OrderEntity> ordersById) {
        this.ordersById = ordersById;
    }
}
