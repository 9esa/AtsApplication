package org.ats.phone.dao;

import javax.persistence.*;
import java.util.Date;
import java.util.Collection;

/**
 * Created by user on 11.04.17.
 */
@Entity
@Table(name = "client", schema = "db_ats_app", catalog = "")
public class ClientEntity {
    private Integer id;
    private String userFio;
    private String address;
    private Date bornDate;
    private Date dateOfCreate;
    private String phoneNumber;
    private Collection<TradeOrderEntity> tradeOrdersById;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "USER_FIO")
    public String getUserFio() {
        return userFio;
    }

    public void setUserFio(String userFio) {
        this.userFio = userFio;
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
    @Column(name = "BornDate")
    public Date getBornDate() {
        return bornDate;
    }

    public void setBornDate(Date bornDate) {
        this.bornDate = bornDate;
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
    @Column(name = "PHONE_NUMBER")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClientEntity that = (ClientEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (userFio != null ? !userFio.equals(that.userFio) : that.userFio != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (bornDate != null ? !bornDate.equals(that.bornDate) : that.bornDate != null) return false;
        if (dateOfCreate != null ? !dateOfCreate.equals(that.dateOfCreate) : that.dateOfCreate != null) return false;
        if (phoneNumber != null ? !phoneNumber.equals(that.phoneNumber) : that.phoneNumber != null) return false;

        return true;
    }

    @Override
    public String toString() {
        return getUserFio() +  " " + getPhoneNumber();
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (userFio != null ? userFio.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (bornDate != null ? bornDate.hashCode() : 0);
        result = 31 * result + (dateOfCreate != null ? dateOfCreate.hashCode() : 0);
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "clientByUserId")
    public Collection<TradeOrderEntity> getTradeOrdersById() {
        return tradeOrdersById;
    }

    public void setTradeOrdersById(Collection<TradeOrderEntity> tradeOrdersById) {
        this.tradeOrdersById = tradeOrdersById;
    }
}
