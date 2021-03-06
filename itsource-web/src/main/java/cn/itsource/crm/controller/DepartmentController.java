package cn.itsource.crm.controller;

import cn.itsource.crm.domain.Department;
import cn.itsource.crm.query.DepartmentQuery;
import cn.itsource.crm.util.AjaxResult;
import cn.itsource.crm.util.PageList;
import cn.itsource.crm.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * @author solargen
 * @since 2019-06-05
 */
@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private IDepartmentService departmentService;

    /**
     * 保存
     * @param department
     * @return
     */
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public AjaxResult save(@RequestBody Department department){
        try {
            if(department.getId()!=null){
                //添加
                departmentService.add(department);
            }else{
                //修改
                departmentService.update(department);
            }
            return AjaxResult.me().setMessage("保存成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("保存失败!"+e.getMessage());
        }
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public AjaxResult delete(@RequestParam("id")Long id){
        try {
            departmentService.remove(id);
            return AjaxResult.me().setMessage("删除成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMessage("删除失败!");
        }
    }

    /**
     * 查询所有
     * @return
     */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<Department> list(){
        return departmentService.getAll();
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @RequestMapping(value = "getById",method = RequestMethod.GET)
    public Department getById(@RequestParam("id") Long id){
        return departmentService.getById(id);
    }

    /**
     * 分页高级查询
     * @param query
     * @return
     */
    @RequestMapping(value = "query",method = RequestMethod.POST)
    public PageList<Department> query(DepartmentQuery query){
        return departmentService.getPage(query);
    }

}
