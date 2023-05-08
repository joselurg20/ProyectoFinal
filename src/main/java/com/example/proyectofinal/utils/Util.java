package com.example.proyectofinal.utils;

import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Util {
    public static String leeString(String mensaje) {
        Scanner sc = new Scanner (System.in);
        String result = null;
        boolean valid = false;
        do {
            try {
                System.out.print(mensaje);
                result = sc.nextLine();
                valid = true;
            } catch (Exception e) {
                System.out.println("Error. Introduzca una cadena");
                sc.nextLine();
            }
        } while (!valid);

        return result;

    }
    public static int leeEntero(String mensaje) {
        Scanner sc = new Scanner (System.in);
        int result = 0;
        boolean valid = false;
        do {
            try {
                System.out.print(mensaje);
                result = sc.nextInt();
                valid = true;
            } catch (Exception e) {
                System.out.println("Error. Introduzca un entero");
                sc.nextLine();
            }
        } while (!valid);
        return result;
    }
    private static final Pattern REGEXP = Pattern.compile("[0-9]{8}[A-Z]");
    private static final String DIGITO_CONTROL = "TRWAGMYFPDXBNJZSQVHLCKE";
    private static final String[] INVALIDOS = new String[] { "00000000T", "00000001R", "99999999R" };
    private static boolean validarDNI(String dni) {
        return Arrays.binarySearch(INVALIDOS, dni) < 0 // (1)
                && REGEXP.matcher(dni).matches() // (2)
                && dni.charAt(8) == DIGITO_CONTROL.charAt(Integer.parseInt(dni.substring(0, 8)) % 23); // (3)
    }



}
