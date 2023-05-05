package com.linkknown.crm.common.util.paramutil;

import com.linkknown.crm.bean.dos.Project;
import com.linkknown.crm.bean.req.ThisProjectDownReq;
import com.linkknown.crm.bean.req.ThisProjectUpReq;
import com.linkknown.crm.common.aspect.exception.WebException;
import com.linkknown.crm.common.enums.ResponseEnum;
import com.linkknown.crm.common.util.RegexUtils;
import org.springframework.util.StringUtils;

/**
 * @author zhoupeng
 * @date 2023/4/4 14:55
 */
public class ProjectParamUtils {

    /**
     * 查询店内项目校验
     * @param project 入参
     */
    public static void queryProjectList(Project project) {
        //门店id不能为空
        if (StringUtils.isEmpty(project.getShopId())){
            throw new WebException(ResponseEnum.project_shop_id_can_not_be_empty);
        }
    }


    /**
     * 添加项目
     * @param project 项目
     */
    public static void addProject(Project project) {
        //门店id不能为空
        if (StringUtils.isEmpty(project.getShopId())){
            throw new WebException(ResponseEnum.project_shop_id_can_not_be_empty);
        }
        //项目名称不能为空
        if (StringUtils.isEmpty(project.getProjectName())){
            throw new WebException(ResponseEnum.project_project_name_can_not_be_empty);
        }
    }


    /**
     * 修改项目
     * @param project 项目
     */
    public static void updateProject(Project project) {
        //门店id不能为空
        if (StringUtils.isEmpty(project.getShopId())){
            throw new WebException(ResponseEnum.project_shop_id_can_not_be_empty);
        }
        //项目id不能为空
        if (StringUtils.isEmpty(project.getProjectId())){
            throw new WebException(ResponseEnum.project_project_id_can_not_be_empty);
        }
        //项目名称不能为空
        if (StringUtils.isEmpty(project.getProjectName())){
            throw new WebException(ResponseEnum.project_project_name_can_not_be_empty);
        }
    }


    /**
     * 删除项目
     * @param project 项目
     */
    public static void deleteProject(Project project) {
        //项目id不能为空
        if (StringUtils.isEmpty(project.getProjectId())){
            throw new WebException(ResponseEnum.project_project_id_can_not_be_empty);
        }
    }


    /**
     * 上移项目
     * @param thisProjectUpReq 请求
     */
    public static void thisProjectUp(ThisProjectUpReq thisProjectUpReq) {
        //门店id不能为空
        if (StringUtils.isEmpty(thisProjectUpReq.getShopId())){
            throw new WebException(ResponseEnum.project_shop_id_can_not_be_empty);
        }
        //项目id不能为空
        if (StringUtils.isEmpty(thisProjectUpReq.getCurrentProjectId())){
            throw new WebException(ResponseEnum.project_project_id_can_not_be_empty);
        }
        //项目id不能为空
        if (StringUtils.isEmpty(thisProjectUpReq.getUpperProjectId())){
            throw new WebException(ResponseEnum.project_project_id_can_not_be_empty);
        }
    }


    /**
     * 下移项目
     * @param thisProjectDownReq 请求
     */
    public static void thisProjectDown(ThisProjectDownReq thisProjectDownReq) {
        //门店id不能为空
        if (StringUtils.isEmpty(thisProjectDownReq.getShopId())){
            throw new WebException(ResponseEnum.project_shop_id_can_not_be_empty);
        }
        //项目id不能为空
        if (StringUtils.isEmpty(thisProjectDownReq.getCurrentProjectId())){
            throw new WebException(ResponseEnum.project_project_id_can_not_be_empty);
        }
        //项目id不能为空
        if (StringUtils.isEmpty(thisProjectDownReq.getDownerProjectId())){
            throw new WebException(ResponseEnum.project_project_id_can_not_be_empty);
        }
    }




}
