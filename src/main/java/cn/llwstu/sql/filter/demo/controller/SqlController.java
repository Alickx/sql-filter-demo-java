package cn.llwstu.sql.filter.demo.controller;

import cn.llwstu.sql.filter.demo.service.SqlService;
import cn.llwstu.sql.filter.demo.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author: Alickx
 * @Date: 2022/06/23/19:08
 * @Description:
 */
@RestController
@RequestMapping("/sql")
public class SqlController {

    @Autowired
    private SqlService sqlService;

    @CrossOrigin
    @GetMapping("/getColumns")
    public Result getColumns() {
        return sqlService.getColumns();
    }

    @CrossOrigin
    @PostMapping("/execute")
    public Result execute(@RequestBody Map<String,String> map) {
        return sqlService.execute(map);
    }

}
