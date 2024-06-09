package dao;

import entity.Power;

import java.util.List;

public interface PowerDao {
    List<Power> getAll(String sql);
}
