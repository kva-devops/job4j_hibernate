package ru.job4j.cars;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "driver")
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private Set<Car> cars;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "history_owner",
            joinColumns = {
                    @JoinColumn(name = "car_id", nullable = false, updatable = false)
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "driver_id", nullable = false, updatable = false)
            }
    )
    public Set<Car> getCars() {
        return cars;
    }
}
