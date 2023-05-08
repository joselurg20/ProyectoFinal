package com.example.proyectofinal.utils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Scanner;

public class XMLManager {
    public static <T> boolean writeXML(T c, String fichero) {
        boolean result = false;
        JAXBContext context;
        try {
            context =  JAXBContext.newInstance(c.getClass());
            Marshaller m =  context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            m.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            m.marshal(c, new File(fichero));
            result = true;
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return result;
    }
    public static <T> T readXML(T c,String fichero) {
        T result = c;
        JAXBContext context;
        try {
            context = JAXBContext.newInstance(result.getClass());
            Unmarshaller m = context.createUnmarshaller();
            result=(T)m.unmarshal(new File( fichero));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return result;
    }
    /*
   // private static final Pattern REGEXP = Pattern.compile("[0-9]{8}[A-Z]");
    private static final String DIGITO_CONTROL = "TRWAGMYFPDXBNJZSQVHLCKE";
    private static final String[] INVALIDOS = new String[] { "00000000T", "00000001R", "99999999R" };
    /**
     * Metodo que valida el DNI introducido
     * @param dni: el dni a validar
     * @return el dni validado
     */
    /*
    private static boolean validarDNI(String dni) {}
    /**
     * Metodo que valida el DNI introducido
     * @param mensaje: cadena que le muestras al usuario.
     * @return String dni validados
     */

  /*  public static String validaDNI(String mensaje) {
        Scanner sc = new Scanner (System.in);
        String result = null;
        boolean valid = false;
        do {
            try {
                System.out.print(mensaje);
                result = sc.nextLine();
                if(validarDNI(result)) {
                    valid = true;
                }else {
                    System.out.println("DNI invalido.");
                }
            } catch (Exception e) {
                System.out.println("Error. Introduzca un entero");
                sc.nextLine();
            }
        } while (!valid);
        return result;
    }
    public static LocalDate validaFecha(String mensaje) {
        LocalDate result = null;
        return result;
    }
  */
    public static void mensaje(String mensaje) {
        System.out.println(mensaje);
    }




}
