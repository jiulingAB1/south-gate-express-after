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
package cn.south.express.express.receiveexpress.param;

import cn.south.express.express.generalFile.GeneralClass;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import vip.xiaonuo.core.pojo.base.param.BaseParam;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
* 收件模块参数类
 *
 * @author wangxijun
 * @date 2024-05-10 21:10:08
*/
@Data
public class ReceiveExpressParam extends BaseParam {

    /**
     * 删除状态 0-未删除 1-删除
     */
    private String delFlag;

    /**
     * 快递单号
     */
    @NotBlank(message = "快递单号不能为空，请检查expressNumber参数", groups = {add.class, edit.class})
    private String expressNumber;

    /**
     * 收件人
     */
    private String expressPeople;

    /**
     * 快递状态(0未取件，1已取件)
     */
    private String expressStatus;

    /**
     * 收手机号
     */
    private Long expressTel;

    /**
     * 快递类型
     */
    private String expressType;

    /**
     * 主键id
     */
    private Long id;

    /**
     * 收件时间
     */
    private String receiveTime;

    /**
     * 取件人
     */
    @TableField(exist = false)
    private String consignee;

    /**
     * 取手机号
     */
    @TableField(exist = false)
    private Long consigneeNumber;

    /**
     * 备注
     */
    @TableField(exist = false)
    private String remarks;

    /**
     * 取件时间
     */
    @TableField(exist = false)
    private String takeTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 快递状态赋空值
     */
    public void clearExpressStatus(){
        this.expressStatus = null;
    }

    /**
     * 快递类型赋空值
     */
    public void clearExpressType(){
        this.expressType = null;
    }

    /**
     * 赋值当前时间
     */
    public void nowDataAndTime(){
        GeneralClass generalClass = new GeneralClass();
        this.receiveTime = generalClass.nowDateTime();
    }


}
