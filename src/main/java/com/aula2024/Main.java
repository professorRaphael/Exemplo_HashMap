package com.aula2024;
import com.aula2024.controller.MainController;

public class Main {
    public static void main(String[] args) {
        try {
            MainController controller = new MainController();
            controller.iniciar();
        } catch (Exception e) {
            System.out.println("Ocorreu um erro durante a execução do programa: " + e.getMessage());
        }
    }
}
