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
package cn.south.express.express.receiveexpress.controller;

import cn.south.express.express.receiveexpress.param.ReceiveExpressParam;
import cn.south.express.express.receiveexpress.service.ReceiveExpressService;
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
 * 收件模块控制器
 *
 * @author wangxijun
 * @date 2024-05-10 21:10:08
 */
@RestController
public class ReceiveExpressController {

    @Resource
    private ReceiveExpressService receiveExpressService;

    /**
     * 查询收件模块
     *
     * @author wangxijun
     * @date 2024-05-10 21:10:08
     */
    @Permission
    @GetMapping("/receiveExpress/page")
    @BusinessLog(title = "收件模块_查询", opType = LogAnnotionOpTypeEnum.QUERY)
    public ResponseData page(ReceiveExpressParam receiveExpressParam) {
        return new SuccessResponseData(receiveExpressService.page(receiveExpressParam));
    }

    /**
     * 统计数据查询模块
     *
     * @author wangxijun
     * @date 2024-05-10 21:10:08
     */
    @Permission
    @GetMapping("/receiveExpress/countData")
    @BusinessLog(title = "数据统计模块_查询", opType = LogAnnotionOpTypeEnum.QUERY)
    public ResponseData countData() {
        return new SuccessResponseData(receiveExpressService.countDataSelect());
    }

    /**
     * 添加收件模块
     *
     * @author wangxijun
     * @date 2024-05-10 21:10:08
     */
    @Permission
    @PostMapping("/receiveExpress/add")
    @BusinessLog(title = "收件模块_增加", opType = LogAnnotionOpTypeEnum.ADD)
    public ResponseData add(@RequestBody @Validated(ReceiveExpressParam.add.class) ReceiveExpressParam receiveExpressParam) {
            receiveExpressService.add(receiveExpressParam);
        return new SuccessResponseData();
    }

    /**
     * 收件模块成功后查询
     *
     *@author wangxijun
     * @date 2024-5-27 19:11:28
     */
    @Permission
    @GetMapping("/receiveExpress/addSuccessSelect")
    @BusinessLog(title = "取件模块查询_列表", opType = LogAnnotionOpTypeEnum.QUERY)
    public ResponseData addSuccessSelect(ReceiveExpressParam receiveExpressParam) {
        return new SuccessResponseData(receiveExpressService.addSuccessSelect(receiveExpressParam));
    }

    /**
     * 删除收件模块，可批量删除
     *
     * @author wangxijun
     * @date 2024-05-10 21:10:08
     */
    @Permission
    @PostMapping("/receiveExpress/delete")
    @BusinessLog(title = "收件模块_删除", opType = LogAnnotionOpTypeEnum.DELETE)
    public ResponseData delete(@RequestBody @Validated(ReceiveExpressParam.delete.class) List<ReceiveExpressParam> receiveExpressParamList) {
            receiveExpressService.delete(receiveExpressParamList);
        return new SuccessResponseData();
    }

    /**
     * 编辑收件模块
     *
     * @author wangxijun
     * @date 2024-05-10 21:10:08
     */
    @Permission
    @PostMapping("/receiveExpress/edit")
    @BusinessLog(title = "收件模块_编辑", opType = LogAnnotionOpTypeEnum.EDIT)
    public ResponseData edit(@RequestBody @Validated(ReceiveExpressParam.edit.class) ReceiveExpressParam receiveExpressParam) {
            receiveExpressService.edit(receiveExpressParam);
        return new SuccessResponseData();
    }

    /**
     * 查看收件模块
     *
     * @author wangxijun
     * @date 2024-05-10 21:10:08
     */
    @Permission
    @GetMapping("/receiveExpress/detail")
    @BusinessLog(title = "收件模块_查看", opType = LogAnnotionOpTypeEnum.DETAIL)
    public ResponseData detail(@Validated(ReceiveExpressParam.detail.class) ReceiveExpressParam receiveExpressParam) {
        return new SuccessResponseData(receiveExpressService.detail(receiveExpressParam));
    }

    /**
     * 收件模块列表
     *
     * @author wangxijun
     * @date 2024-05-10 21:10:08
     */
    @Permission
    @GetMapping("/receiveExpress/list")
    @BusinessLog(title = "收件模块_列表", opType = LogAnnotionOpTypeEnum.QUERY)
    public ResponseData list(ReceiveExpressParam receiveExpressParam) {
        return new SuccessResponseData(receiveExpressService.list(receiveExpressParam));
    }

    /**
     * 取件模块列表
     *
     * @author wangxijun
     * @date 2024-05-10 21:10:08
     */
    @Permission
    @GetMapping("/receiveExpress/pickUpList")
    @BusinessLog(title = "取件模块查询_列表", opType = LogAnnotionOpTypeEnum.QUERY)
    public ResponseData pickUpList(String expressNumber) {
        return new SuccessResponseData(receiveExpressService.pickUpList(expressNumber));
    }

    /**
     * 导出系统用户
     *
     * @author wangxijun
     * @date 2024-05-10 21:10:08
     */
    @Permission
    @GetMapping("/receiveExpress/export")
    @BusinessLog(title = "收件模块_导出", opType = LogAnnotionOpTypeEnum.EXPORT)
    public void export(ReceiveExpressParam receiveExpressParam) {
        receiveExpressService.export(receiveExpressParam);
    }

    /**
     * 收件模块验证
     *
     *@author wangxijun
     * @date 2024-5-27 19:11:28
     */
    @Permission
    @GetMapping("/receiveExpress/addVerification")
    @BusinessLog(title = "取件模块查询_列表", opType = LogAnnotionOpTypeEnum.QUERY)
    public ResponseData addVerification(ReceiveExpressParam receiveExpressParam) {
        return new SuccessResponseData(receiveExpressService.addVerification(receiveExpressParam));
    }

    /**
     * 清理日志
     *
     *@author wangxijun
     * @date 2024-5-27 19:11:28
     */
    @Permission
    @PostMapping("/receiveExpress/deleteExpressLog")
    @BusinessLog(title = "收件模块_删除", opType = LogAnnotionOpTypeEnum.DELETE)
    public ResponseData deleteExpressLog() {
        receiveExpressService.deleteExpressLog();
        return new SuccessResponseData();
    }

    /**
     * 查询模块修改功能验证
     *
     *@author wangxijun
     * @date 2024-5-27 19:11:28
     */
    @Permission
    @GetMapping("/receiveExpress/editVerification")
    @BusinessLog(title = "取件模块查询_列表", opType = LogAnnotionOpTypeEnum.QUERY)
    public ResponseData editVerification(ReceiveExpressParam receiveExpressParam) {
        return new SuccessResponseData(receiveExpressService.editVerification(receiveExpressParam));
    }
}
