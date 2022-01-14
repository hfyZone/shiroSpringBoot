package zone.hfy.shirospringboot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @author YoungNet
 * @date 2022/1/7 11:15
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    private String  id;
    private String username;
    private String password;
    private String salt;

    List<Role> roles;
}
