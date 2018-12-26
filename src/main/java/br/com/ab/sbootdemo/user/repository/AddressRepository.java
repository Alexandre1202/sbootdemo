package br.com.ab.sbootdemo.user.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.ab.sbootdemo.user.model.AddressModel;
import br.com.ab.sbootdemo.user.model.UserModel;

public interface AddressRepository extends CrudRepository<AddressModel, String>{
	Iterable<AddressModel> findByUserModel(UserModel userModel);
	AddressModel findById(long id);
}
