package com.toan_itc.data.local.realm.entities;

/**
 * Created by Toan.IT
 * Date: 14/07/2016
 */

public interface Mapper<M, D> {
    D modelToData(M model);
    M dataToModel(D data);
}
