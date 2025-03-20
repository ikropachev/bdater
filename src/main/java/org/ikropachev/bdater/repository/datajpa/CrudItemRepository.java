package org.ikropachev.bdater.repository.datajpa;

import org.ikropachev.bdater.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface CrudItemRepository extends JpaRepository<Item, Integer> {
    @Transactional
    @Modifying
    @Query("DELETE FROM Item i WHERE i.id=:id")
    int delete(@Param("id") int id);
}
