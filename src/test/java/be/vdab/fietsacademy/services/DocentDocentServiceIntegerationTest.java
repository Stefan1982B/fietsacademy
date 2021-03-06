package be.vdab.fietsacademy.services;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;

import be.vdab.fietsacademy.repositories.DocentRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Import(DefaultDocentService.class)
@ComponentScan(basePackageClasses = DocentRepository.class)
@Sql("/insertDocent.sql")
public class DocentDocentServiceIntegerationTest extends AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	private DefaultDocentService service;
	@Autowired
	private EntityManager manager;

	private long idVanTestMan() {
		return super.jdbcTemplate.queryForObject("select id from docenten where voornaam='testM'", Long.class);
	}

	@Test
	public void opslag() {
		long id = idVanTestMan();
		service.opslag(id, BigDecimal.TEN);
		manager.flush();
		BigDecimal nieuweWedde = super.jdbcTemplate.queryForObject("select wedde from docenten where id=?",
				BigDecimal.class, id);
		assertEquals(0, BigDecimal.valueOf(1_100).compareTo(nieuweWedde));
	}
}
