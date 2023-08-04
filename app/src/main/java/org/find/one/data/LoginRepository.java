package org.find.one.data;

import org.find.one.R;
import org.find.one.data.model.Result;
import org.find.one.data.model.User;

public class LoginRepository {

    private static volatile LoginRepository instance = null;

    private LoginSource loginSource;

    private User user;

    private LoginRepository() {
        loginSource = new LoginSource();
        user = new User();
    }


    public static LoginRepository getInstance() {
        if(instance == null) {
            instance = new LoginRepository();
        }
        return instance;
    }

    /**
     * load user data according to name
     * @param name user name
     * @param pwd user password
     * @return
     */
    public Result login(String name, String pwd) {
        Result result = loginSource.signIn(name, pwd);
        if(result instanceof Result.Success) {
            loadingUser(name);
        }
        return result;

    }

    public Result signUp(String name,String pwd) {

        return loginSource.signUp(name, pwd);
    }

    public Result signIn(String name, String pwd) {
        return loginSource.signIn(name, pwd);
    }

    public Result singOut() {
        return loginSource.signOut();
    }

    /**
     * provide fake data
     * @param name user name
     */
    public void loadingUser(String name){
       user.setUsername(name);
       user.setAge(22);
       user.setSex("male");
       user.setDescription("good good very good");
       user.setImgRes(R.drawable.cat);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
