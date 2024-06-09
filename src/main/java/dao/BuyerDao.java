package dao;

import entity.Buyer;

import java.util.List;

public interface BuyerDao {
    void add(Buyer r);
    void remove(String name);
    List<Buyer> getAll(String sql);
    Buyer getByName(String name);
}
