package com.webage.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EventRepositoryTests {

    @Autowired
    private EventRepository eventRepository;

    //--- Test lifecycle ----------------------------------------------------//

    @Before
    public void beforeEachTest(){

    }


    //--- TESTS -----------------------------------------------------//

    @Test
    public void find_all_customers() {
        eventRepository.findAll().forEach(System.out::println);
    }

    @Test
    public void customer__create_and_update() throws Exception {

        long count = eventRepository.count();

    }



} // The End...
