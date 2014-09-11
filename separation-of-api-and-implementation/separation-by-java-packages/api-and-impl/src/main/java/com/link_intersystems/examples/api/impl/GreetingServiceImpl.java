package com.link_intersystems.examples.api.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.link_intersystems.examples.api.GreetingRequest;
import com.link_intersystems.examples.api.GreetingResponse;
import com.link_intersystems.examples.api.GreetingService;
import com.link_intersystems.examples.domain.Person;

@Repository
public class GreetingServiceImpl implements GreetingService {

	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Transactional
	public GreetingResponse sayHello(GreetingRequest greetingRequest) {
		Integer personId = greetingRequest.getPersonId();

		Person person = hibernateTemplate.get(Person.class, personId);

		String greeting = "Hello ";
		if(person == null){
			greeting += "unknown";
		} else {
			greeting += person.getFirstName() + " " + person.getLastName();
		}
		return new GreetingResponse(greeting);
	}

}
