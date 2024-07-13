package cn.south.express.express.receiveexpress.entity;

import lombok.Data;

import java.util.List;

/**
 * 统计数据类
 */
@Data
public class CountData {
    /**
    * 快递名称
    */
    private List<String> expressMC;

    /**
     * 快递今日数据
     */
    private List<Integer> nowData;

    /**
     * 快递剩余数据
     */
    private List<Integer> surplusData;

    /**
     * 柱状图数据
     */
    private List<Integer> histogramData;

    /**
     * 饼图名称
     */
    private List<String> pieName;

    /**
     * 饼图图数据
     */
    private List<Integer> pieChartData;
}
