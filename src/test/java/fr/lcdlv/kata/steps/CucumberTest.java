package fr.lcdlv.kata.steps;

import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@TestPropertySource("classpath:application-test.properties")
@ActiveProfiles("test")
@RunWith(Cucumber.class)
@Sql({ "classpath:sql/data.sql" })
@CucumberOptions(features = "classpath:/features", glue = { "fr.lcdlv.kata.steps" })
public class CucumberTest extends SpringIntegrationTest {

}
