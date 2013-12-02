/* Licensed under the Apache License, Version 2.0 (the "License");
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
package org.activiti.examples.bpmn.event.error;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.test.ActivitiRule;
import org.activiti.engine.*;
import org.junit.*;
import static org.junit.Assert.*;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.Deployment;


/**
 * @author Joram Barrez
 */
public class BoundaryErrorEventTest {
  @Rule public ActivitiRule activitiRule = new ActivitiRule();
  
  @Before
  public void setUp() throws Exception {
    
    // Normally the UI will do this automatically for us
    Authentication.setAuthenticatedUserId("kermit");
  }
  
  @After
  public void tearDown() throws Exception {
    Authentication.setAuthenticatedUserId(null);
  }
  
  @Deployment(resources = {"org/activiti/examples/bpmn/event/error/reviewSalesLead.bpmn20.xml"})
  @Test
  public void testReviewSalesLeadProcess() {
    
    // After starting the process, a task should be assigned to the 'initiator' (normally set by GUI)
    Map<String, Object> variables = new HashMap<String, Object>();
    variables.put("initiator", "kermit");
    RuntimeService runtimeService = activitiRule.getRuntimeService();
    String procId = runtimeService.startProcessInstanceByKey("reviewSaledLead").getId();
    TaskService taskService = activitiRule.getTaskService();
    Task task = taskService.createTaskQuery().taskAssignee("kermit").singleResult();
    assertEquals("Provide new sales lead", task.getName());
    
    // After completing the task, the review subprocess will be active
    taskService.complete(task.getId());
    Task ratingTask = taskService.createTaskQuery().taskCandidateGroup("accountancy").singleResult();
    assertEquals("Review customer rating", ratingTask.getName());
    Task profitabilityTask = taskService.createTaskQuery().taskCandidateGroup("management").singleResult();
    assertEquals("Review profitability", profitabilityTask.getName());
    
    // Complete the management task by stating that not enough info was provided
    // This should throw the error event, which closes the subprocess
    variables = new HashMap<String, Object>();
    variables.put("notEnoughInformation", true);
    taskService.complete(profitabilityTask.getId(), variables);
    
    // The 'provide additional details' task should now be active
    Task provideDetailsTask = taskService.createTaskQuery().taskAssignee("kermit").singleResult();
    assertEquals("Provide additional details", provideDetailsTask.getName());
    
    // Providing more details (ie. completing the task), will activate the subprocess again
    taskService.complete(provideDetailsTask.getId());
    List<Task> reviewTasks = taskService.createTaskQuery().orderByTaskName().asc().list();
    assertEquals("Review customer rating", reviewTasks.get(0).getName());
    assertEquals("Review profitability", reviewTasks.get(1).getName());
    
    // Completing both tasks normally ends the process
    taskService.complete(reviewTasks.get(0).getId());
    variables.put("notEnoughInformation", false);
    taskService.complete(reviewTasks.get(1).getId(), variables);
    assertNull("Process ended", activitiRule.getRuntimeService()
			                               .createProcessInstanceQuery()
			                               .processInstanceId(procId)
			                               .singleResult());    
  }

}
