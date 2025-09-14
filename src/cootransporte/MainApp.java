package cootransporte;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        AdministradorSistemaDAO dao = new AdministradorSistemaDAO();
        Scanner sc = new Scanner(System.in);

        int opcion;
        do {
            System.out.println("\n--- MENÚ COOTRANSPORTE ---");
            System.out.println("1. Insertar administrador");
            System.out.println("2. Consultar por ID");
            System.out.println("3. Listar todos");
            System.out.println("4. Actualizar administrador");
            System.out.println("5. Eliminar administrador");
            System.out.println("0. Salir");
            System.out.print("Elige una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            try {
                switch (opcion) {
                    case 1: // Insertar
                        System.out.print("Nombre: ");
                        String nombre = sc.nextLine();
                        System.out.print("Identificación: ");
                        String identificacion = sc.nextLine();
                        System.out.print("Correo: ");
                        String correo = sc.nextLine();
                        System.out.print("Contraseña: ");
                        String contrasena = sc.nextLine();
                        System.out.print("ID Rol: ");
                        int idRol = sc.nextInt();

                        AdministradorSistema nuevo = new AdministradorSistema(
                                nombre, identificacion, correo, contrasena, idRol
                        );
                        int id = dao.insertar(nuevo);
                        System.out.println("✅ Insertado con id: " + id);
                        break;

                    case 2: // Consultar por id
                        System.out.print("ID a consultar: ");
                        int idBuscar = sc.nextInt();
                        AdministradorSistema encontrado = dao.obtenerPorId(idBuscar);
                        System.out.println(encontrado != null ? encontrado : "No encontrado.");
                        break;

                    case 3: // Listar todos
                        List<AdministradorSistema> lista = dao.obtenerTodos();
                        lista.forEach(System.out::println);
                        break;

                    case 4: // Actualizar
                        System.out.print("ID a actualizar: ");
                        int idActualizar = sc.nextInt();
                        sc.nextLine(); // limpiar buffer

                        AdministradorSistema adminActualizar = dao.obtenerPorId(idActualizar);
                        if (adminActualizar != null) {
                            System.out.print("Nuevo nombre (" + adminActualizar.getNombre() + "): ");
                            adminActualizar.setNombre(sc.nextLine());
                            System.out.print("Nuevo correo (" + adminActualizar.getCorreo() + "): ");
                            adminActualizar.setCorreo(sc.nextLine());
                            System.out.print("Nueva contraseña: ");
                            adminActualizar.setContrasena(sc.nextLine());

                            boolean ok = dao.actualizar(adminActualizar);
                            System.out.println(ok ? "✅ Actualizado." : "❌ No se pudo actualizar.");
                        } else {
                            System.out.println("❌ No existe ese ID.");
                        }
                        break;

                    case 5: // Eliminar
                        System.out.print("ID a eliminar: ");
                        int idEliminar = sc.nextInt();
                        boolean eliminado = dao.eliminar(idEliminar);
                        System.out.println(eliminado ? "✅ Eliminado." : "❌ No se pudo eliminar.");
                        break;

                    case 0:
                        System.out.println("👋 Saliendo...");
                        break;

                    default:
                        System.out.println("❌ Opción inválida.");
                }
            } catch (SQLException e) {
                System.err.println("Error SQL: " + e.getMessage());
            }
        } while (opcion != 0);

        sc.close();
    }
}
