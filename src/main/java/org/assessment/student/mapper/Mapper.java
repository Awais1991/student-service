package org.assessment.student.mapper;

public interface Mapper<D, E, U> {

    E mapToEntity(D d);
    E updateEntity(U u, E e);
    D mapToDto(E e);

}
