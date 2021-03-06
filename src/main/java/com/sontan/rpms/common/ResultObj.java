package com.sontan.rpms.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultObj {
    public static final ResultObj LOGIN_SUCCESS=new ResultObj("登陆成功",Constant.OK);
    public static final ResultObj LOGIN_ADD_SUCESS=new ResultObj("添加成功",Constant.OK);
    public static final ResultObj LOGIN_MOD_SUCESS=new ResultObj("修改成功",Constant.OK);
    public static final ResultObj LOGIN_ADD_REPEAT=new ResultObj("账号重复或已有该业主",Constant.ERROR);
    public static final ResultObj FLAT_ADD_REPEAT=new ResultObj("已有该房屋信息",Constant.ERROR);
    public static final ResultObj RELATIONSHIP_ADD_REPEAT=new ResultObj("关系已有",Constant.ERROR);
    public static final ResultObj LOGIN_ADD_ERROR=new ResultObj("添加失败",Constant.ERROR);
    public static final ResultObj LOGIN_MOD_ERROR=new ResultObj("修改失败",Constant.ERROR);
    public static final ResultObj LOGIN_ERROR_ACCOUNTORPWD=new ResultObj("账号或密码错误",Constant.ERROR);
    public static final ResultObj LOGIN_ERROR_CODE =new ResultObj("验证码错误",Constant.ERROR);
    public static final ResultObj DELETE_SUCCESS =new ResultObj("删除成功",Constant.OK);
    public static final ResultObj DELETE_ERROR =new ResultObj("删除失败",Constant.ERROR);
    public static final ResultObj UNBIND_SUCCESS =new ResultObj("解绑成功",Constant.OK);
    public static final ResultObj BIND_SUCCESS =new ResultObj("绑定成功",Constant.OK);
    public static final ResultObj UNBIND_ERROR =new ResultObj("解绑失败",Constant.ERROR);
    public static final ResultObj BIND_ERROR =new ResultObj("绑定失败",Constant.ERROR);
    public static final ResultObj CONFIRM_SUCCESS =new ResultObj("处理成功",Constant.OK);
    public static final ResultObj PAY_SUCCESS =new ResultObj("支付成功",Constant.OK);
    public static final ResultObj CONFIRM_ERROR =new ResultObj("处理失败",Constant.ERROR);
    public static final ResultObj PAY_ERROR =new ResultObj("支付失败",Constant.ERROR);

    private String msg;
    private Integer code;

}
