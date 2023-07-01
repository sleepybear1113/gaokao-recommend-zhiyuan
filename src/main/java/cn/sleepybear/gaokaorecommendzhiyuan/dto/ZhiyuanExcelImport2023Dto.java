package cn.sleepybear.gaokaorecommendzhiyuan.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * There is description
 *
 * @author sleepybear
 * @date 2023/06/30 09:41
 */
@Data
public class ZhiyuanExcelImport2023Dto {
    /**
     * 层次，本科/专科
     */
    @ExcelProperty("层次")
    private String level;

    /**
     * 科类名称，普通类、艺术类、体育类等
     */
    @ExcelProperty("科类名称")
    private String klType;

    /**
     * 轮次名称，普通类平行录取、体育类、普通类提前录取等
     */
    @ExcelProperty("轮次名称")
    private String lcType;

    @ExcelProperty("院校代码1")
    private String schoolId;
    @ExcelProperty("院校名称")
    private String schoolName;

    /**
     * 学费
     */
    @ExcelProperty("收费标准")
    private String fee;
    @ExcelProperty("专业代码1")
    private String zyId;
    @ExcelProperty("专业名称")
    private String zyName;
    @ExcelProperty("省份")
    private String province;
    @ExcelProperty("城市")
    private String city;

    /**
     * 选考要求<br/>
     * 4-政治、5-历史、6-地理、7-物理、8-化学、9-生物、0-技术
     */
    @ExcelProperty("选考要求代码")
    private String xuanKao;

    /**
     * 计划数
     */
    @ExcelProperty("计划数")
    private String count;

    /**
     * 备注
     */
    @ExcelProperty("备注u")
    private String msg;

    @ExcelProperty("2022计划数")
    private String year1count;
    @ExcelProperty("2022位次")
    private String year1rank;
    @ExcelProperty("2022分数")
    private String year1score;
    @ExcelProperty("2022往年数据匹配情况")
    private String year1match;

    @ExcelProperty("2021计划数")
    private String year2count;
    @ExcelProperty("2021位次")
    private String year2rank;
    @ExcelProperty("2021分数")
    private String year2score;
    @ExcelProperty("2021往年数据匹配情况")
    private String year2match;

    @ExcelProperty("2020计划数")
    private String year3count;
    @ExcelProperty("2020位次")
    private String year3rank;
    @ExcelProperty("2020分数")
    private String year3score;
    @ExcelProperty("2020往年数据匹配情况")
    private String year3match;
}
