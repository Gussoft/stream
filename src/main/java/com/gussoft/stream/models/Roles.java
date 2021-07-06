package com.gussoft.stream.models;

import com.gussoft.stream.utils.RolNombre;
import org.springframework.lang.NonNull;

import javax.persistence.*;

@Entity
public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    @Enumerated(EnumType.STRING)
    @Column(unique = true)
    private RolNombre rolNombre;

    public Roles() {
    }

    public Roles(int id, @NonNull RolNombre rolNombre) {
        this.id = id;
        this.rolNombre = rolNombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public RolNombre getRolNombre() {
        return rolNombre;
    }

    public void setRolNombre(@NonNull RolNombre rolNombre) {
        this.rolNombre = rolNombre;
    }
}
