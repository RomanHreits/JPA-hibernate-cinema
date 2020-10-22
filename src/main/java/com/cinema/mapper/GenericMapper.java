package com.cinema.mapper;

public interface GenericMapper<T, K, V> {
    T mapFromReqDtoToEntity(K dto);

    V mapToRespDto(T entity);
}
