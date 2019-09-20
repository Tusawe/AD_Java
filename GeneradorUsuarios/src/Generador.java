public class Generador {

    // Base de datos para el generador de personas.
    public static String[] hombres = {"Paco","Manuel", "Pedro", "Jose"};
    public static String[] mujeres = {"Marta", "Maria", "Raquel", "Lucia"};
    public static String[] apellidos = {"Martinez","Gomez","Lopez","Moreno","Castillo","Moya","Hernandez","Rubio"};
    public static String[] emails = {"@gmail.com","@hotmail.com","@yahoo.com","@iesvirgendelcarmen.com"};

    // Generadores.

    public static Usuario generarUsuario(){

        System.out.println("GENERANDO USUARIO:");

        Usuario usuario;

        switch ((int) (Math.random() * 3)) {

            case 0:
                usuario = generarAdmin();
                break;

            case 1:
                usuario = generarAlumno();
                break;

            default:
                usuario = generarProfesor();
                break;

        }

        return usuario;

    }

    public static Admin generarAdmin() {

        System.out.println("Este usuario es administrador/a.");

        Admin admin = new Admin();

        return admin;

    }

    public static Alumno generarAlumno() {

        System.out.println("Este usuario es alumno/a.");

        boolean sexo = generarSexo();
        String nombre = generarNombre(sexo);
        String apellido = generarApellidos();
        String email = generarEmail(nombre, apellido);
        Alumno alumno = new Alumno(nombre, apellido,email);

        return alumno;

    }

    public static Profesor generarProfesor() {

        System.out.println("Este usuario es profesor/a.");

        boolean sexo = generarSexo();
        String nombre = generarNombre(sexo);
        String apellido = generarApellidos();
        String email = generarEmail(nombre, apellido);
        String telefono = generarTelefono();
        String dni = generarDNI();
        Profesor profesor = new Profesor(nombre, apellido, email, telefono, dni);

        return profesor;

    }

    public static boolean generarSexo() {

        if(0 == (int) (Math.random() * 2)) {

            System.out.println("Sexo: Femenino");
            return false;

        } else {

            System.out.println("Sexo: Masculino");
            return true;

        }

    }

    public static String generarNombre(boolean sexo) {

        String nombre;

        if (sexo) {

            nombre = hombres[(int) (Math.random() * 4)];
            System.out.println("Nombre: " + nombre);
            return nombre;

        } else {

            nombre = mujeres[(int) (Math.random() * 4)];
            System.out.println("Nombre: " + nombre);
            return nombre;

        }

    }

    public static String generarApellidos() {

        String apellido1 = apellidos[(int) (Math.random() * 8)];
        String apellido2 = apellidos[(int) (Math.random() * 8)];
        System.out.println("Apellidos: " + apellido1 + " " + apellido2);
        return apellido1 + " " + apellido2;

    }

    public static String generarEmail(String nombre, String apellidos) {

        String email = (nombre.charAt(0) + apellidos.replace(" ","") + emails[(int) (Math.random() * 4)]).toLowerCase();

        System.out.println("Email: " + email);

        return email;

    }

    public static String generarTelefono() {

        String telefono = "6";

        for (int i = 0; i < 8; i++) {

            telefono = telefono + (int) (Math.random() * 10);

        }

        System.out.println("Teléfono: " + telefono);
        return telefono;

    }

    public static String generarDNI() {

        String dni = "";

        for (int i = 0; i < 8; i++) {

            dni = dni + (int) (Math.random() * 10);

        }

        int indice = Integer.parseInt(dni) % 23;

        dni = dni + "TRWAGMYFPDXBNJZSQVHLCKET".charAt(indice);
        System.out.println("DNI: " + dni);

        return dni;

    }

}
