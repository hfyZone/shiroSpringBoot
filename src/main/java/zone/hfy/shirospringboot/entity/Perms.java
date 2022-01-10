package zone.hfy.shirospringboot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author YoungNet
 * @date 2022/1/10 10:53
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class Perms {
    private String id;
    private String name;
    private String url;
}
