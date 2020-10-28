package model.Po;

import lombok.Data;

/**
 * Notice: 胶囊类
 *
 * @author xuxu
 * @version 1.0
 * @date 2020/10/28
 * @since 1.0
 */
@Data
public class Capsule {
    private String capsuleId;
    private String capsuleContent;
    private String capsuleMainId;
    private String capsuleTime;
    private int capsuleIf;
}
