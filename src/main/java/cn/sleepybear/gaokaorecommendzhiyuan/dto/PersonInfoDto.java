package cn.sleepybear.gaokaorecommendzhiyuan.dto;

import lombok.Data;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * There is description
 *
 * @author sleepybear
 * @date 2023/06/30 15:55
 */
@Data
public class PersonInfoDto {
    private Integer score;
    private Integer rank;
    private List<String> xuanKao;

    private Integer minRank;
    private Integer maxRank;

    private List<String> xuanKaoCombinations;

    /**
     * 0 - 不限制<br/>
     * 1 - 不看不限专业<br/>
     * 2 - 仅看同时符合要求的专业<br/>
     */
    private Integer xuanKaoChooseType;
    private String maxFee;
    private String minFee;
    private String minCount;
    private String maxCount;

    private List<List<String>> regionList;

    public boolean invalid() {
        return rank == null || rank <= 0;
    }

    public void build() {
        buildRank();
        buildXuanKaoCombinations();
    }

    public void buildXuanKaoCombinations() {
        xuanKaoCombinations = new ArrayList<>();
        if (CollectionUtils.isEmpty(xuanKao)) {
            return;
        }

        if (xuanKao.contains("10")) {
            xuanKao.remove("10");
            xuanKao.add("0");
        }
        xuanKao = new ArrayList<>(new HashSet<>(xuanKao));
        xuanKao.sort(String::compareTo);
        if ("0".equals(xuanKao.get(0))) {
            xuanKao.add(xuanKao.remove(0));
        }

        if (xuanKaoChooseType == 0) {
            xuanKaoCombinations.add("");
        }

        xuanKaoCombinations.add(String.join("", xuanKao));
        if (xuanKaoChooseType == 2) {
            // 全部选考
            return;
        }

        // 生成选考组合
        if (xuanKao.size() == 3) {
            xuanKaoCombinations.add(xuanKao.get(0) + xuanKao.get(1));
            xuanKaoCombinations.add(xuanKao.get(0) + xuanKao.get(2));
            xuanKaoCombinations.add(xuanKao.get(1) + xuanKao.get(2));
        }
        xuanKaoCombinations.addAll(xuanKao);
    }

    public void buildRank() {
        if (this.minRank == null) {
            this.minRank = rank - (Math.min(rank * 3 / 10, 20000));
        }
        if (this.maxRank == null) {
            this.maxRank = rank + (Math.min(rank * 5 / 10, 50000));
        }

        if (this.minRank > this.maxRank) {
            this.minRank = null;
            this.maxRank = null;
            build();
        }
    }
}
