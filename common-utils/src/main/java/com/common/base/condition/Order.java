package com.common.base.condition;

import com.common.base.enums.Direction;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class Order implements Serializable {
    /**
     * 属性
     */
    private String property;

    /**
     * 方向
     */
    private Direction direction;


    /**
     * 返回递增排序
     *
     * @param property 属性
     * @return 递增排序
     */
    public static Order asc(String property) {
        return new Order(property, Direction.asc);
    }

    /**
     * 返回递减排序
     *
     * @param property 属性
     * @return 递减排序
     */
    public static Order desc(String property) {
        return new Order(property, Direction.desc);
    }

}
