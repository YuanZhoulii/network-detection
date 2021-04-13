package io.renren.modules.net.to;

import lombok.Data;
import lombok.ToString;

/**
 * @author yuanzhouli
 * @date 2021/4/9 - 23:51
 */
@Data
@ToString
public class ProvinceFeignTo {
    private Integer provinceId;
    /**
     * 省份名
     */
    private String name;
}
