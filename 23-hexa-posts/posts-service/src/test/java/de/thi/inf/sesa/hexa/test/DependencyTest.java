package de.thi.inf.sesa.hexa.test;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchUnitRunner;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.runner.RunWith;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

@RunWith(ArchUnitRunner.class) // Remove this line for JUnit 5!!
@AnalyzeClasses(packages = "de.thi.inf.sesa.hexa")
public class DependencyTest {
    @ArchTest
    static final ArchRule REST_SHOULD_NOT_ACCESS_JPA = noClasses().that().resideInAPackage("..adapter.api..")
            .should().accessClassesThat().resideInAPackage("..adapter.jpa..");
    @ArchTest
    static final ArchRule REST_SHOULD_NOT_ACCESS_MESSAGES = noClasses().that().resideInAPackage("..adapter.api..")
            .should().accessClassesThat().resideInAPackage("..adapter.mqtt..");
    @ArchTest
    static final ArchRule JPA_SHOULD_NOT_ACCESS_REST = noClasses().that().resideInAPackage("..adapter.jpa..")
            .should().accessClassesThat().resideInAPackage("..adapter.api..");
    @ArchTest
    static final ArchRule MESSAGES_SHOULD_NOT_ACCESS_REST = noClasses().that().resideInAPackage("..adapter.mqtt..")
            .should().accessClassesThat().resideInAPackage("..adapter.api..");
}
