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
package cn.south.express.express.receiveexpress.service;

import cn.south.express.express.receiveexpress.entity.CountData;
import cn.south.express.express.receiveexpress.entity.ReceiveExpress;
import cn.south.express.express.receiveexpress.param.ReceiveExpressParam;
import com.baomidou.mybatisplus.extension.service.IService;
import vip.xiaonuo.core.pojo.page.PageResult;

import java.util.List;

/**
 * 收件模块service接口
 *
 * @author wangxijun
 * @date 2024-05-10 21:10:08
 */
public interface ReceiveExpressService extends IService<ReceiveExpress> {

    /**
     * 查询收件模块
     *
     * @author wangxijun
     * @date 2024-05-10 21:10:08
     */
    PageResult<ReceiveExpressParam> page(ReceiveExpressParam receiveExpressParam);

    /**
     * 收件模块列表
     *
     * @author wangxijun
     * @date 2024-05-10 21:10:08
     */
    List<ReceiveExpress> list(ReceiveExpressParam receiveExpressParam);

    /**
     * 添加收件模块
     *
     * @author wangxijun
     * @date 2024-05-10 21:10:08
     */
    void add(ReceiveExpressParam receiveExpressParam);

    /**
     * 收件模块成功后查询
     *
     *@author wangxijun
     * @date 2024-5-27 19:11:28
     */
    List<ReceiveExpress> addSuccessSelect(ReceiveExpressParam receiveExpressParam);

    /**
     * 删除收件模块
     *
     * @author wangxijun
     * @date 2024-05-10 21:10:08
     */
    void delete(List<ReceiveExpressParam> receiveExpressParamList);

    /**
     * 编辑收件模块
     *
     * @author wangxijun
     * @date 2024-05-10 21:10:08
     */
    void edit(ReceiveExpressParam receiveExpressParam);

    /**
     * 查看收件模块
     *
     * @author wangxijun
     * @date 2024-05-10 21:10:08
     */
     ReceiveExpress detail(ReceiveExpressParam receiveExpressParam);

    /**
     * 导出收件模块
     *
     * @author wangxijun
     * @date 2024-05-10 21:10:08
     */
     void export(ReceiveExpressParam receiveExpressParam);

    /**
     * 统计数据模块
     *
     * @author wangxijun
     * @date 2024-05-10 21:10:08
     */
    CountData countDataSelect();

    /**
     * 取件模块列表
     *
     * @author wangxijun
     * @date 2024-05-10 21:10:08
     */
    List<ReceiveExpress> pickUpList(String expressNumber);

    /**
     * 收件模块验证
     *
     *@author wangxijun
     * @date 2024-5-27 19:11:28
     */
    List<ReceiveExpress> addVerification(ReceiveExpressParam receiveExpressParam);

    /**
     * 清理日志
     *
     *@author wangxijun
     * @date 2024-5-27 19:11:28
     */
    void deleteExpressLog();

    /**
     * 查询模块修改功能验证
     *
     *@author wangxijun
     * @date 2024-5-27 19:11:28
     */
    List<ReceiveExpress> editVerification(ReceiveExpressParam receiveExpressParam);
}
