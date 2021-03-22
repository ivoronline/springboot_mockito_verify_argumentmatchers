package com.ivoronline.springboot_mockito_verify_argumentmatchers.controllers;

import com.ivoronline.springboot_mockito_verify_argumentmatchers.entities.Person;
import com.ivoronline.springboot_mockito_verify_argumentmatchers.respositories.PersonRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class MyControllerTest {

  //MOCK DEPENDENCY CLASS
  @Mock PersonRepository personRepositoryMock;

  //INJECT MOCKS (where @autowired is used)
  @InjectMocks MyController myController;

  //ENDPOINT
  @Test
  void getPerson() {

    //MOCK REPOSITORY METHOD
    when(personRepositoryMock.getPersonById(1)).thenReturn(new Person(1, "Susan", 50));
    when(personRepositoryMock.getPersonById(2)).thenReturn(new Person(1, "Jill" , 60));

    //CALL REPOSITORY METHOD 2 TIMES WITH ANY INTEGER
    myController.getPerson(1);
    myController.getPerson(2);

    //VERIFY THAT METHOD getPersonById() WAS CALLED EXACTLY 2 TIMES WITH PARAMETER 1
    verify(personRepositoryMock, times(2)).getPersonById(ArgumentMatchers.any(Integer.class));

  }

}
