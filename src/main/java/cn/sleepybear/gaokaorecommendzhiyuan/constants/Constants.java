package cn.sleepybear.gaokaorecommendzhiyuan.constants;

import cn.sleepybear.gaokaorecommendzhiyuan.dto.ZhiyuanDto;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * There is description
 *
 * @author sleepybear
 * @date 2023/06/30 15:07
 */
@Slf4j
public class Constants {
    public static List<ZhiyuanDto> zhiyuanList = new ArrayList<>();

    public static final ThreadPoolExecutor THREAD_POOL = new ThreadPoolExecutor(10, 100, 10, TimeUnit.SECONDS, new SynchronousQueue<>(), r -> {
        Thread t = new Thread(r);
        t.setUncaughtExceptionHandler((t1, e) -> log.error(e.getMessage(), e));
        return t;
    }, new ThreadPoolExecutor.DiscardPolicy());
}
