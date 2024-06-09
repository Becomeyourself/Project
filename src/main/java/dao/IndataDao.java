package dao;

import entity.Indata;

import java.util.List;

public interface IndataDao {
    void add(Indata in);
    void remove(String name,String storename);
    public List<Indata> getAll(String sql);
}
