package dao;

import entity.Sell;

import java.util.List;

public interface SellDao {
    void add(Sell r);
    void remove(String rname);
    List<Sell> getAll(String sql);
    Sell getByName(String rname);
}
