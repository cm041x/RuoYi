package com.ruoyi.research.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 research
 * 
 * @author xionglei
 * @date 2023-03-09
 */
public class Research extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 项目编号 */
    private Long researchId;

    /** $column.columnComment */
    @Excel(name = "项目名称"/*, readConverterExp = "$column.readConverterExp()"*/)
    private String researchName;

    /** $column.columnComment */
    @Excel(name = "项目经费"/*, readConverterExp = "$column.readConverterExp()"*/)
    private String researchPrice;

    /** $column.columnComment */
    @Excel(name = "项目状态"/*, readConverterExp = "$column.readConverterExp()"*/)
    private String researchType;

    /** $column.columnComment */
    @Excel(name = "项目简介"/*, readConverterExp = "$column.readConverterExp()"*/)
    private String researchDescription;

    /** $column.columnComment */
    @Excel(name = "开始时间"/*, readConverterExp = "$column.readConverterExp()"*/)
    private Date beginTime;

    /** $column.columnComment */
    @Excel(name = "项目编码"/*, readConverterExp = "$column.readConverterExp()"*/)
    private String researchCode;

    public void setResearchId(Long researchId) 
    {
        this.researchId = researchId;
    }

    public Long getResearchId() 
    {
        return researchId;
    }
    public void setResearchName(String researchName) 
    {
        this.researchName = researchName;
    }

    public String getResearchName() 
    {
        return researchName;
    }
    public void setResearchPrice(String researchPrice) 
    {
        this.researchPrice = researchPrice;
    }

    public String getResearchPrice() 
    {
        return researchPrice;
    }
    public void setResearchType(String researchType) 
    {
        this.researchType = researchType;
    }

    public String getResearchType() 
    {
        return researchType;
    }
    public void setResearchDescription(String researchDescription) 
    {
        this.researchDescription = researchDescription;
    }

    public String getResearchDescription() 
    {
        return researchDescription;
    }
    public void setBeginTime(Date beginTime) 
    {
        this.beginTime = beginTime;
    }

    public Date getBeginTime() 
    {
        return beginTime;
    }
    public void setResearchCode(String researchCode) 
    {
        this.researchCode = researchCode;
    }

    public String getResearchCode() 
    {
        return researchCode;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("researchId", getResearchId())
            .append("researchName", getResearchName())
            .append("researchPrice", getResearchPrice())
            .append("researchType", getResearchType())
            .append("researchDescription", getResearchDescription())
            .append("beginTime", getBeginTime())
            .append("researchCode", getResearchCode())
            .toString();
    }
}
