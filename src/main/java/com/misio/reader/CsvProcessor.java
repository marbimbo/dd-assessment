package com.misio.reader;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.misio.client.PersonClient;
import com.misio.model.Person;
import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.lang.invoke.MethodHandles;

@Component
public class CsvProcessor implements ApplicationListener<ApplicationStartedEvent> {

    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private String fileName;
    private String csvName;
    private String password;

    private final PersonClient personClient;

    @Value("${fileName}")
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Value("${csvName}")
    public void setCsvName(String csvName) {
        this.csvName = csvName;
    }

    @Value("${password}")
    public void setPassword(String password) {
        this.password = password;
    }

    @Autowired
    public CsvProcessor(PersonClient personClient) {
        this.personClient = personClient;
    }

    @Override
    public void onApplicationEvent(ApplicationStartedEvent applicationStartedEvent) {
        LOG.info("Uncompress and decrypt");
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        LOG.info(classloader.getResource(fileName).getPath());

        String property = "java.io.tmpdir";

        // Get the temporary directory and print it.
        String tempDir = System.getProperty(property);

        try {
            new ZipFile(classloader.getResource(fileName).getPath(), password.toCharArray()).extractFile(csvName, tempDir);
        } catch (ZipException e) {
            e.printStackTrace();
        }
        LOG.info("Decrypted");

        LOG.info("Read the file");
        MappingIterator<Person> personIter = null;
        CsvSchema schema = CsvSchema.emptySchema().withHeader().withColumnSeparator(';').withArrayElementSeparator(", ").withNullValue("");
        try {
            personIter = new CsvMapper().readerFor(Person.class).with(schema).readValues(new File(tempDir + "/" + csvName));
            Person p;

            while (personIter.hasNext()) {
                p = personIter.next();
                if (validateEmail(p.getFirstName(), p.getLastName(), p.getEmail())) {
                    personClient.send(p);
                } else {
                    LOG.warn("wrong email: {}", p.getEmail());
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean validateEmail(String firstName, String lastName, String email) {
        return email.equals(firstName + "." + lastName + "@domain.com");
    }
}
