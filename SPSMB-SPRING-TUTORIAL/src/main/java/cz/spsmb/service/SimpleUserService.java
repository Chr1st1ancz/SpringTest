package cz.spsmb.service;

import cz.spsmb.entity.User;
import cz.spsmb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SimpleUserService implements UserService {

    public String MD5(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        }
        return null;
    }
    private final UserRepository userRepository;

    @Autowired
    public SimpleUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean isAuth(String name, String password) {

        User user =  this.userRepository.findByName(name);
        if(user == null){
            return false;
        }
        String salt = user.getSalt();
        password = MD5(password + salt);
        return user.getPassword().compareTo(password) == 0;

    }

    @Override
    public void save(User user) {
        String salt = String.valueOf(Math.random()*100);
        user.setSalt(salt);
        String password = user.getPassword();
        password = MD5(password + salt);
        //TODO HASH
        user.setPassword(password);
        this.userRepository.save(user);
    }
}
