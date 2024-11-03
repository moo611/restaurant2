package com.example.restaurant.domain;


import com.example.restaurant.domain.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 订单对象 rt_order
 *
 * @author ruoyi
 * @date 2024-10-16
 */
@ApiModel("订单实体")
public class RtOrder extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    @ApiModelProperty("id")
    private String id;

    /** 订单信息 */
    @ApiModelProperty("订单信息")
    private String foodInfo;

    /** 桌号 */
    @ApiModelProperty("桌号")
    private String table;

    /** 订单状态 */
    @ApiModelProperty("订单状态")
    private String status;

    /** 删除标识 */
    private String delFlag;

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    private Integer totalPrice;

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId()
    {
        return id;
    }
    public void setFoodInfo(String foodInfo)
    {
        this.foodInfo = foodInfo;
    }

    public String getFoodInfo()
    {
        return foodInfo;
    }
    public void setTable(String table)
    {
        this.table = table;
    }

    public String getTable()
    {
        return table;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
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
