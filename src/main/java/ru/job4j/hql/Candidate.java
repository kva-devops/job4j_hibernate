package ru.job4j.hql;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "candidates")
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String experience;

    private int salary;

    @OneToOne(fetch = FetchType.LAZY)
    private BaseVacancies baseVacancies;

    public Candidate() {
    }

    public Candidate(String name, String experience, int salary) {
        this.name = name;
        this.experience = experience;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public BaseVacancies getBaseVacancies() {
        return baseVacancies;
    }

    public void setBaseVacancies(BaseVacancies baseVacancies) {
        this.baseVacancies = baseVacancies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Candidate candidate = (Candidate) o;
        return id == candidate.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, experience, salary);
    }

    @Override
    public String toString() {
        return "Candidate{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", experience='" + experience + '\''
                + ", salary=" + salary
                + ", baseVacancies=" + baseVacancies
                + '}';
    }
}
