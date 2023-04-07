package com.linkknown.crm.service;


import com.linkknown.crm.bean.dos.Project;

import java.util.List;

/**
 * @author zhoupeng
 * @date 2022/6/10 15:21
 */
public interface IProjectService {

    /**
     * 查询店里项目集合
     * @param project 项目
     * @return 集合
     */
    List<Project> queryProjectList(Project project);


}
