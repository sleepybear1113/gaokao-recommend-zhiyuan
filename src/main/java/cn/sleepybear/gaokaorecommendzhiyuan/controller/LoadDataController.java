package cn.sleepybear.gaokaorecommendzhiyuan.controller;

import cn.sleepybear.gaokaorecommendzhiyuan.advice.ResultCode;
import cn.sleepybear.gaokaorecommendzhiyuan.logic.LoadDataLogic;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RestController;

/**
 * There is description
 *
 * @author sleepybear
 * @date 2023/06/30 15:40
 */
@RestController
public class LoadDataController {

    @Resource
    private LoadDataLogic loadDataLogic;

    public ResultCode<String> loadData(String filename) {
        LoadDataLogic.loadExcelData(filename);
        return ResultCode.buildResult(filename);
    }
}
