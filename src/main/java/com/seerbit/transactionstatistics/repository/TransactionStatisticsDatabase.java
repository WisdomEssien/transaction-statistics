package com.seerbit.transactionstatistics.repository;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import static com.seerbit.transactionstatistics.constant.AppConstant.INITIAL_DATA_SIZE;

@Component
public class TransactionStatisticsDatabase<ID, Entity> implements Database<ID, Entity> {

    private Map<ID, Entity> storage;

    public TransactionStatisticsDatabase(){
        this.storage = new ConcurrentHashMap<>(INITIAL_DATA_SIZE);
    }

    public Entity save(ID key, Entity item){
        if(Objects.nonNull(key) && Objects.nonNull(item)){
            this.storage.put(key, item);
            return this.read(key);
        }
        return null;
    }

    public Entity update(ID key, Entity item){
        return this.save(key, item);
    }

    public Entity read(ID key){
        if(Objects.nonNull(key)){
            return (Entity) this.storage.get(key);
        }
        return null;
    }

    public List<Entity> read(List<ID> keys){
        return this.storage.entrySet().stream()
                .filter(entry -> keys.contains(entry.getKey()))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }

    public List<Entity> readAll(){
        return new ArrayList<>(this.storage.values());
    }

    public boolean delete(ID key){
        if(Objects.nonNull(key)){
            this.storage.remove(key);
            return true;
        }
        return false;
    }

    public boolean delete(){
        this.storage.clear();
        return true;
    }

}
