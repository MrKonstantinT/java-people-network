package com.terziev.konstantin.peoplenetwork;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class Exercises {

    private Person ariana;
    private Person arnold;
    private Person samuel;
    private Person imogen;
    private People people;
    private PeopleNetwork network;

    @Before
    public void exercise1() throws IOException {
        this.people = new People("people.csv");
        this.network = new PeopleNetwork(this.people, "relationships.csv");
        this.ariana = this.people.findPersonForEmail("ariana@billboards.com");
        this.arnold = this.people.findPersonForEmail("arnold@goldsgym.com");
        this.samuel = this.people.findPersonForEmail("samuel@masterclass.com");
        this.imogen = this.people.findPersonForEmail("imogen@riversidestudios.co.uk");
    }

    @Test
    public void exercise2() throws IOException {
        int expectedPeopleCount = Repo.
            loadRecordsFromResource("people.csv").
            size();
        assertEquals(expectedPeopleCount, this.people.getCount());
    }

    @Test
    public void exercise3() {
        assertEquals(4, this.network.countRelationshipsForPerson(this.ariana));
        assertEquals(3, this.network.countRelationshipsForPerson(this.arnold));
        assertEquals(2, this.network.countRelationshipsForPerson(this.samuel));
        assertEquals(0, this.network.countRelationshipsForPerson(this.imogen));
    }

    @Test
    public void exercise4() {
        assertEquals(4, this.network.countExtendedFamilyForPerson(this.arnold));
        assertEquals(4, this.network.countExtendedFamilyForPerson(this.ariana));
    }
}
