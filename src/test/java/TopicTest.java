import com.osipchik.service.repository.TopicRepository;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TopicTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private TopicRepository topicRepository;


    /*@Autowired
    private TopicRepository topicRepository;
    @Test
    public void saveTopic() throws Exception{
        Topic topic = new Topic();
        topic.setName("test topic");
        topicRepository.save(topic);
        Assert.assertNotNull(topicRepository.findTopicByName(topic.getName()));
    }*/



}
