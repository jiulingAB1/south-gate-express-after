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
package cn.south.express.express.takeexpressdelivery.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.south.express.express.receiveexpress.entity.ReceiveExpress;
import cn.south.express.express.receiveexpress.mapper.ReceiveExpressMapper;
import cn.south.express.express.receiveexpress.param.ReceiveExpressParam;
import cn.south.express.express.takeexpressdelivery.entity.TakeExpressDelivery;
import cn.south.express.express.takeexpressdelivery.enums.TakeExpressDeliveryExceptionEnum;
import cn.south.express.express.takeexpressdelivery.mapper.TakeExpressDeliveryMapper;
import cn.south.express.express.takeexpressdelivery.param.TakeExpressDeliveryParam;
import cn.south.express.express.takeexpressdelivery.service.TakeExpressDeliveryService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vip.xiaonuo.core.exception.ServiceException;
import vip.xiaonuo.core.factory.PageFactory;
import vip.xiaonuo.core.pojo.page.PageResult;
import vip.xiaonuo.core.util.PoiUtil;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 取件模块service接口实现类
 *
 * @author wangxijun
 * @date 2024-05-10 21:10:46
 */
@Service
public class TakeExpressDeliveryServiceImpl extends ServiceImpl<TakeExpressDeliveryMapper, TakeExpressDelivery> implements TakeExpressDeliveryService {

    @Resource
    private ReceiveExpressMapper receiveExpressMapper;

    @Override
    public PageResult<TakeExpressDelivery> page(TakeExpressDeliveryParam takeExpressDeliveryParam) {
        QueryWrapper<TakeExpressDelivery> queryWrapper = new QueryWrapper<>();
        if (ObjectUtil.isNotNull(takeExpressDeliveryParam)) {

            // 根据取件人 查询
            if (ObjectUtil.isNotEmpty(takeExpressDeliveryParam.getConsignee())) {
                queryWrapper.lambda().like(TakeExpressDelivery::getConsignee, takeExpressDeliveryParam.getConsignee());
            }
            // 根据取手机号 查询
            if (ObjectUtil.isNotEmpty(takeExpressDeliveryParam.getConsigneeNumber())) {
                queryWrapper.lambda().like(TakeExpressDelivery::getConsigneeNumber, takeExpressDeliveryParam.getConsigneeNumber());
            }
            // 根据取件时间 查询
            if (ObjectUtil.isNotEmpty(takeExpressDeliveryParam.getTakeTime())) {
                queryWrapper.lambda().like(TakeExpressDelivery::getTakeTime, takeExpressDeliveryParam.getTakeTime());
            }
        }
        return new PageResult<>(this.page(PageFactory.defaultPage(), queryWrapper));
    }

    @Override
    public List<TakeExpressDelivery> list(TakeExpressDeliveryParam takeExpressDeliveryParam) {
        return this.list();
    }

    @Override
    public void add(TakeExpressDeliveryParam takeExpressDeliveryParam) {
        takeExpressDeliveryParam.nowDataAndTime();
        for(int i=0;i<takeExpressDeliveryParam.getIdList().size();i++){
            //更新快递状态
            ReceiveExpress receiveExpress = receiveExpressMapper.selectById(takeExpressDeliveryParam.getIdList().get(i));
            receiveExpress.setExpressStatus("1");
            receiveExpressMapper.updateById(receiveExpress);

            //判断rid是否存在
            QueryWrapper<TakeExpressDelivery> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda().eq(TakeExpressDelivery::getRId, takeExpressDeliveryParam.getIdList().get(i));
            List<TakeExpressDelivery> list = this.list(queryWrapper);
            if (list.size() == 0 || ObjectUtil.isEmpty(list)) {
                //添加取件人
                takeExpressDeliveryParam.setRId(takeExpressDeliveryParam.getIdList().get(i));
                TakeExpressDelivery takeExpressDelivery = new TakeExpressDelivery();
                BeanUtil.copyProperties(takeExpressDeliveryParam, takeExpressDelivery);
                this.save(takeExpressDelivery);
            }
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(List<TakeExpressDeliveryParam> takeExpressDeliveryParamList) {
        takeExpressDeliveryParamList.forEach(takeExpressDeliveryParam -> {
            this.removeById(takeExpressDeliveryParam.getId());
        });
    }

    @Override
    public List<TakeExpressDelivery> takeExpressPeopleData(ReceiveExpressParam receiveExpressParam) {
        List<TakeExpressDelivery> takeExpressDeliveryData = new ArrayList<>();
        if (ObjectUtil.isNotEmpty(receiveExpressParam.getExpressPeople()) && ObjectUtil.isNotEmpty(receiveExpressParam.getExpressTel())) {
            //单表查询
            QueryWrapper<TakeExpressDelivery> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda().like(TakeExpressDelivery::getConsignee, receiveExpressParam.getExpressPeople());
            queryWrapper.lambda().like(TakeExpressDelivery::getConsigneeNumber, receiveExpressParam.getExpressTel());
            queryWrapper.select("distinct consignee_number", "id", "consignee", "consignee_number", "remarks", "take_time", "r_id");
            queryWrapper.lambda().orderByDesc(TakeExpressDelivery::getTakeTime);
            queryWrapper.last("limit 1");
            List<TakeExpressDelivery> takeExpressDeliveryList =this.list(queryWrapper);
            if (takeExpressDeliveryList.size() == 0 || ObjectUtil.isEmpty(takeExpressDeliveryList)) {
                //联表查询
                QueryWrapper<TakeExpressDelivery> queryWrapper1 = new QueryWrapper<>();
                queryWrapper1.like("r.express_people", receiveExpressParam.getExpressPeople());
                queryWrapper1.like("r.express_tel", receiveExpressParam.getExpressTel());
                queryWrapper1.select("distinct t.consignee_number");
                queryWrapper1.orderByDesc("t.take_time");
                queryWrapper1.last("limit 1");
                List<TakeExpressDelivery> takeExpressDeliveryTwoList = this.baseMapper.TakeExpressDataSelect(queryWrapper1);
                if (takeExpressDeliveryTwoList.size() > 0) {
                    takeExpressDeliveryData.add(takeExpressDeliveryTwoList.get(0));
                }
            }else if (takeExpressDeliveryList.size() > 0) {
                takeExpressDeliveryData.add(takeExpressDeliveryList.get(0));
            }
        }
        return takeExpressDeliveryData;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void edit(TakeExpressDeliveryParam takeExpressDeliveryParam) {
        TakeExpressDelivery takeExpressDelivery = this.queryTakeExpressDelivery(takeExpressDeliveryParam);
        BeanUtil.copyProperties(takeExpressDeliveryParam, takeExpressDelivery);
        this.updateById(takeExpressDelivery);
    }

    @Override
    public TakeExpressDelivery detail(TakeExpressDeliveryParam takeExpressDeliveryParam) {
        return this.queryTakeExpressDelivery(takeExpressDeliveryParam);
    }

    /**
     * 获取取件模块
     *
     * @author wangxijun
     * @date 2024-05-10 21:10:46
     */
    private TakeExpressDelivery queryTakeExpressDelivery(TakeExpressDeliveryParam takeExpressDeliveryParam) {
        TakeExpressDelivery takeExpressDelivery = this.getById(takeExpressDeliveryParam.getId());
        if (ObjectUtil.isNull(takeExpressDelivery)) {
            throw new ServiceException(TakeExpressDeliveryExceptionEnum.NOT_EXIST);
        }
        return takeExpressDelivery;
    }

    @Override
    public void export(TakeExpressDeliveryParam takeExpressDeliveryParam) {
        List<TakeExpressDelivery> list = this.list(takeExpressDeliveryParam);
        PoiUtil.exportExcelWithStream("SnowyTakeExpressDelivery.xls", TakeExpressDelivery.class, list);
    }

}
