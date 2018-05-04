package com.ruoyi.framework.web.controller;

import java.util.List;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.framework.web.page.PageDomain;
import com.ruoyi.framework.web.page.PageUtilEntity;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.support.TableSupport;
import com.ruoyi.project.system.user.domain.User;

/**
 * web层通用数据处理
 * 
 * @author ruoyi
 */
public class BaseController
{
    /**
     * 获取请求分页数据
     */
    public PageUtilEntity getPageUtilEntity()
    {
        PageUtilEntity pageUtilEntity = TableSupport.buildPageRequest();
        return pageUtilEntity;
    }

    /**
     * 设置请求分页数据
     */
    protected void setPageInfo(Object obj)
    {
        PageDomain page = (PageDomain) obj;
        if (StringUtils.isNotEmpty(page.getPageNum()) && StringUtils.isNotEmpty(page.getPageSize()))
        {
            int pageNum = Integer.valueOf(page.getPageNum());
            int pageSize = Integer.valueOf(page.getPageSize());
            String orderBy = page.getOrderBy();
            PageHelper.startPage(pageNum, pageSize, Boolean.parseBoolean(orderBy));
        }
    }

    /**
     * 响应请求分页数据
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    protected TableDataInfo getDataTable(List<?> list)
    {
        TableDataInfo rspData = new TableDataInfo();
        rspData.setRows(list);
        rspData.setTotal(new PageInfo(list).getTotal());
        return rspData;
    }

    public User getUser()
    {
        return ShiroUtils.getUser();
    }

    public Long getUserId()
    {
        return getUser().getUserId();
    }

    public String getLoginName()
    {
        return getUser().getLoginName();
    }
}
