package org.find.one.data;

import org.find.one.data.model.Result;

public interface LoginDao {

    Result signIn(String name, String pwd);

    Result signOut();

    Result signUp(String name, String pwd);


}
