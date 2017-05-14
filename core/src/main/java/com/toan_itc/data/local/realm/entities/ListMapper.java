package com.toan_itc.data.local.realm.entities;

import java.util.List;

public interface ListMapper<M, E> {
    E map(M model);
    List<E> map(List<M> listModel);
}
