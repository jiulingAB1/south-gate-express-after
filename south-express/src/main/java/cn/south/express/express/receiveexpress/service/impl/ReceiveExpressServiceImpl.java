/*
Copyright [2020] [https://www.xiaonuo.vip]

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

Snowy采用APACHE LICENSE 2.0开源协议，您在使用过程中，需要注意以下几点：

1.请不要删除和修改根目录下的LICENSE文件。
2.请不要删除和修改Snowy源码头部的版权声明。
3.请保留源码和相关描述文件的项目出处，作者声明等。
4.分发源码时候，请注明软件出处 https://gitee.com/xiaonuobase/snowy
5.在修改包名，模块名称，项目代码等时，请注明软件出处 https://gitee.com/xiaonuobase/snowy
6.若您的项目无法满足以上几点，可申请商业授权，获取Snowy商业授权许可，请在官网购买授权，地址为 https://www.xiaonuo.vip
 */
package cn.south.express.express.receiveexpress.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.south.express.express.generalFile.GeneralClass;
import cn.south.express.express.receiveexpress.entity.CountData;
import cn.south.express.express.receiveexpress.entity.ReceiveExpress;
import cn.south.express.express.receiveexpress.enums.ReceiveExpressExceptionEnum;
import cn.south.express.express.receiveexpress.mapper.ReceiveExpressMapper;
import cn.south.express.express.receiveexpress.param.ReceiveExpressParam;
import cn.south.express.express.receiveexpress.service.ReceiveExpressService;
import cn.south.express.express.takeexpressdelivery.entity.TakeExpressDelivery;
import cn.south.express.express.takeexpressdelivery.service.impl.TakeExpressDeliveryServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vip.xiaonuo.core.exception.ServiceException;
import vip.xiaonuo.core.factory.PageFactory;
import vip.xiaonuo.core.pojo.page.PageResult;
import vip.xiaonuo.core.util.PoiUtil;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 收件模块service接口实现类
 *
 * @author wangxijun
 * @date 2024-05-10 21:10:08
 */
@Service
public class ReceiveExpressServiceImpl extends ServiceImpl<ReceiveExpressMapper, ReceiveExpress> implements ReceiveExpressService {

    @Resource
    private GeneralClass generalClass;

    @Resource
    private TakeExpressDeliveryServiceImpl takeExpressDeliveryServiceImpl;

    @Override
    public PageResult<ReceiveExpressParam> page(ReceiveExpressParam receiveExpressParam) {
        generalClass.switchSZ(receiveExpressParam);
        QueryWrapper<ReceiveExpress> queryWrapper = new QueryWrapper<>();
        if (ObjectUtil.isNotNull(receiveExpressParam)) {
            // 根据快递单号 查询
            if (ObjectUtil.isNotEmpty(receiveExpressParam.getExpressNumber())) {
                queryWrapper.like("r.express_number", receiveExpressParam.getExpressNumber());
            }
            // 根据收件人 查询
            if (ObjectUtil.isNotEmpty(receiveExpressParam.getExpressPeople())) {
                queryWrapper.like("r.express_people", receiveExpressParam.getExpressPeople());
            }
            // 根据快递状态 查询
            if (ObjectUtil.isNotEmpty(receiveExpressParam.getExpressStatus())) {
                queryWrapper.eq("r.express_status", receiveExpressParam.getExpressStatus());
            }
            // 根据收手机号 查询
            if (ObjectUtil.isNotEmpty(receiveExpressParam.getExpressTel())) {
                queryWrapper.like("r.express_tel", receiveExpressParam.getExpressTel());
            }
            // 根据快递类型 查询
            if (ObjectUtil.isNotEmpty(receiveExpressParam.getExpressType())) {
                queryWrapper.eq("r.express_type", receiveExpressParam.getExpressType());
            }
            // 根据收件时间 查询
            if (ObjectUtil.isNotEmpty(receiveExpressParam.getReceiveTime())) {
                queryWrapper.lambda().apply("r.receive_time like '"+receiveExpressParam.getReceiveTime()+"%'");
            }
            // 根据取件人 查询
            if (ObjectUtil.isNotEmpty(receiveExpressParam.getConsignee())) {
                queryWrapper.like("t.consignee", receiveExpressParam.getConsignee());
            }
            // 根据取手机号 查询
            if (ObjectUtil.isNotEmpty(receiveExpressParam.getConsigneeNumber())) {
                queryWrapper.like("t.consignee_number", receiveExpressParam.getConsigneeNumber());
            }
            // 根据取件时间 查询
            if (ObjectUtil.isNotEmpty(receiveExpressParam.getTakeTime())) {
                queryWrapper.lambda().apply("t.take_time like '"+ receiveExpressParam.getTakeTime()+"%'");
            }
        }
        PageFactory.defaultPage();
        Page<ReceiveExpressParam> page = this.baseMapper.ReceiveExpressPage(PageFactory.defaultPage(), queryWrapper);
        page.getRecords().forEach(e ->{
            if (ObjectUtil.isNotEmpty(e.getExpressStatus())){
                e.setExpressStatus(generalClass.switchWZ(e.getExpressStatus()));
            }
        });
        return new PageResult<>(page);
    }

    @Override
    public List<ReceiveExpress> list(ReceiveExpressParam receiveExpressParam) {
        return this.list();
    }

    @Override
    public List<ReceiveExpress> pickUpList(String expressNumber){
        QueryWrapper<ReceiveExpress> queryWrapper = new QueryWrapper<>();
        List<ReceiveExpress> list = new ArrayList<>();
        if (ObjectUtil.isNotEmpty(expressNumber)) {
            queryWrapper.lambda().eq(ReceiveExpress::getExpressNumber, expressNumber);
            queryWrapper.lambda().eq(ReceiveExpress::getExpressStatus, "0");
            list = this.list(queryWrapper);
            list.forEach(e ->{
                if (ObjectUtil.isNotEmpty(e.getExpressStatus())){
                    e.setExpressStatus(generalClass.switchWZ(e.getExpressStatus()));
                }
            });
        }
        return list;
    }

    @Override
    public void add(ReceiveExpressParam receiveExpressParam) {
        receiveExpressParam.nowDataAndTime();
        generalClass.switchSZ(receiveExpressParam);
        ReceiveExpress receiveExpress = new ReceiveExpress();
        BeanUtil.copyProperties(receiveExpressParam, receiveExpress);
        this.save(receiveExpress);
    }

    @Override
    public List<ReceiveExpress> addSuccessSelect(ReceiveExpressParam receiveExpressParam){
        generalClass.switchSZ(receiveExpressParam);
        QueryWrapper<ReceiveExpress> queryWrapper = new QueryWrapper<>();
        // 根据收件人手机号 查询
        if (ObjectUtil.isNotEmpty(receiveExpressParam.getExpressTel())) {
            queryWrapper.lambda().like(ReceiveExpress::getExpressTel, receiveExpressParam.getExpressTel());
        }
        queryWrapper.lambda().eq(ReceiveExpress::getExpressStatus, "0");
        List<ReceiveExpress> list = this.list(queryWrapper);
        list.forEach(e ->{
            if (ObjectUtil.isNotEmpty(e.getExpressStatus())){
                e.setExpressStatus(generalClass.switchWZ(e.getExpressStatus()));
            }
        });
        return list;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(List<ReceiveExpressParam> receiveExpressParamList) {
        receiveExpressParamList.forEach(receiveExpressParam -> {
            this.removeById(receiveExpressParam.getId());
            QueryWrapper<TakeExpressDelivery> queryWrapper = new QueryWrapper<>();
            List<TakeExpressDelivery> takeExpressDeliveryList = takeExpressDeliveryServiceImpl.list(queryWrapper.lambda().eq(TakeExpressDelivery::getRId, receiveExpressParam.getId()));
            if(ObjectUtil.isNotEmpty(takeExpressDeliveryList)) {
                takeExpressDeliveryServiceImpl.removeById(takeExpressDeliveryList.get(0).getId());
            }
        });
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void edit(ReceiveExpressParam receiveExpressParam) {
        generalClass.switchSZ(receiveExpressParam);
        ReceiveExpress receiveExpress = this.queryReceiveExpress(receiveExpressParam);
        BeanUtil.copyProperties(receiveExpressParam, receiveExpress);
        this.updateById(receiveExpress);
        //查看取表是否有数据
        QueryWrapper<TakeExpressDelivery> queryWrapper = new QueryWrapper<>();
        List<TakeExpressDelivery> takeExpressDeliveryList = takeExpressDeliveryServiceImpl.list(queryWrapper.lambda().eq(TakeExpressDelivery::getRId, receiveExpressParam.getId()));

        if(receiveExpressParam.getExpressStatus().equals("1")){
            //取表获取数值
            TakeExpressDelivery takeExpressDelivery = new TakeExpressDelivery();
            if (ObjectUtil.isNotEmpty(receiveExpressParam.getConsignee())) {
                takeExpressDelivery.setConsignee(receiveExpressParam.getConsignee());
            }
            if (ObjectUtil.isNotEmpty(receiveExpressParam.getConsigneeNumber())) {
                takeExpressDelivery.setConsigneeNumber(receiveExpressParam.getConsigneeNumber());
            }
            if (ObjectUtil.isNotEmpty(receiveExpressParam.getRemarks())) {
                takeExpressDelivery.setRemarks(receiveExpressParam.getRemarks());
            }
            takeExpressDelivery.setRId(receiveExpressParam.getId());

            if(takeExpressDeliveryList.size() > 0) {
                takeExpressDelivery.setId(takeExpressDeliveryList.get(0).getId());
                takeExpressDeliveryServiceImpl.updateById(takeExpressDelivery);
            } else {
                Date now = new Date();
                takeExpressDelivery.setTakeTime(now);
                takeExpressDeliveryServiceImpl.save(takeExpressDelivery);
            }
        }
        if(receiveExpressParam.getExpressStatus().equals("0")){
            if (takeExpressDeliveryList.size() > 0){
                takeExpressDeliveryServiceImpl.remove(queryWrapper);
            }
        }
    }

    @Override
    public ReceiveExpress detail(ReceiveExpressParam receiveExpressParam) {
        return this.queryReceiveExpress(receiveExpressParam);
    }

    /**
     * 获取收件模块
     *
     * @author wangxijun
     * @date 2024-05-10 21:10:08
     */
    private ReceiveExpress queryReceiveExpress(ReceiveExpressParam receiveExpressParam) {
        ReceiveExpress receiveExpress = this.getById(receiveExpressParam.getId());
        if (ObjectUtil.isNull(receiveExpress)) {
            throw new ServiceException(ReceiveExpressExceptionEnum.NOT_EXIST);
        }
        return receiveExpress;
    }

    @Override
    public void export(ReceiveExpressParam receiveExpressParam) {
        List<ReceiveExpress> list = this.list(receiveExpressParam);
        PoiUtil.exportExcelWithStream("SnowyReceiveExpress.xls", ReceiveExpress.class, list);
    }

    @Override
    public CountData countDataSelect() {
        CountData countData = new CountData();
        List<String> mingcheng = new ArrayList<>();
        List<String> pieMingCheng = new ArrayList<>();
        List<Integer> daiqu = new ArrayList<>();
        List<Integer> jinri = new ArrayList<>();
        List<Integer> zuori = new ArrayList<>();
        List<Integer> pie = new ArrayList<>();
        String[] expressTypeData = {"京东", "顺丰", "中通", "申通", "圆通", "邮政","韵达", "极兔", "菜鸟", "德邦", "其他"};
        for(int i=0; i<expressTypeData.length;i++){
            mingcheng.add(expressTypeData[i]);
            daiqu.add(generalClass.daiquData(expressTypeData[i]));
            jinri.add(generalClass.jinriData(expressTypeData[i]));
            zuori.add(generalClass.zuoriData(expressTypeData[i]));
        }

        /**
         * 名称
         */
        countData.setExpressMC(mingcheng);

        /**
         * 今日
         */
        countData.setNowData(jinri);

        /**
         * 剩余
         */
        countData.setSurplusData(daiqu);

        /**
         * 柱状图
         */
        countData.setHistogramData(zuori);

        /**
         * 饼图
         */
        pieMingCheng.add("收件表数据");
        pie.add(this.count());
        pieMingCheng.add("取件表数据");
        pie.add(takeExpressDeliveryServiceImpl.count());
        countData.setPieName(pieMingCheng);
        countData.setPieChartData(pie);

        return countData;
    }

    @Override
    public List<ReceiveExpress> addVerification(ReceiveExpressParam receiveExpressParam){
        generalClass.switchSZ(receiveExpressParam);
        QueryWrapper<ReceiveExpress> queryWrapper = new QueryWrapper<>();
        // 根据快递类型 查询
        if (ObjectUtil.isNotEmpty(receiveExpressParam.getExpressType())) {
            queryWrapper.lambda().eq(ReceiveExpress::getExpressType, receiveExpressParam.getExpressType());
        }
        // 根据快递单号 查询
        if (ObjectUtil.isNotEmpty(receiveExpressParam.getExpressNumber())) {
            queryWrapper.lambda().eq(ReceiveExpress::getExpressNumber, receiveExpressParam.getExpressNumber());
        }
        queryWrapper.lambda().eq(ReceiveExpress::getExpressStatus, "0");
        return this.list(queryWrapper);
    }

    @Override
    public void deleteExpressLog(){
        //获取id和rid
        QueryWrapper<TakeExpressDelivery> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().le(TakeExpressDelivery::getTakeTime, generalClass.fiveMonthDate());
        List<TakeExpressDelivery> takeExpressDeliveryList = takeExpressDeliveryServiceImpl.list(queryWrapper);
        for (int i=0; i <takeExpressDeliveryList.size(); i++) {
            //清理取件日志
            takeExpressDeliveryServiceImpl.removeById(takeExpressDeliveryList.get(i).getId());
            //清理收件日志
            this.removeById(takeExpressDeliveryList.get(i).getRId());
        }
    }

    @Override
    public List<ReceiveExpress> editVerification(ReceiveExpressParam receiveExpressParam){
        generalClass.switchSZ(receiveExpressParam);
        QueryWrapper<ReceiveExpress> queryWrapper = new QueryWrapper<>();
        // 根据快递类型 查询
        if (ObjectUtil.isNotEmpty(receiveExpressParam.getExpressType())) {
            queryWrapper.lambda().eq(ReceiveExpress::getExpressType, receiveExpressParam.getExpressType());
        }
        // 根据快递单号 查询
        if (ObjectUtil.isNotEmpty(receiveExpressParam.getExpressNumber())) {
            queryWrapper.lambda().eq(ReceiveExpress::getExpressNumber, receiveExpressParam.getExpressNumber());
        }
        return this.list(queryWrapper);
    }

}
