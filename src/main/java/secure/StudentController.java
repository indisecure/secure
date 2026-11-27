package secure;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
public class StudentController {
	@Autowired
	private StudentService studentService;

	@GetMapping("/login")
    public String loginPage() {
        return "login";
    }
	

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        Model model) {
        if ("indi".equals(username) && "1234".equals(password)) {
            session.setAttribute("user", username);
            return "redirect:/students";
        }
        model.addAttribute("error", "Invalid login");
        return "login";
    }
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/students")
    public String listStudents(HttpSession session, Model model) {
        if (session.getAttribute("user") == null) {
            return "redirect:/login";
        }
        model.addAttribute("data", studentService.findAll());
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
