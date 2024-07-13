package cn.south.express.express.generalFile;

import cn.south.express.express.receiveexpress.entity.ReceiveExpress;
import cn.south.express.express.receiveexpress.param.ReceiveExpressParam;
import cn.south.express.express.receiveexpress.service.impl.ReceiveExpressServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 通用工具类
 */
@Component
public class GeneralClass {

    /**
     * 数字转文字
     */
    public String switchWZ(String a){
        if(a != null && a.equals("0")){
            a = "未领取";
        }else if (a != null && a.equals("1")){
            a = "已领取";
        }
        return a;
    }

    /**
     * 文字转数字
     */
    public void switchSZ(ReceiveExpressParam receiveExpressParam){
        String a = receiveExpressParam.getExpressStatus();
        if(a != null && a.equals("未领取")){
            receiveExpressParam.setExpressStatus("0");
        }else if (a != null && a.equals("已领取")){
            receiveExpressParam.setExpressStatus("1");
        }else {
            receiveExpressParam.clearExpressStatus();
        }
        String b = receiveExpressParam.getExpressType();
        if (b != null && b.equals("全部")){
            receiveExpressParam.clearExpressType();
        }
    }

    /**
     * null转文字
     */
    public String switchWu(String a){
        if(a.equals(null) || a.equals("")){
            a = "无";
        }
        return a;
    }

    /**
     * 文字转null
     */
    public String switchKong(String a){
        if(a.equals("无")){
            a = null;
        }
        return a;
    }
    /**
     * 获取昨日的日期
     */
    public String yesterdayDate(){
        LocalDate today = LocalDate.now();  // 获取当前日期
        LocalDate yesterday = today.minusDays(1);  // 减去1天得到昨天的日期
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");  // 定义日期格式
        String yesterdayStr = yesterday.format(formatter);  // 格式化昨天的日期
        return yesterdayStr;
    }

    /**
     * 获取3日的日期
     */
    public String threeDayDate(){
        LocalDate today = LocalDate.now();  // 获取当前日期
        LocalDate sevenDay = today.minusDays(3);  // 减去3天得到昨天的日期
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");  // 定义日期格式
        String sevenDayStr = sevenDay.format(formatter);  // 格式化昨天的日期
        return sevenDayStr;
    }

    /**
     * 获取15日的日期
     */
    public String fifteenDayDate(){
        LocalDate today = LocalDate.now();  // 获取当前日期
        LocalDate sevenDay = today.minusDays(15);  // 减去15天得到昨天的日期
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");  // 定义日期格式
        String sevenDayStr = sevenDay.format(formatter);  // 格式化昨天的日期
        return sevenDayStr;
    }

    /**
     * 获取今日的日期
     */
    public String nowDate(){
        LocalDate today = LocalDate.now();  // 获取当前日期
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");  // 定义日期格式
        String todayStr = today.format(formatter);  // 格式化昨天的日期
        return todayStr;
    }

    /**
     * 获取5个月前的日期
     */
    public String fiveMonthDate(){
        LocalDate today = LocalDate.now();  // 获取当前日期
        LocalDate fiveMonthsAgo = today.minus(Period.ofMonths(5));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");  // 定义日期格式
        String fiveTodayStr = fiveMonthsAgo.format(formatter);  // 格式化昨天的日期
        return fiveTodayStr;
    }

    /**
     * 获取1个月的日期
     */
    public String oneMonthDate(){
        LocalDate today = LocalDate.now();  // 获取当前日期
        LocalDate fiveMonthsAgo = today.minus(Period.ofMonths(1));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");  // 定义日期格式
        String oneTodayStr = fiveMonthsAgo.format(formatter);  // 格式化昨天的日期
        return oneTodayStr;
    }

    /**
     * 获取当前日期和时间
     */
    public String nowDateTime(){
        // 创建 Date 对象获取当前日期和时间
        Date now = new Date();

        // 创建 SimpleDateFormat 对象，定义日期和时间格式
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // 格式化 Date 对象为字符串
        String currentDateAndTime = dateFormat.format(now);
        return currentDateAndTime;
    }

    @Resource
    private ReceiveExpressServiceImpl receiveExpressServiceImpl;

    /**
     * 昨日快递数据
     */
    public Integer zuoriData(String a){
        QueryWrapper<ReceiveExpress> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(ReceiveExpress::getExpressType, a);
        queryWrapper.lambda().apply("receive_time like '"+yesterdayDate()+"%'");
        Integer b = receiveExpressServiceImpl.count(queryWrapper);
        return b;
    }
    /**
     * 今日快递数据
     */
    public Integer jinriData(String a){
        QueryWrapper<ReceiveExpress> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(ReceiveExpress::getExpressType, a);
        queryWrapper.lambda().apply("receive_time like '"+nowDate()+"%'");
        Integer b = receiveExpressServiceImpl.count(queryWrapper);
        return b;
    }

    /**
     * 待取快递数据
     */
    public Integer daiquData(String a){
        QueryWrapper<ReceiveExpress> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(ReceiveExpress::getExpressType, a);
        queryWrapper.lambda().eq(ReceiveExpress::getExpressStatus,"0");
        Integer b = receiveExpressServiceImpl.count(queryWrapper);
        return b;
    }
}
