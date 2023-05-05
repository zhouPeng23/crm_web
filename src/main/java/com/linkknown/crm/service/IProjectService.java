package com.linkknown.crm.service;


import com.linkknown.crm.bean.dos.Project;
import com.linkknown.crm.bean.req.ThisProjectDownReq;
import com.linkknown.crm.bean.req.ThisProjectUpReq;

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


    /**
     * 添加项目
     * @param project 项目
     */
    void addProject(Project project);


    /**
     * 更新项目
     * @param project 项目
     */
    void updateProject(Project project);


    /**
     * 删除项目
     * @param project 项目
     */
    void deleteProject(Project project);


    /**
     * 上移项目
     * @param thisProjectUpReq 请求
     */
    void thisProjectUp(ThisProjectUpReq thisProjectUpReq);


    /**
     * 下移项目
     * @param thisProjectDownReq 请求
     */
    void thisProjectDown(ThisProjectDownReq thisProjectDownReq);



}
