package com.webage.repository;

import com.webage.domain.Customer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

// Assert-J
// --> assertThat(result.size()).isGreaterThan(0);
// http://joel-costigliola.github.io/assertj/index.html

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class CustomersRepositoryTests {

    @Autowired
    private CustomersRepository customersRepository;

    //--- Test lifecycle ----------------------------------------------------//

    @Before
    public void beforeEachTest(){

    }


    //--- TESTS -----------------------------------------------------//

    @Test
    public void find_all() {
        Iterable<Customer> results = customersRepository.findAll();
        results.forEach(System.out::println);

        assertSoftly(
                softAssertions -> {
                    assertThat(customersRepository.existsById(42L))
                            .isFalse();
                    assertThat(customersRepository.count())
                            .isGreaterThan(1);
                }
        );

    }

    @Test
    public void create_and_update() throws Exception {

        long count = customersRepository.count();

    }



    //---------------------------------------------------------------------------//
    //---------------------------------------------------------------------------//
    //---------------------------------------------------------------------------//


    private Customer createCustomer(){
        long count = customersRepository.count();

        Customer customer = new Customer();

        Customer result = customersRepository.save(customer);

        return result;

    }

    private void deleteIncident(final Customer result){
        customersRepository.delete(result);


    }



} // The End...
