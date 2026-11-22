package secure;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StudentController {
	@Autowired
	private StudentService studentService;


	@GetMapping("/students")
	public String listStudents(Model model) {
		List<Student> students = studentService.findAll();
		model.addAttribute("data", students);
		return "students";
	}

	@GetMapping("/students/new")
	public String showNewStudentForm(Model model) {
		model.addAttribute("student", new Student());
		return "new_student";
	}

	@GetMapping("/students/edit/{id}")
	public String showEditLaptopForm(@PathVariable("id") int id, Model model) {
		Student student = studentService.findById(id);
		model.addAttribute("student", student);
		return "edit_student";
	}

	@PostMapping("/students")
	public String saveLaptop(@ModelAttribute Student student) {
		studentService.save(student);
		return "redirect:/students";
	}

	@GetMapping("/students/{id}/delete")
	public String deleteLaptop(@PathVariable("id") int id) {
		studentService.deleteById(id);
		return "redirect:/students";
	}

}
