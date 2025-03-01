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
package cn.south.express.express.takeexpressdelivery.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

/**
 * 取件模块
 *
 * @author wangxijun
 * @date 2024-05-10 21:10:46
 */
@Data
@TableName("tb_take_express_delivery")
public class TakeExpressDelivery {


    /**
     * 取件人
     */
    @Excel(name = "取件人")
    private String consignee;

    /**
     * 取手机号
     */
    @Excel(name = "取手机号")
    private Long consigneeNumber;

    /**
     * 删除状态 0-未删除 1-删除
     */
    @Excel(name = "删除状态 0-未删除 1-删除")
    private String delFlag;

    /**
     * 主键id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 收件表id
     */
    @Excel(name = "收件表id")
    private Long rId;

    /**
     * 备注
     */
    @Excel(name = "备注")
    private String remarks;

    /**
     * 取件时间
     */
    @Excel(name = "取件时间", databaseFormat = "yyyy-MM-dd HH:mm:ss", format = "yyyy-MM-dd HH:mm", width = 20)
    @TableField(fill = FieldFill.INSERT)
    private Date takeTime;
}
