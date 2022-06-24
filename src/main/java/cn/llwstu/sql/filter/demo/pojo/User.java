package cn.llwstu.sql.filter.demo.pojo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author: Alickx
 * @Date: 2022/06/23/19:25
 * @Description:
 */
@Data
public class User {

    private Integer id;

    private String username;

    private String registeredSource;

    private int age;

    private BigDecimal totalOrder;

}
