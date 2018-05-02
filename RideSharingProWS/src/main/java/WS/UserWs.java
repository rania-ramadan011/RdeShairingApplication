/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WS;

import WSInterfaces.MyUserDao;

import com.mycompany.ridesharingprows.DriverCarInfo;

import com.mycompany.ridesharingprows.User;
import java.util.Date;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Rania
 */
@Controller
public class UserWs {

    ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
    MyUserDao userDao = context.getBean(MyUserDao.class);

    //Return user from Database By Email and Password---------------------------------------------
    @RequestMapping(value = "/getUserByEmailAndPassword", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody

    User getUserByEmailAndPassword(@RequestBody User object) {
        System.out.println("in Methos ByEMailAndPasword eeeeeeeeeeeeeeeeeee");
        User user = userDao.findByEMailAndPasword(object.getEMail(), object.getPassword());
        System.out.println("user name eeeeeeeeeeeeeeeeeeeeeee" + user.getUserName());
        return user;

    }

    // insert user into DataBase-------------------------------------------------
    @RequestMapping(value = "/user", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public User InsertUserDB(@RequestBody User user) {

        System.out.println("user insert" + user.getUserName());
        System.out.println("user insert" + user.getEMail());

        System.out.println("user insert" + user.getGender());
        System.out.println("user insert" + user.getMobile());
        System.out.println("user insert" + user.getNationalid());
        System.out.println("user insert" + user.getPassword());

        user.setUserphoto("aaa");
        System.out.println("user insert" + user.getUserphoto());

        System.out.println("user insert" + user.getUserName());
        System.out.println("user insert" + user.getUserName());

        user.setBirthDate(new Date());
        user.setPending("0");
        userDao.save(user);
        System.out.println("user insert" + user.getUserName());
        return user;
    }

}
