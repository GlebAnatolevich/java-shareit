package ru.practicum.shareit.booking.service;

import ru.practicum.shareit.booking.enums.BookingRequestState;
import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.dto.UpdateBookingDto;
import ru.practicum.shareit.booking.dto.NewBookingDto;

import java.util.List;

public interface BookingService {

    BookingDto addBooking(long userId, NewBookingDto request);

    BookingDto updateBookingStatus(UpdateBookingDto updateRequest);

    BookingDto getBooking(long userId, long bookingId);

    List<BookingDto> getBookerBookingsOfState(long bookerId, BookingRequestState state);

    List<BookingDto> getOwnerBookingsOfState(long ownerId, BookingRequestState state);
}
