package com.api.appactivitats.entities.users.domain;

import com.api.appactivitats.entities.activities.dto.ActivityReference;
import com.api.appactivitats.entities.users.domain.records.Address;
import com.api.appactivitats.entities.users.domain.records.ContactInformation;
import com.api.appactivitats.entities.users.domain.records.Phone;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private Validator validator;
    private Address address;
    private Phone phone;
    private ContactInformation contactInformation;
    private User user;

    @BeforeEach
    void setUp() {
        LocalValidatorFactoryBean factoryBean = new LocalValidatorFactoryBean();
        factoryBean.afterPropertiesSet();
        validator = factoryBean.getValidator();

        address = new Address("Valencia", 255, 13, 4,
                "06485", "L'Eixample", "Barcelona", "Spain");
        phone = new Phone(34, 615849762);
    }

    @Test
    void testConstructorValid(){
        contactInformation = new ContactInformation("user@appactivitats.cat", address, phone);
        user = new User("Manel", "Gracia", contactInformation);

        assertNotNull(user.getId(), "ID should have been generated automatically");
        assertEquals("Manel", user.getName(), "Name should have been correctly set");
        assertEquals("Gracia", user.getSurname(), "Surname should have been correctly set");
        assertEquals(contactInformation, user.getContactInformation(), "Contact information should have been correctly set");
        assertNotNull(user.getRegistrationDateTime(), "Registration date should have been set");
        assertEquals(1, user.getLoginCount(), "Login count should have been initialized to 1");
        assertEquals(user.getRegistrationDateTime(), user.getLastLoginDate(), "Last login date should be initialized to registration date");
    }

    @Test
    void testConstructorInvalidName(){
        contactInformation = new ContactInformation("user@appactivitats.cat", address, phone);
        user = new User("M4ne1", "Gracia", contactInformation);

        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertFalse(violations.isEmpty(), "There should be at least one validation error for invalid name");
        violations.forEach(violation -> assertEquals("Name must contain only letters", violation.getMessage()));
    }

    @Test
    void testAddEnrolledActivity() {
        contactInformation = new ContactInformation("user@appactivitats.cat", address, phone);
        user = new User("Manel", "Gracia", contactInformation);

        ActivityReference activity = new ActivityReference(UUID.randomUUID().toString(), "Yoga Class", Instant.now().toString());
        user.getEnrolledActivities().add(activity);
        assertTrue(user.getEnrolledActivities().contains(activity), "Activity should be added to enrolled activities");
    }

    @Test
    void testSettersAndGetters() {
        contactInformation = new ContactInformation("user@appactivitats.cat", address, phone);
        user = new User("Manel", "Gracia", contactInformation);

        user.setName("Laia");
        assertEquals("Laia", user.getName(), "Name should be updated");

        user.setSurname("Herrada");
        assertEquals("Herrada", user.getSurname(), "Surname should be updated");
    }
}