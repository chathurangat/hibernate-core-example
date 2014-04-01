package com.chathurangaonline.examples.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<T,PK extends Serializable>{

    /**
     * <p>
     *     find by id
     * </p>
     * @param id
     * @return
     */
    T findById(Serializable id);

    void save(T t);

    List<T> findAll();

    Integer countAll();

    void delete(T t);

}
