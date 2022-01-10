package zone.hfy.shirospringboot.mapper;

import org.apache.ibatis.annotations.Mapper;
import zone.hfy.shirospringboot.entity.Perms;
import zone.hfy.shirospringboot.entity.Role;
import zone.hfy.shirospringboot.entity.User;

import java.util.List;

/**
 * @author YoungNet
 * @date 2022/1/7 11:17
 */
@Mapper
public interface IUserMapper {
    void saveUser(User user);
    User findByUserName(String username);
    User findRolesByUserName(String username);
    List<Perms> findPermsByRoleId(String id);
}
