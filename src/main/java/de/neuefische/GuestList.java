package de.neuefische;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class GuestList {
    public void setGuests (List<String> guests) throws IOException {
        Path path = Paths.get("guests.txt");
        String guestsTxt = String.join("\n", guests);
        Files.write(path, guestsTxt.getBytes());
    }

    public List<String> getGuests () throws IOException {
        Path path = Paths.get("guests.txt");
        return Files.readAllLines(path);
    }

    public void addGuest (String guest) throws IOException {
        Files.write(
            Paths.get("guests.txt"),
            ("\n" + guest).getBytes(),
            StandardOpenOption.APPEND
        );
    }
}
