package net.engineering.journalApp.Service;

import net.engineering.journalApp.Entity.User;
import net.engineering.journalApp.Repository.UserRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTests {
    @Autowired
    private UserRepository userRepository;

 @Disabled   //for not test this method
    @Test
    public void testFindByUserName(){
        //assertEquals(4,2+2);
        User user=userRepository.findByUserName("Shiva");
        assertNotNull(userRepository.findByUserName("Shiva"));
        assertTrue(!user.getJournalEntries().isEmpty());
    }

@ParameterizedTest
@CsvSource({
        "1,1,2",
        "2,10,12",
        "3,3,9"
})
    public void test(int a,int b,int expected){
        assertEquals(expected,a+b);
    }
}

