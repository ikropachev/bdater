package org.ikropachev.bdater.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Entity
@Table(name = "item")
public class Item extends AbstractNamedEntity {

    @Column(name = "date_memorable", nullable = false, columnDefinition = "timestamp default now()", updatable = true)
    @NotNull
    private Date dateMemorable;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "item_type_id", nullable = false)
    private ItemType itemType;

    public Item() {
    }

    public Item(Integer id, String name, ItemType itemType) {
        super(id, name);
        this.itemType = itemType;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    public void setDateMemorable(Date dateMemorable) {
        this.dateMemorable = dateMemorable;
    }

    public Date getDateMemorable() {
        return dateMemorable;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name=" + name +
                ", dateMemorable=" + dateMemorable +
                ", itemType=" + itemType +
                '}';
    }
}

