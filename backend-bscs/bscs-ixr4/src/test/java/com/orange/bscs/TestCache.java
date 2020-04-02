package com.orange.bscs;


//@ContextConfiguration(locations = {"/spring/applicationContext-test.xml"})
public class TestCache  {
	//
	// @Autowired
	// ConnectionBackendFactoryFile fileFactory;
	//
	// @Autowired
	// ReferentialsService refService;
	//
	// @Autowired
	// ISOIConnectionProvider connectionFactory;
	//
	// @Autowired
	// ConnectionBackendFactoryRecorder recFactory;
	//
	// @Autowired
	// CacheManager cacheManager;
	//
	// @BeforeClass
	// public void init() {
	// fileFactory.changeRacinePath("/mock/cache");
	// AbstractSVLObjectFactory.init(new SVLObjectFactoryIXR4());
	// }
	//
	// @BeforeMethod
	// public void initConnection() throws Exception {
	// // clear cache
	// Collection<String> cacheNames = cacheManager.getCacheNames();
	// for (String name : cacheNames) {
	// Cache cache = cacheManager.getCache(name);
	// cache.clear();
	// }
	//
	// SOIConnection conn = connectionFactory.retreiveConnection();
	// ConnectionHolder.setConnection(conn);
	// }
	//
	//
	// @Test
	// public void checkCache() {
	// Assertions.assertThat(true);
	// ConnectionBackendRecorder backend =
	// ConnectionHolder.getCurrentConnection().getBackend(ConnectionBackendRecorder.class);
	//
	// Long expectedRP = -1L;
	// // First Table
	// refService.findRatePlanById("PPMOB");
	// Assertions.assertThat(backend.getRecords().size()).as("findRP(PPMOB)").isEqualTo(1);
	//
	// expectedRP = refService.findRatePlanById("PPMOB").getCode();
	// Assertions.assertThat(backend.getRecords().size()).as("findRP(PPMOB)
	// x 2").isEqualTo(1);
	//
	// // An other table
	// refService.findServiceById("ITEL");
	// Assertions.assertThat(backend.getRecords().size()).as("findRP(ITEL)").isEqualTo(2);
	//
	// refService.findServiceById("ITEL");
	// Assertions.assertThat(backend.getRecords().size()).as("findRP(ITEL) x
	// 2").isEqualTo(2);
	//
	//
	// // First Table already in cache
	// long actualRP = refService.findRatePlanById("PPMOB").getCode();
	// Assertions.assertThat(backend.getRecords().size()).as("findRP(PPMOB)
	// x3").isEqualTo(2);
	//
	// Assertions.assertThat(actualRP).isEqualTo(expectedRP);
	//
	// }
}
