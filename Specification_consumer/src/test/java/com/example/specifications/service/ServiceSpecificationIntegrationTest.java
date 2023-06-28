package com.example.specifications.service;

import com.example.AbstractServiceTest;
import com.example.specifications.filter.SearchOperation;
import com.example.specifications.filter.SpecSearchCriteria;
import com.example.specifications.filter.UserSpecification;
import com.example.specifications.model.entity.UserCustom;
import com.example.specifications.repository.UserCustomRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

import static org.assertj.core.api.Assertions.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.isIn;

public class ServiceSpecificationIntegrationTest implements AbstractServiceTest {

    @Autowired
    private UserCustomRepository repository;
    private UserCustom userCustomJohn;
    private UserCustom userCustomTom;

    @BeforeEach
    public void init() {
        userCustomJohn = new UserCustom();
        userCustomJohn.setFirstName("John");
        userCustomJohn.setLastName("Doe");
        userCustomJohn.setEmail("john@doe.com");
        userCustomJohn.setAge(22);
        repository.save(userCustomJohn);

        userCustomTom = new UserCustom();
        userCustomTom.setFirstName("Tom");
        userCustomTom.setLastName("Doe");
        userCustomTom.setEmail("tom@doe.com");
        userCustomTom.setAge(26);
        repository.save(userCustomTom);
    }

    @Test
    public void givenFirstAndLastName_whenGettingListOfUsers_thenCorrect() {
        UserSpecification spec = new UserSpecification(
                new SpecSearchCriteria("firstName", SearchOperation.EQUALITY, "John"));
        UserSpecification spec1 = new UserSpecification(
                new SpecSearchCriteria("lastName", SearchOperation.EQUALITY, "Doe"));
        List<UserCustom> results = repository.findAll(Specification.where(spec).and(spec1));


        assertThat(userCustomJohn, isIn(results));
    }

    @Test
    public void givenFirstNameInverse_whenGettingListOfUsers_thenCorrect() {
        UserSpecification spec = new UserSpecification(
                new SpecSearchCriteria("firstName", SearchOperation.NEGATION, "john"));
        List<UserCustom> results = repository.findAll(Specification.where(spec));

        assertThat(userCustomJohn, isIn(results));

        System.out.println(results);
    }
}
