/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.cloud.task;

import java.util.HashMap;
import java.util.Map;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.task.launcher.TaskLaunchRequest;
import org.springframework.integration.annotation.Transformer;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;

/**
 * @author Glenn Renfro
 */
@EnableBinding(Processor.class)
public class TaskProcessor {


	@Transformer(inputChannel = Processor.INPUT, outputChannel = Processor.OUTPUT)
	public Object setupRequest(Message<?> message) {
		Map<String, String> properties = new HashMap<String,String>();
		properties.put("server.port", "0");
		TaskLaunchRequest request = new TaskLaunchRequest("timestamp-task",
				"org.springframework.cloud.task.module","1.0.0.BUILD-SNAPSHOT", "jar",
				"exec");//, properties);
		GenericMessage<TaskLaunchRequest> taskRequestMessage = new GenericMessage<TaskLaunchRequest>(request);
		return taskRequestMessage;
	}

}
