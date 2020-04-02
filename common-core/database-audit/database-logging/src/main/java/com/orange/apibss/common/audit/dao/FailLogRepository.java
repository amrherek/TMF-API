package com.orange.apibss.common.audit.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Date : 12/01/2017.
 *
 * @author Denis Borscia (deyb6792)
 */
@Repository
public interface FailLogRepository extends CrudRepository<FailHistoricLog, Integer> {
}
