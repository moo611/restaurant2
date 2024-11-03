package com.example.restaurant.controller;

import java.util.Collections;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.example.restaurant.domain.base.AjaxResult;
import com.example.restaurant.domain.base.R;
import com.example.restaurant.domain.req.RtFoodListReq;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.restaurant.domain.RtFood;
import com.example.restaurant.service.IRtFoodService;


/**
 * 菜品Controller
 *
 * @author ruoyi
 * @date 2024-10-16
 */
@RestController
@Api(tags = "餐品管理")
@RequestMapping("/food")
public class RtFoodController extends BaseController
{
    @Autowired
    private IRtFoodService rtFoodService;

    /**
     * 查询菜品列表
     */
    @ApiOperation("查询菜品列表")  
    @GetMapping("/list")
    public R list(RtFoodListReq rtFoodListReq)
    {

        PageHelper.startPage(rtFoodListReq.getPageNum(), rtFoodListReq.getPageSize());
        RtFood rtFood = new RtFood();
        BeanUtils.copyProperties(rtFoodListReq,rtFood);

        List<RtFood> rtFoods = rtFoodService.selectRtFoodList(rtFood);
        if(rtFoods.size()>0){
            PageInfo<RtFood> pageInfo = new PageInfo<>(rtFoods);
            return R.ok(pageInfo);
        }
        return R.ok(new PageInfo<RtFood>(Collections.emptyList()));
    }




    /**
     * 获取菜品详细信息
     */
    @ApiOperation("获取菜品详细信息")  
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(rtFoodService.selectRtFoodById(id));
    }

    /**
     * 新增菜品
     */
    
    
    @PostMapping
    @ApiOperation("新增菜品")  
    public AjaxResult add(@RequestBody RtFood rtFood)
    {
        return toAjax(rtFoodService.insertRtFood(rtFood));
    }

    /**
     * 修改菜品
     */
    
    
    @PutMapping
    @ApiOperation("修改菜品")  
    public AjaxResult edit(@RequestBody RtFood rtFood)
    {
        return toAjax(rtFoodService.updateRtFood(rtFood));
    }

    /**
     * 删除菜品
     */
    
    
    @DeleteMapping("/{ids}")
    @ApiOperation("删除菜品")  
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(rtFoodService.deleteRtFoodByIds(ids));
    }
}
