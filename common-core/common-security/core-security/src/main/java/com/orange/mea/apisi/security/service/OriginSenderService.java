package com.orange.mea.apisi.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.orange.mea.apisi.security.repository.ApiUserRepository;

/**
 * This class retreives the authenticated user and retreives its configured
 * origin_sender in DB
 * 
 * @author JWCK2987
 *
 */
public class OriginSenderService {

    @Autowired
    private ApiUserRepository userRepository;

    /**
     * @return the originSender associated to the authenticated user
     */
    public String getOriginSender() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        String sender = null;
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
            String userName = springSecurityUser.getUsername();
            sender = userRepository.findByLogin(userName).getOriginSender();
        }
        return sender;
    }

}
