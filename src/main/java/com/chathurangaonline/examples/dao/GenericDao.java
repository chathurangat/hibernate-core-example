package com.chathurangaonline.examples.dao;

import java.util.List;

public interface GenericDao<T>{

    /**
     * <p>
     *     find by id
     * </p>
     * @param id
     * @return
     */
    T findById(Long id);

    void save(T t);

    List<T> findAll();

    Integer countAll();

    void delete(T t);

}
