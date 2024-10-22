package com.project.web.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name; //nome
    private String manager; //gerente

    private LocalDateTime startDate; //data inicio
    private LocalDateTime finalDate; //data fim
    private LocalDateTime dateFinalReal; //data real fim

    private Double totalBudget;
    private String description;
    private String status;

    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    private UserEntity createdBy;
}
