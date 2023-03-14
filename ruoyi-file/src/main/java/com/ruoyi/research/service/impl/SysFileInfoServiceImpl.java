package com.ruoyi.research.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.research.mapper.SysFileInfoMapper;
import com.ruoyi.research.domain.SysFileInfo;
import com.ruoyi.research.service.ISysFileInfoService;
import com.ruoyi.common.core.text.Convert;

/**
 * 项目文件信息Service业务层处理
 * 
 * @author xionglei
 * @date 2023-03-12
 */
@Service
public class SysFileInfoServiceImpl implements ISysFileInfoService 
{
    @Autowired
    private SysFileInfoMapper sysFileInfoMapper;

    /**
     * 查询项目文件信息
     * 
     * @param fileId 项目文件信息主键
     * @return 项目文件信息
     */
    @Override
    public SysFileInfo selectSysFileInfoByFileId(Long fileId)
    {
        return sysFileInfoMapper.selectSysFileInfoByFileId(fileId);
    }

    /**
     * 查询项目文件信息列表
     * 
     * @param sysFileInfo 项目文件信息
     * @return 项目文件信息
     */
    @Override
    public List<SysFileInfo> selectSysFileInfoList(SysFileInfo sysFileInfo)
    {
        return sysFileInfoMapper.selectSysFileInfoList(sysFileInfo);
    }

    /**
     * 新增项目文件信息
     * 
     * @param sysFileInfo 项目文件信息
     * @return 结果
     */
    @Override
    public int insertSysFileInfo(SysFileInfo sysFileInfo)
    {
        return sysFileInfoMapper.insertSysFileInfo(sysFileInfo);
    }

    /**
     * 修改项目文件信息
     * 
     * @param sysFileInfo 项目文件信息
     * @return 结果
     */
    @Override
    public int updateSysFileInfo(SysFileInfo sysFileInfo)
    {
        return sysFileInfoMapper.updateSysFileInfo(sysFileInfo);
    }

    /**
     * 批量删除项目文件信息
     * 
     * @param fileIds 需要删除的项目文件信息主键
     * @return 结果
     */
    @Override
    public int deleteSysFileInfoByFileIds(String fileIds)
    {
        return sysFileInfoMapper.deleteSysFileInfoByFileIds(Convert.toStrArray(fileIds));
    }

    /**
     * 删除项目文件信息信息
     * 
     * @param fileId 项目文件信息主键
     * @return 结果
     */
    @Override
    public int deleteSysFileInfoByFileId(Long fileId)
    {
        return sysFileInfoMapper.deleteSysFileInfoByFileId(fileId);
    }
}
