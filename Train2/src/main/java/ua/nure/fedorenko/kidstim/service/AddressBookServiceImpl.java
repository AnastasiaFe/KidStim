package ua.nure.fedorenko.kidstim.service;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tmichels on 8/3/14.
 */
@Service
public class AddressBookServiceImpl implements AddressBookService {

    List<AddressBook> addressBooks = new ArrayList<AddressBook>();
    private static Long id = 0L;

    @PostConstruct
    public void setupAddressBook(){
        AddressBook addressBook1 = new AddressBook();
        addressBook1.setEmail("test@mail.com");
        addressBook1.setFirstName("John");
        addressBook1.setLastName("Doe");
        addressBook1.setPhone("12345678");
        AddressBook addressBook2 = new AddressBook();
        addressBook2.setFirstName("George");
        addressBook2.setLastName("Lucas");
        addressBook2.setPhone("0099332244");
        addressBook2.setEmail("george.lucas@mail.com");
        addressBooks.add(addressBook1);
        addressBooks.add(addressBook2);
    }

    @Override
    public List<AddressBook> viewAllAddressBook() {
        return addressBooks;
    }

    @Override
    public void createAddressBook(AddressBook addressBook) {
        if (!addressBooks.contains(addressBook)){
            addressBook.setId(id);
            addressBooks.add(addressBook);
            ++id;
        }
    }

    @Override
    public void updateAddressBook(int pos, AddressBook updateAddressBook) {
        addressBooks.set(pos, updateAddressBook);
    }

    @Override
    public void deleteAddressBook(int id) {
        addressBooks.remove(id);
    }

    @Override
    public void deleteAllAddressBook() {
        addressBooks.clear();
        id = 0L;
    }

    @Override
    public AddressBook findAddressBook(int id) {
        return addressBooks.get(id);
    }
}
