package com.qdsg.ylt.ylt_user.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.qdsg.ylt.base.controller.BaseController;
import com.qdsg.ylt.kit.MD5Util;
import com.qdsg.ylt.ylt_user.common.BaseResult;
import com.qdsg.ylt.ylt_user.common.model.User;
import com.qdsg.ylt.ylt_user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
//@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    UserService userService;


//    @RequestMapping(value = "/add")
//    public BaseResult<Boolean> addUser(@ModelAttribute("user")User user,String path) {
//        Boolean flag = false;
//        try{
//            flag  = userService.addUser(user);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new BaseResult(0,"500","注册失败",false);
//        }
//        if(flag){
//            try {
//                getHttpServletResponse().sendRedirect(path);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            return new BaseResult<Boolean>(1,"200","注册成功",true);
//        }
//            return new BaseResult(1,"500","注册失败",false);
//    }
//
//    @RequestMapping(value = "/update")
//    public BaseResult<Boolean> updateUser(@ModelAttribute("user")User user) {
//        List<User> users = userService.selectList(new EntityWrapper<User>().eq("account", user.getAccount()));
//        if(null!= users && users.size()>0){
//            User user1 = users.get(0);
//            user.setId(user1.getId());
//            user.setPassword(user1.getPassword());
//            userService.updateById(user);
//            return new BaseResult(1,true);
//        }
//        return new BaseResult(1,"200","失败",false);
//    }
//    @RequestMapping(value = "/delete")
//    public BaseResult<Boolean> deleteUser(@ModelAttribute("user")User user){
////        List<User> users = userService.selectList(new EntityWrapper<User>().eq("account", user.getAccount()));
////        if(null!= users && users.size()>0) {
////            user = users.get(0);
//            user.setFlag(0);
//            userService.updateById(user);
//            return new BaseResult(1,true);
////        }
////        return new BaseResult(1,"200","失败",null);
//    }
}
