package com.pggm.TaskListReactive;

import com.pggm.TaskListReactive.tasks.models.Task;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Sinks;

@SpringBootApplication
public class TaskListReactiveApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskListReactiveApplication.class, args);
	}




}
