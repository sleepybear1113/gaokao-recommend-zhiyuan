package cn.sleepybear.gaokaorecommendzhiyuan.logic;

import cn.sleepybear.gaokaorecommendzhiyuan.dto.PersonInfoDto;
import cn.sleepybear.gaokaorecommendzhiyuan.dto.ZhiyuanDto;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * There is description
 *
 * @author sleepybear
 * @date 2023/06/30 19:20
 */
@Component
public class TestLogic {

    public void test() {
        PersonInfoDto personInfoDto = new PersonInfoDto();
        personInfoDto.setRank(15000);
        personInfoDto.setXuanKao(List.of("7", "9", "8"));
        personInfoDto.setXuanKaoChooseType(0);

        personInfoDto.setRegionList(List.of(List.of("浙江", "安吉", "兰溪", "慈溪", "桐庐", "上虞", "诸暨", "海宁"), List.of("北京", "北京"), List.of("四川", "成都"), List.of("天津", "天津"), List.of("河北", "保定", "廊坊", "三河", "黄骅", "燕郊", "承德", "秦皇岛", "邯郸", "石家庄", "张家口", "唐山")));
        personInfoDto.build();

        List<ZhiyuanDto> recommendedZhiyuanList = RecommendZhiyuanLogic.recommendZhiyuan(personInfoDto);
        System.out.println(recommendedZhiyuanList.size());
        recommendedZhiyuanList.forEach(System.out::println);
        System.out.println(recommendedZhiyuanList.size());
    }
}
