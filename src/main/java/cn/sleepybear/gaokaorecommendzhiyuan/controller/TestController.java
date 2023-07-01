package cn.sleepybear.gaokaorecommendzhiyuan.controller;

import cn.sleepybear.gaokaorecommendzhiyuan.logic.TestLogic;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * There is description
 *
 * @author sleepybear
 * @date 2023/06/30 19:21
 */
@RestController
public class TestController {

    @Resource
    private TestLogic testLogic;

    @RequestMapping("/test/test")
    public void test() {
        testLogic.test();
    }
}
