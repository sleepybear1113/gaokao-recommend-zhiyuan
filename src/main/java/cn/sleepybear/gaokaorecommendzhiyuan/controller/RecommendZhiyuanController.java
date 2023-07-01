package cn.sleepybear.gaokaorecommendzhiyuan.controller;

import cn.sleepybear.gaokaorecommendzhiyuan.dto.PersonInfoDto;
import cn.sleepybear.gaokaorecommendzhiyuan.dto.ZhiyuanDto;
import cn.sleepybear.gaokaorecommendzhiyuan.logic.RecommendZhiyuanLogic;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * There is description
 *
 * @author sleepybear
 * @date 2023/06/30 20:45
 */
@RestController
@Slf4j
public class RecommendZhiyuanController {

    @Resource
    private RecommendZhiyuanLogic recommendZhiyuanLogic;

    @RequestMapping("/recommend")
    public List<ZhiyuanDto> recommend(@RequestBody PersonInfoDto personInfoDto) {
        List<ZhiyuanDto> zhiyuanDtoList = RecommendZhiyuanLogic.recommendZhiyuan(personInfoDto);
        log.info("推荐结果：{}", zhiyuanDtoList.size());
        return zhiyuanDtoList;
    }
}
