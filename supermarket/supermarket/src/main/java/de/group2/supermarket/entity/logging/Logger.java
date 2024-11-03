package de.group2.supermarket.entity.logging;

public class Logger {
    // Eager Initialization: Die Instanz wird sofort beim Laden der Klasse erstellt
    private static final Logger instance = new Logger();

    // Privater Konstruktor verhindert die Instanziierung von außen
    private Logger() {
        // Initialisierungsaktionen, falls nötig
    }

    // Methode, um die Singleton-Instanz abzurufen
    public static Logger getInstance() {
        return instance;
    }

    // Beispiel einer Logging-Methode
    public void log(String message) {
        System.out.println("LOG: " + message);
    }
}
