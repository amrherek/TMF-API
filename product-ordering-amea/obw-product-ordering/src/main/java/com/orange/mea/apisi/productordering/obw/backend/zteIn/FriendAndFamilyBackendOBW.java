package com.orange.mea.apisi.productordering.obw.backend.zteIn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.orange.apibss.common.exceptions.badrequest.BadRequestException;
import com.orange.apibss.common.exceptions.technical.TechnicalException;
import com.orange.mea.apisi.obw.webservice.ZteWebService;
import com.orange.mea.apisi.obw.webservice.exception.FafServiceNotConfiguredException;
import com.orange.mea.apisi.obw.webservice.exception.InvalidMsisdnException;
import com.orange.mea.apisi.productordering.backend.faf.AddFaf;
import com.orange.mea.apisi.productordering.backend.faf.DeleteFaf;
import com.orange.mea.apisi.productordering.backend.faf.UpdateFaf;
import com.orange.mea.apisi.productordering.exception.BadMsisdnException;
import com.orange.mea.apisi.productordering.exception.NoFafException;

/**
 *
 * @author Stéphanie Gerbaud
 *
 */
@Service
@Primary
public class FriendAndFamilyBackendOBW implements AddFaf, DeleteFaf, UpdateFaf {

    @Autowired
    private ZteWebService webService;

    @Override
    public void addFaf(String msisdn, String friendAndFamilyNumber) throws TechnicalException, BadRequestException {
        try {
            webService.addFriendsAndFamily(msisdn, friendAndFamilyNumber);
        } catch (FafServiceNotConfiguredException e) {
            throw new NoFafException(e.getMsisdn(), e.getMessage());
        } catch (InvalidMsisdnException e) {
            throw new BadMsisdnException(e.getMsisdn());
        }
    }

    @Override
    public void deleteFaf(String msisdn, String friendAndFamilyNumber)
            throws BadRequestException, TechnicalException {
        try {
            webService.deleteFriendsAndFamily(msisdn, friendAndFamilyNumber);
        } catch (FafServiceNotConfiguredException e) {
            throw new NoFafException(e.getMsisdn(), e.getMessage());
        } catch (InvalidMsisdnException e) {
            throw new BadMsisdnException(e.getMsisdn());
        }
    }

    @Override
    public void updateFaf(String msisdn, String currentFriendAndFamilyNumber, String newFriendAndFamilyNumber)
            throws BadRequestException, TechnicalException {
        try {
            webService.modifyFriendsAndFamily(msisdn, currentFriendAndFamilyNumber, newFriendAndFamilyNumber);
        } catch (FafServiceNotConfiguredException e) {
            throw new NoFafException(e.getMsisdn(), e.getMessage());
        } catch (InvalidMsisdnException e) {
            throw new BadMsisdnException(e.getMsisdn());
        }
    }

}
