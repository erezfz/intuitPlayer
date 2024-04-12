package intuit.com.web.persistance;

import intuit.com.web.persistance.interfaces.IDAO;
import intuit.com.web.persistance.models.Player;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;


public class DAOMapTest {
    IDAO<Player> dao = DAOMap.getInstance();

    @Test
    void daoAddAllEntities(){

    }
}