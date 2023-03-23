import jakarta.persistence.EntityManager;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * This template demonstrates how to develop a standalone test case for Hibernate ORM.  Although this is perfectly
 * acceptable as a reproducer, usage of ORMUnitTestCase is preferred!
 */
public class ORMStandaloneTestCase {

	private SessionFactory sf;

	@Before
	public void setup() {
		StandardServiceRegistryBuilder srb = new StandardServiceRegistryBuilder()
			// Add in any settings that are specific to your test. See resources/hibernate.properties for the defaults.
			.applySetting( "hibernate.show_sql", "true" )
			.applySetting( "hibernate.format_sql", "true" )
			.applySetting( "hibernate.hbm2ddl.auto", "update" )
			.applySetting("hibernate.orm.jdbc.bind","trace")
			.applySetting("hibernate.type.descriptor.sql","trace");

		Metadata metadata = new MetadataSources( srb.build() )
		// Add your entities here.
			.addAnnotatedClass( InProcessGuaranteeEntity.class )
			.addAnnotatedClass( InProcessPartyEntity.class )
				.addAnnotatedClass( InProcessWorkUnitEntity.class )
			.buildMetadata();

		sf = metadata.buildSessionFactory();
	}

	// Add your tests, using standard JUnit.

	@Test
	public void hhh123Test() throws Exception {
		EntityManager entityManager=sf.createEntityManager();

		entityManager.getTransaction().begin();

		InProcessWorkUnitEntity inProcessWorkUnitEntity = new InProcessWorkUnitEntity();

		inProcessWorkUnitEntity.setRefNo("E313100007923221937");
		inProcessWorkUnitEntity.setSeqNo("001");
		inProcessWorkUnitEntity.setRegBy("PORTAL");

		InProcessPartyEntity inProcessPartyEntity1 =new InProcessPartyEntity();
		inProcessPartyEntity1.setPartyId("6412993");
		inProcessPartyEntity1.setPartyCode("APP");
		inProcessPartyEntity1.setSeqNo("001");
		inProcessPartyEntity1.setRefNo("E313100007923221937");


		InProcessPartyEntity inProcessPartyEntity2 =new InProcessPartyEntity();
		inProcessPartyEntity2.setPartyId("NC");
		inProcessPartyEntity2.setPartyCode("BEN");
		inProcessPartyEntity2.setSeqNo("001");
		inProcessPartyEntity2.setRefNo("E313100007923221937");


		InProcessGuaranteeEntity inProcessGuaranteeEntity =new InProcessGuaranteeEntity();
		inProcessGuaranteeEntity.setEntityId("31");
		inProcessGuaranteeEntity.setGuaranteeNumber("GA3123-15080");
		inProcessGuaranteeEntity.setRefNo("E313100007923221937");
		inProcessGuaranteeEntity.setPartyUnderObligor("PRM");
		inProcessGuaranteeEntity.setSeqNo("001");
		inProcessGuaranteeEntity.setWorkUnit(inProcessWorkUnitEntity);



		Set<InProcessPartyEntity> setOfParties = new HashSet<InProcessPartyEntity>();


		setOfParties.add(inProcessPartyEntity1);
		setOfParties.add(inProcessPartyEntity2);
		inProcessGuaranteeEntity.setParties(setOfParties);



		entityManager.persist( inProcessGuaranteeEntity );



		List<InProcessGuaranteeEntity> mostRecent = entityManager.createQuery("select t from InProcessGuaranteeEntity t " +
						"where t.guaranteeNumber = 'GA3123-15080' " +
						"and exists (select l from InProcessPartyEntity l where l.refNo = t.refNo and l.seqNo=t.seqNo  and l.partyCode = 'APP' and l.partyId in ('6412993'))" )
				.getResultList();

		System.out.println("mostRecent="+mostRecent.size());




	}
}
