package zone.hfy.shirospringboot.service.impl;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zone.hfy.shirospringboot.entity.User;
import zone.hfy.shirospringboot.mapper.IUserMapper;
import zone.hfy.shirospringboot.service.IUserService;
import zone.hfy.shirospringboot.utils.SaltUtils;

/**
 * @author YoungNet
 * @date 2022/1/7 11:25
 */
@Service
//事务，回滚
@Transactional
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserMapper userMapper;

    @Override
    public void register(User user) {
        String salt = SaltUtils.getSalt(8);
        user.setSalt(salt);
        Md5Hash md5Hash = new Md5Hash(user.getPassword(), salt,1024);
        user.setPassword(md5Hash.toHex());

        userMapper.saveUser(user);
    }

    @Override
    public User findByUserName(String username) {
        return userMapper.findByUserName(username);
    }
}
