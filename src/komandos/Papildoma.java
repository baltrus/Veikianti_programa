package komandos;

import java.sql.*;
import java.util.Scanner;

/**
 * Created by Baltramiejus Jakstys on 3/16/2017.
 */
public class Papildoma {

    private Connection connection;
    Scanner sc = new Scanner(System.in);

    public Papildoma() {
        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/aktoriai",
                    "root",
                    ""
            );
        } catch (Exception klaida) {
            System.out.println(klaida);
        }
    }

    public void pasisveikinimas() {
        System.out.println("Labas, dirbsite su duomenu baze pavadinimu Aktoriai.");
    }

    public void paklausimas() {
        System.out.println("Pasirinkite vieną iš funkcijų, kurią norite atlikti su duotąja duomenu baze. Spauskite vieną iš pateiktų variantų");
        System.out.println("1) Atvaizduoti duomenų bazę,   2) Ištrinti pasirinktą laukelį,   3) Pridėti informaciją į pasirinktą laukelį");
    }

    public void sprendimas() {
        int pasirinkimas = sc.nextInt();
        switch (pasirinkimas) {
            case 1:
                duombazes_atvaizdavimas();
                break;

            case 2:
                istrynimas_is_duombazes();
                break;

            case 3:
                pridejimas_i_duombaze();
                break;
        }
    }

    public void pridejimas_i_duombaze() {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO `aktoriai` (`vardas`, `pavarde`, `amzius`) VALUES (?, ?, ?)");
            System.out.println("Ivesti Varda");
            String vardas = sc.next();
            System.out.println("Ivesti Pavarde");
            String pavarde = sc.next();
            System.out.println("Ivesti Amžių");
            String amzius = sc.next();
            statement.setString(1, vardas);
            statement.setString(2, pavarde);
            statement.setString(3, amzius);
            statement.executeUpdate();
        } catch (Exception klaida) {
            System.out.println(klaida);
        }
    }

    private void istrynimas_is_duombazes() {
        try {
            System.out.println("Ivesti ID aktoriaus kuri norite istrinti");
            int id = sc.nextInt();
            PreparedStatement statement = connection.prepareStatement("DELETE FROM `aktoriai` WHERE `aktoriai`.`id` = " + id + ";");
            statement.executeUpdate();
        } catch (Exception klaida) {
            System.out.println(klaida);
        }
    }
    public void duombazes_atvaizdavimas() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM `aktoriai`");
            while (resultSet.next()) {
                System.out.print(resultSet.getInt("id"));
                System.out.print(" | ");
                System.out.print(resultSet.getString("vardas"));
                System.out.print(" ");
                System.out.print(resultSet.getString("pavarde"));
                System.out.print(" ");
                System.out.print(resultSet.getString("amzius"));
                System.out.print("");
                System.out.println();
            }
        } catch (Exception error) {
            System.out.println(error);
        }

    }
}
