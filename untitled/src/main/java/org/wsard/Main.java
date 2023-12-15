package org.wsard;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Student {
    private int id;
    public String name;
    public int group;

    public Student(int id, String name, int group) {
        this.id = id;
        this.name = name;
        this.group = group;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return group;
    }

    @Override
    public String toString() {
        return  " ФИО= " + name +
                " Группа= " + group;
    }
}

class StudentManagementSystem {
    private List<Student> students;
    private int nextId;
    private Scanner scanner;

    public StudentManagementSystem() {
        students = new ArrayList<>();
        nextId = 1;
        scanner = new Scanner(System.in);
    }

    public void start() {
        boolean exit = false;

        while (!exit) {
            printMenu();
            int choice = getChoice();

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    deleteStudent();
                    break;
                case 3:
                    editStudent();
                    break;
                case 4:
                    displayAllStudents();
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Ошибка ввода.");
                    break;
            }
        }

        scanner.close();
    }

    private void printMenu() {
        System.out.println("\nМенеджмент студентов");
        System.out.println("1. Добавить студента");
        System.out.println("2. Удалить студента");
        System.out.println("3. Изменить студента");
        System.out.println("4. Показать всех студентов");
        System.out.println("5. Выход");
        System.out.print("Введите число: ");
    }

    private int getChoice() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private void addStudent() {
        System.out.print("\nВведите ФИО: ");
        String name = scanner.nextLine();

        System.out.print("Введите группу: ");
        int group = Integer.parseInt(scanner.nextLine());

        Student student = new Student(nextId++, name, group);
        students.add(student);

        System.out.println("Студент добавлен!");
    }

    private void deleteStudent() {
        System.out.print("\nВведите id студента для удаления: ");
        int id = Integer.parseInt(scanner.nextLine());

        boolean found = false;
        for (Student student : students) {
            if (student.getId() == id) {
                students.remove(student);
                found = true;
                break;
            }
        }

        if (found) {
            System.out.println("Студент удален.");
        } else {
            System.out.println("Студент не найден.");
        }
    }

    private void editStudent() {
        System.out.print("\nВведите Id студента для изменения: ");
        int id = Integer.parseInt(scanner.nextLine());

        boolean found = false;
        for (Student student : students) {
            if (student.getId() == id) {
                System.out.print("Введите новое имя: ");
                String name = scanner.nextLine();
                student.name = name;

                System.out.print("Введите номер группы: ");
                int group = Integer.parseInt(scanner.nextLine());
                student.group = group;

                found = true;
                break;
            }
        }

        if (found) {
            System.out.println("Данные обновлены.");
        } else {
            System.out.println("Студент не найден.");
        }
    }

    private void displayAllStudents() {
        System.out.println("\nВсе студенты:");
        if (students.isEmpty()) {
            System.out.println("Студенты не найдены.");
        } else {
            for (Student student : students) {
                System.out.println(student);
            }
        }
    }

    public static void main(String[] args) {
        StudentManagementSystem studentManagementSystem = new StudentManagementSystem();
        studentManagementSystem.start();
    }
}