package de.group2.supermarket.controller;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.group2.supermarket.entity.logging.Logger; 

@RestController
@RequestMapping("/log") // Basis-URL für die Logging-Anfragen
public class LogController {

    private final Logger logger = Logger.getInstance(); // Logger-Instanz

    // GET-Anfrage für Test-Logging und um alle Logs zurückzugeben
    @GetMapping("")
    public List<String> getLogs() {
        return logger.getLogMessages(); // Gibt die gespeicherten Log-Nachrichten zurück
    }
    
    // POST-Anfrage für das Logging von Nachrichten
    @PostMapping("")
    public String logMessage(@RequestBody LogRequest logRequest) {
        logger.log(logRequest.getMessage()); // Protokollierung der Nachricht mit der Logger-Instanz
        return "Log-Nachricht erfolgreich protokolliert.";
    }

    // Klasse zur Darstellung der eingehenden Log-Anfrage
    public static class LogRequest {
        private String message;

        
        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
