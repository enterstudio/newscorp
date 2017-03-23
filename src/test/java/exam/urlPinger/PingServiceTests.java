package exam.urlPinger;
/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PingServiceTests {

    @Autowired
    PingRepository repository;

    @Autowired
    PingService pingService;

    @Before
    public void setUp() {

        repository.deleteAll();

        String [] urls = new String[]{"https://www.google.com.au/", "https://accounts.google.com/", "http://www.facebook.com", "http://test.com"};
        pingService.visitUrl(urls);
    }

    @Test
    public void findPingTest() {

        List<Ping> result = repository.findAttributeWithRegex("response", "Set-Cookie");
        assertTrue(result.size() > 0);
    }


}