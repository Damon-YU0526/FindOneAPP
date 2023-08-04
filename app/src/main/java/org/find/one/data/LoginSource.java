package org.find.one.data;

import org.find.one.data.model.Result;

class LoginSource implements LoginDao{



    @Override
    public Result signIn(String name, String pwd) {

        if("abcdefg".equals(name) && "123456".equals(pwd)) {
            return new Result.Success(name);
        }

        return new Result.Error(new IllegalAccessException());
    }

    @Override
    public Result signOut() {
       return new Result.Success("sing out");
    }

    @Override
    public Result signUp(String name, String pwd) {
        return new Result.Success(name);
    }
}
