package br.com.ab.sbootdemo.user.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.ab.sbootdemo.user.model.UserModel;

public interface UserRepository extends CrudRepository<UserModel, String> {
	UserModel findById(long id);
}
