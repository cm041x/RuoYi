package com.ruoyi.research.mapper;

import java.util.List;
import com.ruoyi.research.domain.Research;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author xionglei
 * @date 2023-03-09
 */
public interface ResearchMapper 
{
    /**
     * 查询項目
     *
     * @param researchId 項目主键
     * @return 項目
     */
    public  Research selectResearchByResearchId(Long researchId);

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
     * 删除項目
     * 
     * @param researchId 項目主键
     * @return 结果
     */
    public int deleteResearchByResearchId(Long researchId);

    /**
     * 批量删除項目
     * 
     * @param researchIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteResearchByResearchIds(String[] researchIds);
}
