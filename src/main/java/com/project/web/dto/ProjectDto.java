package com.project.web.dto;

import com.project.web.models.UserEntity;
import lombok.Builder;
import lombok.Data;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Data
@Builder
public class ProjectDto {

    private Long id;
    @NotEmpty(message = "... should not be empty")
    private String name; //nome
    @NotEmpty(message = "Content should not be empty")
    private String manager; //gerente

    private LocalDateTime startDate; //data inicio
    private LocalDateTime finalDate; //data fim
    private LocalDateTime dateFinalReal; //data real fim

    private Double totalBudget;
    private String description;
    private String status;

    private UserEntity createdBy;
}
