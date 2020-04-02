package com.orange.mea.apisi.security.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.orange.mea.apisi.security.Constant;
import com.orange.mea.apisi.security.model.Role;
import com.orange.mea.apisi.security.model.User;
import com.orange.mea.apisi.security.repository.ApiUserRepository;

/**
 * Date : 13/12/2016.
 *
 * @author Denis Borscia (deyb6792)
 */
public class ApiUserDetailsService implements UserDetailsService {

    @Autowired
    private ApiUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ApiAuthenticationFilter.ApiPrincipal principal = new ApiAuthenticationFilter.ApiPrincipal(username);
        List<User> users = userRepository.findByPrincipal(principal.getLogin(),
                ConvertSha256(principal.getPassword()),
                principal.getPlatform());

        if (users == null || users.isEmpty()) {
            throw new UsernameNotFoundException("User not found");

        }

        org.springframework.security.core.userdetails.User securityUser = null;
        Collection<CustomRole> roles = new ArrayList<>();
        for (User user : users) {
            if (user.getRoles() != null) {
                for (Role role : user.getRoles()) {
                    CustomRole customRole = new CustomRole();
                    customRole.setAuthority(role.getRoleName());
                    roles.add(customRole);
                }
            }
        }
        securityUser = new org.springframework.security.core.userdetails.User(principal.getLogin(),
                principal.getPassword(), users.get(0).isActive(), true, true, true, roles);
        return securityUser;
    }

    /**
     * encoder the String to sha256
     * @param password
     * @return password
     */
    private String ConvertSha256(String password) {
        MessageDigestPasswordEncoder messageDigestPasswordEncoder = new MessageDigestPasswordEncoder(
                Constant.ENCODERFORMAT);
        return messageDigestPasswordEncoder.encodePassword(password, null);
    }

}
