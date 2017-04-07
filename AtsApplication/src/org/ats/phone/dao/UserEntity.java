package org.ats.phone.dao;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

/**
 * Created by user on 12.03.17.
 */
@Entity
@Table(name = "user", schema = "db_ats_app", catalog = "")
public class UserEntity {
    private int id;
    private String user_fio;
    private String address;
    private String phone_number;
    private java.util.Date bornDate;
    private java.util.Date dateOfCreate;
    private Collection<OrderEntity> ordersById;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "USER_FIO")
    public String getName() {
        return user_fio;
    }

    public void setName(String name) {
        this.user_fio = name;
    }

    @Basic
    @Column(name = "Address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    @Basic
    @Column(name = "Phone_number")
    public String getPhone() {
        return phone_number;
    }

    public void setPhone(String phone_number) {
        this.phone_number = phone_number;
    }

    @Basic
    @Column(name = "BornDate")
    public java.util.Date getBornDate() {
        return bornDate;
    }

    public void setBornDate(java.util.Date bornDate) {
        this.bornDate = bornDate;
    }

    @Basic
    @Column(name = "DateOfCreate")
    public java.util.Date getDateOfCreate() {
        return dateOfCreate;
    }

    public void setDateOfCreate(java.util.Date dateOfCreate) {
        this.dateOfCreate = dateOfCreate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (id != that.id) return false;
        if (user_fio != null ? !user_fio.equals(that.user_fio) : that.user_fio != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (bornDate != null ? !bornDate.equals(that.bornDate) : that.bornDate != null) return false;
        if (dateOfCreate != null ? !dateOfCreate.equals(that.dateOfCreate) : that.dateOfCreate != null) return false;
        if (ordersById != null ? !ordersById.equals(that.ordersById) : that.ordersById != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (user_fio != null ? user_fio.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (bornDate != null ? bornDate.hashCode() : 0);
        result = 31 * result + (dateOfCreate != null ? dateOfCreate.hashCode() : 0);
        result = 31 * result + (ordersById != null ? ordersById.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "userByUserId")
    public Collection<OrderEntity> getOrdersById() {
        return ordersById;
    }

    public void setOrdersById(Collection<OrderEntity> ordersById) {
        this.ordersById = ordersById;
    }
}
