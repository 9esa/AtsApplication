package org.ats.phone.dao;

import javax.persistence.*;

/**
 * Created by user on 11.04.17.
 */
@Entity
@Table(name = "TradeOrder", schema = "db_ats_app", catalog = "")
public class TradeOrderEntity {
    private Integer id;
    private String address;
    private Integer numberOfTrade;
    private DriverEntity driverByDriverId;
    private TradeEntity tradeByTradeId;
    private ClientEntity clientByUserId;

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
    @Column(name = "NumberOfTrade")
    public Integer getNumberOfTrade() {
        return numberOfTrade;
    }

    public void setNumberOfTrade(int numberOfTrade) {
        this.numberOfTrade = numberOfTrade;
    }

    @Basic
    @Column(name = "Address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TradeOrderEntity that = (TradeOrderEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
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
    @JoinColumn(name = "TradeId", referencedColumnName = "Id", nullable = false)
    public TradeEntity getTradeByTradeId() {
        return tradeByTradeId;
    }

    public void setTradeByTradeId(TradeEntity tradeByTradeId) {
        this.tradeByTradeId = tradeByTradeId;
    }

    @ManyToOne
    @JoinColumn(name = "UserId", referencedColumnName = "id", nullable = false)
    public ClientEntity getClientByUserId() {
        return clientByUserId;
    }

    public void setClientByUserId(ClientEntity clientByUserId) {
        this.clientByUserId = clientByUserId;
    }
}
