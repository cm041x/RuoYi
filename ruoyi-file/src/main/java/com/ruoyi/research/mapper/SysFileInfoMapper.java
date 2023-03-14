package com.ruoyi.research.mapper;

import java.util.List;
import com.ruoyi.research.domain.SysFileInfo;

/**
 * 项目文件信息Mapper接口
 *
 * @author xionglei
 * @date 2023-03-13
 */
public interface SysFileInfoMapper
{
    /**
     * 查询项目文件信息
     *
     * @param fileId 项目文件信息主键
     * @return 项目文件信息
     */
    public SysFileInfo selectSysFileInfoByFileId(Long fileId);

    /**
     * 查询项目文件信息列表
     *
     * @param sysFileInfo 项目文件信息
     * @return 项目文件信息集合
     */
    public List<SysFileInfo> selectSysFileInfoList(SysFileInfo sysFileInfo);

    /**
     * 新增项目文件信息
     *
     * @param sysFileInfo 项目文件信息
     * @return 结果
     */
    public int insertSysFileInfo(SysFileInfo sysFileInfo);

    /**
     * 修改项目文件信息
     *
     * @param sysFileInfo 项目文件信息
     * @return 结果
     */
    public int updateSysFileInfo(SysFileInfo sysFileInfo);

    /**
     * 删除项目文件信息
     *
     * @param fileId 项目文件信息主键
     * @return 结果
     */
    public int deleteSysFileInfoByFileId(Long fileId);

    /**
     * 批量删除项目文件信息
     *
     * @param fileIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysFileInfoByFileIds(String[] fileIds);
}
