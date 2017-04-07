package org.ats.phone.dao;

import javax.persistence.*;

/**
 * Created by user on 12.03.17.
 */
@Entity
@Table(name = "Order", schema = "db_ats_app", catalog = "")
public class OrderEntity {
    private int id;
    private String address;
    private String phone;
    private TradeEntity tradeByTradeId;
    private DriverEntity driverByDriverId;
    private StatusEntity statusByStatusId;
    private LinkSmsEntity linkSmsByLinkSmsId;
    private UserEntity userByUserId;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    @Column(name = "Phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderEntity that = (OrderEntity) o;

        if (id != that.id) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "TradeId", referencedColumnName = "Id", nullable = false)
    public TradeEntity getTradeByTradeId() {
        return tradeByTradeId;
    }

    public void setTradeByTradeId(TradeEntity tradeByTradeId) {
        this.tradeByTradeId = tradeByTradeId;
    }

    @ManyToOne
    @JoinColumn(name = "DriverId", referencedColumnName = "Id", nullable = false)
    public DriverEntity getDriverByDriverId() {
        return driverByDriverId;
    }

    public void setDriverByDriverId(DriverEntity driverByDriverId) {
        this.driverByDriverId = driverByDriverId;
    }

    @ManyToOne
    @JoinColumn(name = "StatusId", referencedColumnName = "Id", nullable = false)
    public StatusEntity getStatusByStatusId() {
        return statusByStatusId;
    }

    public void setStatusByStatusId(StatusEntity statusByStatusId) {
        this.statusByStatusId = statusByStatusId;
    }

    @ManyToOne
    @JoinColumn(name = "LinkSmsId", referencedColumnName = "id", nullable = false)
    public LinkSmsEntity getLinkSmsByLinkSmsId() {
        return linkSmsByLinkSmsId;
    }

    public void setLinkSmsByLinkSmsId(LinkSmsEntity linkSmsByLinkSmsId) {
        this.linkSmsByLinkSmsId = linkSmsByLinkSmsId;
    }

    @ManyToOne
    @JoinColumn(name = "UserId", referencedColumnName = "id", nullable = false)
    public UserEntity getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(UserEntity userByUserId) {
        this.userByUserId = userByUserId;
    }
}
