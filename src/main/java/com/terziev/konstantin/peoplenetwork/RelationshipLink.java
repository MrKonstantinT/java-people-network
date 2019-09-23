package com.terziev.konstantin.peoplenetwork;

public class RelationshipLink {
    
    private String linkId;
    private Relationship relationship;

    public RelationshipLink(String email1, String email2, Relationship relationship) {
        this.linkId = email1 + email2;
        this.relationship = relationship;
    }

    public boolean hasRelationship(Relationship relationship) {
        return this.relationship == relationship;
    }

    @Override
    public boolean equals(Object object) {
        return this.linkId.equals(((RelationshipLink) object).linkId);
    }

    @Override
    public String toString() {
        return "[RelationshipLink " + this.linkId + " " + this.relationship.name() + "]";
    }
}
