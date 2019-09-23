package com.terziev.konstantin.peoplenetwork;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class People {
    
    private List<Person> people;

    public People(String peopleResourceName) throws IOException {
        this.people = Repo.
            loadRecordsFromResource(peopleResourceName).
            stream(). // TODO: check if parallelStream() is more efficient on production hardware.
            map(record -> {
                String name = record.get(0);
                String email = record.get(1);
                int age = Integer.parseInt(record.get(2));
                return new Person(email, name, age);
            }).
            collect(Collectors.toList());
    }

    public Person findPersonForEmail(String email) {
        return people.
            stream().
            filter(person -> person.hasEmail(email)).
            findFirst().
            get();
    }

    public int getCount() {
        return people.size();
    }
}
