/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package library;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Parham Alvani
 */
public class PersonList {
    private final List <Person> persons;
    private final Map <Integer,Person> personMap;

    public PersonList() {
        persons = new ArrayList<>();
        personMap = new HashMap<>();
    }
    
    public void addPerson(Person newPerson){
        persons.add(newPerson);
        personMap.put(newPerson.getIndex(), newPerson);
    }
    
    public Person getPerson(int index){
        return personMap.get(index);
    }
    
    public Person findByName(String fullName){
        for(Person findedPerson : persons){
            if(fullName.equals(findedPerson.getFirstName() + findedPerson.getLastName())){
                return findedPerson;
            }
        }
        return null;
    }
}
