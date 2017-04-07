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
    private String message;
    private java.util.Date creationDate;
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
    @Column(name = "Message")
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "CreationDate")
    public java.util.Date getCreationDate() { return creationDate; }

    public void setCreationDate(java.util.Date creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LinkSmsEntity that = (LinkSmsEntity) o;

        if (id != that.id) return false;
        if (message != null ? !message.equals(that.message) : that.message != null) return false;
        if (creationDate != null ? !creationDate.equals(that.creationDate) : that.creationDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (message != null ? message.hashCode() : 0);
        result = 31 * result + (creationDate != null ? creationDate.hashCode() : 0);
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
