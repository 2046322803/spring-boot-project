package com.spring.boot.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.spring.boot.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUserName(String userName);

	User findByUserNameOrEmail(String username, String email);

	@Transactional(timeout = 10)
	@Modifying
	@Query("update User set userName = ?1 where id = ?2")
	int modifyById(String userName, Long id);

	@Transactional
	@Modifying
	@Query("delete from User where id = ?1")
	void deleteById(Long id);

	@Query("select u from User u where u.email = ?1")
	User findByEmail(String email);

	@Query("select u from User u")
	Page<User> findALL(Pageable pageable);

	Page<User> findByNickName(String nickName, Pageable pageable);

	/**
	 * Page实现了获取所有记录的数量和页面的总数，但是它是通过count query来计算的，所以这个代价就是很大的。
	 * 所以，当我们有一个很大的数据集的时候，Slice可能就能满足我们的需求了。因为大多数时候，我们并不需要知道结果集总数是多少。
	 */
	Slice<User> findByNickNameAndEmail(String nickName, String email, Pageable pageable);

}