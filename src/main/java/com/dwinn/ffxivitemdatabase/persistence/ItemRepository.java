package com.dwinn.ffxivitemdatabase.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Data access for the {@link ItemEntity}, using {@link CrudRepository}.
 *
 * @author David Winn
 */
@Repository
public interface ItemRepository extends CrudRepository<ItemEntity, Integer> {

	Optional<ItemEntity> findByName(String name);

	List<ItemEntity> findAll();
}
