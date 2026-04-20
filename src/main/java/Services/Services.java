package Services;

import org.springframework.stereotype.Service;

@Service
public class Services {

	public String HelloService(String name) {
		return "hello world service" + name;
	}
}
