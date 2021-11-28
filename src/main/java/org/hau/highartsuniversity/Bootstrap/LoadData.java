package org.hau.highartsuniversity.Bootstrap;

import lombok.extern.slf4j.Slf4j;
import org.hau.highartsuniversity.Entity.*;
import org.hau.highartsuniversity.Enums.LessonType;
import org.hau.highartsuniversity.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;

@Component
@Slf4j
public class LoadData {

    @Autowired
    AuditoriumService auditoriumService;
    @Autowired
    GroupService groupService;
    @Autowired
    LecturerService lecturerService;
    @Autowired
    LessonService lessonService;
    @Autowired
    StudentService studentService;
    @Autowired
    TimetableService timetableService;

    @PostConstruct
    public void load() {
        log.info("Begin generating data");

        Student.StudentBuilder studentBuilder = Student.builder();
        Lecturer.LecturerBuilder lecturerBuilder = Lecturer.builder();
        Group.GroupBuilder groupBuilder = Group.builder();
        Auditorium.AuditoriumBuilder auditoriumBuilder = Auditorium.builder();
        Lesson.LessonBuilder lessonBuilder = Lesson.builder();

        for (int i = 0; i < 5; i++) {
            groupService.insert(
                    groupBuilder
                            .number(getGroupNumber())
                            .build()
            );
        }
        log.info("Groups were added");

        getFullNames(50).forEach(
                el -> studentService
                        .insert(studentBuilder
                                .fullName(el)
                                .studyYear(getRandomNumber(1, 6))
                                .group(getRandomGroup())
                                .build())
        );
        log.info("Students were added");

        getFullNames(25).forEach(
                el -> lecturerService
                        .insert(lecturerBuilder
                                .fullName(el)
                                .build())
        );
        log.info("Lecturers were added");

        for (int i = 0; i < 50; i++) {
            auditoriumService.insert(
                    auditoriumBuilder
                            .number(getRandomNumber(101, 299))
                            .build()
            );
        }
        log.info("Auditoriums were added");

        getLessonsNames().forEach(
                el -> {
                    Lesson lesson = lessonBuilder
                            .name(el)
                            .lessonType(LessonType.LECTURE)
                            .lecturers(new HashSet<>())
                            .auditorium(getRandomAuditorium())
                            .build();
                    Lecturer lecturer = getRandomLecturer();

                    lesson.getLecturers().add(lecturer);
                    lecturer.getLessons().add(lesson);

                    lessonService.insert(lesson);
                    lecturerService.insert(lecturer);
                }
        );
        log.info("Lessons were added. Lecturers configured. Auditorium configured");

        groupService.findAll().forEach(
                group -> {
                    Timetable.TimetableBuilder timetableBuilder = Timetable.builder();
                    LocalDate firstDay = LocalDate.of(2021, 9, 1);
                    LocalDate lastDay = LocalDate.of(2021, 9, 15);

                    while (!firstDay.isEqual(lastDay)) {
                        if (firstDay.getDayOfWeek().equals(DayOfWeek.SUNDAY) || firstDay.getDayOfWeek().equals(DayOfWeek.SATURDAY)) {
                            timetableService.insert(timetableBuilder
                                    .date(firstDay)
                                    .group(group)
                                    .lessons(new HashSet<>())
                                    .build());
                        } else {
                            timetableService.insert(timetableBuilder
                                    .date(firstDay)
                                    .group(group)
                                    .lessons(getFiveRandomLessons())
                                    .build());
                        }
                        groupService.insert(group);
                        firstDay = firstDay.plusDays(1);
                    }
                }
        );
        log.info("Timetables were added");

    }

    private Set<Lesson> getFiveRandomLessons() {
        Set<Lesson> lessonSet = new HashSet<>();

        List<Lesson> lessonList = new ArrayList<>(lessonService.findAll());

        for (int i = 0; i < 5; i++) {
            lessonSet.add(lessonList.get(getRandomNumber(0, lessonList.size() - 1)));
        }

        return lessonSet;
    }

    private Auditorium getRandomAuditorium() {
        List<Auditorium> auditoriumList = new ArrayList<>(auditoriumService.findAll());
        return auditoriumList.get(getRandomNumber(0, auditoriumList.size() - 1));
    }

    private Lecturer getRandomLecturer() {

        List<Lecturer> lecturers = new ArrayList<>(lecturerService.findAll());
        return lecturers.get(getRandomNumber(0, lecturers.size() - 1));
    }

    private ArrayList<String> getFullNames(int quantity) {
        // nameType can be "firstname", "surname" or "fullname"
        String nameType = "fullname";

        return new ArrayList(
                Arrays.stream(makeRequest(nameType, quantity)
                                .replace("[", "")
                                .replace("]", "")
                                .replace("\"", "")
                                .split(","))
                        .toList()
        );
    }


    private static String makeRequest(String nameType, int quantity) {

        HttpClient client = HttpClient.newBuilder().build();


        try {
            HttpRequest request = HttpRequest
                    .newBuilder(new URI("https://randommer.io/api/Name?nameType=" + nameType + "&quantity=" + quantity))
                    .GET()
                    .setHeader("accept", "*/*")
                    .setHeader("X-Api-Key", "e4a7beb58c224753b69f13fbca07bff8")
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();

        } catch (URISyntaxException | InterruptedException | IOException e) {
            log.error(e.getMessage());
        }

        return null;
    }

    private int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    private int getGroupNumber() {
        return Integer.parseInt("" +
                getRandomNumber(101, 117) + // faculty code
                getRandomNumber(112, 217) + // speciality code
                getRandomNumber(15, 21));   // year
    }

    private Group getRandomGroup() {
        List<Group> groupList = new ArrayList();
        groupService.findAll().forEach(
                groupList::add
        );
        return groupList.get(getRandomNumber(0, groupList.size() - 1));
    }

    private List<String> getLessonsNames() {
        return new ArrayList<>(
                List.of("Agriculture", "Plant and Crop Sciences", "Farm Management", "Horticulture",
                        "Plant and Crop Sciences", "Veterinary Medicine", "Astronomy", "Chemistry",
                        "Food Science and Technology", "Life Sciences", "Physical Geography", "Biology",
                        "Earth Sciences", "Forensic science", "Materials Sciences", "Physics",
                        "Biomedical Sciences", "Environmental Sciences", "General Sciences", "Mathematics",
                        "Sports Science", "Architecture", "Maintenance Services", "Surveying", "Built Environment",
                        "Planning", "Construction", "Property Management", "Accounting", "Entrepreneurship",
                        "Management", "Quality Management", "Business Studies", "Finance", "Marketing",
                        "Retail", "E-Commerce", "Human Resource Management", "Office Administration",
                        "Transportation and Logistics", "Computer Science", "Multimedia", "Computing", "Software", "IT")
        );
    }
}
