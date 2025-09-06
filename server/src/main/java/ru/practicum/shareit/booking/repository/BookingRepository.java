package ru.practicum.shareit.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.practicum.shareit.booking.enums.BookingStatus;
import ru.practicum.shareit.booking.dto.BookingDate;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.item.model.Item;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Long> {


    List<Booking> findAllByBookerIdOrderByStart(Long bookerId);

    @Query("select b from bookings as b " +
            "where b.booker.id = :bookerId and " +
            "b.start <= :date and " +
            "b.end >= :date " +
            "order by b.start asc")
    List<Booking> findAllByBookerIdAndStartBeforeAndEndAfterOrderByStart(Long bookerId, LocalDateTime date);

    List<Booking> findAllByBookerIdAndStartAfterOrderByStart(Long bookerId, LocalDateTime date);

    List<Booking> findAllByBookerIdAndEndBeforeOrderByStart(Long bookerId, LocalDateTime date);

    List<Booking> findAllByBookerIdAndStatusOrderByStart(Long bookerId, BookingStatus bookingStatus);

    List<Booking> findAllByItemOwnerIdOrderByStart(Long itemOwnerId);

    @Query("select b from bookings as b " +
            "where b.item.owner.id = :itemOwnerId and " +
            "b.start <= :date and " +
            "b.end >= :date " +
            "order by b.start asc")
    List<Booking> findAllByItemOwnerIdAndStartBeforeAndEndAfterOrderByStart(Long itemOwnerId, LocalDateTime date);

    List<Booking> findAllByItemOwnerIdAndStartAfterOrderByStart(Long itemOwnerId, LocalDateTime date);

    List<Booking> findAllByItemOwnerIdAndEndBeforeOrderByStart(Long itemOwnerId, LocalDateTime date);

    List<Booking> findAllByItemOwnerIdAndStatus(Long itemOwnerId, BookingStatus bookingStatus);

    @Query("select max(b.start) from bookings as b where b.item = :item and b.start <= :date")
    Optional<LocalDateTime> findLastBookingDate(Item item, LocalDateTime date);

    @Query("""
            select b.item.id as item, max(b.start) as date
            from bookings as b
            where b.item in :list and b.start <= :date
            group by b.item
            """)
    List<BookingDate> findLastBookingDateForItems(Collection<Item> list, LocalDateTime date);

    @Query("select min(b.start) from bookings as b where b.item = :item and b.start > :date")
    Optional<LocalDateTime> findNextBookingDate(Item item, LocalDateTime date);

    @Query("""
            select b.item.id as item, min(b.start) as date
            from bookings as b
            where b.item in :list and b.start > :date
            group by b.item
            """)
    List<BookingDate> findNextBookingDateForItems(Collection<Item> list, LocalDateTime date);

    boolean existsBookingByItemIdAndBookerIdAndStatusIsAndEndBefore(long itemId, Long bookerId, BookingStatus status, LocalDateTime endBefore);
}
