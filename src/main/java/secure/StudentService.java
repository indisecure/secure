package secure;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
@Autowired
private StudentRepo studentRepo;

public Student  save(Student entity) {
	return studentRepo.save(entity);
}

public List<Student> findAll() {
	return studentRepo.findAll();
}

public Student findById(Integer id) {
	return studentRepo.findById(id).orElse(null);
}

public void deleteById(Integer id) {
	studentRepo.deleteById(id);
}
	
}
