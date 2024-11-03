package com.example.restaurant.domain;


import com.example.restaurant.domain.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 消息对象 rt_message
 *
 * @author ruoyi
 * @date 2024-10-16
 */
@ApiModel("消息实体")
public class RtMessage extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    @ApiModelProperty("id")
    private Long id;

    /** 发起人 */
    @ApiModelProperty("发起人")
    private String from;

    /** 接收人 */
    @ApiModelProperty("接收人")
    private String to;

    /** 内容 */
    @ApiModelProperty("内容")
    private String message;

    /** 桌号 */
    @ApiModelProperty("桌号")
    private String table;

    /** 订单id */
    @ApiModelProperty("订单id")
    private String orderId;


    public String getFoodInfo() {
        return foodInfo;
    }

    public void setFoodInfo(String foodInfo) {
        this.foodInfo = foodInfo;
    }

    private String foodInfo;

    /** 删除标识 */
    private String delFlag;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setFrom(String from)
    {
        this.from = from;
    }

    public String getFrom()
    {
        return from;
    }
    public void setTo(String to)
    {
        this.to = to;
    }

    public String getTo()
    {
        return to;
    }
    public void setMessage(String message)
    {
        this.message = message;
    }

    public String getMessage()
    {
        return message;
    }
    public void setTable(String table)
    {
        this.table = table;
    }

    public String getTable()
    {
        return table;
    }
    public void setOrderId(String orderId)
    {
        this.orderId = orderId;
    }

    public String getOrderId()
    {
        return orderId;
    }
    public void setDelFlag(String delFlag)
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag()
    {
        return delFlag;
    }

   
}
