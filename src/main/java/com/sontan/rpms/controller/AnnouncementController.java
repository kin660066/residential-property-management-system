package com.sontan.rpms.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sontan.rpms.common.DataGridView;
import com.sontan.rpms.common.ResultObj;
import com.sontan.rpms.entity.Announcement;
import com.sontan.rpms.entity.Flat;
import com.sontan.rpms.entity.Parking;
import com.sontan.rpms.service.AnnouncementService;
import com.sontan.rpms.utils.RoomNoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * (Announcement)表控制层
 *
 * @author makejava
 * @since 2020-03-23 09:47:33
 */
@Controller
@RequestMapping("announcement")
public class AnnouncementController extends ApiController {
    /**
     * 服务对象
     */
    @Autowired
    private AnnouncementService announcementService;

    /**
     * 管理员查看业主公告
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @ResponseBody
    @RequestMapping("/announcementList")
    public DataGridView announcementList(@RequestParam("page")int pageIndex,
                                  @RequestParam("limit")int pageSize){
        IPage<Announcement> page = new Page<>(pageIndex,pageSize);
        QueryWrapper<Announcement> wrapper=new QueryWrapper<>();
        wrapper.eq("delflag",0);
        announcementService.page(page,wrapper);
        List<Announcement> list =page.getRecords();
        return  new DataGridView(page.getTotal(),list);
    }

    /**
     * 公告详情
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/announcementDetail")
    public String announcementDetail(Integer id, ModelMap model){
        Announcement acc=announcementService.getById(id);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        model.addAttribute("title",acc.getTitle());
        model.addAttribute("content",acc.getContent());
        model.addAttribute("time",simpleDateFormat.format(acc.getPublishtime()));
        return  "/page/announcement/announcementDetail.html";
    }

    /**
     * 逻辑删除公告
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/delAnnouncement")
    public ResultObj delAnnouncement(Integer id) {
        UpdateWrapper<Announcement> wrapper = new UpdateWrapper<>();
        wrapper.eq("id",id).set("delflag",1);
        if(announcementService.update(wrapper))
            return ResultObj.DELETE_SUCCESS;
        else
            return ResultObj.DELETE_ERROR;
    }

    /**
     * 查看所有历史公告
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @ResponseBody
    @RequestMapping("/announcementHistoryList")
    public DataGridView announcementHistoryList(@RequestParam("page")int pageIndex,
                                         @RequestParam("limit")int pageSize){
        IPage<Announcement> page = new Page<>(pageIndex,pageSize);
        QueryWrapper<Announcement> wrapper=new QueryWrapper<>();
        announcementService.page(page,wrapper);
        List<Announcement> list =page.getRecords();
        return  new DataGridView(page.getTotal(),list);
    }

    /**
     * 业主查看公告
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @ResponseBody
    @RequestMapping("/announcementOwnerList")
    public DataGridView announcementOwnerList(@RequestParam("page")int pageIndex,
                                                @RequestParam("limit")int pageSize){
        IPage<Announcement> page = new Page<>(pageIndex,pageSize);
        QueryWrapper<Announcement> wrapper=new QueryWrapper<>();
        wrapper.eq("delflag",0);
        wrapper.orderByDesc("priority");
        announcementService.page(page,wrapper);
        List<Announcement> list =page.getRecords();
        return  new DataGridView(page.getTotal(),list);
    }

    /**
     * 发布公告
     * @param announcement
     * @return
     */
    @ResponseBody
    @RequestMapping("/addAnnouncement")
    public ResultObj addAnnouncement(Announcement announcement) {
        announcement.setPublishtime(new Date());
        announcement.setDelflag(0);
        if (announcementService.save(announcement)) {
            return ResultObj.LOGIN_ADD_SUCESS;
        }
        return ResultObj.FLAT_ADD_REPEAT;
    }

    /**
     * 跳转发布公告页面
     * @return
     */
    @RequestMapping("toAddAnnouncement")
    public String toAddAnnouncement(){
        return "/page/announcement/addAnnouncement.html";
    }

}