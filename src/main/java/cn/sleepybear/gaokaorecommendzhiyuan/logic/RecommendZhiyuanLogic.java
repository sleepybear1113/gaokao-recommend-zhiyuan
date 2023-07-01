package cn.sleepybear.gaokaorecommendzhiyuan.logic;

import cn.sleepybear.gaokaorecommendzhiyuan.constants.Constants;
import cn.sleepybear.gaokaorecommendzhiyuan.dto.PersonInfoDto;
import cn.sleepybear.gaokaorecommendzhiyuan.dto.RecentYearStatusDto;
import cn.sleepybear.gaokaorecommendzhiyuan.dto.ZhiyuanDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * There is description
 *
 * @author sleepybear
 * @date 2023/06/30 17:35
 */
@Component
@Slf4j
public class RecommendZhiyuanLogic {

    public static List<ZhiyuanDto> recommendZhiyuan(PersonInfoDto personInfoDto) {
        List<ZhiyuanDto> recommendList = new ArrayList<>();

        if (personInfoDto == null || personInfoDto.invalid()) {
            log.info("入参不合法！");
            return recommendList;
        }
        personInfoDto.build();

        for (ZhiyuanDto zhiyuanDto : Constants.zhiyuanList) {
            List<RecentYearStatusDto> recentYearStatusList = zhiyuanDto.getRecentYearStatusList();
            if (CollectionUtils.isEmpty(recentYearStatusList)) {
                continue;
            }
            String rank = recentYearStatusList.get(0).getRank();
            if (StringUtils.isBlank(rank) || !StringUtils.isNumeric(rank)) {
                continue;
            }

            int rankNum = Integer.parseInt(rank);
            if (rankNum > personInfoDto.getMaxRank() || rankNum < personInfoDto.getMinRank()) {
                // 名次超范围
                continue;
            }

            if (!zhiyuanDto.matchXuanKao(personInfoDto.getXuanKaoCombinations())) {
                continue;
            }

            if (CollectionUtils.isNotEmpty(personInfoDto.getRegionList())) {
                if (!zhiyuanDto.matchRegion(personInfoDto.getRegionList())) {
                    continue;
                }
            }

            recommendList.add(zhiyuanDto);
        }

        recommendList.sort(Comparator.comparing(o -> Integer.parseInt(o.getRecentYearStatusList().get(0).getRank())));
        return recommendList;
    }
}
