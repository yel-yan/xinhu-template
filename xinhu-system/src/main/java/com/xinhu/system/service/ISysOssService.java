package com.xinhu.system.service;

import com.xinhu.common.core.domain.PageQuery;
import com.xinhu.common.core.page.TableDataInfo;
import com.xinhu.system.domain.SysOss;
import com.xinhu.system.domain.bo.SysOssBo;
import com.xinhu.system.domain.vo.SysOssVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.List;

/**
 * 文件上传 服务层
 *
 * @author Lion Li
 */
public interface ISysOssService {

    TableDataInfo<SysOssVo> queryPageList(SysOssBo sysOss, PageQuery pageQuery);

    List<SysOssVo> listByIds(Collection<Long> ossIds);

    SysOssVo getById(Long ossId);

    SysOss upload(MultipartFile file);

    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

}
