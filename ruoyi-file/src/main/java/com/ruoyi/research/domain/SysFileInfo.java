package com.ruoyi.research.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 项目文件信息对象 sys_file_info
 *
 * @author xionglei
 * @date 2023-03-13
 */
public class SysFileInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 文件id */
    private Long fileId;

    /** 所属项目编码 */
    @Excel(name = "所属项目编码")
    private String researchCode;

    /** 所属项目名称 */
    @Excel(name = "所属项目名称")
    private String researchName;

    /** 文件名称 */
    @Excel(name = "文件名称")
    private String fileName;

    /** 文件路径 */
    @Excel(name = "文件路径")
    private String filePath;

    public void setFileId(Long fileId)
    {
        this.fileId = fileId;
    }

    public Long getFileId()
    {
        return fileId;
    }
    public void setResearchCode(String researchCode)
    {
        this.researchCode = researchCode;
    }

    public String getResearchCode()
    {
        return researchCode;
    }
    public void setResearchName(String researchName)
    {
        this.researchName = researchName;
    }

    public String getResearchName()
    {
        return researchName;
    }
    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }

    public String getFileName()
    {
        return fileName;
    }
    public void setFilePath(String filePath)
    {
        this.filePath = filePath;
    }

    public String getFilePath()
    {
        return filePath;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("fileId", getFileId())
                .append("researchCode", getResearchCode())
                .append("researchName", getResearchName())
                .append("fileName", getFileName())
                .append("filePath", getFilePath())
                .toString();
    }
}
