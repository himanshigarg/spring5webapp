package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Publisher penguins = new Publisher("Penguins india ltd","dummy address", "dummy city", "dummy state", "125637");

        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Deriven Design", "123123");

        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        publisherRepository.save(penguins);
        authorRepository.save(eric);
        bookRepository.save(ddd);

        ddd.setPublisher(penguins);
        penguins.getBooks().add(ddd);

        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB","3939398423");
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);

        noEJB.setPublisher(penguins);
        penguins.getBooks().add((noEJB));

        authorRepository.save(rod);
        bookRepository.save(noEJB);

        System.out.println("Started in bootstrap");
        System.out.println("Number of books: " + bookRepository.count());
        System.out.println("Publisher Number of books: " + penguins.getBooks().size());

    }
}
