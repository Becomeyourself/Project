package dao;

import entity.Buyer;
import entity.cate;

import java.util.List;

public interface cateDao {
    void add(cate r);
    void remove(String name);
    List<cate> getAll(String sql);
    cate getByName(String name);
}
