/*
 * Copyright 2015 Google Inc. All Rights Reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */

package org.jenkinsci.plugins.jenkernetes.workflowsteps;

import org.jenkinsci.plugins.workflow.steps.AbstractStepDescriptorImpl;
import org.kohsuke.stapler.DataBoundConstructor;


import hudson.Extension;

public class KubeGetStep extends KubeStep {


  public final transient String name;
  
  /**
   * @param resource A String that is one of { "pods", "services", "replicationControllers" }
   * @param name A String giving the id ("name") of the resource to be retrieved
   */
  @DataBoundConstructor
  public KubeGetStep(String resource, String name) {
    super(resource);
    this.name = name;
  }

  @Extension public static final class DescriptorImpl extends AbstractStepDescriptorImpl{

    public DescriptorImpl() {
      super(Execution.class);
    }
    
    @Override
    public String getFunctionName(){
      return "kube_get";
    }
    
    @Override
    public String getDisplayName(){
      return "Get a resource using the Kubernetes API";
    }
    
  }
  
  
  public static class Execution extends KubeStepExecution<KubeGetStep>{

    /**
	 * 
	 */
	private static final long serialVersionUID = -1046986151380438848L;

	@Override
    protected Object run() throws Exception {
      return KubernetesClient.get(step.resource + "/" + step.name);
    }
  }
}
