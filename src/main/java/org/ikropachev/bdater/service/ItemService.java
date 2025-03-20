package org.ikropachev.bdater.service;

import org.ikropachev.bdater.model.Item;
import org.ikropachev.bdater.repository.datajpa.DataJpaItemRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

import static org.ikropachev.bdater.util.ValidationUtil.checkNotFoundWithId;

@Service("itemService")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ItemService {
    private final DataJpaItemRepository repository;

    public ItemService(DataJpaItemRepository repository) {
        this.repository = repository;
    }

    @CacheEvict(value = "item", allEntries = true)
    public Item create(Item item) {
        Assert.notNull(item, "item must not be null");
        return repository.save(item);
    }

    @CacheEvict(value = "item", allEntries = true)
    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id), id);
    }

    public Item get(int id) {
        return checkNotFoundWithId(repository.get(id), id);
    }

    public List<Item> getAll() {
        return repository.getAll();
    }

    @CacheEvict(value = "item", allEntries = true)
    public void update(Item item) {
        Assert.notNull(item, "item must not be null");
        repository.save(item);
    }
}
