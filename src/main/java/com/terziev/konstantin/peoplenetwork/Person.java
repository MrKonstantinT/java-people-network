package com.terziev.konstantin.peoplenetwork;

public class Person {

    private String email;
    private String name;
    private int age;

    public Person(String email, String name, int age) {
        this.email = email;
        this.name = name;
        this.age = age;
    }

    public boolean hasEmail(String email) {
        return this.email.equals(email);
    }

    @Override
    public boolean equals(Object object) {
        return this.email.equals(((Person) object).email);
    }

    @Override
    public String toString() {
        return "[Person " + this.name + " " + this.email + ", age " + this.age +"]";
    }
}
