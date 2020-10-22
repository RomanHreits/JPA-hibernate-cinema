package com.cinema.mapper;

public interface GenericMapper<T, K, V> {
    T mapFromReqDtoToEntity(K dto);

    K mapToReqDto(T entity);

    V mapToRespDto(T entity);
}
