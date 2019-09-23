package com.terziev.konstantin.peoplenetwork;

import java.io.IOException;

import com.google.common.graph.EndpointPair;
import com.google.common.graph.ImmutableNetwork;
import com.google.common.graph.MutableNetwork;
import com.google.common.graph.NetworkBuilder;
import com.google.common.graph.Graphs;

public class PeopleNetwork {

    private ImmutableNetwork<Person, RelationshipLink> network;

    public PeopleNetwork(People people, String relationshipsResourceName) throws IOException {
        MutableNetwork<Person, RelationshipLink> network = NetworkBuilder.
            undirected().
            allowsParallelEdges(true).
            expectedNodeCount(people.getCount()).
            build();
        Repo.
            loadRecordsFromResource(relationshipsResourceName).
            stream(). // TODO: check if parallelStream() is more efficient on production hardware.
            forEach(record -> {
                String personEmail1 = record.get(0);
                Relationship relationship = Relationship.valueOf(record.get(1));
                String personEmail2 = record.get(2);
                Person person1 = people.findPersonForEmail(personEmail1);
                Person person2 = people.findPersonForEmail(personEmail2);
                RelationshipLink link = new RelationshipLink(personEmail1, personEmail2, relationship);
                network.addEdge(person1, person2, link);
            });
        this.network = ImmutableNetwork.copyOf(network);
    }

    /*
        The extended family of a person includes them.

        TODO: find a neat way to just traverse the FAMILY links. Creating a network with only
        FAMILY links is a lot of unnecessary of work as we already have the information in the
        people network.
    */
    public int countExtendedFamilyForPerson(Person person) {
        MutableNetwork<Person, RelationshipLink> mutablefamilyNetwork = NetworkBuilder.
            from(this.network).
            build();
        this.network.
            edges().
            stream().
            filter(link -> link.hasRelationship(Relationship.FAMILY)).
            forEach(link -> {
                EndpointPair<Person> linkPeople = this.network.incidentNodes(link);
                mutablefamilyNetwork.addEdge(linkPeople.nodeU(), linkPeople.nodeV(), link);
            });
        ImmutableNetwork<Person, RelationshipLink> familyNetwork = ImmutableNetwork.copyOf(mutablefamilyNetwork);
        return familyNetwork.nodes().contains(person) ?
            Graphs.reachableNodes(familyNetwork.asGraph(), person).size() :
            1;
    }

    public int countRelationshipsForPerson(Person person) {
        if(this.network.nodes().contains(person)) {
            return this.network.
                incidentEdges(person).
                size();
        }
        return 0;
    }
}
