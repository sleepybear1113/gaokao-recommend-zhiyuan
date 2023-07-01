package cn.sleepybear.gaokaorecommendzhiyuan.dto;

import lombok.Data;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * There is description
 *
 * @author sleepybear
 * @date 2023/06/30 10:21
 */
@Data
public class ZhiyuanDto {
    /**
     * 层次，本科/专科
     */
    private String level;

    /**
     * 科类名称，普通类、艺术类、体育类等
     */
    private String klType;

    /**
     * 轮次名称，普通类平行录取、体育类、普通类提前录取等
     */
    private String lcType;

    private String schoolId;
    private String schoolName;

    /**
     * 学费
     */
    private String fee;
    private String zyId;
    private String zyName;
    private String province;
    private String city;

    /**
     * 选考要求<br/>
     * 4-政治、5-历史、6-地理、7-物理、8-化学、9-生物、0-技术<br/>
     * 为空则不限制，选考代码需要排序后放入，如：4,5 表示 4、5 其中满足一个即可，078 表示 需要同时报考 078 才可以
     */
    private Set<String> xuanKao;

    /**
     * 计划数
     */
    private Integer count;

    /**
     * 备注
     */
    private String msg;

    /**
     * 近年录取情况，key = 2022、2021 等
     */
    private List<RecentYearStatusDto> recentYearStatusList;

    public boolean matchXuanKao(List<String> xuanKaoCombinations) {
        if (CollectionUtils.isEmpty(xuanKaoCombinations)) {
            // 如果没有选科
            // 判断专业有选科要求
            return CollectionUtils.isEmpty(xuanKao);
        }

        // 只看选考
        for (String xuanKaoCombination : xuanKaoCombinations) {
            if (xuanKao.contains(xuanKaoCombination) || (StringUtils.isBlank(xuanKaoCombination) && CollectionUtils.isEmpty(xuanKao))) {
                return true;
            }
        }

        return false;
    }

    public static List<ZhiyuanDto> buildFromExcelImportDto(List<ZhiyuanExcelImport2023Dto> zhiyuanExcelImport2023DtoList) {
        List<ZhiyuanDto> list = new ArrayList<>();
        if (CollectionUtils.isEmpty(zhiyuanExcelImport2023DtoList)) {
            return list;
        }

        for (ZhiyuanExcelImport2023Dto dto : zhiyuanExcelImport2023DtoList) {
            if (!StringUtils.equals(dto.getKlType(), "普通类") || !StringUtils.equals(dto.getLcType(), "普通类平行录取")) {
                continue;
            }
            list.add(buildFromExcelImportDto(dto));
        }

        return list;
    }

    public static ZhiyuanDto buildFromExcelImportDto(ZhiyuanExcelImport2023Dto zhiyuanExcelImport2023Dto) {
        ZhiyuanDto zhiyuanDto = new ZhiyuanDto();
        zhiyuanDto.setLevel(zhiyuanExcelImport2023Dto.getLevel());
        zhiyuanDto.setKlType(zhiyuanExcelImport2023Dto.getKlType());
        zhiyuanDto.setLcType(zhiyuanExcelImport2023Dto.getLcType());
        zhiyuanDto.setSchoolId(zhiyuanExcelImport2023Dto.getSchoolId());
        zhiyuanDto.setSchoolName(zhiyuanExcelImport2023Dto.getSchoolName());
        zhiyuanDto.setFee(zhiyuanExcelImport2023Dto.getFee());
        zhiyuanDto.setZyId(zhiyuanExcelImport2023Dto.getZyId());
        zhiyuanDto.setZyName(zhiyuanExcelImport2023Dto.getZyName());
        zhiyuanDto.setProvince(zhiyuanExcelImport2023Dto.getProvince());
        zhiyuanDto.setCity(zhiyuanExcelImport2023Dto.getCity());
        zhiyuanDto.setMsg(zhiyuanExcelImport2023Dto.getMsg());

        zhiyuanDto.setCount(Integer.valueOf(zhiyuanExcelImport2023Dto.getCount()));

        // 选考转 Set
        String xuanKao = zhiyuanExcelImport2023Dto.getXuanKao();
        Set<String> xuanKaoSet = new HashSet<>();
        zhiyuanDto.setXuanKao(xuanKaoSet);
        if (!StringUtils.equalsIgnoreCase(xuanKao, "all")) {
            String[] xuanKaoArray = xuanKao.split("\\|");
            xuanKaoSet.addAll(Arrays.asList(xuanKaoArray));
            xuanKaoSet.remove("");
        }

        // 构建历年录取分数排名
        zhiyuanDto.recentYearStatusList = new ArrayList<>();
        RecentYearStatusDto[] recentYearStatusDtoList = {new RecentYearStatusDto(), new RecentYearStatusDto(), new RecentYearStatusDto()};
        zhiyuanDto.recentYearStatusList.addAll(Arrays.asList(recentYearStatusDtoList).subList(0, years().length));
        recentYearStatusDtoList[0].setRank(zhiyuanExcelImport2023Dto.getYear1rank());
        recentYearStatusDtoList[0].setCount(zhiyuanExcelImport2023Dto.getYear1count());
        recentYearStatusDtoList[0].setScore(zhiyuanExcelImport2023Dto.getYear1score());
        recentYearStatusDtoList[0].setMatch(zhiyuanExcelImport2023Dto.getYear1match());
        recentYearStatusDtoList[1].setRank(zhiyuanExcelImport2023Dto.getYear2rank());
        recentYearStatusDtoList[1].setCount(zhiyuanExcelImport2023Dto.getYear2count());
        recentYearStatusDtoList[1].setScore(zhiyuanExcelImport2023Dto.getYear2score());
        recentYearStatusDtoList[1].setMatch(zhiyuanExcelImport2023Dto.getYear2match());
        recentYearStatusDtoList[2].setRank(zhiyuanExcelImport2023Dto.getYear3rank());
        recentYearStatusDtoList[2].setCount(zhiyuanExcelImport2023Dto.getYear3count());
        recentYearStatusDtoList[2].setScore(zhiyuanExcelImport2023Dto.getYear3score());
        recentYearStatusDtoList[2].setMatch(zhiyuanExcelImport2023Dto.getYear3match());

        return zhiyuanDto;
    }

    public static String[] years() {
        return new String[]{"2022", "2021", "2020"};
    }

    public boolean matchRegion(List<List<String>> regionList) {
        if (CollectionUtils.isEmpty(regionList)) {
            return true;
        }

        for (List<String> region : regionList) {
            if (StringUtils.equals(region.get(0), province)) {
                if (region.size() == 1) {
                    return true;
                }
                for (String city : region) {
                    if (StringUtils.equals(city, this.city)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
}
