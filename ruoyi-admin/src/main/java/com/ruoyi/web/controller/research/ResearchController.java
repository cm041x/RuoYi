package com.ruoyi.web.controller.research;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;

import com.ruoyi.common.utils.ShiroUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.research.domain.Research;
import com.ruoyi.research.domain.SysFileInfo;
import com.ruoyi.research.service.IResearchService;
import com.ruoyi.research.service.ISysFileInfoService;
import com.ruoyi.system.domain.SysNotice;
import com.ruoyi.system.service.ISysNoticeService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * 项目管理Controller
 * 
 * @author xionglei
 * @date 2023-03-09
 */

@Component("doTest")
@Controller
@RequestMapping("/research/research")
public class ResearchController extends BaseController
{
    private String prefix = "research/research";

    @Autowired
    private IResearchService researchService;

    @Autowired
    private ISysNoticeService noticeService;

    @Autowired
    private ISysFileInfoService fileInfoService;

    @RequiresPermissions("research:research:view")
    @GetMapping()
    public String research()
    {
        return prefix + "/research";
    }



    /*文件上传*/
    @RequestMapping("/upload/{researchId}")
    public String  uploadFile(@PathVariable("researchId") String researchId,ModelMap mmap,HttpServletRequest request){

//        System.out.println(researchId+"11"+researchName);
        mmap.addAttribute("researchId",researchId);
//        request.getSession().setAttribute("researchId",researchId);

        String researchCode = researchService.selectResearchByResearchId(Long.valueOf(researchId)).getResearchCode();
        request.getSession().setAttribute("researchCode",researchCode);//将researchCode存入session中


        return prefix + "/upload";

    }


    /**
     * 上传文件
     * @param file
     * @param researchId
     * @return
     */
    @RequestMapping("/uploadFile")
    @ResponseBody
    public AjaxResult uploadFile(@RequestParam("files") MultipartFile[] file, @RequestParam("researchId")String researchId){
        String filePath = RuoYiConfig.getUploadPath();
        try {

            for (int i = 0; i < file.length; i++) {
                String filePath1 = FileUploadUtils.upload(filePath, file[i]);
                System.out.println(filePath1);
                System.out.println(file[i].getOriginalFilename());
                System.out.println();

                SysFileInfo sysFileInfo = new SysFileInfo();
                sysFileInfo.setFileName(file[i].getOriginalFilename());
                sysFileInfo.setFilePath(filePath1);
                sysFileInfo.setResearchCode(researchService.selectResearchByResearchId(Long.valueOf(researchId)).getResearchCode());
                sysFileInfo.setResearchName(researchService.selectResearchByResearchId(Long.valueOf(researchId)).getResearchName());

                fileInfoService.insertSysFileInfo(sysFileInfo);

            }
        } catch (IOException e) {
            e.printStackTrace();
            AjaxResult.error("文件上传失败!");
        }
        return AjaxResult.success("文件上传成功");
    }


    /**
     * 定时发布截止日期未完成公告
     */
    public void dotest()
    {
//        notice.setNoticeContent("");
//        notice.setCreateBy(getLoginName());
//        return toAjax(noticeService.insertNotice(notice));
//        noticeService.insertNotice(notice);
        System.out.println("执行有参方法：");
        Research research = new Research();
        List<Research> list = researchService.selectResearchList(research);
//        System.out.println(list);
        Iterator<Research> iterator = list.iterator();//获取list集合迭代器

        while (iterator.hasNext()) {//遍历项目集合
            Research next =  iterator.next();
            Date endTime = next.getEndTime();//获取截止时间

            Date queueDate = new Date();
            SimpleDateFormat queueDateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");//日期格式
            long nowTime = System.currentTimeMillis();//获取系统当前时间

            long overTime = endTime.getTime();//结束时间

            Long oneMonth = 2626560000L;//一个月的毫秒值

            if (nowTime - overTime < oneMonth && next.getResearchType().equals("未完成")){
                SysNotice notice = new SysNotice();

                notice.setNoticeTitle("项目《"+next.getResearchName()+"》距离截止日期不足一月，请合理安排项目时间");
                notice.setNoticeType("1");
                notice.setNoticeContent("xxx时间要截止了");
                notice.setCreateBy("admin");
                notice.setStatus("0");

                noticeService.insertNotice(notice);
            }


        }


    }



    /**
     * 查询项目列表
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
     * 新增项目
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存项目
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
     * 修改项目
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
     * 修改保存项目
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
     * 删除项目
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
