package com.orange.mea.apisi.party.backend.bscs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orange.bscs.api.connection.service.BscsConnectionService;
import com.orange.bscs.api.exceptions.BscsConnectionException;
import com.orange.bscs.model.BscsCountry;
import com.orange.bscs.model.BscsCustomerGroup;
import com.orange.bscs.model.BscsIdentificationDocumentType;
import com.orange.bscs.model.BscsTitle;
import com.orange.bscs.service.BscsBusinessPartnerServiceAdapter;
import com.orange.bscs.service.BscsCommonDomainServiceAdapter;
import com.orange.mea.apisi.party.exceptions.CountryNotFoundException;
import com.orange.mea.apisi.party.exceptions.CustomerGroupNotFoundException;
import com.orange.mea.apisi.party.exceptions.IndividualTitleNotFoundException;
import com.orange.mea.apisi.party.exceptions.NationalIdTypeNotFoundException;

/**
 * Service loading all constants from BSCS at startup to provide them to any
 * other service needing to use them during runtime
 * 
 * @author xbbs3851
 *
 */
@Service
public class BscsConstantsService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private BscsConnectionService bscsConnectionService;

    @Autowired
    private BscsCommonDomainServiceAdapter commonDomainServiceAdapter;

	@Autowired
    private BscsBusinessPartnerServiceAdapter businessPartnerServiceAdapter;

	/**
	 * Types of national Id documents <br />
	 * Key : type code
	 */
    private Map<Long, BscsIdentificationDocumentType> nationalIdTypes;

    /**
     * individual titles<br />
     * Key : code
     * 
     */
    private Map<Long, BscsTitle> individualTitles;

	/**
	 * Countries Key : code
	 */
    private Map<Long, BscsCountry> countries;

	/**
	 * CustomerGroups
	 * Key : code
	 * Value : description
	 */
	private Map<String, BscsCustomerGroup> customerGroups;

	/**
	 * During server start-up, constants are loaded into memory to be used
	 * during runtime
	 */
	@PostConstruct
	public void loadConstantsFromBSCS() {

		logger.info("Loading constants from BSCS...");

		try {
			bscsConnectionService.openConnection();
		} catch (BscsConnectionException e) {
            logger.error("Cannot initialize constants from BSCS", e);
		}

		loadCountries();
		loadCustomerGroups();
        // not used anymore
        // loadNationalIdTypes();
        // loadIndividualTitles();

		bscsConnectionService.closeConnection();

		logger.debug("Constants from BSCS loaded");

	}

	/**
	 * Load all national ID types from BSBS to memory
	 */
	private void loadNationalIdTypes() {
		logger.debug("Loading NationalIdType from BSCS...");

        nationalIdTypes = new HashMap<Long, BscsIdentificationDocumentType>();

		// Getting values in BSCS
        List<BscsIdentificationDocumentType> types = businessPartnerServiceAdapter.getIdentificationDocumentTypes();
		logger.debug("Types received from bscs : {}", types);

		// Parsing results and populating constants
		Long code;
        for (BscsIdentificationDocumentType type : types) {
            code = type.getNumericCode();
            if (code != null) {
                nationalIdTypes.put(code, type);
			}
		}

	}

	/**
     * Load all individual titles from BSCS to memory
     */
    private void loadIndividualTitles() {
        logger.debug("Loading IndividualTitle from BSCS...");

        individualTitles = new HashMap<Long, BscsTitle>();

        // Getting values in BSCS
        List<BscsTitle> titles = businessPartnerServiceAdapter.getTitles();

        // Parsing results and populating constants
        Long id;
        for (BscsTitle title : titles) {
            id = title.getNumericId();
            if (id != null) {
                individualTitles.put(id, title);
            }
        }

    }

    /**
     * Load all countries from BSCS to memory
     */
	private void loadCountries() {
		logger.debug("Loading Countries from BSCS...");

        countries = new HashMap<Long, BscsCountry>();

		// Getting values in BSCS
        List<BscsCountry> foundCountries = commonDomainServiceAdapter.getCountries();

		// Parsing results and populating constants
        Long id;
        for (BscsCountry country : foundCountries) {
            id = country.getNumericId();
            if (id != null) {
                countries.put(id, country);
			}
		}
	}

	private void loadCustomerGroups() {
		logger.debug("Loading CustomerGroups from BSCS...");

		customerGroups = new HashMap<String, BscsCustomerGroup>();

		//Getting values in BSCS
		List<BscsCustomerGroup> foundCustomerGroups = businessPartnerServiceAdapter.getCustomerGroups();

		// Parsing results and populating constants
		String code;
		for (BscsCustomerGroup customerGroup : foundCustomerGroups) {
			code = customerGroup.getCode();
			if (code != null) {
				customerGroups.put(code, customerGroup);
			}
		}

	}

    /**
     * Gets a national ID type using its code
     * 
     * @param code
     * @return
     * @throws NationalIdTypeNotFoundException
     */
    public BscsIdentificationDocumentType getNationalIdTypeByCode(Long code) throws NationalIdTypeNotFoundException {
        BscsIdentificationDocumentType result = nationalIdTypes.get(code);

		if (result == null) {
			logger.error("National Id Type not found for code : [{}]", code);
			throw new NationalIdTypeNotFoundException("National Id Type not found for code : " + code);
		}

		return result;
	}

    /**
     * Gets a national ID type using its name
     * 
     * @param name
     * @return
     * @throws NationalIdTypeNotFoundException
     */
    public BscsIdentificationDocumentType getNationalIdTypeByName(String name) throws NationalIdTypeNotFoundException {

        for (BscsIdentificationDocumentType entry : nationalIdTypes.values()) {
			if (entry.getName().equalsIgnoreCase(name)) {
				return entry;
			}
		}

		logger.error("National Id Type not found for name : [{}]", name);
		throw new NationalIdTypeNotFoundException("National Id Type not found for name : " + name);

	}

    /**
     * Gets an individual type using its id
     * 
     * @param id
     * @return
     * @throws IndividualTitleNotFoundException
     */
    public BscsTitle getIndividualTitleById(Long id) throws IndividualTitleNotFoundException {
        BscsTitle result = individualTitles.get(id);

		if (result == null) {
            logger.error("No IndividualTitle found for id : [{}]", id);
            throw new IndividualTitleNotFoundException("No IndividualTitle found for id : " + id);
		}

		return result;
	}

    /**
     * Gets an individual type using its name
     * 
     * @param name
     * @return
     * @throws IndividualTitleNotFoundException
     */
    public BscsTitle getIndividualTitleByName(String name) throws IndividualTitleNotFoundException {
        for (BscsTitle title : individualTitles.values()) {
            if (title.getDescription().equalsIgnoreCase(name)) {
                return title;
			}
		}

		logger.error("No IndividualTitle found for name : [{}]", name);
		throw new IndividualTitleNotFoundException("No IndividualTitle found for name : " + name);

	}

    /**
     * Gets a country using its numeric id
     * 
     * @param id
     * @return
     * @throws CountryNotFoundException
     */
    public BscsCountry getCountryById(Long id) throws CountryNotFoundException {
        BscsCountry result = countries.get(id);

		if (result == null) {
            logger.error("No country found for id : [{}]", id);
            throw new CountryNotFoundException("No country found for id : " + id);
		}

		return result;
	}

    /**
     * Gets a country using its name
     * 
     * @param name
     * @return
     * @throws CountryNotFoundException
     */
    public BscsCountry getCountryByName(String name) throws CountryNotFoundException {
        for (BscsCountry entry : countries.values()) {
            if (entry.getDescription().equalsIgnoreCase(name)) {
				return entry;
			}
		}

		logger.error("No country found for name : [{}]", name);
		throw new CountryNotFoundException("No country found for name : " + name);
	}

    /**
     * Gets a country using its iso2
     * 
     * @param iso2
     * @return
     * @throws CountryNotFoundException
     */
    public BscsCountry getCountryByIso2(String iso2) throws CountryNotFoundException {
        for (BscsCountry entry : countries.values()) {
			if (entry.getIso2().equalsIgnoreCase(iso2)) {
				return entry;
			}
		}

		logger.error("No country found for iso2 : [{}]", iso2);
		throw new CountryNotFoundException("No country found for iso2 : " + iso2);
	}

    /**
     * Gets a customerGroup using its code (e.g PRG_CODE)
     *
     * @param code
     * @return
     * @throws CustomerGroupNotFoundException
     */
    public BscsCustomerGroup getCustomerGroupByCode(String code) throws CustomerGroupNotFoundException {
		BscsCustomerGroup result = customerGroups.get(code);

		if(result == null) {
			logger.error("No  found for code : [{}]", code);
			throw new CustomerGroupNotFoundException("No customerGroup found for code : " + code);
		}
		return result;
	}

}
