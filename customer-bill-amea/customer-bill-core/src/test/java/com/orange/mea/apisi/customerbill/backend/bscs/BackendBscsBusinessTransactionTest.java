package com.orange.mea.apisi.customerbill.backend.bscs;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.doThrow;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import com.orange.apibss.common.audit.AuditContext;
import com.orange.apibss.common.exceptions.badrequest.NotFoundException;
import com.orange.bscs.service.BscsAccountingServiceAdapter;
import com.orange.bscs.service.exception.BscsFieldExceptionEnum;
import com.orange.bscs.service.exception.BscsInvalidIdException;
import com.orange.mea.apisi.customerbill.transformer.BusinessTransactionTransformer;

/**
 * Date : 13/02/2017.
 *
 * @author Denis Borscia (deyb6792)
 */
@RunWith(SpringRunner.class)
public class BackendBscsBusinessTransactionTest {

    @Mock
    private AuditContext auditContext;

    @Mock
    private BscsAccountingServiceAdapter accountingServiceAdapter;

    @Mock
    private BusinessTransactionTransformer transformer;

    @InjectMocks
    private CustomerBillBscsBackendBusinessTransaction backend;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldNotFoundBillWithBackendException() throws NotFoundException, BscsInvalidIdException {
        thrown.expect(NotFoundException.class);
        thrown.expectMessage(containsString("1234"));
        thrown.expect(hasProperty("arguments", is(new String[] { "bill", "1234" })));
        BscsInvalidIdException toBeThrown = new BscsInvalidIdException(BscsFieldExceptionEnum.BUSINESS_TRANSACTION_ID,
                "1234", "");
        doThrow(toBeThrown).when(accountingServiceAdapter).getBusinessTransaction(1234L);
        backend.getBill(1234L);
    }

}
