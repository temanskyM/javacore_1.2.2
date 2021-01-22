import models.Education;
import models.Person;
import models.Sex;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        final int MAX_PERSON = 10_000_000;
        //Генерируем записи
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < MAX_PERSON; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        System.out.println("Количество людей: " + MAX_PERSON);

        //Обрабатываем записи
        //Найти количество несовершеннолетних (т.е. людей младше 18 лет).
        int countAdult = 0;
        countAdult = (int) persons.stream().filter((x) -> x.getAge() < 18).count();
        System.out.println("Количество несовершеннолетних: " + countAdult);

        //Получить список фамилий призывников (т.е. мужчин от 18 и до 27 лет).
        List<String> lastnameRecruits = persons.stream().
                filter((x) -> (x.getAge() <= 27 && x.getAge() >= 17))
                .map(Person::toString)
                .collect(Collectors.toList());
        System.out.println("Количество призывников: " + lastnameRecruits.size());

        //Получить отсортированный по фамилии список потенциально работоспособных людей с высшим образованием в выборке (т.е. людей с высшим образованием от 18 до 60 лет для женщин и до 65 лет для мужчин).
        List<Person> workerList = persons.stream().
                filter(
                        x -> ((x.getAge() <= 65 && x.getAge() >= 18 && x.getSex().equals(Sex.MAN)) ||
                                (x.getAge() <= 60 && x.getAge() >= 18 && x.getSex().equals(Sex.WOMEN))) &&
                                x.getEducation().equals(Education.HIGHER))
                .collect(Collectors.toList());
        System.out.println("Количество потенциально работоспособных людей с высшим образованием: " + workerList.size());
    }
}