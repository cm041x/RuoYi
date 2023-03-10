package com.ruoyi.research.service.impl;

import java.util.List;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.bean.BeanValidators;
import com.ruoyi.common.utils.security.Md5Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.research.mapper.ResearchMapper;
import com.ruoyi.research.domain.Research;
import com.ruoyi.research.service.IResearchService;
import com.ruoyi.common.core.text.Convert;

import javax.validation.Validator;

import static net.sf.jsqlparser.util.validation.metadata.NamedObject.user;

/**
 * 項目Service业务层处理
 * 
 * @author xionglei
 * @date 2023-03-09
 */
@Service
public class ResearchServiceImpl implements IResearchService


{

    private static final Logger log = LoggerFactory.getLogger(ResearchServiceImpl.class);
    @Autowired
    private ResearchMapper researchMapper;
    @Autowired
    protected Validator validator;





    /**
     * 查询項目
     * 
     * @param researchId 項目主键
     * @return 項目
     */
    @Override
    public Research selectResearchByResearchId(Long researchId)
    {
        return researchMapper.selectResearchByResearchId(researchId);
    }

    /**
     * 查询項目列表
     * 
     * @param research 項目
     * @return 項目
     */
    @Override
    public List<Research> selectResearchList(Research research)
    {
        return researchMapper.selectResearchList(research);
    }

    /**
     * 新增項目
     * 
     * @param research 項目
     * @return 结果
     */
    @Override
    public int insertResearch(Research research)
    {
        return researchMapper.insertResearch(research);
    }

    /**
     * 修改項目
     * 
     * @param research 項目
     * @return 结果
     */
    @Override
    public int updateResearch(Research research)
    {
        return researchMapper.updateResearch(research);
    }

    /**
     * 批量删除項目
     * 
     * @param researchIds 需要删除的項目主键
     * @return 结果
     */
    @Override
    public int deleteResearchByResearchIds(String researchIds)
    {
        return researchMapper.deleteResearchByResearchIds(Convert.toStrArray(researchIds));
    }

    /**
     * 删除項目信息
     * 
     * @param researchId 項目主键
     * @return 结果
     */
    @Override
    public int deleteResearchByResearchId(Long researchId)
    {
        return researchMapper.deleteResearchByResearchId(researchId);
    }

    /**
     * 校验用户是否允许操作
     *
     * @param user 用户信息
     */
    @Override
    public void checkUserAllowed(SysUser user)
    {
        if (StringUtils.isNotNull(user.getUserId()) && user.isAdmin())
        {
            throw new ServiceException("不允许操作超级管理员用户");
        }
    }


    /**
     * 导入用户数据
     *
     * @param researchList 项目数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @param operName 操作用户
     * @return 结果
     */
    @Override
    public String importResearch(List<Research> researchList, Boolean isUpdateSupport, String operName)
    {
        if (StringUtils.isNull(researchList) || researchList.size() == 0)
        {
            throw new ServiceException("导入项目数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
//        String password = configService.selectConfigByKey("sys.user.initPassword");
        for (Research research : researchList)
        {
            try
            {
                // 验证是否存在这个项目
                Research u = researchMapper.selectResearchByResearchId(research.getResearchId());
                if (StringUtils.isNull(u))
                {
                    BeanValidators.validateWithException(validator, research);
//                    research.setPassword(Md5Utils.hash(research.getLoginName() + password));
                    research.setCreateBy(operName);
                    this.insertResearch(research);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、账号 " + research.getResearchName() + " 导入成功");
                }
                else if (isUpdateSupport)
                {

                    research.setUpdateBy(operName);
                    this.updateResearch(research);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、账号 " + research.getResearchCode() + " 更新成功");
                }
                else
                {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、账号 " + research.getResearchCode() + " 已存在");
                }
            }
            catch (Exception e)
            {
                failureNum++;
                String msg = "<br/>" + failureNum + "、账号 " + research.getResearchCode() + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
                log.error(msg, e);
            }
        }
        if (failureNum > 0)
        {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new ServiceException(failureMsg.toString());
        }
        else
        {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
        return successMsg.toString();
    }


}
