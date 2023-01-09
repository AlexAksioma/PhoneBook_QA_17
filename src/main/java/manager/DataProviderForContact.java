package manager;

import models.Contact;
import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviderForContact {
    @DataProvider
    public Iterator<Object[]> myDPFile() throws IOException {
        List<Object[]> list = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(
                new FileReader(new File("C:/Automation_testing/PhoneBook_QA_17/src/test/resources/dataTests/registration_test.csv")));
        String  line =bufferedReader.readLine();
        while(line !=null){
            String [] split = line.split(",");// ["Lisa"] ["Simpson"] ["Female"]....
            list.add(new Object[]{Contact.builder()
                    .name(split[0])
                    .lastName(split[1])
                    .phone(split[2])
                    .email(split[3])
                    .addres(split[4])
                    .description(split[5])
                    .build()});
            line=bufferedReader.readLine();

        }
        return list.iterator();
    }
    @DataProvider
    public Iterator<Object[]> myDPMethod(){
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{Contact.builder()
                .name("Alex")
                .lastName("Alex123")
                .phone("11111111111")
                .email("Alex123@mail.com")
                .addres("Alex Alex")
                .description("myfriend Alex")
                .build()});

        list.add(new Object[]{Contact.builder()
                .name("Ron")
                .lastName("Ron123")
                .phone("22222222222")
                .email("Ron123@mail.com")
                .addres("Ron address")
                .description("my friend Ron")
                .build()});

        list.add(new Object[]{Contact.builder()
                .name("Sveta")
                .lastName("Sveta123")
                .phone("33333333333")
                .email("Sveta123@mail.com")
                .addres("Sveta Sveta")
                .description("my wife Sveta")
                .build()});

        return list.iterator();
    }
}
