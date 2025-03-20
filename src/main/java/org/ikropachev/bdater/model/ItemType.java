package org.ikropachev.bdater.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Entity;
import javax.persistence.Table;

@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Entity
@Table(name = "item_type")
public class ItemType extends AbstractNamedEntity {

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name=" + name +
                '}';
    }
}
