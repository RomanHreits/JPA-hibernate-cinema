package com.cinema.model.dto.order;

import com.cinema.model.dto.TicketResponseDto;
import java.time.LocalDateTime;
import java.util.List;

public class OrderResponseDto {
    private Long id;
    private LocalDateTime localDateTime;
    private Long userId;
    private List<TicketResponseDto> tickets;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<TicketResponseDto> getTickets() {
        return tickets;
    }

    public void setTickets(List<TicketResponseDto> tickets) {
        this.tickets = tickets;
    }
}
