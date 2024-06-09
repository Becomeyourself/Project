package dao;
import java.util.List;
import entity.Role;
import entity.User;

public interface RoleDao {
	void add(Role r);
	void remove(String rname);
	List<Role>  getAll(String sql);
	Role getByName(String rname);
}
