package com.sontan.rpms.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sontan.rpms.common.DataGridView;
import com.sontan.rpms.common.ResultObj;
import com.sontan.rpms.entity.Flat;
import com.sontan.rpms.entity.UserFamily;
import com.sontan.rpms.service.FlatLayoutService;
import com.sontan.rpms.service.FlatService;
import com.sontan.rpms.utils.CardUtil;
import com.sontan.rpms.utils.RoomNoUtil;
import com.sontan.rpms.vo.FlatVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/flat")
public class FlatController {
    @Autowired
    FlatService flatService;
    @Autowired
    FlatLayoutService flatLayoutService;

    /**
     * 加载房屋信息
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @ResponseBody
    @RequestMapping("/flatInfoList")
    public DataGridView flatInfoList(@RequestParam("page")int pageIndex,
                           @RequestParam("limit")int pageSize){
        Page<FlatVo> page = new Page<>(pageIndex,pageSize);
        flatService.getFlatVo(page);
        List<FlatVo> list =page.getRecords();
        return  new DataGridView(page.getTotal(),list);
    }

    /**
     * 跳转添加房屋信息页面
     * @return
     */
    @RequestMapping("/toAddFlatInfo")
    public String toAddFlatInfo(){
        return "/page/flat/addFlat.html";
    }

    /**
     * 添加房屋信息
     * @param flat
     * @return
     */
    @ResponseBody
    @RequestMapping("/addFlat")
    public ResultObj addFlat(Flat flat) {
        flat.setNo(RoomNoUtil.getRoomNo(flat.getBlock(),flat.getUnit(),flat.getFloor(),flat.getRoom()));
        QueryWrapper<Flat> wrapper = new QueryWrapper<>();
        wrapper.eq("no", flat.getNo());
        if (flatService.getOne(wrapper) != null) {
            return ResultObj.FLAT_ADD_REPEAT;
        } else {
            flatService.save(flat);
        }
        return ResultObj.LOGIN_ADD_SUCESS;
    }

    /**
     * 删除房屋信息
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/delFlat")
    public ResultObj delFlat(Integer id) {
        UpdateWrapper<Flat> wrapper = new UpdateWrapper<>();
        wrapper.eq("id",id);
        if(flatService.remove(wrapper)==true)
            return ResultObj.DELETE_SUCCESS;
        else
            return ResultObj.DELETE_ERROR;
    }

    /**
     * 解除业主房屋信息
     * @param ownerid
     * @return
     */
    @ResponseBody
    @RequestMapping("/unBindFlat")
    public ResultObj unBindFlat(Integer ownerid){
        UpdateWrapper<Flat> wrapper = new UpdateWrapper<>();
        wrapper.set("ownerid",null).set("state",1).eq("ownerid",ownerid);
        if(flatService.update(wrapper)){
            return ResultObj.UNBIND_SUCCESS;
        }
        return ResultObj.UNBIND_ERROR;
    }
}
