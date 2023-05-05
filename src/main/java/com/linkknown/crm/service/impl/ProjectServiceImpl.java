package com.linkknown.crm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.linkknown.crm.bean.dos.Project;
import com.linkknown.crm.bean.req.ThisProjectDownReq;
import com.linkknown.crm.bean.req.ThisProjectUpReq;
import com.linkknown.crm.mapper.ProjectMapper;
import com.linkknown.crm.service.IProjectService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

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
        QueryWrapper<Project> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("shop_id",project.getShopId())
                    .orderByAsc("order_id");
        return projectMapper.selectList(queryWrapper);
    }


    /**
     * 添加项目
     * @param project 项目
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void addProject(Project project) {
        //如果是第一次插入项目表就设置order_id = 1
        Project projectParam = new Project();
        projectParam.setShopId(project.getShopId());
        List<Project> projectDbList = projectMapper.selectProjectList(projectParam);
        if (CollectionUtils.isEmpty(projectDbList)){
            project.setOrderId(1);
            projectMapper.insertProject(project);

        }else{
            //查询目前该店铺项目最大的orderId
            int maxOrderId = projectMapper.selectMaxOrderId(project.getShopId());
            //设置orderid + 1
            project.setOrderId(maxOrderId+1);
            projectMapper.insertProject(project);
        }
    }


    /**
     * 更新项目
     * @param project 项目
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void updateProject(Project project) {
        projectMapper.updateProject(project);
    }


    /**
     * 删除项目
     * @param project 项目
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void deleteProject(Project project) {
        projectMapper.deleteProjectById(project.getProjectId());
    }


    /**
     * 上移项目
     * @param thisProjectUpReq 请求
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void thisProjectUp(ThisProjectUpReq thisProjectUpReq){
        //入参
        Integer shopId = thisProjectUpReq.getShopId();
        Integer currentProjectId = thisProjectUpReq.getCurrentProjectId();
        Integer upperProjectId = thisProjectUpReq.getUpperProjectId();

        //查询项目
        Project currentProject = projectMapper.selectProjectById(currentProjectId);
        Project upperProject = projectMapper.selectProjectById(upperProjectId);

        //存储orderId
        Integer currentProjectOrderId = currentProject.getOrderId();
        Integer upperProjectOrderId = upperProject.getOrderId();

        //更新项目
        currentProject.setOrderId(upperProjectOrderId);
        projectMapper.updateProject(currentProject);

        upperProject.setOrderId(currentProjectOrderId);
        projectMapper.updateProject(upperProject);
    }


    /**
     * 下移项目
     * @param thisProjectDownReq 请求
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void thisProjectDown(ThisProjectDownReq thisProjectDownReq){
        //入参
        Integer shopId = thisProjectDownReq.getShopId();
        Integer currentProjectId = thisProjectDownReq.getCurrentProjectId();
        Integer downerProjectId = thisProjectDownReq.getDownerProjectId();

        //查询项目
        Project currentProject = projectMapper.selectProjectById(currentProjectId);
        Project downerProject = projectMapper.selectProjectById(downerProjectId);

        //存储orderId
        Integer currentProjectOrderId = currentProject.getOrderId();
        Integer downerProjectOrderId = downerProject.getOrderId();

        //更新项目
        currentProject.setOrderId(downerProjectOrderId);
        projectMapper.updateProject(currentProject);

        downerProject.setOrderId(currentProjectOrderId);
        projectMapper.updateProject(downerProject);
    }


}
