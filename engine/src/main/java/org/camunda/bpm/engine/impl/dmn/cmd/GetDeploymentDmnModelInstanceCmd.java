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
package org.camunda.bpm.engine.impl.dmn.cmd;

import static org.camunda.bpm.engine.impl.util.EnsureUtil.ensureNotNull;

import org.camunda.bpm.engine.exception.dmn.DmnModelInstanceNotFoundException;
import org.camunda.bpm.engine.impl.cmd.GetDeploymentResourceCmd;
import org.camunda.bpm.engine.impl.context.Context;
import org.camunda.bpm.engine.impl.interceptor.Command;
import org.camunda.bpm.engine.impl.interceptor.CommandContext;
import org.camunda.bpm.model.dmn.DmnModelInstance;

public class GetDeploymentDmnModelInstanceCmd implements Command<DmnModelInstance> {

  protected String decisionDefinitionId;

  public GetDeploymentDmnModelInstanceCmd(String decisionDefinitionId) {
    this.decisionDefinitionId = decisionDefinitionId;
  }

  public DmnModelInstance execute(CommandContext commandContext) {
    ensureNotNull("caseDefinitionId", decisionDefinitionId);

    DmnModelInstance modelInstance = Context
        .getProcessEngineConfiguration()
        .getDeploymentCache()
        .findDmnModelInstanceForDecisionDefinition(decisionDefinitionId);

    ensureNotNull(DmnModelInstanceNotFoundException.class, "No DMN model instance found for decision definition id " + decisionDefinitionId, "modelInstance", modelInstance);
    return modelInstance;
  }

}
