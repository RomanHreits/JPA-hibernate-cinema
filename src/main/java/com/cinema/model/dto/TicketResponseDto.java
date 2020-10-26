package com.cinema.model.dto;

public class TicketResponseDto {
    private Long ticketId;
    private String movieSessionMovieTitle;
    private Long userId;

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    public String getMovieSessionMovieTitle() {
        return movieSessionMovieTitle;
    }

    public void setMovieSessionMovieTitle(String movieSessionMovieTitle) {
        this.movieSessionMovieTitle = movieSessionMovieTitle;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
