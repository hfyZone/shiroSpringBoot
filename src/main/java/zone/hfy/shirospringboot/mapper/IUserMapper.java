package zone.hfy.shirospringboot.mapper;

import org.apache.ibatis.annotations.Mapper;
import zone.hfy.shirospringboot.entity.User;

/**
 * @author YoungNet
 * @date 2022/1/7 11:17
 */
@Mapper
public interface IUserMapper {
    void saveUser(User user);
    User findByUserName(String username);
}
