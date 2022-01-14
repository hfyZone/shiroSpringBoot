package zone.hfy.shirospringboot.config;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import zone.hfy.shirospringboot.shiro.cache.RedisCacheManager;
import zone.hfy.shirospringboot.shiro.realms.CustomerRealm;

import java.util.HashMap;
import java.util.Map;

/**
 * @author YoungNet
 * @date 2022/1/6 17:05
 */
@Configuration
public class ShiroConfig {
    //创建shiroFilter拦截请求
    @Bean
    public ShiroFilterFactoryBean getShiroFactoryBean(DefaultSecurityManager defaultSecurityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(defaultSecurityManager);
        //配置系统受限资源
        //配置系统公共资源
        Map<String, String> map = new HashMap<String,String>();
        map.put("/user/login","anon");//设置认证为公共资源
        map.put("/user/register","anon");//设置注册为公共资源
        map.put("/register.jsp", "anon");//放行页面
        map.put("/user/getImage", "anon");//放行验证码
        map.put("/**", "authc");//对所有页面接口加入认证
        //map.put("/index.jsp", "authc");//authc：请求此资源需要认证授权

        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        //跳转的默认认证界面路径
        shiroFilterFactoryBean.setLoginUrl("/login.jsp");

        return shiroFilterFactoryBean;
    }
    //创建安全管理器
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(Realm realm){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(realm);
        return defaultWebSecurityManager;
    }
    //自定义Realm
    @Bean
    public Realm getRealm(){
        CustomerRealm customerRealm = new CustomerRealm();
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
        hashedCredentialsMatcher.setHashIterations(1024);
        customerRealm.setCredentialsMatcher(hashedCredentialsMatcher);

        customerRealm.setCacheManager(new RedisCacheManager());
        customerRealm.setCachingEnabled(true);//开启全局缓存
        customerRealm.setAuthenticationCachingEnabled(true);//认证缓存
        customerRealm.setAuthenticationCacheName("authenticationCache");//默认名称：realm.authentication
        customerRealm.setAuthorizationCachingEnabled(true);//授权缓存
        customerRealm.setAuthorizationCacheName("authorizationCache");
        return customerRealm;
    }

}
