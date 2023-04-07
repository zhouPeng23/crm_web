package com.linkknown.crm.service.impl;

import com.linkknown.crm.bean.dos.Project;
import com.linkknown.crm.mapper.ProjectMapper;
import com.linkknown.crm.service.IProjectService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhoupeng
 * @date 2022/6/10 15:21
 */
@Service
public class ProjectServiceImpl implements IProjectService {

    @Resource
    private ProjectMapper projectMapper;


    /**
     * 查询店里项目集合
     * @param project 项目
     * @return 集合
     */
    @Override
    public List<Project> queryProjectList(Project project) {
        return projectMapper.selectProjectList(project);
    }





}
