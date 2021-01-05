package projectshop.service;

import projectshop.dao.UserDao;
import projectshop.model.User;

public class UserService {
	private UserDao dao = new UserDao();

    public User selectUser(String ID) {
        User user = null;
        user = dao.select(ID);
        return user;
    }
    public boolean insertUser(User user) {
        boolean flag = false;
        if ( !this.dao.find(user.getID()) ) {
            flag = this.dao.insert(user);
        }
        return flag;
    }
}
