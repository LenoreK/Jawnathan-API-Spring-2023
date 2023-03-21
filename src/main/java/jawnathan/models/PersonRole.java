
package jawnathan.models;

public class PersonRole {
    private String personRoleId;
    private String name;

    public PersonRole(String personRoleId, String name) {
        this.personRoleId = personRoleId;
        this.name = name;
    }

    public PersonRole() {
    }

    public String getPersonRoleId() {
        return personRoleId;
    }

    public void setPersonRoleId(String personRoleId) {
        this.personRoleId = personRoleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}