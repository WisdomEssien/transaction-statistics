package com.seerbit.transactionstatistics.repository;

import java.util.List;

public interface Database<ID, Entity> {

    Entity save(ID key, Entity item);

    Entity update(ID key, Entity item);

    Entity read(ID key);

    List<Entity> read(List<ID> keys);

    List<Entity> readAll();

    boolean delete(ID key);

    boolean delete();
}
