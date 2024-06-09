package dao;

import entity.Good;

import java.util.List;

public interface GoodDao {
    void add(Good r);
    void remove(String name);
    List<Good> getAll(String sql);
    Good getByName(String name);
}
