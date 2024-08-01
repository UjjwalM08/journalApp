package net.engineering.journalApp.Repository;
import net.engineering.journalApp.Entity.JournalEntry;
import net.engineering.journalApp.Entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, ObjectId> {
    User findByUserName(String userName);
    User deleteByUserName(String userName);
}
