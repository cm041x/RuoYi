package com.ruoyi.web.controller.research;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;

import com.ruoyi.common.utils.ShiroUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.research.domain.Research;
import com.ruoyi.research.service.IResearchService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 【请填写功能名称】Controller
 * 
 * @author xionglei
 * @date 2023-03-09
 */
@Controller
@RequestMapping("/research/research")
public class ResearchController extends BaseController
{
    private String prefix = "research/research";

    @Autowired
    private IResearchService researchService;

    @RequiresPermissions("research:research:view")
    @GetMapping()
    public String research()
    {
        return prefix + "/research";
    }



    /**
     * 查询【请填写功能名称】列表
     */
    @RequiresPermissions("research:research:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Research research)
    {
        startPage();
        List<Research> list = researchService.selectResearchList(research);
        return getDataTable(list);
    }

    /**
     * 导出項目列表
     */
    @RequiresPermissions("research:research:export")
    @Log(title = "项目数据", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Research research)
    {
        List<Research> list = researchService.selectResearchList(research);
        ExcelUtil<Research> util = new ExcelUtil<Research>(Research.class);
        return util.exportExcel(list, "项目数据");
    }


    @Log(title = "项目管理", businessType = BusinessType.IMPORT)
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {


        ExcelUtil<Research> util = new ExcelUtil<Research>(Research.class);
        List<Research> researchList = util.importExcel(file.getInputStream());
        String operName = ShiroUtils.getSysUser().getLoginName();
        String message = researchService.importResearch(researchList, updateSupport, operName);
        return AjaxResult.success(message);

/*        ExcelUtil<Research> util = new ExcelUtil<Research>(Research.class);
        List<Research> researchList = util.importExcel(file.getInputStream());
        String message = researchService.importResearch(researchList, updateSupport, getLoginName());
        return AjaxResult.success(message);*/
    }

    /**
     * 导入项目列表
     */
    @RequiresPermissions("research:research:view")
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate()
    {
        ExcelUtil<Research> util = new ExcelUtil<Research>(Research.class);
        return util.importTemplateExcel("项目数据");
    }


    /**
     * 新增【请填写功能名称】
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存【请填写功能名称】
     */
    @RequiresPermissions("research:research:add")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Research research)
    {
        return toAjax(researchService.insertResearch(research));
    }

    /**
     * 修改【请填写功能名称】
     */
    @RequiresPermissions("research:research:edit")
    @GetMapping("/edit/{researchId}")
    public String edit(@PathVariable("researchId") Long researchId, ModelMap mmap)
    {
        Research research = researchService.selectResearchByResearchId(researchId);
        mmap.put("research", research);
        return prefix + "/edit";
    }

    /**
     * 修改保存【请填写功能名称】
     */
    @RequiresPermissions("research:research:edit")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Research research)
    {
        return toAjax(researchService.updateResearch(research));
    }

    /**
     * 删除【请填写功能名称】
     */
    @RequiresPermissions("research:research:remove")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(researchService.deleteResearchByResearchIds(ids));
    }
}
