package zone.hfy.shirospringboot.shiro.realms;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import zone.hfy.shirospringboot.entity.Perms;
import zone.hfy.shirospringboot.entity.Role;
import zone.hfy.shirospringboot.entity.User;
import zone.hfy.shirospringboot.service.IUserService;

import java.util.Collection;
import java.util.List;

/**
 * @author YoungNet
 * @date 2022/1/6 17:11
 */
public class CustomerRealm extends AuthorizingRealm {

    @Autowired
    IUserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String primaryPrincipal = (String) principalCollection.getPrimaryPrincipal();
        User user = userService.findRolesByUserName(primaryPrincipal);
        if(!CollectionUtils.isEmpty(user.getRoles())){
            SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
            user.getRoles().forEach(role -> {
                simpleAuthorizationInfo.addRole(role.getName());
                List<Perms> perms = userService.findPermsByRoleId(role.getId());
                if(!CollectionUtils.isEmpty(perms) && perms.get(0)!=null){
                    perms.forEach(perm ->{
                        simpleAuthorizationInfo.addStringPermission(perm.getName());
                    });
                }
            });
            return simpleAuthorizationInfo;
        }
        /*if("hfy".equals(primaryPrincipal)){
            SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
            simpleAuthorizationInfo.addRole("user");
            //对用户角色下的所有资源具有所有操作权限
            simpleAuthorizationInfo.addStringPermission("user:add:*");
            simpleAuthorizationInfo.addStringPermission("user:update:01");
            return simpleAuthorizationInfo;
        }*/
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("===============");
        String principal = (String) authenticationToken.getPrincipal();
        User user = userService.findByUserName(principal);
        if(!ObjectUtils.isEmpty(user)){
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(user.getUsername(),
                    user.getPassword(),
                    ByteSource.Util.bytes(user.getSalt()),
                    this.getName());
            return simpleAuthenticationInfo;
        }
        return null;
    }
}
