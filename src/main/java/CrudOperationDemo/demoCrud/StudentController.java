package CrudOperationDemo.demoCrud;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {
	@Autowired
	private StudentRepo sRepo;
	
	@PostMapping("/std")
	public ResponseEntity<Student> create(@RequestBody Student std){
		return new ResponseEntity<Student>(sRepo.save(std),HttpStatus.CREATED);
	}
	
	@GetMapping("/api/std")
	public ResponseEntity<List<Student>> getAll(){
		return new ResponseEntity<>(sRepo.findAll(),HttpStatus.OK);
	}
	
	@PutMapping("/api/std/{id}")
	public ResponseEntity<Student> update(@PathVariable Integer id,@RequestBody Student std){
		Optional<Student >obj=sRepo.findById(id);
		if(obj.isPresent()) {
			obj.get().setName(std.getName());
			obj.get().setRollno(std.getRollno());
			return new ResponseEntity<Student>(sRepo.save(obj.get()),HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Student>(HttpStatus.OK);
		}
		
	}
	
	@DeleteMapping("/student/api/{id}")
	public ResponseEntity<Student> delete(@PathVariable Integer id){
		Optional op=sRepo.findById(id);
		if(op.isPresent()) {
			sRepo.deleteById(id);
			return new ResponseEntity<Student>(HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Student>(HttpStatus.OK);
		}
	}

}
