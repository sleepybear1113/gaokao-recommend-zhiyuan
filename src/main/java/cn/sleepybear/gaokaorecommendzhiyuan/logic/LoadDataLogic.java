package cn.sleepybear.gaokaorecommendzhiyuan.logic;

import cn.sleepybear.gaokaorecommendzhiyuan.config.MyConfig;
import cn.sleepybear.gaokaorecommendzhiyuan.constants.Constants;
import cn.sleepybear.gaokaorecommendzhiyuan.dto.ZhiyuanDto;
import cn.sleepybear.gaokaorecommendzhiyuan.dto.ZhiyuanExcelImport2023Dto;
import cn.sleepybear.gaokaorecommendzhiyuan.utils.CommonUtil;
import com.alibaba.excel.EasyExcel;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

/**
 * There is description
 *
 * @author sleepybear
 * @date 2023/06/30 15:21
 */
@Component
@Slf4j
public class LoadDataLogic {
    @Resource
    private MyConfig myConfig;

    @PostConstruct
    public void loadData() {
        String dataDir = myConfig.getDataDir();
        List<File> fileList = CommonUtil.listFiles(dataDir);
        fileList.removeIf(file -> !StringUtils.containsIgnoreCase(file.getName(), ".xls"));

        if (CollectionUtils.size(fileList) > 1) {
            log.warn("本地Excel有多个，无法确定需要读取的文件！！！");
            return;
        } else if (CollectionUtils.isEmpty(fileList)) {
            log.warn("本地没有Excel文件可被读取！！！");
            return;
        }

        Constants.THREAD_POOL.execute(() -> loadExcelData(fileList.get(0).getPath()));
    }

    public static void loadExcelData(String path) {
        File file = new File(path);
        log.info("开始读取 Excel 文件，path = {}，大小 = {}", path, CommonUtil.getFileSize(file.length()));
        List<ZhiyuanExcelImport2023Dto> zhiyuanExcelImport2023DtoList = EasyExcel.read(path).head(ZhiyuanExcelImport2023Dto.class).sheet().doReadSync();
        List<ZhiyuanDto> zhiyuanDtoList = ZhiyuanDto.buildFromExcelImportDto(zhiyuanExcelImport2023DtoList);
        if (CollectionUtils.isNotEmpty(zhiyuanDtoList)) {
            Constants.zhiyuanList = zhiyuanDtoList;
            log.info("读取 Excel 文件成功，共读取 {} 条数据", zhiyuanDtoList.size());
        }
    }
}
