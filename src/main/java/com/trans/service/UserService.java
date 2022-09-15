package com.trans.service;


import com.trans.model.User;

import java.util.List;

public interface UserService {
  User findByEmailAndPassword(String e,String p);
  void save(User user);
  void deleteById(int id);
  void update(User user);
  User findById(int id);
  List<User> getAll();
}