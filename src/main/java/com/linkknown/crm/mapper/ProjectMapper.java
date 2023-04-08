package com.linkknown.crm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.linkknown.crm.bean.dos.Project;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zhoupeng
 */
@Repository
public interface ProjectMapper extends BaseMapper<Project> {

    public Project selectProjectById(Integer projectId);

    public List<Project> selectProjectList(Project project);

    public int insertProject(Project project);

    public int updateProject(Project project);

    public int deleteProjectById(Integer projectId);

    public int deleteProjectByIds(String[] projectIds);
}
