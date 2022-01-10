package zone.hfy.shirospringboot.service;

import zone.hfy.shirospringboot.entity.Perms;
import zone.hfy.shirospringboot.entity.Role;
import zone.hfy.shirospringboot.entity.User;

import java.util.List;

/**
 * @author YoungNet
 * @date 2022/1/7 11:23
 */
public interface IUserService {
    //注册用户方法
    void register(User user);
    User findByUserName(String username);
    User findRolesByUserName(String username);
    List<Perms> findPermsByRoleId(String id);
}
