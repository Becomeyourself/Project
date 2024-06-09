package dao;

import entity.Seller;

import java.util.List;

public interface SellerDao {
    void add(Seller s);
    void remove(String name);
    List<Seller> getAll(String sql);
    Seller getByName(String name);
}
