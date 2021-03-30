package com.blaze.network.visual.vo.trend;

import com.blaze.network.visual.common.constant.VisualConstant;
import lombok.Data;
import lombok.ToString;
import org.springframework.stereotype.Component;

/**
 * @author yuanzhouli
 * @date 2021/3/24 - 18:05
 */
@Data
@ToString
@Component
public class CommonTrendVo {
    private String[] years= VisualConstant.YEARS;
}
