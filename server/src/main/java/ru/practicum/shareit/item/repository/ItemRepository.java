package ru.practicum.shareit.item.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.model.User;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findItemsByOwnerId(long id);

    @Query("select it from items as it " +
            "where it.owner = :owner and " +
            "it.available and " +
            "  (lower(it.name) like %:value% or" +
            "   lower(it.description) like %:value%)")
    List<Item> findItemsByOwnerIdAndPattern(User owner, String value);

    Optional<Item> findByIdAndOwnerId(Long id, Long ownerId);

    List<Item> findItemsByRequestIdIn(Collection<Long> requestIds);

    List<Item> findItemsByRequestId(Long requestId);
}
