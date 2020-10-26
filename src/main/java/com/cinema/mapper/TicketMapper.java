package com.cinema.mapper;

import com.cinema.model.Ticket;
import com.cinema.model.dto.TicketResponseDto;
import org.springframework.stereotype.Component;

@Component
public class TicketMapper {

    public TicketResponseDto fromEntityToDto(Ticket ticket) {
        TicketResponseDto ticketResponseDto = new TicketResponseDto();
        ticketResponseDto.setTicketId(ticket.getId());
        ticketResponseDto.setUserId(ticket.getUser().getId());
        ticketResponseDto.setMovieSessionMovieTitle(ticket.getMovieSession().getMovie().getTitle());
        return ticketResponseDto;
    }
}
