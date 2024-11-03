package com.example.restaurant.service.impl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.restaurant.mapper.RtFoodMapper;
import com.example.restaurant.domain.RtFood;
import com.example.restaurant.service.IRtFoodService;

/**
 * 菜品Service业务层处理
 *
 * @author ruoyi
 * @date 2024-10-16
 */
@Service
public class RtFoodServiceImpl implements IRtFoodService
{
    @Autowired
    private RtFoodMapper rtFoodMapper;

    /**
     * 查询菜品
     *
     * @param id 菜品主键
     * @return 菜品
     */
    @Override
    public RtFood selectRtFoodById(Long id)
    {
        return rtFoodMapper.selectRtFoodById(id);
    }

    /**
     * 查询菜品列表
     *
     * @param rtFood 菜品
     * @return 菜品
     */
    @Override
    public List<RtFood> selectRtFoodList(RtFood rtFood)
    {
        return rtFoodMapper.selectRtFoodList(rtFood);
    }

    /**
     * 新增菜品
     *
     * @param rtFood 菜品
     * @return 结果
     */
    @Override
    public int insertRtFood(RtFood rtFood)
    {
        rtFood.setCreateTime(new Date());
        return rtFoodMapper.insertRtFood(rtFood);
    }

    /**
     * 修改菜品
     *
     * @param rtFood 菜品
     * @return 结果
     */
    @Override
    public int updateRtFood(RtFood rtFood)
    {
        rtFood.setUpdateTime(new Date());
        return rtFoodMapper.updateRtFood(rtFood);
    }

    /**
     * 批量删除菜品
     *
     * @param ids 需要删除的菜品主键
     * @return 结果
     */
    @Override
    public int deleteRtFoodByIds(Long[] ids)
    {
        return rtFoodMapper.deleteRtFoodByIds(ids);
    }

    /**
     * 删除菜品信息
     *
     * @param id 菜品主键
     * @return 结果
     */
    @Override
    public int deleteRtFoodById(Long id)
    {
        return rtFoodMapper.deleteRtFoodById(id);
    }
}
