package zone.hfy.shirospringboot.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author YoungNet
 * @date 2022/1/7 15:52
 */
@Controller
@RequestMapping("/order/")
public class OrderController {
    @RequestMapping("save")
    @RequiresRoles("user")//shiro判断角色注解
    @RequiresPermissions("user:update:01")
    //@RequiresRoles(value = {"admin", "user"})
    public String save(){
        Subject subject = SecurityUtils.getSubject();
        if(subject.hasRole("admin")){
            System.out.println("保存订单");
        }
        else{
            System.out.println("无权访问");
        }
        return "redirect:/index.jsp";
    }
}
