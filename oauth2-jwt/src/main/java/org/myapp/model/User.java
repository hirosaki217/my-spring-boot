package org.myapp.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.micrometer.common.lang.NonNull;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;


@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "username")
})

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;


    @NonNull
    private String username;

    @NonNull
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference
    @Column(name = "roles")
    private Set<Role> roles;

}
