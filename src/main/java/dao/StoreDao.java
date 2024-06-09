package dao;

import entity.Store;

import java.util.List;

public interface StoreDao {
    void add(Store s);
    void remove(String name,String number);
    List<Store> getAll(String sql,String name);
    Store getByNumber(String number,String name);
    Double getByName(String name);
    public Store getByNamea(String name,String number);
}
