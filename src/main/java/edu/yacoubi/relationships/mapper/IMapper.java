package edu.yacoubi.relationships.mapper;

import java.util.List;

public interface IMapper<A, B> {
    B mapTo(A a);
    List<B> mapTo(List<A> a);
    A mapFrom(B b);
}
