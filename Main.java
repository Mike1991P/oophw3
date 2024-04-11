import java.util.Iterator;
import java.util.TreeSet;

public class Main {
    public static String getStudentsPlural(int count) {
        if (count == 1) {
            return "студент";
        } else if (count >= 2 && count <= 4) {
            return "студента";
        } else {
            return "студентов";
        }
    }

    public static void main(String[] args) {
        StudentGroup group1 = new StudentGroup("ТМ-8", "Технология машиностроения");
        group1.addStudent(new Student("Картофанов С.В."));
        group1.addStudent(new Student("Сафонов В.Ю."));
        group1.addStudent(new Student("Савостиков М.У."));
        group1.addStudent(new Student("Легинцев С.С."));

        StudentGroup group2 = new StudentGroup("ВТ-10", "Информационные технологии");
        group2.addStudent(new Student("Залётный И.П."));
        group2.addStudent(new Student("Гуревич Ю.С."));
        group2.addStudent(new Student("Питонов Я.З."));
        group2.addStudent(new Student("Небрежнова А.А."));
        group2.addStudent(new Student("Староверова Н.Ю."));
        group2.addStudent(new Student("Селёдкина Р.М."));

        StudentGroup group3 = new StudentGroup("Ю-12", "Юриcпруденция");
        group3.addStudent(new Student("Сафин И.Я."));
        group3.addStudent(new Student("Панова Г.Ф."));
        group3.addStudent(new Student("Козлова Ю.М."));
        group3.addStudent(new Student("Мухамедзянова Л.А."));
        group3.addStudent(new Student("Романов Р.Р."));

        Stream stream = new Stream(group1, group2, group3);

        for (StudentGroup group : stream) {
            int grSize = group.getGroupSize();
            System.out.printf("В группе '" + group.getGroupName() + "' по специализации '"
                    + group.getGroupSpecialization() + "' учатся %d %s: \n", grSize, getStudentsPlural(grSize));
            int count = 0;
            for (Student student : group) {
                System.out.printf("     %d. %s, ID: %d \n", ++count, student.getName(), student.getId());
            }
        }

        TreeSet<StudentGroup> set1 = new TreeSet<>(new StreamComparator());
        set1.add(stream.getStudentGroup("ТМ-8"));
        set1.add(stream.getStudentGroup("ВТ-10"));
        set1.add(stream.getStudentGroup("Ю-12"));

        System.out.println("Вывод отсортированного множества потоков:");
        System.out.println(set1);

        Iterator<StudentGroup> iterator = stream.iterator();
        while (iterator.hasNext()) {
            StudentGroup stream1 = iterator.next();
            if (stream1.getGroupName().equals("ВТ-10")) {
                iterator.remove();
            }
        }

        System.out.println("Проверка после удаления потока:");
        for (StudentGroup stream1 : stream) {
            System.out.println(stream1.getGroupName());
        }

    }
}