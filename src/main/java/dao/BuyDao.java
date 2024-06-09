package dao;

import entity.Buy;

import java.util.List;

public interface BuyDao {
    void add(Buy r);
    void remove(String rname);
    List<Buy> getAll(String sql);
    Buy getByName(String rname);
}
