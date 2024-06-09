package dao;

import entity.User;
import entity.UserRole;

import java.util.List;

public interface UserRoleDao {
    void add(UserRole ur);
    List<UserRole> getAll(String sql);
    List<String> getByUser(String uname);
    List<String> getByRole(String rname);
    void removeUser(String uname);
    void removerRole(String rname);
}
