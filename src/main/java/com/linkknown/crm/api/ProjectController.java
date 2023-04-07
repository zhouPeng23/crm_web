package com.linkknown.crm.api;

import com.linkknown.crm.bean.dos.Project;
import com.linkknown.crm.bean.dos.Role;
import com.linkknown.crm.common.aspect.exception.WebExceptionService;
import com.linkknown.crm.common.aspect.paramslog.WebParamsLog;
import com.linkknown.crm.common.response.BaseResponse;
import com.linkknown.crm.common.util.paramutil.ProjectParamUtils;
import com.linkknown.crm.common.util.paramutil.RoleParamUtils;
import com.linkknown.crm.service.IProjectService;
import com.linkknown.crm.service.IRoleService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhoupeng
 * @date 2022/6/10 15:42
 */
@WebExceptionService
@RestController
@RequestMapping("/crmWebApi/project")
@Validated
public class ProjectController {

    @Resource
    private IProjectService projectService;

    @PostMapping(value = "/queryProjectList")
    @WebParamsLog(description = "查询店里项目集合")
    public BaseResponse<List<Project>> queryProjectList(Project project){
        ProjectParamUtils.queryAllProjectsValidate(project);
        List<Project> projectList = projectService.queryProjectList(project);
        return BaseResponse.success(projectList);
    }


}
