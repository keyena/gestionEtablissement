package groupe.isi.com;

import groupe.isi.com.gestion.etablissement.config.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = TestConfig.class)
@ActiveProfiles("test")
class IsiApplicationTests {

	@Test
	void contextLoads() {
	}

}
