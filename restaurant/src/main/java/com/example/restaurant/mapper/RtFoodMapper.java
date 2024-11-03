package com.example.restaurant.mapper;

import java.util.List;
import com.example.restaurant.domain.RtFood;
import org.apache.ibatis.annotations.Mapper;

/**
 * 菜品Mapper接口
 *
 * @author ruoyi
 * @date 2024-10-16
 */
@Mapper
public interface RtFoodMapper
{
    /**
     * 查询菜品
     *
     * @param id 菜品主键
     * @return 菜品
     */
    public RtFood selectRtFoodById(Long id);

    /**
     * 查询菜品列表
     *
     * @param rtFood 菜品
     * @return 菜品集合
     */
    public List<RtFood> selectRtFoodList(RtFood rtFood);

    /**
     * 新增菜品
     *
     * @param rtFood 菜品
     * @return 结果
     */
    public int insertRtFood(RtFood rtFood);

    /**
     * 修改菜品
     *
     * @param rtFood 菜品
     * @return 结果
     */
    public int updateRtFood(RtFood rtFood);

    /**
     * 删除菜品
     *
     * @param id 菜品主键
     * @return 结果
     */
    public int deleteRtFoodById(Long id);

    /**
     * 批量删除菜品
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteRtFoodByIds(Long[] ids);
}
