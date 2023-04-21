package ru.laverno.controller;

import org.springframework.web.bind.annotation.*;
import ru.laverno.entitiy.Discipline;
import ru.laverno.exception.DataAlreadyExistsException;
import ru.laverno.exception.DataNotFoundException;
import ru.laverno.exception.ParamValidationException;
import ru.laverno.model.BasicResponse;
import ru.laverno.model.discipline.DisciplineRequest;
import ru.laverno.service.discipline.DisciplineService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/discipline")
public class DisciplineController {

    private final DisciplineService disciplineService;

    public DisciplineController(DisciplineService disciplineService) {
        this.disciplineService = disciplineService;
    }

    @GetMapping("/{id}")
    public BasicResponse<Discipline> getDisciplineById(@PathVariable(name = "id") final String id) {
        final var response = new BasicResponse<Discipline>();
        try {
            response.setData(disciplineService.getDisciplineById(UUID.fromString(id)));
        } catch (IllegalArgumentException ex) {
            response.setFault("Получен некорректный [id=%s]!", id);
        } catch (DataNotFoundException ex) {
            response.setFault(ex.getMessage());
        }

        return response;
    }

    @GetMapping("/name/{name}")
    public BasicResponse<Discipline> getDisciplineByName(@PathVariable(name = "name") final String name) {
        final var response = new BasicResponse<Discipline>();
        try {
            response.setData(disciplineService.getDisciplineByName(name));
        } catch (ParamValidationException | DataNotFoundException ex) {
            response.setFault(ex.getMessage());
        }

        return response;
    }

    @GetMapping("/")
    public BasicResponse<List<Discipline>> getDisciplines() {
        final var response = new BasicResponse<List<Discipline>>();
        try {
            response.setData(disciplineService.getDisciplines());
        } catch (DataNotFoundException ex) {
            response.setFault(ex.getMessage());
        }

        return response;
    }

    @PostMapping("/")
    public BasicResponse<Discipline> addNewDiscipline(@RequestBody final DisciplineRequest discipline) {
        final var response = new BasicResponse<Discipline>();
        try {
            response.setData(disciplineService.addNewDiscipline(discipline));
        } catch (ParamValidationException | DataAlreadyExistsException ex) {
            response.setFault(ex.getMessage());
        }

        return response;
    }

    @PutMapping("/")
    public BasicResponse<Discipline> editDiscipline(@RequestBody final DisciplineRequest discipline) {
        final var response = new BasicResponse<Discipline>();
        try {
            response.setData(disciplineService.editDiscipline(discipline));
        } catch (ParamValidationException | DataNotFoundException | DataAlreadyExistsException ex) {
            response.setFault(ex.getMessage());
        }

        return response;
    }

    @DeleteMapping("/{id}")
    public BasicResponse<Boolean> deleteDiscipline(@PathVariable(value = "id") final String id) {
        final var response = new BasicResponse<Boolean>();
        try {
            response.setData(disciplineService.deleteDiscipline(UUID.fromString(id)));
        } catch (IllegalArgumentException ex) {
            response.setFault("Получен некорректный [id=%s]!", id);
        } catch (DataNotFoundException ex) {
            response.setFault(ex.getMessage());
        }

        return response;
    }
}
