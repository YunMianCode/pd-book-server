package com.itheima.pinda.authority.controller.auth;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.itheima.pinda.authority.biz.service.common.OptLogService;
import com.itheima.pinda.authority.dto.auth.*;
import com.itheima.pinda.authority.entity.auth.Role;
import com.itheima.pinda.authority.entity.auth.User;
import com.itheima.pinda.authority.entity.core.Org;
import com.itheima.pinda.authority.biz.service.auth.RoleService;
import com.itheima.pinda.authority.biz.service.auth.UserService;
import com.itheima.pinda.authority.biz.service.core.OrgService;
import com.itheima.pinda.authority.biz.service.core.StationService;
import com.itheima.pinda.authority.entity.core.Station;
import com.itheima.pinda.base.BaseController;
import com.itheima.pinda.base.R;
import com.itheima.pinda.base.entity.SuperEntity;
import com.itheima.pinda.common.utils.ExcelUtils;
import com.itheima.pinda.database.mybatis.conditions.Wraps;
import com.itheima.pinda.database.mybatis.conditions.query.LbqWrapper;
import com.itheima.pinda.dozer.DozerUtils;
import com.itheima.pinda.log.annotation.SysLog;
import com.itheima.pinda.user.feign.UserQuery;
import com.itheima.pinda.user.model.SysOrg;
import com.itheima.pinda.user.model.SysRole;
import com.itheima.pinda.user.model.SysStation;
import com.itheima.pinda.user.model.SysUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 前端控制器
 * 用户
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/user")
@Api(value = "User", tags = "用户")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;
    @Autowired
    private OrgService orgService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private StationService stationService;
    @Autowired
    private OptLogService optLogService;
    @Autowired
    private DozerUtils dozer;

    /**
     * 分页查询用户
     */
    @ApiOperation(value = "分页查询用户", notes = "分页查询用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "页码", dataType = "long", paramType = "query", defaultValue = "1"),
            @ApiImplicitParam(name = "size", value = "分页条数", dataType = "long", paramType = "query", defaultValue = "10"),
    })
    @GetMapping("/page")
    @SysLog("分页查询用户")
    public R<IPage<User>> page(UserPageDTO userPage) {
        IPage<User> page = getPage();

        User user = dozer.map2(userPage, User.class);
        if (userPage.getOrgId() != null && userPage.getOrgId() >= 0) {
            user.setOrgId(null);
        }
        LbqWrapper<User> wrapper = Wraps.lbQ(user);
        if (userPage.getOrgId() != null && userPage.getOrgId() >= 0) {
            List<Org> children = orgService.findChildren(Arrays.asList(userPage.getOrgId()));
            wrapper.in(User::getOrgId, children.stream().mapToLong(Org::getId).boxed().collect(Collectors.toList()));
        }
        wrapper.geHeader(User::getCreateTime, userPage.getStartCreateTime())
                .leFooter(User::getCreateTime, userPage.getEndCreateTime())
                .like(User::getName, userPage.getName())
                .like(User::getAccount, userPage.getAccount())
                .like(User::getEmail, userPage.getEmail())
                .like(User::getMobile, userPage.getMobile())
                .eq(User::getSex, userPage.getSex())
                .eq(User::getStatus, userPage.getStatus())
                .orderByDesc(User::getId);
        userService.findPage(page, wrapper);
        List<User> records = page.getRecords();
        List<User> newList = new ArrayList<>();
        Iterator<User> iterator = records.iterator();
        while (iterator.hasNext()) {
            User tmp = iterator.next();
            String orgId = tmp.getOrgId();
            Org org = orgService.getById(orgId);
            String orgId1 = tmp.getOrgId();
            tmp.setOrgId(org.getName() + "-" + orgId1);
            String stationId = tmp.getStationId();
            Station station = stationService.getById(stationId);
            String stationId1 = tmp.getStationId();
            tmp.setStationId(station.getName() + "-" + stationId1);
            newList.add(tmp);
        }
        page.setRecords(newList);
//        page.getRecords().stream().forEach(record -> {
//            record.setOrgId(orgService.getById(record.getOrgId()).getName());
//            record.setStationId(stationService.getById(record.getStationId()).getName());
//        });
        return success(page);
    }

    /**
     * 查询用户
     */
    @ApiOperation(value = "查询用户", notes = "查询用户")
    @GetMapping("/{id}")
    @SysLog("查询用户")
    public R<User> get(@PathVariable Long id) {
        return success(userService.getById(id));
    }

    @ApiOperation(value = "查询所有用户", notes = "查询所有用户")
    @GetMapping("/find")
    @SysLog("查询所有用户")
    public R<List<Long>> findAllUserId() {
        return success(userService.list().stream().mapToLong(User::getId).boxed().collect(Collectors.toList()));
    }

    /**
     * 新增用户
     */
    @ApiOperation(value = "新增用户", notes = "新增用户不为空的字段")
    @PostMapping
    @SysLog("新增用户")
    public R<User> save(@RequestBody @Validated UserSaveDTO data) {
        User user = dozer.map(data, User.class);
        userService.saveUser(user);
        return success(user);
    }

    /**
     * 修改用户
     */
    @ApiOperation(value = "修改用户", notes = "修改用户不为空的字段")
    @PutMapping
    @SysLog("修改用户")
    public R<User> update(@RequestBody @Validated(SuperEntity.Update.class) UserUpdateDTO data) {
        User user = dozer.map(data, User.class);
        userService.updateUser(user);
        return success(user);
    }

    @ApiOperation(value = "修改头像", notes = "修改头像")
    @PutMapping("/avatar")
    @SysLog("修改头像")
    public R<User> avatar(@RequestBody @Validated(SuperEntity.Update.class) UserUpdateAvatarDTO data) {
        User user = dozer.map(data, User.class);
        userService.updateUser(user);
        return success(user);
    }

    @ApiOperation(value = "修改密码", notes = "修改密码")
    @PutMapping("/password")
    @SysLog("修改密码")
    public R<Boolean> updatePassword(@RequestBody UserUpdatePasswordDTO data) {
        return success(userService.updatePassword(data));
    }

    @ApiOperation(value = "重置密码", notes = "重置密码")
    @GetMapping("/reset")
    @SysLog("重置密码")
    public R<Boolean> resetTx(@RequestParam("ids[]") List<Long> ids) {
        userService.reset(ids);
        return success();
    }

    /**
     * 删除用户
     */
    @ApiOperation(value = "删除用户", notes = "根据id物理删除用户")
    @DeleteMapping
    @SysLog("删除用户")
    public R<Boolean> delete(@RequestParam("ids[]") List<Long> ids) {
        userService.remove(ids);
        return success(true);
    }


    /**
     * 单体查询用户
     */
    @ApiOperation(value = "查询用户详细", notes = "查询用户详细")
    @PostMapping(value = "/anno/id/{id}")
    public R<SysUser> getById(@PathVariable Long id, @RequestBody UserQuery query) {
        User user = userService.getById(id);
        if (user == null) {
            return success(null);
        }
        SysUser sysUser = dozer.map(user, SysUser.class);

        if (query.getFull() || query.getOrg()) {
            sysUser.setOrg(dozer.map(orgService.getById(user.getOrgId()), SysOrg.class));
        }
        if (query.getFull() || query.getStation()) {
            sysUser.setStation(dozer.map(stationService.getById(user.getStationId()), SysStation.class));
        }

        if (query.getFull() || query.getRoles()) {
            List<Role> list = roleService.findRoleByUserId(id);
            sysUser.setRoles(dozer.mapList(list, SysRole.class));
        }

        return success(sysUser);
    }

    /**
     * 查询角色的已关联用户
     *
     * @param roleId  角色id
     * @param keyword 账号account或名称name
     */
    @ApiOperation(value = "查询角色的已关联用户", notes = "查询角色的已关联用户")
    @GetMapping(value = "/role/{roleId}")
    public R<UserRoleDTO> findUserByRoleId(@PathVariable("roleId") Long roleId, @RequestParam(value = "keyword", required = false) String keyword) {
        List<User> list = userService.findUserByRoleId(roleId, keyword);
        List<Long> idList = list.stream().mapToLong(User::getId).boxed().collect(Collectors.toList());
        return success(UserRoleDTO.builder().idList(idList).userList(list).build());
    }

    @ApiOperation(value = "获取本周访问量",notes = "获取本周访问量")
    @GetMapping(value = "VisitOfWeek")
    public R<HashMap<String,Integer>> getVisitOfWeek(){
        HashMap<String, Integer> visitOfWeek = optLogService.getVisitOfWeek();
        return success(visitOfWeek);
    }

    @ApiOperation(value = "获取用户总数",notes = "获取用户总数")
    @GetMapping(value = "getUserCount")
    public R<Integer> getUserCount(){
        Integer userCount = userService.getUserCount();
        return success(userCount);
    }

    @GetMapping(value = "/export", produces = "application/octet-stream")
    @ApiOperation(value = "导出", produces = "application/octet-stream", response = User.class)
    public void exports(HttpServletResponse response) {
        List<User> data = userService.exportAll();
        response.setCharacterEncoding("UTF-8");
//        response.setContentType("application/ms-excel");
        response.setHeader("content-type", "application/octet-stream");
        String fileName = "数据-" + "系统注册用户信息" + ".xls";
        try {
            fileName = java.net.URLEncoder.encode(fileName, "UTF-8"); // 这句很重要，不然文件名为乱码
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
//        (personList,"花名册","草帽一伙",Person.class,"海贼王.xls",response);
        ExcelUtils.exportExcel(data, "花名册", "系统注册用户信息", User.class, fileName, response);
    }
}