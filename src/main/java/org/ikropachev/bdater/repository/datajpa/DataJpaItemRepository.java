package org.ikropachev.bdater.repository.datajpa;

import org.ikropachev.bdater.model.Item;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DataJpaItemRepository {
    private static final Sort SORT_NAME = Sort.by(Sort.Direction.ASC, "name");

    private final CrudItemRepository crudRepository;

    public DataJpaItemRepository(CrudItemRepository crudRepository) {
        this.crudRepository = crudRepository;
    }

    public Item save(Item item) {
        return crudRepository.save(item);
    }

    public boolean delete(int id) {
        return crudRepository.delete(id) != 0;
    }

    public Item get(int id) {
        return crudRepository.findById(id).orElse(null);
    }

    public List<Item> getAll() {
        return crudRepository.findAll(SORT_NAME);
    }
}
