package com.ruoyi.research.service;

import java.util.List;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.research.domain.Research;

/**
 * 項目Service接口
 * 
 * @author xionglei
 * @date 2023-03-09
 */
public interface IResearchService
{


    public void checkUserAllowed(SysUser user);

    public String importResearch(List<Research> researchList, Boolean isUpdateSupport, String operName);

    /**
     * 查询項目
     * 
     * @param researchId 項目主键
     * @return 項目
     */
    public Research selectResearchByResearchId(Long researchId);

    /**
     * 查询項目列表
     * 
     * @param research 項目
     * @return 項目集合
     */
    public List<Research> selectResearchList(Research research);

    /**
     * 新增項目
     * 
     * @param research 項目
     * @return 结果
     */
    public int insertResearch(Research research);

    /**
     * 修改項目
     * 
     * @param research 項目
     * @return 结果
     */
    public int updateResearch(Research research);

    /**
     * 批量删除項目
     * 
     * @param researchIds 需要删除的項目主键集合
     * @return 结果
     */
    public int deleteResearchByResearchIds(String researchIds);

    /**
     * 删除項目信息
     * 
     * @param researchId 項目主键
     * @return 结果
     */
    public int deleteResearchByResearchId(Long researchId);
}
