package net.engineering.journalApp.Service;

import net.engineering.journalApp.Entity.JournalEntry;
import net.engineering.journalApp.Entity.User;
import net.engineering.journalApp.Repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
@Service
public class JournalEntryService {
    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;
@Transactional
    public void saveEntry(JournalEntry journalEntry,String userName) {
    try {
        User user = userService.findByUserName(userName);
        journalEntry.setDate(new Date());
        JournalEntry saved = journalEntryRepository.save(journalEntry);
        user.getJournalEntries().add(saved);
       // user.setUserName(null);
        userService.saveUser(user);
    } catch (Exception e) {
        System.out.println(e);
        throw new RuntimeException("an error occured while saving entry. ", e);
    }
}

    public void saveEntry(JournalEntry jounalEntry){
        journalEntryRepository.save(jounalEntry);
    }

    public Optional<JournalEntry> findById(ObjectId id){
       return journalEntryRepository.findById(id);
    }

    public List<JournalEntry> getAll() {
        return journalEntryRepository.findAll();
    }
    @Transactional
    public boolean deleteById(ObjectId id,String userName) {
    boolean removed =false;
        try {
            User user = userService.findByUserName(userName);
            removed = user.getJournalEntries().removeIf(x -> x.getId().equals(id));
            if (removed) {
                userService.saveNewUser(user);
                journalEntryRepository.deleteById(id);
            }
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException("An error occured while deleting the entry.");
        }
        return removed;
    }

}
