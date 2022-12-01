package de.neuefische;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

class GuestListTest {
    @Test
    void shouldBeEmptyInitially () throws IOException {
        // setup
        try {
            Files.delete(Paths.get("guests.txt"));
        } catch (NoSuchFileException e) {
            // Do nothing it's ok
        }

        // given
        GuestList guestList = new GuestList();

        // when
        guestList.setGuests(new ArrayList<>());
        List<String> actual = guestList.getGuests();

        // then
        Assertions.assertEquals(new ArrayList<>(), actual);
    }

    @Test
    void shouldReadSameGuestsAsWrittenBefore () throws IOException {
        // setup
        try {
            Files.delete(Paths.get("guests.txt"));
        } catch (NoSuchFileException e) {
            // Do nothing it's ok
        }

        // given
        GuestList guestList = new GuestList();

        // when
        guestList.setGuests(new ArrayList<>(List.of("Karl", "Ute")));
        List<String> actual = guestList.getGuests();

        // then
        Assertions.assertEquals(List.of("Karl", "Ute"), actual);

    }

    @Test
    void shouldWriteToFileSystem () throws IOException {
        // setup
        try {
            Files.delete(Paths.get("guests.txt"));
        } catch (NoSuchFileException e) {
            // Do nothing it's ok
        }

        // given
        GuestList guestList = new GuestList();

        // when
        guestList.setGuests(new ArrayList<>(List.of("Theodor", "Anette")));

        // then
        Path path = Paths.get("guests.txt");
        Assertions.assertTrue(Files.exists(path));

        List<String> guestListFromFile = Files.readAllLines(path);
        Assertions.assertEquals(
            List.of("Theodor", "Anette"),
            guestListFromFile
        );
    }

    @Test
    void shouldReadFromFileSystem () throws IOException {
        // setup
        try {
            Files.delete(Paths.get("guests.txt"));
        } catch (NoSuchFileException e) {
            // Do nothing it's ok
        }

        // given
        Path path = Paths.get("guests.txt");
        String guestsTxt = "Stephan\nMax";
        Files.write(path, guestsTxt.getBytes());
        GuestList guestList = new GuestList();

        // when
        List<String> guests = guestList.getGuests();

        // then
        Assertions.assertEquals(
            List.of("Stephan", "Max"),
            guests
        );
    }

    @Test
    void shouldThrowExceptionWhenFileDoesNotExist () throws IOException {
        // setup
        try {
            Files.delete(Paths.get("guests.txt"));
        } catch (NoSuchFileException e) {
            // Do nothing it's ok
        }

        // given
        GuestList guestList = new GuestList();

        // then
        Assertions.assertThrows(IOException.class, guestList::getGuests);
    }

    @Test
    void addGuest_shouldAppendGuestAsNewLineToFile () throws IOException {
        // setup
        try {
            Files.delete(Paths.get("guests.txt"));
        } catch (NoSuchFileException e) {
            // Do nothing it's ok
        }

        // given
        Path path = Paths.get("guests.txt");
        String guestsTxt = "Stephan\nMax";
        Files.write(path, guestsTxt.getBytes());
        GuestList guestList = new GuestList();

        // when
        guestList.addGuest("Adrian");

        // then
        List<String> guestListFromFile = Files.readAllLines(path);
        Assertions.assertEquals(
            List.of("Stephan", "Max", "Adrian"),
            guestListFromFile
        );
    }
}