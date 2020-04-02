package com.orange.bscs.api.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.orange.bscs.api.connection.service.BscsConnectionService;
import com.orange.bscs.api.exceptions.BscsConnectionException;
import com.orange.bscs.api.exceptions.BscsTechnicalException;
import com.orange.bscs.api.model.exception.SOIException;

@Aspect
@Component
public class TransactionalBscsAspect {

    protected final static Logger log = LoggerFactory.getLogger(TransactionalBscsAspect.class);

    @Autowired
    private BscsConnectionService bscsConnectionService;


    @Around("@annotation(TransactionalBscs)")
    public Object aroundAdvice(final ProceedingJoinPoint joinPoint) throws Throwable {
        log.debug("==================> OPEN BSCS transaction");
		try {
			this.bscsConnectionService.openConnection();
		} catch (final BscsConnectionException e) {
			log.error("BSCS uncatched error", e);
			throw new BscsTechnicalException(e);
		}
        try {
            final Object result = joinPoint.proceed();
            return result;
        } catch (final SOIException e) {
            log.error("BSCS uncatched error", e);
            throw new BscsTechnicalException(e);
        } finally {
            log.debug("CLOSE BSCS transaction <==================");
            this.bscsConnectionService.closeConnection();
        }
    }
}
