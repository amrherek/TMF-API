package com.orange.mea.apisi.security.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.orange.mea.apisi.security.model.User;

/**
 * Date : 14/12/2016.
 *
 * @author Denis Borscia (deyb6792)
 */
@Repository
public interface ApiUserRepository extends CrudRepository<User, String> {
    List<User> findByPrincipal(String login, String password, String platform);

    User findByLogin(String login);
}
