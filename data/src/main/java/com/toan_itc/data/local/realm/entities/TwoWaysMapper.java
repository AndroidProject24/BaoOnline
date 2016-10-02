package com.toan_itc.data.local.realm.entities;

import java.util.List;

public interface TwoWaysMapper<M, E> extends Mapper<M, E> {
    M inverseMap(E model);

    List<M> inverseMap(List<E> listModel);
}
