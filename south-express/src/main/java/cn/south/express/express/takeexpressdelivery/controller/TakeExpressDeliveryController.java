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
package cn.south.express.express.takeexpressdelivery.controller;

import cn.south.express.express.receiveexpress.param.ReceiveExpressParam;
import cn.south.express.express.takeexpressdelivery.param.TakeExpressDeliveryParam;
import cn.south.express.express.takeexpressdelivery.service.TakeExpressDeliveryService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import vip.xiaonuo.core.annotion.BusinessLog;
import vip.xiaonuo.core.annotion.Permission;
import vip.xiaonuo.core.enums.LogAnnotionOpTypeEnum;
import vip.xiaonuo.core.pojo.response.ResponseData;
import vip.xiaonuo.core.pojo.response.SuccessResponseData;

import javax.annotation.Resource;
import java.util.List;

/**
 * 取件模块控制器
 *
 * @author wangxijun
 * @date 2024-05-10 21:10:46
 */
@RestController
public class TakeExpressDeliveryController {

    @Resource
    private TakeExpressDeliveryService takeExpressDeliveryService;

    /**
     * 查询取件模块
     *
     * @author wangxijun
     * @date 2024-05-10 21:10:46
     */
    @Permission
    @GetMapping("/takeExpressDelivery/page")
    @BusinessLog(title = "取件模块_查询", opType = LogAnnotionOpTypeEnum.QUERY)
    public ResponseData page(TakeExpressDeliveryParam takeExpressDeliveryParam) {
        return new SuccessResponseData(takeExpressDeliveryService.page(takeExpressDeliveryParam));
    }

    /**
     * 添加取件模块
     *
     * @author wangxijun
     * @date 2024-05-10 21:10:46
     */
    @Permission
    @PostMapping("/takeExpressDelivery/add")
    @BusinessLog(title = "取件模块_增加", opType = LogAnnotionOpTypeEnum.ADD)
    public ResponseData add(@RequestBody @Validated(TakeExpressDeliveryParam.add.class) TakeExpressDeliveryParam takeExpressDeliveryParam) {
            takeExpressDeliveryService.add(takeExpressDeliveryParam);
        return new SuccessResponseData();
    }

    /**
     * 取件人数据获取
     *
     * @author wangxijun
     * @date 2024-05-10 21:10:08
     */
    @Permission
    @GetMapping("/takeExpressDelivery/takeExpressPeopleData")
    @BusinessLog(title = "取件模块_列表", opType = LogAnnotionOpTypeEnum.QUERY)
    public ResponseData takeExpressPeopleData(ReceiveExpressParam receiveExpressParam) {
        return new SuccessResponseData(takeExpressDeliveryService.takeExpressPeopleData(receiveExpressParam));
    }

    /**
     * 删除取件模块，可批量删除
     *
     * @author wangxijun
     * @date 2024-05-10 21:10:46
     */
    @Permission
    @PostMapping("/takeExpressDelivery/delete")
    @BusinessLog(title = "取件模块_删除", opType = LogAnnotionOpTypeEnum.DELETE)
    public ResponseData delete(@RequestBody @Validated(TakeExpressDeliveryParam.delete.class) List<TakeExpressDeliveryParam> takeExpressDeliveryParamList) {
            takeExpressDeliveryService.delete(takeExpressDeliveryParamList);
        return new SuccessResponseData();
    }

    /**
     * 编辑取件模块
     *
     * @author wangxijun
     * @date 2024-05-10 21:10:46
     */
    @Permission
    @PostMapping("/takeExpressDelivery/edit")
    @BusinessLog(title = "取件模块_编辑", opType = LogAnnotionOpTypeEnum.EDIT)
    public ResponseData edit(@RequestBody @Validated(TakeExpressDeliveryParam.edit.class) TakeExpressDeliveryParam takeExpressDeliveryParam) {
            takeExpressDeliveryService.edit(takeExpressDeliveryParam);
        return new SuccessResponseData();
    }

    /**
     * 查看取件模块
     *
     * @author wangxijun
     * @date 2024-05-10 21:10:46
     */
    @Permission
    @GetMapping("/takeExpressDelivery/detail")
    @BusinessLog(title = "取件模块_查看", opType = LogAnnotionOpTypeEnum.DETAIL)
    public ResponseData detail(@Validated(TakeExpressDeliveryParam.detail.class) TakeExpressDeliveryParam takeExpressDeliveryParam) {
        return new SuccessResponseData(takeExpressDeliveryService.detail(takeExpressDeliveryParam));
    }

    /**
     * 取件模块列表
     *
     * @author wangxijun
     * @date 2024-05-10 21:10:46
     */
    @Permission
    @GetMapping("/takeExpressDelivery/list")
    @BusinessLog(title = "取件模块_列表", opType = LogAnnotionOpTypeEnum.QUERY)
    public ResponseData list(TakeExpressDeliveryParam takeExpressDeliveryParam) {
        return new SuccessResponseData(takeExpressDeliveryService.list(takeExpressDeliveryParam));
    }

    /**
     * 导出系统用户
     *
     * @author wangxijun
     * @date 2024-05-10 21:10:46
     */
    @Permission
    @GetMapping("/takeExpressDelivery/export")
    @BusinessLog(title = "取件模块_导出", opType = LogAnnotionOpTypeEnum.EXPORT)
    public void export(TakeExpressDeliveryParam takeExpressDeliveryParam) {
        takeExpressDeliveryService.export(takeExpressDeliveryParam);
    }

}
