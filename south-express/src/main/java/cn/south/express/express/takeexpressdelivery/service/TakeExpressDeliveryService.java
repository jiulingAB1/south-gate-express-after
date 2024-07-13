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
package cn.south.express.express.takeexpressdelivery.service;

import cn.south.express.express.receiveexpress.param.ReceiveExpressParam;
import cn.south.express.express.takeexpressdelivery.entity.TakeExpressDelivery;
import cn.south.express.express.takeexpressdelivery.param.TakeExpressDeliveryParam;
import com.baomidou.mybatisplus.extension.service.IService;
import vip.xiaonuo.core.pojo.page.PageResult;

import java.util.List;

/**
 * 取件模块service接口
 *
 * @author wangxijun
 * @date 2024-05-10 21:10:46
 */
public interface TakeExpressDeliveryService extends IService<TakeExpressDelivery> {

    /**
     * 查询取件模块
     *
     * @author wangxijun
     * @date 2024-05-10 21:10:46
     */
    PageResult<TakeExpressDelivery> page(TakeExpressDeliveryParam takeExpressDeliveryParam);

    /**
     * 取件模块列表
     *
     * @author wangxijun
     * @date 2024-05-10 21:10:46
     */
    List<TakeExpressDelivery> list(TakeExpressDeliveryParam takeExpressDeliveryParam);

    /**
     * 添加取件模块
     *
     * @author wangxijun
     * @date 2024-05-10 21:10:46
     */
    void add(TakeExpressDeliveryParam takeExpressDeliveryParam);

    /**
     * 取件人数据获取
     *
     * @author wangxijun
     * @date 2024-05-10 21:10:08
     */
    List<TakeExpressDelivery> takeExpressPeopleData(ReceiveExpressParam receiveExpressParam);

    /**
     * 删除取件模块
     *
     * @author wangxijun
     * @date 2024-05-10 21:10:46
     */
    void delete(List<TakeExpressDeliveryParam> takeExpressDeliveryParamList);

    /**
     * 编辑取件模块
     *
     * @author wangxijun
     * @date 2024-05-10 21:10:46
     */
    void edit(TakeExpressDeliveryParam takeExpressDeliveryParam);

    /**
     * 查看取件模块
     *
     * @author wangxijun
     * @date 2024-05-10 21:10:46
     */
     TakeExpressDelivery detail(TakeExpressDeliveryParam takeExpressDeliveryParam);

    /**
     * 导出取件模块
     *
     * @author wangxijun
     * @date 2024-05-10 21:10:46
     */
     void export(TakeExpressDeliveryParam takeExpressDeliveryParam);

}
