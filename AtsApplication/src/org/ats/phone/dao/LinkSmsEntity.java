package org.ats.phone.dao;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by user on 12.03.17.
 */
@Entity
@Table(name = "link_sms", schema = "db_ats_app", catalog = "")
public class LinkSmsEntity {
    private int id;
    private byte first;
    private byte second;
    private byte third;
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
    @Column(name = "first")
    public byte getFirst() {
        return first;
    }

    public void setFirst(byte first) {
        this.first = first;
    }

    @Basic
    @Column(name = "second")
    public byte getSecond() {
        return second;
    }

    public void setSecond(byte second) {
        this.second = second;
    }

    @Basic
    @Column(name = "third")
    public byte getThird() {
        return third;
    }

    public void setThird(byte third) {
        this.third = third;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LinkSmsEntity that = (LinkSmsEntity) o;

        if (id != that.id) return false;
        if (first != that.first) return false;
        if (second != that.second) return false;
        if (third != that.third) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (int) first;
        result = 31 * result + (int) second;
        result = 31 * result + (int) third;
        return result;
    }

    @OneToMany(mappedBy = "linkSmsByLinkSmsId")
    public Collection<OrderEntity> getOrdersById() {
        return ordersById;
    }

    public void setOrdersById(Collection<OrderEntity> ordersById) {
        this.ordersById = ordersById;
    }
}
