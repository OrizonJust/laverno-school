package ru.laverno.entitiy;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Table(schema = "school", name = "sc_t_practice")
public class Practice {

    @Id
    @Column(value = "p_id")
    private final UUID id;

    @Column(value = "p_name")
    private final String name;

    @Column(value = "p_description")
    private final String description;

    @Column(value = "p_order_num")
    private final int orderNum;

    @Column(value = "p_disable")
    private final boolean disable;

    @Column(value = "d_id")
    private final UUID disciplineId;

    public Practice(String name, String description, int orderNum, boolean disable, UUID disciplineId) {
        this(null, name, description, orderNum, disable, disciplineId);
    }

    @PersistenceCreator
    public Practice(UUID id, String name, String description, int orderNum, boolean disable, UUID disciplineId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.orderNum = orderNum;
        this.disable = disable;
        this.disciplineId = disciplineId;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public boolean isDisable() {
        return disable;
    }

    public UUID getDisciplineId() {
        return disciplineId;
    }
}
